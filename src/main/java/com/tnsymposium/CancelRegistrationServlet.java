package com.tnsymposium;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@WebServlet("/cancel-registration")
public class CancelRegistrationServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("userId") == null) { response.sendRedirect("login.jsp"); return; }
        int userId = (Integer) session.getAttribute("userId");
        int registrationId;
        try { registrationId = Integer.parseInt(request.getParameter("registrationId")); }
        catch (Exception e) { response.sendRedirect("myRegistrations"); return; }
        String find = "SELECT event_id FROM registrations WHERE id=? AND user_id=? FOR UPDATE";
        String delete = "DELETE FROM registrations WHERE id=? AND user_id=?";
        String seat = "UPDATE events SET available_seats=available_seats+1 WHERE id=?";
        try (Connection con = DBConnection.getConnection()) {
            con.setAutoCommit(false);
            try {
                int eventId=-1;
                try (PreparedStatement ps=con.prepareStatement(find)) { ps.setInt(1,registrationId); ps.setInt(2,userId); try(ResultSet rs=ps.executeQuery()){ if(rs.next()) eventId=rs.getInt("event_id"); } }
                if(eventId!=-1){
                    try(PreparedStatement ps=con.prepareStatement(delete)){ ps.setInt(1,registrationId); ps.setInt(2,userId); ps.executeUpdate(); }
                    try(PreparedStatement ps=con.prepareStatement(seat)){ ps.setInt(1,eventId); ps.executeUpdate(); }
                }
                con.commit(); response.sendRedirect("myRegistrations?success=cancelled");
            } catch(Exception e){ con.rollback(); throw e; } finally { con.setAutoCommit(true); }
        } catch(Exception e){ e.printStackTrace(); response.sendRedirect("myRegistrations?error=server"); }
    }
}
