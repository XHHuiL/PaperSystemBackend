package service;

import com.google.gson.*;
import jsonbean.QuestionDetailJsonBean;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;

/**
 * @author liuhui 16302010048
 */
public class QuestionServiceTest {
    private QuestionService questionService = new QuestionService();
    private Runtime runtime = Runtime.getRuntime();
    private final Gson gson = new Gson();
    private final JsonParser parser = new JsonParser();

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

    /**
     * 朱小宁 16307110325
     * Test normally get all syllabus.
     */
    @Test
    public void testNormallyGetAllSyllabus() {
        // get json of all syllabus
        String json = questionService.getAllSyllabus();

        Assert.assertNotNull("Returned string should not be null", json);

        Assert.assertFalse("There should not be a error", json.startsWith("error"));

        String expectedJson = "[{\"id\":1,\"level\":\"Foundation Level\",\"version\":\"2011 版 中文修订版本 1\"}," +
                "{\"id\":2,\"level\":\"Agile Test\",\"version\":\"1.0\"}," +
                "{\"id\":3,\"level\":\"Advanced Level-TA\",\"version\":\"2012版 中文版20150601\"}," +
                "{\"id\":8,\"level\":\"FL001\",\"version\":\"2019年1.0.2\"}," +
                "{\"id\":14,\"level\":\"Foundation Level\",\"version\":\"2019年1.0.1\"}]";

        Assert.assertTrue("Not equivalent to expected json", same(json, expectedJson));
    }

    /**
     * 朱小宁 16307110325
     * Returned json by getAllSyllabus() should start with error when MySQL service doesn't works.
     */
    @Test
    public void testGetAllSyllabusWhenMysqlNotWorks() {
        try {
            stopMySQLService();
        } catch (Exception e) {
            Assert.fail("Stop mysql service fail!");
            e.printStackTrace();
        }

        // get json of all syllabus
        String json = questionService.getAllSyllabus();

        Assert.assertNotNull("Returned string should not be null", json);

        Assert.assertTrue("Json ##" + json + "## should start with error",
                json.startsWith("error"));

        try {
            startMySQLService();
        } catch (Exception e) {
            Assert.fail("Start mysql service fail!");
            e.printStackTrace();
        }
    }

    /**
     * 朱小宁 16307110325
     * Test get all chapters with a existed syllabus id.
     */
    @Test
    public void testNormallyGetAllChapterInSyllabus() {
        String json = questionService.getAllChapterInSyllabus(1);

        Assert.assertNotNull("Returned string should not be null", json);

        Assert.assertFalse("There should not be a error", json.startsWith("error"));

        String expectedJson = "[{\"id\":2,\"number\":\"第二章\",\"title\":\"软件生命周期中的测试\"}," +
                "{\"id\":3,\"number\":\"第三章\",\"title\":\"静态技术\\t\"}," +
                "{\"id\":4,\"number\":\"第四章\",\"title\":\"测试设计技术\\t\"}," +
                "{\"id\":5,\"number\":\"第五章\",\"title\":\"测试管理\\t\"}," +
                "{\"id\":6,\"number\":\"第六章 \\t\",\"title\":\"软件测试工具\"},{" +
                "\"id\":55,\"number\":\"第一章\",\"title\":\"软件\"}]";

        Assert.assertTrue("Not equivalent to expected json", same(json, expectedJson));
    }

    /**
     * 朱小宁 16307110325
     * Returned json by getAllChapterInSyllabus() should start with error when given a nonexistent id.
     */
    @Test
    public void testGetGetAllChapterInSyllabusWithNonexistentId() {
        // get json of all syllabus
        String json = questionService.getAllChapterInSyllabus(233);

        Assert.assertNotNull("Returned string should not be null", json);

        Assert.assertTrue("Json ##" + json + "## should be empty array",
                json.equals("[]"));
    }

