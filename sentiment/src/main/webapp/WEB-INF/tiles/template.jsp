<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles-extras" prefix="tilesx"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>

<!DOCTYPE html>
<html>
<head>
	<title>Pasabuy</title>
	
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta charset="UTF-8">
	<meta name="description" content="Pasabuy">
	<meta name="keywords" content="crowdshipping">
	<meta name="author" content="cidd">
	
    <link href="<c:url value="/resources/images/favicon-32x32.png" />" rel="shortcut icon" type="image/png" media="screen" />
	<link href="<c:url value="/resources/css/placecomplete/jquery.placecomplete.css" />" rel="stylesheet" media="screen" />
	<link href="<c:url value="/resources/css/select2/select2.css" />" rel="stylesheet" media="screen" />
	<link href="<c:url value="/resources/css/select2/select2-bootstrap.css" />" rel="stylesheet" media="screen" />
	<link href="<c:url value="/resources/css/bootstrap.min.css" />" rel="stylesheet" media="screen" />
	<link href="<c:url value="/resources/css/selectize.css" />" rel="stylesheet" media="screen" />
	\link href="<c:url value="/resources/css/bootstrap-datepicker3.min.css" />" rel="stylesheet" media="screen" />
	<link href="<c:url value="/resources/css/style.css" />" rel="stylesheet" media="screen" />
	<link href="<c:url value="https://maxcdn.bootstrapcdn.com/font-awesome/4.5.0/css/font-awesome.min.css" />" rel="stylesheet" media="screen" />
	<link href="<c:url value="https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.3/themes/smoothness/jquery-ui.css" />" rel="stylesheet" media="screen" />
    <link href="<c:url value="https://fonts.googleapis.com/css?family=Ubuntu:regular,bold&subset=Latin" />" rel="stylesheet" media="screen" />
    
	<script src="<c:url value="/resources/js/jquery.min.js" />"></script>
	<script src="<c:url value="/resources/js/jquery-ui.min.js" />"></script>
	<script src="<c:url value="/resources/js/select2/select2.min.js" />"></script>
	<script src="<c:url value="/resources/js/bootstrap.min.js" />"></script>
	<script src="<c:url value="/resources/js/bootstrap-datepicker.min.js" />"></script>
	<script src="<c:url value="/resources/js/main.js" />"></script>
	<script src="<c:url value="/resources/js/selectize.min.js" />"></script>

	<script type="text/javascript">
		var contextPath = '${pageContext.request.contextPath}';
	</script>
	<!--<script src="<c:url value="/resources/js/app.js" />"></script>-->
	
    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
	
	<tilesx:useAttribute id="styles" name="styles" classname="java.util.List" ignore="true" />
	
	<c:forEach var="cssName" items="${styles}">
		<link type="text/css" href="<c:url value="/resources/css/${cssName}"/>" rel="stylesheet" media="screen" />
	</c:forEach>
	
	<!-- HTML5 shim, for IE6-8 support of HTML5 elements -->
    <!--[if lt IE 9]>
      <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
    <![endif]-->
    
	<script>
	(function(i,s,o,g,r,a,m){i['GoogleAnalyticsObject']=r;i[r]=i[r]||function(){
	(i[r].q=i[r].q||[]).push(arguments)},i[r].l=1*new Date();a=s.createElement(o),
	m=s.getElementsByTagName(o)[0];a.async=1;a.src=g;m.parentNode.insertBefore(a,m)
	})(window,document,'script','//www.google-analytics.com/analytics.js','ga');
	
	ga('create', 'UA-73564685-1', 'auto');
	ga('send', 'pageview');
	</script>
    
</head>
<body>
	
	<tiles:insertAttribute name="header"  defaultValue="" />
	<!-- Page content -->
	<div class="">
		<% /* Show a message. See support.web package */ %>
        <c:if test="${not empty message}">
            <c:choose>
                <c:when test="${message.type == 'WARNING'}">
                    <c:set value="" var="alertClass" />
                </c:when>
                <c:otherwise>
                    <c:set value="alert-${fn:toLowerCase(message.type)}" var="alertClass" />
                </c:otherwise>
            </c:choose>
            <div class="alert ${alertClass}">
                <button type="button" class="btn close" data-dismiss="alert">&times;</button>
                <% /* Display a message by its code. If the code was not found, it will be displayed as default text */ %>
                <s:message code="${message.message}" arguments="${message.args}" text="${message.message}" />
            </div>
        </c:if>
        <c:if test="${not empty messages}">
        	<c:forEach var="message" items="${messages}">
				<c:choose>
				    <c:when test="${message.type == 'WARNING'}">
				        <c:set value="" var="alertClass" />
				    </c:when>
				    <c:otherwise>
				        <c:set value="alert-${fn:toLowerCase(message.type)}" var="alertClass" />
				    </c:otherwise>
				</c:choose>
				<div class="alert ${alertClass}" style="margin-bottom: 0px;">
				    <button type="button" class="btn close" data-dismiss="alert">&times;</button>
				    <% /* Display a message by its code. If the code was not found, it will be displayed as default text */ %>
				    <s:message code="${message.message}" arguments="${message.args}" text="${message.message}" />
            </div>
            </c:forEach>
        </c:if>
		<tiles:insertAttribute name="body" defaultValue="" />
	</div>
	<!-- End of page content -->
	<tiles:insertAttribute name="footer"  defaultValue="" />
	
</body>
</html>