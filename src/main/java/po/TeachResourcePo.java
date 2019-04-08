package po;

import org.apache.struts2.json.annotations.JSON;
import org.hibernate.annotations.GenerationTime;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "teach_resource")
public class TeachResourcePo {
    private String id;
    private int downs;
    private byte[] file;
    private String fileName;
    private String fileType;
    private Date uploadTime;
    private String course;
    private String uploadUser;
    private CoursePo courseByCourse;
    private TeacherPo teacherByUploadUser;

    @Id
    @GenericGenerator(name = "resourceById",strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", nullable = false, length = 255)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Basic
    @Column(name = "downs", nullable = false)
    public int getDowns() {
        return downs;
    }

    public void setDowns(int downs) {
        this.downs = downs;
    }

    @Basic(fetch = FetchType.LAZY)
    @Lob
    @JSON(serialize = false)
    @Column(name = "file", nullable = false)
    public byte[] getFile() {
        return file;
    }

    public void setFile(byte[] file) {
        this.file = file;
    }

    @Basic
    @Column(name = "file_name", nullable = false, length = 255)
    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    @Basic
    @Column(name = "file_type", nullable = false, length = 255)
    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    @Basic
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "upload_time", nullable = false)
    public Date getUploadTime() {
        return uploadTime;
    }

    public void setUploadTime(Date uploadTime) {
        this.uploadTime = uploadTime;
    }

    @Basic
    @Column(name = "course", nullable = false, length = 25,insertable = false,updatable = false)
    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    @Basic
    @Column(name = "upload_user", nullable = false, length = 15,insertable = false,updatable = false)
    public String getUploadUser() {
        return uploadUser;
    }

    public void setUploadUser(String uploadUser) {
        this.uploadUser = uploadUser;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TeachResourcePo that = (TeachResourcePo) o;
        return downs == that.downs &&
                Objects.equals(id, that.id) &&
                Arrays.equals(file, that.file) &&
                Objects.equals(fileName, that.fileName) &&
                Objects.equals(fileType, that.fileType) &&
                Objects.equals(uploadTime, that.uploadTime) &&
                Objects.equals(course, that.course) &&
                Objects.equals(uploadUser, that.uploadUser);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(id, downs, fileName, fileType, uploadTime, course, uploadUser);
        result = 31 * result + Arrays.hashCode(file);
        return result;
    }

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "course", referencedColumnName = "id", nullable = false)
    public CoursePo getCourseByCourse() {
        return courseByCourse;
    }

    public void setCourseByCourse(CoursePo courseByCourse) {
        this.courseByCourse = courseByCourse;
    }

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "upload_user", referencedColumnName = "id", nullable = false)
    public TeacherPo getTeacherByUploadUser() {
        return teacherByUploadUser;
    }

    public void setTeacherByUploadUser(TeacherPo teacherByUploadUser) {
        this.teacherByUploadUser = teacherByUploadUser;
    }
}
