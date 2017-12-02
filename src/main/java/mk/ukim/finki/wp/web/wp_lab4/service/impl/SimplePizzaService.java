package mk.ukim.finki.wp.web.wp_lab4.service.impl;

import mk.ukim.finki.wp.web.wp_lab4.service.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class SimplePizzaService implements PizzaService {

    @Autowired
    public SimplePizzaService() {
    }

    @Override
    public List<String> getPizzaTypes() {
        return Arrays.asList("Small", "Medium", "Large");
    }
}
