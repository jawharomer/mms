<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>



<div>
	<div class="py-2">
		<h3>Revenues</h3>
	</div>

	<table class="table table-bordered">
		<thead>
			<tr>
				<th>Date</th>
				<th>Income</th>
				<th>Expense</th>
				<th>Revenue</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${revenues}" var="item">
				<tr>
					<td><fmt:formatDate value="${item.date}" pattern="yyyy-MM-dd" />
					</td>
					<td>${item.totalIncome}</td>
					<td>${item.totalExpense}</td>
					<td>${item.totalIncome-item.totalExpense}</td>
				</tr>

			</c:forEach>

		</tbody>


	</table>



</div>