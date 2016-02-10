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
        <title>TODO supply a title</title>
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
            var play = 0;
            
            function playNext() {

                $("trackList").innerHTML = "";

                for (var i = 0; i < $("fopen").files.length; i++) {
                    if (i == play)
                        var x = "<tr class='sec'><td class='move'><img src='images/move.png'/></td><td class='art'><img src='images/test.png'/></td><td class='trackTitle'>" + $("fopen").files[i].name + "</td><td class='artistName'>Artist Name</td><td class='albumName'>Album Name</td><td class='spacer'>&#160;</td><td class='buyButton'><button>Buy</button></td><td class='playButton'><button onclick='getElementById('player').play()'>Play</button></td></tr>";
                    else
                        var x = "<tr><td class='move'><img src='images/move.png'/></td><td class='art'><img src='images/test.png'/></td><td class='trackTitle'>" + $("fopen").files[i].name + "</td><td class='artistName'>Artist Name</td><td class='albumName'>Album Name</td><td class='spacer'>&#160;</td><td class='buyButton'><button>Buy</button></td><td class='playButton'><button onclick='getElementById('player').play()'>Play</button></td></tr>"
                    $("trackList").innerHTML = $("trackList").innerHTML + x;
                }

                $("fileName").innerHTML = "File Name: <span style='float:right;'>" + $("fopen").files[play].name + "</span>";
                $("fileType").innerHTML = "File Type: <span style='float:right;'>" + $("fopen").files[play].type + "</span>";
                $("fileSize").innerHTML = "File Size: <span style='float:right;'>" + ($("fopen").files[play].size / 1024 / 1024) + "</span>";
                $("fileDate").innerHTML = "Date Modified: <span style='float:right;'>" + $("fopen").files[play].lastModified + "</span>";

                $("player").src = window.URL.createObjectURL($("fopen").files[play++]);
                $("player").play();

                if (play == $("fopen").files.length) play = 0;
            }

            function setVol(x) {
                $("track").volume = x / 10;
            }
            
            function playPrevious() {
                play -= 2;
                playNext();
            }
        </script>
    </head>
    <body>
        <header>
            <img id="logo" src="images/logo.png"/>
            <img style="margin-top: 7px; margin-left: 210px;" src="images/knob.png" width="45" height="45"/>
            <img style="margin-top: 7px; margin-left: 0px;" src="images/knob.png" width="45" height="45"/>
            <a href="login.jsp">Log In</a>
            <input id="searchBox" type="search" placeholder="Search"/>
        </header>
        <aside>
        </aside>
        <section>
            Registration Failed: <%=request.getParameter("regFail")%>
            <% if (session.getAttribute("user") != null) {%>
                    Welcome <%=((User)session.getAttribute("user")).getFname()%>
                <%}
            %>
            <h2>Library</h2>
            <label>Import Tracks </label><input id="fopen" type="file" accept=".mp3" multiple onchange="playNext()"/>
            <button onclick="playNext()">Next</button><button onclick="playPrevious()">Previous</button>
            <br/>
            <table id="trackList" cellspacing="0">
            </table>
        </section>
        <footer>
            <label id="fileName"></label>
            <label id="fileType"></label>
            <label id="fileSize"></label>
            <label id="fileDate"></label>
            <audio id="player"></audio>
        </footer>
    </body>
</html>