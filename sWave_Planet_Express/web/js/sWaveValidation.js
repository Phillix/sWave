function validateLogin(ev) {
    ev.preventDefault();
    if ($("emailLoginField").value == "")
        displayCustomAlert("Required Field 'Email' is Empty");
    else if ($("passwordLoginField").value == "")
        displayCustomAlert("Required Field 'Password' is Empty");
    else
        $("loginForm").submit();
}

function validateRegistration(ev) {
    ev.preventDefault();
    
    var fnameRegEx = new RegExp("^[A-Z]{1}[a-z]{1,19}$");
    var lnameRegEx = new RegExp("^[A-Z]{1}[A-Z a-z]{1,19}$");
    
    if ($("usernameRegField").value == "")
        displayCustomAlert("Required Field 'Username' is Empty");
    else if ($("emailRegField").value == "")
        displayCustomAlert("Required Field 'Email' is Empty");
    else if ($("passwordRegField").value == "")
        displayCustomAlert("Required Field 'Password' is Empty");
    else if (!validateEmail("emailRegField", "green", "red"))
        displayCustomAlert("The Email Field does not contain a valid email address");
    else if ($("fnameRegField").value != "" && !fnameRegEx.test($("fnameRegField").value))
        displayCustomAlert("First Name should be an uppercase letter followed by 1 or more lowercase letters");
    else if ($("lnameRegField").value != "" && !lnameRegEx.test($("lnameRegField").value))
        displayCustomAlert("Last Name should be an uppercase letter followed by 1 or more upper or lowercase letters or spaces ");
    else
        $("registerForm").submit();
}
