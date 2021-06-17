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
import javax.servlet.http.HttpSession;

public class Editreturn extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	Connection con;
	int row;
	PreparedStatement pst;
	ResultSet rs;
	
    public Editreturn() {
        super();
       
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
        String CustomerId=request.getParameter("CustomerId");
		
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		
		
		try {
			
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "root");
			
			pst=con.prepareStatement("select * from Account where CustomerId=?");
			pst.setString(1, CustomerId);
			rs=pst.executeQuery();
			
			while(rs.next())
			{
				out.print("<form action='EditServlet' method='post'");
				out.print("<table>");
				
				out.print("<tr>  <td>AccountNumber</td> <td><input type='text' name='AccountNumber' value=' "+rs.getString("AccountNumber") + " '/> </td> </tr>");
				out.print("<tr>  <td>AccountType</td> <td><input type='text' name='AccountType' value=' "+rs.getString("AccountType") + " '/> </td> </tr>");
				out.print("<tr>  <td>Balance</td> <td><input type='text' name='Balance' value=' "+rs.getString("Balance") + " '/> </td> </tr>");
				
				out.print("<tr>  <td>CustomerId</td> <td><input type='text' name='CustomerId' value=' "+rs.getString("CustomerId") + " '/> </td> </tr>");
				out.print("<tr>  <td colspan='2'> <input type='submit' value='Edit'/></td></tr>"); 
				
				out.print("</table>");
				out.print("</form>");
			}
			
			
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
