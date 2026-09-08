package dev.gustavo.productservice.service;

import dev.gustavo.productservice.dto.*;
import dev.gustavo.productservice.dto.request.ProductRequestDTO;
import dev.gustavo.productservice.dto.response.ProductResponseDTO;
import dev.gustavo.productservice.exception.ProductNotFoundException;
import dev.gustavo.productservice.model.Product;
import dev.gustavo.productservice.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository repository;

    public ProductService(ProductRepository repository) {
        this.repository = repository;
    }

    public List<ProductResponseDTO> findAllProducts() {
        List<Product> products = repository.findAll();
        return products.stream().map(ProductMapper::toDTO).toList();
    }

    public ProductResponseDTO findProductById(Long id) {
        Product product = repository.findById(id).orElseThrow(() -> new ProductNotFoundException("Product not found."));
        return ProductMapper.toDTO(product);
    }

    public ProductResponseDTO createProduct(ProductRequestDTO dto) {
        Product product = repository.save(ProductMapper.toEntity(dto));
        return ProductMapper.toDTO(product);
    }
}
