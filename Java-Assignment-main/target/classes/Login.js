document.getElementById('loginForm').addEventListener('submit', function
(event) {
 event.preventDefault();
 const formData = new FormData(event.target);
 console.log(formData);
 const credentials = {
 username: formData.get('username'),
 password: formData.get('password')
 };
 console.log(credentials);


 fetch('http://localhost:8080/auth/login', {
 method: 'POST',
 headers: {
 'Content-Type': 'application/json'
 },
 body: JSON.stringify(credentials),
 })
 .then(response => {
 if (!response.ok) {
 if (response.status === 401) {
 throw new Error('Invalid username or password.');
 } else {
 throw new Error('Authentication failed. Please try againlater.');
 }
 }
 return response.json();
 })

 .then(data => {
 // Handle the authentication response
 console.log('Authentication successful. Bearer token:',
data.jwtToken);
 // You might want to store the token securely for subsequent API
calls
 // For simplicity, let's assume you store it in a variable
 const bearerToken = data.jwtToken;
 localStorage.setItem("key",bearerToken);
 // Redirect to the Customer List Screen or perform other actions
 window.location.href = 'customerList.html';
 })
 .catch(error => {
 console.error('Authentication failed:', error);
 // Handle authentication failure (e.g., show error message)
 });
})