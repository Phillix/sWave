function stream(songid, onPlayingPage) {
    var request  = new XMLHttpRequest();
    var formdata = new FormData();
    request.responseType = 'blob';
    request.open("POST", "UserActionServlet", true);
    formdata.append("action", "stream");
    formdata.append("songid", songid);
    request.onload = function() {
        blob = this.response;
        if (blob !== null && blob.size !== 0) {
            initsWaveAudio(genURL(blob));
            lStore("currentSongId", songid);
            setSongInfoDisplay(songid, onPlayingPage);
        }
    };
    request.send(formdata);
}

/*
 * Another AJAX request to get just the text information may seem excessive 
 * since the data can be got from Java in the page on compilation however since 
 * starting a new stream is done with AJAX no page reload happens. The data 
 * can be updated by having a JavaScript call with its parameters added by Java 
 * at compile time however songs can contain all kinds of quotes and other 
 * characters that leaves that method open to all kinds of bugs so would require 
 * some extra logic just to escape everything hence why I think this extra 
 * request to just get the text as text from the server and setting it directly 
 * without passing it to a JS function makes most sense.
 */

function setSongInfoDisplay(songid, onPlayingPage) {
    var request  = new XMLHttpRequest();
    var formdata = new FormData();
    request.responseType = 'text';
    request.open("POST", "UserActionServlet", true);
    formdata.append("action", "songInfoDisplay");
    formdata.append("songid", songid);
    request.onload = function() {
        text = this.response;
        if (text !== null) {
            $("songInfoDisplay").innerText     = text;
            $("songInfoDisplay").style.display = "block";
            if (onPlayingPage) {
                $("songInfoDisplayLarge").innerText = text;
                $("songInfoDisplayLarge").style.display = "block";
                loadArtwork(songid, $("songArtLarge"));
            }
        }
    };
    request.send(formdata);
}
