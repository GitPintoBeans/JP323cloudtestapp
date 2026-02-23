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

    @GetMapping
    public String list(Model model) {
        model.addAttribute("products", productRepo.findAll());
        return "products/list";
    }

    @GetMapping("/new")
    public String newForm(Model model) {
        model.addAttribute("product", new Product());
        return "products/new";
    }

    @PostMapping
    public String create(@ModelAttribute Product product) {
        productRepo.save(product);
        return "redirect:/products";
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable Integer id) {
        productRepo.deleteById(id);
        return "redirect:/products";
    }
}