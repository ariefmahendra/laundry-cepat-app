package com.laundry.laundrycepat.service;

import com.laundry.laundrycepat.dto.ProductCreateRequest;
import com.laundry.laundrycepat.entity.ProductEntity;
import com.laundry.laundrycepat.repository.ProductRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    // create product
    @Transactional
    public ProductEntity create(ProductCreateRequest payload){
        boolean productByName = getByName(payload.getName());

        if (productByName){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "product already exist");
        }

        ProductEntity productEntity = new ProductEntity();
        productEntity.setName(payload.getName());
        productEntity.setUnit(payload.getUnit());
        productEntity.setPrice(payload.getPrice());

        return productRepository.save(productEntity);
    }

    // update product
    @Transactional
    public ProductEntity update(ProductEntity payload){
        getById(payload.getId());
        return productRepository.save(payload);
    }

    // delete product
    @Transactional
    public void delete(String id){
        getById(id);
        productRepository.deleteById(id);
    }

    // get product by id
    @Transactional
    public ProductEntity getById(String id){
        return productRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "product not found"));
    }

    // get list product
    @Transactional
    public List<ProductEntity> getAll(){
        return productRepository.findAll();
    }

    // get product by name
    @Transactional
    public boolean getByName(String name){
        Optional<ProductEntity> product = productRepository.findByName(name);
        return product.isPresent();
    }
}
