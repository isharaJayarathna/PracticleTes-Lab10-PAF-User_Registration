//Ishara***
package com;

import java.sql.*;

public class Registration 
{

			//CONNECTION
			public Connection connect()
			{
					Connection con = null;

					try
					{
							Class.forName("com.mysql.jdbc.Driver");
							con= DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/GB",	"root", "");
			
					}
					catch(Exception e)
					{
							e.printStackTrace();
					}

					return con;
			}
			
			
			
			
			//READ the data
			public String readUser()
			{
					String output = "";
					
					try
					{
							Connection con = connect();
							
							if (con == null)
							{
									return "Error while connecting to the database for reading.";
							}
							
							
							output = "<table class='table table-dark table-striped table-hover'><tr><th>First Name</th><th>Last Name</th><th>Phone Number</th><th>Mail</th><th>UserRole</th><th>Password</th>"
									+"<th>Edit</th><th>Delete</th></tr>";


							String query = "select * from Users";
							Statement stmt = con.createStatement();
							ResultSet rs = stmt.executeQuery(query);

							// iterate through the rows in the result set
							while (rs.next())
							{
									String UserId = Integer.toString(rs.getInt("UserId"));
									String UFirstName = rs.getString("UFirstName");
									String ULastName = rs.getString("ULastName");
									String Uphone = rs.getString("Uphone");
									String UMail = rs.getString("UMail");
									String UserRole = rs.getString("UserRole");
									String UPassword = rs.getString("UPassword");


									// Add a row into the HTML table
									output += "<tr><td>" + UFirstName + "</td>";
									output += "<td>" + ULastName + "</td>";
									output += "<td>" + Uphone + "</td>";
									output += "<td>" + UMail + "</td>";
									output += "<td>" + UserRole + "</td>";
									output += "<td>" + UPassword + "</td>"; 
				

									// buttons
									output += "<td><input name='btnUpdate' type='button' value='Edit' class='btnUpdate btn btn-secondary' data-UserId='" + UserId + "'></td>"
											+"<td><input name='btnRemove' type='button' value='Delete' class='btnRemove btn btn-danger' data-UserId='" + UserId + "'>" + "</td></tr>";
							}

							con.close();

							// Complete the HTML table
							output += "</table>";
					}
					catch (Exception e)
					{
							output = "Error while reading the users.";
							System.err.println(e.getMessage());
					}
					
					return output;
			}
			
			
			
			

			//INSERT the data
			public String insertUser(String UFirstName, String ULastName,String Uphone,String UMail,String UserRole, String UPassword)
			{
					String output = "";
					
					try
					{
							Connection con = connect();
							
							if (con == null)
							{
									return "Error while connecting to the database for inserting";
							}

							// create a prepared statement
							String query = " insert into Users (`UserId`,`UFirstName`,`ULastName`,`Uphone`,`UMail`,`UserRole`,`UPassword`)"+ "values (?, ?, ?, ?, ?, ?, ?)";
							
							PreparedStatement preparedStmt = con.prepareStatement(query);

							// binding values
							preparedStmt.setInt(1, 0);
							preparedStmt.setString(2, UFirstName);
							preparedStmt.setString(3, ULastName);
							preparedStmt.setString(4, Uphone);
							preparedStmt.setString(5, UMail);
							preparedStmt.setString(6, UserRole);
							preparedStmt.setString(7, UPassword);
							
							

							//execute the statement
							preparedStmt.execute();
							con.close();

							String newUser = readUser();
							output = "{\"status\":\"success\", \"data\": \"" + newUser + "\"}";
			
					}
					catch (Exception e)
					{
								output = "{\"status\":\"error\", \"data\":\"Error while inserting the User.\"}";
								System.err.println(e.getMessage());
					}
					
					return output;
			}
			

			
			//UPDATE
			public String updateUser(String UserId,String UFirstName, String ULastName,String Uphone,String UMail,String UserRole, String UPassword)
			{
					String output = "";
					
					try
					{
							Connection con = connect();
							
							if (con == null)
							{
									return "Error while connecting to the database for updating";
							}

							// create a prepared statement
							String query = "UPDATE Users SET UFirstName=?, ULastName=?,Uphone=?,UMail=?,UserRole=?, UPassword=? WHERE UserId=?";
							
							PreparedStatement preparedStmt = con.prepareStatement(query);

							// binding values
							preparedStmt.setString(1, UFirstName);
							preparedStmt.setString(2, ULastName);
							preparedStmt.setString(3, Uphone);
							preparedStmt.setString(4, UMail);
							preparedStmt.setString(5, UserRole);
							preparedStmt.setString(6, UPassword);
							preparedStmt.setInt(7, Integer.parseInt(UserId));

							//execute the statement
							preparedStmt.executeUpdate();
							con.close();

							String newUsers = readUser();
							output = "{\"status\":\"success\", \"data\": \"" + newUsers + "\"}";
			
			
					}
					catch (Exception e)
					{
							output = "{\"status\":\"error\", \"data\":\"Error while updating the user.\"}";
							System.err.println(e.getMessage());
					}
					
					return output;
			}
			
			

			//DELETE
			public String deleteUser(String UserId)
			{
					String output = "";
					
					try
					{
							Connection con = connect();
							
							if (con == null)
							{
									return "Error while connecting to the database for deleting";
							}

							// create a prepared statement
							String query = "DELETE from Users where UserId=?";
							
							PreparedStatement preparedStmt = con.prepareStatement(query);

							// binding values
							preparedStmt.setInt(1, Integer.parseInt(UserId));

							//execute the statement
							preparedStmt.executeUpdate();
							con.close();

							String newUsers = readUser();
							output = "{\"status\":\"success\", \"data\": \"" + newUsers + "\"}";
					}
					catch (Exception e)
					{
						output = "{\"status\":\"error\", \"data\":\"Error while deleting the user.\"}";
						System.err.println(e.getMessage());
					}
					
					return output;
					
					
					
					
					
					
			}

	
}
