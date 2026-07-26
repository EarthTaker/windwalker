/**
 * Function Description: When the DOM has loaded, retrieve the form, add event listener to form on submit.
 */
document.addEventListener("DOMContentLoaded", () => {
    // Check if URL has ?error parameter
    const urlParams = new URLSearchParams(window.location.search);

    if (urlParams.has('error')) {
        const loginMessageContainer = document.querySelector("#messageContainer");

        const loginMessage = document.querySelector("#message");

        loginMessage.innerHTML = "Cannot find user.";

        //Use Login Message Container to create a new Toast Message.
        var toast = new bootstrap.Toast(loginMessageContainer);

        //Show the toast message to the user.
        toast.show();
    }
});
