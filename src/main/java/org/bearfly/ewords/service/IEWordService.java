package org.bearfly.ewords.service;

import java.util.List;

import org.bearfly.ewords.model.Word;

public interface IEWordService {
    public List<Word> getAllEwords();
    public Boolean addEword(Word word);
    public Boolean delEword(Word word);
    public Word getWord(Word word);
}
