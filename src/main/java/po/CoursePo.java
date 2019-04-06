package po;

import org.apache.struts2.json.annotations.JSON;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "course")
public class CoursePo {
    private String id;
    private String name;
    private Collection<ExaminationPaperPo> examinationPapersById;
    private Collection<ProblemPo> problemsById;
    private Collection<TeachResourcePo> teachResourcesById;
    private Collection<TeachingPo> teachingsById;

    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false, length = 25)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name", nullable = false, length = 25)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CoursePo coursePo = (CoursePo) o;
        return Objects.equals(id, coursePo.id) &&
                Objects.equals(name, coursePo.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @JSON(serialize = false)
    @OneToMany(mappedBy = "courseByCId",fetch = FetchType.LAZY)
    public Collection<ExaminationPaperPo> getExaminationPapersById() {
        return examinationPapersById;
    }

    public void setExaminationPapersById(Collection<ExaminationPaperPo> examinationPapersById) {
        this.examinationPapersById = examinationPapersById;
    }

    @JSON(serialize = false)
    @OneToMany(mappedBy = "courseByCId",fetch = FetchType.LAZY)
    public Collection<ProblemPo> getProblemsById() {
        return problemsById;
    }

    public void setProblemsById(Collection<ProblemPo> problemsById) {
        this.problemsById = problemsById;
    }

    @JSON(serialize = false)
    @OneToMany(mappedBy = "courseByCourse",fetch = FetchType.LAZY)
    public Collection<TeachResourcePo> getTeachResourcesById() {
        return teachResourcesById;
    }

    public void setTeachResourcesById(Collection<TeachResourcePo> teachResourcesById) {
        this.teachResourcesById = teachResourcesById;
    }

    @JSON(serialize = false)
    @OneToMany(mappedBy = "courseByCId",fetch = FetchType.LAZY)
    public Collection<TeachingPo> getTeachingsById() {
        return teachingsById;
    }

    public void setTeachingsById(Collection<TeachingPo> teachingsById) {
        this.teachingsById = teachingsById;
    }
}
