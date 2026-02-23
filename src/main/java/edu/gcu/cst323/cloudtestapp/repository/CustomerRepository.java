package edu.gcu.cst323.cloudtestapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.gcu.cst323.cloudtestapp.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
}