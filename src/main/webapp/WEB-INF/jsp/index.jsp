<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<body>
<h2>Hello,${user.username}</h2>
<a href="${ctx}/user/getRegister" >注册</a>
<a href="${ctx}/user/login" >登陆</a>
<%--<a href="${ctx}/j_spring_security_logout">退出登陆</a>--%>
<a href="${ctx}/admin">管理员页面</a>
</body>
</html>