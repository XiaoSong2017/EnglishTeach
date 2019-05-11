package po;

import org.apache.struts2.json.annotations.JSON;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "problem")
public class ProblemPo {
    private int id;
    private String content;
    private Date time;
    private String cId;
    private String tId;
    private Integer type;
    private Set<ComponentPo> componentsById = new HashSet<>();
    private CoursePo courseByCId;
    private TeacherPo teacherByTId;
    private TopicPo topicByType;
    private Set<QuestionPo> questionsById = new HashSet<>();

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
    @Column(name = "content", nullable = true, length = 255)
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Basic
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "time", nullable = false)
    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    @Basic
    @Column(name = "c_id", nullable = false, length = 25, insertable = false, updatable = false)
    public String getcId() {
        return cId;
    }

    public void setcId(String cId) {
        this.cId = cId;
    }

    @Basic
    @Column(name = "t_id", nullable = false, length = 15, insertable = false, updatable = false)
    public String gettId() {
        return tId;
    }

    public void settId(String tId) {
        this.tId = tId;
    }

    @Basic
    @Column(name = "type", nullable = true, insertable = false, updatable = false)
    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProblemPo problemPo = (ProblemPo) o;
        return id == problemPo.id &&
                Objects.equals(content, problemPo.content) &&
                Objects.equals(time, problemPo.time) &&
                Objects.equals(cId, problemPo.cId) &&
                Objects.equals(tId, problemPo.tId) &&
                Objects.equals(type, problemPo.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, content, time, cId, tId, type);
    }

    @JSON(serialize = false)
    @OneToMany(mappedBy = "problemByQId", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    public Set<ComponentPo> getComponentsById() {
        return componentsById;
    }

    public void setComponentsById(Set<ComponentPo> componentsById) {
        this.componentsById = componentsById;
    }

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "c_id", referencedColumnName = "id", nullable = false)
    public CoursePo getCourseByCId() {
        return courseByCId;
    }

    public void setCourseByCId(CoursePo courseByCId) {
        this.courseByCId = courseByCId;
    }

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "t_id", referencedColumnName = "id", nullable = false)
    public TeacherPo getTeacherByTId() {
        return teacherByTId;
    }

    public void setTeacherByTId(TeacherPo teacherByTId) {
        this.teacherByTId = teacherByTId;
    }

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "type", referencedColumnName = "id")
    public TopicPo getTopicByType() {
        return topicByType;
    }

    public void setTopicByType(TopicPo topicByType) {
        this.topicByType = topicByType;
    }

    @OneToMany(mappedBy = "problemByProblem", cascade = CascadeType.ALL)
    public Set<QuestionPo> getQuestionsById() {
        return questionsById;
    }

    public void setQuestionsById(Set<QuestionPo> questionsById) {
        this.questionsById = questionsById;
    }
}
