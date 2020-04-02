<%@ include file="common/header.jspf"%>
<%@ include file="common/navigation.jspf"%>

<div class="container">
	<!-- Display utility: all users -->
	<p>Cart:</p>
	<table class="table table-striped table-bordered">
		<tr>
			<th>Order Id</th>
			<th>Product Id</th>
			<th>Cost (ea.)</th>
			<th>Amount</th>
		</tr>
		<c:forEach var="orderd" items="${orderdetail}">
			<tr>
				<td>${orderd.orderid}</td>
				<td>${orderd.itemid}</td>
				<td>$${orderd.price}</td>
				<td>${orderd.amount}</td>
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