package po;

import org.apache.struts2.json.annotations.JSON;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "specialty")
public class SpecialtyPo {
    private int id;
    private String name;
    private int academy;
    private Set<ClassesPo> classesById=new HashSet<>() ;
    private AcademyPo academyByAcademy;

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
    @Column(name = "name", nullable = false, length = 255)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
        SpecialtyPo that = (SpecialtyPo) o;
        return id == that.id &&
                academy == that.academy &&
                Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, academy);
    }

    @JSON(serialize = false)
    @OneToMany(mappedBy = "specialtyBySpecialty",fetch = FetchType.LAZY)
    public Set<ClassesPo> getClassesById() {
        return classesById;
    }

    public void setClassesById(Set<ClassesPo> classesById) {
        this.classesById = classesById;
    }

    @ManyToOne
    @JoinColumn(name = "academy", referencedColumnName = "id", nullable = false)
    public AcademyPo getAcademyByAcademy() {
        return academyByAcademy;
    }

    public void setAcademyByAcademy(AcademyPo academyByAcademy) {
        this.academyByAcademy = academyByAcademy;
    }
}
