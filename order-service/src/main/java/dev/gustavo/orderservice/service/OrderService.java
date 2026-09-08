package dev.gustavo.orderservice.service;

import dev.gustavo.orderservice.client.ProductClient;
import dev.gustavo.orderservice.client.ProductDTO;
import dev.gustavo.orderservice.dto.OrderMapper;
import dev.gustavo.orderservice.dto.request.OrderRequestDTO;
import dev.gustavo.orderservice.dto.response.OrderRespondeDTO;
import dev.gustavo.orderservice.exception.OrderNotFoundException;
import dev.gustavo.orderservice.model.ItemOrder;
import dev.gustavo.orderservice.model.Order;
import dev.gustavo.orderservice.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class OrderService {

    private final OrderRepository repository;
    private final ProductClient productClient;

    public OrderService(OrderRepository repository, ProductClient productClient) {
        this.repository = repository;
        this.productClient = productClient;
    }

    public List<OrderRespondeDTO> findAllOrders() {
        List<Order> orders = repository.findAll();
        return orders.stream().map(OrderMapper::toDTO).toList();
    }

    public OrderRespondeDTO findOrderById(Long id) {
        Order order = repository.findById(id).orElseThrow(() -> new OrderNotFoundException("Order not found."));
        return OrderMapper.toDTO(order);
    }

    public OrderRespondeDTO createOrder(OrderRequestDTO dto) {
        Order order = OrderMapper.toEntity(dto);
        Map<Long, BigDecimal> prices = new HashMap<>();

        for (ItemOrder item : order.getItens()) {
            ProductDTO product = productClient.getProductById(item.getProductId());
            prices.put(product.id(), product.price());
        }

        order.calculateTotal(prices);
        return OrderMapper.toDTO(repository.save(order));
    }
}
