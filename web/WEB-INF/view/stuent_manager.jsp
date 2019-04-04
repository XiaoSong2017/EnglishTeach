<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page import="org.springframework.context.ApplicationContext" %>
<%@ page import="org.springframework.web.context.support.WebApplicationContextUtils" %>
<%@ page import="dao.StudentDao" %>
<%@ page import="po.StudentPo" %>
<%@ page import="service.UserService" %><%--
  Created by IntelliJ IDEA.
  User: wangsong
  Date: 2019/3/1
  Time: 11:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>学生信息管理</title>
</head>
<body>
<%
    ApplicationContext applicationContext = WebApplicationContextUtils.getWebApplicationContext(application);
%>
<div class="modal fade" id="exampleModa2" tabindex="-1" role="dialog" aria-labelledby="exampleModa2Label"
     aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered" role="document">
        <div class="modal-content">
            <s:form theme="simple" cssClass="form-control" cssErrorClass="alert-danger" method="POST" action="addStudent">
                <div class="modal-header">
                    <h5 class="modal-title" id="exampleModa2Label">添加学生</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    <div class="container-fluid form-group">
                        <div class="row">
                            <div class="col">
                                <label class="form-check-label" for="academy">学院：
                                </label>
                            </div>
                            <div class="col">
                                <select class="custom-select" id="academy" onchange="specialtyChange()" required>
                                    <option value="-1">----请选择----</option>
                                </select>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col">
                                <label class="form-check-label" for="specialty">专业：</label>
                            </div>
                            <div class="col">
                                <select class="custom-select" id="specialty" onchange="classesChange()" required>
                                    <option>----请选择----</option>
                                </select>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col">
                                <label class="form-check-label" for="classes">班级：
                                </label>
                            </div>
                            <div class="col">
                                <select class="custom-select" name="classes" id="classes" required>
                                    <option>----请选择----</option>
                                </select>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col">
                                <label class="form-check-label" for="id">学号：</label>
                            </div>
                            <div class="col">
                                <input type="number" id="id" name="id" placeholder="请输入学号！"
                                       class="form-check-input" required/>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col">
                                <label for="name" class="form-check-label">姓名：</label>
                            </div>
                            <div class="col">
                                <input type="text" id="name" name="name" placeholder="请输入姓名！"
                                       class="form-check-input" required/>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <input type="button" class="btn btn-secondary" data-dismiss="modal" value="取消"/>
                    <input type="submit" class="btn btn-primary" value="添加"/>
                </div>
            </s:form>
        </div>
    </div>
</div>
<div class="container">
    <table class="table table-striped table-hover">
        <thead class="thead-dark">
        <%--<tr class="row"></tr>--%>
        <tr class="row">
            <th class="col text-center">序号</th>
            <%--<th class="col">--%>
            <%--<label>--%>
            <%--<input type="checkbox">选项--%>
            <%--</label>--%>
            <%--</th>--%>
            <th class="col text-center">学号</th>
            <th class="col text-center">姓名</th>
            <th class="col text-center">
                操作
                <div class="btn-group" role="group">
                    <button type="button" class="btn btn-outline-primary" data-toggle="modal" data-target="#exampleModa2">
                        <img src="<%=request.getContextPath()%>/images/add_user.svg" type="svg">
                    </button>
                </div>
            </th>
        </tr>
        </thead>
        <tbody>
        <%
            UserService userService = (UserService) applicationContext.getAutowireCapableBeanFactory().getBean("userService");
            int i = 1;
            for (StudentPo studentPo : userService.getAllStudent()) {
        %>
        <tr class="row">
            <td class="col text-center"><%=i++%>
            </td>
            <td class="col text-center"><%=studentPo.getId()%>
            </td>
            <td class="col text-center"><%=studentPo.getName()%>
            </td>
            <td class="col text-center">
                <div class="btn-group" role="group">
                    <a class="btn btn-outline-info" data-toggle="modal" data-target="#editStudent"
                       onclick="editStudent('<%=studentPo.getId()%>','<%=studentPo.getName()%>','<%=studentPo.getPassword()%>')">修改</a>
                    <a class="btn btn-outline-danger" onclick="deleteStudent(<%=studentPo.getId()%>)">删除</a>
                </div>
            </td>
        </tr>
        <%}%>
        </tbody>
    </table>
