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
import org.bearfly.ewords.model.EWord;
import org.bearfly.ewords.utils.JDBCUtils;
import org.springframework.stereotype.Repository;
@Repository("ewordDAO")
public class SqliteDAO implements IEWordDAO{
    private static Logger logger = LogManager.getLogger(SqliteDAO.class);
    private static Connection connection = null;
/*    static {
        try {
            connection = JDBCUtils.getConnectionByProperties("datasource.driver", "datasource.url", "", "");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }*/

    public List<EWord> getAllEwords() {
        Connection connection;
        List<EWord> ewordList = new ArrayList<EWord>();
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
                ewordList.add(new EWord(id, eword, cword));
            }
            JDBCUtils.closeConnection();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return ewordList;
    }
}
