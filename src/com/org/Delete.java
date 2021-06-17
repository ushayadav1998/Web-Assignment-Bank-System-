package com.org;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class Delete extends HttpServlet {
	private static final long serialVersionUID = 1L;
     
	Connection con;
	int row;
	PreparedStatement pst;
	ResultSet rs;
    
    public Delete() {
        super();
       
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		String CustomerId=request.getParameter("CustomerId");
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "root");
			
			
			
			pst=con.prepareStatement("delete from Account where CustomerId=?");
			pst.setString(1, CustomerId);
			
			
			row=pst.executeUpdate();
			out.println("<font color='green'> Account Deleted </font>");
			
		} catch (ClassNotFoundException e) {
			
			e.printStackTrace();
		} catch (SQLException e) {
			out.println("<font color='red'> Account Failed </font>");
			
			e.printStackTrace();
		}
		
		
	
	
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
