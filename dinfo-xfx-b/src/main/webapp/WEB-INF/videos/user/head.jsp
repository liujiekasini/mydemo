	<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
	<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/js/layui/css/layui.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/uploadify.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/jquery.autocomplete.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/index.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/page.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/layer.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/H-ui.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/H-ui.ie.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/H-ui.min.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/H-ui.reset.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/js/jquery-easyui1.4.1/themes/default/easyui.css">   
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/js/jquery-easyui1.4.1/themes/icon.css">  
	<!-- 头部-->
    <div class="l"><span style="margin-left:10px;: 18px;font-weight: bold;color: #0E2D5F;">boc-model</span></div>
    <div class="r">
    <shiro:guest>  
	您当前是游客，<a href="${pageContext.request.contextPath}/login" >登录</a>
	</shiro:guest> 
    <shiro:user>  
		<span class="r_nav">[ 欢迎你,<shiro:principal/>]</span>  
	</shiro:user>  
    <span class="pipe">|</span><span class="r_nav">[<shiro:hasRole name="manager">您当前权限等级：超级管理员</shiro:hasRole><shiro:hasRole name="admin">您当前权限等级：管理员</shiro:hasRole><shiro:hasRole name="normal">您当前权限等级：普通用户</shiro:hasRole>]</span><span class="pipe">|</span><span class="r_nav">[<a  href="${pageContext.request.contextPath}/pinglun" >首页</a>]</span><span class="pipe">|</span><span class="r_nav">[<a href="${pageContext.request.contextPath}/jiankong"  >登录监控</a>]</span><span class="pipe">|</span><span class="r_nav">[<a href="${pageContext.request.contextPath}/logout"  >退出</a>]</span></div>
 
