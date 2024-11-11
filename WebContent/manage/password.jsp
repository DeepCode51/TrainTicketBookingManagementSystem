<%@page import="cn.util.Const"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>修改密码</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="<%=Const.ROOT %>lib/layui-v2.5.4/css/layui.css" media="all">
    <link rel="stylesheet" href="<%=Const.ROOT %>css/public.css" media="all">
</head>
<body>
<div class="layuimini-container">
    <div class="layuimini-main">
        <fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
            <legend>修改密码</legend>
        </fieldset>

        <form class="layui-form" action="" lay-filter="example">  
       		<input type="hidden" name="id" id="id" value="${sessionScope.users.id}"/>
       		<input type="hidden" name="oldPass" id="oldPass"/>
       		 <div class="layui-form-item">
                <label class="layui-form-label">原密码</label>
                <div class="layui-input-inline">
                    <input type="password" id="pass" name="pass" lay-verify="required|pass" autocomplete="off" placeholder="请输入密码" class="layui-input">
                </div>
                <div class="layui-form-mid layui-word-aux">请填写6到12位密码</div>    
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">新密码</label>
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
                <div class="layui-input-block">
                    <button class="layui-btn" lay-submit="" lay-filter="update">立即提交</button>
                    <button type="reset" class="layui-btn layui-btn-primary">重置</button>
                </div>
            </div>
        </form> 
</div>
</div>

<script src="<%=Const.ROOT %>lib/layui-v2.5.4/layui.js?v=1.0.4" charset="utf-8"></script>
<!-- 注意：如果你直接复制所有代码到本地，上述js路径需要改成你本地的 -->
<script>
    layui.use(['form', 'layedit', 'laydate'], function () {
    	var $ = layui.jquery;
        var form = layui.form
            , layer = layui.layer
            , layedit = layui.layedit
            , laydate = layui.laydate;

        //自定义验证规则
        form.verify({
        	pass:function(value) {
               	//获取密码
               	var pass = $("#oldPass").val();
             	if(pass!=value) {
             		return '原密码不正确';
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
        });

        //监听提交
        form.on('submit(update)', function (data) {
            $.post("<%=Const.ROOT%>uppwd",data.field,function(data){
            	if(data.code=="0"){
	            	layer.msg("修改成功，请重新登录", { icon: 6, time: 800},function(){
	            			<c:if test="${sessionScope.role==0}">
	            				parent.location.href="<%=Const.ROOT%>manage/login.jsp";
	            			</c:if>
	            			<c:if test="${sessionScope.role==1}">
            					parent.location.href="<%=Const.ROOT%>default.jsp";
            				</c:if>
					});
            	}else{
            		layer.msg("修改失败!", { icon: 5, time: 800},function(){});
            	}
            });
            return false;
        });
        
        $("#oldPass").val('${sessionScope.users.password}');
        //表单初始赋值
    });
</script>

</body>
</html>