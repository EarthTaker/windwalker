/**
 * Method Description: On DOM Load, add an event listener awaiting form submission. After submit, fetch results from API.
 */
document.addEventListener("DOMContentLoaded", () => {

    //Grab the passenger information form
    const form = document.querySelector("#passengerForm");

    //Catch form submit event, use asynchronous call to keep browser responsive and await results from API. 
    form.addEventListener("submit", async (event) => {

        //Prevent page reloading after event triggers. 
        event.preventDefault();

        //Creates a snapshot of the form's input names rather than grabbing each input by ID.
        const formData = new FormData(form);

        try {
            //Convert FormData to JSON payload
            const payload = Object.fromEntries(formData.entries());

            //Call to API Controller with passenger data in body
            const response = await fetch('/api/booking/confirm', {
                method: "POST",
                headers: {
                    "Content-Type": "application/json",
                },
                body: JSON.stringify(payload)
            });

            //If the API fails to respond, throw error
            if (!response.ok) {
                throw new Error(`HTTP ${response.status} - Failed to confirm booking.`);
            }

            //Success - redirect to confirmation or home page
            window.location.href = '/dashboard';

        } catch (err) {
            console.error("Booking confirmation failed:", err);
            alert("Failed to confirm booking. Please try again.");
        }
    });
});