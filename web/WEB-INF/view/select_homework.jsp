<%--
  Created by IntelliJ IDEA.
  User: wangsong
  Date: 2019/4/11
  Time: 16:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <!-- 上述meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/css/bootstrap.min.css">
    <link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/css/all.css">
    <link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/css/font-awesome.css">
    <link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/css/font-awesome-ie7.css">
    <link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/css/zTreeStyle/zTreeStyle.css">
    <link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/css/jquery.jnotify.css">
    <link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/css/sweetalert2.min.css">
    <link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/css/summernote-bs4.css">
    <title>作业列表</title>
</head>
<body>
<div class="d-flex justify-content-center align-items-stretch bg-light">
    <div class="p-2 border" style="height: 100%">
        <div id="homework_list" class="list-group list-group-flush">
            <a href="#" class="list-group-item list-group-item-heading">作业列表</a>
        </div>
    </div>
</div>
</body>
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
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.jnotify.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/sweetalert2.all.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/polyfill.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/summernote-bs4.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/summernote-zh-CN.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/bootstrap-show-password.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/all.min.js"></script>
<script type="text/javascript">
    $(() => {
        $.ajax({
            url: '<%=request.getContextPath()%>/getExaminationPaperByCourseId',
            type: 'post',
            async: true,
            data: {'courseId': '${sessionScope.get('courseById')}', 'type': true},
            success: (data) => {
                console.log(data);
                for (var i = 0; i < data.data.length; ++i) {
                    $('#homework_list').append('<a href="<%=request.getContextPath()%>/paper?id='+data.data[i].id+'" class="list-group-item list-group-item-action" target="homework">' + data.data[i].name + '</a>');
                }
            },
            error: () => {
                Swal.fire({
                    text: '加载出错！请重试！',
                    type: 'error'
                });
            }
        });
    });
</script>
</html>
