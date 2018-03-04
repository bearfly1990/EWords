package org.bearfly.ewords.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.bearfly.ewords.dao.IEWordDAO;
import org.bearfly.ewords.model.EWord;
import org.bearfly.ewords.service.IEWordService;
import org.springframework.stereotype.Service;

@Service("ewService")
public class EWordServiceImpl implements IEWordService{
    private IEWordDAO ewordDAO;

    public List<EWord> getAllEwords(){
        return ewordDAO.getAllEwords();
    }
    public IEWordDAO getEwordDAO() {
        return ewordDAO;
    }
    @Resource(name="ewordDAO")
    public void setEwordDAO(IEWordDAO ewordDAO) {
        this.ewordDAO = ewordDAO;
    }
}
