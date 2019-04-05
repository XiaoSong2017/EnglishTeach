<%@ page import="org.springframework.context.ApplicationContext" %>
<%@ page import="org.springframework.web.context.support.WebApplicationContextUtils" %>
<%@ page import="service.ElectiveCourseService" %>
<%@ page import="po.ElectiveCoursePo" %><%--
  Created by IntelliJ IDEA.
  User: wangsong
  Date: 2019/3/3
  Time: 18:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>选课管理</title>
</head>
<body>
<div class="modal fade" tabindex="-1" role="dialog" id="electiveCourseAdd"
     aria-labelledby="electiveCourseAddTitle" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="electiveCourseAddTitle">添加选课</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <div class="container-fluid">
                    <ul class="list-group">
                        <li class="list-group-item list-group-item-action">
                            <label class="label" for="tree">选择学生： </label>
                            <ul class="ztree" id="tree" aria-required="true"></ul>
                        </li>
                        <li class="list-group-item">
                            <label class="label" for="selectElectiveCourse">选择课程：</label>
                            <select class="custom-select" id="selectElectiveCourse" required>
                                <option>--未选择--</option>
                            </select>
                        </li>
                    </ul>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-outline-secondary" data-dismiss="modal">取消</button>
                <button type="button" class="btn btn-outline-info" onclick="addElectiveCourse()" data-dismiss="modal">添加</button>
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
            <th class="col text-center">学号</th>
            <th class="col text-center">选课学生</th>
            <th class="col text-center">
                操作
                <div class="btn-group" role="group">
                    <a role="button" shape="circle" class="btn btn-outline-info" data-toggle="modal"
                       href="#electiveCourseAdd">
                        <img role="img" class="img-fluid" src="<%=request.getContextPath()%>/images/add_course.svg"
                             type="svg" alt="img">
                    </a>
                </div>
            </th>
        </tr>
        </thead>
        <tbody id="tbody_elective_course">
        <%
            ApplicationContext applicationContext = WebApplicationContextUtils.getWebApplicationContext(application);
            ElectiveCourseService electiveCourseService = (ElectiveCourseService) applicationContext.getAutowireCapableBeanFactory().getBean("electiveCourseService");
            int i = 1;
            for (ElectiveCoursePo electiveCoursePo : electiveCourseService.getAll()) {
        %>
        <tr class="row">
            <td class="col text-center"><%=i++%>
            </td>
            <td class="col text-center"><%=electiveCoursePo.getTeachingByEId().getCourseByCId().getId()%>
            </td>
            <td class="col text-center"><%=electiveCoursePo.getTeachingByEId().getCourseByCId().getName()%>
            </td>
            <td class="col text-center"><%=electiveCoursePo.getStudentBySId().getId()%>
            </td>
            <td class="col text-center"><%=electiveCoursePo.getStudentBySId().getName()%>
            </td>
            <td class="col text-center">
                <div class="btn-group" role="group">
                    <a class="btn btn-outline-info" onclick="updateElectiveCourse('<%=electiveCoursePo.getId()%>',
                            '<%=electiveCoursePo.getStudentBySId().getId()%>','<%=electiveCoursePo.getStudentBySId().getName()%>',this)"
                       data-toggle="modal" href="#updateElectiveCourse">修改</a>
                    <a class="btn btn-outline-danger"
                       onclick="deleteElectiveCourseById('<%=electiveCoursePo.getId()%>',this)">删除</a>
                </div>
            </td>
        </tr>
        <%}%>
        </tbody>
    </table>
