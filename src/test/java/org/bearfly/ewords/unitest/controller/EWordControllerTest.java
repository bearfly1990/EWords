package org.bearfly.ewords.unitest.controller;

import org.bearfly.ewords.controller.EWordController;
import org.bearfly.ewords.dao.IEWordDAO;
import org.bearfly.ewords.dao.impl.SqliteDAO;
import org.bearfly.ewords.service.IEWordService;
import org.bearfly.ewords.service.impl.EWordServiceImpl;
import org.junit.Before;
import org.junit.Test;

public class EWordControllerTest {
    private EWordController ewc;
    @Before
    public void setup() {
        ewc = new EWordController();
        EWordServiceImpl service = new EWordServiceImpl();
        IEWordDAO dao = new SqliteDAO();
        service.setEwordDAO(dao);
        ewc.setEwService(service);
    }
    @Test
    public void testGetEwordsList() {
        System.out.println(ewc.getEwordsList());
       
    }
}
