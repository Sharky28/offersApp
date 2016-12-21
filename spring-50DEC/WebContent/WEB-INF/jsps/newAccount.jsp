<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link href="${pageContext.request.contextPath}//static/css/main.css"
	rel="stylesheet" type="text/css">
<script type="text/javascript" src="${pageContext.request.contextPath}/static/script/jquery.js"></script>

<script type="text/javascript">

function onLoad(){
	$("#password").keyup(checkPasswordsMatch);
	$("#confirmpass").keyup(checkPasswordsMatch);
	$("#details").submit(canSubmit);
}

function canSubmit(){
	var password = $("#password").val();
	var confirmpass= $("#confirmpass").val();
	if(password != confirmpass){
		alert("Passwords do not match");
		return false;		
	}
	else
	{
		return true;
	}
}

function checkPasswordsMatch(){
	var password = $("#password").val();
	var confirmpass= $("#confirmpass").val();
	
	if(password.length>3|| confirmpass.length>3)
		{
		
	if(password == confirmpass)
		{
			$("#matchpass").text("Passwords Match")
			$("#matchpass").addClass("valid")
			$("#matchpass").removeClass("error")
		}
	else
		{
			$("#matchpass").text("Passwords do not Match")
			$("#matchpass").addClass("error")
			$("#matchpass").removeClass("valid")
		}
		}
	
}
$(document).ready(onLoad);
</script>


<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h2> Create a new Account</h2>
	<sf:form id="details" method="post"
		action="${pageContext.request.contextPath}/createAccount" commandName="user">
		<table class="formTable">
			<tr>
				<td class="label">Username:</td>
				<td><sf:input path="username" name="username" type="text"></sf:input><div class="error"><sf:errors path = "username" ></sf:errors></div></td>
			</tr>
			<tr>
				<td class="label">Email:</td>
				<td><sf:input path ="email" name="email" type="text"></sf:input><div class="error"><sf:errors path = "email" ></sf:errors></div></td>
			</tr>
			<tr>
				<td class="label">Password:</td>
				<td><sf:input id="password" path ="password" name="password" type="text"></sf:input><div class="error"><sf:errors path = "password" ></sf:errors></div></td>
			</tr>
			<tr>
				<td class="label">Confirm Password:</td>
				<td><input id="confirmpass" name="confirmPass" type="text"></input><div id ="matchpass"></div></td>
			</tr>
			
			<tr>
				<td></td>
				<td class="label"><input value="Create Account" type="submit"></td>
			</tr>
		</table>
	</sf:form>

</body>
</html>