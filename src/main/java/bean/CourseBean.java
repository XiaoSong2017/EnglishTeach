package bean;

import dao.CourseDao;
import dao.ElectiveCourseDao;
import org.apache.struts2.json.annotations.JSON;
import po.CoursePo;

import java.util.List;

public class CourseBean extends PageBean<CoursePo> {
    private CourseDao courseDao;
    private String studentById;
    private ElectiveCourseDao electiveCourseDao;

    public void setCourseDao(CourseDao courseDao) {
        this.courseDao = courseDao;
    }

    public void setElectiveCourseDao(ElectiveCourseDao electiveCourseDao) {
        this.electiveCourseDao = electiveCourseDao;
    }

    public void setStudentById(String studentById) {
        this.studentById = studentById;
    }

    @Override
    @JSON
    public List<CoursePo> getData() {
        return super.getData();
    }

    @Override
    public String execute() throws Exception {
        if(studentById==null)
        super.setData(courseDao.getAll(CoursePo.class));
        else super.setData(electiveCourseDao.getCoursesByStudentId(studentById));
        studentById=null;
        return super.execute();
    }
}
