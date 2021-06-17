package com.org;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ViewAccount extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	Connection con;
	int row;
	PreparedStatement pst;
	ResultSet rs;
	
    public ViewAccount() {
        super();
        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "root");
			
			String sql;
			sql="select * from Account";
			Statement stmt=con.createStatement();
			rs=stmt.executeQuery(sql);
			
			out.println("<table cellspacing='0' width='350px' border='1'>");
			out.println("<tr>");
			out.println("<td> AccountNumber </td>");
			out.println("<td> AccountType </td>");
			out.println("<td> Balance </td>");
			out.println("<td> CustomerId </td>");
			out.println("<td> Edit </td>");
			out.println("<td> Delete </td>");
			
			out.println("</tr>");
			
			
			while(rs.next())
			{
				
				out.println("<tr>");
				out.println("<td>" + rs.getString("AccountNumber")+ "</td>");
				out.println("<td>" + rs.getString("AccountType")+ "</td>");
				out.println("<td>" + rs.getString("Balance")+ "</td>");
				out.println("<td>" + rs.getString("CustomerId")+ "</td>");
				
				out.println("<td>" + "<a href='Editreturn?CustomerId='" +rs.getString("CustomerId")+ " '>Edit </a> "+ "</td>");
				out.println("<td>" + "<a href='Delete?CustomerId='" +rs.getString("CustomerId")+ " '> Delete </a> "+ "</td>");
				
				out.println("</tr>");
			}
			
			out.println("</table>");
			
			
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
