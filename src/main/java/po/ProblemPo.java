package po;

import org.apache.struts2.json.annotations.JSON;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "problem")
public class ProblemPo {
    private int id;
    private String content;
    private CoursePo courseByCId;
    private TeacherPo teacherByTId;
    private TopicPo topicByType;
    private Timestamp time;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "content", nullable = true, length = -1)
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Basic
    @Column(name = "time",nullable = false)
    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProblemPo problemPo = (ProblemPo) o;
        return id == problemPo.id &&
                Objects.equals(content, problemPo.content) && Objects.equals(time, problemPo.time);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, content);
    }

    @ManyToOne
    @JoinColumn(name = "c_id", referencedColumnName = "id", nullable = false)
    public CoursePo getCourseByCId() {
        return courseByCId;
    }

    public void setCourseByCId(CoursePo courseByCId) {
        this.courseByCId = courseByCId;
    }

    @ManyToOne
    @JoinColumn(name = "t_id", referencedColumnName = "id", nullable = false)
    public TeacherPo getTeacherByTId() {
        return teacherByTId;
    }

    public void setTeacherByTId(TeacherPo teacherByTId) {
        this.teacherByTId = teacherByTId;
    }

    @ManyToOne
    @JoinColumn(name = "type", referencedColumnName = "id")
    public TopicPo getTopicByType() {
        return topicByType;
    }

    public void setTopicByType(TopicPo topicByType) {
        this.topicByType = topicByType;
    }

}
