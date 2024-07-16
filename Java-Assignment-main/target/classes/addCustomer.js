const token = localStorage.getItem("key");
console.log(token);
if (!token) {
 console.error('Token not available. Redirect to login or handleaccordingly.');
} else {
 document.getElementById('customerForm').addEventListener('submit',
function (event) {
 event.preventDefault();
 const formData = new FormData(event.target);
 const customerData = {};
 formData.forEach((value, key) => {
 customerData[key] = value;
 });
 console.log(customerData);
 fetch('http://localhost:8080/api/customers/add', {
 method: 'POST',
 headers: {
 'Authorization': `Bearer ${token}`,

 'Content-Type': 'application/json',

 },
 body: JSON.stringify(customerData),
 })
 .then(response => response.json())
 .then(data => {
 console.log('Customer created:', data);
 // Optionally, reset the form or provide user feedback
 })
 .catch(error => {
 console.error('Error creating customer:', error);
 // Optionally, display an error message to the user
 });
 });
}