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
    along with Macgril.  If not, see <http://www.gnu.org/licenses/>
*/

function notify(notifier, msg, timeout, desktop) {
    $(notifier).innerHTML = msg;
    $(notifier).style.display = block;
    if (desktop)
        alert("will show desktop notifications");
    setTimeout(function () {$(notifier).style.display = "none";}, timeout);
}
