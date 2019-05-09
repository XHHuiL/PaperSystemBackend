package entity;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

/**
 * @author 16307110325
 * Created on 2019/4/21.
 */
public class QuestionStatusTransitionPK implements Serializable {
    private int nextStatusId;
    private int statusId;

    @Column(name = "next_status_id")
    @Id
    public int getNextStatusId() {
        return nextStatusId;
    }

    public void setNextStatusId(int nextStatusId) {
        this.nextStatusId = nextStatusId;
    }

    @Column(name = "status_id")
    @Id
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
        QuestionStatusTransitionPK that = (QuestionStatusTransitionPK) o;
        return nextStatusId == that.nextStatusId &&
                statusId == that.statusId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(nextStatusId, statusId);
    }
}
