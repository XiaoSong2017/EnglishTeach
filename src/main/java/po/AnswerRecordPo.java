package po;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "answer_record")
public class AnswerRecordPo {
    private int id;
    private String answer;
    private StudentPo studentBySId;
    private QuestionPo questionByQId;
    private ExaminationPaperPo examinationPaperByEId;

    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "answer", nullable = false)
    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AnswerRecordPo that = (AnswerRecordPo) o;
        return id == that.id &&
                Objects.equals(answer, that.answer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, answer);
    }

    @ManyToOne
    @JoinColumn(name = "s_id", referencedColumnName = "id", nullable = false)
    public StudentPo getStudentBySId() {
        return studentBySId;
    }

    public void setStudentBySId(StudentPo studentBySId) {
        this.studentBySId = studentBySId;
    }

    @ManyToOne
    @JoinColumn(name = "q_id", referencedColumnName = "id", nullable = false)
    public QuestionPo getQuestionByQId() {
        return questionByQId;
    }

    public void setQuestionByQId(QuestionPo questionByQId) {
        this.questionByQId = questionByQId;
    }

    @ManyToOne
    @JoinColumn(name = "e_id", referencedColumnName = "id", nullable = false)
    public ExaminationPaperPo getExaminationPaperByEId() {
        return examinationPaperByEId;
    }

    public void setExaminationPaperByEId(ExaminationPaperPo examinationPaperByEId) {
        this.examinationPaperByEId = examinationPaperByEId;
    }
}
