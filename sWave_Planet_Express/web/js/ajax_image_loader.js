function loadArtwork(songid, image) {
    var request  = new XMLHttpRequest();
    var formdata = new FormData();
    request.responseType = 'blob';
    request.open("POST", "UserActionServlet", true);
    formdata.append("action", "loadArtwork");
    formdata.append("songid", songid);
    request.onload = function() {
        var blob = this.response;
        if (blob !== null && blob.size !== 0)
            image.src = genURL(blob);
        else
            image.src = "images/MP3.png";
    };
    request.send(formdata);
}

function loadUserPicture(onAccountPage) {
    var request  = new XMLHttpRequest();
    var formdata = new FormData();
    request.responseType = 'blob';
    request.open("POST", "UserActionServlet", true);
    formdata.append("action", "loadUserPicture");
    request.onload = function() {
        var blob = this.response;
        if (blob !== null && blob.size !== 0) {
            var url = genURL(blob);
            $("userPic").src = url;
            if (onAccountPage)
                $("largeUserPic").src = url;
        }
    };
    request.send(formdata);
}
