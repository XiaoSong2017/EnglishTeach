package po;

import org.apache.struts2.json.annotations.JSON;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "specialty")
public class SpecialtyPo {
    private int id;
    private String name;
    private AcademyPo academyByAcademy;

    @Id
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
        SpecialtyPo that = (SpecialtyPo) o;
        return id == that.id &&
                Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
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
