package po;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "course")
public class CoursePo {
    private String id;
    private String name;

    @Id
    @Column(name = "id", nullable = false, length = 25)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name", nullable = false, length = 25)
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
        CoursePo coursePo = (CoursePo) o;
        return Objects.equals(id, coursePo.id) &&
                Objects.equals(name, coursePo.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
