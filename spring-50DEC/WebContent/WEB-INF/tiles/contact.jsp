<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<h2>Send message</h2>
<sf:form method="post" commandName="message">

	<input type="hidden" name="flowExecutionKey"
		value="${flowEecutionKey }"></input>
		
	<input type="hidden" name="_eventId"
		value="send"></input>
		

	<table class="formTable">
		<tr>
			<td class="label">Your name:</td>
			<td><sf:input path="name" type="text"></sf:input>
				<div class="error">
					<sf:errors path="name"></sf:errors>
				</div></td>
		</tr>
		<tr>
			<td class="label">Your Email:</td>
			<td><sf:input path="email" type="text"></sf:input>
				<div class="error">
					<sf:errors path="email"></sf:errors>
				</div></td>
		</tr>
		<tr>
			<td class="label">Subject:</td>
			<td><sf:input path="subject" type="text"></sf:input>
				<div class="error">
					<sf:errors path="subject"></sf:errors>
				</div></td>
		</tr>
		<tr>
			<td class="label">Your Message:</td>
			<td><sf:textarea path="content" type="text"></sf:textarea>
				<div class="error">
					<sf:errors path="content"></sf:errors>
				</div></td>
		</tr>

		<tr>
			<td></td>
			<td class="label"><input value="Send Message" type="submit"></td>
		</tr>
	</table>
</sf:form>


