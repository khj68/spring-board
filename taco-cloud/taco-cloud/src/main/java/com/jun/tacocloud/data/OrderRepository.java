package com.jun.tacocloud.data;

import java.util.Date;
import java.util.List;

import com.jun.tacocloud.Order;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<Order, Long>{
    // Order save(Order order);
    List<Order> findByDeliveryZip(String deliveryZip);
    List<Order> readOrdersByDeliveryZipAndPlacedAtBetween(String deliveryZip, Date startDate, Date endDate);

    // @Query("Order o where o.deliveryCity='Seattle'")
    // List<Order> readOrdersDeliveredInSeattle();
}