    /**
     * 朱小宁 16307110325
     * Returned json by getAllChapterInSyllabus() should start with error when MySQL service doesn't works.
     */
    @Test
    public void testGetAllChapterInSyllabusWhenMysqlNotWorks() {
        try {
            stopMySQLService();
        } catch (Exception e) {
            Assert.fail("Stop mysql service fail!");
            e.printStackTrace();
        }

        // get json of all syllabus
        String json = questionService.getAllChapterInSyllabus(1);

        Assert.assertNotNull("Returned string should not be null", json);

        Assert.assertTrue("Json ##" + json + "## should start with error",
                json.startsWith("error"));

        try {
            startMySQLService();
        } catch (Exception e) {
            Assert.fail("Start mysql service fail!");
            e.printStackTrace();
        }
    }

    /**
     * 朱小宁 16307110325
     * Test get all questions with a existed chapter id.
     */
    @Test
    public void testNormallyGetAllQuestionInChapter() {
        String json = questionService.getAllQuestionInChapter(1);

        Assert.assertNotNull("Returned string should not be null", json);

        Assert.assertFalse("There should not be a error", json.startsWith("error"));

        String expectedJson = "[{\"id\":84,\"difficulty\":2,\"type\":\"单选题\",\"score\":1,\"stem" +
                "\":\"\\u003cp\\u003e\\u003cstrong\\u003e开发团队正在开发一款在线交易系统，你负责其中付款" +
                "功能的组件测试。\\u003c/strong\\u003e\\u003c/p\\u003e\\n\\u003cp\\u003e\\u003cstrong\\u003e下面关" +
                "于测试付款功能的主要测试目标的描述，哪个是最恰当的？\\u003c/strong\\u003e\\u003c/p" +
                "\\u003e\",\"knowledge_point\":\"通过具体的例子，描述软" +
                "件中的缺陷会以什么样的方式损害个人、损害环境或者损害公司利益\"}," +
                "{\"id\":85,\"difficulty\":2,\"type\":\"单选题\",\"score\":1,\"" +
                "stem\":\"\\u003cp\\u003e\\u003cstrong\\u003e1.你是负责某在线支付系统图形化" +
                "界面测试的测试人员。你参与了该图形化界面的代码评审，在评审过程中，发现代码语句" +
                "将\\u0026ldquo;总额人民币\\u003c/strong\\u003e\\u003cstrong\\u003eRM\\u003c/s" +
                "trong\\u003e\\u003cstrong\\u003eB（）元\\u0026rdquo;写成了\\u0026ldquo;总额人" +
                "民币\\u003c/strong\\u003e\\u003cstrong\\u003eRMD\\u003c/strong\\u003e\\u003cst" +
                "rong\\u003e（）元\\u0026rdquo;，即存在拼写错误。\\u003c/strong\\u003e\\u003c/p\\u0" +
                "03e\\n\\u003cp\\u003e\\u003cstrong\\u003e\\u003cstrong\\u003e根据\\u003c/strong\\" +
                "u003e\\u003c/strong\\u003e\\u003cstrong\\u003e\\u003cstrong\\u003eISTQB\\u003c/st" +
                "rong\\u003e\\u003c/strong\\u003e\\u003cstrong\\u003e\\u003cstrong\\u003e的术语定义" +
                "，这个问题应该如何称呼最合适？（K2）\\u003c/strong\\u003e\\u003c/strong\\u003e\\u00" +
                "3c/p\\u003e\",\"knowledge_point\":\"通过具体的例子，描述软件中的缺陷会以什么样的方式" +
                "损害个人、损害环境或者损害公司利益\"},{\"id\":118,\"difficulty\":2,\"type\":\"视频题\"" +
                ",\"score\":1,\"knowledge_point\":\"通过具体的例子，描述软件中的缺陷会以什么样的方式损害" +
                "个人、损害环境或者损害公司利益\"}]";

        Assert.assertTrue("Not equivalent to expected json", same(json, expectedJson));
    }

    /**
     * 朱小宁 16307110325
     * Returned json by getAllQuestionInChapter() should start with error when given a nonexistent id.
     */
    @Test
    public void testGetAllQuestionInChapterWithNonexistentId() {
        // get json of all syllabus
        String json = questionService.getAllQuestionInChapter(233);

        Assert.assertNotNull("Returned string should not be null", json);

        Assert.assertTrue("Json ##" + json + "## should be empty array",
                json.equals("[]"));
    }

