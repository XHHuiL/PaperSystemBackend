package dao.impl;

import dao.ChapterDao;
import dao.QuestionDao;
import dao.SyllabusDao;
import entity.Chapter;
import entity.KnowledgePoint;
import entity.Question;
import util.Querier;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 16307110325
 * Created on 2019/4/21.
 */
public class QuestionDaoImpl implements QuestionDao {
    private static final String tableName = "question";
    private static EntityBuilder<Question> builder = rs -> {
        Question question = new Question();
        try {
            question.setId(rs.getInt("id"));
            question.setDifficulty(rs.getInt("difficulty"));
            question.setScore(rs.getShort("score"));
            question.setStem(rs.getString("stem"));
            question.setKnowledgePoint(rs.getInt("knowledge_point"));
            question.setStatus(rs.getInt(rs.getInt("status")));
            question.setLanguage(rs.getInt("language"));
            question.setType(rs.getInt("type"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return question;
    };

    @Override
    public Question getOne(int id) {
        Querier<Question> querier = new Querier<>(tableName, builder).where("id=" + id);
        List<Question> result = querier.query();
        return (result == null || result.isEmpty()) ? null : result.get(0);
    }

    @Override
    public List<Question> getAll() {
        Querier<Question> querier = new Querier<>(tableName, builder);
        return querier.query();
    }

    @Override
    public List<Question> getBySyllabus(int syllabusId) {
        String sql = "SELECT question.* " +
                "FROM syllabus " +
                "INNER JOIN chapter ON syllabus.id=chapter.syllabus_id " +
                "INNER JOIN knowledge_point ON chapter.id=knowledge_point.chapter_id " +
                "INNER JOIN question ON knowledge_point.id=question.knowledge_point " +
                "WHERE chapter.id=" + syllabusId;
        System.out.println(sql);
        Querier<Question> querier = new Querier<>(sql, builder, false);
        return querier.query();
    }

    @Override
    public List<Question> getByChapter(int chapterId) {
        String sql = "SELECT question.* " +
                "FROM chapter " +
                "INNER JOIN knowledge_point ON chapter.id=knowledge_point.chapter_id " +
                "INNER JOIN question ON knowledge_point.id=question.knowledge_point " +
                "WHERE chapter.id=" + chapterId;
        Querier<Question> querier = new Querier<>(sql, builder, false);
        return querier.query();
    }
}
