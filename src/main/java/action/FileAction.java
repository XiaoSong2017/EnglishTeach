package action;

import bean.PageBean;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import dao.TeacherDao;
import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.json.annotations.JSON;
import po.TeachResourcePo;
import service.FileService;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.UUID;

public class FileAction extends ActionSupport {
    private String filesFileName;
    private String filesContentType;
    private String id;
    private TeacherDao teacherDao;
    private File files;
    private TeachResourcePo teachResourcePo;
    private FileService fileService;
    private String courseId;
    private long pageNumber;

    public long getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(long pageNumber) {
        this.pageNumber = pageNumber;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFilesFileName() {
        return filesFileName;
    }

    public void setFilesFileName(String filesFileName) {
        this.filesFileName = filesFileName;
    }

    public String getFilesContentType() {
        return filesContentType;
    }

    public void setFilesContentType(String filesContentType) {
        this.filesContentType = filesContentType;
    }

    public File getFiles() {
        return files;
    }

    public void setFiles(File files) {
        this.files = files;
    }

    public void setFileService(FileService fileService) {
        this.fileService = fileService;
    }

    public TeacherDao getTeacherDao() {
        return teacherDao;
    }

    public void setTeacherDao(TeacherDao teacherDao) {
        this.teacherDao = teacherDao;
    }

    public String deleteFile() throws Exception {
        fileService.deleteFileById(id);
        return SUCCESS;
    }

    public String setUploadFile() throws Exception {
        HttpServletResponse response=ServletActionContext.getResponse();
        response.setCharacterEncoding("UTF-8");
        //PrintWriter printWriter=response.getWriter();
        if (fileService.uploadFile(filesFileName, filesContentType, files, courseId)) {
            //printWriter.write("<script>alert('上传成功！');</script>");
            return SUCCESS;
        } else {
            //printWriter.write("<script>alert('上传失败！请重试！');</script>");
            return ERROR;
        }
    }

    public InputStream getDownloadFile() throws Exception {
        InputStream inputStream = FileUtils.openInputStream(files);
        files.deleteOnExit();
        return inputStream;
    }

    @Override
    public String execute() throws Exception {
        teachResourcePo = fileService.downloadFile(id);
        filesFileName = new String(teachResourcePo.getFileName().getBytes(), "ISO8859-1");
        files = File.createTempFile(String.valueOf(UUID.randomUUID()), filesFileName.substring(filesFileName.indexOf(".")));
        FileUtils.writeByteArrayToFile(files, teachResourcePo.getFile());
        return SUCCESS;
    }
    @JSON
    public PageBean<TeachResourcePo> getTeacherResourceListByUserId(){
        return fileService.getFilesByUser(1,10, String.valueOf(ActionContext.getContext().getSession().get("ID")));
    }
}
