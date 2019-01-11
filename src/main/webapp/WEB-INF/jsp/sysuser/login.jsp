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
<title>Insert title here</title>
</head>
<body>
	<form action="${ctx}/user/login" modelAttribute="user" method="post" class="form-horizontal">
	<div class="form-group">
    <label for="userName">UserName</label>
    <input type="username"  name="userName" path="userName" class="form-control"  id="username" aria-describedby="emailHelp" placeholder="Enter email">
    <small id="emailHelp" class="form-text text-muted">We'll never share your email with anyone else.</small>
  </div>
  <div class="form-group">
    <label for="password">Password</label>
    <input type="password" name="password"  class="form-control" id="exampleInputPassword1" placeholder="Password">
  </div>
  <button type="submit" class="btn btn-primary">Submit</button>
</form>
</body>
</html>