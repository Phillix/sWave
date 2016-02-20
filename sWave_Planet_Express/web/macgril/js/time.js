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

//This file contains eXastum-specific code that should be generalised ASAP

function clock() {
    $("sysClock").innerHTML = "|&#160;&#160;" + fDate(true) + "&#160;&#160;|&#160;&#160;" + fTime(true) + "&#160;";
     if ($("timePanel").style.display == "block")
        updateAnalogClock();
    setTimeout("clock()",500);
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
