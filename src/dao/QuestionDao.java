package dao;

import entity.Question;

import java.util.List;

/**
 * @author 16307110325
 * Created on 2019/4/21.
 */
public interface QuestionDao {

    Question getOne(int id);

    List<Question> getAll();

    List<Question> getBySyllabus(int syllabusId);

    List<Question> getByChapter(int chapterId);
}
