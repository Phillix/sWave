package Daos;

import Dtos.User;
import Security.UserSecurity;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.sql.DataSource;

/**
 * The UsersDao class is used for communicating with the user table in the database
 * @author Phillix
 * @author Austin
 * @author Brian Millar
 */

public class UsersDao extends Dao implements UserDaoInterface {
    private final boolean DEBUG = sWave.Server.DEBUGGING;

    private final String TABLE_NAME = "USERS";
    private final String USERID     = "USERID";
    private final String UNAME      = "USERNAME";
    private final String FNAME      = "FNAME";
    private final String LNAME      = "LNAME";
    private final String USER_NAME  = "USERNAME";
    private final String EMAIL      = "EMAIL";
    private final String PASSWORD   = "PASSWD";
    private final String ADD1       = "ADD1";
    private final String ADD2       = "ADD2";
    private final String CITY       = "CITY";
    private final String COUNTY     = "COUNTY";
    private final String SKIN       = "SKIN";
    private final String LANG       = "LANGPREF";
    private final String PICTURE    = "PICTURE";
    private final String ADMIN      = "ADMIN";
    
    /**
     * Default Constructor for UsersDao
     */
    public UsersDao() {
        super();
    }
    
    /**
     * Parameterized Constructor for UsersDao
     * @param ds The DataSource to use for connections
     */
    public UsersDao(DataSource ds) {
        super(ds);
    }
    
    /**
     * Checks to see if the username is in the database
     * @param username The username we wish to check is in the database
     * @return 0 if it is in the database; -5 if it isn't in the database; -1 through -4 for errors
     */
    @Override
    public int checkUname(String username) {
        Connection con       = null;
        PreparedStatement ps = null;
        ResultSet rs         = null;

        try {
            con          = getConnection();
            String query = "SELECT " + USER_NAME + " FROM " + TABLE_NAME + " WHERE " + USER_NAME + " = ?";
            ps           = con.prepareStatement(query);
            ps.setString(1, username);
            rs = ps.executeQuery();

            if(rs.next())
                return SUCCESS; //success in this case means details exist and therefore user cannot use them
        }
        catch (SQLException ex2) {
            if (DEBUG)
                ex2.printStackTrace();
            return SQLEX;
        }
        finally {
            try {
                if(rs  != null)
                    rs.close();
                if(ps  != null)
                    ps.close();
                if(con != null)
                    freeConnection(con);
            }
            catch(SQLException e) {
                if (DEBUG)
                    e.printStackTrace();
               return CONNCLOSEFAIL;
            }
        }
        return OTHER;
    }

    /**
     * Registering a user by taking in a user object and attempting to insert into the database
     * @param u The user we wish to register
     * @return 0 if it inserted fine; -5 if it didn't insert; -1 through -4 for errors
     */
    @Override
    public int register(User u) {
        Connection con       = null;
        PreparedStatement ps = null;

        try {
            con          = getConnection();
            String query = "INSERT INTO " +
                           TABLE_NAME + " (" +
                           EMAIL      + ", " +
                           PASSWORD   + ", " +
                           USER_NAME  + ", " +
                           FNAME      + ", " +
                           LNAME      + ", " +
                           ADD1       + ", " +
                           ADD2       + ", " +
                           CITY       + ", " +
                           COUNTY     + ", " +
                           SKIN       + ", " +
                           LANG       + ", " +
                           ADMIN      + ") VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            ps = con.prepareStatement(query);
            ps.setString(1, u.getEmail());
            ps.setString(2, u.getPassword());
            ps.setString(3, u.getUsername());
            ps.setString(4, u.getFname());
            ps.setString(5, u.getLname());
            ps.setString(6, u.getAdd1());
            ps.setString(7, u.getAdd2());
            ps.setString(8, u.getCity());
            ps.setString(9, u.getCounty());
            ps.setString(10, u.getSkin());
            ps.setString(11, u.getLangPref());
            ps.setBoolean(12, u.isIsAdmin());

            if (ps.executeUpdate() > 0)
                return SUCCESS; //It successfully inserted into the database
        }
        catch (SQLException ex2) {
            if (DEBUG)
                ex2.printStackTrace();
            return SQLEX;
        }
        finally {
            try {
                if(ps  != null)
                    ps.close();
                if(con != null)
                    freeConnection(con);
            }
            catch(SQLException e) {
                if (DEBUG)
                    e.printStackTrace();
               return CONNCLOSEFAIL;
            }
        }
        return OTHER;
    }

