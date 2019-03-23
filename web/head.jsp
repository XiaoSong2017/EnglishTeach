<%--
  Created by IntelliJ IDEA.
  User: wangsong
  Date: 2019/2/19
  Time: 12:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <!-- 上述meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/css/bootstrap.min.css">
    <link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/css/zTreeStyle/zTreeStyle.css">
    <link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/ueditor/themes/default/css/umeditor.css">
    <link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/css/jquery.jnotify.css">
    <link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/css/sweetalert2.min.css">
    <title>头部</title>
</head>
<body>
<div class="container">
    <nav class="navbar navbar-light bg-light justify-content-between">
        <div class="container-fluid">
            <div class="navbar-header">
                <a class="navbar-brand" href="#">
                    <img alt="Brand" src="<%=request.getContextPath()%>/images/icon.svg"
                         class="d-inline-block align-top"
                         type="svg" width="10%" height="10%"/>
                    <span class="navbar-brand mb-0 h1" style="color: #2732ff">大学英语过程化教学监控智能平台</span>
                </a>
            </div>
            <%
                request.setCharacterEncoding("UTF-8");
                if (request.getSession().getAttribute("user") != null) {
            %>
            <div class="nav-item">
                <span class="navbar-text">欢迎您登录！用户<%=request.getSession().getAttribute("user")%><a class="badge-light"
                                                                                                   href="logout!logout">注销</a></span>
            </div>
            <%}%>
        </div>
    </nav>
</div>
<script src="<%=request.getContextPath()%>/js/jquery-3.3.1.min.js"></script>
<script src="<%=request.getContextPath()%>/js/jq-paginator.min.js"></script>
<script src="<%=request.getContextPath()%>/js/json3.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"
        integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
        crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"
        integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
        crossorigin="anonymous"></script>
<script src="<%=request.getContextPath()%>/js/jquery.ztree.core.js" type="text/javascript"></script>
<script src="<%=request.getContextPath()%>/js/jquery.ztree.excheck.js" type="text/javascript"></script>
<script src="<%=request.getContextPath()%>/js/jquery.ztree.exedit.js" type="text/javascript"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/ueditor/umeditor.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/ueditor/umeditor.config.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/ueditor/lang/zh-cn/zh-cn.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.jnotify.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/sweetalert2.all.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/promise-polyfill.js"></script>
</body>
</html>
