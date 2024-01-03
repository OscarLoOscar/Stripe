package com.example.shoppingcart.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.shoppingcart.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
