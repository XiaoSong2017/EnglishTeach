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
    <%--$(()=>{--%>
        <%--//console.log('<%=request.getSession().getAttribute("courseById")%>');--%>
        <%--loadCourse();--%>
        <%--$('#selectStudentCourse').on('change',function () {--%>
            <%--console.log('select:'+$('#selectStudentCourse option:selected').text());--%>
            <%--sessionStorage.removeItem('courseById');--%>
            <%--sessionStorage.setItem('courseById',$('#selectStudentCourse option:selected').val());--%>
            <%--console.log('session:'+sessionStorage.getItem('courseById'));--%>
            <%--location.reload();--%>
        <%--})--%>
    <%--});--%>
    <%--function loadCourse(){--%>
        <%--$.ajax({--%>
            <%--url:'<%=request.getContextPath()%>/courseBean',--%>
            <%--type:'post',--%>
            <%--async:true,--%>
            <%--data:{'studentById':'<%=request.getSession().getAttribute("ID")%>'},--%>
            <%--success:(data)=>{--%>
                    <%--$('#selectStudentCourse').empty();--%>
                    <%--console.log(data);--%>
                    <%--for (var i = 0; i < data.data.length; ++i) {--%>
                        <%--$('#selectStudentCourse').append('<option value="' + data.data[i].id + '"'+(data.data[i].id==='<%=request.getSession().getAttribute("courseById")%>'?"selected":"\n")+'>' + data.data[i].name + '</option>');--%>
                    <%--}--%>
            <%--}--%>
        <%--});--%>
    <%--}--%>
    $('#pills-tab a').on('click', function (e) {
        e.preventDefault();
        $(this).tab('show');
    })
</script>
</body>
</html>
