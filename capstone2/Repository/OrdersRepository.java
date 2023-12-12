package com.example.capstone2.Repository;

import com.example.capstone2.Model.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Repository
public interface OrdersRepository extends JpaRepository<Orders,Integer> {
    Orders findOrdersByOrderId(Integer id);
    @Query("select o from Orders o where o.status='not returned'")
    List<Orders> findOrdersNotReturned();

    @Query("select o from Orders o where o.status='returned'")
    List<Orders> findOrdersReturned();
}
