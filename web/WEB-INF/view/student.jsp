<%@ taglib prefix="s" uri="/struts-tags" %>
<%--
  Created by IntelliJ IDEA.
  User: wangsong
  Date: 2019/2/13
  Time: 11:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>大学英语过程化教学监控智能学生平台</title>
</head>
<body>
<%@include file="../../head.jsp"%>
<div class="container">
    <ul class="nav nav-pills nav-justified" id="pills-tab" role="tablist">
        <li class="nav-item">
            <a class="nav-link active" id="pills-home-tab" data-toggle="pill" href="#pills-home" role="tab"
               aria-controls="pills-home" aria-selected="true">在线作业</a>
        </li>
        <li class="nav-item">
            <a class="nav-link" id="pills-profile-tab" data-toggle="pill" href="#pills-profile" role="tab"
               aria-controls="pills-profile" aria-selected="false">在线考试</a>
        </li>
        <li class="nav-item">
            <a class="nav-link" id="pills-contact-tab" data-toggle="pill" href="#pills-contact" role="tab"
               aria-controls="pills-contact" aria-selected="false">教学资源</a>
        </li>
    </ul>
    <div class="tab-content" id="pills-tabContent">
        <div class="tab-pane fade show active" id="pills-home" role="tabpanel" aria-labelledby="pills-home-tab">...
        </div>
        <div class="tab-pane fade" id="pills-profile" role="tabpanel" aria-labelledby="pills-profile-tab">...</div>
        <div class="tab-pane fade" id="pills-contact" role="tabpanel" aria-labelledby="pills-contact-tab"><jsp:include page="student_resource.jsp"/></div>
    </div>
</div>
<%@include file="../../footer.jsp"%>
<script>
    $('#pills-tab a').on('click', function (e) {
        e.preventDefault();
        $(this).tab('show');
    })
</script>
</body>
</html>
