package Daos;

import Dtos.User;

/**
 *
 * @author Austin
 */
public interface UserDaoInterface {
    public int checkUname(String username);
    public int register(User u);
    public User logIn(String email, String password);
    public int checkDetails(String email, String username);
    public int getUserId(String email, String username);
    public User getUserById(int id);
}