    /**
     * 朱小宁 16307110325
     * Returned json by getAllQuestionInChapter() should start with error when MySQL service doesn't works.
     */
    @Test
    public void testGetAllQuestionInChapterWhenMysqlNotWorks() {
        try {
            stopMySQLService();
        } catch (Exception e) {
            Assert.fail("Stop mysql service fail!");
            e.printStackTrace();
        }

        // get json of all syllabus
        String json = questionService.getAllQuestionInChapter(1);

        Assert.assertNotNull("Returned string should not be null", json);

        Assert.assertTrue("Json ##" + json + "## should start with error",
                json.startsWith("error"));

        try {
            startMySQLService();
        } catch (Exception e) {
            Assert.fail("Start mysql service fail!");
            e.printStackTrace();
        }
    }

    /**
     * 朱小宁 16307110325
     * Test get all questions with a existed syllabus id.
     */
    @Test
    public void testNormallyGetAllQuestionInSyllabus() {
        String json = questionService.getAllQuestionInSyllabus(1);

        Assert.assertNotNull("Returned string should not be null", json);

        Assert.assertFalse("There should not be a error", json.startsWith("error"));

        String expectedJson = "[{\"id\":84,\"difficulty\":2,\"type\":\"单选题\",\"score\":1,\"stem\":\"\\u003cp\\" +
                "u003e\\u003cstrong\\u003e开发团队正在开发一款在线交易系统，你负责其中付款功能的组件测试。\\u003c/st" +
                "rong\\u003e\\u003c/p\\u003e\\n\\u003cp\\u003e\\u003cstrong\\u003e下面关于测试付款功能的主要测试目" +
                "标的描述，哪个是最恰当的？\\u003c/strong\\u003e\\u003c/p\\u003e\",\"knowledge_point\":\"通过具体的" +
                "例子，描述软件中的缺陷会以什么样的方式损害个人、损害环境或者损害公司利益\"},{\"id\":85,\"difficulty" +
                "\":2,\"type\":\"单选题\",\"score\":1,\"stem\":\"\\u003cp\\u003e\\u003cstrong\\u003e1.你是负责某" +
                "在线支付系统图形化界面测试的测试人员。你参与了该图形化界面的代码评审，在评审过程中，发现代码语句将\\" +
                "u0026ldquo;总额人民币\\u003c/strong\\u003e\\u003cstrong\\u003eRM\\u003c/strong\\u003e\\u003cstr" +
                "ong\\u003eB（）元\\u0026rdquo;写成了\\u0026ldquo;总额人民币\\u003c/strong\\u003e\\u003cstrong\\u0" +
                "03eRMD\\u003c/strong\\u003e\\u003cstrong\\u003e（）元\\u0026rdquo;，即存在拼写错误。\\u003c/stron" +
                "g\\u003e\\u003c/p\\u003e\\n\\u003cp\\u003e\\u003cstrong\\u003e\\u003cstrong\\u003e根据\\u003c/st" +
                "rong\\u003e\\u003c/strong\\u003e\\u003cstrong\\u003e\\u003cstrong\\u003eISTQB\\u003c/strong\\u00" +
                "3e\\u003c/strong\\u003e\\u003cstrong\\u003e\\u003cstrong\\u003e的术语定义，这个问题应该如何称呼最" +
                "合适？（K2）\\u003c/strong\\u003e\\u003c/strong\\u003e\\u003c/p\\u003e\",\"knowledge_point\":\"通" +
                "过具体的例子，描述软件中的缺陷会以什么样的方式损害个人、损害环境或者损害公司利益\"},{\"id\":118,\"d" +
                "ifficulty\":2,\"type\":\"视频题\",\"score\":1,\"knowledge_point\":\"通过具体的例子，描述软件中的缺" +
                "陷会以什么样的方式损害个人、损害环境或者损害公司利益\"}]";

        Assert.assertTrue("Not equivalent to expected json", same(json, expectedJson));
    }

