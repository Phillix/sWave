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


//TODO
function validateEmail(fieldId, validCol, invalidCol) {
    var patt = new RegExp("^\\d{4}[-]\\d{4}[-]\\d{4}[-]\\d{4}$");

    var p1 = new RegExp("^\\d{4}$");
    var p2 = new RegExp("^\\d{4}[-]\\d{4}$");
    var p3 = new RegExp("^\\d{4}[-]\\d{4}[-]\\d{4}$");

    if (p1.test($(fieldId).value) || p2.test($(fieldId).value) || p3.test($(fieldId).value))
        $(fieldId).value = $(fieldId).value + "-";

    if (patt.test($(fieldId).value)) {
        $(fieldId).style.color = validCol;
        return true;
    } else {
        $(fieldId).style.color = invalidCol;
        return false;
    }
}

function validateCreditCard(fieldId, validCol, invalidCol) {
    var patt = new RegExp("^\\d{4}[-]\\d{4}[-]\\d{4}[-]\\d{4}$");

    var p1 = new RegExp("^\\d{4}$");
    var p2 = new RegExp("^\\d{4}[-]\\d{4}$");
    var p3 = new RegExp("^\\d{4}[-]\\d{4}[-]\\d{4}$");

    if (p1.test($(fieldId).value) || p2.test($(fieldId).value) || p3.test($(fieldId).value))
        $(fieldId).value = $(fieldId).value + "-";

    if (patt.test($(fieldId).value)) {
        $(fieldId).style.color = validCol;
        return true;
    } else {
        $(fieldId).style.color = invalidCol;
        return false;
    }
}
