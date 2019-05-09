package entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

/**
 * @author 16307110325
 * Created on 2019/4/21.
 */
@Entity
@Table(name = "question_status_transition", schema = "softtest", catalog = "")
@IdClass(QuestionStatusTransitionPK.class)
public class QuestionStatusTransition {
    private Timestamp createdOn;
    private Timestamp updatedOn;
    private Integer sequence;
    private int nextStatusId;
    private int statusId;

    @Basic
    @Column(name = "created_on")
    public Timestamp getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Timestamp createdOn) {
        this.createdOn = createdOn;
    }

    @Basic
    @Column(name = "updated_on")
    public Timestamp getUpdatedOn() {
        return updatedOn;
    }

    public void setUpdatedOn(Timestamp updatedOn) {
        this.updatedOn = updatedOn;
    }

    @Basic
    @Column(name = "sequence")
    public Integer getSequence() {
        return sequence;
    }

    public void setSequence(Integer sequence) {
        this.sequence = sequence;
    }

    @Id
    @Column(name = "next_status_id")
    public int getNextStatusId() {
        return nextStatusId;
    }

    public void setNextStatusId(int nextStatusId) {
        this.nextStatusId = nextStatusId;
    }

    @Id
    @Column(name = "status_id")
    public int getStatusId() {
        return statusId;
    }

    public void setStatusId(int statusId) {
        this.statusId = statusId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        QuestionStatusTransition that = (QuestionStatusTransition) o;
        return nextStatusId == that.nextStatusId &&
                statusId == that.statusId &&
                Objects.equals(createdOn, that.createdOn) &&
                Objects.equals(updatedOn, that.updatedOn) &&
                Objects.equals(sequence, that.sequence);
    }

    @Override
    public int hashCode() {
        return Objects.hash(createdOn, updatedOn, sequence, nextStatusId, statusId);
    }
}
