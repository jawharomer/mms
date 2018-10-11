function getAddingDollarToDinar() {
	console.log("getAddingDollarToDinar->fired");
	$.ajax({
		url : $$ContextURL + '/dollarToDinars/add',
		type : 'GET',
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