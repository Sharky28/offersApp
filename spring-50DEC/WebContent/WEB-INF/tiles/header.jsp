
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<a class="title" href="<c:url value ='/'/>">Offers</a>


<sec:authorize access="!isAuthenticated()">

	<a class="login" href="/spring/login">Log in</a>

</sec:authorize>

<sec:authorize access="isAuthenticated()">

	<c:url var="logoutUrl" value="/loggedOut" />
	<form action="${logoutUrl}" id="logout" method="post">
		<input type="hidden" name="${_csrf.parameterName}"
			value="${_csrf.token}" />
	</form>
	<a class="login" href="#"
		onclick="document.getElementById('logout').submit();">Log out</a>
</sec:authorize>