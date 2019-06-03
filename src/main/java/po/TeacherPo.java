package po;

import org.apache.struts2.json.annotations.JSON;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "teacher")
public class TeacherPo {
    private String id;
    private String name;
    private String password;
    private int academy;
    private Set<ExaminationPaperPo> examinationPapersById=new HashSet<>();
    private Set<ProblemPo> problemsById=new HashSet<>();
    private Set<TeachResourcePo> teachResourcesById=new HashSet<>();
    private AcademyPo academyByAcademy;
    private Set<TeachingPo> teachingsById=new HashSet<>();

    @Id
    @Column(name = "id", nullable = false, length = 15)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name", nullable = false, length = 20)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @JSON(serialize = false)
    @Basic(fetch = FetchType.LAZY)
    @Column(name = "password", nullable = false, length = 25)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    @Column(name = "academy", nullable = false,insertable = false,updatable = false)
    public int getAcademy() {
        return academy;
    }

    public void setAcademy(int academy) {
        this.academy = academy;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TeacherPo teacherPo = (TeacherPo) o;
        return academy == teacherPo.academy &&
                Objects.equals(id, teacherPo.id) &&
                Objects.equals(name, teacherPo.name) &&
                Objects.equals(password, teacherPo.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, password, academy);
    }

    @JSON(serialize = false)
    @OneToMany(mappedBy = "teacherByTId",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    public Set<ExaminationPaperPo> getExaminationPapersById() {
        return examinationPapersById;
    }

    public void setExaminationPapersById(Set<ExaminationPaperPo> examinationPapersById) {
        this.examinationPapersById = examinationPapersById;
    }

    @JSON(serialize = false)
    @OneToMany(mappedBy = "teacherByTId",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    public Set<ProblemPo> getProblemsById() {
        return problemsById;
    }

    public void setProblemsById(Set<ProblemPo> problemsById) {
        this.problemsById = problemsById;
    }

    @JSON(serialize = false)
    @OneToMany(mappedBy = "teacherByUploadUser",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    public Set<TeachResourcePo> getTeachResourcesById() {
        return teachResourcesById;
    }

    public void setTeachResourcesById(Set<TeachResourcePo> teachResourcesById) {
        this.teachResourcesById = teachResourcesById;
    }

    @JSON(serialize = false)
    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(name = "academy", referencedColumnName = "id", nullable = false)
    public AcademyPo getAcademyByAcademy() {
        return academyByAcademy;
    }

    public void setAcademyByAcademy(AcademyPo academyByAcademy) {
        this.academyByAcademy = academyByAcademy;
    }

    @JSON(serialize = false)
    @OneToMany(mappedBy = "teacherByTId",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    public Set<TeachingPo> getTeachingsById() {
        return teachingsById;
    }

    public void setTeachingsById(Set<TeachingPo> teachingsById) {
        this.teachingsById = teachingsById;
    }
}
