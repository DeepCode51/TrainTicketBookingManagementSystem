<%@page import="cn.util.Const"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>火车票订票系统网站系统登录</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta http-equiv="Access-Control-Allow-Origin" content="*">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="format-detection" content="telephone=no">
    <link rel="stylesheet" href="<%=Const.ROOT %>lib/layui-v2.5.4/css/layui.css" media="all">
    <style>
        html, body {width: 100%;height: 100%;overflow: hidden}
        body {background: #009688;}
        body:after {content:'';background-repeat:no-repeat;background-size:cover;-webkit-filter:blur(3px);-moz-filter:blur(3px);-o-filter:blur(3px);-ms-filter:blur(3px);filter:blur(3px);position:absolute;top:0;left:0;right:0;bottom:0;z-index:-1;}
        .layui-container {width: 100%;height: 100%;overflow: hidden}
        .admin-login-background {width:360px;height:300px;position:absolute;left:50%;top:40%;margin-left:-180px;margin-top:-100px;}
        .logo-title {text-align:center;letter-spacing:2px;padding:14px 0;}
        .logo-title h1 {color:#009688;font-size:25px;font-weight:bold;}
        .login-form {background-color:#fff;border:1px solid #fff;border-radius:3px;padding:14px 20px;box-shadow:0 0 8px #eeeeee;}
        .login-form .layui-form-item {position:relative;}
        .login-form .layui-form-item label {position:absolute;left:1px;top:1px;width:38px;line-height:36px;text-align:center;color:#d2d2d2;}
        .login-form .layui-form-item input {padding-left:36px;}
        .captcha {width:60%;display:inline-block;}
        .captcha-img {display:inline-block;width:34%;float:right;}
        .captcha-img img {height:34px;border:1px solid #e6e6e6;height:36px;width:100%;}
    </style>
</head>
<body>
<div class="layui-container">
    <div class="admin-login-background">
        <div class="layui-form login-form">
            <form class="layui-form">
                <div class="layui-form-item logo-title">
                    <h1>火车票订票系统管理系统</h1>
                </div>
                <div class="layui-form-item">
                    <label class="layui-icon layui-icon-username" for="uno"></label>
                    <input type="hidden" name="role" value="0">
                    <input type="text" name="username" lay-verify="required|account" placeholder="用户名" autocomplete="off" class="layui-input" value="">
                </div>
                <div class="layui-form-item">
                    <label class="layui-icon layui-icon-password" for="password"></label>
                    <input type="password" name="password" lay-verify="required|password" placeholder="密码" autocomplete="off" class="layui-input" value="">
                </div>
                <div class="ayui-row layui-form-item">
                	<div class="layui-col-md12">
                    <button class="layui-btn layui-btn-fluid" lay-submit lay-filter="login">登 入</button>
                    </div>
                </div>
                <div class="ayui-row layui-form-item">
                	<div class="layui-col-md7 layui-col-md-offset5">
                		<a href="<%=Const.ROOT%>default.jsp">前台首页</a>
                    </div>  
                </div>
            </form>
        </div>
    </div>
</div>
<script src="<%=Const.ROOT %>lib/jquery-3.4.1/jquery-3.4.1.min.js" charset="utf-8"></script>
<script src="<%=Const.ROOT %>lib/layui-v2.5.4/layui.js" charset="utf-8"></script>
<script src="<%=Const.ROOT %>lib/jq-module/jquery.particleground.min.js" charset="utf-8"></script>
<script>
    layui.use(['form'], function () {
        var form = layui.form,
            layer = layui.layer;

        // 登录过期的时候，跳出ifram框架
        if (top.location != self.location) top.location = self.location;

        // 粒子线条背景
        $(document).ready(function(){
            $('.layui-container').particleground({
                dotColor:'#5cbdaa',
                lineColor:'#5cbdaa'
            });
            $("#reg").on("click", function () {
            	var addIndex=layer.open({
            		title:"注册",
            		area: ['700px', '520px'],
            		type: 2, 
            		content: '<%=Const.ROOT%>adduser.jsp'
            	});
            	console.log(addIndex);
            });
        });
     // 监听添加操作
        
        // 进行登录操作
        form.on('submit(login)', function (data) {
            data = data.field;
            if (data.sno == '') {
                layer.msg('用户名不能为空');
                return false;
            }
            if (data.password == '') {
                layer.msg('密码不能为空');
                return false;
            }
            //ajax提交表单
            $.post("<%=Const.ROOT%>login",data,function(result){
            	if(result=="ok"){
        			location.href="<%=Const.ROOT%>manage/index.jsp";
        		}else{
        			layer.alert("用户名或者密码错误!",{
                        title: "提示信息"
                    });
        		}
            });
            return false;//阻止提交,通过ajax提交
        });
    });
</script>
</body>
</html>