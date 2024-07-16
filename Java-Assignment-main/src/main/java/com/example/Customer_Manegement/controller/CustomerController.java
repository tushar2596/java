package com.example.Customer_Manegement.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.Customer_Manegement.model.Customer;
import com.example.Customer_Manegement.service.CustomerService;
import java.util.List;
import java.util.Optional;
@RestController
@CrossOrigin("*")
@RequestMapping("/api/customers")
public class CustomerController {
 @Autowired
 private CustomerService customerService;
 /**
 * Create a new customer.
 *
 * @param customer The customer data to be created.
 * @return ResponseEntity containing the created customer and HTTP status.
 */
 @PostMapping("/add")
 public ResponseEntity<Customer> createCustomer(@RequestBody Customer customer) {
 Customer createdCustomer = customerService.createCustomer(customer);
 return new ResponseEntity<>(createdCustomer, HttpStatus.CREATED);
 }
 /**
 * Update an existing customer.
 *
 * @param customerId The ID of the customer to be updated.
 * @param customer The updated customer data.
 * @return ResponseEntity containing the updated customer and HTTP status.
 */
 @PutMapping("update/{customerId}")
 public ResponseEntity<Customer> updateCustomer(@PathVariable Long customerId,
@RequestBody Customer customer) {
 Customer updatedCustomer = customerService.updateCustomer(customerId, customer);
 if (updatedCustomer != null) {
 return new ResponseEntity<>(updatedCustomer, HttpStatus.OK);
 } else {
 return new ResponseEntity<>(HttpStatus.NOT_FOUND);
 }
 }
 /**
 * Get a list of all customers.
 *
 * @return ResponseEntity containing the list of customers and HTTP status.
 */
 @GetMapping("/AllCustomers")
 public ResponseEntity<Page<Customer>> getAllCustomers(
 @RequestParam(defaultValue = "1") int page,
 @RequestParam(defaultValue = "5" )int pageSize,
 @RequestParam(defaultValue = "id" )String sortBy,
 @RequestParam(defaultValue = "asc" )String sortOrder,
 @RequestParam(defaultValue = "" )String search) {
 Page<Customer> customers =
customerService.getAllCustomers(page,pageSize,sortBy,sortOrder,search);
 return new ResponseEntity<>(customers, HttpStatus.OK);
 }
 /**
 * Get a single customer by ID.
 *
 * @param customerId The ID of the customer to be retrieved.
 * @return ResponseEntity containing the customer and HTTP status.
 */
 @GetMapping("find/{customerId}")
 public ResponseEntity<Customer> getCustomerById(@PathVariable Long customerId) {
 Optional<Customer> customer = customerService.getCustomerById(customerId);
 return customer.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
 .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
 }
 /**
 * Delete a customer by ID.
 *
 * @param customerId The ID of the customer to be deleted.
 * @return ResponseEntity with HTTP status indicating success or failure.
 */
 @DeleteMapping("/delete/{customerId}")
 public ResponseEntity<Void> deleteCustomer(@PathVariable Long customerId) {
 customerService.deleteCustomer(customerId);
 return new ResponseEntity<>(HttpStatus.NO_CONTENT);
 }
}
