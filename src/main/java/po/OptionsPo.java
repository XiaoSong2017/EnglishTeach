package po;

import org.apache.struts2.json.annotations.JSON;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "options")
public class OptionsPo {
    private int id;
    private String content;
    private String mark;
    private int question;
    private QuestionPo questionByQuestion;

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
    @Column(name = "content", nullable = false, length = 255)
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Basic
    @Column(name = "mark", nullable = false, length = 1)
    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    @Basic
    @Column(name = "question", nullable = false,insertable = false,updatable = false)
    public int getQuestion() {
        return question;
    }

    public void setQuestion(int question) {
        this.question = question;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OptionsPo optionsPo = (OptionsPo) o;
        return id == optionsPo.id &&
                question == optionsPo.question &&
                Objects.equals(content, optionsPo.content) &&
                Objects.equals(mark, optionsPo.mark);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, content, mark, question);
    }

    @JSON(serialize = false)
    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(name = "question", referencedColumnName = "id", nullable = false)
    public QuestionPo getQuestionByQuestion() {
        return questionByQuestion;
    }

    public void setQuestionByQuestion(QuestionPo questionByQuestion) {
        this.questionByQuestion = questionByQuestion;
    }
}
