/**
 * Method Description: On DOM Load, add an event listener awaiting form submission. After submit, fetch results from API.
 */
document.addEventListener("DOMContentLoaded", () => {

    //Grab the flight search form
    const form = document.querySelector("#flightSearchForm");

    //Grab the flight results container
    const flightResultsInnerContainer = document.querySelector("#flight-results_InnerContainer");

    //Grab user authentication status from body dataset attribute.
    const isLoggedIn = document.body.dataset.authenticated === 'true';

    //Catch form submit event, use asynchronous call to keep browser responsive and await results from API. 
    form.addEventListener("submit", async (event) => {

        //Prevent page reloading after event triggers. 
        event.preventDefault();

        //Creates a snapshot of the form's input names rather than grabbing each input by ID.
        const formData = new FormData(form);

        //Converts the FormData Object into a URL search parameter format.
        const params = new URLSearchParams(formData);

        try {
            //Set API endpoint
            const url = `/api/flights/search?${params.toString()}`;

            //Call to API Controller, pass URL search parameters.
            const response = await fetch(url);

            //If the API fails to respond, 
            if (!response.ok) {
                throw new Error('HTTP ${response.status} - Failed to call to API.');
            }

            //Convert the JSON response into a JavaScript Array
            const flights = await response.json();

            //Display the flight results in the UI.
            displayFlightResults(flights, flightResultsInnerContainer, isLoggedIn);

        } catch (err) {
            console.error("Flight search failed:", err);
        }
    });
});

/**
 * Dyanmically create the UI for each returning flight within the flight list. 
 * Handles hiding and displaying the flights results container. 
 * 
 * @param {*} flights 
 * @param {*} container 
 * @returns 
 */
function displayFlightResults(flights, flightResultsInnerContainer, isLoggedIn) {

    const resultsWrapper = document.querySelector("#flightResultsContainer");

    flightResultsInnerContainer.innerHTML = "";

    resultsWrapper.style.display = "block";

    if (flights.length === 0) {
        flightResultsInnerContainer.innerHTML = "<p>No flights found.</p>";
        return;
    }

    //Check if user is logged in from body dataset attribute.

    let cardHTML = `<div class="d-flex overflow-auto flex-row gap-3">`;

    //Iterate through each flight and create a card for it.
    //Add attributes pulled from each flight object using template literals.
    //Use the button's internal dataset (data-*) to store the flight object as JSON.
    flights.forEach(f => {
        cardHTML += `
            <div class="card shadow-sm">
                <div class="card-body">
                    <h5 class="card-title">${f.airlineName}</h5>
                    <p class="card-text">
                        <strong>Flight:</strong> ${f.flightNumber} <br>
                        <strong>From:</strong> ${f.departureCity}, ${f.departureCountry} <br>
                        <strong>To:</strong> ${f.arriveCity}, ${f.arriveCountry} <br>
                        <strong>Depart:</strong> ${f.departureTime} <br>
                        <strong>Arrive:</strong> ${f.arriveTime} <br>
                        <strong>Available Seats:</strong> ${f.numPassengers} <br>
                        <strong>Price:</strong> $${f.price}
                    </p>
        `;
        //If the user is authenticated, add the Book Now button to each card.
        if (isLoggedIn) {
            cardHTML += `<button class="btn btn-primary w-100 bookFlightBtn" data-flight='${JSON.stringify(f)}'>Book Now</button>`;
        }

        //Close card divs
        cardHTML += '</div></div>';
    });

    flightResultsInnerContainer.innerHTML = cardHTML;

    //Event delegation: single event listener for all book flight buttons.
    flightResultsInnerContainer.addEventListener('click', (event) => {

        //Search through the event target's class list (the clicked element's classes) to
        // see if it contains the ID.
        if (event.target.classList.contains('bookFlightBtn')) {

            //Unpack the flight data from the button's dataset (data-flight) and parse into JSON.
            const flightData = JSON.parse(event.target.dataset.flight);

            bookSelectedFlight(flightData);

        }
    });
}

/**
 * 
 * @param {Object} flightData - Contains the user's selected flight data (full FlightOption object)
 */
async function bookSelectedFlight(flightData) {
    
    try {
        const response = await fetch('/api/booking/flight', {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(flightData)
        });

        if (response.ok) {

            //Redirect user to booking confirmation page.
            window.location.href = '/bookFlight';

        } else {
            console.error('Booking failed:', response.status);

            alert('Unable to book flight. Please try again.');
        }
    } catch (err) {
        console.error('Error:', err);
    }
};
