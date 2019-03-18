package po;

import org.apache.struts2.json.annotations.JSON;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "option")
public class OptionPo {
    private int id;
    private String content;
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
    @Column(name = "content", nullable = false, length = 255)
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
        OptionPo optionPo = (OptionPo) o;
        return id == optionPo.id &&
                Objects.equals(content, optionPo.content);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, content);
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JSON(serialize = false)
    @JoinColumn(name = "question", referencedColumnName = "id", nullable = false)
    public QuestionPo getQuestionByQuestion() {
        return questionByQuestion;
    }

    public void setQuestionByQuestion(QuestionPo questionByQuestion) {
        this.questionByQuestion = questionByQuestion;
    }
}
