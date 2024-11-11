<%@page import="cn.util.Const"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div class="header">
			<div class="header-wrap">
				<h1 class="logo pull-left">
					<a href="<%=Const.ROOT %>index" style="color:#EEE;font-size:35px;">
						火车票订票系统网站
					</a>
				</h1>
				<form class="layui-form blog-seach pull-left" action="<%=Const.ROOT %>index" method="post" id="searchForm">
					<input type="hidden" name="pageNo" id="pageNo"/>
					<!-- <div class="layui-form-item blog-sewrap">
					    <div class="layui-input-block blog-sebox">
					      <i class="layui-icon layui-icon-search" onclick="goPage(1)"></i>
					      <input type="text" id="keyword" style="color:#EEE" name="keyword" placeholder="" lay-verify="title" autocomplete="off"  class="layui-input">
					    </div>
					</div> -->
				</form>
				<div class="blog-nav pull-right">
					<ul class="layui-nav pull-left">
					  <li class="layui-nav-item"><a href="<%=Const.ROOT %>index">首页</a></li>
					  <li class="layui-nav-item"><a href="<%=Const.ROOT %>message/index">在线留言</a></li>
					  <c:choose>
					  <c:when test="${not empty sessionScope.users and sessionScope.role==1 }">
					  	<li class="layui-nav-item"><a href="<%=Const.ROOT %>orders/index?id=${sessionScope.users.id}">我的订单</a></li>
					  	<li class="layui-nav-item"><a href="<%=Const.ROOT %>user/toupdate?id=${sessionScope.users.id}">个人中心</a></li>
					  	<li class="layui-nav-item"><a href="<%=Const.ROOT %>logout">退出</a></li>
					  </c:when>
					  <c:otherwise>
					  	<li class="layui-nav-item"><a href="<%=Const.ROOT %>reg.jsp">注册</a></li>
					  	<li class="layui-nav-item"><a href="<%=Const.ROOT %>login.jsp">登录</a></li>
					  </c:otherwise>
					  </c:choose>
					</ul>
					 <c:if test="${not empty sessionScope.user and sessionScope.role==1}">
						<a href="#" class="personal pull-left" style="color:#DDD">
							<i class="layui-icon layui-icon-username"></i> ${sessionScope.users.name }
						</a>
					 </c:if>
				</div>
			</div>
		</div>