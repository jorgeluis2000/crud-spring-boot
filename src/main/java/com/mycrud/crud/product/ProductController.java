package com.mycrud.crud.product;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/v1/products")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public List<Product> getProducts() {
        return productService.getProducts();
    }

    @GetMapping(path = "/hello")
    public String getHelloWorld() {
        return productService.getHelloWorld();
    }

    @PostMapping
    public ResponseEntity<Object> registerProduct(@RequestBody Product product) {

        try {
            return this.productService.newProduct(product);
        } catch (Exception e) {
            return new ResponseEntity<>(
                e.getMessage(),
                HttpStatus.INTERNAL_SERVER_ERROR
            );
        }

    }

    @PutMapping
    public ResponseEntity<Object> updateProduct(@RequestBody Product product) {

        HashMap<String, Object> dataM = new HashMap<>();
        dataM.put("ok", false);
        dataM.put("message", "Upps!! There was a problem.");
        dataM.put("data", null);
        
        return new ResponseEntity<>(
            dataM,
            HttpStatus.BAD_REQUEST
        );
    }
}
