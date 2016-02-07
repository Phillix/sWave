package Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author austin
 */
public interface Command {
    public String executeCommand(HttpServletRequest request, HttpServletResponse response);
}
