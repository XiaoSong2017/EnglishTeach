<%@ page import="org.springframework.context.ApplicationContext" %>
<%@ page import="org.springframework.web.context.support.WebApplicationContextUtils" %>
<%@ page import="po.TeachingPo" %>
<%@ page import="service.TeachingService" %>
<%--
  Created by IntelliJ IDEA.
  User: wangsong
  Date: 2019/3/3
  Time: 18:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>授课管理</title>
</head>
<body>
<div class="modal fade" id="addTeaching" tabindex="-1" role="dialog" aria-labelledby="addTeachingTitle"
     aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="addTeachingTitle">添加任课</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <div class="container-fluid list-group">
                    <div class="list-group-item">
                        <label class="col-form-label" for="selectTeaching">选择课程：</label>
                        <select class="custom-select" id="selectTeaching" required>
                            <option>--未选择--</option>
                        </select>
                    </div>
                    <div class="list-group-item">
                        <label class="label" for="addUsualProportion">平时成绩比重：</label>
                        <input class="input-group-text" onchange="$('#updateExamProportion').val(1-parseFloat(value))" oninput="if(value>=1)value=1;if(value<=0)value=0;"
                               type="number" id="addUsualProportion" required>
                    </div>
                    <div class="list-group-item">
                        <label class="label" for="addExamProportion">考试成绩比重：</label>
                        <input class="input-group-text" type="number" id="addExamProportion" required>
                    </div>
                    <div class="list-group-item">
                        <label class="label">任课教师：</label>
                        <p class="text"><%=request.getSession().getAttribute("user")%>
                        </p>
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-outline-secondary" data-dismiss="modal">取消</button>
                <button type="button" class="btn btn-outline-primary" onclick="addTeaching()" data-dismiss="modal">添加
                </button>
            </div>
        </div>
    </div>
</div>
<div class="container">
    <table class="table table-striped table-hover">
        <thead class="thead-dark">
        <tr class="row">
            <th class="col text-center">序号</th>
            <th class="col text-center">课程号</th>
            <th class="col text-center">课程名</th>
            <th class="col text-center">平时成绩比重</th>
            <th class="col text-center">考试成绩比重</th>
            <th class="col text-center">任课教师</th>
            <th class="col text-center">
                操作
                <div class="btn-group" role="group">
                    <a role="button" class="btn btn-outline-primary"
                       data-toggle="modal" href="#addTeaching">
                        <img src="<%=request.getContextPath()%>/images/add_course.svg" type="svg">
                    </a>
                </div>
            </th>
        </tr>
        </thead>
        <tbody id="tbodyTeaching">
        <%
            ApplicationContext applicationContext = WebApplicationContextUtils.getWebApplicationContext(application);
            TeachingService teachingService = (TeachingService) applicationContext.getAutowireCapableBeanFactory().getBean("teachingService");
            int i = 1;
            for (TeachingPo teachingPo : teachingService.getAll()) {
        %>
        <tr class="row">
            <td class="col text-center"><%=i++%>
            </td>
            <td class="col text-center"><%=teachingPo.getCourseByCId().getId()%>
            </td>
            <td class="col text-center"><%=teachingPo.getCourseByCId().getName()%>
            </td>
            <td class="col text-center"><%=teachingPo.getUsualProportion()%>
            </td>
            <td class="col text-center"><%=teachingPo.getExamProportion()%>
            </td>
            <td class="col text-center"><%=teachingPo.getTeacherByTId().getName()%>
            </td>
            <td class="col text-center">
                <div class="btn-group" role="group">
                    <a role="button" class="btn btn-outline-info" data-toggle="modal"
                       onclick="updateTeachingById('<%=teachingPo.getId()%>','<%=teachingPo.getCourseByCId().getId()%>'
                               ,'<%=teachingPo.getTeacherByTId().getId()%>','<%=teachingPo.getCourseByCId().getName()%>'
                               ,'<%=teachingPo.getUsualProportion()%>','<%=teachingPo.getExamProportion()%>',this)"
                       href="#updateTeaching">修改</a>
                    <a role="button" class="btn btn-outline-danger"
                       onclick="deleteTeachingById('<%=teachingPo.getId()%>',this)">删除</a>
                </div>
            </td>
        </tr>
        <%}%>
        </tbody>
    </table>
