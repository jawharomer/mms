<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>



<div id="section-to-print">
	<div class="py-2 d-print-none">
		<h3>Checks</h3>
		<button class="btn btn-success" onclick="getAddingCheck()">
			<i class="fa fa-plus"></i>
		</button>

		<button class="btn btn-info" onclick="window.print()">
			<i class="fa fa-print"></i>
		</button>
	</div>

	<table class="table table-bordered">
		<thead>
			<tr>
				<th>Time</th>
				<th>Amount</th>
				<th>Extra</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${checks}" var="item">
				<tr>
					<td><fmt:formatDate value="${item.time}"
							pattern="yyyy-MM-dd hh:mm:ss" />
					<td>${item.amount}</td>
					<td>${item.note}</td>
				</tr>

			</c:forEach>

		</tbody>


	</table>

	<div class="row d-none d-print-block">
		<div class="col-6 py-2">First Signature</div>
		<div class="col-6 py-2">Second Signature</div>

	</div>



</div>