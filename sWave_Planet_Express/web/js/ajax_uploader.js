function uploadSongs() {
    var request  = new XMLHttpRequest();
    var formdata = new FormData();
    var files    = $("fileSelector").files;

    request.open("POST", "UserActionServlet", true);

    formdata.append("action", "upload");

    for (var i = 0; i < files.length; i++)
        formdata.append(files[i].name, files[i]);

    request.send(formdata);

    request.upload.onprogress = function(event) {
        $("uploadProgress").value = (100 / event.total) * event.loaded;
        $("progressInfo").innerHTML = "Uploaded " + (event.loaded / (1024 * 1024)) + " of " + (event.total / (1024 * 1024)) + " MB.";
    }

    request.upload.onload = function(event) {
        $("progressInfo").innerHTML = "Done";
    }
}

function beginStream(songid) {
    var request  = new XMLHttpRequest();
    var formdata = new FormData();
    request.open("GET", "UserActionServlet", true);
    formdata.append("action", "streamNG");
    formdata.append("songid", songid);
    request.send(formdata);
}
