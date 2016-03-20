function initsWaveAudio() {
    //Calls to Macgril Framework's audio.js
    initAudioSystem();
    addAudioSource($("player"));
    listenForEvents();
    playAudioSource($("player"), $("trackTimer"), $("progress"), $("scrubber"));
}

function listenForEvents() {
    $("player").addEventListener("play", function() {
        $("playButton").style.display   = "none";
        $("pauseButton1").style.display = "block";
        $("pauseButton2").style.display = "block";
        $("playerStatus").innerHTML = "Playing";
        recordTime();
    });
    $("player").addEventListener("pause", function() {
        $("playButton").style.display   = "block";
        $("pauseButton1").style.display = "none";
        $("pauseButton2").style.display = "none";
        $("playerStatus").innerHTML = "Paused";
    });
    $("player").addEventListener("progress", function() {$("playerStatus").innerHTML = "Downloading...";});
}

function playPause() {
    if (!$("player").paused)
        $("player").pause();
    else
        $("player").play();
}

function jumpTo(e) {
    $("player").currentTime = $("player").duration * ((e.clientX - 10) / (window.innerWidth - 20));
}

function showScrubber() {
    $("scrubber").style.MozTransform = "scale(1.0)";
}

function hideScrubber() {
    $("scrubber").style.MozTransform = "scale(0.0)";
}

function recordTime() {
    var currTime = $("player").currentTime;
    if (currTime != null && currTime != undefined) {
        x = "?time=" + currTime;
        $("indexLink").href   = "index.jsp"   + x;
        $("shopLink").href    = "shop.jsp"    + x;
        $("accountLink").href = "account.jsp" + x;
        $("aboutLink").href   = "about.jsp"   + x;
        $("cartLink").href    = "cart.jsp"    + x;
        if (document.getElementById("musicLink") !== null && document.getElementById("musicLink") !== undefined)
            $("musicLink").href = "music.jsp" + x;
        if (document.getElementById("index2Link") !== null && document.getElementById("index2Link") !== undefined)
            $("index2Link").href = "index.jsp" + x;
    }
    setTimeout(recordTime, 500);
}
