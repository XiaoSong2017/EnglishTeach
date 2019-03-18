package po;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "teaching", schema = "english_teach", catalog = "")
public class TeachingPo {
    private double usualProportion;
    private double examProportion;
    private int id;
    private CoursePo courseByCId;
    private TeacherPo teacherByTId;

    @Basic
    @Column(name = "usual_proportion", nullable = false, precision = 2)
    public double getUsualProportion() {
        return usualProportion;
    }

    public void setUsualProportion(double usualProportion) {
        this.usualProportion = usualProportion;
    }

    @Basic
    @Column(name = "exam_proportion", nullable = false, precision = 2)
    public double getExamProportion() {
        return examProportion;
    }

    public void setExamProportion(double examProportion) {
        this.examProportion = examProportion;
    }

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TeachingPo that = (TeachingPo) o;
        return Double.compare(that.usualProportion, usualProportion) == 0 &&
                Double.compare(that.examProportion, examProportion) == 0 &&
                id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(usualProportion, examProportion, id);
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
