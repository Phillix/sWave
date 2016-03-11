/*
    Copyright 2015, 2016 Brian Millar
    This file is part of Macgril.
    Macgril is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.
    Macgril is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.
    You should have received a copy of the GNU General Public License
    along with eXastum.  If not, see <http://www.gnu.org/licenses/>
*/


var mgDate = new Date();

function fDate(natural) {
    mgDate    = new Date();
    var day   = mgDate.getDay();
    var date  = mgDate.getDate();
    var month = mgDate.getMonth();
    var year  = mgDate.getFullYear().toString();
    if (natural) {
        var days   = ["Sun", "Mon", "Tues", "Wed", "Thur", "Fri", "Sat"];
        var months = ["Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sept", "Oct", "Nov", "Dec"];
        var extra  = "th";
        if      (date === 1 || date === 21 || date === 31) extra = "st";
        else if (date === 2 || date === 22)                extra = "nd";
        else if (date === 3 || date === 23)                extra = "rd";
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
        var tick = " ";
        if (seconds % 2 === 0) tick = ":";
        return hours + tick + minutes;
    }
    else return hours + ":" + minutes + ":" + seconds;
}

function genCal(dateObj,cal) {
    var monthDays = [31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31];
    if (isLeapYear(dateObj.getFullYear()))
        monthDays[1] = 29;
    var calStr = "";
    $(cal).innerHTML = calStr;
    for (var i = 0; i < 5; i++) {
        calStr += "<tr>";
        for (var j = 1; j <= 7; j++) {
            var tempDay = j + (7 * i);
            if (tempDay <= monthDays[dateObj.getMonth()]) {
                calStr += "<td";
                if (dateObj.getDate() === tempDay)
                    calStr += " style='border-width:1px; border-style:solid;' ";
                calStr += ">" + (j + (7 * i)) + "</td>";
            }
            else calStr += "&#160;";
        }
        calStr += "</tr>";
    }
    $(cal).innerHTML = calStr;
}

//May be incorrect, please revisit
function isLeapYear(year) {
    if (year % 4 === 0) {
        if (year % 100 === 0 && year % 400 === 0)
            return true;
        else
            return false;
    }
    return false;
}

function formatTime(s) {
    var timeModFloor = Math.floor(s % 60);
    var timeDiv      = s / 60;

    var mins  = Math.floor(timeDiv);
    var secs  = timeModFloor;
    var hours = null;

    if (mins > 60) {
        hours = Math.floor(timeDiv / 60);
        mins  = Math.floor(timeDiv % 60);
    }

    if (secs < 10) secs = "0" + secs;
    if (mins < 10) mins = "0" + mins;

    if (isNaN(mins) || isNaN(secs))
        return "00<span style='font-family:monospace'>:</span>00";
    else {
        if (hours !== null)
            return hours + "<span style='font-family:monospace'>:</span>" + mins + "<span style='font-family:monospace'>:</span>" + secs;
        else
            return mins + "<span style='font-family:monospace'>:</span>" + secs;
    }
}

//eXastum Specific Code

function clock() {
    $("sysClock").innerHTML = "|&#160;&#160;" + fDate(true) + "&#160;&#160;|&#160;&#160;" + fTime(true) + "&#160;";
    if ($("timePanel").style.display == "block")
        updateAnalogClock();
    setTimeout("clock()", 500);
}

function updateAnalogClock() {
    var today = new Date();
    genCal(today, "miniCal");
    var hrs = today.getHours();
    var min = today.getMinutes();
    if (hrs > 12) hrs -= 12;
    if (hrs == 0) hrs = 12;
    min *= 6;
    hrs *= 30;
    if (min > 180) hrs += 15;
    $("minsHand").style.Transform       = "rotate(" + min + "deg)";
    $("minsHand").style.WebkitTransform = "rotate(" + min + "deg)";
    $("minsHand").style.MozTransform    = "rotate(" + min + "deg)";
    $("minsHand").style.MSTransform     = "rotate(" + min + "deg)";
	
    $("hourHand").style.Transform       = "rotate(" + hrs + "deg)";
    $("hourHand").style.WebkitTransform = "rotate(" + hrs + "deg)";
    $("hourHand").style.MozTransform    = "rotate(" + hrs + "deg)";
    $("hourHand").style.MSTransform     = "rotate(" + hrs + "deg)";
	
    $("secsHand").style.Transform       = "rotate(" + (today.getSeconds() * 6) + "deg)";
    $("secsHand").style.WebkitTransform = "rotate(" + (today.getSeconds() * 6) + "deg)";
    $("secsHand").style.MozTransform    = "rotate(" + (today.getSeconds() * 6) + "deg)";
    $("secsHand").style.MSTransform     = "rotate(" + (today.getSeconds() * 6) + "deg)";
}