package com.server.myselectshop.controller;

import com.server.myselectshop.dto.ProductMypriceRequestDto;
import com.server.myselectshop.dto.ProductRequestDto;
import com.server.myselectshop.dto.ProductResponseDto;
import com.server.myselectshop.security.UserDetailsImpl;
import com.server.myselectshop.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ProductController {

    private final ProductService productService;

    @PostMapping("/products")
    public ProductResponseDto createProduct(
            @RequestBody ProductRequestDto requestDto,
            @AuthenticationPrincipal UserDetailsImpl userDetails
            ) {
        return productService.createProduct(requestDto, userDetails.getUser());
    }

    @PutMapping("/products/{id}")
    public ProductResponseDto updateProduct(
            @PathVariable Long id,
            @RequestBody ProductMypriceRequestDto requestDto
    ) {
        return productService.updateProduct(id, requestDto);
    }

    @GetMapping("/products")
    public Page<ProductResponseDto> getProducts(
            @RequestParam("page") int page,
            @RequestParam("size") int size,
            @RequestParam("sortBy") String sortBy,
            @RequestParam("isAsc") boolean isAsc,
            @AuthenticationPrincipal UserDetailsImpl userDetails
    ) {
        return productService.getProducts(
                userDetails.getUser(),
                page-1,
                size,
                sortBy,
                isAsc
        );
    }

    @GetMapping("/admin/products")
    public List<ProductResponseDto> getAllProducts() {
        return productService.getAllProducts();
    }
}
