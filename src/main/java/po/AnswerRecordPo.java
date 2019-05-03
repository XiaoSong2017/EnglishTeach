package po;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "answer_record")
public class AnswerRecordPo {
    private int id;
    private String answer;
    private int eId;
    private int qId;
    private String sId;
    private float core;
    private Date time;
    private ExaminationPaperPo examinationPaperByEId;
    private QuestionPo questionByQId;
    private StudentPo studentBySId;

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

    @Basic
    @Column(name = "e_id", nullable = false)
    public int geteId() {
        return eId;
    }

    public void seteId(int eId) {
        this.eId = eId;
    }

    @Basic
    @Column(name = "q_id", nullable = false)
    public int getqId() {
        return qId;
    }

    public void setqId(int qId) {
        this.qId = qId;
    }

    @Basic
    @Column(name = "s_id", nullable = false, length = 20)
    public String getsId() {
        return sId;
    }

    public void setsId(String sId) {
        this.sId = sId;
    }

    @Basic
    @Column(name = "core",length = 4,precision = 2)
    public float getCore() {
        return core;
    }

    public void setCore(float core) {
        this.core = core;
    }

    @Basic
    @Temporal(TemporalType.DATE)
    @Column(name = "time")
    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AnswerRecordPo that = (AnswerRecordPo) o;
        return id == that.id &&
                eId == that.eId &&
                qId == that.qId &&
                Float.compare(core, that.core) == 0 &&
                Objects.equals(answer, that.answer) &&
                Objects.equals(sId, that.sId)&&
                Objects.equals(time,that.time);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, answer, eId, qId, sId, core,time);
    }

    @ManyToOne
    @JoinColumn(name = "e_id", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
    public ExaminationPaperPo getExaminationPaperByEId() {
        return examinationPaperByEId;
    }

    public void setExaminationPaperByEId(ExaminationPaperPo examinationPaperByEId) {
        this.examinationPaperByEId = examinationPaperByEId;
    }

    @ManyToOne
    @JoinColumn(name = "q_id", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
    public QuestionPo getQuestionByQId() {
        return questionByQId;
    }

    public void setQuestionByQId(QuestionPo questionByQId) {
        this.questionByQId = questionByQId;
    }

    @ManyToOne
    @JoinColumn(name = "s_id", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
    public StudentPo getStudentBySId() {
        return studentBySId;
    }

    public void setStudentBySId(StudentPo studentBySId) {
        this.studentBySId = studentBySId;
    }
}
