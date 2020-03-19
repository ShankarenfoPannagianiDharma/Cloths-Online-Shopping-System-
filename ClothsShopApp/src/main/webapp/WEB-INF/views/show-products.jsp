<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>javaguides.net</title>
<link href="<c:url value="/resources/css/bootstrap.min.css" />"
	rel="stylesheet">
<script src="<c:url value="/resources/js/jquery-1.11.1.min.js" />"></script>
<script src="<c:url value="/resources/js/bootstrap.min.js" />"></script>
<%@ page isELIgnored="false"%>
</head>
<body>
<div class="container">
		<div class="col-md-offset-2 col-md-7">
			<h1>Product List</h1>
			${message}
			<hr />
			<form:form action="selectedProduct" cssClass="form-horizontal"
				method="post" modelAttribute="product" id="productSelectedForm">
				<form:hidden path="productID" cssClass="form-control" />
				<form:hidden path="productName" cssClass="form-control" />
				<form:hidden path="price" cssClass="form-control" />
				<form:hidden path="productType" cssClass="form-control" />
				<form:hidden path="stock" cssClass="form-control" />
				<form:hidden path="productSize" cssClass="form-control" />
				<form:hidden path="description" cssClass="form-control" />
				<form:hidden path="imgURL" cssClass="form-control" />
				<table class="table table-striped table-bordered">
					<c:forEach var="product" items="${products}">
						<tr>
							<td>${product.productID} ${product.name}${product.price}${product.productType}${product.stock}${product.productSize}${product.description}${product.imgURL}</td> 
							<td><a href="#" onclick="document.getElementById('productID').value='${product.productID}';document.getElementById('productName').value='${product.productName}';
							document.getElementById('price').value='${product.price}';document.getElementById('productType').value='${product.productType}';document.getElementById('stock').value='${product.stock}';
							document.getElementById('productSize').value='${product.productSize}';document.getElementById('description').value='${product.description}';document.getElementById('imgURL').value='${product.imgURL}';
							document.getElementById('productSelectedForm').submit();">Selected</a></td>
						</tr>
					</c:forEach>

				</table>
			</form:form>
		</div>
	</div>

</body>
</html>