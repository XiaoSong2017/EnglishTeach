package po;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "elective_course")
public class ElectiveCoursePo {
    private int id;
    private Integer examGrade;
    private Integer grade;
    private Integer usualGrade;
    private String sId;
    private int eId;
    private StudentPo studentBySId;
    private TeachingPo teachingByEId;

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
    @Column(name = "exam_grade", nullable = true)
    public Integer getExamGrade() {
        return examGrade;
    }

    public void setExamGrade(Integer examGrade) {
        this.examGrade = examGrade;
    }

    @Basic
    @Column(name = "grade", nullable = true)
    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    @Basic
    @Column(name = "usual_grade", nullable = true)
    public Integer getUsualGrade() {
        return usualGrade;
    }

    public void setUsualGrade(Integer usualGrade) {
        this.usualGrade = usualGrade;
    }

    @Basic
    @Column(name = "s_id", nullable = false, length = 20,insertable = false,updatable = false)
    public String getsId() {
        return sId;
    }

    public void setsId(String sId) {
        this.sId = sId;
    }

    @Basic
    @Column(name = "e_id", nullable = false,insertable = false,updatable = false)
    public int geteId() {
        return eId;
    }

    public void seteId(int eId) {
        this.eId = eId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ElectiveCoursePo that = (ElectiveCoursePo) o;
        return id == that.id &&
                eId == that.eId &&
                Objects.equals(examGrade, that.examGrade) &&
                Objects.equals(grade, that.grade) &&
                Objects.equals(usualGrade, that.usualGrade) &&
                Objects.equals(sId, that.sId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, examGrade, grade, usualGrade, sId, eId);
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
    @JoinColumn(name = "e_id", referencedColumnName = "id", nullable = false)
    public TeachingPo getTeachingByEId() {
        return teachingByEId;
    }

    public void setTeachingByEId(TeachingPo teachingByEId) {
        this.teachingByEId = teachingByEId;
    }
}
