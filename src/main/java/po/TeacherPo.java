package po;

import org.apache.struts2.json.annotations.JSON;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "teacher")
public class TeacherPo {
    private String id;
    private String name;
    private String password;
    private AcademyPo academyByAcademy;

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

    @Basic
    @JSON(serialize = false)
    @Column(name = "password", nullable = false, length = 25)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TeacherPo teacherPo = (TeacherPo) o;
        return Objects.equals(id, teacherPo.id) &&
                Objects.equals(name, teacherPo.name) &&
                Objects.equals(password, teacherPo.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, password);
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JSON(serialize = false)
    @JoinColumn(name = "academy", referencedColumnName = "id", nullable = false)
    public AcademyPo getAcademyByAcademy() {
        return academyByAcademy;
    }

    public void setAcademyByAcademy(AcademyPo academyByAcademy) {
        this.academyByAcademy = academyByAcademy;
    }
}
