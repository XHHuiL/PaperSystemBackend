package service;

import com.google.gson.Gson;
import dao.*;
import dao.impl.*;
import entity.*;
import jsonbean.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author 16307110325
 * Created on 2019/4/21.
 */
public class QuestionService {
    private Gson gson = new Gson();
    private SyllabusDao syllabusDao = new SyllabusDaoImpl();
    private ChapterDao chapterDao = new ChapterDaoImpl();
    private QuestionDao questionDao = new QuestionDaoImpl();
    private QuestionTypeDao questionTypeDao = new QuestionTypeDaoImpl();
    private KnowledgePointDao knowledgePointDao = new KnowledgePointDaoImpl();
    private QuestionChoiceDao questionChoiceDao = new QuestionChoiceDaoImpl();

    public String getAllSyllabus() {
        List<Syllabus> syllabi = syllabusDao.getAll();
        if (syllabi == null) {
            return "error: fail to get";
        }
        List<SyllabusJsonBean> jsonBeans = syllabi.stream().map(syllabus -> {
            SyllabusJsonBean jsonBean = new SyllabusJsonBean();
            jsonBean.id = syllabus.getId();
            jsonBean.version = syllabus.getVersion();
            jsonBean.level = syllabus.getLevel();
            return jsonBean;
        }).collect(Collectors.toList());

        return gson.toJson(jsonBeans);
    }

    public String getAllChapterInSyllabus(int id) {
        List<Chapter> chapters = chapterDao.getBySyllabus(id);
        if (chapters == null) {
            return "error: fail to get";
        }

        List<ChapterJsonBean> jsonBeans = chapters.stream().map(chapter -> {
            ChapterJsonBean jsonBean = new ChapterJsonBean();
            jsonBean.id = chapter.getId();
            jsonBean.number = chapter.getNumber();
            jsonBean.title = chapter.getTitle();
            return jsonBean;
        }).collect(Collectors.toList());

        return gson.toJson(jsonBeans);
    }

    public String getAllQuestionInChapter(int id) {
        return getQuestionJson(questionDao.getByChapter(id));
    }

    public String getAllQuestionInSyllabus(int id) {
        return getQuestionJson(questionDao.getBySyllabus(id));
    }

    public String getAllQuestion() {
        return getQuestionJson(questionDao.getAll());
    }

    public String getQuestion(int id) {
        Question question = questionDao.getOne(id);
        if (question == null) {
            return "error: fail to get question";
        }

        QuestionType type = questionTypeDao.getOne(question.getType());
        if (type == null) {
            return "error: fail to get question type";
        }

        KnowledgePoint knowledgePoint = knowledgePointDao.getOne(question.getKnowledgePoint());
        if (knowledgePoint == null) {
            return "error: fail to get question knowledge point";
        }

        List<QuestionChoice> questionChoices = questionChoiceDao.getByQuestion(question.getId());
        if (questionChoices == null) {
            return "error: fail to get question question choices";
        }

        List<QuestionChoiceJsonBean> questionChoiceJsonBeans = new ArrayList<>();
        for (QuestionChoice choice : questionChoices) {
            QuestionChoiceJsonBean jsonBean = new QuestionChoiceJsonBean();
            jsonBean.content = choice.getContent();
            jsonBean.is_correct_answer = choice.isCorrectAnswer();
            jsonBean.label = choice.getChoiceLabel();
            jsonBean.image = null; // TODO: this is left
        }

        QuestionDetailJsonBean jsonBean = new QuestionDetailJsonBean();
        jsonBean.id = question.getId();
        jsonBean.difficulty = question.getDifficulty();
        jsonBean.score = question.getScore();
        jsonBean.stem = question.getStem();
        jsonBean.type = type.getName();
        jsonBean.knowledge_point = knowledgePoint.getTitle();
        jsonBean.question_choices = questionChoiceJsonBeans;
        jsonBean.question_images = null; // TODO: question image is left

        return gson.toJson(jsonBean);
    }

    private String getQuestionJson(List<Question> questions) {
        if (questions == null) {
            return "error: fail to get";
        }

        List<QuestionJsonBean> jsonBeans = new ArrayList<>(questions.size());
        for (Question question : questions) {
            QuestionType type = questionTypeDao.getOne(question.getType());
            if (type == null) {
                return "error: fail to get question type";
            }

            KnowledgePoint knowledgePoint = knowledgePointDao.getOne(question.getKnowledgePoint());
            if (knowledgePoint == null) {
                return "error: fail to get question knowledge point";
            }

            QuestionJsonBean jsonBean = new QuestionJsonBean();

            jsonBean.id = question.getId();
            jsonBean.difficulty = question.getDifficulty();
            jsonBean.score = question.getScore();
            jsonBean.stem = question.getStem();
            jsonBean.type = type.getName();
            jsonBean.knowledge_point = knowledgePoint.getTitle();

            jsonBeans.add(jsonBean);
        }

        return gson.toJson(jsonBeans);
    }
}