</div>
<div class="modal fade" id="updateTeaching" tabindex="-1" role="dialog" aria-labelledby="updateTeachingTitle"
     aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="updateTeachingTitle">修改任课</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <div class="container-fluid list-group">
                    <div class="list-group-item">
                        <label class="label">课程名：</label>
                        <p class="text" id="courseName"></p>
                    </div>
                    <div class="list-group-item">
                        <label class="label" for="updateUsualProportion">平时成绩比重：</label>
                        <input class="input-group-text" onchange="$('#updateExamProportion').val(1-parseFloat(value))" oninput="if(value>=1)value=1;if(value<=0)value=0;" type="number" id="updateUsualProportion" required>
                    </div>
                    <div class="list-group-item">
                        <label class="label" for="updateExamProportion">考试成绩比重：</label>
                        <input class="input-group-text" type="number" id="updateExamProportion" required>
                    </div>
                    <div class="list-group-item">
                        <label class="label">任课教师：</label>
                        <p class="text"><%=request.getSession().getAttribute("user")%>
                        </p>
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-outline-secondary" data-dismiss="modal">取消</button>
                <button id="updateTeachingButton" type="button" class="btn btn-outline-primary" data-dismiss="modal">
                    修改
                </button>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript">
    $(function () {
        $.ajax({
            url: '<%=request.getContextPath()%>/courseBean',
            async: true,
            type: 'post',
            success: function (data) {
                for (var i = 0; i < data.data.length; ++i) {
                    $('#selectTeaching').append('<option value="' + data.data[i].id + '">' + data.data[i].name + '</option>');
                }
            }
        })
    });

    function addTeaching() {
        $.ajax({
            url: '<%=request.getContextPath()%>/saveTeaching',
            type: 'post',
            async: true,
            data: {
                'cId': $('#selectTeaching option:checked').val(),
                'tId': '<%=request.getSession().getAttribute("ID")%>',
                'usualProportion': $('#addUsualProportion').val(),
                'examProportion': $('#addExamProportion').val()
            },
            success: function (data) {
                if (data.result === 'repeat') alert("不允许重复添加！");
                else {
                    $('#tbodyTeaching').append('<tr class="row">' +
                        '            <td class="col text-center">' +(parseInt($('#tbodyTeaching').children().length)+1)+'</td>' +
                        '            <td class="col text-center">'+$('#selectTeaching option:checked').val()+'</td>' +
                        '            <td class="col text-center">'+$('#selectTeaching option:checked').text()+'</td>'+
                        '            <td class="col text-center">'+$('#addUsualProportion').val() + '</td>' +
                        '            <td class="col text-center">'+ $('#addExamProportion').val()+ '</td>\n' +
                        '            <td class="col text-center"><%=request.getSession().getAttribute("user")%>\n' +
                        '            </td>\n' +
                        '            <td class="col text-center">\n' +
                        '                <div class="btn-group" role="group">\n' +
                        '                    <a role="button" class="btn btn-outline-info" data-toggle="modal"\n' +
                        '                       onclick="updateTeachingById(\''+data.result+'\',\''+$('#selectTeaching option:checked').val()+'\'\n' +
                        '                               ,\'<%=request.getSession().getAttribute("ID")%>\',\''+$('#selectTeaching option:checked').text()+'\'\n' +
                        '                               ,\''+$('#addUsualProportion').val()+'\',\''+ $('#addExamProportion').val()+'\',this)"\n' +
                        '                       href="#updateTeaching">修改</a>\n' +
                        '                    <a role="button" class="btn btn-outline-danger"\n' +
                        '                       onclick="deleteTeachingById(\''+data.result+'\',this)">删除</a>\n' +
                        '                </div>\n' +
                        '            </td>\n' +
                        '        </tr>');
                    alert('添加已完成！');
                }
            },
            error: function () {
                alert('添加失败！请重试！');
            }
        });
    }

    function updateTeachingById(id, cId, tId, courseName, usualProportion, examProportion, obj) {
        $('#courseName').text(courseName);
        $('#updateUsualProportion').val(usualProportion);
        $('#updateExamProportion').val(examProportion);
        $('#updateTeachingButton').on('click', function () {
            $.ajax({
                url: '<%=request.getContextPath()%>/updateTeaching',
                type: 'post',
                async: true,
                data: {
                    'id': id, 'cId': cId, 'tId': tId, 'usualProportion': $('#updateUsualProportion').val()
                    , 'examProportion': $('#updateExamProportion').val()
                },
                success: function () {
                    $(obj).parent().parent().prev().prev().prev().text($('#updateUsualProportion').val());
                    $(obj).parent().parent().prev().prev().text($('#updateExamProportion').val());
                    alert('修改已完成！');
                },
                error: function () {
                    alert("修改失败！请重试！");
                }
            });
        });
    }

    function deleteTeachingById(id, obj) {
        if (confirm("确定删除？")) {
            $.ajax({
                url: '<%=request.getContextPath()%>/deleteTeaching!deleteTeachingById',
                type: 'post',
                async: true,
                data: {"electiveCourseId": id},
                success: function () {
                    $(obj).parent().parent().parent().remove();
                    var rows = $('#tbodyCourse').children();
                    for (var i = 0; i < rows.length; ++i) {
                        rows.eq(i).children().eq(0).text(parseInt(i) + 1);
                    }
                    alert("已删除！");
                },
                error: function () {
                    alert("删除失败！请请确定无人选课后重试！")
                }
            });
        }
    }
</script>
</body>
</html>
