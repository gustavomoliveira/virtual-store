package dev.gustavo.productservice.controller;

import dev.gustavo.productservice.dto.request.ProductRequestDTO;
import dev.gustavo.productservice.dto.response.ProductResponseDTO;
import dev.gustavo.productservice.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<ProductResponseDTO>> getAllProducts() {
        List<ProductResponseDTO> response = service.findAllProducts();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponseDTO> getProductById(@PathVariable Long id) {
        ProductResponseDTO response = service.findProductById(id);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping
    public ResponseEntity<ProductResponseDTO> createProduct(@RequestBody ProductRequestDTO dto) {
        ProductResponseDTO response = service.createProduct(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

}
