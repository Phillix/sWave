//Save querying the DOM more than once

var currTimeDis;
var durTimeDis;
var progressBar;
var player;
var crossfade;

function initsWaveAudio(song) {
    if (player !== null && player !== undefined) {
        crossfade.src = player.src;
        crossfade.volume = player.volume;
        crossfade.currentTime = player.currentTime;
        crossfade.play();
        var oldVol = player.volume;
        player.volume = 0.0;
        player.src = song;
        player.play();
        crossFade(oldVol);
    }
    else {
        currTimeDis = $("currTimeDisplay");
        durTimeDis  = $("durationDisplay");
        progressBar = $("progress");
        player      = new Audio();
        crossfade   = new Audio();
        player.src  = song;
        initAudioSystem();
        addAudioSource(player);
        addAudioSource(crossfade);
        listenForEvents();
        var playTime = lStore("playTime");
        if (playTime !== null && playTime !== undefined)
            player.currentTime = playTime;
        player.play();
        $("volSlider").value = player.volume * 10;
        playerUpdate();
        startAudioVisualization("visualizer", $("visualizer").offsetWidth, $("visualizer").offsetHeight);
    }
}

function crossFade(oldVol) {
    var again = false;
    if (parseFloat(crossfade.volume.toFixed(1)) > 0.0) {
        crossfade.volume = parseFloat((crossfade.volume - 0.1).toFixed(1));
        again = true;
    }
    if (parseFloat(player.volume.toFixed(1)) < oldVol) {
        player.volume = parseFloat((player.volume + 0.1).toFixed(1));
        again = true;
    }
    if (again) {
        setTimeout(function() {
            crossFade(oldVol);
        }, 500);
    } else
        $("volSlider").value = player.volume * 10;
}

function listenForEvents() {
    window.addEventListener("beforeunload", function() {
        var curr = player.currentTime;
        if (curr !== null && curr !== undefined && curr > 0.0)
            lStore("playTime", curr);
    });
    
    player.addEventListener("play", function() {
        $("playButton").style.display   = "none";
        $("pauseButton1").style.display = "block";
        $("pauseButton2").style.display = "block";
        document.activeElement.blur();
    });

    player.addEventListener("pause", function() {
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
                seek(player, player.currentTime + 10);
            }
        }
        else if ((event.which === 37) || (event.keyCode === 37)) {
            //Don't perform the action if user types the key in an input field
            if (!document.activeElement.toString().contains("HTMLInputElement")) {
                event.preventDefault();
                seek(player, player.currentTime - 10);
            }
        }
        else if ((event.which === 38) || (event.keyCode === 38)) {
            //Don't perform the action if user types the key in an input field
            if (!document.activeElement.toString().contains("HTMLInputElement")) {
                event.preventDefault();
                seek(player, player.currentTime + 30);
            }
        }
        else if ((event.which === 40) || (event.keyCode === 40)) {
            //Don't perform the action if user types the key in an input field
            if (!document.activeElement.toString().contains("HTMLInputElement")) {
                event.preventDefault();
                seek(player, player.currentTime - 30);
            }
        }
    });
}

function playPause() {
    if (!player.paused)
        player.pause();
    else if (player.paused)
        player.play();
}

function jumpTo(e) {
    player.currentTime = player.duration * ((e.clientX - 132) / (document.body.scrollWidth - 204));
}

function showScrubber() {
    $("scrubber").style.MozTransform = "scale(1.0)";
}

function hideScrubber() {
    $("scrubber").style.MozTransform = "scale(0.0)";
}

function playerUpdate() {
    var curr = player.currentTime;
    var durr = player.duration;
    currTimeDis.innerHTML = formatTime(curr);
    //It seems wasteful to constantly update the duration as it shouldn't change 
    //but this actually prevents bugs like the duration being NaN at the start.
    durTimeDis.innerHTML  = formatTime(durr);
    progressBar.style.width = Math.floor(((document.body.scrollWidth - 204) / durr) * curr) + "px";
    /* window.innerWidth causes a bug when the song list is long enough to cause 
     * scrolling as the scrollbar makes the page narrower yet the value of 
     * window.innerWidth remains the same resulting in the progress bar
     * progressing past the end of its container by the width of the scrollbar.
    */
    setTimeout("playerUpdate()", 200);
}

function updateVol() {
    player.volume = $("volSlider").value / 10;
}
