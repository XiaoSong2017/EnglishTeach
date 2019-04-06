package action;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.json.annotations.JSON;
import po.ExaminationPaperPo;
import po.ProblemPo;
import service.ExaminationPaperService;

import java.sql.Timestamp;
import java.util.List;

import static org.hibernate.internal.HEMLogging.logger;

public class ExaminationPaperAction extends ActionSupport {
    private ExaminationPaperService examinationPaperService;
    private String examinationPaperId;
    private String resultCode;
    private ExaminationPaperPo examinationPaperPo;

    public void setExaminationPaperService(ExaminationPaperService examinationPaperService) {
        this.examinationPaperService = examinationPaperService;
    }

    public void setExaminationPaperId(String examinationPaperId) {
        this.examinationPaperId = examinationPaperId;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    public void setExaminationPaperPo(ExaminationPaperPo examinationPaperPo) {
        this.examinationPaperPo = examinationPaperPo;
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
        logger("examinationTitle:"+examinationPaperPo);
        examinationPaperService.saveExaminationPaper(examinationPaperPo);
        resultCode=SUCCESS;
        return SUCCESS;
    }
}
