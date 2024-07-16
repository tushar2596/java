package com.example.Customer_Manegement;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.CrossOrigin;
@SpringBootApplication
@CrossOrigin("*")
public class CustomerManegementApplication {
public static void main(String[] args) {
SpringApplication.run(CustomerManegementApplication.class, args);
}
}