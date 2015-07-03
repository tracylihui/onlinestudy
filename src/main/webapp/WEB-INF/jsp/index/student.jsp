<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
	<div class="main white">
		<div class="personal">
			<div class="left">
				<img
					src="<%=request.getContextPath()%>/resources/css/index/image/avatar.png"
					width="96" height="96" />
				<div class="stuname">${student.username}</div>
				<ul>
					<li><a id="one">个人信息</a></li>
					<li><a id="two">在线考试</a></li>
					<li><a id="three">修改密码</a></li>
					<li><a href="<%=request.getContextPath()%>/index">返回首页</a></li>
				</ul>
			</div>
			<div class="right">
				<div id="accordion">
					<h3>个人信息</h3>
					<div>
						<div id="person">
							<label id="changpersonbtn" style="float: right; cursor: pointer">编辑</label>
							<table width="500px" style="margin: 0 auto; font-size: 13px;">
								<tr>
									<td class="need" style="width: 10px;"></td>
									<td style="width: 105px; text-align: right; color: #999999">姓名：</td>
									<td>${student.nickname}</td>
								</tr>
								<tr>
									<td class="need"></td>
									<td style="text-align: right; color: #999999">邮箱：</td>
									<td>${student.email}</td>
								</tr>
								<tr>
									<td class="need"></td>
									<td style="text-align: right; color: #999999">电话：</td>
									<td>${student.phone}</td>
								</tr>
								<tr>
									<td class="need"></td>
									<td style="text-align: right; color: #999999">QQ：</td>
									<td>${student.qq}</td>
								</tr>
							</table>
						</div>
						<div id="changeperson" style="display: none;">
							<label id="canclepersonbtn" style="float: right; cursor: pointer">取消</label>
							<form class="registerform" method="post"
								action="<%=request.getContextPath()%>/update/${student.id}">
								<table width="500px" style="margin: 0 auto;">
									<tr>
										<td class="need">*</td>
										<td style="width: 105px;">姓名：</td>
										<td style="width: 205px;"><input type="text"
											value="${student.nickname}" name="nickname" class="inputxt"
											datatype="*" nullmsg="请填写姓名！" /></td>
										<td><div class="Validform_checktip"></div></td>
									</tr>
									<tr>
										<td class="need">*</td>
										<td>邮箱：</td>
										<td><input type="text" value="${student.email}"
											name="email" class="inputxt" datatype="*" nullmsg="请输入邮箱！" /></td>
										<td><div class="Validform_checktip"></div></td>
									</tr>
									<tr>
										<td class="need">*</td>
										<td style="width: 105px;">电话：</td>
										<td style="width: 205px;"><input type="text"
											value="${student.phone}" name="phone" class="inputxt"
											datatype="m" errormsg="请输入您的手机号码！" /></td>
										<td><div class="Validform_checktip"></div></td>
									</tr>
									<tr>
										<td class="need">*</td>
										<td>QQ：</td>
										<td><input type="text" value="${student.qq}" name="qq"
											class="inputxt" datatype="n" /></td>
										<td><div class="Validform_checktip"></div></td>
									</tr>
									<tr>
										<td class="need"></td>
										<td></td>
										<td colspan="2"><input type="submit" value="保存" /><span
											id="msgdemo" style="color: red; margin-left: 30px;"></span></td>
									</tr>
								</table>
							</form>
						</div>
					</div>
					<h3>在线考试</h3>
					<div>
						<form method="post" action="<%=request.getContextPath()%>/beginexam" target="_blank">
							<table width="500px" style="margin: 0 auto;">
								<tr>
                 				<td>&nbsp;</td>
                 				<td>
                 					<select  name="eid">
                 						<c:forEach items="${examtypes }" var="examtype">
                 						<option value="${examtype.id }">${examtype.name }</option>
                 						</c:forEach>
                 					</select>
                 				</td>
                 				</tr>
                 				<tr>
									<td></td>
									<td colspan="2"><input type="submit" value="开始考试" /></td>
								</tr>
							</table>
						</form>
					</div>
					<h3>修改密码</h3>
					<div>
						<form class="registerform" method="post"
							action="<%=request.getContextPath()%>/updatepass/${student.id}">
							<table width="500px" style="margin: 0 auto;">
								<tr>
									<td class="need" style="width: 10px;">*</td>
									<td style="width: 105px;">当前密码：</td>
									<td style="width: 205px;"><input type="password"
										name="oldpass" class="inputxt" datatype="*" /></td>
									<td><div class="Validform_checktip"></div></td>
								</tr>
								<tr>
									<td class="need" style="width: 10px;">*</td>
									<td>新密码：</td>
									<td><input type="password" value="" name="pass"
										class="inputxt" datatype="*" nullmsg="请设置密码！" errormsg="输入错误！" /></td>
									<td><div class="Validform_checktip"></div></td>
								</tr>
								<tr>
									<td class="need">*</td>
									<td>确认密码：</td>
									<td><input type="password" value="" name="pass2"
										tip="test" class="inputxt" datatype="*" recheck="pass"
										nullmsg="请再输入一次密码！" errormsg="两次输入不一致！" /></td>
									<td><div class="Validform_checktip"></div></td>
								</tr>
								<tr>
									<td class="need"></td>
									<td></td>
									<td colspan="2"><input type="submit" value="保存" /><span
										id="msgdemo" style="color: red; margin-left: 30px;"></span></td>
								</tr>
							</table>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script
		src="<%=request.getContextPath()%>/resources/js/jquery-1.6.2.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/resources/personal/jquery-ui.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/resources/reg/js/Validform_v5.3.js"></script>
	<script type="text/javascript"
		src="<%=request.getContextPath()%>/resources/reg/plugin/jqtransform/jquery.jqtransform.js"></script>
	<script
		src="<%=request.getContextPath()%>/resources/reg/plugin/datePicker/jquery.datePicker-min.js"></script>
	<script>
		/*表单美化js*/
		$(function() {
			$(".registerform").Validform(
					{
						ajaxPost : true,
						callback : function(data) {
							if (data.status == "y") {
								window.location.reload();
							} else {
								var objtip = $(".registerform")
										.find("#msgdemo");

								objtip.text(data.info);
							}
						},
						tiptype : function(msg, o, cssctl) {
							if (!o.obj.is("form")) {
								var objtip = o.obj.parents("td").next().find(
										".Validform_checktip");
								cssctl(objtip, o.type);
								objtip.text(msg);
							}
						},

						usePlugin : {
							jqtransform : {},
							datepicker : {
								format : "yyyy-mm-dd",//指定输出的时间格式;
								firstDayOfWeek : 1,//指定每周开始的日期，0、1-6 对应 周日、周一到周六;

								//以上三个参数是在Validform插件内调用Datepicker时可传入的参数;
								//下面几个参数是Datepicker插件本身初始化时可接收的参数，还有更多请查看页面说明;
								clickInput : true,
								startDate : "1970-00-00",
								createButton : false
							}
						}
					});

		})

		/*手风琴的js*/
		$('#accordion').accordion({
			active : 0,

		});
		$("#selectmenu").selectmenu();
		$("#selectmenu1").selectmenu();
		$("#selectmenu2").selectmenu();
		$("#selectmenu3").selectmenu();
		$("#selectmenu4").selectmenu();
		$('#accordion div').css('height', 'auto');

		$('#changpersonbtn').click(function(e) {
			$('#person').hide();
			$('#changeperson').show();
		});
		$('#canclepersonbtn').click(function(e) {
			$('#person').show();
			$('#changeperson').hide();
		});
		$('#changschoolbtn').click(function(e) {
			$('#school').hide();
			$('#changeschool').show();
		});
		$('#cancleschoolbtn').click(function(e) {
			$('#school').show();
			$('#changeschool').hide();
		});
		$('#changotherbtn').click(function(e) {

			$('#other').hide();
			$('#changeother').show();
		});
		$('#cancleotherbtn').click(function(e) {
			$('#other').show();
			$('#changeother').hide();
		});
		$('#one').click(function(e) {
			$('#accordion').accordion('option', 'active', 0);
			$('#one').addClass("current");
			$('#two').removeClass("current");
			$('#three').removeClass("current");
		});
		$('#two').click(function(e) {

			$('#accordion').accordion('option', 'active', 1);

			$('#two').addClass("current");
			$('#one').removeClass("current");
			$('#three').removeClass("current");
		});
		$('#three').click(function(e) {
			$('#accordion').accordion('option', 'active', 2);
			$('#three').addClass("current");
			$('#two').removeClass("current");
			$('#one').removeClass("current");
		});
	</script>
</body>
</html>