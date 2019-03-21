<%@ page import="bean.PageBean" %>
<%@ page import="org.springframework.context.ApplicationContext" %>
<%@ page import="org.springframework.web.context.support.WebApplicationContextUtils" %>
<%@ page import="po.CoursePo" %>
<%@ page import="po.TeachResourcePo" %>
<%@ page import="service.FileService" %>
<%@ page import="service.CourseService" %>
<%@ page import="java.util.List" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%--
  Created by IntelliJ IDEA.
  User: wangsong
  Date: 2019/2/19
  Time: 13:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>教学资源</title>
</head>
<body>
<%
    ApplicationContext applicationContext = WebApplicationContextUtils.getWebApplicationContext(application);
    CourseService courseService = (CourseService) applicationContext.getAutowireCapableBeanFactory().getBean("courseService");
    List<CoursePo> coursePos = courseService.getCourseByTeacherId(String.valueOf(request.getSession().getAttribute("ID")));
%>
<div class="modal hide fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
     aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <s:form cssClass="form-control-file" cssErrorClass="alert-danger" method="POST"
                    enctype="multipart/form-data" action="upload!setUploadFile">
                <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLabel">上传文件</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    <div class="form-group">
                        <label class="col-form-label" for="courseId">教学资源对应课程名：</label>
                        <select id="courseId" name="courseId" class="form-control custom-select" required>
                            <%
                                for (CoursePo coursePo : coursePos) {
                            %>
                            <option value="<%=coursePo.getId()%>"><%=coursePo.getName()%>
                            </option>
                            <%}%>
                        </select>
                        <label for="files" class="col-form-label">上传文件路径：</label>
                        <input type="file" id="files" name="files" Class="form-control-file" required/>
                    </div>
                </div>
                <div class="modal-footer">
                    <input type="button" class="btn btn-secondary" data-dismiss="modal" value="取消"/>
                    <input type="submit" class="btn btn-primary" value="上传"/>
                </div>
            </s:form>
        </div>
    </div>
</div>
<div class="container">
    <table class="table table-striped table-hover">
        <thead class="thead-dark">
        <tr class="row">
            <th class="col text-center">条目</th>
            <th class="col text-center">文件名</th>
            <th class="col text-center">文件类型</th>
            <th class="col text-center">上传时间</th>
            <th class="col text-center">下载量</th>
            <th class="col text-center">上传人</th>
            <th class="col text-center">操作
                <a role="button" class="btn btn-outline-info" data-toggle="modal" href="#exampleModal"
                   data-whatever="文件">
                    <img src="<%=request.getContextPath()%>/images/add_course.svg" type="svg" alt="">
                </a>
            </th>
        </tr>
        </thead>
        <tbody id="tbodyTeacherResource"></tbody>
        <tfoot>
        <tr class="row">
            <td class="col text-center">
                <nav>
                    <ul class="pagination justify-content-center" id="pagination1"></ul>
                </nav>
            </td>
        </tr>
        </tfoot>
    </table>
</div>
<script type="text/javascript">
    $(function () {
        $.ajax({
            url: '<%=request.getContextPath()%>/teachResourceBean',
            datatype: 'json',
            async: true,
            type: 'post',
            success: function (data) {
                for (var i = 0; i < data.data.length; ++i) {
                    $('#tbodyTeacherResource').append('<tr class="row">\n' +
                        '            <td class="col text-center">'+(i+1)+
                        '            </td>\n' +
                        '            <td class="col text-center"><a\n' +
                        '                    href="download.action?id='+data.data[i].id+'">'+data.data[i].fileName+
                        '            </a></td>\n' +
                        '            <td class="col text-center">'+data.data[i].fileType +
                        '            </td>\n' +
                        '            <td class="col text-center">'+data.data[i].uploadTime +
                        '            </td>\n' +
                        '            <td class="col text-center">'+data.data[i].downs +
                        '            </td>\n' +
                        '            <td class="col text-center">'+data.data[i].teacherByUploadUser.name +
                        '            </td>\n' +
                        '            <td class="col text-center">\n' +
                        '                <div class="btn-group" role="group">\n' +
                        '                    <a href="download.action?id='+data.data[i].id+'" class="btn btn-outline-info" role="button"\n' +
                        '                       aria-pressed="true">下载</a>\n' +
                        '                    <a onclick="deleteResource(\''+data.data[i].id+'\')" class="btn '+(data.data[i].teacherByUploadUser.name==='<%=request.getSession().getAttribute("user")%>'?'btn-outline-danger':'btn-outline-light text-dark disabled')+'" role="button"\n' +
                        '                       aria-pressed="true">删除</a>\n' +
                        '                </div>\n' +
                        '            </td>\n' +
                        '        </tr>');
                }
            },
            error: function () {
                alert("加载失败！请刷新页面重试！");
            }
        });
    });
    // $('#tbody')._clear();
    $.jqPaginator('#pagination1', {
        totalPages: 100,
        visiblePages: 3,
        currentPage: 1,
        onPageChange: function (num, type) {
            if (type ==='change') {
                //这里是点击分页的回调
            }
        }
    });
    // $('#exampleModal').on('show.bs.modal', function (event) {
    //     var button = $(event.relatedTarget); // Button that triggered the modal
    //     var recipient = button.data('whatever');// Extract info from data-* attributes
    //     // If necessary, you could initiate an AJAX request here (and then do the updating in a callback).
    //     // Update the modal's content. We'll use jQuery here, but you could use a data binding library or other methods instead.
    //     var modal = $(this);
    //     modal.find('.modal-title').text('上传' + recipient);
    //     modal.find('.modal-body input').val(recipient);
    // });

    function deleteResource(id) {
        if (confirm("确认删除？")) {
            location.href = 'deleteFile.action?id=' + id;
        }
    }
</script>
</body>
</html>
