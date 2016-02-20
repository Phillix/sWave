<%--
    Document   : register
    Created on : Feb 8, 2016, 10:26:50 PM
    Author     : Brian Millar
--%>
<%@page import="Dtos.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Welcome to sWave</title>
        <meta author="Brian Millar">
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" type="text/css" href="main.css"/>
        <style>
            @media screen and (max-width: 800px) {
                aside {
                    display: none;
                }

                #searchBox {
                    position:         fixed;
                    top:              60px;
                    right:            30px;
                    background-color: #1F1D26;
                    color:            #e3e3e3;
                }

                section {
                    left: 0px;
                }

                td.spacer {
                    display: none;
                }

                td.albumName {
                    display: none;
                }
            }

            @media screen and (max-width: 600px) {
                td.art {
                    display: none;
                }
            }

            @media screen and (max-width: 400px) {
                td.artistName {
                    display: none;
                }
            }
	</style>
        <script src="macgril/js/dom.js"></script>
        <script src="macgril/js/datetime.js"></script>
        <script src="js/three.min.js"></script>
        <script src="js/visualizer.js"></script>
        <script>
            /*
             * This code is not a real implementation, it is only
             * a mockup for testing purposes
             *
             */
            var play = 0;

            function playNext() {

                $("trackList").innerHTML = "";

                for (var i = 0; i < $("fopen").files.length; i++) {
                    if (i == play)
                        var x = "<tr class='sec'><td class='move'><img src='images/move.png'/></td><td class='art'><img src='images/test.png'/></td><td class='trackTitle'><marquee>" + $("fopen").files[i].name + "</marquee></td><td class='artistName'>Artist Name</td><td class='albumName'>Album Name</td><td class='spacer'>&#160;</td><td class='buyButton'><button>Buy</button></td><td class='playButton'><button onclick='getElementById('player').play()'>Play</button></td></tr>";
                    else
                        var x = "<tr><td class='move'><img src='images/move.png'/></td><td class='art'><img src='images/test.png'/></td><td class='trackTitle'>" + $("fopen").files[i].name + "</td><td class='artistName'>Artist Name</td><td class='albumName'>Album Name</td><td class='spacer'>&#160;</td><td class='buyButton'><button>Buy</button></td><td class='playButton'><button onclick='getElementById('player').play()'>Play</button></td></tr>"
                    $("trackList").innerHTML = $("trackList").innerHTML + x;
                }
                
                $("player").src = window.URL.createObjectURL($("fopen").files[play++]);
                $("player").play();

                if (play == $("fopen").files.length) play = 0;
            }

            function setVol(x) {
                $("player").volume = x / 10;
            }

            function playPrevious() {
                play -= 2;
                playNext();
            }

            function playing() {
                $("volCtrl").value = this.volume * 10;
                updateTime();
                $("playerStatus").innerHTML = "Playing";
            }
            
            function updateTime() {
                $("trackTimer").innerHTML = formatTime($("player").currentTime) + " / " + formatTime($("player").duration);
                $("progress").style.width = $("scrubber").style.left = Math.floor($("player").currentTime * ((window.innerWidth - 24) / $("player").duration)) + "px";
                setTimeout("updateTime()", 500);
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
        </script>
    </head>
    <body onload="initAudioSystem()">
        <header>
            <img id="logo" src="images/logo.png"/>
            <span style="float: left; margin-left: 220px; margin-top:20px;">
                <input id="fopen" type="file" accept="audio/*" multiple onchange="playNext()"/>
                <button onclick="$('fopen').click()">Import</button>
                <img style="position:fixed; top:5px; left:300px;" src="images/knob_ring.png" width="50" height="50"/>
                <img id="theKnob" style="-moz-transform:rotate('0deg'); position:fixed; top:5px; left:300px;" onmousedown="knobLogic($('theKnob'),event)" src="images/knob_inside.png" width="50" height="50"/>
            </span>
            <input id="searchBox" type="search" placeholder="Search"/>
            <% if (session.getAttribute("user") == null) {%>
                <a style="margin-right:10px; margin-top:20px;" href="register.jsp">Register</a>
                <a style="margin-top:20px;" href="login.jsp">Log In</a>
            <%} else {%>
                <form action="UserActionServlet" method="post">
                    <input type="hidden" name="action" value="logout"/>
                    <input type="submit" value="Log Out"/>
                </form>
                <h5 style="float:right; margin-top:20px;">Welcome <%=((User)session.getAttribute("user")).getUsername()%></h5>
            <%}%>
            <input style="background-color: transparent;" id="volCtrl" type="range" min="0" max="10" step="1" value="10" onmousemove="setVol(this.value)"/>
        </header>
        <aside>
        </aside>
        <section>
            <div id="hmm"></div>
            <% if ((request.getParameter("regFail")) != null && (request.getParameter("regFail")).equals("yes")) {%>
                <h1 style="margin-left: 30px; color: #000;">Registration Failed</h1>
                <p style="margin-left: 30px; color: #000;">A user with your credentials may already exist.</p>
                <a style="margin-left: 30px; text-shadow:1px 1px 0px #000" href="register.jsp">Try Again</a>
            <%} else {%>
                <table id="trackList" cellspacing="0">
                </table>
            <%}%>
        </section>
        <div id="visualizer">
        </div>
        <footer>
            <span id="playerStatus">No Data</span>
            <span id="controls">
                <img onclick="playPrevious()" style="width: 30px; height:30px; position:relative; top:-5px; border-radius:40px; border-width:1px; border-style:outset;" src="images/rw.png"/>
                <img onclick="playPause()" style="border-radius:60px; border-width:1px; border-style:outset;" src="images/play.png"/>
                <img onclick="playNext()" style="width: 30px; height:30px; position:relative; top:-5px; border-radius:40px; border-width:1px; border-style:outset;" src="images/fw.png"/>
            </span>
            <span id="trackTimer">
                --:-- / --:--
            </span>
            <span onclick="jumpTo(event)" onmouseover="showScrubber()" onmouseout="hideScrubber()" id="progressBG"></span>
            <span onclick="jumpTo(event)" onmouseover="showScrubber()" onmouseout="hideScrubber()" id="progress"></span>
            <img src="images/scrubber.png" onmouseover="showScrubber()" onmouseout="hideScrubber()" id="scrubber"/>
            <audio id="player" onplay="playing();"></audio>
        </footer>
        <img id="wallpaper" src="images/hmm.jpg"/>
    </body>
</html>
