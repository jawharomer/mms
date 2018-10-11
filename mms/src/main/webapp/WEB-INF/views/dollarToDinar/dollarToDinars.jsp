<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>



<div>
	<div class="py-2">
		<h3>Dollar To Dinars</h3>
		<h4>Current 1 USD=${dollarToDinars[0].dinar} IQD</h4>
		<button class="btn btn-success" onclick="getAddingDollarToDinar()">
			<i class="fa fa-plus"></i>
		</button>

	</div>

	<table id="incomes-table" class="table table-bordered">
		<thead>
			<tr>
				<th>#</th>
				<th>Time</th>
				<th>Dollar</th>
				<th>Dinar</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${dollarToDinars}" var="item">
				<tr>
					<td>${item.id }</td>
					<td><fmt:formatDate pattern="yyyy-MM-dd hh:mm:ss"
							value="${item.time }" /></td>
					<td>${item.dollar}</td>
					<td>${item.dinar}</td>
				</tr>
			</c:forEach>

		</tbody>
	</table>
</div>