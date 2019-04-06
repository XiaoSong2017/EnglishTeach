package po;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "topic")
public class TopicPo {
    private int id;
    private String name;
    private Collection<ProblemPo> problemsById;

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
        TopicPo topicPo = (TopicPo) o;
        return id == topicPo.id &&
                Objects.equals(name, topicPo.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @OneToMany(mappedBy = "topicByType")
    public Collection<ProblemPo> getProblemsById() {
        return problemsById;
    }

    public void setProblemsById(Collection<ProblemPo> problemsById) {
        this.problemsById = problemsById;
    }
}
