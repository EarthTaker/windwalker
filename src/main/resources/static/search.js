//Gather necessary elements
//Trip Type
const tripTypeSelect = document.getElementById('tripType');

//Return Date
const returnDateInput = document.getElementById('returnDate');
const returnDateGroup = document.getElementById('returnDateGroup');

//Departure Date   
const departureDateInput = document.getElementById('departureDate');

//Create a function that toggles the return date input based on the selected trip type. 
function toggleReturnDate() {
    if (tripTypeSelect.value === 'Round-Trip') {
        returnDateGroup.style.display = 'block';
        returnDateInput.required = true;
    } else {
        returnDateGroup.style.display = 'none';
        //Allow the form to be submited without a return date
        returnDateInput.required = false;
        //Reset the return date value when one-way is selected.
        returnDateInput.value = '';
    }
}

//Ensure Return Date is greater than Departure Date
function validateReturnDate() {

    //Translate the date strings into date objects for comparison
    const returnDate = new Date(returnDateInput.value);
    const departureDate = new Date(departureDateInput.value);

    if (returnDate < departureDate) {
        alert('Return date must be after departure date.');
        returnDateInput.value = '';
    }
}


//Add an event listener that targets the return date input to validate the date when changed.
returnDateInput.addEventListener('change', validateReturnDate);

//Add an event listener that targets the trip type selected option. 
tripTypeSelect.addEventListener('change', toggleReturnDate);

//Call the function on page load to set the initial state. 
toggleReturnDate();