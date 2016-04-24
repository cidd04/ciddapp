<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<security:authorize access="isAuthenticated()">
	<security:authentication property="principal" var="principalUser" />
</security:authorize>

<script type="text/javascript">
	
</script>

<div id="sb-site">
	<div class="min-wrap">
		<div class="container">
			<div class="row" align="center">
				<div class="col-md-12">Hello Worlds</div>
			</div>
		</div>
	</div>
</div>

<!-- end row -->
<!-- Closing tag for div id sb-site -->