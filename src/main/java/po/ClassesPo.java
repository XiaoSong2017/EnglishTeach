package po;

import org.apache.struts2.json.annotations.JSON;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "classes")
public class ClassesPo {
    private int id;
    private String name;
    private SpecialtyPo specialtyBySpecialty;

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
        ClassesPo classesPo = (ClassesPo) o;
        return id == classesPo.id &&
                Objects.equals(name, classesPo.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JSON(serialize = false)
    @JoinColumn(name = "specialty", referencedColumnName = "id", nullable = false)
    public SpecialtyPo getSpecialtyBySpecialty() {
        return specialtyBySpecialty;
    }

    public void setSpecialtyBySpecialty(SpecialtyPo specialtyBySpecialty) {
        this.specialtyBySpecialty = specialtyBySpecialty;
    }
}
