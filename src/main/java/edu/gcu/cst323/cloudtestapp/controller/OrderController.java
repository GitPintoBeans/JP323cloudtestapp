package edu.gcu.cst323.cloudtestapp.controller;

import edu.gcu.cst323.cloudtestapp.entity.*;
import edu.gcu.cst323.cloudtestapp.model.OrderCreateForm;
import edu.gcu.cst323.cloudtestapp.repository.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Controller
@RequestMapping("/orders")
public class OrderController {

    private final OrderRepository orderRepo;
    private final CustomerRepository customerRepo;
    private final ProductRepository productRepo;

    public OrderController(OrderRepository orderRepo, CustomerRepository customerRepo, ProductRepository productRepo) {
        this.orderRepo = orderRepo;
        this.customerRepo = customerRepo;
        this.productRepo = productRepo;
    }

    // Display a list of all orders
    @GetMapping
    public String list(Model model) {
        model.addAttribute("orders", orderRepo.findAll());
        return "orders/list";
    }

    // Show form to create a new order
    @GetMapping("/new")
    public String newForm(Model model) {
        model.addAttribute("form", new OrderCreateForm());
        model.addAttribute("customers", customerRepo.findAll());
        model.addAttribute("products", productRepo.findAll());
        return "orders/new";
    }

    // Handle submission of new order data
    @PostMapping
    public String create(@ModelAttribute("form") OrderCreateForm form) {
        Customer customer = customerRepo.findById(form.getCustomerId()).orElseThrow();

        Order order = new Order();
        order.setCustomer(customer);
        order.setOrderDate(LocalDateTime.now());

        BigDecimal total = BigDecimal.ZERO;

        // Build order items only where quantity > 0
        for (var entry : form.getQuantities().entrySet()) {
            Integer productId = entry.getKey();
            Integer qty = entry.getValue();

            if (qty == null || qty <= 0) continue;

            Product product = productRepo.findById(productId).orElseThrow();

            OrderItem item = new OrderItem();
            item.setOrder(order);
            item.setProduct(product);
            item.setQuantity(qty);

            order.getItems().add(item);

            total = total.add(product.getPrice().multiply(BigDecimal.valueOf(qty)));
        }

        order.setTotalAmount(total);
        orderRepo.save(order); // cascades items

        return "redirect:/orders";
    }

    // Delete an order by id and return to list
    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        orderRepo.deleteById(id);
        return "redirect:/orders";
    }
}