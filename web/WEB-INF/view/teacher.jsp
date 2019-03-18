<%@ taglib prefix="s" uri="/struts-tags" %>
<%--
  Created by IntelliJ IDEA.
  User: wangsong
  Date: 2019/2/17
  Time: 18:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>大学英语过程化教学监控智能教师平台</title>
</head>
<body>
<%@include file="../../head.jsp"%>
<div class="container">
    <ul class="nav nav-pills nav-justified" id="pills-tab" role="tablist">
        <li class="nav-item">
            <a class="nav-link active" id="pills-home-tab" data-toggle="pill" href="#pills-home" role="tab"
               aria-controls="pills-home" aria-selected="true">教学信息管理</a>
        </li>
        <li class="nav-item">
            <a class="nav-link" id="pills-profile-tab" data-toggle="pill" href="#pills-profile" role="tab"
               aria-controls="pills-profile" aria-selected="false">教学资源管理</a>
        </li>
        <li class="nav-item">
            <a class="nav-link" id="pills-contact-tab" data-toggle="pill" href="#pills-contact" role="tab"
               aria-controls="pills-contact" aria-selected="false">教学智能化分析</a>
        </li>
    </ul>
    <div class="tab-content" id="pills-tabContent">
        <div class="tab-pane fade show active" id="pills-home" role="tabpanel" aria-labelledby="pills-home-tab">
            <jsp:include page="teach_information_manager.jsp" flush="true"/>
        </div>
        <div class="tab-pane fade" id="pills-profile" role="tabpanel" aria-labelledby="pills-profile-tab">
            <jsp:include page="teacher_resource.jsp" flush="true"/>
        </div>
        <div class="tab-pane fade" id="pills-contact" role="tabpanel" aria-labelledby="pills-contact-tab">...</div>
    </div>
</div>
<%@include file="../../footer.jsp"%>
<script type="text/javascript">
    $('#pills-tab a').on('click', function (e) {
        e.preventDefault();
        $(this).tab('show');
    });
</script>
</body>
</html>
