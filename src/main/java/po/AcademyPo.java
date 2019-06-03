package po;

import org.apache.struts2.json.annotations.JSON;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@org.hibernate.annotations.Table(appliesTo = "academy",comment="学院表")
@Table(name = "academy")
public class AcademyPo {
    private int id;
    private String name;
    private Set<SpecialtyPo> specialtiesById=new HashSet<>();
    private Set<TeacherPo> teachersById=new HashSet<>();

    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false,columnDefinition = "integer COMMENT '学院ID'")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name", nullable = false, length = 255,columnDefinition = "varchar(255) COMMENT '学院名称'")
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
    public Set<SpecialtyPo> getSpecialtiesById() {
        return specialtiesById;
    }

    public void setSpecialtiesById(Set<SpecialtyPo> specialtiesById) {
        this.specialtiesById = specialtiesById;
    }
    @JSON(serialize = false)
    @OneToMany(mappedBy = "academyByAcademy",fetch = FetchType.LAZY)
    public Set<TeacherPo> getTeachersById() {
        return teachersById;
    }

    public void setTeachersById(Set<TeacherPo> teachersById) {
        this.teachersById = teachersById;
    }
}
