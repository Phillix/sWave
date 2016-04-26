function loadArtwork(songid, image, image2) {
    var request  = new XMLHttpRequest();
    var formdata = new FormData();
    request.responseType = 'blob';
    request.open("POST", "UserActionServlet", true);
    formdata.append("action", "loadArtwork");
    formdata.append("songid", songid);
    request.onload = function() {
        var blob = this.response;
        if (blob !== null && blob.size !== 0) {
            var url = genURL(blob);
            image.src = url;
            image.addEventListener("load", function () {
               window.URL.revokeObjectURL(url); 
            });
        }
        else
            image.src = "images/MP3.png";
    };
    request.send(formdata);
}

function loadUserPicture(userid, img, img2) {
    var request  = new XMLHttpRequest();
    var formdata = new FormData();
    request.responseType = 'blob';
    request.open("POST", "UserActionServlet", true);
    formdata.append("action", "loadUserPicture");
    formdata.append("userid", userid);
    request.onload = function() {
        var blob = this.response;
        if (blob !== null && blob.size !== 0) {
            var url = genURL(blob);
            img.src = url;
            if (img2 !== null && img2 !== undefined)
                img2.src = url;
            img.addEventListener("load", function () {
                window.URL.revokeObjectURL(url);
            });
        }
    };
    request.send(formdata);
}
