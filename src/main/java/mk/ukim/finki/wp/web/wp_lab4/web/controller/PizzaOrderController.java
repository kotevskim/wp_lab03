package mk.ukim.finki.wp.web.wp_lab4.web.controller;

import mk.ukim.finki.wp.web.wp_lab4.model.Order;
import mk.ukim.finki.wp.web.wp_lab4.service.OrderService;
import mk.ukim.finki.wp.web.wp_lab4.service.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.*;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
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

    @RequestMapping(value = "/pizza_index.html", method = GET)
    public ModelAndView index() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index");
        List<String> pizzaTypes = pizzaService.getPizzaTypes();
        modelAndView.addObject("pizzaTypes", pizzaTypes);
        return modelAndView;
    }

    @RequestMapping(value = "/client_info.html", method = POST)
    ModelAndView showClientInfo(@RequestParam String pizzaType, HttpSession session) {
        session.setAttribute("pizzaType", pizzaType);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("client-input-info");
        return modelAndView;
    }

    @RequestMapping(value = "/order-info.html", method = POST)
    public ModelAndView placeOrder(
            @RequestParam String clientName,
            @RequestParam String clientAddress,
            HttpSession session) {
        String pizzaType = session.getAttribute("pizzaType").toString();
        Order order = this.orderService.placeOrder(pizzaType, clientName, clientAddress);
        Map<Long, Order> orders = (Map<Long, Order>) session.getAttribute("orders");
        orders.put(order.getOrderId(), order);
        // TODO ask riste should i explicitly call setAttribute, because getAttribute returns a reference to the real object, not to a copy
//        session.setAttribute("orders", orders);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("order-info");
        modelAndView.addObject("order", order);
        return modelAndView;
    }

    @RequestMapping(value = "/order/{id}", method = GET)
    public ModelAndView showOrder(@PathVariable String id, HttpSession session) {
        ModelAndView modelAndView = new ModelAndView();
        Map<Long, Order> orders = (Map<Long, Order>) session.getAttribute("orders");
        if (!orders.keySet().contains(Long.parseLong(id))) {
            modelAndView.setViewName("/order-not-exist");
            modelAndView.addObject("id", id);
            return modelAndView;
        }
        modelAndView.setViewName("order-info");
        Order order = orders.get(Long.parseLong(id));
        modelAndView.addObject("order", order);
        return modelAndView;
    }

    @RequestMapping(value = "/delete/{id}", method = GET)
    public ModelAndView deleteOrder(@PathVariable String id, HttpSession session) {
        Map<Long, Order> orders = (Map<Long, Order>) session.getAttribute("orders");
        orders.remove(Long.parseLong(id));
//        session.setAttribute("orders", orders);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index");
        List<String> pizzaTypes = pizzaService.getPizzaTypes();
        modelAndView.addObject("pizzaTypes", pizzaTypes);
        return modelAndView;
    }
}
