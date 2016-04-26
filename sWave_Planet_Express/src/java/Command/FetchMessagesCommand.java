package Command;

import Daos.MessageDao;
import Dtos.Message;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *            session
 * @author Brian Millar
 */
public class FetchMessagesCommand implements Command {

    private static final boolean DEBUG = sWave.Server.DEBUGGING;

    @Override
    public String executeCommand(HttpServletRequest request, HttpServletResponse response) {
        try {
            MessageDao mdao = new MessageDao();
            ArrayList<Message> mlist = mdao.getConversation(Integer.parseInt(request.getParameter("friendId")));
            Writer out = response.getWriter();
            if (mlist != null) {
                String msgs = "";
                for (int i = 0; i < 20 && i < mlist.size(); i++)
                    msgs += mlist.get(i) + "$$";
                out.write(msgs);
                out.flush();
            }
            out.close();
            return null; //We don't want to redirect
        } catch (IOException ex) {
            if (DEBUG)
                ex.printStackTrace();
        }
        return null;
    }
}
