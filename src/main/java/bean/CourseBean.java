package bean;

import dao.CourseDao;
import org.apache.struts2.json.annotations.JSON;
import po.CoursePo;

import java.util.List;

public class CourseBean extends PageBean<CoursePo> {
    private CourseDao courseDao;

    public void setCourseDao(CourseDao courseDao) {
        this.courseDao = courseDao;
    }

    @Override
    @JSON
    public List<CoursePo> getData() {
        super.setData(courseDao.getAll(CoursePo.class));
        return super.getData();
    }
}
