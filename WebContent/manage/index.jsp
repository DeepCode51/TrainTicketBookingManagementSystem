<%@page import="cn.util.Const"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>火车票订票系统登录</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta http-equiv="Access-Control-Allow-Origin" content="*">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <meta name="format-detection" content="telephone=no">
    <link rel="icon" href="<%=Const.ROOT %>images/favicon.ico">
    <link rel="stylesheet" href="<%=Const.ROOT %>lib/layui-v2.5.4/css/layui.css" media="all">
    <link rel="stylesheet" href="<%=Const.ROOT %>css/layuimini.css" media="all">
    <link rel="stylesheet" href="<%=Const.ROOT %>lib/font-awesome-4.7.0/css/font-awesome.min.css" media="all">
    <style id="layuimini-bg-color">
    </style>
</head>
<body class="layui-layout-body layuimini-all">
<div class="layui-layout layui-layout-admin">
    <div class="layui-header header">
        <div class="layui-logo">
        </div>
        <a>
            <div class="layuimini-tool"><i title="展开" class="fa fa-outdent" data-side-fold="1"></i></div>
        </a>
        <ul class="layui-nav layui-layout-right">
            <li class="layui-nav-item">
                <a href="javascript:;" data-refresh="刷新"><i class="fa fa-refresh"></i></a>
            </li>
            <li class="layui-nav-item layuimini-setting">
                <a href="javascript:;">${sessionScope.users.name}</a>
                <dl class="layui-nav-child">
                    <dd>
                        <a href="javascript:;" data-iframe-tab="<%=Const.ROOT %>manage/update_users.jsp" data-title="基本资料" data-icon="fa fa-gears">基本资料</a>
                    </dd>
                    <dd>
                        <a href="javascript:;" data-iframe-tab="<%=Const.ROOT %>manage/password.jsp" data-title="修改密码" data-icon="fa fa-gears">修改密码</a>
                    </dd>
                    <dd>
                    	<c:if test="${sessionScope.role==0 }">
                        <a href="<%=Const.ROOT %>user/logout" class="login-out">退出登录</a>
                        </c:if>
                        <c:if test="${sessionScope.role==1 }">
                        <a href="<%=Const.ROOT %>logout" class="login-out">退出登录</a>
                        </c:if>
                    </dd>
                </dl>
            </li>
            <li class="layui-nav-item layuimini-select-bgcolor mobile layui-hide-xs">
                <a href="javascript:;" data-bgcolor="配色方案"><i class="fa fa-ellipsis-v"></i></a>
            </li>
        </ul>
    </div>

    <div class="layui-side layui-bg-black">
        <div class="layui-side-scroll layui-left-menu">
        </div>
    </div>

    <div class="layui-body">
        <div class="layui-tab" lay-filter="layuiminiTab" id="top_tabs_box">
            <ul class="layui-tab-title" id="top_tabs">
                <li class="layui-this" id="layuiminiHomeTabId" lay-id=""></li>
            </ul>
            <ul class="layui-nav closeBox">
                <li class="layui-nav-item">
                    <a href="javascript:;"> <i class="fa fa-dot-circle-o"></i> 页面操作</a>
                    <dl class="layui-nav-child">
                        <dd><a href="javascript:;" data-page-close="other"><i class="fa fa-window-close"></i> 关闭其他</a></dd>
                        <dd><a href="javascript:;" data-page-close="all"><i class="fa fa-window-close-o"></i> 关闭全部</a></dd>
                    </dl>
                </li>
            </ul>
            <div class="layui-tab-content clildFrame">
                <div id="layuiminiHomeTabIframe" class="layui-tab-item layui-show">
                </div>
            </div>
        </div>
    </div>
</div>
</body>
<script src="<%=Const.ROOT %>lib/layui-v2.5.4/layui.js?v=1.0.4" charset="utf-8"></script>
<script src="<%=Const.ROOT %>js/lay-config.js?v=1.0.4" charset="utf-8"></script>
<script>
    layui.use(['element', 'layer', 'layuimini'], function () {
        var $ = layui.jquery,
            element = layui.element,
            layer = layui.layer;
	    	layuimini.init('<%=Const.ROOT %>api/menus${sessionScope.role}.json');
	        $('.login-out').on("click", function () {
	            layer.msg('退出登录成功', function () {
	                window.location = '<%=Const.ROOT %>logout';
	            });
	        });
    });
</script>
</html>
