package action;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.json.annotations.JSON;
import service.ExaminationPaperService;

public class ExaminationPaperAction extends ActionSupport {
    private ExaminationPaperService examinationPaperService;
    private String examinationPaperId;
    private String resultCode;

    public void setExaminationPaperService(ExaminationPaperService examinationPaperService) {
        this.examinationPaperService = examinationPaperService;
    }

    public void setExaminationPaperId(String examinationPaperId) {
        this.examinationPaperId = examinationPaperId;
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
}
