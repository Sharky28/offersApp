<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
 <%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<p><a href="${pageContext.request.contextPath}/offers">Show Current Offers</a></p>
<p><a href="${pageContext.request.contextPath}/createOffer">Create a new Offer</a></p>

<sec:authorize access="isAuthenticated()">

<%-- <p><a href="<c:url value='j_spring_security_logout'></c:url>"> Log Out</a></p> --%>
<c:url var="logoutUrl" value="/loggedOut" />
	<form action="${logoutUrl}" id="logout" method="post">
		<input type="hidden" name="${_csrf.parameterName}"
			value="${_csrf.token}" />
	</form>
	<a class="login" href="#"
		onclick="document.getElementById('logout').submit();">Log out</a>
		
		
		
</sec:authorize>
<sec:authorize access="!isAuthenticated()">	
<p><a href="${pageContext.request.contextPath}/login">Log in</a></p>
</sec:authorize>	
		
<sec:authorize access="hasRole('ROLE_ADMIN')">	
<p><a href="${pageContext.request.contextPath}/admin">Admin Section</a></p>
</sec:authorize>	
</body>
</html>