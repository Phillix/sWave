function streamNG(songid) {
    var request  = new XMLHttpRequest();
    var formdata = new FormData();
    request.responseType = 'tet';
    request.open("POST", "UserActionServlet", true);
    formdata.append("action", "socketTest");
    request.bind();
    request.send(formdata);
}
