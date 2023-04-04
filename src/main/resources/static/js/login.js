// Get the form and error elements
const form = document.querySelector('form');
const errorDiv = document.querySelector('#error');

// Add an event listener to the form submit event
form.addEventListener('submit', async (event) => {

  // Prevent the default form submission behavior
  event.preventDefault();

  // Get the username and password values from the form fields and build user.
  const username = form.username.value;
  const password = form.password.value;
  const user = { username, password }

  try {
    // Send a POST request to the login endpoint on the Java backend
    const response = await fetch('/login', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json'
      },
      body: JSON.stringify(user)
    });

    // Check if the response was successful
    if (response.ok) {
      // Await and create a new response object with the collected responseEntity from userController
      const responseOk = await response.json();

      if (responseOk.role === 'USER') {
        alert('Login is successful for user access.')
        window.location.href = '/user-access';
      } 
      else if (responseOk.role === 'MANAGER') {
        alert('Login is successful for manager access.')
        window.location.href = '/manager-access';
      }
      else {
        alert('Invalid role for user - role has been assigned incorrectly. Please approach helpdesk for assistance.')
      }

    } else {
      // Display an error message if credentials are wrong.
      alert('Login failed. Invalid userid or password');
    }

  } catch (error) {
    errorDiv.innerHTML = error.message;
    // Display an error message
    alert('An error occurred. Please try again later.');
  }

});