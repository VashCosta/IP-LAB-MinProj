package com.tnsymposium;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request,HttpServletResponse response)throws IOException{
        String email=request.getParameter("email"),password=request.getParameter("password");
        String sql="SELECT id,name,email,phone,college,department,year_of_study,password_hash FROM users WHERE email=?";
        try(Connection con=DBConnection.getConnection();PreparedStatement ps=con.prepareStatement(sql)){
            ps.setString(1,email.trim());ResultSet rs=ps.executeQuery();
            if(rs.next()&&PasswordUtil.sha256(password).equals(rs.getString("password_hash"))){
                HttpSession session=request.getSession(true);
                session.setAttribute("userId",rs.getInt("id"));session.setAttribute("userName",rs.getString("name"));session.setAttribute("userEmail",rs.getString("email"));session.setAttribute("userPhone",rs.getString("phone"));session.setAttribute("userCollege",rs.getString("college"));session.setAttribute("userDepartment",rs.getString("department"));session.setAttribute("userYear",rs.getString("year_of_study"));
                response.sendRedirect("dashboard.jsp");
            }else response.sendRedirect("login.jsp?error=invalid");
        }catch(Exception e){e.printStackTrace();response.sendRedirect("login.jsp?error=server");}
    }
}
