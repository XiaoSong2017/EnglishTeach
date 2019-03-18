package po;

import org.apache.struts2.json.annotations.JSON;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "component")
public class ComponentPo {
    private int core;
    private int questionNumber;
    private int id;
    private String title;
    private ExaminationPaperPo examinationPaperByEId;
    private ProblemPo problemByQId;

    @Basic
    @Column(name = "core", nullable = false)
    public int getCore() {
        return core;
    }

    public void setCore(int core) {
        this.core = core;
    }

    @Basic
    @Column(name = "question_number", nullable = false)
    public int getQuestionNumber() {
        return questionNumber;
    }

    public void setQuestionNumber(int questionNumber) {
        this.questionNumber = questionNumber;
    }

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "title", nullable = false, length = 255)
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ComponentPo that = (ComponentPo) o;
        return core == that.core &&
                questionNumber == that.questionNumber &&
                id == that.id &&
                Objects.equals(title, that.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(core, questionNumber, id, title);
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JSON(serialize = false)
    @JoinColumn(name = "e_id", referencedColumnName = "id", nullable = false)
    public ExaminationPaperPo getExaminationPaperByEId() {
        return examinationPaperByEId;
    }

    public void setExaminationPaperByEId(ExaminationPaperPo examinationPaperByEId) {
        this.examinationPaperByEId = examinationPaperByEId;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JSON(serialize = false)
    @JoinColumn(name = "q_id", referencedColumnName = "id", nullable = false)
    public ProblemPo getProblemByQId() {
        return problemByQId;
    }

    public void setProblemByQId(ProblemPo problemByQId) {
        this.problemByQId = problemByQId;
    }
}
