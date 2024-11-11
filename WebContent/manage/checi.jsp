<%@page import="cn.util.Const"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>火车票订票系统</title>
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1">
<link rel="stylesheet"
	href="<%=Const.ROOT%>lib/layui-v2.5.4/css/layui.css" media="all">
<link rel="stylesheet" href="<%=Const.ROOT%>css/public.css" media="all">
</head>
<body>
	<div class="layuimini-container">
		<div class="layuimini-main">
			<fieldset class="layui-elem-field layuimini-search">
				<legend>搜索信息</legend>
				<div style="margin: 10px 10px 10px 10px">
					<form class="layui-form layui-form-pane" action="">
						<div class="layui-form-item">
							<div class="layui-inline">
								<label class="layui-form-label">车次</label>
								<div class="layui-input-inline">
									<input type="text" name="name" autocomplete="off"
										class="layui-input">
								</div>
							</div>
							<div class="layui-inline">
								<a class="layui-btn" lay-submit="" lay-filter="data-search-btn">搜索</a>
							</div>
							<c:if test="${sessionScope.role==0 }">
							<div class="layui-inline">
								<button type="button"
									class="layui-btn layui-btn-normal data-add-btn">
									<i class="layui-icon layui-icon-add-1"></i>添加
								</button>
							</div>
							</c:if>
						</div>
					</form>
				</div>
			</fieldset>
			<table class="layui-hide" id="currentTableId"
				lay-filter="currentTableFilter"></table>
			<script type="text/html" id="currentTableBar">
 				<a class="layui-btn layui-btn-xs layui-btn-normal data-count-edit" lay-event="view">途经点</a>
 				<a class="layui-btn layui-btn-xs data-count-edit" lay-event="edit">编辑</a>
            	<a class="layui-btn layui-btn-xs layui-btn-danger data-count-delete" lay-event="delete">删除</a>
        	</script>
		</div>
	</div>
	<div class="layuimini-container" id="popAdd" style="display: none;">
		<div class="layuimini-main">
			<form class="layui-form" action="" lay-filter="aexample">
				<div class="layui-form-item">
					<label class="layui-form-label">车次</label>
					<div class="layui-input-block">
						<input type="text" name="name"
							lay-verify="required" autocomplete="off"
							placeholder="请输入车次" class="layui-input">
					</div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label">类型</label>
					<div class="layui-input-block">
						<select name="type" lay-verify="required">
							<option value="高铁">高铁</option>
							<option value="动车">动车</option>
							<option value="特快">特快</option>
							<option value="普快">普快</option>
						</select>
					</div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label">始发站</label>
					<div class="layui-input-block">
						<input type="text" name="bsite"
							lay-verify="required" autocomplete="off"
							placeholder="请输入始发站" class="layui-input">
					</div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label">终点站</label>
					<div class="layui-input-block">
						<input type="text" name="esite"
							lay-verify="required" autocomplete="off"
							placeholder="请输入终点站" class="layui-input">
					</div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label">始发时间</label>
					<div class="layui-input-block">
						<input type="text" id="btime1" name="btime" lay-verify="required"
							autocomplete="off" placeholder="请输入始发时间" class="layui-input">
					</div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label">终到时间</label>
					<div class="layui-input-block">
						<input type="text" id="etime1" name="etime" lay-verify="required"
							autocomplete="off" placeholder="请输入终到时间" class="layui-input">
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
	<!--  表单的id用于表单的选择，style是在本页隐藏，只有点击编辑才会弹出 -->
	<div class="layuimini-container" id="popUpdate" style="display: none;">
		<div class="layuimini-main">
			<form class="layui-form" action="" lay-filter="uexample">
				<input type="hidden" name="id" /> 
				<div class="layui-form-item">
					<label class="layui-form-label">车次</label>
					<div class="layui-input-block">
						<input type="text" name="name"
							lay-verify="required" autocomplete="off"
							placeholder="请输入车次" class="layui-input">
					</div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label">类型</label>
					<div class="layui-input-block">
						<select name="type" lay-verify="required">
							<option value="高铁">高铁</option>
							<option value="动车">动车</option>
							<option value="特快">特快</option>
							<option value="普快">普快</option>
						</select>
					</div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label">始发站</label>
					<div class="layui-input-block">
						<input type="text" name="bsite"
							lay-verify="required" autocomplete="off"
							placeholder="请输入始发站" class="layui-input">
					</div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label">终点站</label>
					<div class="layui-input-block">
						<input type="text" name="esite"
							lay-verify="required" autocomplete="off"
							placeholder="请输入终点站" class="layui-input">
					</div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label">始发时间</label>
					<div class="layui-input-block">
						<input type="text" id="btime2" name="btime" lay-verify="required"
							autocomplete="off" placeholder="请输入始发时间" class="layui-input">
					</div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label">终到时间</label>
					<div class="layui-input-block">
						<input type="text" id="etime2" name="etime" lay-verify="required"
							autocomplete="off" placeholder="请输入终到时间" class="layui-input">
					</div>
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

	<script src="<%=Const.ROOT%>lib/layui-v2.5.4/layui.js" charset="utf-8"></script>
	<script>
	
	 layui.use(['form', 'table','laydate','upload'], function () {
	        var $ = layui.jquery,
	            form = layui.form,
	            table = layui.table,
	        	laydate = layui.laydate,
	       		upload = layui.upload;
	       		
	       		laydate.render({
	       			elem: '#btime1',
	       			type: 'time',
	       			format:'HH:mm'
	       		});
	       		laydate.render({
	       			elem: '#btime2',
	       			type: 'time',
	       			format:'HH:mm'
	       		});
	       		laydate.render({
	       			elem: '#etime1',
	       			type: 'time',
	       			format:'HH:mm'
	       		});
	       		laydate.render({
	       			elem: '#etime2',
	       			type: 'time',
	       			format:'HH:mm'
	       		});
	       		
	       		form.verify({
	            	etime1: function (value) {
	            		var time1=$("#btime1").val();
	                    if (time1!='' && value < time1) {
	                        return '结束时间不能小于开始时间';
	                    }
	                }
	                , etime2: function (value) {
	            		var time1=$("#btime2").val();
	                    if (time1!='' && value < time1) {
	                        return '结束时间不能小于开始时间';
	                    }
	                }
	            });
	       		
	    			
        table.render({
            elem: '#currentTableId',
            url: '<%=Const.ROOT%>checi/list',
            cols: [[
				{field:'id',hide:true,width: 0},
				{width: 80, templet:'#no',title: 'NO', sort: true},
                {field: 'name', width: 150, title: '车次'},
                {field: 'type', width: 150, title: '车型'},
                {field: 'bsite', width: 150, title: '始发站'},
                {field: 'esite', width: 150, title: '终点站'},
                {field: 'btime', width: 150, title: '始发时间'},
                {field: 'etime', width: 150, title: '终到时间'},
                {title: '操作', minWidth: 60, templet: '#currentTableBar', fixed: "right", align: "center"}
            ]],
            limits: [10, 15, 20, 25, 50, 100],
            limit: 10,
            page: true
        });

        // 监听搜索操作
        form.on('submit(data-search-btn)', function (data) {
            //var result = JSON.stringify(data.field);
            //console.log(data.field);
            //执行搜索重载
            table.reload('currentTableId', {
                page: {
                    curr: 1
                }
                , where:data.field
            }, 'data');

            return false;
        });

        // 监听添加操作
        $(".data-add-btn").on("click", function () {
        	var addIndex=layer.open({
        		title:"新增",
        		area: ['700px', '460px'],
        		type: 1, 
        		content: $("#popAdd")
        	});
        });

        table.on('tool(currentTableFilter)', function (obj) {
            var data = obj.data;
            if (obj.event === 'view') {
               location.href="<%=Const.ROOT%>manage/site.jsp?cid="+data.id;
            }else 
            	if (obj.event === 'edit') {
                //layer.alert('编辑行：<br>' + JSON.stringify(data))
                layer.open({
                        //layer提供了5种层类型。可传入的值有：0（信息框，默认）1（页面层）2（iframe层）3（加载层）4（tips层）
                        type: 1,
                        title: "修改",
                        area: ['700px', '460px'],
                        content: $("#popUpdate")//引用的弹出层的页面层的方式加载修改界面表单
               });
              //表单初始赋值
               form.val('uexample', data);
               //动态向表传递赋值当然也是异步请求的要数据的修改数据的获取
               setFormValue(obj,data);    
            } else if (obj.event === 'delete') {
                layer.confirm('真的删除行么', function (index) {
                    //console.log(obj.data);
                    $.getJSON('<%=Const.ROOT%>checi/delete',{'id':obj.data.id},function(msg) {
  						//console.log(msg);
  						if(msg.code=="0"){
  							layer.msg(msg.msg, { icon: 6, time: 800},function(){
  								layer.close(index);
  								obj.del();
  								location.reload();
  							});
  						}else{
  							layer.msg(msg.msg, {icon: 5});
  						}	
  					}); 
                });
            }
        });
        //监听提交事件，其中data.filed就是需要提交的表单数据
        form.on('submit(add)', function (data) {
        	var url="<%=Const.ROOT%>checi/add";
            $.post(url,data.field,function(data){
            	//parent.location.reload();//用layer弹出的iframe则这样刷新父页面
            	if(data.code==1){//5错误，6正常
            		layer.msg(data.msg,{"icon":5,time:2000});
            	}else{
            		layer.msg(data.msg,{"icon":6,time:2000},function(){
            			location.href="<%=Const.ROOT%>manage/checi.jsp";
            		});
            	}	
            });
            return false;//return false是阻止提交
        });
        //监听弹出框表单提交，massage是修改界面的表单数据
          function setFormValue(obj,data){
              form.on('submit(update)', function(message) {
            	  $.ajax({
                  url:'<%=Const.ROOT%>checi/update',
					type : 'POST',
					data : message.field,
					dataType:"JSON",
					success : function(msg) {
						layer.closeAll();
						if(msg.code=="0"){
							layer.msg(msg.msg, { icon: 6, time: 800},function(){
									location.href="<%=Const.ROOT%>manage/checi.jsp";
									//	table.reload('currentTableId');//数据表格重
									layer.closeAll();//关闭弹出层
								});
							} else {
								layer.msg(msg.msg, {
									icon : 5
								});
							}
						}
					});
					return false;
				})
			}
        
          //详情
          form.on('submit(view)', function(message) {
				layer.closeAll();//关闭弹出层
		   });

		});
	 
	 function getTime(){     	//获取时间
	    	var date=new Date();
	    	var year=date.getFullYear();
	    	var month=date.getMonth()+1;
	    	var day=date.getDate();
	        //这样写显示时间在1~9会挤占空间；所以要在1~9的数字前补零;
	        if (month<10) {
	        	month='0'+month;
	        }
	        if (day<10) {
	        	day='0'+day;
	        }
	        return year+'-'+month+'-'+day;
	    }
	</script>
	<script type="text/html" id="no">
    {{d.LAY_TABLE_INDEX+1}}
	</script>
	
</body>
</html>