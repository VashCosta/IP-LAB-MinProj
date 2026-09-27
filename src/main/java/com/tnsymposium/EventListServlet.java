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
import java.util.ArrayList;
import java.util.List;

@WebServlet("/events")
public class EventListServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException {
        List<Event> events=new ArrayList<>();
        String sql="SELECT id,event_name,category,description,event_date,start_time,venue,city,fee,available_seats FROM events ORDER BY event_date,start_time";
        try(Connection con=DBConnection.getConnection();PreparedStatement ps=con.prepareStatement(sql);ResultSet rs=ps.executeQuery()){
            while(rs.next()) events.add(new Event(rs.getInt("id"),rs.getString("event_name"),rs.getString("category"),rs.getString("description"),rs.getDate("event_date").toString(),rs.getTime("start_time").toString().substring(0,5),rs.getString("venue"),rs.getString("city"),rs.getDouble("fee"),rs.getInt("available_seats")));
            request.setAttribute("events",events);
            request.getRequestDispatcher("events.jsp").forward(request,response);
        }catch(Exception e){e.printStackTrace();response.getWriter().println("Unable to load events. Check the database connection.");}
    }
}
