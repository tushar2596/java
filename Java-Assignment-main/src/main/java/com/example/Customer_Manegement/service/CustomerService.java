package com.example.Customer_Manegement.service;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import com.example.Customer_Manegement.model.Customer;
import com.example.Customer_Manegement.repository.CustomerRepository;
@Service
public class CustomerService {
// This @Autowired annotation injects the CustomerRepository bean into this service.
 //@Autowired
@org.springframework.beans.factory.annotation.Autowired(required=true)
 private CustomerRepository customerRepository;
 /**
 * Create a new customer.
 *
 * @param customer The customer data to be created.
 * @return The created customer.
 */
 public Customer createCustomer(Customer customer) {
 return customerRepository.save(customer);
 }
 /**
 * Update an existing customer.
 *
 * @param customerId The ID of the customer to be updated.
 * @param customer The updated customer data.
 * @return The updated customer if found, otherwise null.
 */
 public Customer updateCustomer(Long customerId, Customer customer) {
 Optional<Customer> existingCustomerOptional =
customerRepository.findById(customerId);
 if (existingCustomerOptional.isPresent()) {
 Customer existingCustomer = existingCustomerOptional.get();
 existingCustomer.setFirstName(customer.getFirstName());
 existingCustomer.setLastName(customer.getLastName());
 existingCustomer.setStreet(customer.getStreet());
 existingCustomer.setAddress(customer.getAddress());
 existingCustomer.setCity(customer.getCity());
 existingCustomer.setState(customer.getState());
 existingCustomer.setEmail(customer.getEmail());
 existingCustomer.setPhone(customer.getPhone());
 return customerRepository.save(existingCustomer);
 } else {

 return null;
 }
 }
 /**
 * Get a list of all customers.
 * @param search
 * @param sortOrder
 * @param sortBy
 * @param pageSize
 * @param page
 *
 * @return The list of all customers.
 */
 public Page<Customer> getAllCustomers(int page, int pageSize, String sortBy, String
sortOrder, String search) {
 Pageable pageable=PageRequest.of(page-1, pageSize,
Sort.by(Sort.Direction.fromString(sortOrder), sortBy));
 if(search.isEmpty())
 return customerRepository.findAll(pageable);
 else {
 return customerRepository.findByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCase(search,search,pageable);
 }
 }
 /**
 * Get a single customer by ID.
 *
 * @param customerId The ID of the customer to be retrieved.
 * @return The customer if found, otherwise an empty Optional.
 */
 public Optional<Customer> getCustomerById(Long customerId) {
 return customerRepository.findById(customerId);
 }
 /**
 * Delete a customer by ID.
 *
 * @param customerId The ID of the customer to be deleted.
 */
 public void deleteCustomer(Long customerId) {
 customerRepository.deleteById(customerId);
 }
}
