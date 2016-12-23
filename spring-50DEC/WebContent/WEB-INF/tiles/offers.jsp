
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

	<table class="offers">
		<tr>
			<td><b>Name</b></td>
			<td><b>Email</b></td>
			<td><b>Offer</b></td>


		</tr>
		<c:forEach var="offer" items="${offers}">
			<tr>
				<td><c:out value="${offer.name}"></c:out></td>
				<td><c:out value="${offer.email}"></c:out></td>
				<td><c:out value="${offer.text}"></c:out></td>

			</tr>
		</c:forEach>
	</table>
