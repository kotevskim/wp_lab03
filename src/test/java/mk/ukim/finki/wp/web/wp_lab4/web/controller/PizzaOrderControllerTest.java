package mk.ukim.finki.wp.web.wp_lab4.web.controller;

import mk.ukim.finki.wp.web.wp_lab4.model.Order;
import mk.ukim.finki.wp.web.wp_lab4.service.OrderService;
import mk.ukim.finki.wp.web.wp_lab4.service.PizzaService;
import org.junit.Test;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

public class PizzaOrderControllerTest {

    private PizzaService mockPizzaService;
    private OrderService mockOrderService;
    private PizzaOrderController controller;

    public PizzaOrderControllerTest() {
        mockPizzaService = mock(PizzaService.class);
        mockOrderService = mock(OrderService.class);
        controller = new PizzaOrderController(
                mockPizzaService,
                mockOrderService
        );
    }

    @Test
    public void showPizzaTypes() throws Exception {
        // Arrange
        String urlTemplate = "/order/pizza-type";
        List<String> expectedTypes = Arrays.asList("a", "b", "c");
        String expectedViewName = "pizza-type-input";

        when(mockPizzaService.getPizzaTypes())
                .thenReturn(expectedTypes);
        MockMvc mockMvc = standaloneSetup(this.controller).build();

        // Act and Assert
        mockMvc.perform(get(urlTemplate))
                .andExpect(view().name(expectedViewName))
                .andExpect(model().attributeExists("pizzaTypes"))
                .andExpect(model().attribute("pizzaTypes", expectedTypes));
    }

    @Test
    public void processPizzaTypeSelection() throws Exception {
        String urlTemplate = "/order/pizza-type";
        String expectedRedirectUrl = "/order/client-info";

        MockMvc mockMvc = standaloneSetup(this.controller).build();
        mockMvc.perform(post(urlTemplate))
                .andExpect(redirectedUrl(expectedRedirectUrl));
    }

    @Test
    public void showClientInfoForm() throws Exception {
        String urlTemplate = "/order/client-info";
        String expectedViewName = "client-info-input";

        MockMvc mockMvc = standaloneSetup(this.controller).build();
        mockMvc.perform(get(urlTemplate))
                .andExpect(view().name(expectedViewName));
    }


    // TODO doesn't work as it should
    @Test
    public void processOrder() throws Exception {
        String pizzaType = "Small";
        String clientName = "Martin";
        String clientAddress = "Bul. V. S. Bato";
        Long orderId = Long.parseLong("123");
        String urlTemplate = "/order/client-info";
        String expectedRedirectUrl = "/order/" + orderId;

        when(mockOrderService.placeOrder(pizzaType, clientName, clientAddress))
                .thenReturn(
                        new Order(pizzaType, clientName, clientAddress, orderId)
                );

        MockMvc mockMvc = standaloneSetup(this.controller).build();
        mockMvc.perform(post(urlTemplate)
                .param("clientName", clientName)
                .param("clientAddress", clientAddress)
                .sessionAttr("pizzaType", pizzaType)
                .sessionAttr("orders", new HashMap<Long, Order>())
        ).andExpect(redirectedUrl(expectedRedirectUrl));
    }

    // TODO ask if saving/removing an attr to/from session should be tested
    @Test
    public void deleteOrder() throws Exception {
        String urlTemplate = "/order/delete/123";
        String expectedRedirectUrl = "/order/pizza-type";

        MockMvc mockMvc = standaloneSetup(this.controller).build();
        mockMvc.perform(get(urlTemplate)
        .sessionAttr("orders", new HashMap<Long, Order>()))
                .andExpect(redirectedUrl(expectedRedirectUrl));
    }

}