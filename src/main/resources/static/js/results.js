/**
 * Method Description: On DOM Load, add an event listener awaiting form submission. After submit, fetch results from API.
 */
document.addEventListener("DOMContentLoaded", () => {
    const form = document.querySelector("#flightSearchForm");

    //Grab the flight results
    const flightResults = document.querySelector("#flight-results");

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

            displayFlightResults(flights, flightResults);

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
function displayFlightResults(flights, container) {

    const resultsWrapper = document.querySelector("#flightResultsContainer");

    container.innerHTML = "";

    resultsWrapper.style.display = "block";

    if (flights.length === 0) {
        container.innerHTML = "<p>No flights found.</p>";
        return;
    }

    let cardHTML = `<div class="d-flex overflow-auto flex-row gap-3">`;

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
                        <strong>Price:</strong> $${f.price}
                    </p>
                    <button id="bookFlight" class="btn btn-primary w-100">Book Now</button>
                </div>
            </div>
        `;
    });

    cardHTML += "</div>";
    container.innerHTML = cardHTML;
}
