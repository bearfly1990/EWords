package fun.bearfly.ewords.service;

import java.util.List;

import fun.bearfly.ewords.model.Word;

public interface IEWordService {
    public List<Word> getAllEwords();
    public Boolean addEword(Word word);
    public Boolean delEword(Word word);
    public Word getWord(Word word);
}
