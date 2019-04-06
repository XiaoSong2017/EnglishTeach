package po;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "examination_paper")
public class ExaminationPaperPo {
    private int id;
    private Timestamp endTime;
    private String name;
    private Timestamp startTime;
    private boolean type;
    private String cId;
    private String tId;
    private Collection<AnswerRecordPo> answerRecordsById;
    private Collection<ComponentPo> componentsById;
    private CoursePo courseByCId;
    private TeacherPo teacherByTId;

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
    @Column(name = "end_time", nullable = false)
    public Timestamp getEndTime() {
        return endTime;
    }

    public void setEndTime(Timestamp endTime) {
        this.endTime = endTime;
    }

    @Basic
    @Column(name = "name", nullable = false, length = 255)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "start_time", nullable = false)
    public Timestamp getStartTime() {
        return startTime;
    }

    public void setStartTime(Timestamp startTime) {
        this.startTime = startTime;
    }

    @Basic
    @Column(name = "type", nullable = false)
    public boolean isType() {
        return type;
    }

    public void setType(boolean type) {
        this.type = type;
    }

    @Basic
    @Column(name = "c_id", nullable = false, length = 25,insertable = false,updatable = false)
    public String getcId() {
        return cId;
    }

    public void setcId(String cId) {
        this.cId = cId;
    }

    @Basic
    @Column(name = "t_id", nullable = false, length = 15,insertable = false,updatable = false)
    public String gettId() {
        return tId;
    }

    public void settId(String tId) {
        this.tId = tId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ExaminationPaperPo that = (ExaminationPaperPo) o;
        return id == that.id &&
                type == that.type &&
                Objects.equals(endTime, that.endTime) &&
                Objects.equals(name, that.name) &&
                Objects.equals(startTime, that.startTime) &&
                Objects.equals(cId, that.cId) &&
                Objects.equals(tId, that.tId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, endTime, name, startTime, type, cId, tId);
    }

    @OneToMany(mappedBy = "examinationPaperByEId")
    public Collection<AnswerRecordPo> getAnswerRecordsById() {
        return answerRecordsById;
    }

    public void setAnswerRecordsById(Collection<AnswerRecordPo> answerRecordsById) {
        this.answerRecordsById = answerRecordsById;
    }

    @OneToMany(mappedBy = "examinationPaperByEId")
    public Collection<ComponentPo> getComponentsById() {
        return componentsById;
    }

    public void setComponentsById(Collection<ComponentPo> componentsById) {
        this.componentsById = componentsById;
    }

    @ManyToOne
    @JoinColumn(name = "c_id", referencedColumnName = "id", nullable = false)
    public CoursePo getCourseByCId() {
        return courseByCId;
    }

    public void setCourseByCId(CoursePo courseByCId) {
        this.courseByCId = courseByCId;
    }

    @ManyToOne
    @JoinColumn(name = "t_id", referencedColumnName = "id", nullable = false)
    public TeacherPo getTeacherByTId() {
        return teacherByTId;
    }

    public void setTeacherByTId(TeacherPo teacherByTId) {
        this.teacherByTId = teacherByTId;
    }
}
