package edu.gcu.cst323.cloudtestapp.repository;

import edu.gcu.cst323.cloudtestapp.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Integer> {
}