package com.training.repository;

import org.springframework.data.repository.CrudRepository;

import com.training.model.Product;

public interface ShoppingCartRepository extends CrudRepository<Product, Long> {

}
