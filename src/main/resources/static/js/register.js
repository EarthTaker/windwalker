/**
 * Function Description: When the DOM has loaded, retrieve the form, add event listener to form on submit.
 */
document.addEventListener("DOMContentLoaded", () => {
    //Grab register form from DOM.
    const registerForm = document.querySelector("#registerForm");

    //Catch form submit event, use asynchronous call to keep browser responsive and await results from API.
    registerForm.addEventListener("submit", handleRegisterSubmit);
});

/**
 * Function to handle registering a new user and storing it within the XML file. 
 * 
 * @param {*} event - User registers for account.
 */
async function handleRegisterSubmit(event) {

    //Prevent page from loading after event triggers.
    event.preventDefault();

    //Grab username for use in onFailure message and payload.
    const username = document.getElementById("username").value;

    // Query the DOM to find the meta tag with name="_csrf"
    const token = document.querySelector('meta[name="_csrf"]').content;

    // Query the DOM to find the meta tag with name="_csrf_header"
    const header = document.querySelector('meta[name="_csrf_header"]').content;

    //Grab values from form.
    const payload = {
        username,
        firstName: document.getElementById("firstName").value,
        lastName: document.getElementById("lastName").value,
        email: document.getElementById("email").value,
        phone: document.getElementById("phone").value,
        password: document.getElementById("password").value        
    };

    //Assign endpoint address.
    const endpoint = "/api/auth/register";

    //Exploration: Object Literal syntax - key:value. Ex: onSuccess: () => {}
    // () => {} defines an anonymous (arrow) function
    // onSuccess and onFailure are keys whose values are anonymous functions.
    submitAuthForm({
        endpoint,
        payload,

        //CSRF Token Header - Syntax Explanation: [] - Resolves the value of the variable 'header' as the property name.
        headers: { [header]: token },
        //Callbacks
        //Syntax Explanation: Object literal who's value is a function.
        onSuccess: () => {
            window.location.href = "/login";
        },

        //User failed to be authorized.
        onFailure: (response) => {
            const registerMessageContainer = document.querySelector("#messageContainer");

            const registerMessage = document.querySelector("#message");

            //Use Register Message Container to create a new Toast Message.
            var toast = new bootstrap.Toast(registerMessageContainer);

            //Define message to user.
            if (response.status == 409) {
                registerMessage.innerHTML = "An account with this username: " + username + ", already exists. Please log in instead.";
            } else {
                registerMessage.innerHTML = "HTTP " + response.status + " - Failed to call to API.";
            }

            //Show Toast Message.
            toast.show();
        }
    });
}
