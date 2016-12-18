<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link href="${pageContext.request.contextPath}//static/css/main.css"
	rel="stylesheet" type="text/css">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

	<sf:form method="post"
		action="${pageContext.request.contextPath}/doCreate" commandName="offer">
		<table class="formTable">
			<tr>
				<td class="label">Name:</td>
				<td><sf:input path="name" name="name" type="text"></sf:input> <br><sf:errors path = "name" cssClass="error"></sf:errors></br></td>
			</tr>
			<tr>
				<td class="label">Email:</td>
				<td><sf:input path ="email" name="email" type="text"></sf:input> <br><sf:errors path = "email" cssClass="error"></sf:errors></br><br></br></td>
			</tr>
			<tr>
				<td class="label">Your Offer:</td>
				<td><sf:textarea path ="text" name="text" rows="10" cols="10"></sf:textarea><br><sf:errors path = "text" cssClass="error"></sf:errors></br></td>
			</tr>
			<tr>
				<td></td>
				<td class="label"><input value="Create Advert" type="submit"></td>
			</tr>
		</table>
	</sf:form>

</body>
</html>