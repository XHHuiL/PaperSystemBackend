package dao.impl;

import dao.QuestionLanguageDao;
import entity.QuestionLanguage;
import util.Querier;

import java.util.List;

/**
 * @author 16307110325
 * Created on 2019/4/21.
 */
public class QuestionLanguageDaoImpl implements QuestionLanguageDao {
    private static final String tableName = "question_language";
    private static EntityBuilder<QuestionLanguage> builder; // TODO: unused.

    @Override
    public QuestionLanguage getOne(int id) {
        Querier<QuestionLanguage> querier = new Querier<>(tableName, builder).where("id=" + id);
        List<QuestionLanguage> result = querier.query();
        return (result == null || result.isEmpty()) ? null : result.get(0);
    }
}
