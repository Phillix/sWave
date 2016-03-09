package Command;

/**
 *
 * @author Austin
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
        } else if ( action.equalsIgnoreCase("createTicket") ) {
            //Creates ticket
            command = new CreateTicketCommand();
        } else if ( action.equalsIgnoreCase("closeTicket") ) {
            //Closes ticket
            command = new CloseTicketCommand();
        } else if ( action.equalsIgnoreCase("upload") ) {
            //Allows File Uploading
            command = new UploadCommand();
        } else if ( action.equalsIgnoreCase("stream") ) {
            //Allows Streaming
            command = new StreamCommand();
        } else if ( action.equalsIgnoreCase("search") ) {
            //For searching for songs
            command = new SearchCommand();
        } else if ( action.equalsIgnoreCase("deleteSong") ) {
            //Deleting a song from the database
            command = new DeleteSongCommand();
        } else if ( action.equalsIgnoreCase("changeSkin") ) {
            //Allows the user to change the look of sWave
            command = new ChangeSkinCommand();
        } else if ( action.equalsIgnoreCase("checkout") ) {
            //Checking out the cart
            command = new CheckoutCommand();
        } else if ( action.equalsIgnoreCase("updateDetails") ) {
            //Updating the users details
            command = new UpdateDetailsCommand();
        } else if ( action.equalsIgnoreCase("addSongToCart") ) {
            //Adding a song to the cart
            command = new AddSongToCartCommand();
        } else {
            command = new DummyCommand();
        }
        return command;
    }
}
