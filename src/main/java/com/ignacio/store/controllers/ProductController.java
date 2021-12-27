package com.ignacio.store.controllers;

import com.ignacio.store.models.ProductModel;
import com.ignacio.store.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.MessageFormat;
import java.util.ArrayList;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    ProductService productService;

    @GetMapping
    public ArrayList<ProductModel> getAll() {
        return productService.getAll();
    }

    @PostMapping
    public ProductModel save(@RequestBody ProductModel model) {
        return productService.save(model);
    }

    @DeleteMapping()
    public String DeleteById(@RequestParam("productId") Long id) {
        var ok = productService.deleteById(id);
        return ok ?
                MessageFormat.format("The product with id: {0} was deleted", id)
                : MessageFormat.format("The product with id: {0} was not deleted", id);
    }
}
