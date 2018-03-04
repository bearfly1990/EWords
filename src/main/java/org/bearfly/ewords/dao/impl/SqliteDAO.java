package org.bearfly.ewords.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.bearfly.ewords.dao.IEWordDAO;
import org.bearfly.ewords.model.Word;
import org.bearfly.ewords.utils.JDBCUtils;
import org.springframework.stereotype.Repository;
@Repository("ewordDAO")
public class SqliteDAO implements IEWordDAO{
    private static Logger logger = LogManager.getLogger(SqliteDAO.class);
/*    static {
        try {
            connection = JDBCUtils.getConnectionByProperties("datasource.driver", "datasource.url", "", "");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }*/
    @Override
    public List<Word> getAllEwords() {
        Connection connection;
        List<Word> ewordList = new ArrayList<Word>();
        try {
            connection = JDBCUtils.getConnection();
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30); // set timeout to 30 sec.
            // 执行查询语句
            ResultSet rs = statement.executeQuery("select * from ewords");
            while (rs.next()) {
                int id = rs.getInt("id");
                String eword = rs.getString("eword");
                String cword = rs.getString("cword");
                ewordList.add(new Word(id, eword, cword));
            }
            JDBCUtils.closeConnection();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return ewordList;
    }

    @Override
    public Boolean addEWord(Word word) {
        Connection connection;
        try {
            connection = JDBCUtils.getConnection();
            Statement statement = connection.createStatement();
            //statement.setQueryTimeout(30); // set timeout to 30 sec.
            // 执行insert语句
            String sql = String.format("insert into ewords(eword, cword) values('%s', '%s')", word.getEword(), word.getCword());
            logger.info(sql);
            statement.executeUpdate(sql);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            return false;
        }finally {
            JDBCUtils.closeConnection();
        }
        return true;
    }

    @Override
    public Boolean delEword(Word word) {
        Connection connection;
        try {
            connection = JDBCUtils.getConnection();
            Statement statement = connection.createStatement();
            //statement.setQueryTimeout(30); // set timeout to 30 sec.
            // 执行insert语句
            String sql = String.format("delete from ewords where id = %s", word.getId());
            logger.info(sql);
            statement.executeUpdate(sql);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            return false;
        }finally {
            JDBCUtils.closeConnection();
        }
        return true;
    }
    @Override
    public Word getWord(Word word) {
        Connection connection;
        List<Word> ewordList = new ArrayList<Word>();
        try {
            String sql = "select * from ewords where eword='%s' and cword='%s'"; //limit 0,5
            sql = String.format(sql, word.getEword(), word.getCword());
            logger.info(sql);
            System.out.println(sql);
            connection = JDBCUtils.getConnection();
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30); // set timeout to 30 sec.
           
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                int id = rs.getInt("id");
                String eword = rs.getString("eword");
                String cword = rs.getString("cword");
                ewordList.add(new Word(id, eword, cword));
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }finally {
            JDBCUtils.closeConnection();
        }
        
        return ewordList.size()>0 ? ewordList.get(0):null;
    }
}
