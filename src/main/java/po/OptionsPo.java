package po;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "options")
public class OptionsPo {
    private int id;
    private String content;
    private String mark;
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
    @Column(name = "content", nullable = false)
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Basic
    @Column(name = "mark",nullable = false,length = 1)
    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OptionsPo optionsPo = (OptionsPo) o;
        return id == optionsPo.id &&
                Objects.equals(content, optionsPo.content)&&
                Objects.equals(mark, optionsPo.mark);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, content,mark);
    }

    @ManyToOne
    @JoinColumn(name = "question", referencedColumnName = "id", nullable = false)
    public QuestionPo getQuestionByQuestion() {
        return questionByQuestion;
    }

    public void setQuestionByQuestion(QuestionPo questionByQuestion) {
        this.questionByQuestion = questionByQuestion;
    }
}
