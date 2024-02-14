package com.sergio.springboot.di.app.springbootdi.services;

import java.util.*;
import java.util.stream.Collectors;

/* import org.springframework.beans.factory.annotation.Autowired; */
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.sergio.springboot.di.app.springbootdi.models.Product;
import com.sergio.springboot.di.app.springbootdi.repositories.ProductRepositoryJson;

@Service
public class ProductServiceImpl implements ProductService {
    
    /* @Autowired */
    private ProductRepositoryJson repository;

    @Value("${config.price.tax}")
    private Double tax; 

    //@Autowired
    public ProductServiceImpl(@Qualifier("productJson") ProductRepositoryJson repository) {
        this.repository = repository;
    }

    @Override
    public List<Product> findAll() {
        return repository.findAll().stream().map(p -> {
            Double priceTax = p.getPrice() * tax;
            p.setPrice(priceTax.longValue());
            return p;
        }).collect(Collectors.toList());
    }
    

    @Override
    public Product findById(Long id) {
        return repository.findById(id);
    }    

}
