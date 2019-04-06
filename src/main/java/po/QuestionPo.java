package po;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "question")
public class QuestionPo {
    private int id;
    private String answer;
    private String content;
    private Integer problem;
    private Collection<AnswerRecordPo> answerRecordsById;
    private Collection<OptionsPo> optionsById;
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

    @Basic
    @Column(name = "problem", nullable = true,insertable = false,updatable = false)
    public Integer getProblem() {
        return problem;
    }

    public void setProblem(Integer problem) {
        this.problem = problem;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        QuestionPo that = (QuestionPo) o;
        return id == that.id &&
                Objects.equals(answer, that.answer) &&
                Objects.equals(content, that.content) &&
                Objects.equals(problem, that.problem);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, answer, content, problem);
    }

    @OneToMany(mappedBy = "questionByQId")
    public Collection<AnswerRecordPo> getAnswerRecordsById() {
        return answerRecordsById;
    }

    public void setAnswerRecordsById(Collection<AnswerRecordPo> answerRecordsById) {
        this.answerRecordsById = answerRecordsById;
    }

    @OneToMany(mappedBy = "questionByQuestion")
    public Collection<OptionsPo> getOptionsById() {
        return optionsById;
    }

    public void setOptionsById(Collection<OptionsPo> optionsById) {
        this.optionsById = optionsById;
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
