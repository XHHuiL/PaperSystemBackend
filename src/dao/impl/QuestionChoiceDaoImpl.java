package dao.impl;

import dao.QuestionChoiceDao;
import entity.QuestionChoice;
import util.Querier;

import java.sql.SQLException;
import java.util.List;

/**
 * @author 16307110325
 * Created on 2019/4/21.
 */
public class QuestionChoiceDaoImpl implements QuestionChoiceDao {
    private static final String tableName = "question_choice";
    private static EntityBuilder<QuestionChoice> builder = rs -> {
        QuestionChoice choice = new QuestionChoice();
        try {
            choice.setId(rs.getInt("id"));
            choice.setChoiceLabel(rs.getString("choice_label"));
            choice.setContent(rs.getString("content"));
            choice.setCorrectAnswer(rs.getBoolean("correct_answer"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return choice;
    };

    @Override
    public List<QuestionChoice> getByQuestion(int id) {
        Querier<QuestionChoice> querier = new Querier<>(tableName, builder).where("question_id=" + id);
        return querier.query();
    }
}
