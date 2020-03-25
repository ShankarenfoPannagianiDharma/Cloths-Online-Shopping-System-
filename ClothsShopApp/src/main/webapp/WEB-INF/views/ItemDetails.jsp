<%@ include file="common/header.jspf"%>
<%@ include file="common/navigation.jspf"%>

<div class="container">
	<!-- Header -->
	<div class="row">
		<div class="col-md-offset-2 col-md-7">
			<h1>${product.productName}</h1>
			<hr />
		</div>
	</div>

	<div class="row">
		<!-- display the item -->
		<div class="col">
			<h2>${targetProduct.productName}</h2>
			Type : ${targetProduct.productType}<br> Size :
			${targetProduct.productSize}<br> Price : $${targetProduct.price}<br>
			Stock : ${targetProduct.stock}<br> Description:<br>${targetProduct.description}<br>
			<br>

		</div>
		<div class="col">
			<img src="<c:url value="/resources/img/${targetProduct.imgURL}"/>"
				alt="imgNOTFOUND">
		</div>
	</div>

</div>
<%@ include file="common/footer.jspf"%>