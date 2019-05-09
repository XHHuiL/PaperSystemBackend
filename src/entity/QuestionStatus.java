package entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

/**
 * @author 16307110325
 * Created on 2019/4/21.
 */
@Entity
@Table(name = "question_status", schema = "softtest", catalog = "")
public class QuestionStatus {
    private int id;
    private Timestamp createdOn;
    private Timestamp updatedOn;
    private boolean isFinish;
    private boolean isStart;
    private String name;
    private boolean accessibleByAuthor;
    private boolean accessibleByFacilitator;
    private boolean accessibleByQualityAdmin;
    private boolean accessibleByReviewer;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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
    @Column(name = "is_finish")
    public boolean isFinish() {
        return isFinish;
    }

    public void setFinish(boolean finish) {
        isFinish = finish;
    }

    @Basic
    @Column(name = "is_start")
    public boolean isStart() {
        return isStart;
    }

    public void setStart(boolean start) {
        isStart = start;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "accessible_by_author")
    public boolean isAccessibleByAuthor() {
        return accessibleByAuthor;
    }

    public void setAccessibleByAuthor(boolean accessibleByAuthor) {
        this.accessibleByAuthor = accessibleByAuthor;
    }

    @Basic
    @Column(name = "accessible_by_facilitator")
    public boolean isAccessibleByFacilitator() {
        return accessibleByFacilitator;
    }

    public void setAccessibleByFacilitator(boolean accessibleByFacilitator) {
        this.accessibleByFacilitator = accessibleByFacilitator;
    }

    @Basic
    @Column(name = "accessible_by_quality_admin")
    public boolean isAccessibleByQualityAdmin() {
        return accessibleByQualityAdmin;
    }

    public void setAccessibleByQualityAdmin(boolean accessibleByQualityAdmin) {
        this.accessibleByQualityAdmin = accessibleByQualityAdmin;
    }

    @Basic
    @Column(name = "accessible_by_reviewer")
    public boolean isAccessibleByReviewer() {
        return accessibleByReviewer;
    }

    public void setAccessibleByReviewer(boolean accessibleByReviewer) {
        this.accessibleByReviewer = accessibleByReviewer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        QuestionStatus that = (QuestionStatus) o;
        return id == that.id &&
                isFinish == that.isFinish &&
                isStart == that.isStart &&
                accessibleByAuthor == that.accessibleByAuthor &&
                accessibleByFacilitator == that.accessibleByFacilitator &&
                accessibleByQualityAdmin == that.accessibleByQualityAdmin &&
                accessibleByReviewer == that.accessibleByReviewer &&
                Objects.equals(createdOn, that.createdOn) &&
                Objects.equals(updatedOn, that.updatedOn) &&
                Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, createdOn, updatedOn, isFinish, isStart, name, accessibleByAuthor, accessibleByFacilitator, accessibleByQualityAdmin, accessibleByReviewer);
    }
}
