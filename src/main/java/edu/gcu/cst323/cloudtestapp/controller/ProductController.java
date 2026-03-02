package edu.gcu.cst323.cloudtestapp.controller;

import edu.gcu.cst323.cloudtestapp.entity.Product;
import edu.gcu.cst323.cloudtestapp.repository.ProductRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/products")
public class ProductController {

    private final ProductRepository productRepo;

    public ProductController(ProductRepository productRepo) {
        this.productRepo = productRepo;
    }

    // Show all products
    @GetMapping
    public String list(Model model) {
        model.addAttribute("products", productRepo.findAll());
        return "products/list";
    }

    // Display form to add a new product
    @GetMapping("/new")
    public String newForm(Model model) {
        model.addAttribute("product", new Product());
        return "products/new";
    }

    // Persist a new or updated product
    @PostMapping
    public String create(@ModelAttribute Product product) {
        productRepo.save(product);
        return "redirect:/products";
    }

    // Remove a product by its ID and refresh list
    @PostMapping("/{id}/delete")
    public String delete(@PathVariable Integer id) {
        productRepo.deleteById(id);
        return "redirect:/products";
    }
}