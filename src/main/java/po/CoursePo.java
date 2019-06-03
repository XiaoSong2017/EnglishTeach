package po;

import org.apache.struts2.json.annotations.JSON;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "course")
public class CoursePo {
    private String id;
    private String name;
    private Set<ExaminationPaperPo> examinationPapersById=new HashSet<>();
    private Set<ProblemPo> problemsById=new HashSet<>();
    private Set<TeachResourcePo> teachResourcesById=new HashSet<>();
    private Set<TeachingPo> teachingsById=new HashSet<>();

    @Id
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
    @OneToMany(mappedBy = "courseByCId",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    public Set<ExaminationPaperPo> getExaminationPapersById() {
        return examinationPapersById;
    }

    public void setExaminationPapersById(Set<ExaminationPaperPo> examinationPapersById) {
        this.examinationPapersById = examinationPapersById;
    }

    @JSON(serialize = false)
    @OneToMany(mappedBy = "courseByCId",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    public Set<ProblemPo> getProblemsById() {
        return problemsById;
    }

    public void setProblemsById(Set<ProblemPo> problemsById) {
        this.problemsById = problemsById;
    }

    @JSON(serialize = false)
    @OneToMany(mappedBy = "courseByCourse",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    public Set<TeachResourcePo> getTeachResourcesById() {
        return teachResourcesById;
    }

    public void setTeachResourcesById(Set<TeachResourcePo> teachResourcesById) {
        this.teachResourcesById = teachResourcesById;
    }

    @JSON(serialize = false)
    @OneToMany(mappedBy = "courseByCId",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    public Set<TeachingPo> getTeachingsById() {
        return teachingsById;
    }

    public void setTeachingsById(Set<TeachingPo> teachingsById) {
        this.teachingsById = teachingsById;
    }
}