    /**
     * Used for logging in a user
     * @param email String email to check against the database
     * @param password String password to check against the database
     * @return user object based on successful login, returns null Users object if not found
     */
    @Override
    public User logIn(String email, String password) {

        Connection con       = null;
        PreparedStatement ps = null;
        ResultSet rs         = null;
        User u               = null;
        UserSecurity ms      = new UserSecurity();

        try{
            con          = getConnection();
            String query = "SELECT * FROM " + TABLE_NAME + " WHERE " + EMAIL + " = ?";
            ps           = con.prepareStatement(query);
            ps.setString(1, email);
            rs = ps.executeQuery();

            if(rs.next()) {
                String dbPass = rs.getString(PASSWORD);
                if(ms.checkPassword(password.toCharArray(), dbPass)) {
                    u = new User();
                    u.setUserId(rs.getInt(USERID));
                    u.setUsername(rs.getString(UNAME));
                    u.setPassword("uh-uh-uh! you didnt say the magic word!");
                    u.setEmail(rs.getString(EMAIL));
                    u.setFname(rs.getString(FNAME));
                    u.setLname(rs.getString(LNAME));
                    u.setAdd1(rs.getString(ADD1));
                    u.setAdd2(rs.getString(ADD2));
                    u.setCity(rs.getString(CITY));
                    u.setCounty(rs.getString(COUNTY));
                    u.setSkin(rs.getString(SKIN));
                    u.setLangPref(rs.getString(LANG));
                    u.setIsAdmin(rs.getBoolean(ADMIN));

                    return u;
                }
            }
        }
        catch(Exception e) {
            if (DEBUG)
                e.printStackTrace();
        }
        finally {
            try {
                if(rs  != null)
                    rs.close();
                if(ps  != null)
                    ps.close();
                if(con != null)
                    freeConnection(con);
            }
            catch(SQLException e) {
                if (DEBUG)
                    e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * for use at registration form
     * @param email String email to check if already exists
     * @param username String username to check if already exists
     * @return return integer value indicating result: 0 = one or both already exists in the database
     */
    @Override
    public int checkDetails(String email, String username) {
        Connection con       = null;
        PreparedStatement ps = null;
        ResultSet rs         = null;

        try {
            con          = getConnection();
            String query = "SELECT " + EMAIL + " FROM " + TABLE_NAME + " WHERE " + EMAIL + " = ? OR " + USER_NAME + " = ?";
            ps           = con.prepareStatement(query);
            ps.setString(1, email);
            ps.setString(2, username);
            rs = ps.executeQuery();

            if(rs.next())
                return SUCCESS;
        }
        catch (SQLException ex2) {
            if (DEBUG)
                ex2.printStackTrace();
            return SQLEX;
        }
        finally {
            try {
                if(rs  != null)
                    rs.close();
                if(ps  != null)
                    ps.close();
                if(con != null)
                    freeConnection(con);
            }
            catch(SQLException e) {
                if (DEBUG)
                    e.printStackTrace();
               return CONNCLOSEFAIL;
            }
        }
        return OTHER;
    }
    
    
    /**
     * Getting a users id based on email and username
     * @param email
     * @param username
     * @return the id of the user with the passing in username and email
     */
    @Override
    public int getUserId(String email, String username) {
        Connection con       = null;
        PreparedStatement ps = null;
        ResultSet rs         = null;

        try {
            con          = getConnection();
            String query = "SELECT " + USERID + " FROM " + TABLE_NAME + " WHERE " + EMAIL + " = ? AND " + USER_NAME + " = ?";
            ps           = con.prepareStatement(query);
            ps.setString(1, email);
            ps.setString(2, username);
            rs = ps.executeQuery();

            if(rs.next())
                return rs.getInt(USERID);
        }
        catch (SQLException ex2) {
            if (DEBUG)
                ex2.printStackTrace();
            return SQLEX;
        }
        finally {
            try {
                if(rs  != null)
                    rs.close();
                if(ps  != null)
                    ps.close();
                if(con != null)
                    freeConnection(con);
            }
            catch(SQLException e) {
                if (DEBUG)
                    e.printStackTrace();
               return CONNCLOSEFAIL;
            }
        }
        return OTHER;
    }

    /**
     * A method for deleting a user from the users table based on email
     * @param email The email of the user you wish to delete
     * @return 0 if the email was there and got deleted; -5 if it didn't exist; -1 through -4 for errors
     */
    @Override
    public int deleteUser(String email) {
        Connection con       = null;
        PreparedStatement ps = null;

        try{
            con          = getConnection();
            String query = "DELETE FROM " + TABLE_NAME + " WHERE " + EMAIL + " = ?";
            ps           = con.prepareStatement(query);
            ps.setString(1, email);
            boolean check = ps.executeUpdate() > 0;
            if (check) return SUCCESS;
        }
        catch (SQLException ex2) {
            if (DEBUG)
                ex2.printStackTrace();
            return SQLEX;
        }
        finally {

            try {
                if(ps  != null)
                    ps.close();
                if(con != null)
                    freeConnection(con);
            }
            catch(SQLException e) {
                if (DEBUG)
                    e.printStackTrace();
                return SQLEX;
            }
        }
        return OTHER;
    }
    
    /**
     * Used for changing the users skin
     * @param skin The skin the user wishes to change to
     * @param userid The id of the user who wishes to change skin
     * @return A range of ints based of success or failure
     */
    @Override
    public int changeSkin(String skin, int userid) {
        Connection con       = null;
        PreparedStatement ps = null;

        try{
            con          = getConnection();
            String query = "UPDATE " + TABLE_NAME + " SET " + SKIN + " = ? WHERE " + USERID + " = ?";
            ps           = con.prepareStatement(query);
            ps.setString(1, skin);
            ps.setInt(2, userid);
            if (ps.executeUpdate() > 0)
                return SUCCESS;
        }
        catch (SQLException ex2) {
            if (DEBUG)
                ex2.printStackTrace();
            return SQLEX;
        }
        finally {
            try {
                if(ps  != null)
                    ps.close();
                if(con != null)
                    freeConnection(con);
            }
            catch(SQLException e) {
                if (DEBUG)
                    e.printStackTrace();
                return SQLEX;
            }
        }
        return OTHER;
    }
    
    /**
     * Getting a user based on his id
     * @param id the of the user you wish to get
     * @return a User Object built from the users id
     */
    @Override
    public User getUserById(int id) {

        Connection con       = null;
        PreparedStatement ps = null;
        ResultSet rs         = null;
        User u               = null;
        UserSecurity ms      = new UserSecurity();

        try{
            con          = getConnection();
            String query = "SELECT * FROM " + TABLE_NAME + " WHERE " + USERID + " = ?";
            ps           = con.prepareStatement(query);
            ps.setInt(1, id);
            rs = ps.executeQuery();


            if(rs.next()) {
                String dbPass = rs.getString(PASSWORD);
                u = new User();
                u.setUserId(rs.getInt(USERID));
                u.setUsername(rs.getString(UNAME));
                u.setPassword("uh-uh-uh! you didnt say the magic word!");
                u.setEmail(rs.getString(EMAIL));
                u.setFname(rs.getString(FNAME));
                u.setLname(rs.getString(LNAME));
                u.setAdd1(rs.getString(ADD1));
                u.setAdd2(rs.getString(ADD2));
                u.setCity(rs.getString(CITY));
                u.setCounty(rs.getString(COUNTY));
                u.setSkin(rs.getString(SKIN));
                u.setLangPref(rs.getString(LANG));
                u.setIsAdmin(rs.getBoolean(ADMIN));

                return u;
            }
        }
        catch(Exception e) {
            if (DEBUG)
                e.printStackTrace();
        }
        finally {
            try {
                if(rs  != null)
                    rs.close();
                if(ps  != null)
                    ps.close();
                if(con != null)
                    freeConnection(con);
            }
            catch(SQLException e) {
                if (DEBUG)
                    e.printStackTrace();
            }
        }
        return null;
    }
    
    /**
     * This method is used for updating a user, userid will be in the user parameter
     * @param u The user you wish to update and updated details
     * @return SUCCESS if it successfully updated, OTHER if not
     */
    @Override
    public int updateUser(User u) {
        Connection con       = null;
        PreparedStatement ps = null;
        try{
            con          = getConnection();
            String query = "UPDATE " + TABLE_NAME + " SET " + EMAIL + " = ?, " + USER_NAME + " = ?, "
                    + FNAME + " = ?, " + LNAME + " = ?, " + ADD1 + " = ?, " + ADD2 + " = ?, " + CITY + " = ?, " + COUNTY + " = ?, " + SKIN
                    + " = ?, " + LANG + " = ? WHERE " + USERID + " = ?";
            ps           = con.prepareStatement(query);
            ps.setString(1, u.getEmail());
            ps.setString(2, u.getUsername());
            ps.setString(3, u.getFname());
            ps.setString(4, u.getLname());
            ps.setString(5, u.getAdd1());
            ps.setString(6, u.getAdd2());
            ps.setString(7, u.getCity());
            ps.setString(8, u.getCounty());
            ps.setString(9, u.getSkin());
            ps.setString(10, u.getLangPref());
            ps.setInt(11, u.getUserId());
            int result = ps.executeUpdate();
            if (result > 0) return SUCCESS;

        }
        catch(Exception e) {
            if (DEBUG)
                e.printStackTrace();
        }
        finally {
            try {
                if(ps  != null)
                    ps.close();
                if(con != null)
                    freeConnection(con);
            }
            catch(SQLException e) {
                if (DEBUG)
                    e.printStackTrace();
            }
        }
        return OTHER;
    }

    /**
     * Used for searching for users
     * @param searchWord String of text to use in searching
     * @return a Collection of successful User matches
     */
    @Override
    public ArrayList<User> searchUsers(String searchWord) {
        
        Connection con       = null;
        PreparedStatement ps = null;
        ResultSet rs         = null;
        User u               = null;
        ArrayList<User> users;

        try {

            con = getConnection();
            ps = con.prepareStatement("SELECT * FROM " + TABLE_NAME + " WHERE " + UNAME + " LIKE ?");
            ps.setString(1, "%" + searchWord + "%");
            rs = ps.executeQuery();
            users = new ArrayList<>();

            while(rs.next()) {
                u = new User();
                u = getUserById(rs.getInt(USERID));
                users.add(u);
            }
        }
        catch(Exception e) {
            if(DEBUG) {
                e.printStackTrace();
            }
            return null;
        }
        finally {
            try {
                if(ps != null) {
                    ps.close();
                }
                if(con != null) {
                    freeConnection(con);
                }
                if (rs != null) {
                    rs.close();
                }
            }
            catch(SQLException e) {
                if(DEBUG) {
                    e.printStackTrace();
                }
                return null;
            }
        }
        return users;
    }

    @Override
    public int addUserPicture(int id, byte[] buffer) {
        Connection con       = null;
        PreparedStatement ps = null;

        try{
            con          = getConnection();
            String query = "UPDATE " + TABLE_NAME + " SET " + PICTURE + " = ? WHERE " + USERID + " = ?";
            ps           = con.prepareStatement(query);
            Blob b = con.createBlob();
            b.setBytes(1, buffer);
            ps.setBlob(1, b);
            ps.setInt(2, id);
            if (ps.executeUpdate() > 0)
                return SUCCESS;
        }
        catch (SQLException ex2) {
            if (DEBUG)
                ex2.printStackTrace();
            return SQLEX;
        }
        finally {
            try {
                if(ps  != null)
                    ps.close();
                if(con != null)
                    freeConnection(con);
            }
            catch(SQLException e) {
                if (DEBUG)
                    e.printStackTrace();
                return SQLEX;
            }
        }
        return OTHER;
    }

    @Override
    public byte[] getUserPicture(int id) {
        Connection con       = null;
        PreparedStatement ps = null;
        ResultSet rs         = null;

        try {
            con          = getConnection();
            String query = "SELECT " + PICTURE + " FROM " + TABLE_NAME + " WHERE " + USERID + " = ?";
            ps           = con.prepareStatement(query);
            ps.setInt(1, id);
            rs = ps.executeQuery();

            if(rs.next()) {
                Blob b = rs.getBlob(PICTURE);
                if (b != null)
                    return b.getBytes(1, (int)b.length());
            }
        }
        catch (SQLException ex2) {
            if (DEBUG)
                ex2.printStackTrace();
        }
        finally {
            try {
                if(rs  != null)
                    rs.close();
                if(ps  != null)
                    ps.close();
                if(con != null)
                    freeConnection(con);
            }
            catch(SQLException e) {
                if (DEBUG)
                    e.printStackTrace();
            }
        }
        return null;
    }
}
