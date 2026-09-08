package dev.gustavo.orderservice.client;

import dev.gustavo.orderservice.exception.ProductNotFoundException;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
public class ProductClient {

    private final RestClient restClient;

    public ProductClient(
            RestClient.Builder builder,
            @org.springframework.beans.factory.annotation.Value("${product-service.url}") String productServiceUrl) {
        this.restClient = builder.baseUrl(productServiceUrl).build();
    }

    public ProductDTO getProductById(Long id) {
        return restClient.get()
                .uri("/products/{id}", id)
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, (request, response) -> {
                    throw new ProductNotFoundException("Product not found with id: " + id);
                })
                .body(ProductDTO.class);
    }
}
