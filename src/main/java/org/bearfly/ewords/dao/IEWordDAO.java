package org.bearfly.ewords.dao;

import java.util.List;

import org.bearfly.ewords.model.Word;

public interface IEWordDAO {
    public List<Word> getAllEwords();
    public Boolean addEWord(Word word);
    public Boolean delEword(Word word);
    public Word getWord(Word word);
}
