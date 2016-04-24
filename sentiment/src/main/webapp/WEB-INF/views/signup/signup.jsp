<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="botDetect" uri="botDetect"%>
<script type="text/javascript">

$(document).ready(function() {
	if ($("#isSocialSignup").val() !== '') {
		$(".password").addClass("hidden");
		$(".password input").val("anypasswordthatdoesnotmatter");
	}
});

</script>
<div id="sb-site">
	<div class="min-wrap">
		<div class="container">
			<div class="row">
				<div class="col-xs-12 col-sm-12">
					<h2>
						<i class="fa fa-asterisk"></i> Please Sign Up
					</h2>
				</div>
				<div class="col-xs-12 col-sm-6">
					<form:form class="form-narrow form-horizontal" method="post"
						modelAttribute="signup">
						<fieldset>
							<form:errors path="" element="p" class="text-error" />
							<div class="form-group">
								<label for="name" class="control-label">Name</label>
								<div class="col-lg-8">
									<form:input path="name" class="form-control"
										cssErrorClass="form-control" id="name" placeholder="Name" />
									<form:errors path="name" element="span" class="help-block" />
								</div>
							</div>
							<div class="form-group">
								<label for="email" class="control-label">Email</label>
								<div class="col-lg-8">
									<form:input path="email" class="form-control"
										cssErrorClass="form-control" id="email"
										placeholder="Email address" />
									<form:errors path="email" element="span" class="help-block" />
								</div>
							</div>
							<div class="form-group password">
								<label for="password" class="control-label">Password</label>
								<div class="col-lg-8">
									<form:password path="password" class="form-control"
										id="password" placeholder="Password" />
									<form:errors path="password" element="span" class="help-block" />
								</div>
							</div>
							<div class="form-group password">
								<label for="password2" class="control-label">Retype Password</label>
								<div class="col-lg-8">
									<form:password path="password2" class="form-control"
										id="password2" placeholder="Retype Password" />
									<form:errors path="password2" element="span" class="help-block" />
								</div>
							</div>
							<div class="form-group">
								<div class="">
									<c:if test="${not signup.isSocialSignup}">
										<botDetect:captcha id="captchaCodeImage" />
										<p>Retype the code from the picture:</p>
										<!-- Adding BotDetect Captcha to the page -->
										<div class="validationDiv">
											<input id="captchaCodeTextBox" type="text"
												name="captchaCodeTextBox" />
										</div>
									</c:if>
									<div style="padding-top: 10px;">
										<form:input path="isSocialSignup" type="hidden"/>
										<button type="submit" class="btn btn-default">Sign up</button>
									</div>
								</div>
							</div>
							<c:if test="${not signup.isSocialSignup}">
							<div class="form-group">
								<div class="">
									<p>
										Already have an account? <a href='<s:url value="/signin"/>'>Sign
											In</a>
									</p>
								</div>
							</div>
							</c:if>
						</fieldset>
					</form:form>
				</div>
			</div>
		</div>
	</div>
</div>