package com.micropos.order.model;

import com.micropos.cart.model.Cart;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "orders")
@Data
public class Order {
    @Id
    @GeneratedValue
    private Integer id;

    @OneToOne
    @JoinTable(name = "carts", joinColumns = @JoinColumn(name = "cart_id"))
    private Cart cart;
}
