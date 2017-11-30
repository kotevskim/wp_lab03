package mk.ukim.finki.wp.web.wp_lab4.service;

import mk.ukim.finki.wp.web.wp_lab4.model.Order;

public interface OrderService {
    public Order placeOrder(String pizzaType, String clientName, String address);
}
