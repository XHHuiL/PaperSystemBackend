package dao;

import entity.QuestionChoice;

import java.util.List;

/**
 * @author 16307110325
 * Created on 2019/4/21.
 */
public interface QuestionChoiceDao {

    List<QuestionChoice> getByQuestion(int id);
}
