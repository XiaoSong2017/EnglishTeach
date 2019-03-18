package service;

import com.sun.istack.NotNull;
import dao.CourseDao;
import dao.ElectiveCourseDao;
import dao.TeachingDao;
import org.springframework.stereotype.Service;
import po.CoursePo;
import po.TeacherPo;
import po.TeachingPo;

import java.util.List;
@Service
public class CourseService{
    private ElectiveCourseDao electiveCourseDao;
    private CourseDao courseDao;
    private TeachingDao teachingDao;

    public void setTeachingDao(TeachingDao teachingDao) {
        this.teachingDao = teachingDao;
    }

    public void setCourseDao(CourseDao courseDao) {
        this.courseDao = courseDao;
    }


    public void setElectiveCourseDao(ElectiveCourseDao electiveCourseDao) {
        this.electiveCourseDao = electiveCourseDao;
    }

    public List<CoursePo> getCoursesByStudentId(String id) {
        return electiveCourseDao.getCoursesByStudentId(id);
    }

    public List<CoursePo> getCourseByTeacherId(String id) {
        return teachingDao.getCourseByTeacherId(id);
    }

    public TeacherPo getTeacherByCourseId(String id) {
        return teachingDao.getById(TeachingPo.class, id).getTeacherByTId();
    }

    public List<CoursePo> getAll() {
        return courseDao.getAll(CoursePo.class);
    }
    public void deleteCourseById(String id){
        courseDao.delete(CoursePo.class,id);
    }

    public void updateCourseById(String id, String name) {
        CoursePo coursePo=courseDao.getById(CoursePo.class,id);
        coursePo.setName(name);
        courseDao.update(coursePo);
    }

    public  void addCourse(@NotNull String id,@NotNull String name){
        CoursePo coursePo=new CoursePo();
        coursePo.setId(id);
        coursePo.setName(name);
        courseDao.save(coursePo);
    }
}
