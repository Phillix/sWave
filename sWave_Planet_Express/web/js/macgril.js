/*
    Copyright 2015 Brian Millar
    This file is part of eXastum.
    eXastum is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.
    eXastum is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.
    You should have received a copy of the GNU General Public License
    along with eXastum.  If not, see <http://www.gnu.org/licenses/>
*/

var mgID = 0;

function macgrilInit() {
    $("macgril").style.display = "none";
}

function print(x) {
    document.write(x);
}

function log(x) {
    console.log(x);
}


//DOM Functions

function $(element) {
    var id = document.getElementById(element);
    if ((id != null) && (id != undefined)) return id;
}

function generate(x,y,z) {
    var el = document.createElement(x);
    var id;
    if (z != null) id = z;
    else id = "macgrilID" + (mgID++);
    el.setAttribute("id",id);
    $(y).appendChild(el);
    return id;
}

function quickShake(element,passTwo) {
    $(element).style.transition       = "0.1s transform";
    $(element).style.MozTransition    = "0.1s transform";
    $(element).style.WebkitTransition = "0.1s transform";
    $(element).style.msTransition     = "0.1s transform";
    $(element).style.transform        = "translateX(-40px)";
    $(element).style.MozTransform     = "translateX(-40px)";
    $(element).style.WebkitTransform  = "translateX(-40px)";
    $(element).style.msTransform      = "translateX(-40px)";
    var timer1 = setTimeout(
        function() {
            $(element).style.transform       = "translateX(40px)";
            $(element).style.MozTransform    = "translateX(40px)";
            $(element).style.WebkitTransform = "translateX(40px)";
            $(element).style.msTransform     = "translateX(40px)";
            var timer2 = setTimeout(
                function() {
                    clearTimeout(timer1);
                    $(element).style.transform       = "translateX(0px)";
                    $(element).style.MozTransform    = "translateX(0px)";
                    $(element).style.WebkitTransform = "translateX(0px)";
                    $(element).style.msTransform     = "translateX(0px)";
                    clearTimeout(timer2);
                    if(!passTwo) quickShake(element,true);
                },100);
        },100);
}


//File Access & Storage

function lStore(x,y) {
        if (y == "del")     window.localStorage.removeItem(x);
        else if (y != null) window.localStorage.setItem(x,y);
        else                return window.localStorage.getItem(x);
}

function fOpen(accept) {
        if (($("fOpen") != 0) && ($("fOpen") != undefined)) $("macgril").removeChild($("fOpen"));
        generate("input","macgril","fOpen");
        $("fOpen").setAttribute("type","file");
        if ((accept != null) && (accept != undefined)) $("fOpen").setAttribute("accept", accept);
        $("fOpen").click();
        var x = window.confirm("Are you sure you want to load this file from disk?");
        if (x) if ($('fOpen').files[0] != undefined) return window.URL.createObjectURL($('fOpen').files[0]);
}


//Date & Time Functions

var mgDate = new Date();

function fDate(natural) {
    mgDate    = new Date();
    var day   = mgDate.getDay();
    var date  = mgDate.getDate();
    var month = mgDate.getMonth();
    var year  = mgDate.getFullYear().toString();
    if (natural) {
        var days   = ["Sun","Mon","Tues","Wed","Thur","Fri","Sat"];
        var months = ["Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sept","Oct","Nov","Dec"];
        var extra  = "th";
        if (date == 1 || date == 21 || date == 31) extra = "st";
        else if (date == 2 || date == 22) extra = "nd";
        else if (date == 3 || date == 23) extra = "rd";
        return days[day] + " " + date + "<sup>" + extra + "</sup> " + months[month];
    }
    else {
        month++;
        if (month < 10) month = "0" + month;
        return date + "/" + month + "/" + year.charAt(2) + year.charAt(3);
    }
}

function fTime(natural) {
    mgDate      = new Date();
    var hours   = mgDate.getHours();
    var minutes = mgDate.getMinutes();
    var seconds = mgDate.getSeconds();
    if (hours < 10)   hours   = "0" + hours;
    if (minutes < 10) minutes = "0" + minutes;
    if (seconds < 10) seconds = "0" + seconds;
    if (natural) {
        var tick = "<span style='font-family:monospace;'> </span>";
        if (seconds % 2 == 0) tick = "<span style='font-family:monospace;'>:</span>";
        return hours + tick + minutes;
    }
    else return hours + "<span style='font-family:monospace;'>:</span>" + minutes + "<span style='font-family:monospace;'>:</span>" + seconds;
}

function genCal(dateObj,cal) {
    var monthDays = [31,28,31,30,31,30,31,31,30,31,30,31];
    if (isLeapYear(dateObj.getFullYear())) monthDays[1] = 29;
    var calStr = "";
    $(cal).innerHTML = calStr;
    for (var i = 0; i < 5; i++) {
        calStr += "<tr>";
        for (var j = 1; j <= 7; j++) {
            var tempDay = j + (7 * i);
            if (tempDay <= monthDays[dateObj.getMonth()]) {
                calStr += "<td";
                if (dateObj.getDate() == tempDay) calStr += " style='border-width:1px; border-style:solid;' ";
                calStr += ">" + (j + (7 * i)) + "</td>";
            }
            else calStr += "&#160;";
        }
        calStr += "</tr>";
    }
    $(cal).innerHTML = calStr;
}

function isLeapYear(year) {
    if (year % 4 == 0) {
        if (year % 100 == 0 && year % 400 == 0) return true;
        else return false;
    }
    return false;
}


//Input Functions

function onStrikeEnter(func,ev,prvnt) {
    if ((ev.which === 13) || (ev.keyCode === 13)) {
        if (prvnt) ev.preventDefault();
        return (eval(func));
    }
}

function knobLogic(knob,ev,func) {
    var startPos    = ev.clientY;
    var newPos      = startPos;
    var startingRot = knob.style.MozTransform;
    var startRot    = "0";
    for (var i = 0; i < startingRot.length; i++) if (!isNaN(startingRot[i])) startRot = startRot+startingRot[i];
    document.onmousemove = function(ev) {
        var rotation = parseInt(startRot);
        newPos       = rotation + parseInt(startPos - ev.clientY);
        if (newPos > 120)  newPos = 120;
        if (newPos < -120) newPos = -120;
        knob.style.MozTransform   = "rotate(" + newPos + "deg)";
        rotation = 0;
        if ((func != null) && (func != undefined)) eval(func);
        document.onmouseup = function(ev) {
            newPos   = null;
            startPos = null;
            document.onmousemove = null;
            document.onmouseup   = null;
            return newPos;
        }
    }
}

function switchTabs(id1,id2) {
    $(id1).style.display = "none";
    $(id2).style.display = "block";
}

function showHideIDs(idArray,x) {
    for (var i = 0; i < idArray.length; i++) {
        if (x == "show") $(idArray[i]).style.display = "block";
        else             $(idArray[i]).style.display = "none";
    }
}


//Mathematics Functions

function randNum(x,y) {
    return (Math.random() * x).toFixed(y);
}
