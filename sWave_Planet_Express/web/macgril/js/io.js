/*
    Copyright 2015 Brian Millar
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
