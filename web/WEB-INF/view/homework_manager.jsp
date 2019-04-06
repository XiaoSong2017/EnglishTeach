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
                                     onclick="addProblem()" class="img-circle btn btn-outline-info" type="svg">
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
                        <tr class="row">
                            <td class="col" colspan="3">
                                <div class="card">
                                    <div class="card-header">
                                        <a class="card-link" data-toggle="collapse" onclick="onclickCollapse(this)"
                                           href="#">第<label class="label">1</label>题：</a>
                                    </div>
                                    <div class="collapse" data-parent="#parent_homework">
                                        <div class="card-body">
                                            <label class="label">
                                                选择题型：
                                                <select class="custom-select" required title="选择题型!"></select>
                                            </label>
                                            <table class="table table-striped">
                                                <thead class="thead-light">
                                                <tr class="row">
                                                    <td class="col">
                                                        <label class="label">题目内容：
                                                            <textarea class="text-area" required></textarea>
                                                        </label>
                                                    </td>
                                                </tr>
                                                </thead>
                                                <tbody>
                                                <tr class="row">
                                                    <td class="col">
                                                        <table class="table table-striped">
                                                            <thead>
                                                            <tr class="row">
                                                                <td class="col">
                                                                    <label class="label">问题：<textarea
                                                                            class="text-area"></textarea></label>
                                                                </td>
                                                            </tr>
                                                            <tr class="row">
                                                                <td class="col">
                                                                    <label class="label">
                                                                        参考答案：<textarea class="text-area"></textarea>
                                                                    </label>
                                                                </td>
                                                            </tr>
                                                            </thead>
                                                            <tbody>
                                                            <tr class="row">
                                                                <td class="col">
                                                                    <label class="label">选项<span style="color: #e0a800">A</span>:
                                                                        <textarea class="text-area"></textarea>
                                                                    </label>
                                                                </td>
                                                            </tr>
                                                            </tbody>
                                                            <tfoot>
                                                            <tr class="row">
                                                                <td class="col text-center">
                                                                    <img role="button"
                                                                         src="<%=request.getContextPath()%>/images/add.svg"
                                                                         alt="添加选项！"
                                                                         onclick="addTopic(this)"
                                                                         class="img-circle btn btn-outline-info"
                                                                         type="svg">
                                                                </td>
                                                            </tr>
                                                            </tfoot>
                                                        </table>
                                                    </td>
                                                </tr>
                                                </tbody>
                                                <tfoot>
                                                <tr class="row">
                                                    <td class="col text-center">
                                                        <img role="button"
                                                             src="<%=request.getContextPath()%>/images/add.svg"
                                                             alt="添加问题！"
                                                             onclick="addQuestion(this)"
                                                             class="img-circle btn btn-outline-info"
                                                             type="svg">
                                                    </td>
                                                </tr>
                                                </tfoot>
                                            </table>
                                        </div>
                                    </div>
                                </div>
                            </td>
                        </tr>
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
            <th class="col text-center">试题号</th>
            <th class="col text-center">试题名称</th>
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
<script type="text/javascript">
    function addExamPager(obj){
        //console.log($(obj).parent().prev().children().children().children().eq(0));
        const thead = $(obj).parent().prev().children().children().children().eq(0);
        //console.log($(thead.children().eq(2).children().eq(1).children()).val());
        var courseById=$(thead.children().eq(0).children().eq(1).children()).val();
        var title=$(thead.children().eq(1).children().eq(1).children()).val();
        var startTime=$(thead.children().eq(2).children().eq(1).children()).val();
        var startTime=$(thead.children().eq(3).children().eq(1).children()).val();
        var rows = $(obj).parent().prev().children().children().children().eq(1).children();
        var problems=[];
        for(var i=0;i<rows.length;++i){
            var problem=[];
            var problemNumber=rows.eq(i).children().children().children().eq(0).children().children().text();
            var problemTopic=rows.eq(i).children().children().children().eq(1).children().children().eq(0).children().val();
            var problemContent=rows.eq(i).children().children().children().eq(1).children().children().eq(1).children().eq(0).children().children().children().children().summernote('code');
            var questions=[];
            var rowsI=rows.eq(i).children().children().children().eq(1).children().children().eq(1).children().eq(1).children();
            for(var j=0;j<rowsI.length;++j){
                var question=[];
                var tableJ=rowsI.eq(j).children().children();
                var theadJ=tableJ.children().eq(0);
                var questionContent=theadJ.children().eq(0).children().children().children().summernote('code');
                var answer=theadJ.children().eq(1).children().children().children().summernote('code');
                var options=[];
                var tbodyJ=tableJ.children().eq(1);
                for(var k=0;k<tbodyJ.children().length;++k){
                    var option=[];
                    var mark=tbodyJ.children().eq(k).children().children().children().eq(0).text();
                    var optionContent=tbodyJ.children().eq(k).children().children().children().eq(1).summernote('code');
                    option.push({'mark':mark});
                    option.push({'content':optionContent});
                    options.push(option);
                    //console.log(tbodyJ.children().eq(k).children().children().children().eq(0).text());
                }
                question.push({'question':questionContent});
                question.push({'answer':answer});
                question.push({'option':options});
                questions.push(question);
            }
            problem.push({'number':problemNumber});
            problem.push({'topicId':problemTopic});
            problem.push({'content':problemContent});
            problem.push({'question':questions});
            problems.push(problem);
            //console.log(rowsI.eq(0).children().children());
        }
        console.log(problems);
    }
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
                        '            <td class="col text-center">' + data.data[i].name + '</td>\n' +
                        '            <td class="col text-center">' + data.data[i].startTime + '</td>\n' +
                        '            <td class="col text-center">' + data.data[i].endTime + '</td>\n' +
                        '            <td class="col text-center">' + '<%=request.getSession().getAttribute("user")%>' + '</td>\n' +
                        '            <td class="col text-center">\n' +
                        '                <div class="btn-group" role="group">\n' +
                        '                    <a class="btn btn-outline-info" data-toggle="modal">修改</a>\n' +
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
    function onclickAddHomework() {
        $.ajax({
            url: '<%=request.getContextPath()%>/courseBean',
            async: true,
            type: 'post',
            success: (data)=> {
                $('#add_homework_manager_course').empty();
                for (var i = 0; i < data.data.length; ++i) {
                    $('#add_homework_manager_course').append('<option value="' + data.data[i].id + '">' + data.data[i].name + '</option>');
                    //$('#updateSelectElectiveCourse').append('<option value="' + data.data[i].id + '">' + data.data[i].name + '</option>');
                }
            }
        });
    }
    function onclickCollapse(obj) {
        $(obj).parent().next().collapse('toggle');
    }
    function addProblem() {
        // console.log($('#tbody_work_Problem').children('last'));
        var row = $('#tbody_work_Problem').children().eq(parseInt($('#tbody_work_Problem').children().length) - 1).clone();
        //console.log(row.children().children().children().eq(0).children().children().text());
        var label = row.children().children().children().eq(0).children().children();
        label.text(parseInt(label.text()) + 1);
        $('#tbody_work_Problem').append(row);
    }
    function  addQuestion(obj) {
        var tbody=$(obj).parent().parent().parent().prev();
        var row=tbody.children().eq(parseInt(tbody.children().length)-1).clone();
        tbody.append(row);
    }
    function addTopic(obj) {
        var tbody=$(obj).parent().parent().parent().prev();
        var row=tbody.children().eq(parseInt(tbody.children().length)-1).clone();
        row.children().children().children().eq(0).text( String.fromCharCode(row.children().children().children().eq(0).text().charCodeAt()+1));
        tbody.append(row);
    }
</script>
</html>
