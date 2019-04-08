package po;

import org.apache.struts2.json.annotations.JSON;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "classes")
public class ClassesPo {
    private int id;
    private String name;
    private int specialty;
    private SpecialtyPo specialtyBySpecialty;
    private Set<StudentPo> studentsById=new HashSet<>();

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
    @Column(name = "specialty", nullable = false,insertable = false,updatable = false)
    public int getSpecialty() {
        return specialty;
    }

    public void setSpecialty(int specialty) {
        this.specialty = specialty;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClassesPo classesPo = (ClassesPo) o;
        return id == classesPo.id &&
                specialty == classesPo.specialty &&
                Objects.equals(name, classesPo.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, specialty);
    }

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "specialty", referencedColumnName = "id", nullable = false)
    public SpecialtyPo getSpecialtyBySpecialty() {
        return specialtyBySpecialty;
    }

    public void setSpecialtyBySpecialty(SpecialtyPo specialtyBySpecialty) {
        this.specialtyBySpecialty = specialtyBySpecialty;
    }

    @JSON(serialize = false)
    @OneToMany(mappedBy = "classesByClazz",fetch = FetchType.LAZY)
    public Set<StudentPo> getStudentsById() {
        return studentsById;
    }

    public void setStudentsById(Set<StudentPo> studentsById) {
        this.studentsById = studentsById;
    }
}
