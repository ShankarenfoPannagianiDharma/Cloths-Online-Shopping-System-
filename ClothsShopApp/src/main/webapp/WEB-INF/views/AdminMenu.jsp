<%@ include file="common/header.jspf"%>
<%@ include file="common/navigation.jspf"%>
<div>
<h1>This is ADMIN.JSP</h1>
<div class="container">
	<div class="col-md-offset-2 col-md-7">
		<h2 class="text-center">List of User: ${message}</h2>
		<div class="panel panel-info">
			<div class="panel-body">
				<form:form action="login" cssClass="form-horizontal" method="post"
					modelAttribute="LoginInfo">
					
					<!-- Display utility: all users -->
					<p>Available user accounts:</p>
					<table class="table table-striped table-bordered">
							<tr>
								<td>name</td>
								<td>password</td>
								<td>email</td>
								<!--#bao Display if admin -->
								<td>Admin ???</td>
							</tr>
						<c:forEach var="users" items="${allUsers}">
							<tr>
								<td>${users.userName}</td>
								<td>${users.password}</td>
								<td>${users.email}</td>
								<!--#bao Display if admin -->
								<td>(${users.admin})</td>
							</tr>
						</c:forEach>

					</table>

				</form:form>
			</div>
		</div>
	</div>
</div>
<%@ include file="common/footer.jspf"%>