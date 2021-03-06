package service;

import bean.PageBean;
import com.opensymphony.xwork2.ActionContext;
import dao.CourseDao;
import dao.FilesDao;
import dao.TeacherDao;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import po.CoursePo;
import po.TeachResourcePo;
import po.TeacherPo;

import java.io.*;
import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

@Service
public class FileService {
    private FilesDao filesDao;
    private TeacherDao teacherDao;
    private CourseDao courseDao;

    public CourseDao getCourseDao() {
        return courseDao;
    }

    public void setCourseDao(CourseDao courseDao) {
        this.courseDao = courseDao;
    }

    public void setFilesDao(FilesDao filesDao) {
        this.filesDao = filesDao;
    }

    public void setTeacherDao(TeacherDao teacherDao) {
        this.teacherDao = teacherDao;
    }

    @Transactional
    public TeachResourcePo downloadFile(String id) {
        TeachResourcePo teachResourcePo = filesDao.getById(TeachResourcePo.class, id);
        teachResourcePo.setDowns(teachResourcePo.getDowns() + 1);
        filesDao.save(teachResourcePo);
        return teachResourcePo;
    }

    @Transactional
    public boolean uploadFile(String fileName, String contentType, File file, String courseId) {
        TeachResourcePo teachResourcePo = new TeachResourcePo();
        String userId = String.valueOf(ActionContext.getContext().getSession().getOrDefault("ID", null));
        if (!userId.isEmpty()) {
            try {
                TeacherPo teacherPo = teacherDao.getById(TeacherPo.class, userId);
                teachResourcePo.setFileName(fileName);
                teachResourcePo.setFileType(contentType);
                teachResourcePo.setCourseByCourse(courseDao.getById(CoursePo.class, courseId));
                teachResourcePo.setTeacherByUploadUser(teacherPo);
                teachResourcePo.setFile(FileUtils.readFileToByteArray(file));
                teachResourcePo.setUploadTime(new Timestamp(System.currentTimeMillis()));
                teachResourcePo.setId(UUID.randomUUID().toString());
                teachResourcePo.setDowns(0);
                filesDao.save(teachResourcePo);
                return true;
            } catch (IOException e) {
                return false;
            }
        } else return false;
    }

    @Transactional(readOnly = true)
    public PageBean<TeachResourcePo> getFilesByUser(int pageNumber, int pageSize, String id) {
        return filesDao.getByUser(TeachResourcePo.class, pageNumber,pageSize, id);
    }

    @Transactional
    public void deleteFileById(String id) {
        filesDao.delete(TeachResourcePo.class, id);
    }

    @Transactional(readOnly = true)
    public List<TeachResourcePo> getFiles() {
        return filesDao.getAll(TeachResourcePo.class);
    }

    @Transactional(readOnly = true)
    public List<TeachResourcePo> getFilesByCourseId(String courseId){
        return filesDao.getFilesByCourseId(courseId);
    }
}
