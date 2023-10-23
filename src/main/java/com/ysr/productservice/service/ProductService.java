package com.ysr.productservice.service;

import com.ysr.productservice.dto.ProductRequest;
import com.ysr.productservice.dto.ProductResponse;
import com.ysr.productservice.model.Product;
import com.ysr.productservice.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {

   private final ProductRepository productRepository;
    public void createProduct(ProductRequest productRequest) {

        Product product= Product.builder().name(productRequest.getName())
                                          .description(productRequest.getDescription())
                                            .price(productRequest.getPrice()).build();

        productRepository.save(product);

        log.info("Product {} is saved",product.getId());
    }

    public List<ProductResponse> getAllProcuts() {
       List<Product> products= productRepository.findAll();
       return products.stream().map(this::mapToProductResponse).toList();
    }

    private ProductResponse mapToProductResponse(Product product){
        return ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .price(product.getPrice())
                .description(product.getDescription())
                .build();

    }
}
