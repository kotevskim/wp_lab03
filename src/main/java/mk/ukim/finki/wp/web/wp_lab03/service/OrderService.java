package mk.ukim.finki.wp.web.wp_lab03.service;

import mk.ukim.finki.wp.web.wp_lab03.model.Order;

public interface OrderService {
    public Order placeOrder(String pizzaType, String clientName, String address);
}
