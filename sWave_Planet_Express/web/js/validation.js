function validateRegistration(ev) {
    ev.preventDefault();
    
    var fnameRegEx = new RegExp("^[A-Z]{1}[a-z]{1,19}$");
    var lnameRegEx = new RegExp("^[A-Z]{1}[A-Z a-z]{1,19}$");

    if ($("fnameRegField").value !== "" && !fnameRegEx.test($("fnameRegField").value))
        notify($("notifier"), "First Name should be an uppercase letter followed by one or more lowercase letters", 5000);
    else if ($("lnameRegField").value !== "" && !lnameRegEx.test($("lnameRegField").value))
        notify($("notifier"), "Last Name should be an uppercase letter followed by one or more upper or lowercase letters or spaces", 5000);
    else
        $("registerForm").submit();
}
