function initsWaveAudio() {
    //Calls to Macgril Framework's audio.js
    initAudioSystem();
    addAudioSource($("player"));
    startAudioVisualization($("visualizer"), $("visualizer").offsetWidth, $("visualizer").offsetHeight, "e3e3e3", "838383", "audioLoop()");
}

function audioLoop() {
}