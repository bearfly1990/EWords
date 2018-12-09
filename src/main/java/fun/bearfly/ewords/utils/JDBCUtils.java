package fun.bearfly.ewords.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class JDBCUtils {
    private static Logger logger = LogManager.getLogger(JDBCUtils.class);

    private static Connection connection;
    
    public static Connection getConnection() throws ClassNotFoundException, SQLException {
        connection = getConnectionByProperties("datasource.driver","datasource.url","","");
        return connection;
    }
    
    public static Connection getConnection(String driver, String url, String user, String pwd)
            throws SQLException, ClassNotFoundException {
        Class.forName(driver);
        return DriverManager.getConnection(url, user, pwd);
    }

    public static Connection getConnectionByProperties(String driverKeyStr, String urlKeyStr, String userKeyStr,
            String pwdKeyStr) throws ClassNotFoundException, SQLException {

        Connection connection = null;
        String driver = ConfigUtils.getProperty(driverKeyStr);
        String url = ConfigUtils.getProperty(urlKeyStr);
        String user = ConfigUtils.getProperty(userKeyStr);
        String password = ConfigUtils.getProperty(pwdKeyStr);
        logger.info("url=" + url + ", driver=" + driver + ", user=" + user);
        if (url == null || driver == null) {
            logger.error("url=" + url + ", driver=" + driver + ", user=" + user);
        } else {
            connection = JDBCUtils.getConnection(driver, url, user, password);
        }

        return connection;
    }
    
    public static void closeConnection() {
        try {
            if (connection != null) connection.close();
        } catch (SQLException e) {
            // connection close failed.
           e.printStackTrace();
        }
    }
    
    public static void closeConnection(Connection conn) {
        try {
            if (conn != null) conn.close();
        } catch (SQLException e) {
           e.printStackTrace();
        }
    }
    public static String getQueryStr(String filePath, String queryStr) {
        /*
         * String result = ""; try { result = XMLUtils.getValueByExpression(filePath,
         * queryStr); } catch (JDOMException | IOException e) { e.printStackTrace(); }
         * return result;
         */
        return "";
    }
}
