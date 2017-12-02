package mk.ukim.finki.wp.web.wp_lab4.web.controller;

import mk.ukim.finki.wp.web.wp_lab4.model.Order;
import mk.ukim.finki.wp.web.wp_lab4.service.OrderService;
import mk.ukim.finki.wp.web.wp_lab4.service.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.*;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
@RequestMapping("/order")
public class PizzaOrderController {

    private PizzaService pizzaService;
    private OrderService orderService;

    @Autowired
    public PizzaOrderController(
            PizzaService pizzaService,
            OrderService simpleOrderService) {
        this.pizzaService = pizzaService;
        this.orderService = simpleOrderService;
    }

    @RequestMapping(value = "pizza-type", method = GET)
    public String showPizzaTypes(Map model) {
        model.put("pizzaTypes", pizzaService.getPizzaTypes());
        return "pizza-type-input";
    }

    @RequestMapping(value = "client-info", method = POST)
    String showClientInfo(@RequestParam String pizzaType, HttpSession session) {
        session.setAttribute("pizzaType", pizzaType);
        return "client-input-info";
    }

    @RequestMapping(value = "order-info", method = POST)
    public String placeOrder(
            @RequestParam String clientName,
            @RequestParam String clientAddress,
            HttpSession session) {
        String pizzaType = session.getAttribute("pizzaType").toString();
        Order order = this.orderService.placeOrder(pizzaType, clientName, clientAddress);
        Map<Long, Order> orders = (Map<Long, Order>) session.getAttribute("orders");
        orders.put(order.getOrderId(), order);
        // TODO ask riste should i explicitly call setAttribute, because getAttribute returns a reference to the real object, not to a copy
//        session.setAttribute("orders", orders);
        return "redirect:/order/" + order.getOrderId();
    }

    @RequestMapping(value = "{id}", method = GET)
    public String showOrder(@PathVariable String id, HttpSession session, Map model) {
        Map<Long, Order> orders = (Map<Long, Order>) session.getAttribute("orders");
        String viewName;
        Long orderId = Long.parseLong(id);
        if (!orders.keySet().contains(orderId)){
            model.put("id", id);
            viewName = "order-not-exist";
        } else {
            Order order = orders.get(orderId);
            model.put("order", order);
            viewName = "order-info";
        }
        return viewName;
    }

    @RequestMapping(value = "delete/{id}", method = GET)
    public String deleteOrder(@PathVariable String id, HttpSession session) {
        Map<Long, Order> orders = (Map<Long, Order>) session.getAttribute("orders");
        orders.remove(Long.parseLong(id));
        return "redirect:/order/pizza-type";
    }
}
