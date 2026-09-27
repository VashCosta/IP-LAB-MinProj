package com.tnsymposium;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/myRegistrations")
public class MyRegistrationsServlet extends HttpServlet {
    @Override protected void doGet(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException{
        HttpSession session=request.getSession(false);if(session==null||session.getAttribute("userId")==null){response.sendRedirect("login.jsp");return;}
        int userId=(Integer)session.getAttribute("userId");List<Registration> registrations=new ArrayList<>();
        String sql="SELECT r.id,e.event_name,e.category,e.event_date,e.start_time,e.venue,e.city,r.registration_code,r.registered_at,r.participation_type,r.team_name FROM registrations r JOIN events e ON r.event_id=e.id WHERE r.user_id=? ORDER BY e.event_date,e.start_time";
        try(Connection con=DBConnection.getConnection();PreparedStatement ps=con.prepareStatement(sql)){
            ps.setInt(1,userId);ResultSet rs=ps.executeQuery();
            while(rs.next()){Registration r=new Registration();r.setId(rs.getInt("id"));r.setEventName(rs.getString("event_name"));r.setCategory(rs.getString("category"));r.setEventDate(rs.getDate("event_date").toString());r.setStartTime(rs.getTime("start_time").toString().substring(0,5));r.setVenue(rs.getString("venue"));r.setCity(rs.getString("city"));r.setRegistrationCode(rs.getString("registration_code"));r.setRegisteredAt(rs.getTimestamp("registered_at").toString());r.setParticipationType(rs.getString("participation_type"));r.setTeamName(rs.getString("team_name"));registrations.add(r);}
            request.setAttribute("registrations",registrations);request.getRequestDispatcher("my-registrations.jsp").forward(request,response);
        }catch(Exception e){e.printStackTrace();response.getWriter().println("Unable to load registrations.");}
    }
}
