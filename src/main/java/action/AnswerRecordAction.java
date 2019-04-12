package action;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import dao.AnswerRecordDao;
import dao.QuestionDao;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.json.annotations.JSON;
import org.springframework.test.web.client.ResponseActions;
import service.AnswerRecordService;

public class AnswerRecordAction extends ActionSupport {
    private String resultCode;
    private int[] questionById;
    private String[] content;
    private String studentById;
    private int examinationById;
    private AnswerRecordService answerRecordService;

    public int[] getQuestionById() {
        return questionById;
    }

    public void setQuestionById(int[] questionById) {
        this.questionById = questionById;
    }

    public String[] getContent() {
        return content;
    }

    public void setContent(String[] content) {
        this.content = content;
    }

    public String getStudentById() {
        return studentById;
    }

    public void setStudentById(String studentById) {
        this.studentById = studentById;
    }

    public int getExaminationById() {
        return examinationById;
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
        resultCode=SUCCESS;
        answerRecordService.saveAnswerRecord(examinationById,questionById,content,studentById);
        ServletActionContext.getResponse().getWriter().write("<script>Swal.fire({text: \"已提交！\", type: 'success'});</script>");
        return SUCCESS;
    }
}
