package action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import service.UserService;

public class StudentAction extends ActionSupport {
    private String classes;
    private String name;
    private String id;
    private UserService userService;
    private String password;
    private String courseById;

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getClasses() {
        return classes;
    }

    public void setClasses(String classes) {
        this.classes = classes;
    }

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

    public void setCourseById(String courseById) {
        this.courseById = courseById;
    }

    public String addStudent() throws Exception{
        userService.addStudent(id,name,classes);
        return SUCCESS;
    }

    public String deleteStudent(){
        userService.deleteStudent(id);
        return SUCCESS;
    }

    public String updateStudent(){
        userService.updateStudent(id,name,classes,password);
        return SUCCESS;
    }

    public String studentLogin()throws Exception{
        ActionContext.getContext().getSession().put("courseById",courseById);
        return SUCCESS;
    }
}
