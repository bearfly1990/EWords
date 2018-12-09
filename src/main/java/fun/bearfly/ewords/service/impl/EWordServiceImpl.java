package fun.bearfly.ewords.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import fun.bearfly.ewords.dao.IEWordDAO;
import fun.bearfly.ewords.model.Word;
import fun.bearfly.ewords.service.IEWordService;

@Service("ewService")
public class EWordServiceImpl implements IEWordService{
    private IEWordDAO ewordDAO;
    private static Logger logger = LogManager.getLogger(EWordServiceImpl.class);

    public IEWordDAO getEwordDAO() {
        return ewordDAO;
    }
    @Resource(name="ewordDAO")
    public void setEwordDAO(IEWordDAO ewordDAO) {
        this.ewordDAO = ewordDAO;
    }
    
    @Override
    public List<Word> getAllEwords(){
        return ewordDAO.getAllEwords();
    }
    @Override
    public Boolean addEword(Word word) {
        return ewordDAO.addEWord(word);
    }
    @Override
    public Boolean delEword(Word word) {
        return ewordDAO.delEword(word);
    }
    @Override
    public Word getWord(Word word) {
        logger.info("Get whole Word...");
        return ewordDAO.getWord(word);
    }
}
