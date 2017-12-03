package mk.ukim.finki.wp.web.wp_lab4.model;

import javax.validation.constraints.NotNull;

public class Order {
    private String pizzaType;
    @NotNull
    private String clientName;
    @NotNull
    private String clientAddress;
    private Long orderId;

    public Order(String pizzaType, String clientName, String clientAddress, Long orderId) {
        this.pizzaType = pizzaType;
        this.clientName = clientName;
        this.clientAddress = clientAddress;
        this.orderId = orderId;
    }

    public String getPizzaType() {
        return pizzaType;
    }

    public void setPizzaType(String pizzaType) {
        this.pizzaType = pizzaType;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getClientAddress() {
        return clientAddress;
    }

    public void setClientAddress(String clientAddress) {
        this.clientAddress = clientAddress;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    @Override
    public String toString() {
        return "[OrderID:" + this.orderId + "]";
    }
}
