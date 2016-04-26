function openChat(friendId) {
    $("chat").style.display = "block";
}

function fetchMessage(messageId) {
    var request  = new XMLHttpRequest();
    var formdata = new FormData();
    request.responseType = 'text';
    request.open("POST", "UserActionServlet", true);
    formdata.append("action", "fetchMessage");
    formdata.append("messageId", messageId);
    request.onload = function() {
        text = this.response;
        if (text !== null) {
            var msgBubble = generate("li", "theMessages");
            msgBubble.setAttribute("class", "chatMessageThem");
            msgBubble.innerText = text;
        }
    };
    request.send(formdata);
}

function showHideChat() {
    if (document.getElementById("chat").style.height === "280px")
        $("chat").style.height = "30px";
    else
        $("chat").style.height = "280px";
}
