<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>

<div>
	<h4>Current 1 USD=${dollarToDinar.dinar} IQD</h4>
	<sf:form id="add-check-form" commandName="check"
		onsubmit="addCheck(event)">
		<table class="w-100">
			<tbody>
				<tr>
					<td>Current Amount</td>
					<td>${check.amount}</td>
				</tr>
				<tr>
					<td>Calculated Amount</td>
					<td id="calculatedAmount"></td>
				</tr>
				<tr>
					<td colspan="3">Extra</td>
				</tr>
				<tr>
					<td>
						<div>
							<select id="currency" class="form-control form-contrl-sm">
								<option value="">Choose</option>
								<optgroup label="IQD">
									<option value="IQD-250">250</option>
									<option value="IQD-500">500</option>
									<option value="IQD-1000">1,000</option>
									<option value="IQD-5000">5,000</option>
									<option value="IQD-10000">10,000</option>
									<option value="IQD-25000">25,000</option>
									<option value="IQD-50000">50,000</option>
								</optgroup>
								<optgroup label="USD">
									<option value="USD-1">1</option>
									<option value="USD-2">2</option>
									<option value="USD-5">5</option>
									<option value="USD-10">10</option>
									<option value="USD-20">20</option>
									<option value="USD-50">50</option>
									<option value="USD-100">100</option>
								</optgroup>
							</select>
						</div>
					</td>
					<td><input id="amount" type="number"
						class="form-control form-control-sm"></td>
					<td>
						<button type="button" class="btn btn-success btn-sm"
							onclick="addCurrency()">
							<i class="fa fa-plus"></i>
						</button>
					</td>
				</tr>

				<tr>
					<td colspan="3"><sf:textarea readonly="true" maxlength="499"
							cssClass="form-control" cols="20" rows="15" path="note" /></td>
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
	var currentDinarToDollar = ${dollarToDinar.dinar};
	var total = 0;

	$(document).ready(function() {
		$(window).keydown(function(event) {
			if (event.keyCode == 13) {
				event.preventDefault();
				return false;
			}
		});
	});

	var items = [];

	function addCurrency() {
		console.log("addCurrency->fired");

		var currency = $("#currency").val();
		var amount = $("#amount").val();

		console.log("currency=", currency);
		console.log("amount=", amount);

		if (currency && !isNaN(amount) && amount > 0) {
			var newStr = currency + "*" + amount + "\n";

			var currenctyInput = currency.split("-");

			var newItem = {
				amount : +amount,
				currentyType : currenctyInput[0],
				unit : +currenctyInput[1]
			};
			console.log("newItem=", newItem);
			items.push(newItem);

			console.log("newStr=" + newStr);

			for (var i = 0; i < items.length; i++) {
				var item = items[i];

				var calAmount = item.amount * item.unit;

				if (item.currentyType == "IQD") {
					console.log("IQD");
					total += calAmount/currentDinarToDollar;
				} else {
					console.log("USD");
					total += calAmount;
				}

			}

			console.log("total=" + total);

			$("#calculatedAmount").text(total);

			$("#note").append(newStr);

			$("#amount").val("");

		}

	}
	function addCheck(event) {
		event.preventDefault();
		console.log("addCheck->fired");
		if(total==${check.amount}){
		
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
		else{
			alert("Calculated amount is not equal to Current amount!");
		}
	}
</script>