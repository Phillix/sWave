function loadArtwork(songid, image) {
    var request  = new XMLHttpRequest();
    var formdata = new FormData();
    request.responseType = 'blob';
    request.open("POST", "UserActionServlet", true);
    formdata.append("action", "loadArtwork");
    formdata.append("songid", songid);
    request.onload = function() {
        blob = this.response;
        if (blob != null && blob.size != 0)
            image.src = URL.createObjectURL(blob);
        else
            image.src = "images/MP3.png";
    };
    request.send(formdata);
}


