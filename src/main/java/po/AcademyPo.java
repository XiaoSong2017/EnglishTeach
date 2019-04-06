package po;

import org.apache.struts2.json.annotations.JSON;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "academy")
public class AcademyPo {
    private int id;
    private String name;
    private Collection<SpecialtyPo> specialtiesById;
    private Collection<TeacherPo> teachersById;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AcademyPo academyPo = (AcademyPo) o;
        return id == academyPo.id &&
                Objects.equals(name, academyPo.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @JSON(serialize = false)
    @OneToMany(mappedBy = "academyByAcademy",fetch = FetchType.LAZY)
    public Collection<SpecialtyPo> getSpecialtiesById() {
        return specialtiesById;
    }

    public void setSpecialtiesById(Collection<SpecialtyPo> specialtiesById) {
        this.specialtiesById = specialtiesById;
    }
    @JSON(serialize = false)
    @OneToMany(mappedBy = "academyByAcademy",fetch = FetchType.LAZY)
    public Collection<TeacherPo> getTeachersById() {
        return teachersById;
    }

    public void setTeachersById(Collection<TeacherPo> teachersById) {
        this.teachersById = teachersById;
    }
}
