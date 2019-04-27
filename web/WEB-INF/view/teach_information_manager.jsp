<%@ taglib prefix="sx" uri="/struts-tags" %>
<%--
  Created by IntelliJ IDEA.
  User: wangsong
  Date: 2019/2/28
  Time: 11:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>教学信息管理</title>
</head>
<body>
<div class="row">
    <div class="col-2">
        <div class="nav flex-column nav-pills" id="v-pills-tab" role="tablist"
             aria-orientation="vertical">
            <a class="nav-link active" id="v-pills-home-tab" data-toggle="pill" href="#v-pills-home" role="tab"
               aria-controls="v-pills-home" aria-selected="true">学生管理</a>
            <a class="nav-link" id="v-pills-profile-tab" data-toggle="pill" href="#v-pills-profile" role="tab"
               aria-controls="v-pills-profile" aria-selected="false">课程管理</a>
            <a class="nav-link" id="v-pills-messages-tab" data-toggle="pill" href="#v-pills-messages" role="tab"
               aria-controls="v-pills-messages" aria-selected="false">学生选课管理</a>
            <a class="nav-link" id="v-pills-settings-tab" data-toggle="pill" href="#v-pills-settings" role="tab"
               aria-controls="v-pills-settings" aria-selected="false">教师授课管理</a>
            <a class="nav-link" id="v-pills-homework-tab" data-toggle="pill" href="#v-pills-homework" role="tab"
               aria-controls="v-pills-homework" aria-selected="false">作业管理</a>
            <a class="nav-link" id="v-pills-exam-tab" data-toggle="pill" href="#v-pills-exam" role="tab"
               aria-controls="v-pills-exam" aria-selected="false">考试管理</a>
            <a class="nav-link" id="v-pills-answerRecord-tab" data-toggle="pill" href="#v-pills-answerRecord" role="tab"
               aria-controls="v-pills-answerRecord" aria-selected="false">
                主观题作答管理</a>
        </div>
    </div>
    <div class="col-10">
        <div class="tab-content" id="v-pills-tabContent">
            <div class="tab-pane fade show active" id="v-pills-home" role="tabpanel"
                 aria-labelledby="v-pills-home-tab">
                <jsp:include page="stuent_manager.jsp" flush="true"/>
            </div>
            <div class="tab-pane fade" id="v-pills-profile" role="tabpanel"
                 aria-labelledby="v-pills-profile-tab">
                <jsp:include page="course_manager.jsp" flush="true"/>
            </div>
            <div class="tab-pane fade" id="v-pills-messages" role="tabpanel"
                 aria-labelledby="v-pills-messages-tab">
                <jsp:include page="elective_course_manager.jsp" flush="true"/>
            </div>
            <div class="tab-pane fade" id="v-pills-settings" role="tabpanel"
                 aria-labelledby="v-pills-settings-tab">
                <jsp:include page="teaching_manager.jsp" flush="true"/>
            </div>
            <div class="tab-pane fade" id="v-pills-homework" role="tabpanel"
                 aria-labelledby="v-pills-homework-tab">
                <jsp:include page="homework_manager.jsp" flush="true"/>
            </div>
            <div class="tab-pane fade" id="v-pills-exam" role="tabpanel"
                 aria-labelledby="v-pills-exam-tab">
                <jsp:include page="exam_manager.jsp" flush="true"/>
            </div>
            <div class="tab-pane fade" id="v-pills-answerRecord" role="tabpanel"
                 aria-labelledby="v-pills-answerRecord-tab">
                <jsp:include page="answer_record.jsp" flush="true"/>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript">
    $('#v-pills-tab a').on('click', function (e) {
        e.preventDefault();
        $(this).tab('show');
    });
</script>
</body>
</html>
