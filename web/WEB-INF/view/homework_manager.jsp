<%--
  Created by IntelliJ IDEA.
  User: wangsong
  Date: 2019/3/3
  Time: 19:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>作业管理</title>
</head>
<body>
<div class="modal hide fade" id="ModalHomework" tabindex="-1" role="dialog" aria-labelledby="ModalHomeworkLabel"
     aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="ModalHomeworkLabel">添加作业</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <div id="parent_homework" class="container-fluid">
                    <table class="table table-striped table-hover">
                        <thead class="thead-light">
                        <tr class="row">
                            <th><label for="add_homework_manager_course" class="label">选择课程：</label></th>
                            <th><select id="add_homework_manager_course" class="custom-select"></select></th>
                        </tr>
                        <tr class="row">
                            <th class="col text-center">
                                <label for="home_work_title">作业标题：</label>
                            </th>
                            <th class="col text-center">
                                <input type="text" id="home_work_title" required class="text-input">
                            </th>
                            <th class="col text-center">
                                <img role="button" src="<%=request.getContextPath()%>/images/add.svg" alt="添加题目"
                                     onclick="addProblem(this)" class="img-circle btn btn-outline-info" type="svg">
                            </th>
                        </tr>
                        <tr class="row">
                            <th class="col text-center">
                                <label for="home_work_start_time">开始时间：</label>
                            </th>
                            <th class="col text-center">
                                <input id="home_work_start_time" class="currentDate" type="datetime-local" required>
                            </th>
                        </tr>
                        <tr class="row">
                            <th class="col text-center">
                                <label for="home_work_end_time">结束时间：</label>
                            </th>
                            <th class="col text-center">
                                <input id="home_work_end_time" class="currentDate" type="datetime-local" required>
                            </th>
                        </tr>
                        </thead>
                        <tbody id="tbody_work_Problem">
                        </tbody>
                    </table>
                </div>
            </div>
            <div class="modal-footer">
                <button class="btn btn-secondary" data-dismiss="modal">取消</button>
                <button class="btn btn-primary" onclick="addExamPager(this)" data-dismiss="modal">添加</button>
            </div>
            <%--</form>--%>
        </div>
    </div>
</div>
<div class="container">
    <table class="table table-striped table-hover">
        <thead class="thead-dark">
        <tr class="row">
            <th class="col text-center">序号</th>
            <th class="col text-center">作业号</th>
            <th class="col text-center">作业名称</th>
            <th class="col text-center">开考时间</th>
            <th class="col text-center">结束时间</th>
            <th class="col text-center">出题人</th>
            <th class="col text-center">操作
                <div class="btn-group" role="group">
                    <a role="button" shape="circle" class="btn btn-outline-info" data-toggle="modal"
                       href="#ModalHomework" onclick="onclickAddHomework()">
                        <img role="img" class="img-fluid" src="<%=request.getContextPath()%>/images/add.svg"
                             type="svg" alt="img">
                    </a>
                </div>
            </th>
        </tr>
        </thead>
        <tbody class="popover-body" id="tbody_homework">
        </tbody>
    </table>
</div>
</body>
<div class="modal hide fade" id="preExaminationPaperByHomework" tabindex="-1" role="dialog"
     aria-labelledby="preHomeworkModalExamLabel"
     aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="preHomeworkModalExamLabel">查看作业</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body" id="homework_modal_body"></div>
            <div class="modal-footer">
                <button class="btn btn-secondary" data-dismiss="modal">取消</button>
            </div>
            <%--</form>--%>
        </div>
    </div>
