<%@ include file="common/header.jspf"%>
<%@ include file="common/navigation.jspf"%>

<div class="container">
	<p>Orders:</p>
	<table class="table table-striped table-bordered">
		<tr>
			<th>Order Id</th>
			<th>Address</th>
			<th>Total Items</th>
			<th>Total Price</th>
		</tr>
		<c:forEach var="orders" items="${orders}">
			<tr>
				<td>${orders.orderID}</td>
				<td>${orders.destinationAddress}</td>
				<td>${orders.orderTotalNum()}</td>
				<td>$${orders.orderTotalCosts()}</td>
				<td>
					<!-- Details button: use model Orders to store ID -->
					<form:form action="orderInfo" cssClass="form-horizontal" method="post" modelAttribute="orderModel">
					<form:input type="hidden" value="${orders.orderID}" path="orderID"/>
						<button type="submit" class="btn btn-success btn-block">Details</button>
					</form:form>
				</td>
			</tr>
		</c:forEach>
	</table>
</div>
<%@ include file="common/footer.jspf"%>