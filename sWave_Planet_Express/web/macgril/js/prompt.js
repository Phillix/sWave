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

function consoleInit(console) {
	alert(console);
    $(console).setAttribute("onkeypress","onStrikeEnter('runConsoleCommand($(\\'" + console + "\\').value, $(\\'" + console + "\\'))',event,true);");
    $(console).focus();
}

function consoleOut(msg,console) {
    $(console).value = msg + "\n";
}

function runConsoleCommand(cmd,console) {
	eval(cmd);
	console.value = console.value + "\n";
	console.focus();
}
