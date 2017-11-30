package mk.ukim.finki.wp.web.wp_lab4.web;

import mk.ukim.finki.wp.web.wp_lab4.model.Order;
import mk.ukim.finki.wp.web.wp_lab4.service.OrderService;
import mk.ukim.finki.wp.web.wp_lab4.service.PizzaService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
public class PizzaOrderController {

    private PizzaService pizzaService;
    private OrderService orderService;

    public PizzaOrderController(
            PizzaService pizzaService,
            OrderService orderService) {
        this.pizzaService = pizzaService;
        this.orderService = orderService;
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
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("order-info");
        modelAndView.addObject("order", order);
        return modelAndView;
    }
}
