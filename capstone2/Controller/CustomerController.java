package com.example.capstone2.Controller;

import com.example.capstone2.Model.Customer;
import com.example.capstone2.Service.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/customer")
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerService customerService;
    Logger logger= LoggerFactory.getLogger(CustomerController.class);
    @GetMapping("/get")
    public ResponseEntity getAllCustomers(){
        logger.info("controller get");
        return ResponseEntity.status(200).body(customerService.getAllCustomers());
    }
    @PostMapping("/add")
    public ResponseEntity addCustomer(@RequestBody@Valid Customer customer){
        logger.info("controller add");
        customerService.addCustomer(customer);
        return ResponseEntity.status(200).body("customer added!");
    }
    @PutMapping("/update/{id}")
    public ResponseEntity updateCustomer(@PathVariable Integer id,@RequestBody@Valid Customer customer){
        logger.info("controller update");
        customerService.updateCustomer(id, customer);
        return ResponseEntity.status(200).body("customer updated!");
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteCustomer(@PathVariable Integer id){
        logger.info("controller delete");
        customerService.deleteCustomer(id);
        return ResponseEntity.status(200).body("customer deleted!");
    }
    //18
    @GetMapping("/logIn/{userName}/{email}")
    public ResponseEntity logIn(@PathVariable String userName,@PathVariable String email){
        logger.info("controller login");
        return ResponseEntity.status(200).body(customerService.logIn(userName, email));
    }
    //19
    @PutMapping("/receipt/{orderId}")
    public ResponseEntity receipt(@PathVariable Integer orderId){
        logger.info("controller receipt");
        return ResponseEntity.status(200).body(customerService.receipt(orderId));
    }
    //20
    @GetMapping("/getSpecial")
    public ResponseEntity findSpecial(){
        logger.info("controller get special customer");
        return ResponseEntity.status(200).body(customerService.findSpecialCustomer());
    }
}
