package action;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.json.annotations.JSON;
import service.ExaminationPaperService;

import java.sql.Timestamp;

public class ExaminationPaperAction extends ActionSupport {
    private ExaminationPaperService examinationPaperService;
    private String examinationPaperId;
    private String resultCode;
    private String courseById;
    private String examinationTitle;
    private Timestamp start_time;
    private Timestamp end_time;

    public void setExaminationPaperService(ExaminationPaperService examinationPaperService) {
        this.examinationPaperService = examinationPaperService;
    }

    public void setExaminationPaperId(String examinationPaperId) {
        this.examinationPaperId = examinationPaperId;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    public void setCourseById(String courseById) {
        this.courseById = courseById;
    }

    public void setExaminationTitle(String examinationTitle) {
        this.examinationTitle = examinationTitle;
    }

    public void setStart_time(Timestamp start_time) {
        this.start_time = start_time;
    }

    public void setEnd_time(Timestamp end_time) {
        this.end_time = end_time;
    }

    @JSON
    public String getResultCode() {
        return resultCode;
    }

    @Override
    public String execute() throws Exception {
        return super.execute();
    }

    public String deleteExaminationPaperById() throws Exception {
        examinationPaperService.deleteExaminationPaperById(examinationPaperId);
        resultCode = SUCCESS;
        return SUCCESS;
    }

    public String saveExaminationPager()throws Exception{

        resultCode=SUCCESS;
        return resultCode;
    }
}
