<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<body>
<h2>Hello 222SSM</h2>
<h3>${user.password}</h3>
<a href="${ctx}/user/getRegister" >注册</a>
<a href="${ctx}/user/login" title="${ctx}/user/login">登陆情况</a>
</body>
</html>