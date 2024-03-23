package com.g2academy.jwt.Repository;

import com.g2academy.jwt.Entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,Integer> {

}
