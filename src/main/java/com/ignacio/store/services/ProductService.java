package com.ignacio.store.services;

import com.ignacio.store.models.ProductModel;
import com.ignacio.store.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    public ArrayList<ProductModel> getAll() {
        return (ArrayList<ProductModel>) productRepository.findAll();
    }

    public ProductModel save(ProductModel model) {
        return productRepository.save(model);
    }

    public boolean deleteById(Long id) {
        try {
            productRepository.deleteById(id);

            return true;
        } catch (Exception e) {

            return false;
        }
    }
}
