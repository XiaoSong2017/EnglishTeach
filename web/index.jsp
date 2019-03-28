<%@ taglib prefix="s" uri="/struts-tags" %>
<%--
  Created by IntelliJ IDEA.
  User: wangsong
  Date: 2019/2/15
  Time: 17:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>首页</title>
</head>
<body style="width: 100%;height: 100%;background-image:url('images/background.jpg');background-repeat: no-repeat;">
<%@include file="head.jsp"%>
<div class="container">
    <div class="panel panel-primary" align="center"
         style="margin-top: 10%;margin-left:30%;margin-right:30%;background-color: rgba(0,0,0,0)">
        <div class="panel-heading" align="center"><h3 class="panel-title h3" style="color: maroon">用户登录</h3></div>
        <div class="panel-body">
            <s:form action="login!login" theme="xhtml" method="POST" cssStyle="align-content: center">
                <s:actionmessage cssClass="alert alert-danger dismissible fade show"/>
                <s:textfield name="account" type="number" cssClass="form-control glyphicon glyphicon-user" label="账号"
                             placeholder="请输入学号或职工号！" required="true" requiredLabel="*" requiredPosition="right"/>
                <s:password  name="password" autocomplete="on" data-toggle="password" cssClass="form-control" label="密码" placeholder="请输入密码！" required="true"/>
                <s:radio name="type" cssClass="custom-radio" cssStyle="align-content: center" required="true"
                         list="#{0:'学生',1:'教师'}" label="用户类型"/>
                <s:submit cssClass="btn btn-block btn-success" cssStyle="margin-top: 20px" value="登录"/>
            </s:form>
        </div>
    </div>
</div>
<%@include file="footer.jsp"%>
</body>
</html>
