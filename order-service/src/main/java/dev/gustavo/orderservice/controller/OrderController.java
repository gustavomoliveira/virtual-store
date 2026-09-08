package dev.gustavo.orderservice.controller;

import dev.gustavo.orderservice.dto.request.OrderRequestDTO;
import dev.gustavo.orderservice.dto.response.OrderRespondeDTO;
import dev.gustavo.orderservice.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderService service;

    public OrderController(OrderService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<OrderRespondeDTO>> getAllOrders() {
        List<OrderRespondeDTO> response = service.findAllOrders();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderRespondeDTO> getOrderById(@PathVariable Long id) {
        OrderRespondeDTO response = service.findOrderById(id);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping
    public ResponseEntity<OrderRespondeDTO> createOrder(@RequestBody OrderRequestDTO dto) {
        OrderRespondeDTO response = service.createOrder(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
