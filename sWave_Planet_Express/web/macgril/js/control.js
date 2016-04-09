/*
    Copyright 2015. 2016 Brian Millar
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
    along with Macgril.  If not, see <http://www.gnu.org/licenses/>
*/

function onStrikeEnter(func, ev, prvnt) {
    if ((ev.which === 13) || (ev.keyCode === 13)) {
        if (prvnt)
            ev.preventDefault();
        return (eval(func));
    }
}


/*
 * The knobLogic function handles setting values based on the rotation of a knob
 * The knob variable is an object reference to the turning knob element in the page
 * The ev variable is the mousedown event when the knob is clicked
 * The func variable is the function that will receive the knob value as a parameter
 * while the knob is turned.
 */

function knobLogic(knob, ev, func) {
    var startPos    = ev.clientY;
    var newPos      = startPos;
    var startingRot = knob.style.MozTransform;
    var startRot    = "";

    for (var i = 0; i < startingRot.length; i++)
        if (!isNaN(startingRot[i]))
            startRot = startRot + startingRot[i];

    document.onmousemove = function(ev) {
        var rotation = parseInt(startRot);
        newPos       = rotation + parseInt(startPos - ev.clientY);
        if (newPos > 120)  newPos = 120;
        if (newPos < -120) newPos = -120;
        //Add cross-browser support here
        knob.style.MozTransform   = "rotate(" + newPos + "deg)";
        rotation = 0;
        if ((func !== null) && (func !== undefined))
            func(newPos);
        document.onmouseup = function(ev) {
            newPos   = null;
            startPos = null;
            document.onmousemove = null;
            document.onmouseup   = null;
            return newPos;
        };
    };
}