</div>
<div class="modal fade" id="updateElectiveCourse" tabindex="-1" role="dialog"
     aria-labelledby="updateElectiveCourseTitle"
     aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="updateElectiveCourseTitle">修改选课信息</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <%--<div class="container-fluid list-group">--%>
                <%--<div class="list-group-item">--%>
                <%--<label class="label">课程名：</label>--%>
                <%--<p class="text" id="updateElectiveCourseCourseName"></p>--%>
                <%--</div>--%>
                <%--<div class="list-group-item">--%>
                <%--<label class="label" for="updateElectiveCourseUsualGrade">平时成绩：</label>--%>
                <%--<input class="input-group-text" type="number" id="updateElectiveCourseUsualGrade" required>--%>
                <%--</div>--%>
                <%--<div class="list-group-item">--%>
                <%--<label class="label" for="updateElectiveCourseExamGrade">考试成绩：</label>--%>
                <%--<input class="input-group-text" type="number" id="updateElectiveCourseExamGrade" required>--%>
                <%--</div>--%>
                <%--<div class="list-group-item">--%>
                <%--<label class="label" for="updateElectiveCourseGrade">总体成绩：</label>--%>
                <%--<input class="input-group-text" type="number" id="updateElectiveCourseGrade" required>--%>
                <%--</div>--%>
                <%--</div>--%>
                <div class="list-group">
                    <div class="list-group-item">
                        <label class="col-form-label" for="updateSelectElectiveCourse">选择课程：</label>
                        <select class="custom-select" id="updateSelectElectiveCourse" required>
                            <option>--未选择--</option>
                        </select>
                    </div>
                    <div class="list-group-item">
                        <label class="col-form-label" for="updateStudentName">学生：</label>
                        <p class="text" id="updateStudentName"></p>
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-outline-secondary" data-dismiss="modal">取消</button>
                <button type="button" id="updateElectiveCourseButton" class="btn btn-outline-primary"
                        data-dismiss="modal">修改
                </button>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript">
    var obj;
    var leveles = ['academy', 'specialty', 'classes', 'student'];
    var setting = {
        view: {
            selectedMulti: true,
            showIcon: true,
            showTitle: true
        },
        data: {
            simpleData: {
                enable: true
            }
        },
        check: {
            enable: true
        },
        callback: {
            onClick: function (event, treeId, treeNode, clickFlag) {
                // console.log('clickFlag:' + clickFlag);
                if (!treeNode.isParent && treeNode.level <= 2) {
                    $.ajax({
                        url: '<%=request.getContextPath()%>/' + leveles[treeNode.level + 1] + 'Bean',
                        type: 'post',
                        async: true,
                        data: {id: treeNode.id},
                        success: function (data) {
                            obj.addNodes(treeNode, data.data);
                        },
                        error: function (data) {
                            alert('加载失败！');
                        }
                    });
                }
            }
        }
    };
    function addElectiveCourse(){
        var id=[];
        var levels=[];
        var temp=obj.getCheckedNodes(true);
        for(var i=0,j=0;i<temp.length;++i){
            if(!temp[i].isParent){
                id.push(temp[i].id);
                levels.push(temp[i].level);
                ++j;
            }
        }
        var data=$.param({'id':id},true);
        data+='&'+$.param({'level':levels},true);
        data+='&teacherId='+'<%=request.getSession().getAttribute("ID")%>'+'&courseId='+$('#selectElectiveCourse option:checked').val();
        //console.log(data);
        $.ajax({
            url:'<%=request.getContextPath()%>/saveElectiveCourseBatchBy',
            type:'post',
            async:true,
            dataType:'json',
            data:data,
            success:function (data) {
                $.ajax({
                   url:'<%=request.getContextPath()%>/electiveCourseBean',
                    type:'post',
                    async:true,
                    data:{'teacherId':<%=request.getSession().getAttribute("ID")%>},
                    success:function (data) {
                        $('#tbody_elective_course').empty();
                        for(var i=0;i<data.data.length;++i){
                            $('#tbody_elective_course').append('<tr class="row">\n' +
                                '            <td class="col text-center">'+(i+1)+
                                '            </td>\n' +
                                '            <td class="col text-center">'+data.data[i].teachingByEId.id+<%--<%=electiveCoursePo.getTeachingByEId().getCourseByCId().getId()%>--%>
                                '            </td>\n' +
                                '            <td class="col text-center">'+data.data[i].teachingByEId.courseByCId.name+'<%--<%=electiveCoursePo.getTeachingByEId().getCourseByCId().getName()%>--%>' +
                                '            </td>\n' +
                                '            <td class="col text-center">'+data.data[i].studentBySId.id+'<%--<%=electiveCoursePo.getStudentBySId().getId()%>--%>' +
                                '            </td>\n' +
                                '            <td class="col text-center">'+data.data[i].studentBySId.name+'<%--<%=electiveCoursePo.getStudentBySId().getName()%>--%>' +
                                '            </td>\n' +
                                '            <td class="col text-center">\n' +
                                '                <div class="btn-group" role="group">\n' +
                                '                    <a class="btn btn-outline-info" onclick="updateElectiveCourse(\''+data.data[i].id+'<%--<%=electiveCoursePo.getId()%>--%>\',\n' +
                                '                            \''+data.data[i].studentBySId.id+'<%--<%=electiveCoursePo.getStudentBySId().getId()%>--%>\',\''+data.data[i].studentBySId.name+'<%--<%=electiveCoursePo.getStudentBySId().getName()%>--%>\',this)"\n' +
                                '                       data-toggle="modal" href="#updateElectiveCourse">修改</a>\n' +
                                '                    <a class="btn btn-outline-danger"\n' +
                                '                       onclick="deleteElectiveCourseById(\''+data.data[i].id+'<%--<%=electiveCoursePo.getId()%>--%>\',this)">删除</a>\n' +
                                '                </div>\n' +
                                '            </td>\n' +
                                '        </tr>');
                        }
                        alert("添加已完成！");
                    },
                    error:function () {
                        alert("加载失败！请重试！");
                    }
                });
            },
            error:function () {
                alert("添加出错！请重试！");
            }
        });
    }

    $(function () {
        $.ajax({
            url: '<%=request.getContextPath()%>/academyBean',
            type: 'post',
            async: true,
            success: function (data) {
                obj = $.fn.zTree.init($("#tree"), setting, data.data);
            }
        });
        $.ajax({
            url: '<%=request.getContextPath()%>/courseBean',
            async: true,
            type: 'post',
            success: function (data) {
                for (var i = 0; i < data.data.length; ++i) {
                    $('#selectElectiveCourse').append('<option value="' + data.data[i].id + '">' + data.data[i].name + '</option>');
                    $('#updateSelectElectiveCourse').append('<option value="' + data.data[i].id + '">' + data.data[i].name + '</option>');
                }
            }
        });
    });

    function updateElectiveCourse(id, sId, name, obj) {
        $('#updateStudentName').text(name);
        $('#updateElectiveCourseButton').on('click', function () {
            $.ajax({
                url: '<%=request.getContextPath()%>/updateElectiveCourse',
                type: 'post',
                async: true,
                data: {
                    'electiveCourseId': id, 'studentId': sId, 'teacherId':<%=request.getSession().getAttribute("ID")%>,
                    'courseId': $('#updateSelectElectiveCourse option:checked').val()
                },
                success: function (data) {
                    if(data===true){
                        obj.parent().parent().prev().prev().prev().text($('#updateSelectElectiveCourse').text());
                        obj.parent().parent().prev().prev().prev().prev().text($('#updateSelectElectiveCourse').val());
                        alert('修改已完成！')
                    }else {
                        alert('您未教授此课，不能完成修改！');
                    }
                },
                error: function () {
                    alert("修改失败！请重试！");
                }
            });
        });
    }

    function deleteElectiveCourseById(id, obj) {
        if (confirm("确定删除？")) {
            $.ajax({
                url: '<%=request.getContextPath()%>/deleteElectiveCourse!deleteElectiveCourseById',
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
