<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>


<table class="offers">
	<tr>
		<td><b>Name</b></td>
		<td><b>Email</b></td>
		<td><b>Offer</b></td>


	</tr>
	<c:forEach var="offer" items="${offers}">
		<tr>
			<td><c:out value="${offer.user.name}"></c:out></td>
			<td><c:out value="${offer.user.email}"></c:out></td>
			<td><c:out value="${offer.text}"></c:out></td>

		</tr>
	</c:forEach>
</table>

<c:choose>
	<c:when test="${hasOffer}">
	<p>
	<a href="${pageContext.request.contextPath}/createOffer">Edit or delete your current offer</a>
</p>
	</c:when>
	<c:otherwise>
		<p>
			<a href="${pageContext.request.contextPath}/createOffer">Create a
				new Offer</a>
		</p>
	</c:otherwise>
</c:choose>




<sec:authorize access="hasRole('ROLE_ADMIN')">
	<p>
		<a href="${pageContext.request.contextPath}/admin">Admin Section</a>
	</p>
</sec:authorize>
