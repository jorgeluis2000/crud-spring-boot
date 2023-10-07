package com.mycrud.crud.product;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    public ResponseEntity<Object> newProduct(Product product) {
        Optional<Product> res = productRepository.findProductByName(product.getName());

        HashMap<String, Object> dataM = new HashMap<>();
        if (res.isPresent()) {
            dataM.put("ok", false);
            dataM.put("message", "The product already exist with that name!!!");
            dataM.put("data", null);
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        productRepository.save(product);
        dataM.put("ok", true);
        dataM.put("message", "Has been saved a new product!!!");
        dataM.put("data", product);
        return new ResponseEntity<>(
                dataM,
                HttpStatus.CREATED);
    }

    public ResponseEntity<Object> updateProduct(Product product) {

        Optional<Product> productFound = productRepository.findProductByName(product.getName());
        HashMap<String, Object> dataM = new HashMap<>();

        if (!productFound.isPresent()) {
            dataM.put("ok", false);
            dataM.put("message", "The product doesn't exist yet, please create a product with that name!!!");
            dataM.put("data", null);
            return new ResponseEntity<>(dataM, HttpStatus.NOT_FOUND);
        }
        Product productUpdated = productRepository.saveAndFlush(product);
        if (productUpdated.getId() != null) {
            dataM.put("ok", true);
            dataM.put("message", "The product has been updated!!!");
            dataM.put("data", product);
            return new ResponseEntity<>(dataM, HttpStatus.CREATED);

        } else {
            dataM.put("ok", false);
            dataM.put("message", "The product hasn't been updated!!!");
            dataM.put("data", null);
            return new ResponseEntity<>(dataM, HttpStatus.BAD_REQUEST);
        }

    }

    public String getHelloWorld() {
        return "Hello World!";
    }
}
