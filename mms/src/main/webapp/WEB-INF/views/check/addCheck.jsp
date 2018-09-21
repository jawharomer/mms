<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>

<div>
	<sf:form id="add-check-form" commandName="check"
		onsubmit="addCheck(event)">
		<table class="w-100">
			<tbody>
				<tr>
					<td>Extra</td>
				</tr>

				<tr>
					<td><sf:textarea maxlength="499" cssClass="form-control" cols="20" rows="15"
							path="note" /></td>
				</tr>

				<tr>
					<td>
						<button class="btn btn-sm  btn-success">
							<i class="fa fa-plus"></i>
						</button>
					</td>

				</tr>

			</tbody>

		</table>


	</sf:form>

</div>


<script>
	function addCheck(event) {
		event.preventDefault();
		console.log("addCheck->fired");
		var data = $("#add-check-form").serializeJSON();
		console.log("data=", data);
		$.ajax({
			type : "POST",
			url : "<c:url value="/checks/add"/>",
			data : JSON.stringify(data),
			contentType : "application/json",
			success : function(response) {
				$("#modal-body").html(response);
				$("#modal").modal("show");
			},
			error : function(response) {
				$("#modal-body").html(response.responseText);
				$("#modal").modal("show");
			}
		});
	}
</script>