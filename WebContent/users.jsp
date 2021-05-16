<%@ page import="com.Registration"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>PAF_INTERFACE</title>

<script src="Components/jquery-3.2.1.min.js"></script>
<script src="Components/users.js"></script>
<link rel="stylesheet" href="Views/bootstrap.min.css">
<style>
body{
background-color:#ddd;
}
header {
  background-color:black;
  padding: 0.5px;
  text-align: center;
  font-size: 20px;
  color: white;
  height:100px;
}
/* Style the top navigation bar */
.topnav {
    overflow: hidden;
    background-color: red;	
}

/* Style the topnav links */
.topnav a {
    float: right;
    display: block;
    color: #f2f2f2;
    text-align: center;
    padding: 14px 16px;
    text-decoration: none;	
}

/* Change color on hover */
.topnav a:hover {
    background-color: #ddd;
    color: black;	
}
/* Style the footer */
footer {
  background-color: black;
  padding: 20px;
  text-align: center; 
}
</style>

 
</head>
<header>
<h2>GADGETBADGET</h2>
  <div class="topnav">
	  <a href="#">PAYMENT</a>
	  <a href="#">PROJECT</a>
	  <a href="#">FUNDS</a>
	  <a href="#">REGISTRATION</a>
	  <a href="#">HOME</a>
 </div>
 </header>
<body>
	<div class="container">
		<div class="row">
			<div class="col">
			
				<h1>User Registration</h1>
				
				<form id="formUser" name="formUser">
 					    First Name:
 						<input id="UFirstName" name="UFirstName" type="text" class="form-control form-control-sm">
 						<br> 
 						Last Name:
 						<input id="ULastName" name="ULastName" type="text" class="form-control form-control-sm">
 						<br> 
 						
 						phone Number:
 						<input id="Uphone" name="Uphone" type="text" class="form-control form-control-sm">
 						<br> 
 						Mail:
 						<input id="UMail" name="UMail" type="text" class="form-control form-control-sm">
 						<br> 
 						UserRole:
 						<input id="UserRole" name="UserRole" type="text" class="form-control form-control-sm">
 						<br> 
 						
 						Password:
 						<input id="UPassword" name="UPassword" type="password" class="form-control form-control-sm">
 						<br>
						
 						
 						<input id="btnSave" name="btnSave" type="button" value="Save" class="btn btn-primary">
 						<input type="hidden" id="hidUserIDSave" name="hidUserIDSave" value="">
				</form>

				<br>

				<div id="alertSuccess" class="alert alert-success"></div>
				<div id="alertError" class="alert alert-danger"></div>

				<br>
				<div id="divUsersGrid">
					<%
						Registration userObjRead = new Registration();
										out.print(userObjRead.readUser());
					%>
				</div>

			</div>
		</div>
	</div>
	
<footer>
  <div class="footer1">
    <a href="#">ABOUTUS</a>
    <a href="#">NEED HELP</a>
    <a href="#">CONTACT</a>
    <a href="#">PRIVACY</a>
  </div>

</footer>
	
	
</body>
</html>