function initsWaveAudio() {
    //Calls to Macgril Framework's audio.js
    initAudioSystem();
    addAudioSource($("player"));
    listenForEvents();
    if (lStore("playerCurrTime") !== null && lStore("playerCurrTime") !== undefined)
        $("player").currentTime = lStore("playerCurrTime");
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

function seek(x) {
    $("player").currentTime = x;
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