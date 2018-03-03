package org.bearfly.ewords.unitest.utils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.bearfly.ewords.utils.JDBCUtils;
import org.junit.Test;

public class UtilsTest {
    @Test
    public void testJDBCUtils() {

        Connection connection = null;
        try {
            connection = JDBCUtils.getConnection("org.sqlite.JDBC", "jdbc:sqlite::resource:data/ewords.db", "", "");
            func1(connection);
            System.out.println("Success!");
        }  catch (SQLException e) {
            System.err.println(e.getMessage());
        } catch(Exception e) {
            e.printStackTrace();
        } finally{
            try {
                if (connection != null)
                    connection.close();
            } catch (SQLException e) {
                // connection close failed.
               e.printStackTrace();
            }
        }
    }
    public static void func1(Connection connection) throws SQLException {
        Statement statement = connection.createStatement();
       // Statement statement1 = connection.createStatement();
        statement.setQueryTimeout(30); // set timeout to 30 sec.
        // 执行查询语句
        ResultSet rs = statement.executeQuery("select * from ewords");
        while (rs.next()) {
            String col1 = rs.getString("eword");
            String col2 = rs.getString("cword");
            System.out.println("col1 = " + col1 + "  col2 = " + col2);
            
            // 执行插入语句操作
//            statement1.executeUpdate("insert into table_name2(col2) values('" + col2_value + "')");
            // 执行更新语句
//            statement1.executeUpdate("update table_name2 set 字段名1=" +  字段值1 + " where 字段名2='" +  字段值2 + "'");
        }
    }
}
