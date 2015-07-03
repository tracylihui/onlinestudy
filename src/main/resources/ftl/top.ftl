<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
   <input type="hidden" id="ctx" value="<%=request.getContextPath()%>"/>
<div class="header">
  <div class="head_center"><a href="<%=request.getContextPath()%>/index"><img  style="margin-top:17px;" src="<%=request.getContextPath() %>/resources/css/index/image/logo.png"/></a>&nbsp;&nbsp;&nbsp;在线学习系统
    <div class="head_right"><a href="<%=request.getContextPath()%>/student">${indexLogin.username}</a>&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;<a href="<%=request.getContextPath()%>/logout">退出</a></div>
  </div>
</div>
<div class="jx"></div>
<div class="menu-bar">
  <div class="menu">
    <ul>
      <li> <a id="highlight" href="<%=request.getContextPath()%>/index">网站首页</a> </li>
      <c:forEach items="${cs }" var="channel">
				<li><a id="highlight" href="<%=request.getContextPath()%>/channel/${channel.id }">${channel.name }</a></li>
		</c:forEach>
    </ul>
  </div>
</div>