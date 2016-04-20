function streamNG(songid) {
    var request  = new XMLHttpRequest();
    var formdata = new FormData();
    request.responseType = 'blob';
    request.open("POST", "UserActionServlet", true);
    formdata.append("action", "streamNG");
    formdata.append("songid", songid);
    request.onload = function() {
        blob = this.response;
        if (blob !== null && blob.size !== 0)
            initsWaveAudio(genURL(blob));
    };
    request.send(formdata);
}
