package Daos;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.logging.Logger;
import javax.sql.DataSource;

/**
 *
 * @author Austin
 */
public class MyDataSource implements DataSource {

    @Override
    public Connection getConnection() throws SQLException {
        String driver = sWave.Server.DB_DRIVER;
        //           jdbc:mysql://              localhost              :    3306                       /    SWAVE_TEST
        String url = sWave.Server.DB_PROTOCOL + sWave.Server.DOMAIN + ":" + sWave.Server.MYSQL_PORT + "/" + sWave.Server.TEST_DB_NAME;
        String username = sWave.Server.MYSQL_USERNAME;
        String password = sWave.Server.MYSQL_PASSWORD;
        Connection con = null;
        
        try 
        {
            Class.forName(driver);
            con = DriverManager.getConnection(url, username, password);
        } 
        catch (ClassNotFoundException ex1) 
        {
            System.out.println("Failed to find driver class " + ex1.getMessage());
            System.exit(1);
        } 
        catch (SQLException ex2) 
        {
            System.out.println("Connection failed " + ex2.getMessage());
            System.exit(2);
        }
        return con;
    }

    @Override
    public Connection getConnection(String username, String password) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public PrintWriter getLogWriter() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setLogWriter(PrintWriter out) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setLoginTimeout(int seconds) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getLoginTimeout() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Logger getParentLogger() throws SQLFeatureNotSupportedException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public <T> T unwrap(Class<T> iface) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isWrapperFor(Class<?> iface) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