</div>
<div class="modal fade" id="editStudent" tabindex="-1" role="dialog"
     aria-label="title" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="title">编辑</h5>
                <button type="button" class="colse" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <div class="container-fluid">
                    <div class="row">
                        <div class="col">
                            <label for="edit_academy">学院：</label>
                            <select class="custom-select" name="edit_academy" id="edit_academy"
                                    onchange="editAcademy()">
                                <option>----未选择----</option>
                            </select>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col">
                            <label for="edit_specialty">专业：</label>
                            <select class="custom-select" name="edit_specialty" id="edit_specialty"
                                    onchange="editSpecialty()">
                                <option>----未选择----</option>
                            </select>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col">
                            <label for="edit_classes">班级：</label>
                            <select class="custom-select" name="edit_classes" id="edit_classes">
                                <option>----未选择----</option>
                            </select>
                        </div>
                    </div>
                    <div role="row" class="row">
                        <div class="col">
                            <label for="edit_id">学号：</label>
                            <input type="number" id="edit_id" required>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col">
                            <label for="edit_name">姓名：</label>
                            <input type="text" id="edit_name" required>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col">
                            <label for="edit_password">密码：</label>
                            <input type="password" placeholder="请输入密码！" data-toggle="password" Class="form-control" id="edit_password" name="edit_password" required>
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">取消</button>
                <button type="button" class="btn btn-primary" onclick="editSave()">修改</button>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript">
    // $('#exampleModa2').on('show.bs.modal', function (event) {
    //     var button = $(event.relatedTarget); // Button that triggered the modal
    //     var recipient = button.data('whatever');// Extract info from data-* attributes
    //     // If necessary, you could initiate an AJAX request here (and then do the updating in a callback).
    //     // Update the modal's content. We'll use jQuery here, but you could use a data binding library or other methods instead.
    //     var modal = $(this);
    //     modal.find('.modal-title').text('添加' + recipient);
    //     modal.find('.modal-body input').val(recipient);
    // });
    $.ajax({
        url: '<%=request.getContextPath()%>/academyBean.action',
        type: 'post',
        async: true,
        success: function (data, textStatus) {
            for (var i = 0; i < data.data.length; ++i) {
                $('#academy').append('<option value="' + data.data[i].id + '">' + data.data[i].name + '</option>');
                $('#edit_academy').append('<option value="' + data.data[i].id + '">' + data.data[i].name + '</option>');
            }
        }
    });

    function specialtyChange() {
        $.ajax({
            url: '<%=request.getContextPath()%>/specialtyBean',
            data: {'id': $('#academy option:selected').val()},
            type: 'post',
            async: true,
            success: function (data, state) {
                $('#specialty').empty();
                $('#specialty').append('<option>----未选择----</option>');
                for (var i = 0; i < data.data.length; ++i) {
                    $('#specialty').append('<option value="' + data.data[i].id + '">' + data.data[i].name + '</option>');
                }
            }
        });
    }

    function classesChange() {
        $.ajax({
            url: '<%=request.getContextPath()%>/classesBean',
            data: {'id': $('#specialty option:selected').val()},
            type: 'post',
            async: true,
            success: function (data, state) {
                $('#classes').empty();
                $('#classes').append('<option>----未选择----</option>');
                for (var i = 0; i < data.data.length; ++i) {
                    $('#classes').append('<option value="' + data.data[i].id + '">' + data.data[i].name + '</option>');
                }
            }
        });
    }

    function deleteStudent(id) {
        if (confirm("确定删除？")) {
            location.href = 'deleteStudent.action?id=' + id;
        }
    }

    function editStudent(id, name, password) {
        $('#edit_id').val(id);
        $('#edit_name').val(name);
        $('#edit_password').val(password);
    }

    function editAcademy() {
        $.ajax({
            url: '<%=request.getContextPath()%>/specialtyBean',
            data: {'id': $('#edit_academy option:selected').val()},
            type: 'post',
            async: true,
            success: function (data, state) {
                $('#edit_specialty').empty();
                $('#edit_specialty').append('<option>----未选择----</option>');
                for (var i = 0; i < data.data.length; ++i) {
                    $('#edit_specialty').append('<option value="' + data.data[i].id + '">' + data.data[i].name + '</option>');
                }
            }
        });
    }

    function editSpecialty() {
        $.ajax({
            url: '<%=request.getContextPath()%>/classesBean',
            data: {'id': $('#edit_specialty option:selected').val()},
            type: 'post',
            async: true,
            success: function (data, state) {
                $('#edit_classes').empty();
                for (var i = 0; i < data.data.length; ++i) {
                    $('#edit_classes').append('<option value="' + data.data[i].id + '">' + data.data[i].name + '</option>');
                }
            }
        });
    }

    function editSave() {
        $.ajax({
            url: '<%=request.getContextPath()%>/updateStudent',
            data: {
                "id": $('#edit_id').val(),
                "name": $('#edit_name').val(),
                "password": $('#edit_password').val(),
                "classes": $('#edit_classes').val()
            },
            sync: false,
            type: 'post',
            success: function () {
                alert("修改成功！");
                window.location.reload();
            },
            error: function () {
                alert("修改失败！请重试！");
            }
        });
    }
</script>
</body>
</html>
