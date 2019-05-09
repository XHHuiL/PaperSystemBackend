package dao.impl;

import dao.ChapterDao;
import entity.Chapter;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;


/**
 * @author liuhui 16302010048
 */
public class ChapterDaoImplTest {
    private ChapterDao chapterDao = new ChapterDaoImpl();
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
     * mysql8 service is not running,
     * throws NullPointerException
     */
    @Test(expected = NullPointerException.class)
    public void testGetOneWithNoMysqlService() throws Exception {
        runtime.exec("net stop mysql8").waitFor();
        chapterDao.getOne(1);
    }

    /**
     * @author liuhui 16302010048
     * test case 2
     * input invalid chapter id
     * ChapterDaoImpl.getOne() return null
     */
    @Test
    public void testGetOneWithInvalidChapterId() throws Exception{
        Assert.assertNull(chapterDao.getOne(0));
    }

    /**
     * @author liuhui 16302010048
     * test case 3
     * input valid chapter id
     * ChapterDaoImpl.getOne() return corresponding Chapter Object
     */
    @Test
    public void testGetOneWithValidChapterId() throws Exception {
        Chapter expectedChapter = getChapter( 1, "第一章", "软件测试基础");
        Chapter chapter = chapterDao.getOne(1);
        Assert.assertEquals(expectedChapter, chapter);
    }

    /**
     * @author liuhui 16302010048
     * test case 4
     * mysql8 service is not running,
     * throws NullPointerException
     */
    @Test(expected = NullPointerException.class)
    public void testGetBySyllabusWithNoMysqlService() throws Exception {
        runtime.exec("net stop mysql8").waitFor();
        chapterDao.getBySyllabus(1);
    }

    /**
     * @author liuhui 16302010048
     * test case 5
     * input invalid syllabus id
     * ChapterDaoImpl.getBySyllabus() return empty list
     */
    @Test
    public void testGetBySyllabusWithInvalidSyllabusId() throws Exception {
        Assert.assertEquals(0, chapterDao.getBySyllabus(0).size());
    }


    /**
     * @author liuhui 16302010048
     * test case 6
     * input valid syllabus id
     * ChapterDaoImpl.getBySyllabus() return corresponding chapter list
     */
    @Test
    public void testGetBySyllabusWithValidSyllabusId() throws Exception {
        List<Chapter> expectedChapters = new ArrayList<>();
        expectedChapters.add(getChapter(11, "第一章", "测试过程"));
        expectedChapters.add(getChapter(12, "第二章", "测试管理：测试分析师的职责"));
        expectedChapters.add(getChapter(13, "第三章", "测试技术"));
        expectedChapters.add(getChapter(14, "第四章", "测试软件质量特性"));
        expectedChapters.add(getChapter(15, "第五章", "评审"));
        expectedChapters.add(getChapter(16, "第六章", "缺陷管理"));
        List<Chapter> chapters = chapterDao.getBySyllabus(3);
        Assert.assertEquals(6, chapters.size());
        for (int i = 0; i < 6; i++) {
            Assert.assertEquals(expectedChapters.get(i), chapters.get(i));
        }
    }

    /**
     * @param id chapter's id
     * @param number chapter's number
     * @param title chapter's title
     * @return chapter constructed by input id, input umber and input title
     */
    private Chapter getChapter(int id, String number, String title){
        Chapter chapter = new Chapter();
        chapter.setId(id);
        chapter.setNumber(number);
        chapter.setTitle(title);
        return chapter;
    }

}