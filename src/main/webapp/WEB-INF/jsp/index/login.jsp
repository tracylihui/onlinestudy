<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>登录</title>
<meta http-equiv="keywords" content="昭通师专,昭通学院,昭通学院附中,昭通师专附中">
<meta http-equiv="description" content="昭通学院附中网站,昭通师专附中网站">
<!-- Date: 2013-09-04 -->
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/resources/css/index/login.css" />
<script type="text/javascript"
	src="<%=request.getContextPath()%>/resources/js/jquery-1.7.2.min.js"></script>
<script>
	$(function() {
		$("#login").addClass("current");

		//input 按键事件
		$("input[name]").keyup(function(e) {
			//禁止输入空格  把空格替换掉
			if ($(this).attr('name') == "password" && e.keyCode == 32) {
				$(this).val(function(i, v) {
					return $.trim(v);
				});
			}
			if ($.trim($(this).val()) != "") {
				$(this).nextAll('span').eq(0).css({
					display : 'none'
				});
			}
		});

		//错误信息
		var succ_arr = [];

		//input失去焦点事件
		$("input[name]").focusout(function(e) {

			var msg = "";
			if ($.trim($(this).val()) == "") {
				if ($(this).attr('name') == 'username') {
					succ_arr[0] = false;
					msg = "登入名为空";
				} else if ($(this).attr('name') == 'password') {
					succ_arr[1] = false;
					msg = "密码为空";
				}

			} else {
				if ($(this).attr('name') == 'username') {
					succ_arr[0] = true;

				} else if ($(this).attr('name') == 'password') {
					succ_arr[1] = true;

				}
			}
			var a = $(this).nextAll('span').eq(0);
			a.css({
				display : 'block'
			}).text(msg);
		});
		//Ajax用户注册
		$("button[type='button']").click(function() {
			$("input[name]").focusout(); //让所有的input标记失去一次焦点 来设置msg信息
			for (x in succ_arr) {
				if (succ_arr[x] == false)
					return;
			}
			$.ajax({
				url : "dologin",
				data : $("#login").serialize(),
				type : "post",
				dataType : "json",
				async : true,
				success : function(data) {
					if (data.status == "n") {
						$("#error_msg").html(data.info);
					} else {
						parent.document.location.href = "index";
					}
				}
			});
		});
	});
</script>
</head>
<body>
<body>

	<div id="home">

		<form id="login" class="current1" method="post">
			<h3>学生登入</h3>
			<img class="avator" src="<%=request.getContextPath() %>/resources/css/index/image/avatar.png" width="96"
				height="96" /> <label>学号 <input type="text" name="username"
				style="width: 210px;" /> <span>邮箱为空</span>
			</label> <label>密码 <input type="password" name="password"
				style="width: 210px;" /> <span>密码为空</span>
			</label> <span id="error_msg" style="float: right; color: red;"></span>
			<button type="button">登入</button>
			<span style="float: left;">没有账号？<a href="register">立即注册</a></span><span
				style="float: right; font-size: 10px; line-height: 22px">推荐使用谷歌、火狐浏览器</span>
		</form>

		<div id="myinfo">
			在线学习系统
		</div>

	</div>
</body>
</body>

</html>