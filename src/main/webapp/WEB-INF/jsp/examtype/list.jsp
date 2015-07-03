<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/resources/css/admin/main.css"/>
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/core/jquery.cms.core.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/admin/main.js"></script>
</head>
<body>
<div id="content">
	<h3 class="admin_link_bar">
		<jsp:include page="inc.jsp"></jsp:include>
	</h3>
	<table width="800" cellspacing="0" cellPadding="0" id="listTable">
		<thead>
		<tr>
			<td>考试标识</td>
			<td>考试名称</td>
			<td width="300">考试描述</td>
			<td>考试操作</td>
		</tr>
		</thead>
		<tbody>
		<c:forEach items="${datas.datas }" var="examtype">
			<tr>
				<td>${examtype.id }&nbsp;</td>
				<td><a href="<%=request.getContextPath() %>/admin/question/${examtype.id }" class="list_link">${examtype.name }</a></td>
				<td>${examtype.descr }&nbsp;</td>
				<td>
					<a href="delete/${examtype.id }" class="list_op delete">删除</a>
					<a href="update/${examtype.id }" class="list_op">更新</a>
					<a href="<%=request.getContextPath() %>/admin/question/clearQuestions/${examtype.id }" class="list_op delete">清空试题</a>
				&nbsp;
				</td>
			</tr>
		</c:forEach>
		</tbody>
		<tfoot>
		<tr>
			<td colspan="6" style="text-align:right;margin-right:10px;">
			<jsp:include page="/jsp/pager.jsp">
				<jsp:param value="${datas.total }" name="totalRecord"/>
				<jsp:param value="examtypes" name="url"/>
			</jsp:include>
			</td>
		</tr>
		</tfoot>
	</table>
</div>
</body>
</html>