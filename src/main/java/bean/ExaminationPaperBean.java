package bean;

import org.apache.struts2.json.annotations.JSON;
import po.ExaminationPaperPo;
import service.ExaminationPaperService;

import java.util.List;

public class ExaminationPaperBean extends PageBean<ExaminationPaperPo> {
    private ExaminationPaperService examinationPaperService;
    private String teacherId;
    private String type;

    public void setExaminationPaperService(ExaminationPaperService examinationPaperService) {
        this.examinationPaperService = examinationPaperService;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }
    @JSON(serialize = false)
    public String getTeacherId() {
        return teacherId;
    }

    @JSON(serialize = false)
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    @JSON
    public List<ExaminationPaperPo> getData() {
        return super.getData();
    }

    @Override
    public String execute() throws Exception {
        setData(examinationPaperService.getExaminationPaperByTeacherId(teacherId,type));
        return super.execute();
    }
}
