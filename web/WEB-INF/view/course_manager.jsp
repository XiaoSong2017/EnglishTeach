<%@ page import="org.springframework.context.ApplicationContext" %>
<%@ page import="org.springframework.web.context.support.WebApplicationContextUtils" %>
<%@ page import="po.CoursePo" %>
<%@ page import="service.CourseService" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
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
    <title>课程管理</title>
</head>
<body>
<div class="modal hide fade" id="ModalCourse" tabindex="-1" role="dialog" aria-labelledby="ModalCourseLabel"
     aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="ModalCourseLabel">添加课程</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <form class="form-group">
                <div class="modal-body">
                    <div class="container-fluid form-group">
                        <div class="row">
                            <div class="col">
                                <label class="form-check-label" for="course_id">课程号：</label>
                            </div>
                            <div class="col">
                                <input type="number" class="form-control-plaintext" id="course_id" name="course_id"
                                       placeholder="请输入课程号！" min="100" minlength="2" required>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col">
                                <label for="course_name" class="form-check-label">课程名：</label>
                            </div>
                            <div class="col">
                                <input type="text" class="form-control-plaintext" id="course_name" name="course_name"
                                       placeholder="请输入课程名！" minlength="2" required>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col"><label class="form-check-label">添加人：</label></div>
                            <div class="col"><p><%=request.getSession().getAttribute("user")%>
                            </p></div>
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <input type="button" class="btn btn-secondary" data-dismiss="modal" value="取消"/>
                    <input type="submit" class="btn btn-primary" onclick="addCourse()" data-dismiss="modal" value="添加"/>
                </div>
            </form>
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
            <th class="col text-center">操作
                <div class="btn-group" role="group">
                    <button type="button" class="btn btn-outline-primary" data-toggle="modal"
                            data-target="#ModalCourse">
                        <img role="img" class="img-fluid" src="<%=request.getContextPath()%>/images/add_course.svg"
                             type="svg">
                    </button>
                </div>
            </th>
        </tr>
        </thead>
        <tbody id="tbodyCourse" class="popover-body">
        <%
            ApplicationContext applicationContext = WebApplicationContextUtils.getWebApplicationContext(application);
            CourseService courseService = (CourseService) applicationContext.getAutowireCapableBeanFactory().getBean("courseService");
            int i = 1;
            for (CoursePo coursePo : courseService.getAll()) {
        %>
        <tr class="row">
            <td class="col text-center"><%=i++%>
            </td>
            <td class="col text-center"><%=coursePo.getId()%>
            </td>
            <td class="col text-center"><%=coursePo.getName()%>
            </td>
            <td class="col text-center">
                <div class="btn-group" role="group">
                    <a class="btn btn-outline-info" role="button" data-toggle="modal"
                       onclick="udateCourse('<%=coursePo.getId()%>','<%=coursePo.getName()%>',$(this).parent().parent().prev())"
                       href="#ModalUpdateCourse">修改</a>
                    <a class="btn btn-outline-danger" role="button"
                       onclick="deleteCourseById('<%=coursePo.getId()%>',this)">删除</a>
                </div>
            </td>
        </tr>
        <%}%>
        </tbody>
    </table>
</div>
<div class="modal hide fade" id="ModalUpdateCourse" tabindex="-1" role="dialog" aria-labelledby="update_course_title"
     aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="update_course_title">修改课程</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <div class="form-group">
                    <label class="col-form-label" for="update_course_id">课程号：</label>
                    <p id="update_course_id" class="form-control"></p>
                </div>
                <div class="form-group">
                    <label class="col-form-label" for="update_course_name">课程名：</label>
                    <input id="update_course_name" class="form-control" type="text" required>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">取消</button>
                <button id="update_button" type="button" class="btn btn-primary" data-dismiss="modal"
                        data-hiden="modal">修改
                </button>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript">
    // $('#update_course').on('show.bs.modal', function (event) {
    //     var button = $(event.relatedTarget) // Button that triggered the modal
    //     // Extract info from data-* attributes
    //     // If necessary, you could initiate an AJAX request here (and then do the updating in a callback).
    //     // Update the modal's content. We'll use jQuery here, but you could use a data binding library or other methods instead.
    //     var modal = $(this)
    //     modal.find('.modal-body input').val(recipient)
    //     $('#update_course_id').val(id);
    //     $('#update_course_name').val(obj.value)
    // });

    function udateCourse(id, name, obj) {
        $('#update_course_id').text(id);
        $('#update_course_name').val(name);
        // console.log(obj.parent());
        $('#update_button').on('click', function () {
            $.ajax({
                url: '<%=request.getContextPath()%>/updateCourse!updateCourseById',
                type: 'post',
                async: true,
                data: {'id': id, 'name': $('#update_course_name').val()},
                success: function () {
                    console.log(obj);
                    obj.text($('#update_course_name').val());
                    alert("修改已完成！")
                },
                error: function () {
                    alert('操作有误！请重试！');
                }
            });
        })
    }

    function deleteCourseById(id, obj) {
        if (confirm("确定删除？")) {
            $.ajax({
                url: '<%=request.getContextPath()%>/deleteCourse!deleteCourseById',
                type: 'post',
                async: true,
                data: {"id": id},
                success: function () {
                    $(obj).parent().parent().parent().remove();
                    var rows = $('#tbodyCourse').children();
                    for (var i = 0; i < rows.length; ++i) {
                        rows.eq(i).children().eq(0).text(parseInt(i) + 1);
                    }
                    Swal.fire("已删除！",'','info');
                },
                error: function () {
                    Swal.fire("删除失败！请请确定无人选课后重试！",'','warning');
                }
            });
        }
    }

    function isEmpty(obj) {
        if (typeof obj === 'undefined' || obj == null || obj === '') {
            return true;
        } else {
            return false;
        }
    }

    function addCourse() {
        if (!isEmpty($('#course_id').val()) && !isEmpty($('#course_name').val())) {
            $.ajax({
                url: '<%=request.getContextPath()%>/addCourse',
                type: 'post',
                async: 'true',
                data: {"id": $('#course_id').val(), "name": $('#course_name').val()},
                success: function () {
                    $('#tbodyCourse').append('<tr class="row">' +
                        '<td class="col text-center">' + (parseInt($('#tbodyCourse').children().length )+ 1) + '</td>' +
                        '<td class="col text-center">' + $('#course_id').val() + '</td>' +
                        '<td class="col text-center">' + $('#course_name').val() + '</td>' +
                        '<td class="col text-center">' +
                        '<div class="btn-group" role="group">' +
                        '<a class="btn btn-outline-info" role="button" data-toggle="modal"' +
                        'onclick="udateCourse(\'' + $('#course_id').val() + '\',\'' + $('#course_name').val() + '\',$(this).parent().parent().prev())"' +
                        'href="#ModalUpdateCourse">修改</a>' +
                        '<a class="btn btn-outline-danger" role="button"' +
                        'onclick="deleteCourseById(\'' + $('#course_id').val() + '\',this)">删除</a>' +
                        '</div>' +
                        '</td>' +
                        '</tr>');
                    Swal.fire('添加成功！','','success');
                },
                error: function () {
                    Swal.fire('添加失败！请重试！','','error');
                }
            });
        } else {
            Swal.fire('输入有误！请重试！','','error');
        }
    }
</script>
</body>
</html>
