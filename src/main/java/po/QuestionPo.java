package po;

import org.apache.struts2.json.annotations.JSON;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "question")
public class QuestionPo {
    private int id;
    private String answer;
    private String content;
    private Integer problem;
    private int questionNumber;
    private Set<AnswerRecordPo> answerRecordsById=new HashSet<>() ;
    private Set<OptionsPo> optionsById=new HashSet<>();
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

    @Basic
    @Column(name = "question_number", nullable = true)
    public int getQuestionNumber() {
        return questionNumber;
    }

    public void setQuestionNumber(int questionNumber) {
        this.questionNumber = questionNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        QuestionPo that = (QuestionPo) o;
        return id == that.id &&
                Objects.equals(answer, that.answer) &&
                Objects.equals(content, that.content) &&
                Objects.equals(problem, that.problem)&&
                Objects.equals(questionNumber,that.questionNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, answer, content, problem,questionNumber);
    }

    @JSON(serialize = false)
    @OneToMany(mappedBy = "questionByQId",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    public Set<AnswerRecordPo> getAnswerRecordsById() {
        return answerRecordsById;
    }

    public void setAnswerRecordsById(Set<AnswerRecordPo> answerRecordsById) {
        this.answerRecordsById = answerRecordsById;
    }

    @OneToMany(mappedBy = "questionByQuestion",cascade = CascadeType.ALL)
    public Set<OptionsPo> getOptionsById() {
        return optionsById;
    }

    public void setOptionsById(Set<OptionsPo> optionsById) {
        this.optionsById = optionsById;
    }

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "problem", referencedColumnName = "id")
    public ProblemPo getProblemByProblem() {
        return problemByProblem;
    }

    public void setProblemByProblem(ProblemPo problemByProblem) {
        this.problemByProblem = problemByProblem;
    }
}
