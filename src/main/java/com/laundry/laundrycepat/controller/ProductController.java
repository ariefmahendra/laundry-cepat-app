package com.laundry.laundrycepat.controller;

import com.laundry.laundrycepat.dto.ProductCreateRequest;
import com.laundry.laundrycepat.dto.WebResponse;
import com.laundry.laundrycepat.entity.ProductEntity;
import com.laundry.laundrycepat.service.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    // create product
    @PostMapping("/api/products")
    public WebResponse<ProductEntity> create(@RequestBody ProductCreateRequest payload){
        ProductEntity productEntity = productService.create(payload);
        return WebResponse.<ProductEntity>builder()
                .message("product created")
                .data(productEntity)
                .build();
    }

    // update product
    @PutMapping("/api/products/{id}")
    public WebResponse<ProductEntity> update(@PathVariable String id, @RequestBody ProductCreateRequest payload){
        ProductEntity request = new ProductEntity();
        request.setId(id);
        request.setName(payload.getName());
        request.setPrice(payload.getPrice());
        request.setUnit(payload.getUnit());

        ProductEntity productEntity = productService.update(request);
        return WebResponse.<ProductEntity>builder()
                .message("product updated")
                .data(productEntity)
                .build();
    }

    // delete product
    @DeleteMapping("/api/products/{id}")
    public WebResponse<String> delete(@PathVariable String id){
        productService.delete(id);
        return WebResponse.<String>builder()
                .message("product deleted")
                .build();
    }

    // get list product
    @GetMapping("/api/products")
    public WebResponse<List<ProductEntity>> getAll(){
        List<ProductEntity> products = productService.getAll();
        return WebResponse.<List<ProductEntity>>builder().message("OK").data(products).build();
    }

    // get product by id
    @GetMapping("/api/products/{id}")
    public WebResponse<ProductEntity> getById(@PathVariable String id){
        ProductEntity product = productService.getById(id);
        return WebResponse.<ProductEntity>builder().message("OK").data(product).build();
    }

}
