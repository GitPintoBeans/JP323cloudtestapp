package edu.gcu.cst323.cloudtestapp.repository;

import edu.gcu.cst323.cloudtestapp.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {
}