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
	<%--<form action="${ctx}/user/login" modelAttribute="user" method="post" class="form-horizontal">--%>
	<%--<div class="form-group">--%>
    <%--<label for="userName">UserName</label>--%>
    <%--<input type="username"  name="userName" path="userName" class="form-control"  id="username" aria-describedby="emailHelp" placeholder="Enter email">--%>
    <%--<small id="emailHelp" class="form-text text-muted">We'll never share your email with anyone else.</small>--%>
  <%--</div>--%>
  <%--<div class="form-group">--%>
    <%--<label for="password">Password</label>--%>
    <%--<input type="password" id="password"  class="form-control" id="exampleInputPassword1" placeholder="Password">--%>
  <%--</div>--%>
  <%--<button type="submit" class="btn btn-primary">Submit</button>--%>
    <div class="error ${param.error == true ? '' : 'hide'}">
        登陆失败<br>
        ${sessionScope['SPRING_SECURITY_LAST_EXCEPTION'].message}
    </div>
    <%--
        特别要注意的是form表单的action是提交登陆信息的地址，这是security内部定义好的，
        同时自定义form时，要把form的action设置为/j_spring_security_check。
        注意这里要使用绝对路径，避免登陆页面存放的页面可能带来的问题。
    --%>
    <form method="post" action="${pageContext.request.contextPath}/j_spring_security_check"
          style="width:260px; text-align: center">
        <fieldset>
            <legend>新页面-登陆</legend>
            <%--j_username，输入登陆名的参数名称，j_password，输入密码的参数名称，这两个正常情况下也不会修改--%>
            用户： <input type="text" name="j_username" style="width: 150px;"
                       value="${sessionScope['SPRING_SECURITY_LAST_USERNAME']}" /><br />
            密码： <input type="password" name="j_password" style="width: 150px;" /><br />
            <%--
                _spring_security_remember_me，选择是否允许自动登录的参数名称。
                可以直接把这个参数设置为一个checkbox，无需设置value，Spring Security会自行判断它是否被选中，
                这也是security内部提供的，只需要配置，不需要自己实现。
            --%>

            <input type="checkbox" name="_spring_security_remember_me" />两周之内不必登陆<br />
            <input type="submit" value="登陆" />
            <input type="reset" value="重置" />
        </fieldset>
    </form>
</form>
</body>
</html>