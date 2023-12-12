package com.example.capstone2.Service;

import com.example.capstone2.Api.ApiException;
import com.example.capstone2.Model.Book;
import com.example.capstone2.Model.Customer;
import com.example.capstone2.Model.Orders;
import com.example.capstone2.Repository.BookRepository;
import com.example.capstone2.Repository.CustomerRepository;
import com.example.capstone2.Repository.OrdersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final OrdersRepository ordersRepository;
    private final BookRepository bookRepository;
    public List<Customer> getAllCustomers(){
        return customerRepository.findAll();
    }
    public void addCustomer(Customer customer){
        customerRepository.save(customer);
    }
    public void updateCustomer(Integer id,Customer customer){
        Customer oldCustomer=customerRepository.findCustomerByCustomerId(id);
        if(oldCustomer==null){
            throw new ApiException("wrong id");
        }
        oldCustomer.setName(customer.getName());
        oldCustomer.setAddress(customer.getAddress());
        oldCustomer.setAge(customer.getAge());
        oldCustomer.setUserName(customer.getUserName());
        oldCustomer.setEmail(customer.getEmail());
        oldCustomer.setPassword(customer.getPassword());
        oldCustomer.setPhoneNumber(customer.getPhoneNumber());
        oldCustomer.setCatPref(customer.getCatPref());

        customerRepository.save(oldCustomer);
    }
    public void deleteCustomer(Integer id){
        Customer customer=customerRepository.findCustomerByCustomerId(id);
        if(customer==null){
            throw new ApiException("customer not found");
        }
        customerRepository.delete(customer);
    }
    //18
    public Customer logIn(String userName,String email){
        Customer customer=customerRepository.findCustomerByUserNameAndEmail(userName, email);
        if(customer==null){
            throw new ApiException("wrong user name or email");
        }
        return customer;
    }
    //19
    public Orders receipt(Integer orderId){
        Orders order=ordersRepository.findOrdersByOrderId(orderId);
        if(order==null){
            throw new ApiException("order not found");
        }
        if(customerRepository.findCustomerByCustomerId(order.getCustomerId()).getRentedTimes()>10){
            order.setTotalPrice(bookRepository.findBookByBookId(order.getBookId()).getPriceForOneDay()*(order.getNumDays())/2);
        }else order.setTotalPrice(bookRepository.findBookByBookId(order.getBookId()).getPriceForOneDay()*(order.getNumDays()));
        ordersRepository.save(order);
        return order;
    }
    //20
    public Customer findSpecialCustomer(){
        List<Customer>customers=customerRepository.findAll();
        if(customers.isEmpty()){
            throw new ApiException("no customers");
        }
        Customer c=new Customer();
        for(int i=0;i<customers.size();i++){
            if(customers.get(i).getRentedTimes()>c.getRentedTimes()){
                c=customers.get(i);
                customerRepository.save(c);
            }
        }
        return c;
    }
}
