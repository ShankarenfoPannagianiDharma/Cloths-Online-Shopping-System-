<%@ include file="common/header.jspf"%>
<%@ include file="common/navigation.jspf"%>

<div class="container">
	<div class="row">
		<h1>Product List</h1>
		${message}
		<hr />
		<!-- Display: All items in stock -->
		<div class="grid-container">
			<div class="col">
				<div class="row">
					<!-- Each slot links to the page itemDetail?id=[productID] -->
					<c:forEach var="product" items="${products}">

						<div class="col-12 col-md-6 col-lg-4">
							<div class="card">
								<img class="card-img-top"
									src="<c:url value="/resources/img/${product.imgURL}"/>"
									alt="Card image cap">
								<div class="card-body">
									<h4 class="card-title">
										<a href="/product/itemDetail?id=${product.productID}">
											${product.productName}</a>
									</h4>
									<p class="card-text">Some quick example text to build on
										the card title and make up the bulk of the card's content.</p>
									<div class="row">
										<div class="col">
											<p class="btn btn-danger btn-block">${product.price}</p>
										</div>
										<div class="col">
											<form:form action="addToCart" cssClass="form-horizontal"
												method="post" modelAttribute="Product">
												<button type="submit" class="btn btn-success btn-block">Add
													to cart</button>
												<form:input type="hidden" path="productID"
													value="${product.productID}" />
											</form:form>

										</div>
									</div>
								</div>
							</div>
						</div>
					</c:forEach>
				</div>
			</div>
		</div>
	</div>
</div>
<%@ include file="common/footer.jspf"%>
