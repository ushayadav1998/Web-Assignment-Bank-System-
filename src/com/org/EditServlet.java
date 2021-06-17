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


public class EditServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	Connection con;
	int row;
	PreparedStatement pst;
	ResultSet rs;
    
    public EditServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		
		try {
			
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "root");
			
			con.setAutoCommit(true);
			
			String AccountNumber=request.getParameter("AccountNumber");
			String AccountType=request.getParameter("AccountType");
			String Balance=request.getParameter("Balance");
			String CustomerId=request.getParameter("CustomerId");
			
			pst=con.prepareStatement("update Account set AccountNumber=?,AccountType=?,Balance=? where CustomerId=?");
			pst.setString(1, AccountNumber);
			pst.setString(2, AccountType);
			pst.setString(3, Balance);
			pst.setString(4, CustomerId);
			
			row=pst.executeUpdate();
			out.println("<font color='green'> Account updated </font>");
			response.sendRedirect("ViewAccount");
			
		} catch (ClassNotFoundException e) {
			
			e.printStackTrace();
		} catch (SQLException e) {
			out.println("<font color='red'> Account Failed </font>");
			
			e.printStackTrace();
		}
		
		
	}

}
