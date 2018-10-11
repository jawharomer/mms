<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>

<div>
	<sf:form id="add-dollar-to-dinar-form" commandName="dollarToDinar"
		onsubmit="addDollarToDinar(event)">
		<table class="w-100">
			<tbody>
				<tr>
					<td>Dollar</td>
					<td><sf:input  cssClass="form-control form-control-sm"
							path="dollar" readonly="true" /></td>
					<td><sf:errors path="dollar" /></td>
				</tr>


				<tr>
					<td>Dinar</td>
					<td><sf:input cssClass="form-control form-control-sm"
							path="dinar" /></td>
					<td><sf:errors path="dinar" /></td>
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
	function addDollarToDinar(event) {
		event.preventDefault();
		console.log("addDollarToDinar->fired");
		var data = $("#add-dollar-to-dinar-form").serializeJSON();
		console.log("data=", data);
		$.ajax({
			type : "POST",
			url : "<c:url value="/dollarToDinars/add"/>",
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