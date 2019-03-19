package po;

import org.apache.struts2.json.annotations.JSON;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "option")
public class OptionPo {
    private int id;
    private String content;
    private String mark;
    private QuestionPo questionByQuestion;

    @Id
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
        OptionPo optionPo = (OptionPo) o;
        return id == optionPo.id &&
                Objects.equals(content, optionPo.content)&&
                Objects.equals(mark,optionPo.mark);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, content);
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
