<%@ include file="common/header.jspf"%>
<%@ include file="common/navigation.jspf"%>

<div class="container">
	<p>Cart:</p>
	<table class="table table-striped table-bordered">
		<tr>
			<th>Product</th>
			<th>Cost (ea.)</th>
			<th>Amount</th>
			<th>Remove</th>
		</tr>
		<c:forEach var="orderd" items="${orderdetail}">
			<tr>
				<td>${orderd.itemName}</td>
				<td>$${orderd.price}</td>
				<td>${orderd.amount}</td>
				<td>
					<!-- Remove an item button: use model products to store ID -->
					<form:form action="cartRemoveOne" cssClass="form-horizontal" method="post" modelAttribute="Product">
					<form:input type="hidden" value="${orderd.itemid}" path="productID"/>
						<button type="submit" class="btn btn-success btn-block">X</button>
					</form:form>
				</td>
			</tr>
		</c:forEach>

	</table>
	<!-- Cart info -->
	<p>Total items: ${orderAmount}</p>
	<p>Total price: $${orderCost}</p>
	
	<!-- Make purchase, empty cart buttons -->
	<form:form action="makePurchase" cssClass="form-horizontal" method="post" modelAttribute="PurchaseFormModel">
		Delivery address: <form:input path="targetAddress" cssClass="form-control" />
		<button type="submit" class="btn btn-success btn-block">Purchase</button>
	</form:form>
	<form:form action="cancelCart" cssClass="form-horizontal" method="post">
		<button type="submit" class="btn btn-success btn-block">Clear Cart</button>
	</form:form>
	
</div>
<%@ include file="common/footer.jspf"%>