    /**
     * 朱小宁 16307110325
     * Returned json by getAllQuestionInSyllabus() should start with error when given a nonexistent id.
     */
    @Test
    public void testGetAllQuestionInSyllabusWithNonexistentId() {
        // get json of all syllabus
        String json = questionService.getAllChapterInSyllabus(233);

        Assert.assertNotNull("Returned string should not be null", json);

        Assert.assertTrue("Json ##" + json + "## should be empty array",
                json.equals("[]"));
    }

    /**
     * 朱小宁 16307110325
     * Returned json by getAllQuestionInSyllabus() should start with error when MySQL service doesn't works.
     */
    @Test
    public void testGetAllQuestionInSyllabusWhenMysqlNotWorks() {
        try {
            stopMySQLService();
        } catch (Exception e) {
            Assert.fail("Stop mysql service fail!");
            e.printStackTrace();
        }

        // get json of all syllabus
        String json = questionService.getAllQuestionInSyllabus(1);

        Assert.assertNotNull("Returned string should not be null", json);

        Assert.assertTrue("Json ##" + json + "## should start with error",
                json.startsWith("error"));

        try {
            startMySQLService();
        } catch (Exception e) {
            Assert.fail("Start mysql service fail!");
            e.printStackTrace();
        }
    }

    private void stopMySQLService() throws Exception {
        Process process = Runtime.getRuntime().exec("net stop mysql8");
        if (process.waitFor() != 0) {
            throw new Exception();
        }
    }

    private void startMySQLService() throws Exception {
        Process process = Runtime.getRuntime().exec("net start mysql8");
        if (process.waitFor() != 0) {
            throw new Exception();
        }
    }

    /**
     * Compare if two json string are equivalent.
     */
    private boolean same(String a, String b) {
        if (a == null) {
            return b == null;
        }
        if (a.equals(b)) {
            return true;
        }
        JsonElement aElement = parser.parse(a);
        JsonElement bElement = parser.parse(b);
        if (gson.toJson(aElement).equals(gson.toJson(bElement))) {
            return true;
        }
        return same(aElement, bElement);
    }

    private boolean same(JsonElement a, JsonElement b) {
        if (a.isJsonObject() && b.isJsonObject()) {
            return same((JsonObject) a, (JsonObject) b);
        } else if (a.isJsonArray() && b.isJsonArray()) {
            return same((JsonArray) a, (JsonArray) b);
        } else if (a.isJsonPrimitive() && b.isJsonPrimitive()) {
            return same((JsonPrimitive) a, (JsonPrimitive) b);
        } else if (a.isJsonNull() && b.isJsonNull()) {
            return same((JsonNull) a, (JsonNull) b);
        } else {
            return Boolean.FALSE;
        }
    }

    private boolean same(JsonObject a, JsonObject b) {
        Set<String> aSet = a.keySet();
        Set<String> bSet = b.keySet();
        if (!aSet.equals(bSet)) {
            return false;
        }
        for (String aKey : aSet) {
            if (!same(a.get(aKey), b.get(aKey))) {
                return false;
            }
        }
        return true;
    }

    private boolean same(JsonArray a, JsonArray b) {
        if (a.size() != b.size()) {
            return false;
        }
        List<JsonElement> aList = toSortedList(a);
        List<JsonElement> bList = toSortedList(b);
        for (int i = 0; i < aList.size(); i++) {
            if (!same(aList.get(i), bList.get(i))) {
                return false;
            }
        }
        return true;
    }

    private boolean same(JsonPrimitive a, JsonPrimitive b) {

        return a.equals(b);
    }

    private boolean same(JsonNull a, JsonNull b) {
        return true;
    }

    private List<JsonElement> toSortedList(JsonArray a) {
        List<JsonElement> aList = new ArrayList<>();
        a.forEach(aList::add);
        aList.sort(Comparator.comparing(gson::toJson));
        return aList;
    }
}