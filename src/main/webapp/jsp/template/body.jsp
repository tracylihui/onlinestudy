<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<div id="content">
	<div id="content_con">
		<div id="xiaoxun"></div>
		<div id="xwgk_xxgk">
			<div id='xwgk'>
				<h3>
					<a href="channel/1" class="index_title_href">学科前沿</a>
				</h3>
				<div id="xwgk_bg"></div>
				<dl>
					<c:forEach items="${t1 }" var="topic">
						<dd>
							<a title="${topic.title }"
								href="<%=request.getContextPath()%>/topic/${topic.id }"
								class='index_link'> [<fmt:formatDate
									value="${topic.publishDate }" pattern="yyyy-MM-dd" />]
								${topic.title }
							</a>
						</dd>
					</c:forEach>

				</dl>
			</div>
			<div id="xxgk">
				<h3>
					系统概况
				</h3>
				<div id="xxgk_bg"></div>
				这是系统概括这是系统概括这是系统概括这是系统概括这是系统概括这是系统概括这是系统概括这是系统概括这是系统概括这是系统概括这是系统概括这是系统概括这是系统概括这是系统概括这是系统概括这是系统概括这是系统概括这是系统概括这是系统概括这是系统概括这是系统概括这是系统概括这是系统概括这是系统概括这是系统概括这是系统概括这是系统概括这是系统概括这是系统概括这是系统概括这是系统概括这是系统概括这是系统概括这是系统概括这是系统概括
			</div>
		</div>
		<div id="hdjx_jyky">
			<div id='hdjx'>
				<dl>
					<dt>
						<span class="t_title">考研部落</span><span class="more"><a
							href="<%=request.getContextPath()%>/channel/2">更多</a></span>
					</dt>
					<c:forEach items="${t2 }" var="topic">
						<dd>
							<a title="${topic.title }"
								href="<%=request.getContextPath()%>/topic/${topic.id }"
								class='index_link'> [${fn:substring(topic.publishDate, 0, 10)}]
								${topic.title } </a>
						</dd>
					</c:forEach>
				</dl>
			</div>

			<div id='jyky'>
				<dl>
					<dt>
						<span class="t_title">资格认证</span><span class="more"><a
							href="<%=request.getContextPath()%>/channel/3">更多</a></span>
					</dt>
					<c:forEach items="${t3 }" var="topic">
						<dd>
							<a title="${topic.title }"
								href="<%=request.getContextPath()%>/topic/${topic.id }"
								class='index_link'> [${fn:substring(topic.publishDate, 0, 10)}]
								${topic.title } </a>
						</dd>
					</c:forEach>
				</dl>
			</div>
		</div>
		<div id="chief_keyword">
			<div>
				<c:forEach items="${kws }" var="keyword">
					<span class="keyword" href="keyword/${keyword.name}">${keyword.name}</span>
				</c:forEach>
			</div>
		</div>
		<div id="chief_keyword">
			<div>
				<c:forEach items="${link }" var="cmslink">
					<a  href="${cmslink.url}" style="padding: 5px;margin: 5px 7px;margin-top: 18px;float: left;color: #fff;cursor: pointer;color:black;">${cmslink.title}</a>
				</c:forEach>
			</div>
		</div>
	</div>
</div>