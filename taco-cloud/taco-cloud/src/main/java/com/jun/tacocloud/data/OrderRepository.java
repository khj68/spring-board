package com.jun.tacocloud.data;

import com.jun.tacocloud.Order;

import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<Order, Long>{
    // Order save(Order order);
}