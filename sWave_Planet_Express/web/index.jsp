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
        <author>Brian Millar</author>
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
        <script src="js/macgril.js"></script>
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

                $("fileName").innerHTML = "File Name: <span style='float:right;'>" + $("fopen").files[play].name + "</span>";
                $("fileType").innerHTML = "File Type: <span style='float:right;'>" + $("fopen").files[play].type + "</span>";
                $("fileSize").innerHTML = "File Size: <span style='float:right;'>" + ($("fopen").files[play].size / 1024 / 1024) + "</span>";

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
            
            function playPause() {
                if ($("playPause").innerHTML == "Pause")
                    $("player").pause();
                else
                    $("player").play();
            }
            
            function playing() {
                $("volCtrl").value = this.volume * 10;
                $("playPause").innerHTML = "Pause";
            }
        </script>
    </head>
    <body>
        <header>
            <img id="logo" src="images/logo.png"/>
            <span style="float: left; margin-left: 220px; margin-top:20px;">
                <input id="fopen" type="file" accept=".mp3" multiple onchange="playNext()"/>
                <button onclick="playNext()">Next</button>
                <button id="playPause" onclick="playPause()">Play</button>
                <button onclick="playPrevious()">Previous</button>
                <button onclick="$('fopen').click()">Import</button>
            </span>
            <input id="searchBox" type="search" placeholder="Search"/>
            <a href="register.jsp">Register</a>
            <a href="login.jsp">Log In</a>
            <input style="background-color: transparent;" id="volCtrl" type="range" min="0" max="10" step="1" value="10" onmousemove="setVol(this.value)"/>
        </header>
        <aside>
        </aside>
        <section>
            <table id="trackList" cellspacing="0">
            </table>
        </section>
        <footer>
            <label id="fileName"></label><br/>
            <label id="fileType"></label><br/>
            <label id="fileSize"></label>
            <audio id="player" onplay="playing()" onpause="$('playPause').innerHTML='Play';"></audio>
        </footer>
    </body>
</html>