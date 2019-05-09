package service;

import com.google.gson.Gson;
import jsonbean.QuestionDetailJsonBean;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

/**
 * @author liuhui 16302010048
 */
public class QuestionServiceTest {
    private QuestionService questionService = new QuestionService();
    private Runtime runtime = Runtime.getRuntime();

    /**
     * start the mysql8 service before execute each method
     */
    @Before
    public void setUp() throws Exception {
        runtime.exec("net start mysql8").waitFor();
    }

    /**
     * stop the mysql8 service after execute each method
     */
    @After
    public void tearDown() throws Exception {
        runtime.exec("net stop mysql8").waitFor();
    }

    /**
     * @author liuhui 16302010048
     * test case 1
     * input invalid question id
     * QuestionService.getQuestion() return "error: fail to get question"
     */
    @Test
    public void testGetQuestionWithInvalidQuestionId() throws Exception {
        Assert.assertEquals("error: fail to get question", questionService.getQuestion(0));
    }

    /**
     * @author liuhui 16302010048
     * test case 2
     * input valid question id
     * QuestionService.getQuestion() return corresponding question information
     */
    @Test
    public void testGetQuestionWithValidQuestionId() throws Exception {
        QuestionDetailJsonBean jsonBean = new QuestionDetailJsonBean();
        jsonBean.id = 84;
        jsonBean.difficulty = 2;
        jsonBean.score = 1;
        jsonBean.stem = "<p><strong>开发团队正在开发一款在线交易系统，你负责其中付款功能的组件测试。</strong></p>\n" +
                "<p><strong>下面关于测试付款功能的主要测试目标的描述，哪个是最恰当的？</strong></p>";
        jsonBean.type = "单选题";
        jsonBean.knowledge_point = "通过具体的例子，描述软件中的缺陷会以什么样的方式损害个人、损害环境或者损害公司利益";
        jsonBean.question_choices = new ArrayList<>();
        jsonBean.question_images = null;
        Assert.assertEquals(new Gson().toJson(jsonBean), questionService.getQuestion(84));
    }

}