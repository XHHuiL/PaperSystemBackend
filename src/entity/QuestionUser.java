package entity;

import javax.persistence.*;
import java.util.Objects;

/**
 * @author 16307110325
 * Created on 2019/4/21.
 */
@Entity
@Table(name = "question_user", schema = "softtest", catalog = "")
@IdClass(QuestionUserPK.class)
public class QuestionUser {
    private int questionId;
    private int reviewerId;

    @Id
    @Column(name = "question_id")
    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    @Id
    @Column(name = "reviewer_id")
    public int getReviewerId() {
        return reviewerId;
    }

    public void setReviewerId(int reviewerId) {
        this.reviewerId = reviewerId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        QuestionUser that = (QuestionUser) o;
        return questionId == that.questionId &&
                reviewerId == that.reviewerId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(questionId, reviewerId);
    }
}
