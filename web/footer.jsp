<%--
  Created by IntelliJ IDEA.
  User: wangsong
  Date: 2019/2/21
  Time: 22:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>尾部</title>
</head>
<body>
<div class="container fixed-bottom">
    <%
        long counter = application.getAttribute("counter") == null ? 0 : (Long) application.getAttribute("counter");
    %>
    <div class="align-content-center" align="center">
        总计该网站被访问<label class="custom-control-label" style="color: #d51fe0"><%=counter%></label>次
    </div>
    <%
        if (session.isNew()) application.setAttribute("counter", ++counter);
    %>
    <div class="align-content-center" align="center">©版权所有©2019 王松. All rights reserved</div>
</div>
<script type="text/javascript">
    <%
    String userType=String.valueOf(request.getSession().getAttribute("userType"));
    if(userType!=null&&userType.equals("student")){
    %>
    setInterval(function () {
        $.ajax({
            url: '<%=request.getContextPath()%>/count',
            async: true,
            type: 'post',
            data: {"id":<%=request.getSession().getAttribute("LogId")%>}
        });
    }, 60000);
    <%}%>
</script>
</body>
</html>
