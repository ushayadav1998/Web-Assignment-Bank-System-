package com.org;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public Register() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String CustomerId=request.getParameter("CustomerId");
			String Password =request.getParameter("Password");
			String CustomerName=request.getParameter("CustomerName");
			
			
			String sql="insert into Customer(CustomerId,Password,CustomerName) values(?,?,?)";
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "root");
			PreparedStatement ps=con.prepareStatement(sql);
			ps.setString(1, CustomerId);
			ps.setString(2, Password);
			ps.setString(3, CustomerName);
			ps.executeUpdate();
			PrintWriter out=response.getWriter();
			out.println("You Have Successfully registered");
			out.println("Thank you for register ! Please <a href='login.jsp'>Login</a> to continue.");
			
		} catch (ClassNotFoundException e) {
			
			e.printStackTrace();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	}

}
