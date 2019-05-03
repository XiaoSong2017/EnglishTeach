package bean;

import org.apache.struts2.json.annotations.JSON;
import po.SubjectiveAnswerRecordPo;
import service.AnswerRecordService;

import java.util.List;

public class SubjectiveAnswerRecordBean extends PageBean<SubjectiveAnswerRecordPo> {
    private AnswerRecordService answerRecordService;

    public void setAnswerRecordService(AnswerRecordService answerRecordService) {
        this.answerRecordService = answerRecordService;
    }

    @Override
    @JSON
    public List<SubjectiveAnswerRecordPo> getData() {
        return super.getData();
    }

    @Override
    public String execute() throws Exception {
        setData(answerRecordService.getSubjectiveAnswerRecords());
        return super.execute();
    }
}
