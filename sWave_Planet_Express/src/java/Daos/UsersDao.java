/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Daos;

import Dtos.Users;
import Security.MuhSecurity;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Phillix
 */
public class UsersDao extends Dao {
    
    private final String TABLE_NAME = "users";
    private final String USERID     = "id";
    private final String FNAME      = "fname";
    private final String LNAME      = "lname";        
    private final String USER_NAME  = "username";
    private final String EMAIL      = "email";
    private final String PASSWORD   = "password";
    private final String SALT       = "salt";
    private final String ADD1       = "add1";
    private final String ADD2       = "add2";
    private final String CITY       = "city";
    private final String COUNTY     = "county";
    private final String ADMIN      = "isadmin";
    
    
    /**
     * 
     * @param email     = String email to check against the database
     * @param password  = String password to check against the database
     * @return          = user object based on successful login, returns null Users object if not found 
     */
    public Users logIn(String email, String password) {
        
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Users u = null;
        MuhSecurity ms = new MuhSecurity();
        
        try{
            con = getConnection();
            String query = "SELECT * FROM " + TABLE_NAME + " WHERE " + EMAIL + " = ?";
            ps =  con.prepareStatement(query);
            ps.setString(1, email);
            rs = ps.executeQuery();
            
            if(rs.next()) {
                
                String dbPass = rs.getString(PASSWORD);
                if(ms.checkPassword(password.toCharArray(), dbPass)) {
                    
                    u = new Users();
                    u.setUserId(rs.getInt(USERID));
                    u.setPassword("uh-uh-uh! you didnt say the magic word!");
                    u.setSalt("pepper?");
                    u.setEmail(rs.getString(EMAIL));
                    u.setFname(rs.getString(FNAME));
                    u.setLname(rs.getString(LNAME));
                    u.setAdd1(rs.getString(ADD1));
                    u.setAdd2(rs.getString(ADD2));
                    u.setCity(rs.getString(CITY));
                    u.setCounty(rs.getString(COUNTY));
                    u.setIsAdmin(rs.getBoolean(ADMIN));

                    return u;
                    
                }
            }
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        finally {
            
            try {
                
                if(rs != null) {
                    rs.close();
                }
                if(ps != null) {
                    ps.close();
                }
                if(con != null) {
                    freeConnection(con);
                }
            }
            catch(SQLException e) {
               
            }
        }    
        return null;
    }
    
    /**
     * for use at registration form
     * @param email    = String email to check if already exists
     * @param username = String username to check if already exists
     * @return         = return integer value indicating result: 0 = one or both already exists in the database
     */
    public int checkDetails(String email, String username) {
        
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try {
            
            con = getConnection();
            String query = "SELECT " + EMAIL + " FROM " + TABLE_NAME + " WHERE " + EMAIL + " = ? OR " + USER_NAME + " = ?";
            ps = con.prepareStatement(query);
            ps.setString(1, email);
            ps.setString(2, username);
            rs = ps.executeQuery();
            
            if(rs.next()) {
                
                return SUCCESS;
            }
            
        }
        catch (ClassNotFoundException ex1) {
            return CLASSNOTFOUND;
        }
        catch (SQLException ex2) {
            ex2.printStackTrace();
            return SQLEX;
        }
        finally {
            try {
                if(rs != null) {
                    rs.close();
                }
                if(ps != null) {
                    ps.close();
                }
                if(con != null) {
                    freeConnection(con);
                }
                
            }
            catch(SQLException e) {
               return CONNCLOSEFAIL;
            }
        }
        return OTHER;
    }
    
    
}
