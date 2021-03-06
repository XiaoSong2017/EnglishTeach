package action;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import org.apache.struts2.json.annotations.JSON;
import po.ExaminationPaperPo;
import po.ProblemPo;
import service.ExaminationPaperService;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import static org.hibernate.internal.HEMLogging.logger;

public class ExaminationPaperAction extends ActionSupport{
    private ExaminationPaperService examinationPaperService;
    private String examinationPaperId;
    private String resultCode;
    private ExaminationPaperPo examinationPaperPo;
    private int id;
    private Map<String,Object> list;
    public void setExaminationPaperService(ExaminationPaperService examinationPaperService) {
        this.examinationPaperService = examinationPaperService;
    }

    public void setExaminationPaperId(String examinationPaperId) {
        this.examinationPaperId = examinationPaperId;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    @JSON
    public String getResultCode() {
        return resultCode;
    }

    public void setId(int id) {
        this.id = id;
    }

    @JSON
    public Map<String, Object> getList() {
        return list;
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

    public void setExaminationPaperPo(ExaminationPaperPo examinationPaperPo) {
        this.examinationPaperPo = examinationPaperPo;
    }

    public String saveExaminationPager()throws Exception{
        logger("examinationTitle:"+examinationPaperPo);
        examinationPaperService.saveExaminationPaper(examinationPaperPo);
        resultCode=SUCCESS;
        return SUCCESS;
    }

    public String getExaminationPaperDetailById()throws Exception{
        list=examinationPaperService.getExaminationPaperDetailById(id);
        resultCode=SUCCESS;
        return SUCCESS;
    }

    public String updateExaminationPager()throws Exception{
        examinationPaperService.updateExaminationPaper(examinationPaperPo);
        return resultCode=SUCCESS;
    }
}
