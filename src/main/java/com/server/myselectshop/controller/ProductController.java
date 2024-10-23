package com.server.myselectshop.controller;

import com.server.myselectshop.dto.ProductMypriceRequestDto;
import com.server.myselectshop.dto.ProductRequestDto;
import com.server.myselectshop.dto.ProductResponseDto;
import com.server.myselectshop.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ProductController {

    private final ProductService productService;

    @PostMapping("/products")
    public ProductResponseDto createProduct(
            @RequestBody ProductRequestDto requestDto
    ) {
        return productService.createProduct(requestDto);
    }

    @PutMapping("/products/{id}")
    public ProductResponseDto updateProduct(
            @PathVariable Long id,
            @RequestBody ProductMypriceRequestDto requestDto
    ) {
        return productService.updateProduct(id, requestDto);
    }
}
