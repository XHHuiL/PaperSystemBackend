package dao.impl;

import dao.QuestionChoiceImageDao;
import entity.QuestionChoice;
import entity.QuestionChoiceImage;
import util.Querier;

import java.sql.SQLException;
import java.util.List;

/**
 * @author 16307110325
 * Created on 2019/4/21.
 */
public class QuestionChoiceImageDaoImpl implements QuestionChoiceImageDao {
    private static final String tableName = "question_choice_image";
    private static EntityBuilder<QuestionChoiceImage> builder = rs -> {
        QuestionChoiceImage image = new QuestionChoiceImage();
        try {
            image.setId(rs.getInt("id"));
            image.setPath(rs.getString("path"));
            image.setCaption(rs.getString("caption"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return image;
    };

    @Override
    public QuestionChoiceImage getOne(int id) {
        Querier<QuestionChoiceImage> querier = new Querier<>(tableName, builder).where("id=" + id);
        List<QuestionChoiceImage> result = querier.query();
        return (result == null || result.isEmpty()) ? null : result.get(0);
    }
}
