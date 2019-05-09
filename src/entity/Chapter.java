package entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Timestamp;
import java.util.Objects;

/**
 * @author 16307110325
 * Created on 2019/4/21.
 */
@Entity
public class Chapter {
    private int id;
    private Timestamp createdOn;
    private Timestamp updatedOn;
    private String number;
    private String title;
    private int syllabusId;

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
    @Column(name = "number")
    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    @Basic
    @Column(name = "title")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Basic
    @Column(name = "syllabus_id")
    public int getSyllabusId() {
        return syllabusId;
    }

    public void setSyllabusId(int syllabusId) {
        this.syllabusId = syllabusId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Chapter chapter = (Chapter) o;
        return id == chapter.id &&
                syllabusId == chapter.syllabusId &&
                Objects.equals(createdOn, chapter.createdOn) &&
                Objects.equals(updatedOn, chapter.updatedOn) &&
                Objects.equals(number, chapter.number) &&
                Objects.equals(title, chapter.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, createdOn, updatedOn, number, title, syllabusId);
    }
}
