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
								<h3>用户注册</h3>
							</div>
						</div>	
						<form class="layui-form" action="" lay-filter="example">
				        	<div class="layui-form-item">
				                <label class="layui-form-label">用户名</label>
				                <div class="layui-input-block">
				                    <input type="text" name="username" lay-verify="required|username" autocomplete="off" placeholder="请输入用户名" class="layui-input">
				                </div>
				            </div>
				             <div class="layui-form-item">
				                <label class="layui-form-label">密码</label>
				                <div class="layui-input-inline">
				                    <input type="password" id="password" name="password" lay-verify="required|password" autocomplete="off" placeholder="请输入密码" class="layui-input">
				                </div>
				                <div class="layui-form-mid layui-word-aux">请填写6到12位密码</div>    
				            </div>
				             <div class="layui-form-item">
				                <label class="layui-form-label">确认密码</label>
				                <div class="layui-input-inline">
				                    <input type="password" name="repass" lay-verify="required|repass" autocomplete="off" placeholder="请输入确认密码" class="layui-input">
				                </div>
				                <div class="layui-form-mid layui-word-aux">请填写6到12位密码</div>
				            </div>
				            <div class="layui-form-item">
				                <label class="layui-form-label">姓名</label>
				                <div class="layui-input-block">
				                    <input type="text" name="name" lay-verify="required" lay-reqtext="必填项，不能为空" placeholder="请输入昵称" autocomplete="off" class="layui-input">
				                </div>
				            </div>
				            <div class="layui-form-item">
				                <label class="layui-form-label">电话</label>
				                <div class="layui-input-block">
				                    <input type="text" name="phone" lay-verify="required|phone" lay-reqtext="必填项，不能为空" autocomplete="off" placeholder="请输入" class="layui-input">
				                </div>
				            </div>
				            <div class="layui-form-item">
				                <label class="layui-form-label">身份证号</label>
				                <div class="layui-input-block">
				                    <input type="text" name="idcard" lay-verify="required|idcard" lay-reqtext="必填项，不能为空" autocomplete="off" placeholder="请输入" class="layui-input">
				                </div>
				            </div>
				            <div class="layui-form-item">
				                <div class="layui-input-block">
				                    <button class="layui-btn" lay-submit="" lay-filter="add">注册</button>
				                </div>
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
      //自定义验证规则,注意required|uUsername,其中required是自带的,uUsername是自定义的
        form.verify({
        	username: function (value) {
                if (value.length < 2) {
                    return '用户名长度必须大于等于2';
                }
            }
            , password: [
                /^[\S]{6,12}$/
                , '密码必须6到12位'
            ]
            ,repass: function(value) {
               	//获取密码
               	var pass = $("#password").val();
             	if(pass!=value) {
             		return '两次输入的密码不一致';
             	}
             }
           ,phone: [
                 /^[\d]{11}$/
                 , '电话必须11位'
             ]
           ,idcard: [
                     /^[\d]{17}[0-9Xx]$/
                     , '身份证号必须18位'
                 ]
        });
        
        //监听提交事件，其中data.filed就是需要提交的表单数据
        form.on('submit(add)', function (data) {
        	var url="<%=Const.ROOT%>reg";
            $.post(url,data.field,function(data){
            	//parent.location.reload();//用layer弹出的iframe则这样刷新父页面
            	if(data.code==1){//5错误，6正常
            		layer.msg(data.msg,{"icon":5,timeout:500});
            	}else{
            		layer.msg(data.msg,{"icon":6,timeout:500},function(){
            			location.href="<%=Const.ROOT%>login.jsp";
            		});
            	}	
            });
            return false;//return false是阻止提交
        });
	});
		layui.config({
		  base: '<%=Const.ROOT %>res/static/js/' 
		}).use('blog');
	</script>
</body>
</html>