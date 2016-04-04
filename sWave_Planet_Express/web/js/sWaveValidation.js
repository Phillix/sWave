function validateLogin(ev) {
    ev.preventDefault();
    if ($("emailLoginField").value == "")
        displayCustomAlert("Required Field 'Email' is Empty");
    else if ($("passwordLoginField").value == "")
        displayCustomAlert("Required Field 'Password' is Empty");
    else
        $("loginForm").submit();
}

