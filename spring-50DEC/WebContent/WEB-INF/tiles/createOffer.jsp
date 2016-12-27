<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript">
	function onDeleteClick(event) {

		var doDelete = confirm("Are you sure you want to delete this offer");
		if (doDelete == false) {
			event.preventDefault();
		}
	}
	function onReady() {
		$("#delete").click(onDeleteClick);
	}
	$(document).ready(onReady);
</script>

<sf:form method="post"
	action="${pageContext.request.contextPath}/doCreate"
	commandName="offer">

	<sf:input path="id" name="id" type="hidden" />
	<table class="formTable">
		<tr>
			<td class="label">Your Offer:</td>
			<td><sf:textarea path="text" name="text" rows="10" cols="10"></sf:textarea><br>
				<sf:errors path="text" cssClass="error"></sf:errors></br></td>
		</tr>

		<tr>
			<td class="label"><input value="Save Advert" type="submit"></td>
		</tr>
		<c:if test="${offer.id!=0}">
			<tr>
				<td class="label"><input class="delete" name="delete"
					id="delete" value="Delete Advert" type="submit"></td>
			</tr>

		</c:if>

	</table>
</sf:form>

