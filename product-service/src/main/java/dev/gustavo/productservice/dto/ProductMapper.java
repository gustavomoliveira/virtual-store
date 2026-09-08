package dev.gustavo.productservice.dto;

import dev.gustavo.productservice.dto.request.ProductRequestDTO;
import dev.gustavo.productservice.dto.response.ProductResponseDTO;
import dev.gustavo.productservice.model.Product;

public class ProductMapper {

    public static Product toEntity(ProductRequestDTO dto) {
        return new Product(
                dto.name(),
                dto.category(),
                dto.price(),
                dto.description()
        );

    }

    public static ProductResponseDTO toDTO(Product entity) {
        return new ProductResponseDTO(
                entity.getId(),
                entity.getName(),
                entity.getCategory(),
                entity.getPrice(),
                entity.getDescription()
        );
    }
}
