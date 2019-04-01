package po;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "academy")
public class AcademyPo {
    private int id;
    private String name;

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
    @Column(name = "name", nullable = false)
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
}
