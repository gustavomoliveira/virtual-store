package dev.gustavo.orderservice.dto;

import dev.gustavo.orderservice.dto.request.OrderRequestDTO;
import dev.gustavo.orderservice.dto.response.OrderRespondeDTO;
import dev.gustavo.orderservice.model.Order;

public class OrderMapper {

    public static Order toEntity(OrderRequestDTO dto) {
        return new Order(
                dto.itens()
        );
    }

    public static OrderRespondeDTO toDTO(Order entity) {
        return new OrderRespondeDTO(
                entity.getId(),
                entity.getItens(),
                entity.getTotal(),
                entity.getStatus()
        );
    }
}
