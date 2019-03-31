package po;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "question")
public class QuestionPo {
    private int id;
    private String answer;
    private String content;
    private ProblemPo problemByProblem;

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
    @Column(name = "answer", nullable = false, length = 255)
    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    @Basic
    @Column(name = "content", nullable = true, length = 255)
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        QuestionPo that = (QuestionPo) o;
        return id == that.id &&
                Objects.equals(answer, that.answer) &&
                Objects.equals(content, that.content);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, answer, content);
    }

    @ManyToOne
    @JoinColumn(name = "problem", referencedColumnName = "id")
    public ProblemPo getProblemByProblem() {
        return problemByProblem;
    }

    public void setProblemByProblem(ProblemPo problemByProblem) {
        this.problemByProblem = problemByProblem;
    }
}
