package bean;

import org.apache.struts2.json.annotations.JSON;
import service.StudentLogService;
import vo.RelationShip;

import java.util.List;

import static com.opensymphony.xwork2.Action.SUCCESS;

public class StudentLogBean {
    private StudentLogService studentLogService;

    public void setStudentLogService(StudentLogService studentLogService) {
        this.studentLogService = studentLogService;
    }

    @JSON
    public List<RelationShip> getData() {
        List<RelationShip>data=studentLogService.getStudentOnlineTimeAndCore();
        return data;
    }

    public String execute() throws Exception {
        return SUCCESS;
    }
}
