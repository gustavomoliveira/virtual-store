package dev.gustavo.orderservice.model;

import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ItemOrder {

    private Long productId;
    private int amount;

    public ItemOrder(Long productId, int amount) {
        this.productId = productId;
        this.amount = amount;
    }
}
