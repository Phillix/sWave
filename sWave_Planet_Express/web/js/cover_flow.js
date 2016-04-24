function flow() {
    var count  = 0;
    var zindex = 0;
    var scale  = 0.5;
    var mtop  = 40;
    var currentCover;
    var rotation = -80;
    for (var i = 1; i < 10; i++) {
        currentCover = $("cover" + i);
        if (currentCover !== null && currentCover !== undefined) {
            if (i < 6) {
                currentCover.style.zIndex = ++zindex;
                currentCover.style.MozTransform = "translateX(" + count + "px) scale(" + (scale += 0.1) + ") rotateY(" + (rotation += 20) + "deg) translateY(" + ((mtop -= 10) + 20) + "px)";
            }
            else {
                currentCover.style.zIndex = --zindex;
                currentCover.style.MozTransform = "translateX(" + count + "px) scale(" + (scale -= 0.1) + ") rotateY(" + (rotation -= 20) + "deg) translateY(" + ((mtop += 10) + 20) + "px)";
            }
            count += 60;
        }
    }
}

function checkKey(ev) {
    if(ev.keyCode === 37)
        cycleRight();
    else if (ev.keyCode === 39)
        cycleLeft();
}

function cycleRight() {
    var currentCover;
    var num;
    for (var i = 9; i > 0; i--) {
        currentCover = $("coverFlow").getElementsByTagName('img')[i - 1];
        num = currentCover.id;
        num = parseInt(num.substring(num.length - 1, num.length));
        if (num != 1)
            currentCover.id = "cover" + (num - 1);
        else
            currentCover.id = "cover9";
    }
    flow();
}

function cycleLeft() {
    var currentCover;
    var num;
    for (var i = 1; i < 10; i++) {
        currentCover = $("coverFlow").getElementsByTagName('img')[i - 1];
        num = currentCover.id;
        num = parseInt(num.substring(num.length - 1, num.length));
        if (num != 9)
            currentCover.id = "cover" + (num + 1);
        else
            currentCover.id = "cover1";
    }
    flow();
}


