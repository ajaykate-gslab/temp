package com.example.Chargebee_Apis.Chargebee_Apis.controller;

import com.example.Chargebee_Apis.Chargebee_Apis.entity.Product;
import com.example.Chargebee_Apis.Chargebee_Apis.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/product")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    //----------------------- API TO CREATE PRODUCT AT LOCAL ---------------------
    @PostMapping("/create")
    public ResponseEntity createProduct(@RequestBody Product product){
        return new ResponseEntity( productRepository.save(product), HttpStatus.CREATED);
    }

    //-----------------------------------------------------------------------------

    @GetMapping("/listAll")
    public ResponseEntity listAllProduct(){
        return new ResponseEntity(productRepository.findAll(),HttpStatus.OK);
    }

    //-------------------------------------------------------------------------------

    @GetMapping("/getById")
    public ResponseEntity fetchProductById(@RequestBody Product product){

        //productRepository.findById(product.getProduct_id());
        return new ResponseEntity(productRepository.getById(product.getProduct_id()),HttpStatus.OK);

    }


}
