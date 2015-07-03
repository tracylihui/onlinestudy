<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/resources/css/admin/main.css" />
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/resources/css/validate/main.css" />
<script type="text/javascript"
	src="<%=request.getContextPath()%>/resources/js/jquery-1.7.2.min.js"></script>
</head>
<body>
	<div id="content">
		<h3 class="admin_link_bar">
			<jsp:include page="inc.jsp"></jsp:include>
		</h3>
		<sf:form method="post" modelAttribute="question" id="addForm">
			<table width="800" cellspacing="0" cellPadding="0">
				<thead>
					<tr>
						<td colspan="2">添加问题功能</td>
					</tr>
				</thead>
				<tr>
					<td class="rightTd" width="200px">问题名称:</td>
					<td class="leftTd"><sf:input path="name" size="30" /></td>
				</tr>
				<tr>
					<td class="rightTd">选项A:</td>
					<td class="leftTd"><input type="text" name="answer" size="50" /></td>
				</tr>
				<tr>
					<td class="rightTd">选项B:</td>
					<td class="leftTd"><input type="text" name="answer" size="50" /></td>
				</tr>
				<tr>
					<td class="rightTd">选项C:</td>
					<td class="leftTd"><input type="text" name="answer" size="50" /></td>
				</tr>
				<tr>
					<td class="rightTd">选项D:</td>
					<td class="leftTd"><input type="text" name="answer" size="50" /></td>
				</tr>
				<tr>
					<td class="rightTd" width="200px">分数:</td>
					<td class="leftTd"><sf:input path="score" size="30" /></td>
				</tr>
				<tr>
					<td class="rightTd" width="200px">答案:</td>
					<td class="leftTd"><input type="checkbox" name="theoption"
						value="1" />选项A <input type="checkbox" name="theoption" value="2" />选项B
						<input type="checkbox" name="theoption" value="3" />选项C <input
						type="checkbox" name="theoption" value="4" />选项D</td>
				</tr>
				<tr>
					<td class="rightTd" width="200px">试卷:</td>
					<td class="leftTd">
						<select name="eid">
							<c:forEach items="${examtypes }" var="examtype">
							<option <c:if test="${examtype.id==eid }">selected="selected"</c:if> value="${examtype.id }">${examtype.name }</option>
							</c:forEach>
						</select>
					</td>
				</tr>
				<tr>
					<td colspan="2" class="centerTd"><input type="hidden"
						name="eid" value="1" /><input type="submit" value="添加问题" /><input
						type="reset" /></td>
				</tr>
			</table>
		</sf:form>
	</div>
</body>
</html>