<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
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
			$("#matchpass").text("<fmt:message key='MatchedPasswords.user.password'></fmt:message>")
			$("#matchpass").addClass("valid")
			$("#matchpass").removeClass("error")
		}
	else
		{
			$("#matchpass").text("<fmt:message key='UnmatchedPasswords.user.password'></fmt:message>")
			$("#matchpass").addClass("error")
			$("#matchpass").removeClass("valid")
		}
		}
	
}
$(document).ready(onLoad);
</script>


</body>
</html>