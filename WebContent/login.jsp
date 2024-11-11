<%@page import="cn.util.Const"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="zh">
<head>
	<meta charset="UTF-8">
	<title>火车票订票系统</title>
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet" href="<%=Const.ROOT %>res/layui/css/layui.css">
	<link rel="stylesheet" href="<%=Const.ROOT %>res/static/css/mian.css">
</head>
<body class="lay-blog">
		<%@include file="top.jsp" %>
		<div class="container-wrap">
			<div class="container container-message container-details container-comment">
					<div class="contar-wrap">
						<div class="item">
							<div class="item-box  layer-photos-demo1 layer-photos-demo">
								<h3>用户登录</h3>
							</div>
						</div>	
						<form class="layui-form" action="">
							<input type="hidden" name="role" value="1"/>
							<div class="layui-form-item layui-form-text">
								<input type="text" name="username" lay-verify="required" placeholder="用户名" autocomplete="off" class="layui-input" value="">
							</div>
							<div class="layui-form-item layui-form-text">
								<input type="password" name="password" lay-verify="required" placeholder="密码" autocomplete="off" class="layui-input" value="">
							</div>
							<div class="ayui-row layui-form-item">
								<button class="layui-btn layui-btn-fluid" lay-submit lay-filter="login">登 入</button>
							</div>
						</form>
					</div>
			</div>
		</div>
		<%@include file="footer.jsp" %>	
	<script src="<%=Const.ROOT %>res/layui/layui.js"></script>
	<script src="<%=Const.ROOT %>js/jquery-3.2.1.js"></script>
	<script>
	layui.use(['form'], function () {
        var form = layui.form,
        layer = layui.layer;
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
        			location.href="<%=Const.ROOT%>index";
        		}else if(result=="error"){
        			layer.alert("用户名或者密码错误!",{
                        title: "提示信息"
                    });
        		}
            });
            return false;//阻止提交,通过ajax提交
        });
	});
		layui.config({
		  base: '<%=Const.ROOT %>res/static/js/' 
		}).use('blog');
	</script>
</body>
</html>