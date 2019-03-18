package po;

import org.apache.struts2.json.annotations.JSON;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "elective_course")
public class ElectiveCoursePo {
    private int id;
    private Integer usualGrade;
    private Integer examGrade;
    private Integer grade;
    private StudentPo studentBySId;
    private TeachingPo teachingByEId;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ElectiveCoursePo that = (ElectiveCoursePo) o;
        return id == that.id &&
                Objects.equals(usualGrade, that.usualGrade) &&
                Objects.equals(examGrade, that.examGrade) &&
                Objects.equals(grade, that.grade);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, usualGrade, examGrade, grade);
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JSON(serialize = false)
    @JoinColumn(name = "s_id", referencedColumnName = "id", nullable = false)
    public StudentPo getStudentBySId() {
        return studentBySId;
    }

    public void setStudentBySId(StudentPo studentBySId) {
        this.studentBySId = studentBySId;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JSON(serialize = false)
    @JoinColumn(name = "e_id", referencedColumnName = "id", nullable = false)
    public TeachingPo getTeachingByEId() {
        return teachingByEId;
    }

    public void setTeachingByEId(TeachingPo teachingByEId) {
        this.teachingByEId = teachingByEId;
    }
}
