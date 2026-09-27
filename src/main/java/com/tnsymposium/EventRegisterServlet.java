package com.tnsymposium;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.UUID;

@WebServlet("/submit-registration")
public class EventRegisterServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request,HttpServletResponse response) throws IOException {
        HttpSession session=request.getSession(false);
        if(session==null || session.getAttribute("userId")==null){response.sendRedirect("login.jsp");return;}
        int userId=(Integer)session.getAttribute("userId");
        int eventId;
        try{eventId=Integer.parseInt(request.getParameter("eventId"));}catch(Exception e){response.sendRedirect("events");return;}
        String participationType=request.getParameter("participationType");
        String teamName=request.getParameter("teamName");
        if(participationType==null||participationType.isBlank()) participationType="Individual";
        if("Individual".equalsIgnoreCase(participationType)) teamName=null;
        String checkDuplicate="SELECT id FROM registrations WHERE user_id=? AND event_id=?";
        String lockEvent="SELECT available_seats FROM events WHERE id=? FOR UPDATE";
        String insert="INSERT INTO registrations(user_id,event_id,registration_code,participation_type,team_name) VALUES(?,?,?,?,?)";
        String updateSeats="UPDATE events SET available_seats=available_seats-1 WHERE id=?";
        try(Connection con=DBConnection.getConnection()){
            con.setAutoCommit(false);
            try{
                try(PreparedStatement dup=con.prepareStatement(checkDuplicate)){dup.setInt(1,userId);dup.setInt(2,eventId);try(ResultSet rs=dup.executeQuery()){if(rs.next()){con.rollback();response.sendRedirect("events?error=duplicate");return;}}}
                int seats;
                try(PreparedStatement lock=con.prepareStatement(lockEvent)){lock.setInt(1,eventId);try(ResultSet rs=lock.executeQuery()){if(!rs.next()){con.rollback();response.sendRedirect("events");return;}seats=rs.getInt("available_seats");}}
                if(seats<=0){con.rollback();response.sendRedirect("events?error=full");return;}
                String code="TN26-"+UUID.randomUUID().toString().substring(0,8).toUpperCase();
                try(PreparedStatement ps=con.prepareStatement(insert);PreparedStatement seatPs=con.prepareStatement(updateSeats)){
                    ps.setInt(1,userId);ps.setInt(2,eventId);ps.setString(3,code);ps.setString(4,participationType);ps.setString(5,teamName);ps.executeUpdate();
                    seatPs.setInt(1,eventId);seatPs.executeUpdate();
                }
                con.commit();response.sendRedirect("myRegistrations?success=registered");
            }catch(Exception e){con.rollback();throw e;}finally{con.setAutoCommit(true);}
        }catch(Exception e){e.printStackTrace();response.sendRedirect("events?error=server");}
    }
}
