package Command;

import Dtos.CartItem;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Brian Millar
 */
public class AddSongToCartCommand implements Command {

    @Override
    public String executeCommand(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        CartItem item = new CartItem(true, Integer.parseInt(request.getParameter("songid")), 1, Double.parseDouble(request.getParameter("price")), null);
        if (session.getAttribute("cart") == null)
            session.setAttribute("cart", new ArrayList<>());
        ArrayList<CartItem> cart = (ArrayList<CartItem>)session.getAttribute("cart");
        for (CartItem i : cart)
            if ((i.getProdId() == item.getProdId()) && i.getType())
                return "/music.jsp"; //Don't add it twice
        cart.add(item);
        session.setAttribute("cart", cart);
        return "/music.jsp?addedToCart=yes";
    }
}
