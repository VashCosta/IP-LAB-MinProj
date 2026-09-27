package com.tnsymposium;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request,HttpServletResponse response)throws IOException{
        String name=request.getParameter("name"),email=request.getParameter("email"),phone=request.getParameter("phone"),college=request.getParameter("college"),department=request.getParameter("department"),year=request.getParameter("year"),password=request.getParameter("password");
        if(name==null||email==null||phone==null||college==null||department==null||year==null||password==null||name.isBlank()||email.isBlank()||phone.isBlank()||college.isBlank()||department.isBlank()||year.isBlank()||password.isBlank()){response.sendRedirect("register.jsp?error=missing");return;}
        String checkSql="SELECT id FROM users WHERE email=?";
        String insertSql="INSERT INTO users(name,email,phone,college,department,year_of_study,password_hash) VALUES(?,?,?,?,?,?,?)";
        try(Connection con=DBConnection.getConnection();PreparedStatement check=con.prepareStatement(checkSql)){
            check.setString(1,email.trim());ResultSet rs=check.executeQuery();if(rs.next()){response.sendRedirect("register.jsp?error=email");return;}
            try(PreparedStatement ps=con.prepareStatement(insertSql)){ps.setString(1,name.trim());ps.setString(2,email.trim());ps.setString(3,phone.trim());ps.setString(4,college.trim());ps.setString(5,department.trim());ps.setString(6,year.trim());ps.setString(7,PasswordUtil.sha256(password));ps.executeUpdate();}
            response.sendRedirect("login.jsp?success=registered");
        }catch(Exception e){e.printStackTrace();response.sendRedirect("register.jsp?error=server");}
    }
}
