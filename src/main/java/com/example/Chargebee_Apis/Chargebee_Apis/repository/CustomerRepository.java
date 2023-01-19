package com.example.Chargebee_Apis.Chargebee_Apis.repository;

import com.example.Chargebee_Apis.Chargebee_Apis.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer,String> {
}
