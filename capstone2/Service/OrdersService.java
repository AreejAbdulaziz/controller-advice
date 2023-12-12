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
public class OrdersService {
    private final OrdersRepository ordersRepository;
    private final CustomerRepository customerRepository;
    private final BookRepository bookRepository;
    public List<Orders>getAllOrders(){
        return ordersRepository.findAll();
    }
    //add order CRUD
    public void rentBook(Orders order){
        Book book=bookRepository.findBookByBookId(order.getBookId());
        if(book==null){
            throw new ApiException("wrong book id");
        }
        Customer customer=customerRepository.findCustomerByCustomerId(order.getCustomerId());
        if(customer==null){
            throw new ApiException("wrong customer id");
        }
        if(book.getQuantity()==0){
            throw new ApiException("no available books");
        }
        if(book.getIsAvailable()==true){
            book.setQuantity(book.getQuantity()-1);
            if(book.getQuantity()==0){
                book.setIsAvailable(false);
            }
            order.setStatus("not returned");
            book.setRentedTimes(book.getRentedTimes()+1);
            customer.setRentedTimes(customer.getRentedTimes()+1);
        }
        ordersRepository.save(order);
        bookRepository.save(book);
    }
    //////////////////////////21///////////////////////////
    public void returnBook(Integer orderId) {
        Orders order=ordersRepository.findOrdersByOrderId(orderId);
        if(order==null){
            throw new ApiException("order not found");
        }
        Book book = bookRepository.findBookByBookId(order.getBookId());
        book.setQuantity(book.getQuantity() + 1);
        order.setStatus("returned");
        if (book.getIsAvailable()==false) {
            book.setIsAvailable(true);
        }
        ordersRepository.save(order);
        bookRepository.save(book);
    }
    public void updateOrder(Integer id,Orders order){
        Orders oldOrder=ordersRepository.findOrdersByOrderId(id);
        if(oldOrder==null){
            throw new ApiException("order id not found");
        }
        Customer customer=customerRepository.findCustomerByCustomerId(order.getCustomerId());
        if(customer==null){
            throw new ApiException("wrong customer id");
        }
        Book book=bookRepository.findBookByBookId(order.getBookId());
        if(book==null){
            throw new ApiException("wrong book id");
        }
        oldOrder.setCustomerId(order.getCustomerId());
        oldOrder.setBookId(order.getBookId());
        oldOrder.setNumDays(order.getNumDays());
        oldOrder.setStatus(order.getStatus());
        oldOrder.setTotalPrice(bookRepository.findBookByBookId(order.getBookId()).getPriceForOneDay()*(order.getNumDays()));
        ordersRepository.save(oldOrder);
    }
    public void deleteOrder(Integer id){
        Orders order=ordersRepository.findOrdersByOrderId(id);
        if(order==null){
            throw new ApiException("order id not found");
        }
        ordersRepository.delete(order);
    }
    //////////////////////22////////////////////
    public List<Orders>findNotReturnedOrders(){
        List<Orders>orders=ordersRepository.findOrdersNotReturned();
        if(orders.isEmpty()){
            throw new ApiException("there is no order not returned");
        }
        return orders;
    }
    /////////////////////23////////////////////
    public List<Orders>findReturnedOrders(){
        List<Orders>orders=ordersRepository.findOrdersReturned();
        if(orders.isEmpty()){
            throw new ApiException("there is no order returned");
        }
        return orders;
    }
}
