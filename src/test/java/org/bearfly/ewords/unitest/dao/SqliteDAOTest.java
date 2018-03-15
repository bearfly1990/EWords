package org.bearfly.ewords.unitest.dao;

import org.bearfly.ewords.dao.impl.SqliteDAO;
import org.bearfly.ewords.model.Word;
import org.junit.Test;
import org.springframework.util.Assert;

public class SqliteDAOTest {
    @Test
    public void testGetAllEwords() {
        SqliteDAO sd = new SqliteDAO();
        Assert.isTrue(sd.getAllEwords().size() == 3);
    }
    @Test
    public void testAddEword() {
        //SqliteDAO sd = new SqliteDAO();
        //sd.addEWord(new Word("A", "B"));
        
    }
    @Test
    public void testGetEword() {
        SqliteDAO sd = new SqliteDAO();
        sd.getWord(new Word("China", "中国"));
        
    }
}
