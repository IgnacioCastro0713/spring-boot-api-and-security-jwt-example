package com.ignacio.store.repositories;

import com.ignacio.store.models.ProductModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ProductRepository extends CrudRepository<ProductModel, Long> {

}
