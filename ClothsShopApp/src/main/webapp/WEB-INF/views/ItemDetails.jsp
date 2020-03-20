<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Cloths Shop App</title>
<link href="<c:url value="/resources/css/bootstrap.min.css" />"
	rel="stylesheet">
<link href="<c:url value="/resources/css/pc_reset.css"/>"
	rel="stylesheet" />
<link href="<c:url value="/resources/css/pc_home.css"/>"
	rel="stylesheet" />
<link href="<c:url value="/resources/css/pc_styles1.css?v=098765"/>"
	rel="stylesheet" />

<script src="<c:url value="/resources/js/jquery-1.11.1.min.js" />"></script>
<script src="<c:url value="/resources/js/bootstrap.min.js" />"></script>
<%@ page isELIgnored="false"%>
</head>
<body>
	<!-- Header -->
	<div class="container">
		<div class="col-md-offset-2 col-md-7">
			<h1>${product.productName}</h1>
			<hr />
		</div>
	</div>
	<!-- Header: horizontal ul li for navigation & controls -->
	<ul class="control-menu">
		<li><a href="home">Home</a></li>
		<li><a href="browse">Browse</a></li>
		<li><a href="account">Account</a></li>
		<li><a href="logout">Log Out</a></li>
	</ul>
	
	<!-- display the item -->
	<div class="displayLeft">
		<h2>${targetProduct.productName}</h2>
		Type  : ${targetProduct.productType}<br>
		Size  : ${targetProduct.productSize}<br>
		Price : $${targetProduct.price}<br>
		Stock : ${targetProduct.stock}<br>
		Description:<br>${targetProduct.description}<br><br>
		
	</div>
	<div class="displayRight">
		<img src="<c:url value="/resources/img/${targetProduct.imgURL}"/>" alt="imgNOTFOUND">
	</div>
</body>
</html>