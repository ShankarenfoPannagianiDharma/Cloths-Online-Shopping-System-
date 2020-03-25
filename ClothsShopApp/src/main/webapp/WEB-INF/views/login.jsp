<%@ include file="common/header.jspf"%>
<%@ include file="common/navigation.jspf"%>

<div class="container">
	<div class="col-md-offset-2 col-md-7">
		<h2 class="text-center">User Login: ${message}</h2>
		<div class="panel panel-info">
			<div class="panel-body">
				<form:form action="login" cssClass="form-horizontal" method="post"
					modelAttribute="LoginInfo">

					<div class="form-group">
						<label for="email" class="col-md-3 control-label">email</label>
						<div class="col-md-9">
							<form:input path="email" cssClass="form-control" />
						</div>
					</div>
					<div class="form-group">
						<label for="password" class="col-md-3 control-label">Password</label>
						<div class="col-md-9">
							<form:password path="password" cssClass="form-control" />
						</div>
					</div>

					<div class="form-group">
						<!-- Button -->
						<div class="col-md-offset-3 col-md-9">
							<form:button cssClass="btn btn-primary">Submit</form:button>
						</div>
					</div>

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