package fun.bearfly.ewords.dao;

import java.util.List;

import fun.bearfly.ewords.model.Word;

public interface IEWordDAO {
    public List<Word> getAllEwords();
    public Boolean addEWord(Word word);
    public Boolean delEword(Word word);
    public Word getWord(Word word);
}
