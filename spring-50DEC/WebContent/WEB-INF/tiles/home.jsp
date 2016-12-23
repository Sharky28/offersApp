<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
 <%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>


<body>

<p><a href="${pageContext.request.contextPath}/offers">Show Current Offers</a></p>
<p><a href="${pageContext.request.contextPath}/createOffer">Create a new Offer</a></p>

	
		
<sec:authorize access="hasRole('ROLE_ADMIN')">	
<p><a href="${pageContext.request.contextPath}/admin">Admin Section</a></p>
</sec:authorize>	