</div>
<div class="modal hide fade" id="updateExaminationPaperByHomework" tabindex="-1" role="dialog"
     aria-labelledby="uppdateHomeworkModalExamLabel" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="updateHomeworkModalExamLabel">修改作业</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body" id="update_homework_modal_body">
                <div id="update_parent_homework" class="container-fluid">
                    <input id="update_id" type="hidden">
                    <input id="update_cId" type="hidden">
                    <table class="table table-striped table-hover">
                        <thead>
                        <tr class="row">
                            <th><label for="update_homework_manager_course" class="label">对应课程：</label></th>
                            <th><p id="update_homework_manager_course" class="text-center text-info"></p></th>
                        </tr>
                        <tr class="row">
                            <th class="col text-center">
                                <label for="update_home_work_title">作业标题：</label>
                            </th>
                            <th class="col text-center">
                                <input id="update_home_work_title" type="text" required class="text-input text-center">
                            </th>
                            <th class="col text-center">
                                <img role="button" src="<%=request.getContextPath()%>/images/add.svg" alt="添加题目"
                                     onclick="addProblemByWork()" class="img-circle btn btn-outline-info" type="svg">
                            </th>
                        </tr>
                        <tr class="row">
                            <th class="col text-center">
                                <label for="update_home_work_start_time">开始时间：</label>
                            </th>
                            <th class="col text-center">
                                <input id="update_home_work_start_time" class="currentDate" type="datetime-local"
                                       required>
                            </th>
                        </tr>
                        <tr class="row">
                            <th class="col text-center">
                                <label for="update_home_work_end_time">结束时间：</label>
                            </th>
                            <th class="col text-center">
                                <input id="update_home_work_end_time" class="currentDate" type="datetime-local"
                                       required>
                            </th>
                        </tr>
                        </thead>
                        <tbody id="tbody_update_work_Problem">
                        </tbody>
                    </table>
                </div>
            </div>
            <div class="modal-footer">
                <button class="btn btn-secondary" data-dismiss="modal">取消</button>
                <button class="btn btn-primary" data-dismiss="modal" onclick="updateExamPagerByHomework(true)">修改</button>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript">
    function updateExamPagerByHomework(examType) {
        var examinationPaper = {};
        examinationPaper.id = $('#update_id').val();
        examinationPaper.cId = $('#update_cId').val();
        examinationPaper.type = examType;
        examinationPaper.tId = '<%=request.getSession().getAttribute("ID")%>';
        examinationPaper.name = $('#update_home_work_title').val();
        examinationPaper.startTime = $('#update_home_work_start_time').val();
        examinationPaper.endTime = $('#update_home_work_end_time').val();
        var rows = $('#tbody_update_work_Problem').children();
        var problems = [];
        for (var i = 0; i < rows.length; ++i) {
            var componentId = $(rows.eq(i).children().children().children().eq(0).children().children()).attr("component");
            var problemNumber = rows.eq(i).children().children().children().eq(0).children().children().text();
            var problemTopic = rows.eq(i).children().children().children().eq(1).children().children().eq(0).children().val();
            var problemContent = rows.eq(i).children().children().children().eq(1).children().children().eq(1).children().eq(0).children().eq(0).children().children().children().summernote('code');
            //console.log( rows.eq(i).children().children().children().eq(1).children().children().eq(1).children().eq(0));
            var core = rows.eq(i).children().children().children().eq(1).children().children().eq(1).children().eq(0).children().eq(1).children().children().children().val();
            var questions = [];
            var rowsI = rows.eq(i).children().children().children().eq(1).children().children().eq(1).children().eq(1).children();
            for (var j = 0; j < rowsI.length; ++j) {
                var tableJ = rowsI.eq(j).children().children();
                var theadJ = tableJ.children().eq(0);
                var questionId = $(theadJ.children().eq(0).children().children().children()).attr("question");
                var questionContent = theadJ.children().eq(0).children().children().children().summernote('code');
                var answer = theadJ.children().eq(1).children().children().children().summernote('code');
                var options = [];
                var tbodyJ = tableJ.children().eq(1);
                for (var k = 0; k < tbodyJ.children().length; ++k) {
                    var optionId = $(tbodyJ.children().eq(k).children().children().children().eq(0)).attr('option');
                    var mark = tbodyJ.children().eq(k).children().children().children().eq(0).text();
                    var optionContent = tbodyJ.children().eq(k).children().children().children().eq(1).summernote('code');
                    var option = {};
                    if (!isEmpty(optionId)) option.id = optionId;
                    option.mark = mark;
                    option.content = optionContent;
                    options.push(option);
                    //console.log(tbodyJ.children().eq(k).children().children().children().eq(0).text());
                }
                var question = {};
                if (!isEmpty(questionId)) question.id = questionId;
                question.questionNumber = j + 1;
                question.question = questionContent;
                question.answer = answer;
                question.option = options;
                questions.push(question);
            }
            var problem = {};
            if (!isEmpty(componentId)) {
                problem.id = componentId;
            }
            problem.problemNumber = problemNumber;
            problem.type = problemTopic;
            problem.content = problemContent;
            problem.core = core;
            problem.question = questions;
            problems.push(problem);
            //console.log(rowsI.eq(0).children().children());
        }
        examinationPaper.componentsById = problems;
        //console.log(examinationPaper);
        $.ajax({
            url: '<%=request.getContextPath()%>/updateExaminationPager',
            async: true,
            type: 'POST',
            datatype: 'json',
            data: {'examinationPaperPo': JSON.stringify(examinationPaper)},
            success: (data) => {
                if (data.resultCode === 'success') {
                    $.ajax({
                        url: '<%=request.getContextPath()%>/examinationPaperBean',
                        datatype: 'json',
                        async: true,
                        type: 'post',
                        data: {teacherId: '<%=request.getSession().getAttribute("ID")%>', type: examType},
                        success: (data) => {
                            $('#tbody_homework').empty();
                            for (var i = 0; i < data.data.length; ++i) {
                                $('#tbody_homework').append('<tr class="row">\n' +
                                    '            <td class="col text-center">' + (parseInt(i) + 1) + '</td>\n' +
                                    '            <td class="col text-center">' + data.data[i].id + '</td>\n' +
                                    '            <td class="col text-center"><a data-toggle="modal"\n' +
                                    '                       href="#preExaminationPaperByHomework" onclick="preExaminPapgerByHomework(\'' + data.data[i].id + '\')">' + data.data[i].name + '</a></td>\n' +
                                    '            <td class="col text-center">' + data.data[i].startTime + '</td>\n' +
                                    '            <td class="col text-center">' + data.data[i].endTime + '</td>\n' +
                                    '            <td class="col text-center">' + '<%=request.getSession().getAttribute("user")%>' + '</td>\n' +
                                    '            <td class="col text-center">\n' +
                                    '                <div class="btn-group" role="group">\n' +
                                    '                    <a class="btn btn-outline-info" data-toggle="modal" href="#updateExaminationPaperByHomework" onclick="updateExaminationPaperById(\'' + data.data[i].courseByCId.name + '\',\'' + data.data[i].courseByCId.id + '\',\'' + data.data[i].id + '\',\'' + data.data[i].name + '\',\'' + data.data[i].startTime + '\',\'' + data.data[i].endTime + '\')">修改</a>\n' +
                                    '                    <a class="btn btn-outline-danger" onclick="deleteExaminationPaperById(\'' + data.data[i].id + '\',this)">删除</a>\n' +
                                    '                </div>\n' +
                                    '            </td>\n' +
                                    '        </tr>');
                            }
                            Swal.fire({text: "修改已完成！", type: 'success'});
                        },
                        error: () => {
                            Swal.fire({text: "加载失败！请刷新页面重试！", type: 'error'});
                        }
                    });
                } else Swal.fire({text: "修改出错！请确保上传无误后重试！", type: 'error'});
            },
            error:
                () => {
                    Swal.fire({text: "上传出错！请确保输入无误后重试！", type: 'error'});
                }
        });
    }

    function addExamPager(obj) {
        //console.log($(obj).parent().prev().children().children().children().eq(0));
        const thead = $(obj).parent().prev().children().children().children().eq(0);
        //console.log($(thead.children().eq(2).children().eq(1).children()).val());
        var courseById = $(thead.children().eq(0).children().eq(1).children()).val();
        var title = $(thead.children().eq(1).children().eq(1).children()).val();
        var startTime = $(thead.children().eq(2).children().eq(1).children()).val();
        var endTime = $(thead.children().eq(3).children().eq(1).children()).val();
        var rows = $(obj).parent().prev().children().children().children().eq(1).children();
        var problems = [];
        for (var i = 0; i < rows.length; ++i) {
            var problemNumber = rows.eq(i).children().children().children().eq(0).children().children().text();
            var problemTopic = rows.eq(i).children().children().children().eq(1).children().children().eq(0).children().val();
            var problemContent = rows.eq(i).children().children().children().eq(1).children().children().eq(1).children().eq(0).children().eq(0).children().children().children().summernote('code');
            //console.log( rows.eq(i).children().children().children().eq(1).children().children().eq(1).children().eq(0));
            var core = rows.eq(i).children().children().children().eq(1).children().children().eq(1).children().eq(0).children().eq(1).children().children().children().val();
            var questions = [];
            var rowsI = rows.eq(i).children().children().children().eq(1).children().children().eq(1).children().eq(1).children();
            for (var j = 0; j < rowsI.length; ++j) {
                var tableJ = rowsI.eq(j).children().children();
                var theadJ = tableJ.children().eq(0);
                var questionContent = theadJ.children().eq(0).children().children().children().summernote('code');
                var answer = theadJ.children().eq(1).children().children().children().summernote('code');
                var options = [];
                var tbodyJ = tableJ.children().eq(1);
                for (var k = 0; k < tbodyJ.children().length; ++k) {
                    var mark = tbodyJ.children().eq(k).children().children().children().eq(0).text();
                    var optionContent = tbodyJ.children().eq(k).children().children().children().eq(1).summernote('code');
                    var option = {
                        'mark': mark,
                        'content': optionContent
                    };
                    options.push(option);
                    //console.log(tbodyJ.children().eq(k).children().children().children().eq(0).text());
                }
                var question = {
                    'questionNumber': j + 1,
                    'question': questionContent,
                    'answer': answer,
                    'option': options
                };
                questions.push(question);
            }
            var problem = {
                'problemNumber': problemNumber,
                'type': problemTopic,
                'content': problemContent,
                'core': core,
                'question': questions
            };
            problems.push(problem);
            //console.log(rowsI.eq(0).children().children());
        }
        var examinationPaper = {
            'type': 1,
            'name': title,
            'startTime': startTime,
            'endTime': endTime,
            'cId': courseById,
            'tId': '<%=request.getSession().getAttribute("ID")%>',
            'componentsById': problems
        };
        //console.log(examinationPaper);
        $.ajax({
            url: '<%=request.getContextPath()%>/saveExaminationPaper',
            type: 'post',
            async: true,
            datatype: 'json',
            data: {'examinationPaperPo': JSON.stringify(examinationPaper)},
            success: (data) => {
                if (data.resultCode === 'success') {
                    $.ajax({
                        url: '<%=request.getContextPath()%>/examinationPaperBean',
                        datatype: 'json',
                        async: true,
                        type: 'post',
                        data: {teacherId: '<%=request.getSession().getAttribute("ID")%>', type: true},
                        success: (data) => {
                            $('#tbody_homework').empty();
                            for (var i = 0; i < data.data.length; ++i) {
                                $('#tbody_homework').append('<tr class="row">\n' +
                                    '            <td class="col text-center">' + (parseInt(i) + 1) + '</td>\n' +
                                    '            <td class="col text-center">' + data.data[i].id + '</td>\n' +
                                    '            <td class="col text-center">' + data.data[i].name + '</td>\n' +
                                    '            <td class="col text-center">' + data.data[i].startTime + '</td>\n' +
                                    '            <td class="col text-center">' + data.data[i].endTime + '</td>\n' +
                                    '            <td class="col text-center">' + '<%=request.getSession().getAttribute("user")%>' + '</td>\n' +
                                    '            <td class="col text-center">\n' +
                                    '                <div class="btn-group" role="group">\n' +
                                    '                    <a class="btn btn-outline-info" data-toggle="modal" href="#updateExaminationPaperByHomework" onclick="updateExaminationPaperById(\'' + data.data[i].courseByCId.name + '\',\'' + data.data[i].courseByCId.id + '\',\'' + data.data[i].id + '\',\'' + data.data[i].name + '\',\'' + data.data[i].startTime + '\',\'' + data.data[i].endTime + '\')">修改</a>\n' +
                                    '                    <a class="btn btn-outline-danger" onclick="deleteExaminationPaperById(\'' + data.data[i].id + '\',this)">删除</a>\n' +
                                    '                </div>\n' +
                                    '            </td>\n' +
                                    '        </tr>');
                            }
                            Swal.fire({text: "添加已完成！", type: 'success'});
                        },
                        error: () => {
                            Swal.fire({text: "加载失败！请刷新页面重试！", type: 'error'});
                        }
                    });
                } else Swal.fire({text: "添加出错！请确保上传无误后重试！", type: 'error'});
            },
            error: () => {
                Swal.fire({text: "上传出错！请确保输入无误后重试！", type: 'error'});
            }
        });
    }

    var topic;
    $('.text-area').summernote({
        lang: 'zh-CN',
        placeholder: '请输入内容！',
        focus: true
    });

    $(() => {
        $.ajax({
            url: '<%=request.getContextPath()%>/examinationPaperBean',
            datatype: 'json',
            async: true,
            type: 'post',
            data: {teacherId: '<%=request.getSession().getAttribute("ID")%>', type: true},
            success: (data) => {
                for (var i = 0; i < data.data.length; ++i) {
                    $('#tbody_homework').append('<tr class="row">\n' +
                        '            <td class="col text-center">' + (parseInt(i) + 1) + '</td>\n' +
                        '            <td class="col text-center">' + data.data[i].id + '</td>\n' +
                        '            <td class="col text-center"><a data-toggle="modal"\n' +
                        '                       href="#preExaminationPaperByHomework" onclick="preExaminPapgerByHomework(\'' + data.data[i].id + '\')">' + data.data[i].name + '</a></td>\n' +
                        '            <td class="col text-center">' + data.data[i].startTime + '</td>\n' +
                        '            <td class="col text-center">' + data.data[i].endTime + '</td>\n' +
                        '            <td class="col text-center">' + '<%=request.getSession().getAttribute("user")%>' + '</td>\n' +
                        '            <td class="col text-center">\n' +
                        '                <div class="btn-group" role="group">\n' +
                        '                    <a class="btn btn-outline-info" data-toggle="modal" href="#updateExaminationPaperByHomework" onclick="updateExaminationPaperById(\'' + data.data[i].courseByCId.name + '\',\'' + data.data[i].courseByCId.id + '\',\'' + data.data[i].id + '\',\'' + data.data[i].name + '\',\'' + data.data[i].startTime + '\',\'' + data.data[i].endTime + '\')">修改</a>\n' +
                        '                    <a class="btn btn-outline-danger" onclick="deleteExaminationPaperById(\'' + data.data[i].id + '\',this)">删除</a>\n' +
                        '                </div>\n' +
                        '            </td>\n' +
                        '        </tr>');
                }
            },
            error: () => {
                Swal.fire({text: "加载失败！请刷新页面重试！", type: 'error'});
            }
        });
        $.ajax({
            url: '<%=request.getContextPath()%>/topicBean',
            async: true,
            type: 'post',
            success: (data) => {
                topic = data.data;
                for (var i = 0; i < data.data.length; ++i) {
                    //console.log('select:'+$('#tbody_work_Problem').children().eq(0).children().children().children().eq(1).children().children().eq(0).children());
                    $('#tbody_work_Problem').children().eq(0).children().children().children().eq(1).children().children().eq(0).children()
                        .append(' <option class="selectedItem" value="' + data.data[i].id + '">' + data.data[i].name + '</option>');
                }
            },
            error: () => {
                Swal.fire({text: "加载失败！请刷新页面重试！", type: 'error'});
            }
        });
    });

    function updateExaminationPaperById(nameByCourse, cId, id, name, startTime, endTime) {
        $('#update_homework_manager_course').text(nameByCourse);
        $('#update_cId').val(cId);
        $('#update_id').val(id);
        $('#update_home_work_title').val(name);
        $('#update_home_work_start_time').val(startTime);
        $('#update_home_work_end_time').val(endTime);
        $.ajax({
            url: '<%=request.getContextPath()%>/componentBean',
            type: 'POST',
            async: true,
            data: {'examId': id},
            success: (data) => {
                $('#tbody_update_work_Problem').empty();
                var data1 = data.data;
                var temp='';
                for (var i = 0, length1 = data1.length; i < length1; ++i) {
                    temp += '<tr class="row"><td class="col" colspan="3">' +
                        '<div class="card">' +
                        '<div class="card-header">' +
                        '<a class="card-link" data-toggle="collapse" onclick="onclickCollapse(this)"' +
                        ' href="#">第<label class="label" component="' + data1[i].id + '">' + data1[i].problemNumber + '</label>题：</a>' +
                        '</div>' +
                        '<div class="collapse" data-parent="#update_parent_homework">' +
                        '<div class="card-body">选择题型：' +
                        '<label class="label">' +
                        '<select class="custom-select" required title="选择题型!">';
                    for (var j = 0, length2 = topic.length; j < length2; ++j) {
                        if (data1[i].problemByQId.topicByType.id === topic[j].id) {
                            temp += '<option class="selectedItem" value="' + topic[j].id + '" selected="selected">' + topic[j].name + '</option>';
                        } else {
                            temp += '<option class="selectedItem" value="' + topic[j].id + '">' + topic[j].name + '</option>';
                        }
                    }
                    temp += '</select></label>';
                    temp += '<table class="table table-striped"><thead class="thead-light"><tr class="row"><td class="col">' +
                        '<label class="label">题目内容：<textarea class="text-area" problem="' + data1[i].problemByQId.id + '">' + (isEmpty(data1[i].title) ? '' : data1[i].title) + '</textarea></label></td>' +
                        '</tr><tr class="row"><td class="col"><label class="label">每一问分值：<input type="number" placeholder="请输入！" required value="' + data1[i].core + '"></label>' +
                        '</td></tr></thead>';
                    temp += '<tbody>';
                    var question = data1[i].problemByQId.questionsById;
                    for (var j = 0, length3 = question.length; j < length3; ++j) {
                        temp += '<tr class="row"><td class="col"><table class="table table-striped"><thead>' +
                            '<tr class="row"><td class="col"><label class="label">问题：<textarea class="text-area" question="' + question[j].id + '">' + (isEmpty(question[j].content) ? '' : question[j].content) + '</textarea></label></td></tr>' +
                            '<tr class="row"><td class="col"><label class="label">参考答案：<textarea class="text-area">' + (isEmpty(question[j].answer) ? '' : question[j].answer) + '</textarea></label></td></tr>' +
                            '</thead><tbody>';
                        for (var option = question[j].optionsById, k = 0, length4 = option.length; k < length4; ++k) {
                            temp += '<tr class="row"><td class="col"><label class="label">选项<span style="color: #e0a800" option="' + option[k].id + '">' + option[k].mark + '</span>:' +
                                '<textarea class="text-area">' + option[k].content + '</textarea></label></td></tr>';
                        }
                        temp += '</tbody><tfoot><tr class="row"><td class="col text-center">' +
                            '<img role="button" src="<%=request.getContextPath()%>/images/add.svg" alt="添加选项！" onclick="addTopic(this)" class="img-circle btn btn-outline-info" type="svg">' +
                            '</td></tr></tfoot>';
                        temp += '</table></td></tr>';
                    }
                    temp += '</tbody><tfoot><tr class="row"><td class="col text-center">' +
                        '<img role="button" src="<%=request.getContextPath()%>/images/add.svg" alt="添加问题！" onclick="addQuestion(this)" class="img-circle btn btn-outline-info" type="svg">' +
                        '</td></tr></tfoot></table>';
                    temp += '</div></div></div></td></tr>';
                }
                //console.log(temp);
                //$('#tbody_update_work_Problem').append(temp);
                $('#tbody_update_work_Problem').html(temp);
                //document.getElementById('tbody_update_work_Problem').innerHTML=temp;
                $('.text-area').summernote({
                    lang: 'zh-CN',
                    placeholder: '请输入内容！',
                    focus: true
                });
            },
            error: () => {
                Swal.fire({
                    text: '网络错误！请重试！',
                    type: 'error'
                });
            }
        });
    }

    function preExaminPapgerByHomework(obj) {
        $('#homework_modal_body').empty();
        $('#homework_modal_body').append('<iframe src="<%=request.getContextPath()%>/paper?id=' + obj + '&preview=disabled" style="width: 100%" height="100%" onload="changeFrameHeight(this)"></iframe>');
    }

    function onclickAddHomework() {
        $.ajax({
            url: '<%=request.getContextPath()%>/courseBean',
            async: true,
            type: 'post',
            success: (data) => {
                $('#add_homework_manager_course').empty();
                for (var i = 0; i < data.data.length; ++i) {
                    $('#add_homework_manager_course').append('<option value="' + data.data[i].id + '">' + data.data[i].name + '</option>');
                    //$('#add_exam_manager_course').append('<option value="' + data.data[i].id + '">' + data.data[i].name + '</option>');
                }
            }
        });
    }

    function onclickCollapse(obj) {
        $(obj).parent().next().collapse('toggle');
    }

    function addProblem(obj) {
        var tbody = $(obj).parent().parent().parent().next();
        //console.log(lastRow.children().children().children().eq(0).children().children().text());
        tbody.append('<tr class="row">\n' +
            '                            <td class="col" colspan="3">\n' +
            '                                <div class="card">\n' +
            '                                    <div class="card-header">\n' +
            '                                        <a class="card-link" data-toggle="collapse" onclick="onclickCollapse(this)"\n' +
            '                                           href="#">第<label class="label">' + (parseInt(tbody.children().length) + 1) + '</label>题：</a>\n' +
            '                                    </div>\n' +
            '                                    <div class="collapse" data-parent="#parent_homework">\n' +
            '                                        <div class="card-body">\n' +
            '                                            <label class="label">\n' +
            '                                                选择题型：\n' +
            '                                                <select class="custom-select" required title="选择题型!"></select>\n' +
            '                                            </label>\n' +
            '                                            <table class="table table-striped">\n' +
            '                                                <thead class="thead-light">\n' +
            '                                                <tr class="row">\n' +
            '                                                    <td class="col">\n' +
            '                                                        <label class="label">题目内容：\n' +
            '                                                            <textarea class="text-area" required></textarea>\n' +
            '                                                        </label>\n' +
            '                                                    </td>\n' +
            '                                                </tr>\n' +
            '                                                <tr class="row">\n' +
            '                                                    <td class="col">\n' +
            '                                                        <label class="label">\n' +
            '                                                            每一问分值：<input type="number" placeholder="请输入！" required>\n' +
            '                                                        </label>\n' +
            '                                                    </td>\n' +
            '                                                </tr>\n' +
            '                                                </thead>\n' +
            '                                                <tbody>\n' +
            '                                                </tbody>\n' +
            '                                                <tfoot>\n' +
            '                                                <tr class="row">\n' +
            '                                                    <td class="col text-center">\n' +
            '                                                        <img role="button"\n' +
            '                                                             src="<%=request.getContextPath()%>/images/add.svg"\n' +
            '                                                             alt="添加问题！"\n' +
            '                                                             onclick="addQuestion(this)"\n' +
            '                                                             class="img-circle btn btn-outline-info"\n' +
            '                                                             type="svg">\n' +
            '                                                    </td>\n' +
            '                                                </tr>\n' +
            '                                                </tfoot>\n' +
            '                                            </table>\n' +
            '                                        </div>\n' +
            '                                    </div>\n' +
            '                                </div>\n' +
            '                            </td>\n' +
            '                        </tr>');
        $.ajax({
            url: '<%=request.getContextPath()%>/topicBean',
            async: true,
            type: 'post',
            success: (data) => {
                topic = data.data;
                for (var i = 0; i < data.data.length; ++i) {
                    //console.log('select:'+$('#tbody_work_Problem').children().eq(0).children().children().children().eq(1).children().children().eq(0).children());
                    $(tbody).children().eq(parseInt(tbody.children().length) - 1).children().children().children().eq(1).children().children().eq(0).children()
                        .append(' <option class="selectedItem" value="' + data.data[i].id + '">' + data.data[i].name + '</option>');
                }
            },
            error: () => {
                Swal.fire({text: "加载失败！请刷新页面重试！", type: 'error'});
            }
        });
        $('.text-area').summernote({
            lang: 'zh-CN',
            placeholder: '请输入内容！',
            focus: true
        });
    }

    function addQuestion(obj) {
        var tbody = $(obj).parent().parent().parent().prev();
        tbody.append('<tr class="row">' +
            '                                                    <td class="col">\n' +
            '                                                        <table class="table table-striped">\n' +
            '                                                            <thead>\n' +
            '                                                            <tr class="row">\n' +
            '                                                                <td class="col">\n' +
            '                                                                    <label class="label">问题' + (parseInt(tbody.children().length) + 1) + '：<textarea\n' +
            '                                                                            class="text-area"></textarea></label>\n' +
            '                                                                </td>\n' +
            '                                                            </tr>\n' +
            '                                                            <tr class="row">\n' +
            '                                                                <td class="col">\n' +
            '                                                                    <label class="label">\n' +
            '                                                                        参考答案：<textarea class="text-area"></textarea>\n' +
            '                                                                    </label>\n' +
            '                                                                </td>\n' +
            '                                                            </tr>\n' +
            '                                                            </thead>\n' +
            '                                                            <tbody>\n' +
            '                                                            </tbody>\n' +
            '                                                            <tfoot>\n' +
            '                                                            <tr class="row">\n' +
            '                                                                <td class="col text-center">\n' +
            '                                                                    <img role="button"\n' +
            '                                                                         src="<%=request.getContextPath()%>/images/add.svg"\n' +
            '                                                                         alt="添加选项！"\n' +
            '                                                                         onclick="addOption(this)"\n' +
            '                                                                         class="img-circle btn btn-outline-info"\n' +
            '                                                                         type="svg">\n' +
            '                                                                </td>\n' +
            '                                                            </tr>\n' +
            '                                                            </tfoot>' +
            '                                                        </table>' +
            '                                                    </td>' +
            '                                                </tr>');
        $('.text-area').summernote({
            lang: 'zh-CN',
            placeholder: '请输入内容！',
            focus: true
        });
    }

    function addOption(obj) {
        var tbody = $(obj).parent().parent().parent().prev();
        //console.log(tbody.children().length);
        tbody.append('<tr class="row">' +
            '                                                                <td class="col">' +
            '                                                                    <label class="label">选项<span style="color: #e0a800">' + String.fromCharCode('A'.charCodeAt() + tbody.children().length) + '</span>:' +
            '                                                                        <textarea class="text-area"></textarea>' +
            '                                                                    </label>' +
            '                                                                </td>' +
            '                                                            </tr>');
        $('.text-area').summernote({
            lang: 'zh-CN',
            placeholder: '请输入内容！',
            focus: true
        });
    }
</script>
</html>
