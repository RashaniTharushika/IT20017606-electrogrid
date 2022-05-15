<%@page import="com.Unit"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
	<title>Unit Management</title>
	<link rel="stylesheet" href="Views/bootstrap.min.css">
	<script src="Components/jquery-3.6.0.min.js"></script>
	<script src="Components/unit.js"></script>
</head>
<body>
	<div class="container">
		<div class="row">
			<div class="col-6">
				<h1>Unit Management</h1>
				<form action="Unit_Managemenet.jsp" id="formUnit" name="formUnit" method="post">
					minUnitValue:
					<input id="minunitValue" name="minunitValue" type="text" class="form-control form-control-sm">
					<br>
					maxUnitValue:
					<input id="maxunitValue" name="maxunitValue" type="text" class="form-control form-control-sm">
					<br>
					unitPrice:
					<input id="unitprice" name="unitprice" type="text" class="form-control form-control-sm">
					<br>
					insertDate:
					<input id="insertdate" name="insertdate" type="text" class="form-control form-control-sm">
					<br>
					modifiedDate:
					<input id="modifieddate" name="modifieddate" type="text" class="form-control form-control-sm">
					<br>
					 <input id="btnSave" name="btnSave" type="button" value="Save" class="btn btn-primary">
 					 <input type="hidden" id="hididSave" name="hididSave" value="">
				</form>
				<div id="alertSuccess" class="alert alert-success"></div>
				<div id="alertError" class="alert alert-danger"></div>
				<br>
				<div id="divUnitGrid">
					 <%
					 	Unit unitMgmt = new Unit();
					 	out.print(unitMgmt.readUnit()); 
					 %>
				</div>
			</div>
		</div>
	</div> 
</body>
</html>