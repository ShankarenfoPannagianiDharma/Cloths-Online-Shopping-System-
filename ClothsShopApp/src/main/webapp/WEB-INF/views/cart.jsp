<%@ include file="common/header.jspf"%>
<%@ include file="common/navigation.jspf"%>

<div class="container">
	<!-- Display utility: all users -->
	<p>Cart:</p>
	<table class="table table-striped table-bordered">
		<tr>
			<th>Order Id</th>
			<th>Product Id</th>
			<th>Amount</th>
		</tr>
		<c:forEach var="orderd" items="${orderdetail}">
			<tr>
				<td>${orderd.orderid}</td>
				<td>${orderd.itemid}</td>
				<td>${orderd.amount}</td>
			</tr>
		</c:forEach>

	</table>
</div>
<%@ include file="common/footer.jspf"%>