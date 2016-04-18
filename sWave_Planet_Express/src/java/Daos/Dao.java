package Daos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 *
 * @author Phillix
 * @author Austin
 */
public class Dao {
    private static final boolean DEBUG = sWave.Server.DEBUGGING;
    private DataSource dataSource;
    
    // Integer returns for DAO's
    protected static final int SUCCESS       = 0;
    protected static final int CLASSNOTFOUND = -1;
    protected static final int SQLEX         = -2;
    protected static final int CONNCLOSEFAIL = -3;
    protected static final int SQLINTEG      = -4;
    protected static final int OTHER         = -5;
    
    /**
     * Constructs a new Dao class
     */
    public Dao() {
        Connection con = null;
        String DATASOURCE_CONTEXT = "jdbc/swave";
        try {
            Context initialContext = new InitialContext();
            DataSource ds = (DataSource)initialContext.lookup("java:comp/env/" + DATASOURCE_CONTEXT);
            if (ds != null) dataSource = ds;
        } catch (NamingException ex) {
            ex.printStackTrace();
        }
    }
    
    /**
     * Overloaded Dao constructor
     * @param dataSource The datasource you wish to work with
     */
    public Dao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    /*public Connection getConnection() throws ClassNotFoundException, SQLException {

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
    }*/

    public Connection getConnection() {
        Connection con = null;
        try {
            if (dataSource != null) {
                con = dataSource.getConnection();
            } else {
                System.out.println("Failed to lookup datasource");
            }
        } catch (SQLException ex) {
            System.out.println("Connection failed");
        }
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
