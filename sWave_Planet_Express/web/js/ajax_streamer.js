function streamNG(songid) {
    var request  = new XMLHttpRequest();
    var formdata = new FormData();
    request.responseType = 'blob';
    request.open("POST", "UserActionServlet", true);
    formdata.append("action", "streamNG");
    formdata.append("songid", songid);
    request.onload = function() {
        blob = this.response;
        if (blob !== null && blob.size !== 0) {
            /*
                In the old streamer it was okay to call initsWaveAudio() every 
                time as the page was reloaded, now however doing so will keep
                generating new a visualizer canvas every time and cause it to
                freeze.
            */
            if($("player").src === "") {
                $("player").src = genURL(blob);
                initsWaveAudio();
            } else {
                $("player").src = genURL(blob);
                $("player").play();
            }
            //Remove focus from the current focused element so keyboard controls 
            //can be used straight away.
            document.activeElement.blur();
        }
    };
    request.send(formdata);
}
