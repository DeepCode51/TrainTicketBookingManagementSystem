<%@page import="cn.util.Const"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>添加公告</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="<%=Const.ROOT %>lib/layui-v2.5.4/css/layui.css" media="all">
    <link rel="stylesheet" href="<%=Const.ROOT %>css/public.css" media="all">
</head>
<body>
<div class="layuimini-container">
    <div class="layuimini-main">
        <form class="layui-form" action="" lay-filter="example"> 
        	<div class="layui-form-item">
					<label class="layui-form-label">标题</label>
					<div class="layui-input-block">
						<input type="text" name="title"
							lay-verify="required" autocomplete="off"
							placeholder="请输入标题" class="layui-input">
					</div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label">内容</label>
					<div class="layui-input-block">
						<input id="content" name="content" value="" type="hidden">
					    <div id="editor">
						</div>
					</div>
				</div>    
            <div class="layui-form-item">
                <div class="layui-input-block">
                    <button class="layui-btn" lay-submit="" lay-filter="add">立即提交</button>
                    <button type="reset" class="layui-btn layui-btn-primary">重置</button>
                </div>
            </div>
        </form> 
</div>
</div>

<script src="<%=Const.ROOT %>lib/layui-v2.5.4/layui.js?v=1.0.4" charset="utf-8"></script>
<script type="text/javascript" src="<%=Const.ROOT %>wangEditor/wangEditor.min.js"></script>
<!-- 注意：如果你直接复制所有代码到本地，上述js路径需要改成你本地的 -->
<script>
	//加载需要的模块
    layui.use(['form','layer','laydate'], function () {
    	var $ = layui.jquery;
        var form = layui.form
            , layer = layui.layer
            , laydate = layui.laydate;
       
        //监听提交事件，其中data.filed就是需要提交的表单数据
        form.on('submit(add)', function (data) {
            $.post("<%=Const.ROOT%>gonggao/add",data.field,function(data){
            	parent.location.reload();//用layer弹出的iframe则这样刷新父页面
            });
            return false;//return false是阻止提交
        });

    });
	
    var E = window.wangEditor;
	var editor = new E('#editor');
	editor.customConfig.uploadFileName = 'file';
	editor.customConfig.uploadImgServer = '<%=Const.ROOT %>upfile';
	editor.customConfig.onchange = function (html) {
		document.getElementById("content").value=html;
    }
	editor.create();
	
</script>

</body>
</html>