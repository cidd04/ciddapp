<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="security"%>

<!-- End header area -->
<security:authorize access="isAuthenticated()">
	<security:authentication property="principal" var="principalUser" />
</security:authorize>
<div class="visible-xs">
	<div class="sb-slidebar sb-left menu">
		<ul>
			<c:if test="${not empty principalUser}">
			<li><a style="padding-bottom: 10px;padding-top: 10px;" 
					href="${pageContext.request.contextPath}/user?id=${principalUser.id}"><img alt="${principalUser.name}"
					src="${pageContext.request.contextPath}/displayAccountImage/${principalUser.accountImageUrl}"
					width="40px" height="40px" class="img-circle">&nbsp;&nbsp;${principalUser.name}</a></li>
			</c:if>
			<c:if test="${empty principalUser}">
				<li><a href="${pageContext.request.contextPath}/signin"><i
						class="fa fa-sign-in"></i>&nbsp;&nbsp;Sign In</a></li>
			</c:if>
			<li><a href="${pageContext.request.contextPath}/search"><i
					class="fa fa-search"></i>&nbsp;&nbsp;Search</a></li>
			<li><a href="${pageContext.request.contextPath}/product/add"><i
					class="fa fa-shopping-basket"></i>&nbsp;&nbsp;Add Product</a></li>
			<li><a href="${pageContext.request.contextPath}/journey"><i
					class="fa fa-space-shuttle"></i>&nbsp;&nbsp;Add Journey</a></li>
			<li><a href="${pageContext.request.contextPath}/#faq"><i
					class="fa fa-question"></i>&nbsp;&nbsp;FAQ</a></li>
			<c:if test="${not empty principalUser}">
				<li><a href="${pageContext.request.contextPath}/user?id=${principalUser.id}"><i
						class="fa fa-bed"></i>&nbsp;&nbsp;My Activities
						<span class="badge hidden" style="background-color: red"></span>
					</a>	
				</li>
				<li><a href="${pageContext.request.contextPath}/product/matched/${principalUser.id}"><i
						class="fa fa-shopping-basket"></i>&nbsp;&nbsp;Matched Products</a></li>
				<li><a href="${pageContext.request.contextPath}/search?likedBy=${principalUser.id}"><i
						class="fa fa-shopping-basket"></i>&nbsp;&nbsp;Liked Products</a></li>
				<li><a href="${pageContext.request.contextPath}/logout"><i
						class="fa fa-sign-out"></i>&nbsp;&nbsp;Logout</a></li>
			</c:if>
			
		</ul>
	</div>
	<script>
		var width = (window.innerWidth > 0) ? window.innerWidth : screen.width;
		if (width < 768) {
			(function($) {
				$(document).ready(function() {
					$.slidebars();
				});
			})(jQuery);
		}
	</script>
</div>
<div class="nav-container">
	<nav id="nav" class="navbar navbar-default navbar-fixed-top sb-slide">
		<div class="container">
			<div id="logo" class="hidden-xs">
				<a href="${pageContext.request.contextPath}/"><img id="logo-img" alt="logo"
					src="${pageContext.request.contextPath}/resources/images/logo.png"
					width="210px" height="43px"></a>
			</div>
			<div id="topnav" class="hidden-xs">
				<ul class="nav navbar-nav navbar-right">
					<c:if test="${empty principalUser}">
						<li><button id="myid" type="button" class="btn btn-success" 
								onclick="window.location.href='${pageContext.request.contextPath}/signin'">
								Sign In
							</button></li>
					</c:if>
					<c:if test="${not empty principalUser}">
						<li>
							<div class="btn-group" style="margin-left: 10px">
								<button id="myid" type="button"
									class="btn btn-default dropdown-toggle" data-toggle="dropdown">
									<img
										src="${pageContext.request.contextPath}/displayAccountImage/${principalUser.accountImageUrl}"
										width="30px" height="30px" class="img-circle">&nbsp; <span
										class="menutext">${principalUser.name}</span>&nbsp; <span
										class="caret"></span>
										<c:if test="${not empty principalUser}">
											<span class="badge hidden" style="background-color: red"></span>
										</c:if>
								</button>
								<ul class="dropdown-menu dropdown-menu-right" role="menu">
									<li><a href="${pageContext.request.contextPath}/logout">Logout</a></li>
								</ul>
							</div>
						</li>
					</c:if>
				</ul>
			</div>
			
			<div id="topnav-mobile" class="visible-xs">
				<div class="icon-menu sb-toggle-left">
					<button class="btn btn-default">
						<span style="color: #000"><i class="fa fa-align-justify"></i></span>
					</button>
					<c:if test="${not empty principalUser}">
						<span class="badge hidden" style="background-color: red"></span>
					</c:if>					
				</div>
			</div>
			<div id="logo" class="visible-xs" style="padding-top: 15px">
				<a href="${pageContext.request.contextPath}/"><img alt="logo"
					src="${pageContext.request.contextPath}/resources/images/logo.png"
					width="170px" height="38px"></a>
			</div>
		</div>
		<!-- end container div -->
	</nav>
</div>
<!-- end nav-container div -->
