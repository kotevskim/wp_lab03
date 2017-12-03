package mk.ukim.finki.wp.web.wp_lab4.web.controller;

import mk.ukim.finki.wp.web.wp_lab4.model.Order;
import mk.ukim.finki.wp.web.wp_lab4.service.OrderService;
import mk.ukim.finki.wp.web.wp_lab4.service.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;

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
            PizzaService simplePizzaService,
            OrderService simpleOrderService) {
        this.pizzaService = simplePizzaService;
        this.orderService = simpleOrderService;
    }

    @RequestMapping(value = "pizza-type", method = GET)
    public String showPizzaTypes(Map<String, Object> model) {
        model.put("pizzaTypes", pizzaService.getPizzaTypes());
        return "pizza-type-input";
    }

    @RequestMapping(value = "pizza-type", method = POST)
    public String processPizzaTypeSelection(
            @RequestParam(defaultValue = "Medium") String pizzaType,
            HttpSession session) {
        session.setAttribute("pizzaType", pizzaType);
        return "redirect:/order/client-info";
    }

    @RequestMapping(value = "client-info", method = GET)
    public String showClientInfoForm() {
        return "client-info-input";
    }

    @RequestMapping(value = "client-info", method = POST)
    public String processOrder(
            @RequestParam String clientName,
            @RequestParam String clientAddress,
            @SessionAttribute String pizzaType,
            @SessionAttribute Map<Long, Order> orders) {
        Order order = this.orderService.placeOrder(
                pizzaType, clientName, clientAddress
        );
        orders.put(order.getOrderId(), order);
        // TODO ask should i explicitly call setAttribute, because getAttribute returns a reference to the real object, not to a copy
//        session.setAttribute("orders", orders);
        return "redirect:/order/" + order.getOrderId();
    }

    // TODO how to test this method
    @RequestMapping(value = "{id}", method = GET)
    public String showOrder(
            @PathVariable String id,
            @SessionAttribute Map<Long, Order> orders,
            Map<String, Object> model) {
        Long orderId = Long.parseLong(id);
        Order order = orders.get(orderId);
        if (order == null) {
            return "page-not-found";
        }
        model.put("order", order);
        return "order-info";
    }

    @RequestMapping(value = "delete/{id}", method = GET)
    public String deleteOrder(
            @PathVariable String id,
            @SessionAttribute Map<Long, Order> orders) {
        orders.remove(Long.parseLong(id));
        return "redirect:/order/pizza-type";
    }

}
