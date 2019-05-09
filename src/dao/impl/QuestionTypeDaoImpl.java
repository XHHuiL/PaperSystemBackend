package dao.impl;

import dao.QuestionTypeDao;
import entity.Question;
import entity.QuestionType;
import util.Querier;

import java.sql.SQLException;
import java.util.List;

/**
 * @author 16307110325
 * Created on 2019/4/21.
 */
public class QuestionTypeDaoImpl implements QuestionTypeDao {
    private static final String tableName = "question_type";
    private static EntityBuilder<QuestionType> builder = rs -> {
        QuestionType type = new QuestionType();
        try {
            type.setId(rs.getInt("id"));
            type.setName(rs.getString("name"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return type;
    };

    @Override
    public QuestionType getOne(int id) {
        Querier<QuestionType> querier = new Querier<>(tableName, builder).where("id=" + id);
        List<QuestionType> result = querier.query();
        return (result == null || result.isEmpty()) ? null : result.get(0);
    }
}
