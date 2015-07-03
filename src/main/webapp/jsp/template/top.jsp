<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/resources/css/index/lrtk.css" />
<script type="text/javascript">
var timeout         = 500;
var closetimer		= 0;
var ddmenuitem      = 0;

function mopen(id)
{	
	// cancel close timer
	mcancelclosetime();

	// close old layer
	if(ddmenuitem) ddmenuitem.style.visibility = 'hidden';

	// get new layer and show it
	ddmenuitem = document.getElementById(id);
	ddmenuitem.style.visibility = 'visible';

}
// close showed layer
function mclose()
{
	if(ddmenuitem) ddmenuitem.style.visibility = 'hidden';
}

// go close timer
function mclosetime()
{
	closetimer = window.setTimeout(mclose, timeout);
}

// cancel close timer
function mcancelclosetime()
{
	if(closetimer)
	{
		window.clearTimeout(closetimer);
		closetimer = null;
	}
}

// close layer when click-out
document.onclick = mclose; 
</script>
   <input type="hidden" id="ctx" value="<%=request.getContextPath()%>"/>
<div class="header">
  <div class="head_center"><a href="<%=request.getContextPath()%>/index"><img  style="margin-top:17px;" src="<%=request.getContextPath() %>/resources/css/index/image/logo.png"/></a>&nbsp;&nbsp;&nbsp;在线学习系统
    <div class="head_right"><a href="<%=request.getContextPath()%>/student">${indexLogin.username}</a>&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;<a href="<%=request.getContextPath()%>/logout">退出</a></div>
  </div>
</div>
<div class="jx"></div>
<div class="menu-bar">
  <div id="sddm">
    <ul>
      <li> <a id="highlight"  href="<%=request.getContextPath()%>/index">网站首页</a> 
      </li>
      <c:forEach items="${cs }" var="newchannel">
		<li><a id="highlight" onmouseover="mopen('${newchannel.parent.id }')" onmouseout="mclosetime()" href="<%=request.getContextPath()%>/channel/${newchannel.parent.id }">${newchannel.parent.name }</a>
			<div id="${newchannel.parent.id }" onmouseover="mcancelclosetime()" onmouseout="mclosetime()">
			 <c:forEach items="${newchannel.child }" var="channel">
			 	<a href="<%=request.getContextPath()%>/channel/${channel.id }">${channel.name }</a>
			 </c:forEach>
			</div>
		</li>
	</c:forEach>
    </ul>
  </div>
</div>