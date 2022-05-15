package com;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Unit {
	private Connection connect(){
		Connection con = null; 
		
		try {
			Class.forName("com.mysql.jdbc.Driver"); 
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/test","root","#rash#97");
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return con; 
	}
	
	public String insertUnit(String minunitValue, String maxunitValue, String unitprice, String insertdate, String modifieddate) {
		String output = "";
		try
		{ 
			Connection con = connect(); 
			
			if (con == null) 
			{
				return "Error while connecting to the database for inserting."; 
				
			} 
			// create a prepared statement
			
			String query = " insert into UNIT(`ID`,`minUnitValue`,`maxUnitValue`,`unitPrice`,`insertDate`,`modifiedDate`)"+" values (?, ?, ?, ?, ?, ?)";
			PreparedStatement preparedStmt = con.prepareStatement(query); 
			
			preparedStmt.setInt(1, 0); 
			preparedStmt.setString(2, minunitValue); 
			preparedStmt.setString(3, maxunitValue); 
			preparedStmt.setDouble(4, Double.parseDouble(unitprice)); 
			preparedStmt.setString(5, insertdate);
			preparedStmt.setString(6, modifieddate);
			
			preparedStmt.execute(); 
			con.close(); 
			
			Unit unitMgmt = new Unit(); 
			output = "{\"status\":\"success\",\"data\":\""+unitMgmt+"\"}"; 
		} 
		
		catch (Exception e) 
		{ 
			output = "{\"status\":\"error\", \"data\":\"Error while inserting the Unit.\"}"; 
			System.err.println(e.getMessage()); 
		} 
		return output; 
	}
	
	
	public String readUnit() { 
		String output = ""; 
		try
		{ 
			Connection con = connect(); 
			if (con == null) 
			{ 
				return "Error while connecting to the database for reading."; 
			} 
	 // Prepare the html table to be displayed
	 output = "<table border=\"1\" class=\"table\"><tr><th>minUnitValue</th>"
	 		+ "<th>maxUnitValue</th>"
	 		+ "<th>unitPrice</th>"
	 		+ "<th>insertDate</th>"
	 		+ "<th>modifiedDate</th>"
	 		+ "<th>Update</th>"
	 		+ "<th>Remove</th></tr>"; 
	
	 String query = "select * from UNIT"; 
	 Statement stmt = (Statement) con.createStatement(); 
	 ResultSet rs = stmt.executeQuery(query); 
	 // iterate through the rows in the result set
	 while (rs.next()) 
	 { 
	 String id = Integer.toString(rs.getInt("id")); 
	 String minunitValue = rs.getString("minunitValue"); 
	 String maxunitValue = rs.getString("maxunitValue"); 
	 String unitprice = Double.toString(rs.getDouble("unitprice")); 
	 String insertdate = rs.getString("insertdate"); 
	 String modifieddate = rs.getString("modifieddate");
	 // Add into the html table
	 output += "<tr><td><input id='hidIDUpdate' name='hidIDUpdate' type='hidden' value='"+id+"'>"+minunitValue+"</td>"; 
	 output += "<td>" + maxunitValue + "</td>"; 
	 output += "<td>" + unitprice + "</td>"; 
	 output += "<td>" + insertdate + "</td>"; 
	 output += "<td>" + modifieddate + "</td>"; 
	 // buttons
	 output += "<td><input name='btnUpdate' type='button' value='Update' "
			 + "class='btnUpdate btn btn-secondary' data-iD='" + id + "'></td>"
			 + "<td><input name='btnRemove' type='button' value='Remove' "
			 + "class='btnRemove btn btn-danger' data-iD='" + id + "'></td></tr>"; 
	 
	 } 
	 con.close(); 
	 // Complete the html table
	 output += "</table>"; 
	 } 
	 
	catch (Exception e) 
	 { 
	 output = "Error while reading the Unit."; 
	 System.err.println(e.getMessage()); 
	 } 
	return output; 
	}
	
	public String updateUnit(String id, String minunitValue, String maxunitValue, String unitprice, String insertdate, String modifieddate){ 
		
		String output = ""; 
		
		try{ 
				Connection con = connect(); 
				if (con == null){
					return "Error while connecting to the database for updating.";
					} 
				// create a prepared statement
				String query = "UPDATE UNIT SET minUnitValue=?,maxUnitValue=?,unitPrice=?,insertDate=?,modifiedDate=? WHERE ID=?"; 
				PreparedStatement preparedStmt = con.prepareStatement(query); 
				// binding values
				preparedStmt.setString(1, minunitValue); 
				preparedStmt.setString(2, maxunitValue); 
				preparedStmt.setDouble(3, Double.parseDouble(unitprice)); 
				preparedStmt.setString(4, insertdate); 
				preparedStmt.setString(5, modifieddate); 
				preparedStmt.setInt(6, Integer.parseInt(id)); 
				// execute the statement
				preparedStmt.execute(); 
				con.close(); 
				Unit unitMgmt = new Unit(); 
				output = "{\"status\":\"success\",\"data\":\""+unitMgmt+"\"}";

		} 
		
		catch (Exception e){ 
			
			output = "{\"status\":\"error\",\"data\":\"Error while updating the Unit.\"}"; 

			System.err.println(e.getMessage()); 
			
		} 
		
		return output; 
} 


public String deleteUnit(String id){ 
	
		String output = ""; 
		
		try{ 
			Connection con = connect(); 
			
			if (con == null){
				return "Error while connecting to the database for deleting."; 
				} 
			// create a prepared statement
			String query = "delete from UNIT where ID=?"; 
			PreparedStatement preparedStmt = con.prepareStatement(query); 
			// binding values
			preparedStmt.setInt(1, Integer.parseInt(id)); 
			// execute the statement
			preparedStmt.execute(); 
			con.close(); 
			Unit unitMgmt = new Unit(); 
			output = "{\"status\":\"success\",\"data\":\""+unitMgmt+"\"}";

		} 
		
		catch (Exception e){ 
			output = "{\"status\":\"error\",\"data\":\"Error while deleting the Unit.\"}";
			System.err.println(e.getMessage()); 
		} 
		return output; 
} 


}

