package com.example.beststore.controller;

import com.example.beststore.models.product;
import com.example.beststore.models.productDto;
import com.example.beststore.service.productService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class productController {

    private final productService service;
    public productController(productService service) {
        this.service = service;
    }

    @GetMapping("/products")
    public ResponseEntity<List<product>> getAllProduct() {
        List<product> products = service.getAllProducts();
        if (products.isEmpty()) {
            return new ResponseEntity<>(products, HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(products, HttpStatus.OK);
        }


    }

    @PostMapping("/create")
    public ResponseEntity<product>createProduct(@Valid @ModelAttribute productDto dto){
           try{
               product product = service.createProduct(dto);
               return new ResponseEntity<>(product, HttpStatus.CREATED);
           }catch (Exception e){
               return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
           }
    }
}
