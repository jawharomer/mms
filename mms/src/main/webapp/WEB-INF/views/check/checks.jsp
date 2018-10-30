<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>



<div id="main">
	<div class="py-2 d-print-none">
		<h3>Checks</h3>
		<button class="btn btn-success" onclick="getAddingCheck()">
			<i class="fa fa-plus"></i>
		</button>

		<button class="btn btn-info" onclick="cusPrint('main')">
			<i class="fa fa-print"></i>
		</button>
	</div>


	<c:forEach items="${checks}" var="item">
		<div id="tr-${item.id}">
			<table class="table table-bordered">
				<thead>
					<tr>
						<th>Time</th>
						<th>Amount</th>
						<th>Extra</th>
						<th class="d-print-none">F</th>
					</tr>
				</thead>
				<tbody>
					<tr class="section-to-print">
						<td><fmt:formatDate value="${item.time}"
								pattern="yyyy-MM-dd hh:mm:ss" />
						<td>${item.amount}</td>
						<td><span style="white-space: pre-line">${item.note}</span></td>
						<td class="d-print-none">
							<button class="btn btn-danger btn-sm"
								onclick="deleteCheck(${item.id})">
								<i class="fa fa-times"></i>
							</button>
							<button class="btn btn-info btn-sm"
								onclick="cusPrint('tr-${item.id}')">
								<i class="fa fa-print"></i>
							</button>
						</td>

					</tr>



				</tbody>

			</table>

			<div class="row d-none d-print-block">
				<div class="col-6 py-2">Finance Signature</div>
				<div class="col-6 py-2">Manager Signature</div>

			</div>
		</div>


	</c:forEach>

</div>