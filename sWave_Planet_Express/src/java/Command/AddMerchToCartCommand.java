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
public class AddMerchToCartCommand implements Command {

    @Override
    public String executeCommand(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        //TEMP NULL FOR IMAGE UNTIL FUNCTIONALITY ADDED
        System.out.println(request.getParameter("merchid"));
        CartItem item = new CartItem(false, Integer.parseInt(request.getParameter("merchid")), Integer.parseInt(request.getParameter("qty")), Double.parseDouble(request.getParameter("price")), null);
        if (session.getAttribute("cart") == null)
            session.setAttribute("cart", new ArrayList<>());
        ArrayList<CartItem> cart = (ArrayList<CartItem>)session.getAttribute("cart");
        for (int i = 0; i < cart.size(); i++) {
            if (cart.get(i).getProdId() == item.getProdId() && !cart.get(i).getType()) {
                cart.get(i).setQty(cart.get(i).getQty() + item.getQty()); //if in cart just add to existing quantity
                return "/shop.jsp?addedToCart=yes";
            }
        }
        cart.add(item);
        session.setAttribute("cart", cart);
        return "/shop.jsp?addedToCart=yes";
    }
}
