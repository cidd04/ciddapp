<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>

<div id="sb-site">
	<div class="min-wrap">
		<div class="container">
			<div class="row">
				<div class="col-xs-12 col-sm-12">
					<h2>
						<i class="fa fa-sign-in"></i> Sign In
					</h2>
				</div>
				<div class="col-xs-12 col-sm-4">
					<form class="form-narrow form-horizontal"
						action='<s:url value="authenticate"/>' method="post">
						<c:if test="${not empty param['error']}">
							<div class="alert alert-error">Sign in error. Please try
								again.</div>
						</c:if>
						<fieldset>
							<div class="form-group">
								<label for="inputName" class="col-lg-2 control-label">Email </label>
								<div class="col-lg-10">
									<input type="text" class="form-control" id="inputEmail"
										placeholder="Email" name="username">
								</div>
							</div>
							<div class="form-group">
								<label for="inputPassword" class="col-lg-2 control-label">Password </label>
								<div class="col-lg-10">
									<input type="password" class="form-control" id="inputPassword"
										placeholder="Password" name="password">
								</div>
							</div>
							<div class="form-group">
								<div class="col-lg-offset-2 col-lg-10">
									<div class="checkbox">
										<label> <input type="checkbox"
											name="_spring_security_remember_me"> Remember me
										</label>
									</div>
								</div>
							</div>
							<div class="form-group">
								<div class="col-lg-offset-2 col-lg-10">
									<button type="submit" class="btn btn-default">Sign in</button>
								</div>
							</div>
							<div class="form-group">
								<div class="col-lg-offset-2 col-lg-10">
									<p>
										New here? <a href='<s:url value="/signup?social=false"/>'>Sign Up</a>
									</p>
								</div>
							</div>
						</fieldset>
					</form>
				</div>
			</div>
			<div class="row">
				<div class="col-xs-12 col-sm-12">
					<h2>
						<i class="fa fa-sign-in"></i> Sign In Using
					</h2>
				</div>
				<div class="col-xs-12 col-sm-4">
					<div class="row social-button-row">
						<div class="col-lg-4">
							<a href="${pageContext.request.contextPath}/auth/facebook" 
								class="btn btn-default btn-recommend btn-info">
								<i class="fa fa-facebook"></i> | Sign in with Facebook
							</a>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>