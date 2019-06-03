package action;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.json.annotations.JSON;
import service.CourseService;

public class CourseAction extends ActionSupport {
    private CourseService courseService;
    private String id;
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setCourseService(CourseService courseService) {
        this.courseService = courseService;
    }

    @Override
    public String execute() throws Exception {
        return super.execute();
    }
    @JSON
    public String deleteCourseById(){
        courseService.deleteCourseById(id);
        return SUCCESS;
    }
    @JSON
    public String updateCourseById(){
        courseService.updateCourseById(id,name);
        return SUCCESS;
    }
    @JSON
    public String addCourse(){
        courseService.addCourse(id,name);
        return SUCCESS;
    }
}
