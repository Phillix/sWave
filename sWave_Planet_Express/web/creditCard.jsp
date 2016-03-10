<%-- 
    Document   : creditCard
    Created on : Mar 10, 2016, 3:21:15 AM
    Author     : Brian Millar
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Card Details</title>
        <script src="macgril/js/dom.js"></script>
        <script src="macgril/js/validation.js"></script>
    </head>
    <body>
        <a href="cart.jsp">Back to Cart</a>
        <form onsubmit="if (!validateCardNum()) {event.preventDefault(); alert('Please Enter a Valid Credit Card Number in the Format Shown')}" action="UserActionServlet" method="POST">
            <input type="hidden" name="action" value="checkout"/>
            <h2>Shipping Address</h2>
                <p>Please Ensure your account has an associated Address if you are ordering Merchandise</p>
                <a href="account.jsp?view=profile">Add One Now</a><br/>
            <h2>Enter Card Details</h2>
            <h5>Note: We do not store your card details</h5>
            <input id="cardNumField" onkeyup="validateCardNum()" required type="text" name="card_num" placeholder="xxxx-xxxx-xxxx-xxxx"/><br/>
            <input type="submit" value="Buy"/>
        </form>
    </body>
</html>
