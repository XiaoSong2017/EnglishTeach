<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page import="org.springframework.context.ApplicationContext" %>
<%@ page import="org.springframework.web.context.support.WebApplicationContextUtils" %>
<%@ page import="po.CoursePo" %>
<%@ page import="service.CourseService" %>
<%--
  Created by IntelliJ IDEA.
  User: wangsong
  Date: 2019/3/1
  Time: 11:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>选择课程</title>
</head>
<body>
<%@include file="../../head.jsp" %>
<div class="container">
    <div class="list-group">
        <div class="list-group-item-info">
            <label>选择对应课程：</label>
        </div>
        <%
            ApplicationContext applicationContext = WebApplicationContextUtils.getWebApplicationContext(application);
            CourseService courseService = (CourseService) applicationContext.getAutowireCapableBeanFactory().getBean("courseService");
            for (CoursePo coursePo : courseService.getCoursesByStudentId(String.valueOf(request.getSession().getAttribute("ID")))) {
        %>
            <s:a theme="simple" cssClass="list-group-item list-group-item-action" href="student_login" target="_parent"><%=coursePo.getName()%></s:a>
        <%}%>
    </div>
</div>
<%@include file="../../footer.jsp" %>
</body>
</html>
