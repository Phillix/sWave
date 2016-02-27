package Command;

/**
 *
 * @author austin
 */
public class CommandFactory {
    public Command createCommand(String action) {
        Command command = null;
        
        if ( action.equalsIgnoreCase("login") ) {
            //Logging a user in and storing in the session
            command = new LoginCommand();
        } else if ( action.equalsIgnoreCase("register") ) {
            //Register is the command for registering a user and adding him to the database
            command = new RegisterCommand();
        } else if ( action.equalsIgnoreCase("logout") ) {
            //Logs out the user by invalidating the session and returns to the home page
            command = new LogoutCommand();
        } else if ( action.equalsIgnoreCase("upload") ) {
            //Uploads a file and sends to user to a success or failure page
            command = new UploadCommand();
        } else {
            command = new DummyCommand();
        }
        return command;
    }
}
