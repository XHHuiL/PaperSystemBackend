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
public class Syllabus {
    private int id;
    private Timestamp createdOn;
    private Timestamp updatedOn;
    private Boolean isDisabled;
    private String level;
    private String version;

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
    @Column(name = "is_disabled")
    public Boolean getDisabled() {
        return isDisabled;
    }

    public void setDisabled(Boolean disabled) {
        isDisabled = disabled;
    }

    @Basic
    @Column(name = "level")
    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    @Basic
    @Column(name = "version")
    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Syllabus syllabus = (Syllabus) o;
        return id == syllabus.id &&
                Objects.equals(createdOn, syllabus.createdOn) &&
                Objects.equals(updatedOn, syllabus.updatedOn) &&
                Objects.equals(isDisabled, syllabus.isDisabled) &&
                Objects.equals(level, syllabus.level) &&
                Objects.equals(version, syllabus.version);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, createdOn, updatedOn, isDisabled, level, version);
    }
}
