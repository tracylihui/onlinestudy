<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>注册_在线学习系统</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/reg/css/style.css" type="text/css" media="all" />
<link href="<%=request.getContextPath()%>/resources/personal/jquery-ui.min.css" rel="stylesheet">
<link href="<%=request.getContextPath()%>/resources/reg/css/demo.css" type="text/css" rel="stylesheet" />
<link href="<%=request.getContextPath()%>/resources/reg/plugin/jqtransform/jqtransform.css" type="text/css" rel="stylesheet" />
<link href="<%=request.getContextPath()%>/resources/reg/plugin/datePicker/datePicker.css" type="text/css" rel="stylesheet" />
<style>
.info{
	margin-left:8px; 
	border:1px solid #ccc; 
	padding:8px 20px 8px 10px; 
	color:#666; 
	float:left;
	position:relative;
	display:none;
	line-height:13px;
}
.dec {
    top: 9px;
    display: block;
    height: 10px;
    overflow: hidden;
    position: absolute;
    left: -8px;
    width: 8px;
}
.dec s {
    font-family: simsun;
    font-size: 10px;
    height: 12px;
    left: 0;
    line-height: 14px;
    position: absolute;
    text-decoration: none;
    top: 0;
    width: 17px;
}
.dec .dec1 {
    color: #ccc;
}
.dec .dec2 {
    color: #fff;
    left: 1px;
}
</style>
</head>

<body>
<div class="header">
  <div class="wraper">
    <h1><a href="<%=request.getContextPath()%>/index">在线学习系统</a></h1>
    <ul class="nav">
      <li><a href="<%=request.getContextPath()%>/inde_login" class="current">登录</a></li>
    </ul>
  </div>
</div>
<div class="main padd">
  <div class="wraper">
    <h2 class="green">个人信息</h2>
    <form class="registerform" method="post" action="<%=request.getContextPath()%>/insert">
      <table width="600px" style="margin:0 auto;">
        <tr>
          <td class="need" style="width:10px;">*</td>
          <td style="width:100px;">用户名：</td>
          <td style="width:205px;"><input type="text" ajaxurl="<%=request.getContextPath()%>/checkusername" name="username" class="inputxt" datatype="*"  nullmsg="请填写用户名！"  /></td>
          <td><div class="Validform_checktip"></div></td>
        </tr>
        <tr>
          <td class="need" style="width:10px;">*</td>
          <td>密码：</td>
          <td><input type="password" value="" name="password" class="inputxt" datatype="*" nullmsg="请设置密码！" errormsg="输入错误！" /></td>
          <td><div class="Validform_checktip"></div></td>
        </tr>
        <tr>
          <td class="need">*</td>
          <td>确认密码：</td>
          <td><input type="password" value="" name="repassword" tip="test" class="inputxt" datatype="*" recheck="password" nullmsg="请再输入一次密码！" errormsg="两次输入不一致！" /></td>
          <td><div class="Validform_checktip"></div></td>
        </tr>
        <tr>
          <td class="need">*</td>
          <td>姓名：</td>
          <td><input type="text" value="" name="nickname" class="inputxt" datatype="*" nullmsg="请填写姓名！"  /></td>
          <td><div class="Validform_checktip"></div></td>
        </tr>
         <tr>
          <td class="need">*</td>
          <td  style="width:100px;">联系电话：</td>
          <td  style="width:205px;"><input type="text" value="" name="phone" class="inputxt" datatype="m" errormsg="请输入您的手机号码！"  /></td>
          <td><div class="Validform_checktip"></div></td>
        </tr>
         <tr>
          <td class="need">*</td>
          <td>邮箱：</td>
          <td><input type="text" value="" name="email" class="inputxt" datatype="e"/></td>
          <td><div class="Validform_checktip"></div></td>
        </tr>
         <tr>
          <td class="need">*</td>
          <td>QQ：</td>
          <td><input type="text" value="" name="qq" class="inputxt" datatype="n"/></td>
          <td><div class="Validform_checktip"></div></td>
        </tr>
        <tr>
          <td class="need"></td>
          <td></td>
          <td colspan="2"><input type="submit" value="提 交" />
            <input type="reset" value="重 置" /><span id="msgdemo" style="color:red;margin-left:30px;"></span></td>
        </tr>
      </table>
    </form>
  </div>
</div>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/jquery-1.6.2.min.js"></script> 

<script type="text/javascript" src="<%=request.getContextPath()%>/resources/reg/js/Validform_v5.3.js"></script> 
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/reg/plugin/jqtransform/jquery.jqtransform-min.js"></script> 

<script type="text/javascript">
$(function(){
	//$(".registerform").Validform();  //就这一行代码！;
	var getInfoObj=function(){
			return 	$(this).parents("td").next().find(".info");
		}
	
	$("[datatype]").focusin(function(){
		if(this.timeout){clearTimeout(this.timeout);}
		var infoObj=getInfoObj.call(this);
		if(infoObj.siblings(".Validform_right").length!=0){
			return;	
		}
		infoObj.show().siblings().hide();
		
	}).focusout(function(){
		var infoObj=getInfoObj.call(this);
		this.timeout=setTimeout(function(){
			infoObj.hide().siblings(".Validform_wrong,.Validform_loading").show();
		},0);
		
	});	
	$(".registerform").Validform({
		ajaxPost:true,
		callback:function(data){
			if(data.status=="y"){
				location.href = "<%=request.getContextPath()%>/index";
			}else{
				var objtip=$(".registerform").find("#msgdemo");
				
				objtip.text(data.info);
			}
		},
		tiptype:function(msg,o,cssctl){
			if(!o.obj.is("form")){
				var objtip=o.obj.parents("td").next().find(".Validform_checktip");
				cssctl(objtip,o.type);
				objtip.text(msg);
			}

		},
		usePlugin:{
			jqtransform:{},
			datepicker:{format:"yyyy-mm-dd",//指定输出的时间格式;
				firstDayOfWeek:0,//指定每周开始的日期，0、1-6 对应 周日、周一到周六;
				
				//以上三个参数是在Validform插件内调用Datepicker时可传入的参数;
				//下面几个参数是Datepicker插件本身初始化时可接收的参数，还有更多请查看页面说明;
				clickInput:true,
				startDate:"1970-00-00",
				createButton:false}
			
		}
	});
	
	
	
})
</script>


</body>
</html>