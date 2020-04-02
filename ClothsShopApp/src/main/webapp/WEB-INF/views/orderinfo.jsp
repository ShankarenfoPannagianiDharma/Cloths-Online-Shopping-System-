<%@ include file="common/header.jspf"%>
<%@ include file="common/navigation.jspf"%>

<div class="container">
	<table class="table table-striped table-bordered">
		<tr>
			<th>Item</th>
			<th>Cost (ea.)</th>
			<th>Amount</th>
		</tr>
		<c:forEach var="orderd" items="${orderd}">
			<tr>
				<td>${orderd.itemName}</td>
				<td>$${orderd.price}</td>
				<td>${orderd.amount}</td>
			</tr>
		</c:forEach>

	</table>
	<!-- Cart info -->
	<p>Total items: ${orderAmount}</p>
	<p>Total price: $${orderCost}</p>
	
</div>
<%@ include file="common/footer.jspf"%>