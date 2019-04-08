package po;

import org.apache.struts2.json.annotations.JSON;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "teaching")
public class TeachingPo {
    private int id;
    private double examProportion;
    private double usualProportion;
    private String cId;
    private String tId;
    private Set<ElectiveCoursePo> electiveCoursesById=new HashSet<>();
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
    @Column(name = "exam_proportion", nullable = false, precision = 0)
    public double getExamProportion() {
        return examProportion;
    }

    public void setExamProportion(double examProportion) {
        this.examProportion = examProportion;
    }

    @Basic
    @Column(name = "usual_proportion", nullable = false, precision = 0)
    public double getUsualProportion() {
        return usualProportion;
    }

    public void setUsualProportion(double usualProportion) {
        this.usualProportion = usualProportion;
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
        TeachingPo that = (TeachingPo) o;
        return id == that.id &&
                Double.compare(that.examProportion, examProportion) == 0 &&
                Double.compare(that.usualProportion, usualProportion) == 0 &&
                Objects.equals(cId, that.cId) &&
                Objects.equals(tId, that.tId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, examProportion, usualProportion, cId, tId);
    }

    @JSON(serialize = false)
    @OneToMany(mappedBy = "teachingByEId",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    public Set<ElectiveCoursePo> getElectiveCoursesById() {
        return electiveCoursesById;
    }

    public void setElectiveCoursesById(Set<ElectiveCoursePo> electiveCoursesById) {
        this.electiveCoursesById = electiveCoursesById;
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
