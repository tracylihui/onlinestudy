<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>${baseInfo.name }</title>
<meta http-equiv="keywords" content="昭通师专,昭通学院,昭通学院附中,昭通师专附中">
<meta http-equiv="description" content="昭通学院附中网站,昭通师专附中网站">
<!-- Date: 2013-09-04 -->
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/resources/css/index/style.css" />
<link href="<%=request.getContextPath()%>/resources/css/index/web.css"
	rel="stylesheet" type="text/css" />
<link
	href="<%=request.getContextPath()%>/resources/personal/jquery-ui.min.css"
	rel="stylesheet">
<link href="<%=request.getContextPath()%>/resources/reg/css/demo.css"
	type="text/css" rel="stylesheet" />
<link
	href="<%=request.getContextPath()%>/resources/reg/plugin/jqtransform/jqtransform.css"
	type="text/css" rel="stylesheet" />
<link
	href="<%=request.getContextPath()%>/resources/reg/plugin/datePicker/datePicker.css"
	type="text/css" rel="stylesheet" />
</head>
<body>
	<jsp:include page="/jsp/template/top2.jsp" />
	<div class="main white" style="padding-top: 20px;">
		<div style="font-size: 18px; font-family: '微软雅黑'; color: red;text-align: center;">
			考试科目：${examtype.name }
		</div>
		<div style="font-size: 16px; font-family: '微软雅黑'; margin-left: 60px;">
			考试人：${indexLogin.username }
		</div>
		<form class="registerform" method="post" action="<%=request.getContextPath()%>/exam">
		<c:forEach items="${questionDtos }" var="questionDto" varStatus="status">
			<dl style="overflow:auto; min-height:60px; width: 840px; margin-left: 60px; margin-top:20px; font-size: 14px; font-family: '微软雅黑'">
				<dt style="float:left;width: 50px;">第${status.index+1 }题</dt>
        		<dd style="float:left;width: 790px;">
        			${questionDto.question.name }&nbsp;&nbsp;(${questionDto.question.score }分)<br><br>
        			<c:forEach items="${questionDto.answers }" var="answer" varStatus="st">
        			<input type="checkbox" name="answer${status.index }" value="${st.index+1 }" />&nbsp;&nbsp;${answer }<br>
        			</c:forEach>
        		</dd>
			</dl>
			
		</c:forEach>
		<input type="hidden" value="${examtype.id }" name="eid" />
		<input type="submit" value="提交" style="background-color:#ef7c5f; border:none; color:#FFF;font-family:'MicroSoft YaHei';margin-top:10px;margin-left: 500px;cursor: pointer;" />
		</form>
	</div>
</body>
</html>