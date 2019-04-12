<%--
  Created by IntelliJ IDEA.
  User: wangsong
  Date: 2019/4/8
  Time: 11:47
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
    <title>试题（作业）展示</title>
</head>
<body>
<div class="container-fluid">
    <h2 class="h2" id="examination_paper_name" align="center"></h2>
    <h6 align="right"><label class="label">距离考试结束还有：<strong style="color:red" id="time"></strong>秒</label></h6>
    <form class="form-group" action="<%=request.getContextPath()%>/saveAnswerRecord" method="post">
        <input name="studentById" type="hidden" value="${sessionScope.get("ID")}">
        <input name="examinationById" type="hidden" value="${param.get("id")}">
        <div id="parent_examination" class="container-fluid">
        </div>
        <div class="container-fluid justify-content-center" align="center">
            <label class="label">
                <input class="btn btn-outline-primary" type="submit" value="提交">
            </label>
        </div>
    </form>
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
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.jnotify.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/sweetalert2.all.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/polyfill.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/summernote-bs4.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/summernote-zh-CN.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/bootstrap-show-password.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/all.min.js"></script>
<script type="text/javascript">
    function onclickCollapse(obj) {
        $(obj).parent().next().collapse('toggle');
    }

    $(() => {
        $.ajax({
            url: '<%=request.getContextPath()%>/getExaminationPaperDetailById',
            type: 'post',
            async: true,
            data: {'id': '${param.get("id")}'},
            success: (data) => {
                if (!isEmpty(data.list)&&data.resultCode === 'success') {
                    //console.log(data);
                    $('#examination_paper_name').text(data.list.name);
                    var startTime=new Date(data.list.startTime);
                    var endTime=new Date(data.list.endTime);
                    $('#time').text(parseInt(endTime-startTime)/1000);
                    setInterval(()=>{
                        $('#time').text(parseInt($('#time').text())-1);
                    },1000);
                    var problem = data.list.problem;
                    problem.sort((a, b) => {
                        return parseInt(a.problemNumber) - parseInt(b.problemNumber);
                    });
                    //console.log(problem);
                    for (var i = 0; i < problem.length; ++i) {
                        if (problem[i].type === 1 || problem[i].type === 2 || problem[i].type === 3) {
                            var question = problem[i].question;
                            var temp = '';
                            question.sort((a, b) => {
                                return parseInt(a.questionNumber) - parseInt(b.questionNumber);
                            });
                            //console.log(question);
                            for (let j = 0; j < question.length; ++j) {
                                const option = question[j].option;
                                option.sort((a, b) => {
                                    return a.mark.localeCompare(b.mark);
                                });
                                temp += '<label class="label">' + question[j].questionNumber + '.' + (isEmpty(question[j].content) ? '' : question[j].content) + '</label>  ' +
                                    '<ul class="list-group">';
                                //console.log(option);
                                for (let k = 0; k < option.length; ++k) {
                                    temp += '<li class="list-group-item"><div class="radio"><label><input type="radio" name="' + question[j].id + '" value="' + option[k].mark + '" required>' + option[k].content + '</label></div></li>';
                                }
                                temp += '</ul>';
                            }
                            $('#parent_examination').append('<div class="card">\n' +
                                '        <div class="card-header">\n' +
                                '            <a class="card-link" data-toggle="collapse" href="#" onclick="onclickCollapse(this)">\n' +
                                '                第<label>' + (i + 1) + '</label>题：\n' +
                                '            </a>\n' +
                                '        </div>\n' +
                                '        <div class="collapse" data-parent="#parent_examination">\n' +
                                '            <div class="card-body">' + temp +
                                '</div>\n' +
                                '        </div>\n' +
                                '    </div>');
                        }
                    }
                } else {
                    Swal.fire({
                        text: '加载失败！,请刷新重试！',
                        type: 'error'
                    });
                }
            },
            error: () => {
                Swal.fire({
                    text: '网络出错！,请刷新重试！',
                    type: 'error'
                });
            }
        });
    });

    function isEmpty(obj) {
        return typeof obj === 'undefined' || obj == null || obj === '';
    }
</script>
</body>
</html>
