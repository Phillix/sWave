function initsWaveAudio() {
    //Calls to Macgril Framework's audio.js
    initAudioSystem();
    addAudioSource($("player"));
    listenForEvents();
    playAudioSource($("player"), $("currentTimeDisplay"), $("durationDisplay"), $("progress"), $("scrubber"));
}

function listenForEvents() {
    $("player").addEventListener("play", function() {
        $("playButton").style.display   = "none";
        $("pauseButton1").style.display = "block";
        $("pauseButton2").style.display = "block";
    });

    $("player").addEventListener("pause", function() {
        $("playButton").style.display   = "block";
        $("pauseButton1").style.display = "none";
        $("pauseButton2").style.display = "none";
    });
    
    document.body.addEventListener("keypress", function(event) {
        if ((event.which === 32) || (event.keyCode === 32)) {
            /*
             * Don't perform the action if user types the key in an input field 
             * or a button has focus. This prevents a bug where a user hits 
             * space while focused on the play track button while the song is 
             * playing resulting in a conflict where the song pauses for a 
             * moment then plays again from the start instantly.
             */
            var currEl = document.activeElement.toString();
            if (!currEl.contains("HTMLInputElement") && !currEl.contains("HTMLButtonElement")) {
                event.preventDefault();
                playPause();
            }
        }
        else if ((event.which === 39) || (event.keyCode === 39)) {
            //Don't perform the action if user types the key in an input field
            if (!document.activeElement.toString().contains("HTMLInputElement")) {
                event.preventDefault();
                seek($("player").currentTime + 10);
            }
        }
        else if ((event.which === 37) || (event.keyCode === 37)) {
            //Don't perform the action if user types the key in an input field
            if (!document.activeElement.toString().contains("HTMLInputElement")) {
                event.preventDefault();
                seek($("player").currentTime - 10);
            }
        }
        else if ((event.which === 38) || (event.keyCode === 38)) {
            //Don't perform the action if user types the key in an input field
            if (!document.activeElement.toString().contains("HTMLInputElement")) {
                event.preventDefault();
                seek($("player").currentTime + 30);
            }
        }
        else if ((event.which === 40) || (event.keyCode === 40)) {
            //Don't perform the action if user types the key in an input field
            if (!document.activeElement.toString().contains("HTMLInputElement")) {
                event.preventDefault();
                seek($("player").currentTime - 30);
            }
        }
    });
}

function playPause() {
    if (!$("player").paused)
        $("player").pause();
    else if ($("player").paused)
        $("player").play();
}

function jumpTo(e) {
    $("player").currentTime = $("player").duration * ((e.clientX - 132) / (window.innerWidth - 202));
}

function showScrubber() {
    $("scrubber").style.MozTransform = "scale(1.0)";
}

function hideScrubber() {
    $("scrubber").style.MozTransform = "scale(0.0)";
}
