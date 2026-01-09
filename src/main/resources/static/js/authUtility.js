/**
 * Function establishes the contract between the Submitting form and the associated functionality, e.g., Login vs. Register User.
 * 
 * This function expects one object argument that has properties named endpoint, payload, onSuccess, and onFailure, 
 * and it immediately binds those property values to local variables.
 * 
 * Note: Relies on JavaScript's Duck Typing (Implicit type, i.e., endpoint, etc.) 
 * & Object destructuring (extracting specific values from an incoming object literal based on property names.)
 * 
 * @param {String} endpoint - Determines the API endpoint to target.
 * @param {*} payload - Contains the values pulled from the form.
 * @param {*} onSuccess - Determines the outcome if the form submission succeeds.
 * @param {*} onFailure - Determines the outcome if the form submission fails.
 */
async function submitAuthForm({ endpoint, payload, headers = {}, onSuccess, onFailure }) {

    //Send HTTP POST (request) to API URL
    //Client defines the outbound HTTP request, Server defines what its looking for.
    const response = await fetch(endpoint, {

        //Use POST to match recipient method's request type.
        method: "POST",

        //Describes the Request, acting as its contextual metadata, i.e., what do I interpret this inbound request as? 
        //Tells Spring to use a specific deserializer, i.e., one that can deserialize JSON to the POJO.
        //Ensures RequestBody will bind inbound Payload to LoginRequest DTO.
        headers: {
            "Content-Type": "application/json",

            //CSRF Header + Token
            //Syntax Explanation - ... Spread Operator - Expands the headers object into individual key:value pairs.
            ...headers
            
        },

        //Store the Payload in the body, transform it into JSON.
        body: JSON.stringify(payload)
    });

    //Handle response callback. Response.ok handles HTTP Codes between 200 - 299.
    if (response.ok) {
        
        //Syntax Explanation: ? - Optional Chaining
        onSuccess?.();
    } else {

        //Reads as: If the onSuccess function exists and is callable, pass it the response.
        onFailure?.(response);
    }
};