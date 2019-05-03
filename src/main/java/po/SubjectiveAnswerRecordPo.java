package po;


import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "subjective_answer_record")
public class SubjectiveAnswerRecordPo extends AnswerRecordPo {

    private float similarity;

    @Basic
    @Column(name = "similarity", nullable = false,length =4,precision = 3)
    public float getSimilarity() {
        return similarity;
    }

    public void setSimilarity(float similarity) {
        this.similarity = similarity;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SubjectiveAnswerRecordPo that = (SubjectiveAnswerRecordPo) o;
        return Objects.equals(similarity, that.similarity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(similarity);
    }
}
