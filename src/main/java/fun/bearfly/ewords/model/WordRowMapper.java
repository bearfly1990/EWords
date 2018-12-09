package fun.bearfly.ewords.model;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

/**
 * @Description: TODO
 * @author bearfly1990
 * @date Dec 9, 2018 8:09:14 PM
 */
public class WordRowMapper implements RowMapper<Word> {

	@Override
	public Word mapRow(ResultSet rs, int rowNum) throws SQLException {
		Word word = new Word();
		word.setId(rs.getInt("id"));
		word.setEword(rs.getString("eword"));
		word.setCword(rs.getString("cword"));
		return word;
	}

}