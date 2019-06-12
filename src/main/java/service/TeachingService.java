package service;

import dao.CourseDao;
import dao.TeacherDao;
import dao.TeachingDao;
import org.apache.struts2.json.annotations.JSON;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import po.CoursePo;
import po.TeacherPo;
import po.TeachingPo;

import java.util.List;

@Service
public class TeachingService {
    private TeachingDao teachingDao;
    private CourseDao courseDao;
    private TeacherDao teacherDao;

    public TeachingDao getTeachingDao() {
        return teachingDao;
    }

    public CourseDao getCourseDao() {
        return courseDao;
    }

    public void setCourseDao(CourseDao courseDao) {
        this.courseDao = courseDao;
    }

    public TeacherDao getTeacherDao() {
        return teacherDao;
    }

    public void setTeacherDao(TeacherDao teacherDao) {
        this.teacherDao = teacherDao;
    }

    public void setTeachingDao(TeachingDao teachingDao) {
        this.teachingDao = teachingDao;
    }

    private static void setTeaching(String cId, String tId, String usualProportion, String examProportion, TeachingPo teachingPo, CourseDao courseDao, TeacherDao teacherDao) {
        teachingPo.setExamProportion(Double.valueOf(examProportion));
        teachingPo.setUsualProportion(Double.valueOf(usualProportion));
        teachingPo.setCourseByCId(courseDao.getById(CoursePo.class, cId));
        teachingPo.setTeacherByTId(teacherDao.getById(TeacherPo.class, tId));
    }

    @Transactional(readOnly = true)
    public List<CoursePo> getCourseByTeacherId(String id) {
        return teachingDao.getCourseByTeacherId(id);
    }

    @Transactional(readOnly = true)
    public List<TeachingPo> getAll() {
        return teachingDao.getAll(TeachingPo.class);
    }

    @Transactional
    public void deleteTeachingById(int electiveCourseId) {
        teachingDao.delete(TeachingPo.class, electiveCourseId);
    }

    @Transactional
    public void saveTeaching(String cId, String tId, String usualProportion, String examProportion) {
        TeachingPo teachingPo = new TeachingPo();
        setTeaching(cId, tId, usualProportion, examProportion, teachingPo, courseDao, teacherDao);
        teachingDao.save(teachingPo);
    }

    @Transactional
    public void updateTeachingById(String id, String cId, String tId, String usualProportion, String examProportion) {
        TeachingPo teachingPo = teachingDao.getById(TeachingPo.class, Integer.valueOf(id));
        setTeaching(cId, tId, usualProportion, examProportion, teachingPo, courseDao, teacherDao);
        teachingDao.update(teachingPo);
    }

    @Transactional(readOnly = true)
    public TeachingPo getTeachingByCourseIdAndTeacherId(String teacherId, String courseId) {
        return teachingDao.getTeachingByCourseIdAndTeacherId(teacherId, courseId);
    }

    @Transactional(readOnly = true)
    public long getCount() {
        return teachingDao.count(TeachingPo.class);
    }
}
