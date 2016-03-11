package Daos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Phillix
 */
public class Dao {
    private static final boolean DEBUG = sWave.Debugging.debug;

    // Integer returns for DAO's
    protected static final int SUCCESS       = 0;
    protected static final int CLASSNOTFOUND = -1;
    protected static final int SQLEX         = -2;
    protected static final int CONNCLOSEFAIL = -3;
    protected static final int SQLINTEG      = -4;
    protected static final int OTHER         = -5;

    public Connection getConnection() throws ClassNotFoundException, SQLException {

        String driver   = sWave.Server.dbDriver;

        String url      = sWave.Server.dbProtocol + 
                          sWave.Server.domain    + ":" + 
                          sWave.Server.MySQLPort + "/" + 
                          sWave.Server.dbName;

        String username = sWave.Server.MySQLUsername;
        String password = sWave.Server.MySQLPassword;

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
