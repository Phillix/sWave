package Command;

import Daos.OrderDao;
import Daos.OrderMerchDao;
import Daos.OrderSongDao;
import Dtos.CartItem;
import Dtos.Order;
import Dtos.OrderMerch;
import Dtos.OrderSong;
import Dtos.User;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Austin
 */
public class CheckoutCommand implements Command {

    @Override
    public String executeCommand(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        String forwardToJsp;
        int userId = ((User)session.getAttribute("user")).getUserId();
        ArrayList<CartItem> cart = (ArrayList)session.getAttribute("cart");
        
        if (cart != null) {
            Order order = new Order(userId, 0);
            OrderDao od = new OrderDao();
            OrderSongDao osd = new OrderSongDao();
            OrderMerchDao omd = new OrderMerchDao();
            od.createOrder(order);
            order = od.getCurrentOrder(userId);
            
            
            for (CartItem i: cart) {
                if (i.getType()) {
                    OrderSong os = new OrderSong(order.getOrderId(), i.getProdId(), i.getPrice());
                    osd.createOrderSong(os);
                } else {
                    OrderMerch om = new OrderMerch(order.getOrderId(), i.getProdId(), i.getQty(), i.getPrice());
                    omd.createOrder(om);
                }
            }
            
            //"Flag" for checking if the order was successful
            session.setAttribute("wasActuallyBought", true);
            return "/receipt.jsp";
        }
        session.setAttribute("wasActuallyBought", false);
        return "/error.jsp?msg=Order%20not%20successful";
    }
    
}
