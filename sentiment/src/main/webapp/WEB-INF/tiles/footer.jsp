<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="security"%>

<security:authorize access="isAuthenticated()">
	<security:authentication property="principal" var="principalUser" />
</security:authorize>

<div id="footer">
	<div class="container sb-slide">
		<div class="row">
			<div class="col-xs-12 col-sm-6">
				<ul>
					<li>Follow Pasabuy on</li>
					<li><a href="https://www.facebook.com/pasabuy" target="_blank"><i
							class="fa fa-facebook-square fa-2x"></i></a></li>
					<li><a href="https://www.instagram.com/pasabuy"
						target="_blank"><i class="fa fa-instagram fa-2x"></i></a></li>
					<li><a href="https://www.twitter.com/pasabuyme" target="_blank"><i
							class="fa fa-twitter-square fa-2x"></i></a></li>
				</ul>
				<p class="contactUs">
					<i class="fa fa-home fa-2x"></i>&nbsp;&nbsp;Philippines
				</p>
				<p class="contactUs">
					<i class="fa fa-phone fa-2x"></i>&nbsp;&nbsp;+639155989340
				</p>
				<p class="contactUs">
					<i class="fa fa-envelope fa-2x"></i>&nbsp;&nbsp;<a
						href="mailto:mail@pasabuy.com">mail@pasabuy.com</a>
				</p>
				<br>

			</div>
		</div>
		<div class="row">
			<div class="col-xs-12 col-sm-6">
				<p class="contactUs">
					Copyright&nbsp;<i class="fa fa-copyright"></i>&nbsp;2016 pasabuy.
					All Rights Reserved. Coded with <i class="fa fa-heart"></i> by cidd
				</p>
				<br>
			</div>
		</div>
	</div>
</div>
<!-- End footer bottom area -->