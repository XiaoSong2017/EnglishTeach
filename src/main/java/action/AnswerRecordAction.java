package action;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.json.annotations.JSON;
import service.AnswerRecordService;

import java.util.List;

public class AnswerRecordAction extends ActionSupport {
    private String resultCode;
    private List<String> questionById;
    private List<String> content;
    private String studentById;
    private int examinationById;
    private AnswerRecordService answerRecordService;

    public void setQuestionById(List<String> questionById) {
        this.questionById = questionById;
    }

    public void setContent(List<String> content) {
        this.content = content;
    }

    public void setStudentById(String studentById) {
        this.studentById = studentById;
    }


    public void setExaminationById(int examinationById) {
        this.examinationById = examinationById;
    }

    public void setAnswerRecordService(AnswerRecordService answerRecordService) {
        this.answerRecordService = answerRecordService;
    }

    @JSON
    public String getResultCode() {
        return resultCode;
    }

    public String saveAnswerRecord() throws Exception {
        answerRecordService.saveAnswerRecord(examinationById,questionById,content,studentById);
        //ServletActionContext.getResponse().getWriter().write("<script>Swal.fire({text: \"已提交！\", type: 'success'});</script>");
        resultCode=SUCCESS;
        return SUCCESS;
    }
}
