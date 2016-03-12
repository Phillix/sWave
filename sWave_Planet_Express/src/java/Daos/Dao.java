package Daos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Phillix
 */
public class Dao {
    private static final boolean DEBUG = sWave.Server.DEBUGGING;

    // Integer returns for DAO's
    protected static final int SUCCESS       = 0;
    protected static final int CLASSNOTFOUND = -1;
    protected static final int SQLEX         = -2;
    protected static final int CONNCLOSEFAIL = -3;
    protected static final int SQLINTEG      = -4;
    protected static final int OTHER         = -5;

    public Connection getConnection() throws ClassNotFoundException, SQLException {

        String driver   = sWave.Server.DB_DRIVER;

        String url      = sWave.Server.DB_PROTOCOL +
                          sWave.Server.DOMAIN      + ":" +
                          sWave.Server.MYSQL_PORT  + "/" +
                          sWave.Server.DB_NAME;

        String username = sWave.Server.MYSQL_USERNAME;
        String password = sWave.Server.MYSQL_PASSWORD;

        Connection con  = null;

        Class.forName(driver);
        con = DriverManager.getConnection(url, username, password);

        return con;
    }

    public void freeConnection(Connection con) {
        try {
            if (con != null) {
                con.close();
                con = null;
            }
        } catch (SQLException e) {
            if (DEBUG)
                e.printStackTrace();
        }
    }
}
