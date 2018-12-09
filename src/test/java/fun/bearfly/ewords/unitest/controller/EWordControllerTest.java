package fun.bearfly.ewords.unitest.controller;

import org.junit.Before;
import org.junit.Test;

import fun.bearfly.ewords.controller.EWordController;
import fun.bearfly.ewords.dao.IEWordDAO;
import fun.bearfly.ewords.dao.impl.SqliteDAO;
import fun.bearfly.ewords.service.impl.EWordServiceImpl;

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
//        System.out.println(ewc.getEwordsList());
       
    }
}
