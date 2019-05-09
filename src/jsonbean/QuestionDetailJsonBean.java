package jsonbean;

import java.util.List;

/**
 * @author 16307110325
 * Created on 2019/4/21.
 */
public class QuestionDetailJsonBean {
    public int id;
    public int difficulty;
    public String type;
    public String version;
    public int score;
    public String stem;
    public String knowledge_point;
    public List<QuestionChoiceJsonBean> question_choices;
    public List<String> question_images;
}
