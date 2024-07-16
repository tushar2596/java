# Java-Assignment
This is a simple CRUD (Create, Read, Update, Delete) application for managing customer information. The project uses Spring Boot for the backend, JSP Servlet for server-side processing, and HTML/CSS/JS for the frontend. It includes JWT authentication for securing API endpoints.

## 📋 Table of Contents

- [Getting Started](#getting-started)
  - [Prerequisites](#prerequisites)
  - [Installation](#installation)
- [Backend](#backend)
  - [API Endpoints](#api-endpoints)
  - [Authentication](#authentication)
- [Frontend](#frontend)
  - [Screens](#screens)
  - [Functionality](#functionality)
- [Syncing Data](#syncing-data)
- [Usage](#usage)
- [Contributing](#contributing)
- [License](#license)

## 🚀 Getting Started

### Prerequisites

- Java Development Kit (JDK)
- Maven
- MySQL Database
- html,css,javascript (for frontend development)
- 
 ## ⚙️ Backend

The backend is built using Spring Boot and provides API endpoints for managing customer data.

### 🛠️ API Endpoints

- **Create a customer:**
  - `POST /api/customers/add`

- **Update a customer:**
  - `PUT /api/customers/update/{customerId}`

- **Get a list of customers (with pagination, sorting, and searching):**
  - `GET /api/customers/AllCustomers?page={pageNumber}&pageSize={pageSize}&sortBy={sortField}&sortOrder={sortOrder}&search={searchTerm}`

- **Get a single customer based on ID:**
  - `GET /api/customers/{customerId}`

- **Delete a customer:**
  - `DELETE /api/customers/delete/{customerId}`

### 🔐 Authentication

For authentication, the application uses JWT (JSON Web Token). The authentication API endpoint is:
  - `POST https://qa.sunbasedata.com/sunbase/portal/api/assignment_auth.jsp`

Request body:
  ```json
  {
    "login_id": "test@sunbasedata.com",
    "password": "Test@123"
  }


🎨 Frontend
The frontend is a basic HTML/CSS/JS application with three screens:

🖥️ Screens
Login Screen:

Allows users to authenticate and obtain a JWT token.
Customer List Screen:

Displays a list of customers with pagination, sorting, and searching functionality.
Allows syncing data from a remote API.
Add a New Customer:

Provides a form to add a new customer to the database.
🚀 Functionality
Search:

Users can search for customers by name or any other criteria.
Pagination:

Display a limited number of items per page.
Sorting:

Sort the customer list based on different fields.
