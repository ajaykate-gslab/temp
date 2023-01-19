package com.example.Chargebee_Apis.Chargebee_Apis.repository;

import com.example.Chargebee_Apis.Chargebee_Apis.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,String> {
}
