package edu.gcu.cst323.cloudtestapp.model;

import java.util.HashMap;
import java.util.Map;

public class OrderCreateForm {
    // id of customer placing the order
    private Integer customerId;

    // productId -> quantity mapping from the form
    private Map<Integer, Integer> quantities = new HashMap<>();

    public Integer getCustomerId() { return customerId; }
    public void setCustomerId(Integer customerId) { this.customerId = customerId; }

    public Map<Integer, Integer> getQuantities() { return quantities; }
    public void setQuantities(Map<Integer, Integer> quantities) { this.quantities = quantities; }
}