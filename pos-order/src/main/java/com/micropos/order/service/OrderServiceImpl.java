package com.micropos.order.service;

import com.micropos.cart.model.Cart;
import com.micropos.dto.OrderDto;
import com.micropos.order.model.Item;
import com.micropos.order.model.Order;
import com.micropos.order.repository.ItemRepository;
import com.micropos.order.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ItemRepository itemRepository;

    @Override
    public Order createOrder(Cart cart) {
        Order order = new Order();
        order = orderRepository.save(order);
        List<Item> items = new ArrayList<>();
        for (com.micropos.cart.model.Item item : cart.items()) {
            Item item1 = new Item();
            item1.order(order)
                    .productId(item.productId())
                    .productName(item.productName())
                    .quantity(item.quantity())
                    .unitPrice(item.unitPrice());
            items.add(item1);
            itemRepository.save(item1);
        }
        order.items(items);
        order.status(OrderDto.StatusEnum.CREATED);
        return orderRepository.save(order);
    }

    @Override
    public List<Order> listOrders() {
        return Streamable.of(orderRepository.findAll()).toList();
    }
}
