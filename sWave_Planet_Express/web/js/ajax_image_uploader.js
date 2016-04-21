function uploadUserPicture() {
    var request  = new XMLHttpRequest();
    var formdata = new FormData();
    var picture  = $("userPicField").files[0];

    request.open("POST", "UserActionServlet", true);
    formdata.append("action", "uploadUserPicture");
    formdata.append("userPicture", picture)
    request.send(formdata);

/*
    request.upload.onprogress = function(event) {
        $("uploadProgress").value = (100 / event.total) * event.loaded;
        $("progressInfo").innerHTML = "Uploaded " + (event.loaded / (1024 * 1024)) + " of " + (event.total / (1024 * 1024)) + " MB.";
    };


    request.upload.onload = function(event) {
        $("progressInfo").innerHTML = "Done";
    };
    */
}
