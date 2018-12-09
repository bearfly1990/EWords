package fun.bearfly.ewords.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import fun.bearfly.ewords.dao.IEWordDAO;
import fun.bearfly.ewords.model.Word;
import fun.bearfly.ewords.model.WordRowMapper;

@Repository("ewordDAO")
public class SqliteDAO implements IEWordDAO {
	private static Logger logger = LogManager.getLogger(SqliteDAO.class);
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	@Transactional(readOnly = true)
	public List<Word> getAllEwords() {
		List<Word> ewordList = jdbcTemplate.query("select * from ewords", new WordRowMapper());
		return ewordList;
	}

	@Override
	public Boolean addEWord(Word word) {
		final String sql = "insert into ewords(eword, cword) values(?,?)";

		KeyHolder holder = new GeneratedKeyHolder();

		jdbcTemplate.update(new PreparedStatementCreator() {

			@Override
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
				ps.setString(1, word.getEword());
				ps.setString(2, word.getCword());
				return ps;
			}
		}, holder);

		int newWordId = holder.getKey().intValue();
		word.setId(newWordId);
		return true;
	}

	@Override
	public Boolean delEword(Word word) {
		final String sql = "delete from ewords where id ==?";
		jdbcTemplate.update(sql, new Object[] { word.getId() }, new int[] { java.sql.Types.INTEGER });
		return true;
	}

	@Override
	@Transactional(readOnly = true)
	public Word getWord(Word word) {
		Word eword = null;
		eword = jdbcTemplate.queryForObject("select * from ewords where eword=? and cword=?",
				new Object[] { word.getEword(), word.getCword() }, new WordRowMapper());
		return eword;

	}
}
