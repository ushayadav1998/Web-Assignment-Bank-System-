package com.org;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public Login() {
        super();
        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String CustomerId=request.getParameter("CustomerId");
			String Password =request.getParameter("Password");
			String dbId=null;
			String dbPassword=null;
			
			String sql="select * from Customer where CustomerId=? and Password=?";
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "root");
			PreparedStatement ps=con.prepareStatement(sql);
			ps.setString(1, CustomerId);
			ps.setString(2, Password);
			ResultSet rs=ps.executeQuery();
			PrintWriter out=response.getWriter();
		
			while(rs.next()) {
				dbId=rs.getString("CustomerId");
				dbPassword=rs.getString("Password");
			}
			if(CustomerId.equals(dbId) && Password.equals(dbPassword)) {
				out.println("You Have Successfully registered");
				
				
				response.sendRedirect("account.html");
			}
			
			else{
				RequestDispatcher rd=request.getRequestDispatcher("login.jsp");
				
				rd.include(request, response);
			
			  }
			
			
			
			
			
		} catch (ClassNotFoundException e) {
			
			e.printStackTrace();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	}

}
