function uploadSongs() {
    request  = new XMLHttpRequest();
    formdata = new FormData();
    request.open("POST", "UserActionServlet", true);
    formdata.append("action", "upload");
    var files = $("fileSelector").files;
    for (var i = 0; i < files.length; i++)
        formdata.append(files[i].name, files[i]);
    request.send(formdata);
    request.upload.onprogress = function(event) {
        $("uploadProgress").value = (100 / event.total) * event.loaded;
        $("progressInfo").innerHTML = "Uploaded " + event.loaded + " of " + event.total + " bytes.";
        if (event.loaded == event.total)
            $("progressInfo").innerHTML = "Done";
    }
}
