package action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import dao.StudentLogDao;
import service.StudentLogService;
import service.UserService;

import java.sql.Timestamp;

public class UserAction extends ActionSupport {
    private String account;
    private String password;
    private int type;
    private StudentLogService studentLogService;

    public void setStudentLogService(StudentLogService studentLogService) {
        this.studentLogService = studentLogService;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    private String target;
    private UserService userService;

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public String login() throws Exception{
        if (getType()==1) {
            /*
             * 教师登录处理*/
            if (userService.validTeacherLogin(account, password)) {
                target = "teacher";
                ActionContext.getContext().getSession().put("ID", userService.getTeacherPo().getId());
                ActionContext.getContext().getSession().put("user", userService.getTeacherPo().getName());
                ActionContext.getContext().getSession().put("userType",target);
                return SUCCESS;
            }
        } else {
            /*
             * 学生登录处理*/
            if (userService.validStudentLogin(account, password)) {
                target = "select_course";
                ActionContext.getContext().getSession().put("ID", userService.getStudentPo().getId());
                ActionContext.getContext().getSession().put("user", userService.getStudentPo().getName());
                ActionContext.getContext().getSession().put("userType","student");
                ActionContext.getContext().getSession().put("LogId",studentLogService.save(new Timestamp(System.
                        currentTimeMillis()),new Timestamp(System.currentTimeMillis()),account ));
                return SUCCESS;
            }
        }
        addActionMessage("用户输入有误！请重新输入！");
        return INPUT;
    }
    public String logout() throws Exception{
        if(ActionContext.getContext().getSession().get("user")==null)return ERROR;
        if(ActionContext.getContext().getSession().get("userType").equals("student")){
            studentLogService.updateById(new Timestamp(System.currentTimeMillis()), (long)ActionContext.getContext().getSession().get("LogId"));
        }
        ActionContext.getContext().getSession().clear();
        return SUCCESS;
    }

}
