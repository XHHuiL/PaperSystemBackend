package entity;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

/**
 * @author 16307110325
 * Created on 2019/4/21.
 */
public class QuestionReviewersPK implements Serializable {
    private int questionId;
    private int reviewerId;

    @Column(name = "question_id")
    @Id
    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    @Column(name = "reviewer_id")
    @Id
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
        QuestionReviewersPK that = (QuestionReviewersPK) o;
        return questionId == that.questionId &&
                reviewerId == that.reviewerId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(questionId, reviewerId);
    }
}
