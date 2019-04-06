package po;

import org.apache.struts2.json.annotations.JSON;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "student")
public class StudentPo {
    private String id;
    private String name;
    private String password;
    private int clazz;
    private Collection<AnswerRecordPo> answerRecordsById;
    private Collection<ElectiveCoursePo> electiveCoursesById;
    private ClassesPo classesByClazz;
    private Collection<StudentLogPo> studentLogsById;

    @Id
    @GeneratedValue
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

    @Basic
    @Column(name = "class", nullable = false,insertable = false,updatable = false)
    public int getClazz() {
        return clazz;
    }

    public void setClazz(int clazz) {
        this.clazz = clazz;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StudentPo studentPo = (StudentPo) o;
        return clazz == studentPo.clazz &&
                Objects.equals(id, studentPo.id) &&
                Objects.equals(name, studentPo.name) &&
                Objects.equals(password, studentPo.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, password, clazz);
    }

    @JSON(serialize = false)
    @OneToMany(mappedBy = "studentBySId",fetch = FetchType.LAZY)
    public Collection<AnswerRecordPo> getAnswerRecordsById() {
        return answerRecordsById;
    }

    public void setAnswerRecordsById(Collection<AnswerRecordPo> answerRecordsById) {
        this.answerRecordsById = answerRecordsById;
    }

    @JSON(serialize = false)
    @OneToMany(mappedBy = "studentBySId",fetch = FetchType.LAZY)
    public Collection<ElectiveCoursePo> getElectiveCoursesById() {
        return electiveCoursesById;
    }

    public void setElectiveCoursesById(Collection<ElectiveCoursePo> electiveCoursesById) {
        this.electiveCoursesById = electiveCoursesById;
    }

    @ManyToOne
    @JoinColumn(name = "class", referencedColumnName = "id", nullable = false)
    public ClassesPo getClassesByClazz() {
        return classesByClazz;
    }

    public void setClassesByClazz(ClassesPo classesByClazz) {
        this.classesByClazz = classesByClazz;
    }

    @JSON(serialize = false)
    @OneToMany(mappedBy = "studentBySId",fetch = FetchType.LAZY)
    public Collection<StudentLogPo> getStudentLogsById() {
        return studentLogsById;
    }

    public void setStudentLogsById(Collection<StudentLogPo> studentLogsById) {
        this.studentLogsById = studentLogsById;
    }
}
