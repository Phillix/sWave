function initsWaveAudio() {
    //Calls to Macgril Framework's audio.js
    initAudioSystem();
    addAudioSource($("player"));
    listenForEvents();
    playAudioSource($("player"), $("trackTimer"), $("progress"), $("scrubber"));
}

function listenForEvents() {
    $("player").addEventListener("play",     function() {$("playerStatus").innerHTML = "Playing";});
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

function clock() {
    $("clock").innerHTML = fTime(true);
    var currTime = $("player").currentTime;
    if (currTime != null && currTime != undefined) {
        x = "?time=" + currTime;
        $("indexLink").href = "index.jsp" + x;
        $("musicLink").href = "music.jsp" + x;
    }
    setTimeout(clock, 500);
}