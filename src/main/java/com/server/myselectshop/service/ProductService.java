package com.server.myselectshop.service;

import com.server.myselectshop.dto.ProductRequestDto;
import com.server.myselectshop.dto.ProductResponseDto;
import com.server.myselectshop.entity.Product;
import com.server.myselectshop.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public ProductResponseDto createProduct(ProductRequestDto requestDto) {
        Product product = productRepository.save(new Product(requestDto));
        return new ProductResponseDto(product);
    }
}
