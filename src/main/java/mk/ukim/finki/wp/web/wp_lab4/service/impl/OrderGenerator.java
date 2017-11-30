package mk.ukim.finki.wp.web.wp_lab4.service.impl;

import mk.ukim.finki.wp.web.wp_lab4.model.Order;
import mk.ukim.finki.wp.web.wp_lab4.service.OrderService;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class OrderGenerator implements OrderService {

    @Override
    public Order placeOrder(String pizzaType, String clientName, String clientAddress) {
        Random rand = new Random();
        Long odrerId = Long.valueOf(rand.nextInt(1000));
        return new Order(pizzaType, clientName, clientAddress, odrerId);
    }
}
