package po;

import org.hibernate.annotations.GenerationTime;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "student_log")
public class StudentLogPo {
    private long id;
    private Timestamp loginTime;
    private Timestamp logoutTime;
    private String sId;
    private StudentPo studentBySId;

    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @org.hibernate.annotations.Generated(value = GenerationTime.ALWAYS)
    @Column(name = "login_time", nullable = false)
    public Timestamp getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(Timestamp loginTime) {
        this.loginTime = loginTime;
    }

    @Basic
    @org.hibernate.annotations.Generated(value = GenerationTime.ALWAYS)
    @Column(name = "logout_time", nullable = false)
    public Timestamp getLogoutTime() {
        return logoutTime;
    }

    public void setLogoutTime(Timestamp logoutTime) {
        this.logoutTime = logoutTime;
    }

    @Basic
    @Column(name = "s_id", nullable = false, length = 20,insertable = false,updatable = false)
    public String getsId() {
        return sId;
    }

    public void setsId(String sId) {
        this.sId = sId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StudentLogPo that = (StudentLogPo) o;
        return id == that.id &&
                Objects.equals(loginTime, that.loginTime) &&
                Objects.equals(logoutTime, that.logoutTime) &&
                Objects.equals(sId, that.sId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, loginTime, logoutTime, sId);
    }

    @ManyToOne
    @JoinColumn(name = "s_id", referencedColumnName = "id", nullable = false)
    public StudentPo getStudentBySId() {
        return studentBySId;
    }

    public void setStudentBySId(StudentPo studentBySId) {
        this.studentBySId = studentBySId;
    }
}
