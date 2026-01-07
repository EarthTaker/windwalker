/**
 * Function Description: When the DOM has loaded, retrieve the form, add event listener to form on submit.
 */
document.addEventListener("DOMContentLoaded", () => {
    //Grab login form from DOM.
    const loginForm = document.querySelector("#loginForm");

    //Catch form submit event, use asynchronous call to keep browser responsive and await results from API.
    loginForm.addEventListener("submit", handleLoginSubmit);

});

/**
 * Function to handle sending user login credentials to Service for authorization. 
 * 
 * @param {*} event - User submits login credentials.
 */
async function handleLoginSubmit(event) {

    //Prevent page from loading after event triggers.
    event.preventDefault();

    //Grab values from form.
    const payload = {
        username: document.getElementById("username").value,
        password: document.getElementById("password").value
    };

    //Assign endpoint address.
    const endpoint = "/api/auth/login";

    //Exploration: Object Literal syntax - key:value. Ex: onSuccess: () => {}
    // () => {} defines an anonymous (arrow) function
    // onSuccess and onFailure are keys whose values are anonymous functions.
    submitAuthForm({
        endpoint,
        payload,

        //Callbacks
        onSuccess: () => {
            window.location.href = "/dashboard";
        },

        //User failed to be authorized.
        onFailure: (response) => {
            const loginMessageContainer = document.querySelector("#messageContainer");

            const loginMessage = document.querySelector("#message");

            //Use Login Message Container to create a new Toast Message.
            var toast = new bootstrap.Toast(loginMessageContainer);

            //Define message to user.
            if (response.status == 401) {
                loginMessage.innerHTML = "Cannot find user.";
            } else {
                loginMessage.innerHTML = "HTTP " + response.status + " - Failed to call to API.";
            }

            //Show Toast Message.
            toast.show();
        }
    });
}
