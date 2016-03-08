function initsWaveAudio() {
    //Calls to Macgril Framework's audio.js
    initAudioSystem();
    addAudioSource($("player"));
    playAudioSource($("player"), $("trackTimer"), $("progress"), $("scrubber"));
}