package mk.ukim.finki.wp.web.wp_lab4.service.impl;

import mk.ukim.finki.wp.web.wp_lab4.model.Order;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class OrderGeneratorTest {

    OrderGenerator service;

    @Before
    public void setup() {
        service = new OrderGenerator();
    }

    @Test
    public void test_place_order() {
        String pizzaType = "pizzaType";
        String clientName = "clientName";
        String clientAddress = "clientAddress";

        Order actual = service.placeOrder(pizzaType, clientName, clientAddress);

        assertNotNull(actual);
        assertEquals(actual.getPizzaType(), pizzaType);
        assertEquals(actual.getClientName(), clientName);
        assertEquals(actual.getClientAddress(), clientAddress);
        assertNotNull(actual.getOrderId());
    }

}