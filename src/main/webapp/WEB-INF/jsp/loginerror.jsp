<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<c:set var="ctxStatic" value="${pageContext.request.contextPath}/static"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <script src="${ctxStatic}/jquery/jquery-3.3.1.js"></script>
    <script src="${ctxStatic}/bootstrap/bootstrap.js" type="text/javascript"></script>
    <link href="${ctxStatic}/bootstrap/bootstrap.css" type="text/css" rel="stylesheet">
    <title>jsp页面不能更新</title>
</head>
<body>
<a href="${ctx}/user/login">登陆失败，请重新登陆</a>
</body>
</html>