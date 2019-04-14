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
    <form class="form-group" id="form_submit" onsubmit="return submitCheck()"
          action="<%=request.getContextPath()%>/saveAnswerRecord" method="post">
        <div id="parent_examination" class="container-fluid">
        </div>
        <div class="container justify-content-center">
            <button class="btn btn-outline-primary btn-block" ${param.getOrDefault("preview","")} type="submit">提交</button>
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

    function submitCheck() {
        var data = $('#form_submit').serializeArray();
        var questionById = [];
        //console.log(data);
        var content = [];
        var studentById =${sessionScope.get('ID')};
        var examinationById =${param.get("id")};
        // console.log(studentById);
        // console.log(examinationById);
        for (var i = 0; i < data.length; ++i) {
            questionById.push(data[i].name);
            content.push(data[i].value);
        }
        console.log(questionById);
        console.log(content);
        var temp = $.param({'questionById': questionById}, true);
        temp += '&' + $.param({'content': content}, true);
        temp += '&studentById=' + studentById + '&examinationById=' + examinationById;
        $.ajax({
            url: '<%=request.getContextPath()%>/saveAnswerRecord',
            type: 'post',
            async: true,
            dataType: 'json',
            data: temp,
            success: (data) => {
                if (data.resultCode === 'success') {
                    Swal.fire({
                        text: '已提交！',
                        type: 'success'
                    });
                } else {
                    Swal.fire({
                        text: '提交出错！请重试！',
                        type: 'error'
                    });
                }
            },
            error: () => {
                Swal.fire({
                    text: '网络出错！请重试！',
                    type: 'error'
                });
            }
        });
        return false;
    }

    $(() => {
        $.ajax({
            url: '<%=request.getContextPath()%>/getExaminationPaperDetailById',
            type: 'post',
            async: true,
            data: {'id': '${param.get("id")}'},
            success: (data) => {
                if (!isEmpty(data.list) && data.resultCode === 'success') {
                    //console.log(data);
                    $('#examination_paper_name').text(data.list.name);
                    var startTime = new Date(data.list.startTime);
                    var endTime = new Date(data.list.endTime);
                    $('#time').text(parseInt(endTime - startTime) / 1000);
                    setInterval(() => {
                        $('#time').text(parseInt($('#time').text()) - 1);
                    }, 1000);
                    var problem = data.list.problem;
                    problem.sort((a, b) => {
                        return parseInt(a.problemNumber) - parseInt(b.problemNumber);
                    });
                    //console.log(problem);
                    for (var i = 0; i < problem.length; ++i) {
                        var temp='';
                        if (problem[i].type === 1 || problem[i].type === 2 || problem[i].type === 3||problem[i].type===6) {
                            var question = problem[i].question;
                            if(!isEmpty(problem[i].content))temp+=problem[i].content;
                            question.sort((a, b) => {
                                return parseInt(a.questionNumber) - parseInt(b.questionNumber);
                            });
                            //console.log(question);
                            for (let j = 0; j < question.length; ++j) {
                                const option = question[j].option;
                                option.sort((a, b) => {
                                    return a.mark.localeCompare(b.mark);
                                });
                                temp += '<label class="label">' + question[j].questionNumber + '.</label>  ' + (isEmpty(question[j].content) ? '' : question[j].content)+
                                    '<ul class="list-group">';
                                //console.log(option);
                                for (let k = 0; k < option.length; ++k) {
                                    temp += '<li class="list-group-item"><div class="radio"><label><input type="radio" name="' + question[j].id + '" value="' + option[k].mark + '" required>' + option[k].content + '</label></div></li>';
                                }
                                temp += '</ul>';
                            }
                        }
                        else if(problem[i].type===7||problem[i].type===8){
                            if(!isEmpty(problem[i].content))temp+=problem[i].content;
                            console.log(problem[i]);
                            var question=problem[i].question;
                            for (let j = 0; j < question.length; ++j) {
                                temp += '<label class="label">' + question[j].questionNumber + '.</label>  ' + (isEmpty(question[j].content) ? '' : question[j].content)+'<textarea name="' + question[j].questionNumber + '" class="text-area"></textarea>';
                            }
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
                    $('.text-area').summernote({
                        lang: 'zh-CN',
                        placeholder: '请输入内容！',
                        focus: true
                    });
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
