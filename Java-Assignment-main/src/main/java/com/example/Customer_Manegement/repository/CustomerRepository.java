package com.example.Customer_Manegement.repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.RepositoryDefinition;
import org.springframework.stereotype.Repository;
import com.example.Customer_Manegement.model.Customer;
@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
Page<Customer>
findByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCase(String search, String
search2,
Pageable pageable);

}