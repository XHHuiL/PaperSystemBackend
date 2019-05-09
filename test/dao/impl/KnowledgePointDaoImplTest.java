package dao.impl;

import dao.KnowledgePointDao;
import entity.KnowledgePoint;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author liuhui 16302010048
 */
public class KnowledgePointDaoImplTest {
    private KnowledgePointDao knowledgePointDao = new KnowledgePointDaoImpl();
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
        knowledgePointDao.getOne(1);
    }

    /**
     * @author liuhui 16302010048
     * test case 2
     * input knowledge point id is invalid
     * KnowledgePointDaoImpl.getOne() return null
     */
    @Test
    public void testGetOneWithInvalidKnowledgePoint() throws Exception {
        Assert.assertNull(knowledgePointDao.getOne(0));
    }

    /**
     * @author liuhui 16302010048
     * test case 3
     * input knowledge point id is valid
     * KnowledgePointDaoImpl.getOne() return corresponding KnowledgePoint Object
     */
    @Test
    public void testGetOneWithValidKnowledgePoint() throws Exception {
        KnowledgePoint expected = new KnowledgePoint();
        expected.setId(1);
        expected.setTitle("通过具体的例子，描述软件中的缺陷会以什么样的方式损害个人、损害环境或者损害公司利益");
        Assert.assertEquals(expected, knowledgePointDao.getOne(1));
    }

}