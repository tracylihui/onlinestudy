<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/resources/css/admin/main.css"/>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/resources/css/validate/main.css"/>
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/jquery.validate.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/core/jquery.cms.validate.js"></script>
<script type="text/javascript">
$(function(){
	$("#addForm").cmsvalidate();
});
</script>
</head>
<body>
<div id="content">
	<h3 class="admin_link_bar">
		<jsp:include page="inc.jsp"></jsp:include>
	</h3>
	<sf:form method="post" modelAttribute="channel" id="addForm">
	<table width="550px" cellspacing="0" cellPadding="0">
		<thead><tr><td colspan="2">添加[${pc.name}]子栏目功能</td></tr></thead>
		<tr>
			<td class="rightTd" width="96px">栏目名称:</td><td class="leftTd"><sf:input path="name" size="30"/><sf:errors cssClass="errorContainer" path="name"/></td>
		</tr>
		<tr>
			<td class="rightTd">是否指定链接</td>
			<td class="leftTd">
				<sf:radiobutton path="customLink" value="0"/>不指定
				<sf:radiobutton path="customLink" value="1"/>指定
			</td>
		</tr>
		<tr>
			<td class="rightTd">链接地址:</td>
			<td><sf:input path="customLinkUrl" size="50"/></td>
		</tr>
		<tr>
			<td class="rightTd">状态:</td>
			<td>
				<sf:radiobutton path="status" value="0"/>启用
				<sf:radiobutton path="status" value="1"/>停用
			</td>
		</tr>
		<tr>
			<td class="rightTd">导航序号:</td>
			<td>
				<sf:input path="navOrder" size="50"/>
			</td>
		</tr>
		<tr>
			<td colspan="2" class="centerTd"><input type="submit" value="添加栏目"/><input type="reset"/></td>
		</tr>
	</table>
	</sf:form>
</div>
</body>
</html>