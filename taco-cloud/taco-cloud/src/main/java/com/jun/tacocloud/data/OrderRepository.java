package com.jun.tacocloud.data;

import com.jun.tacocloud.Order;

public interface OrderRepository {
    Order save(Order order);
}