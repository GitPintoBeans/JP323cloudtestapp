package edu.gcu.cst323.cloudtestapp.repository;

import edu.gcu.cst323.cloudtestapp.entity.Order;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Integer> {

    // Used by orders/list.html so Thymeleaf can safely access customer + items + product
    @Override
    @EntityGraph(attributePaths = {"customer", "items", "items.product"})
    List<Order> findAll();
}