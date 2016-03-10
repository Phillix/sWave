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

function validateCardNum() {
    var patt = new RegExp("^\\d{4}[-]\\d{4}[-]\\d{4}[-]\\d{4}$");

    var p1 = new RegExp("^\\d{4}$");
    var p2 = new RegExp("^\\d{4}[-]\\d{4}$");
    var p3 = new RegExp("^\\d{4}[-]\\d{4}[-]\\d{4}$");

    if (p1.test($("cardNumField").value) || p2.test($("cardNumField").value) || p3.test($("cardNumField").value))
        $("cardNumField").value = $("cardNumField").value + "-";

    if (patt.test($("cardNumField").value)) {
        $("cardNumField").style.color = "green";
        return true;
    } else {
        $("cardNumField").style.color = "red";
        return false;
    }
}
