function renderProduct(productId) {
    var request  = new XMLHttpRequest();
    var formdata = new FormData();
    request.responseType = 'text';
    request.open("POST", "UserActionServlet", true);
    formdata.append("action", "getModelURL");
    formdata.append("productId", productId);
    request.onload = function() {
        text = this.response;
        if (text !== null)
            displayProductModel(text);
    };
    request.send(formdata);
}

function displayProductModel(src) {
    
}
