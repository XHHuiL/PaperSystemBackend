package entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.Objects;

/**
 * @author 16307110325
 * Created on 2019/4/21.
 */
@Entity
public class Question {
    private int id;
    private Timestamp createdOn;
    private Timestamp updatedOn;
    private Date authoringFinishDate;
    private Date authoringStartDate;
    private String customField1;
    private String customField2;
    private String customField3;
    private String description;
    private int difficulty;
    private String memo;
    private boolean multipleChoice;
    private Timestamp published;
    private Date reviewingFinishDate;
    private Date reviewingStartDate;
    private String scenario;
    private short score;
    private String stem;
    private int authorId;
    private Integer knowledgePoint;
    private int language;
    private int projectId;
    private int qualityAdminId;
    private int status;
    private int type;
    private String customFiled1;
    private String remark;

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
    @Column(name = "authoring_finish_date")
    public Date getAuthoringFinishDate() {
        return authoringFinishDate;
    }

    public void setAuthoringFinishDate(Date authoringFinishDate) {
        this.authoringFinishDate = authoringFinishDate;
    }

    @Basic
    @Column(name = "authoring_start_date")
    public Date getAuthoringStartDate() {
        return authoringStartDate;
    }

    public void setAuthoringStartDate(Date authoringStartDate) {
        this.authoringStartDate = authoringStartDate;
    }

    @Basic
    @Column(name = "custom_field1")
    public String getCustomField1() {
        return customField1;
    }

    public void setCustomField1(String customField1) {
        this.customField1 = customField1;
    }

    @Basic
    @Column(name = "custom_field2")
    public String getCustomField2() {
        return customField2;
    }

    public void setCustomField2(String customField2) {
        this.customField2 = customField2;
    }

    @Basic
    @Column(name = "custom_field3")
    public String getCustomField3() {
        return customField3;
    }

    public void setCustomField3(String customField3) {
        this.customField3 = customField3;
    }

    @Basic
    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Basic
    @Column(name = "difficulty")
    public int getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }

    @Basic
    @Column(name = "memo")
    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    @Basic
    @Column(name = "multiple_choice")
    public boolean isMultipleChoice() {
        return multipleChoice;
    }

    public void setMultipleChoice(boolean multipleChoice) {
        this.multipleChoice = multipleChoice;
    }

    @Basic
    @Column(name = "published")
    public Timestamp getPublished() {
        return published;
    }

    public void setPublished(Timestamp published) {
        this.published = published;
    }

    @Basic
    @Column(name = "reviewing_finish_date")
    public Date getReviewingFinishDate() {
        return reviewingFinishDate;
    }

    public void setReviewingFinishDate(Date reviewingFinishDate) {
        this.reviewingFinishDate = reviewingFinishDate;
    }

    @Basic
    @Column(name = "reviewing_start_date")
    public Date getReviewingStartDate() {
        return reviewingStartDate;
    }

    public void setReviewingStartDate(Date reviewingStartDate) {
        this.reviewingStartDate = reviewingStartDate;
    }

    @Basic
    @Column(name = "scenario")
    public String getScenario() {
        return scenario;
    }

    public void setScenario(String scenario) {
        this.scenario = scenario;
    }

    @Basic
    @Column(name = "score")
    public short getScore() {
        return score;
    }

    public void setScore(short score) {
        this.score = score;
    }

    @Basic
    @Column(name = "stem")
    public String getStem() {
        return stem;
    }

    public void setStem(String stem) {
        this.stem = stem;
    }

    @Basic
    @Column(name = "author_id")
    public int getAuthorId() {
        return authorId;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }

    @Basic
    @Column(name = "knowledge_point")
    public Integer getKnowledgePoint() {
        return knowledgePoint;
    }

    public void setKnowledgePoint(Integer knowledgePoint) {
        this.knowledgePoint = knowledgePoint;
    }

    @Basic
    @Column(name = "language")
    public int getLanguage() {
        return language;
    }

    public void setLanguage(int language) {
        this.language = language;
    }

    @Basic
    @Column(name = "project_id")
    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    @Basic
    @Column(name = "quality_admin_id")
    public int getQualityAdminId() {
        return qualityAdminId;
    }

    public void setQualityAdminId(int qualityAdminId) {
        this.qualityAdminId = qualityAdminId;
    }

    @Basic
    @Column(name = "status")
    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Basic
    @Column(name = "type")
    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    @Basic
    @Column(name = "custom_filed1")
    public String getCustomFiled1() {
        return customFiled1;
    }

    public void setCustomFiled1(String customFiled1) {
        this.customFiled1 = customFiled1;
    }

    @Basic
    @Column(name = "remark")
    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Question question = (Question) o;
        return id == question.id &&
                difficulty == question.difficulty &&
                multipleChoice == question.multipleChoice &&
                score == question.score &&
                authorId == question.authorId &&
                language == question.language &&
                projectId == question.projectId &&
                qualityAdminId == question.qualityAdminId &&
                status == question.status &&
                type == question.type &&
                Objects.equals(createdOn, question.createdOn) &&
                Objects.equals(updatedOn, question.updatedOn) &&
                Objects.equals(authoringFinishDate, question.authoringFinishDate) &&
                Objects.equals(authoringStartDate, question.authoringStartDate) &&
                Objects.equals(customField1, question.customField1) &&
                Objects.equals(customField2, question.customField2) &&
                Objects.equals(customField3, question.customField3) &&
                Objects.equals(description, question.description) &&
                Objects.equals(memo, question.memo) &&
                Objects.equals(published, question.published) &&
                Objects.equals(reviewingFinishDate, question.reviewingFinishDate) &&
                Objects.equals(reviewingStartDate, question.reviewingStartDate) &&
                Objects.equals(scenario, question.scenario) &&
                Objects.equals(stem, question.stem) &&
                Objects.equals(knowledgePoint, question.knowledgePoint) &&
                Objects.equals(customFiled1, question.customFiled1) &&
                Objects.equals(remark, question.remark);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, createdOn, updatedOn, authoringFinishDate, authoringStartDate, customField1, customField2, customField3, description, difficulty, memo, multipleChoice, published, reviewingFinishDate, reviewingStartDate, scenario, score, stem, authorId, knowledgePoint, language, projectId, qualityAdminId, status, type, customFiled1, remark);
    }
}
