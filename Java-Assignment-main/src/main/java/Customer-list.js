// customer-list.js
let currentPage = 1;
let totalPageCount = 1;
const token=localStorage.getItem("key")||"";
document.addEventListener('DOMContentLoaded', function () {
    const pageSize = 5; // Set the number of items per page
    // let currentPage = 1;
    // let totalPageCount = 1;

    // Initial load
    loadCustomers();

   // Event listener for pagination controls
    document.getElementById('prevPageBtn').addEventListener('click', function () {
        if (currentPage > 1) {
            currentPage--;
            loadCustomers();
        }
    });

    document.getElementById('nextPageBtn').addEventListener('click', function () {
       console.log(currentPage+"ii"+totalPageCount)
        if (currentPage < totalPageCount) {
            currentPage++;
            loadCustomers();
        }
    });
    document.getElementById('searchInput').addEventListener('input', function () {
        const searchInput = document.getElementById('searchInput').value;
        loadCustomers(1, pageSize, 'id', 'asc', searchInput);
    });
 
});

function loadCustomers(page = 1, pageSize = 5, sortBy = 'id', sortOrder = 'asc', search = '') {
    const url = `http://localhost:8080/api/customers/AllCustomers?page=${page}&pageSize=${pageSize}&sortBy=${sortBy}&sortOrder=${sortOrder}&search=${search}`;
  
    fetch(url, {
        method: 'GET',
        headers: {
            'Authorization': `Bearer ${token}`,
            'Origin':'*',
            'Content-Type': 'application/json',
           
        }
    })
    .then(response => response.json())
    .then(data => {
      console.log(data);
         displayCustomers(data.content);
         displayPagination(data.totalPages, data.number + 1);
    })
    .catch(error => {
        console.log(token)
        console.error('Error fetching customers:', error);
    });
}

function displayCustomers(customers) {
    const table = document.getElementById('customerTable');
    table.innerHTML = '<tr><th>ID</th><th>First Name</th><th>Last Name</th><th>Street</th><th>Address</th><th>City</th><th>State</th><th>Email</th><th>Phone</th><th>Action</th></tr>';

    customers.forEach(customer => {
        const row = table.insertRow();
        row.insertCell(0).textContent = customer.id;
        row.insertCell(1).textContent = customer.firstName;
        row.insertCell(2).textContent = customer.lastName;
        row.insertCell(3).textContent = customer.street;
        row.insertCell(4).textContent = customer.address;
        row.insertCell(5).textContent = customer.city;
        row.insertCell(6).textContent = customer.state;
        row.insertCell(7).textContent = customer.email;
        row.insertCell(8).textContent = customer.phone;

        // Edit and Delete icons with event listeners
        const actionsCell = row.insertCell(9);
        const editIcon = document.createElement('span');
        editIcon.className = 'edit-icon';
        editIcon.textContent = 'Edit';
        editIcon.addEventListener('click', () => editCustomer(customer.id));
        actionsCell.appendChild(editIcon);

        const deleteIcon = document.createElement('span');
        deleteIcon.className = 'delete-icon';
        deleteIcon.textContent = 'Delete';
        deleteIcon.addEventListener('click', () => deleteCustomer(customer.id));
        actionsCell.appendChild(deleteIcon);
    });
}

function displayPagination(totalPages, currentPage) {
    const paginationInfo = document.getElementById('paginationInfo');
    paginationInfo.textContent = `Page ${currentPage} of ${totalPages}`;

    const prevPageBtn = document.getElementById('prevPageBtn');
    const nextPageBtn = document.getElementById('nextPageBtn');

    totalPageCount = totalPages;
    console.log(totalPageCount)
    if (currentPage === 1) {
        prevPageBtn.disabled = true;
    } else {
        prevPageBtn.disabled = false;
    }

    if (currentPage === totalPageCount) {
        nextPageBtn.disabled = true;
    } else {
        nextPageBtn.disabled = false;
    }
}

function editCustomer(customerId) {
    // Placeholder for actual edit logic (e.g., redirect to edit page)
    console.log(`Edit customer with ID: ${customerId}`);
}

function deleteCustomer(customerId) {
    const isConfirmed = confirm(`Are you sure you want to delete customer with ID ${customerId}?`);
    
    if (isConfirmed) {
        // If the user confirms, make the delete request
        fetch(`http://localhost:8080/api/customers/delete/${customerId}`, {
            method: 'DELETE',
            headers: {
                'Authorization': 'Bearer your_jwt_token_here',
            },
        })
        .then(response => {
            if (response.ok) {
                console.log(`Customer with ID ${customerId} deleted successfully`);
                // Refresh the customer list after deletion
                loadCustomers(currentPage);
            } else {
                console.error(`Failed to delete customer with ID ${customerId}`);
                // Handle the error (e.g., show an error message)
            }
        })
        .catch(error => {
            console.error('Error deleting customer:', error);
        });
    }

}
