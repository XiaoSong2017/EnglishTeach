package po;

import org.apache.struts2.json.annotations.JSON;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "student")
public class StudentPo {
    private String id;
    private String name;
    private String password;
    private ClassesPo classesByClazz;

    @Id
    @Column(name = "id", nullable = false, length = 20)
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

    @Basic
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
        StudentPo studentPo = (StudentPo) o;
        return Objects.equals(id, studentPo.id) &&
                Objects.equals(name, studentPo.name) &&
                Objects.equals(password, studentPo.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, password);
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JSON(serialize = false)
    @JoinColumn(name = "class", referencedColumnName = "id", nullable = false)
    public ClassesPo getClassesByClazz() {
        return classesByClazz;
    }

    public void setClassesByClazz(ClassesPo classesByClazz) {
        this.classesByClazz = classesByClazz;
    }
}
