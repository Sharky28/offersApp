<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<h2> Create a new Account</h2>
	<sf:form id="details" method="post"
		action="${pageContext.request.contextPath}/createAccount" commandName="user">
		<table class="formTable">
			<tr>
				<td class="label">Username:</td>
				<td><sf:input path="username" name="username" type="text"></sf:input><div class="error"><sf:errors path = "username" ></sf:errors></div></td>
			</tr>
			<tr>
				<td class="label">Name:</td>
				<td><sf:input path="name" name="name" type="text"></sf:input><div class="error"><sf:errors path = "name" ></sf:errors></div></td>
			</tr>
			<tr>
				<td class="label">Email:</td>
				<td><sf:input path ="email" name="email" type="text"></sf:input><div class="error"><sf:errors path = "email" ></sf:errors></div></td>
			</tr>
			<tr>
				<td class="label">Password:</td>
				<td><sf:input id="password" path ="password" name="password" type="password"></sf:input><div class="error"><sf:errors path = "password" ></sf:errors></div></td>
			</tr>
			<tr>
				<td class="label">Confirm Password:</td>
				<td><input id="confirmpass" name="confirmPass" type="password"></input><div id ="matchpass"></div></td>
			</tr>
			
			<tr>
				<td></td>
				<td class="label"><input value="Create Account" type="submit"></td>
			</tr>
		</table>
	</sf:form>


