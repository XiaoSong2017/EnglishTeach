package po;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "teacher")
public class TeacherPo {
    private String id;
    private String name;
    private String password;
    private int academy;
    private Collection<ExaminationPaperPo> examinationPapersById;
    private Collection<ProblemPo> problemsById;
    private Collection<TeachResourcePo> teachResourcesById;
    private AcademyPo academyByAcademy;
    private Collection<TeachingPo> teachingsById;

    @Id
    @GeneratedValue
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

    @Basic
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

    @OneToMany(mappedBy = "teacherByTId")
    public Collection<ExaminationPaperPo> getExaminationPapersById() {
        return examinationPapersById;
    }

    public void setExaminationPapersById(Collection<ExaminationPaperPo> examinationPapersById) {
        this.examinationPapersById = examinationPapersById;
    }

    @OneToMany(mappedBy = "teacherByTId")
    public Collection<ProblemPo> getProblemsById() {
        return problemsById;
    }

    public void setProblemsById(Collection<ProblemPo> problemsById) {
        this.problemsById = problemsById;
    }

    @OneToMany(mappedBy = "teacherByUploadUser")
    public Collection<TeachResourcePo> getTeachResourcesById() {
        return teachResourcesById;
    }

    public void setTeachResourcesById(Collection<TeachResourcePo> teachResourcesById) {
        this.teachResourcesById = teachResourcesById;
    }

    @ManyToOne
    @JoinColumn(name = "academy", referencedColumnName = "id", nullable = false)
    public AcademyPo getAcademyByAcademy() {
        return academyByAcademy;
    }

    public void setAcademyByAcademy(AcademyPo academyByAcademy) {
        this.academyByAcademy = academyByAcademy;
    }

    @OneToMany(mappedBy = "teacherByTId")
    public Collection<TeachingPo> getTeachingsById() {
        return teachingsById;
    }

    public void setTeachingsById(Collection<TeachingPo> teachingsById) {
        this.teachingsById = teachingsById;
    }
}
