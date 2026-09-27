package com.tnsymposium;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@WebServlet("/register-event")
public class EventRegistrationPageServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (request.getSession(false) == null || request.getSession(false).getAttribute("userId") == null) {
            response.sendRedirect("login.jsp");
            return;
        }
        int eventId;
        try { eventId = Integer.parseInt(request.getParameter("id")); }
        catch (Exception e) { response.sendRedirect("events"); return; }

        String sql = "SELECT id,event_name,category,description,event_date,start_time,venue,city,fee,available_seats FROM events WHERE id=?";
        try (Connection con = DBConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, eventId);
            ResultSet rs = ps.executeQuery();
            if (!rs.next()) { response.sendRedirect("events"); return; }
            Event event = new Event(rs.getInt("id"),rs.getString("event_name"),rs.getString("category"),rs.getString("description"),rs.getDate("event_date").toString(),rs.getTime("start_time").toString().substring(0,5),rs.getString("venue"),rs.getString("city"),rs.getDouble("fee"),rs.getInt("available_seats"));
            request.setAttribute("event", event);
            request.getRequestDispatcher("event-register.jsp").forward(request, response);
        } catch (Exception e) { e.printStackTrace(); response.sendRedirect("events"); }
    }
}
