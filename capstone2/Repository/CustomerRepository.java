package com.example.capstone2.Repository;

import com.example.capstone2.Model.Book;
import com.example.capstone2.Model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Integer> {
    Customer findCustomerByCustomerId(Integer id);

    Customer findCustomerByUserNameAndEmail(String userName,String email);

}
