$(document)
		.ready(
				function() {

					$("#from").datepicker({
						dateFormat : "yy-mm-dd"
					}).datepicker("setDate", $("#from").val());

					$("#to").datepicker({
						dateFormat : "yy-mm-dd"
					}).datepicker("setDate", $("#to").val());

					console.log("Activate data table");

					// S-DataTable
					$('#revenue-table tfoot th:not(.cus-not-search)')
							.each(
									function() {
										var title = $(this).text();
										$(this)
												.html(
														'<input class="form-control fomt-control-sm cus-inline" type="text" />');
									});

					var table = $('#revenue-table').DataTable({
						paginate : false,
						dom : 'Bfrtip',
						buttons : [ {
							extend : "excel",
							charset : 'UTF-8',
							className : "btn btn-sm  btn-outline-info",
							exportOptions : {
								columns : ':not(.cus-not-export)'
							}
						}, {
							extend : "csv",
							charset : 'UTF-8',
							className : "btn btn-sm btn-outline-info",
							exportOptions : {
								columns : ':not(.cus-not-export)'
							}
						} ],
						bInfo : false
					});

					table.columns().every(
							function() {
								var that = this;
								console.log("that=", that);
								console.log("that.search()=", that.search());

								$('input', this.footer()).on('keyup change',
										function() {
											if (that.search() !== this.value) {
												that.search(this.value).draw();
											}
										});
							});

				});