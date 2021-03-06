package po;

import org.apache.struts2.json.annotations.JSON;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "examination_paper")
public class ExaminationPaperPo {
    private int id;
    private Date endTime;
    private String name;
    private Date startTime;
    private boolean type;
    private String cId;
    private String tId;
    private Set<AnswerRecordPo> answerRecordsById=new HashSet<>();
    private Set<ComponentPo> componentsById=new HashSet<>();
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
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "end_time", nullable = false)
    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
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
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "start_time", nullable = false)
    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
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

    @JSON(serialize = false)
    @OneToMany(mappedBy = "examinationPaperByEId",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    public Set<AnswerRecordPo> getAnswerRecordsById() {
        return answerRecordsById;
    }

    public void setAnswerRecordsById(Set<AnswerRecordPo> answerRecordsById) {
        this.answerRecordsById = answerRecordsById;
    }

    @JSON(serialize = false)
    @OneToMany(mappedBy = "examinationPaperByEId",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    public Set<ComponentPo> getComponentsById() {
        return componentsById;
    }

    public void setComponentsById(Set<ComponentPo> componentsById) {
        this.componentsById = componentsById;
    }

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "c_id", referencedColumnName = "id", nullable = false)
    public CoursePo getCourseByCId() {
        return courseByCId;
    }

    public void setCourseByCId(CoursePo courseByCId) {
        this.courseByCId = courseByCId;
    }

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "t_id", referencedColumnName = "id", nullable = false)
    public TeacherPo getTeacherByTId() {
        return teacherByTId;
    }

    public void setTeacherByTId(TeacherPo teacherByTId) {
        this.teacherByTId = teacherByTId;
    }
}
