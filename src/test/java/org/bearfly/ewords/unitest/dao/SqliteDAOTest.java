package org.bearfly.ewords.unitest.dao;

import org.bearfly.ewords.dao.impl.SqliteDAO;
import org.junit.Test;
import org.springframework.util.Assert;

public class SqliteDAOTest {
    @Test
    public void testGetAllEwords() {
        SqliteDAO sd = new SqliteDAO();
        Assert.isTrue(sd.getAllEwords().size() == 3);
    }
}
