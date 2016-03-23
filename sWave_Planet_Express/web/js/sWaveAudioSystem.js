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
    
    document.body.addEventListener("keypress", function(event) {
        if ((event.which === 32) || (event.keyCode === 32)) {
            //Don't perform the action if user types the key in an input field
            if (!document.activeElement.toString().contains("HTMLInputElement")) {
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
