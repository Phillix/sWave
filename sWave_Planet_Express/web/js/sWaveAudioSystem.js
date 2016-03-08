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