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

function newWindow(x, y, title, content, resize, min, max) {
    if((max === null) || (max === undefined)) max = true;
    if((min === null) || (min === undefined)) min = true;
    var newWindow = generate("div",  "windowSystem");
    var titleBar  = generate("span", newWindow);
    var windowTab = newTab(title);
    $(newWindow).setAttribute("class", "window");
    $(newWindow).setAttribute("onmouseover", "updateInfoBar('" + title + "');");
    $(newWindow).setAttribute("onmouseout", "updateInfoBar('eXastum 3.0');");
    $(newWindow).style.width    = parseInt(x) + 2  + "px";
    $(newWindow).style.height   = parseInt(y) + 32 + "px";
    $(newWindow).style.resize   = resize;
    $(newWindow).style.left     = randNum(600, 0) + "px";
    $(newWindow).style.top      = (parseInt(randNum(200, 0)) + 25) + "px";
    $(newWindow).style.display  = "block";
    $(titleBar).style.width     = x;
    if(content !== null) {
        var appContent = generate("iframe", newWindow);
        $(appContent).setAttribute("src", content);
        $(appContent).setAttribute("class", "app");
    }
    var minString = "";
    var maxString = "";
    if(min)
        minString = "<img draggable=\"false\" ondragstart=\"return false;\" src=\"sys/skins/" +
                    lStore("skin") + "/ui/min.png\" onmouseover=\"this.src='sys/skins/" +
                    lStore("skin") + "/ui/minHover.png';\" onmouseout=\"this.src='sys/skins/" +
                    lStore("skin") + "/ui/min.png';\" onmousedown=\"this.src='sys/skins/" +
                    lStore("skin") + "/ui/minDown.png';\" onmouseup=\"this.src='sys/skins/" +
                    lStore("skin") + "/ui/min.png';\" class=\"minButton\" onclick=\"minMaxWindow('" +
                    newWindow + "','" + windowTab + "')\"/>";
    
    if(max)
        maxString = "<img draggable=\"false\" ondragstart=\"return false;\" src=\"sys/skins/" +
            lStore("skin") + "/ui/max.png\" onmouseover=\"this.src='sys/skins/" +
            lStore("skin") + "/ui/maxHover.png';\" onmouseout=\"this.src='sys/skins/" +
            lStore("skin") + "/ui/max.png';\" onmousedown=\"this.src='sys/skins/" +
            lStore("skin") + "/ui/maxDown.png';\" onmouseup=\"this.src='sys/skins/" +
            lStore("skin") + "/ui/max.png';\" class=\"maxButton\" onclick=\"maxRestoreWindow('" +
            newWindow + "','" + windowTab + "')\"/>";
    
    $(titleBar).innerHTML = "<span class='titleBarText'>" + title + "</span>" +
                            "<img draggable=\"false\" ondragstart=\"return false;\" src=\"sys/skins/" +
                            lStore("skin") + "/ui/close.png\" onmouseover=\"this.src='sys/skins/" +
                            lStore("skin") + "/ui/closeHover.png';\" onmouseout=\"this.src='sys/skins/" +
                            lStore("skin") + "/ui/close.png';\" onmousedown=\"this.src='sys/skins/" +
                            lStore("skin") + "/ui/closeDown.png';\" onmouseup=\"this.src='sys/skins/" +
                            lStore("skin") + "/ui/close.png';\" class=\"closeButton\" onclick=\"destroyWindow('" +
                            newWindow + "','" + windowTab + "')\"/>" + maxString + minString;
    
    $(titleBar).setAttribute("class", "titleBar");
    $(titleBar).setAttribute("onmousedown", "dragWindow('" + newWindow + "', event);");
    $(windowTab).setAttribute("onclick", "minMaxWindow('" + newWindow + "','" + windowTab + "')");
    
    return newWindow;
}

function destroyWindow(winID, tabID) {
    $(winID).remove();
    $(tabID).remove();
}

function minMaxWindow(winID, tabID) {
    if ($(winID).style.display === "block") {
        $(winID).style.display =  "none";
        $(tabID).style.backgroundImage = "URL('sys/skins/" + lStore("skin") + "/ui/backing_up.png')";
        document.onmousemove = function() {return false};
    }
    else {
        $(winID).style.display = "block";
        $(tabID).style.backgroundImage = "URL('sys/skins/" + lStore("skin") + "/ui/backing_down.png')";
    }
}

function maxWindow(winID) {
    lStore(winID + "Width",  $(winID).style.width);
    lStore(winID + "Height", $(winID).style.height);
    lStore(winID + "Left",   $(winID).style.left);
    lStore(winID + "Top",    $(winID).style.top);
    document.onmousemove  =  function() {return false};
    $(winID).style.top    =  "22px";
    $(winID).style.left   =  "0px";
    $(winID).style.width  =  "100%";
    $(winID).style.height =  "calc(100% - 44px)";
    $(winID).style.borderLeftStyle         = "none";
    $(winID).style.borderRightStyle        = "none";
    $(winID).style.borderTopLeftRadius     = "0px";
    $(winID).style.borderTopRightRadius    = "0px";
    $(winID).style.borderBottomLeftRadius  = "0px";
    $(winID).style.borderBottomRightRadius = "0px";
}

function restoreWindow(winID) {
    $(winID).style.width  = lStore(winID + "Width");
    $(winID).style.height = lStore(winID + "Height");
    $(winID).style.top    = lStore(winID + "Top");
    $(winID).style.left   = lStore(winID + "Left");
    lStore(winID + "Width",  "del");
    lStore(winID + "Height", "del");
    lStore(winID + "Left",   "del");
    lStore(winID + "Top",    "del");
    $(winID).style.borderLeftStyle         = "solid";
    $(winID).style.borderRightStyle        = "solid";
    $(winID).style.borderTopLeftRadius     = "20px";
    $(winID).style.borderTopRightRadius    = "20px";
    $(winID).style.borderBottomLeftRadius  = "16px";
    $(winID).style.borderBottomRightRadius = "16px";
}

function maxRestoreWindow(winID) {
    document.onmousemove = function() {return false};
    if(lStore(winID + "Width") !== null)
        restoreWindow(winID);
    else maxWindow(winID);
}

function dragWindow(appWindow,ev) {
    positionLeft = parseInt($(appWindow).style.left);
    positionTop  = parseInt($(appWindow).style.top);
    xcoor        = ev.clientX;
    ycoor        = ev.clientY;
    
    document.onmousemove = function(ev) {
        if($(appWindow) === undefined) return false;
        
        var leftdist    =  positionLeft + ev.clientX - xcoor;
        var topdist     =  positionTop  + ev.clientY - ycoor;
        
        if(topdist  < 33) topdist  = "23";
        if(leftdist < 10) leftdist = "0";
        
        if((leftdist === 0) && (topdist == 23))
            $(appWindow).style.borderTopLeftRadius = "0px";
        else
            $(appWindow).style.borderTopLeftRadius = "20px";
        
        $(appWindow).style.opacity = 0.7;
        $(appWindow).style.top     = topdist  + "px";
        $(appWindow).style.left    = leftdist + "px";
        
        document.onmouseup         = function(ev) {
            $(appWindow).style.opacity = 1.0;
            document.onmousemove       = function() {return false};
            document.onmouseup         = function() {return false};
            leftdist                   = null;
            topdist                    = null;
            xcoor                      = null;
            ycoor                      = null;
            return false;
        }
    }
}

function newTab(title) {
    var newTab          = generate("span", "sysTasks");
    $(newTab).innerHTML = title;
    $(newTab).setAttribute("class", "workTab");
    return newTab;
}
