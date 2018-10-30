function getAddingCheck() {
	console.log("getAddingCheck->fired");
	$.ajax({
		url : $$ContextURL + '/checks/add',
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

function deleteCheck(id) {
	console.log("deleteCheck->fired");
	console.log("id=" + id);

	$.when(cusConfirm()).done(function() {
		$.ajax({
			url : $$ContextURL + '/checks/delete/' + id,
			type : 'POST',
			success : function(response) {
				$("#modal-body").html(response);
				$("#modal").modal("show");
			},
			error : function(response) {
				$("#modal-body").html(response.responseText);
				$("#modal").modal("show");
			}
		});
	});

}

function cusPrint(id) {
	console.log("cusPrint->fired");
	console.log("id=", id);

	var divs = document.getElementsByClassName('section-to-print');

	$(".section-to-print").each(function(index) {
		$(this).removeClass("section-to-print");
	});

	$("#" + id).addClass('section-to-print');
	window.print();

}