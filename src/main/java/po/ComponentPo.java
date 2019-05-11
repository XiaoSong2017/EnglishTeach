package po;

import org.apache.struts2.json.annotations.JSON;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "component")
public class ComponentPo {
    private int id;
    private float core;
    private int problemNumber;
    private String title;
    private int eId;
    private int qId;
    private ExaminationPaperPo examinationPaperByEId;
    private ProblemPo problemByQId;

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
    @Column(name = "core", nullable = false,length = 4,precision = 2)
    public float getCore() {
        return core;
    }

    public void setCore(float core) {
        this.core = core;
    }

    @Basic
    @Column(name = "problem_number", nullable = false)
    public int getProblemNumber() {
        return problemNumber;
    }

    public void setProblemNumber(int problemNumber) {
        this.problemNumber = problemNumber;
    }

    @Basic
    @Column(name = "title")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Basic
    @Column(name = "e_id", nullable = false,insertable = false,updatable = false)
    public int geteId() {
        return eId;
    }

    public void seteId(int eId) {
        this.eId = eId;
    }

    @Basic
    @Column(name = "q_id", nullable = false,insertable = false,updatable = false)
    public int getqId() {
        return qId;
    }

    public void setqId(int qId) {
        this.qId = qId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ComponentPo that = (ComponentPo) o;
        return id == that.id &&
                core == that.core &&
                problemNumber == that.problemNumber &&
                eId == that.eId &&
                qId == that.qId &&
                Objects.equals(title, that.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, core, problemNumber, title, eId, qId);
    }

    @JSON(serialize = false)
    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(name = "e_id", referencedColumnName = "id", nullable = false)
    public ExaminationPaperPo getExaminationPaperByEId() {
        return examinationPaperByEId;
    }

    public void setExaminationPaperByEId(ExaminationPaperPo examinationPaperByEId) {
        this.examinationPaperByEId = examinationPaperByEId;
    }

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "q_id", referencedColumnName = "id", nullable = false)
    public ProblemPo getProblemByQId() {
        return problemByQId;
    }

    public void setProblemByQId(ProblemPo problemByQId) {
        this.problemByQId = problemByQId;
    }
}
