package po;

import org.apache.struts2.json.annotations.JSON;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Objects;

@Entity
@Table(name = "teach_resource")
public class TeachResourcePo {
    private String id;
    private byte[] file;
    private String fileName;
    private String fileType;
    private Timestamp uploadTime;
    private int downs;
    private TeacherPo teacherByUploadUser;
    private CoursePo courseByCourse;

    @Id
    @Column(name = "id", nullable = false, length = 255)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Basic(fetch = FetchType.LAZY)
    @Lob
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
    @Column(name = "upload_time", nullable = false)
    public Timestamp getUploadTime() {
        return uploadTime;
    }

    public void setUploadTime(Timestamp uploadTime) {
        this.uploadTime = uploadTime;
    }

    @Basic
    @Column(name = "downs", nullable = false)
    public int getDowns() {
        return downs;
    }

    public void setDowns(int downs) {
        this.downs = downs;
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
                Objects.equals(uploadTime, that.uploadTime);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(id, fileName, fileType, uploadTime, downs);
        result = 31 * result + Arrays.hashCode(file);
        return result;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JSON(serialize = false)
    @JoinColumn(name = "upload_user", referencedColumnName = "id", nullable = false)
    public TeacherPo getTeacherByUploadUser() {
        return teacherByUploadUser;
    }

    public void setTeacherByUploadUser(TeacherPo teacherByUploadUser) {
        this.teacherByUploadUser = teacherByUploadUser;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JSON(serialize = false)
    @JoinColumn(name = "course", referencedColumnName = "id", nullable = false)
    public CoursePo getCourseByCourse() {
        return courseByCourse;
    }

    public void setCourseByCourse(CoursePo courseByCourse) {
        this.courseByCourse = courseByCourse;
    }
}
