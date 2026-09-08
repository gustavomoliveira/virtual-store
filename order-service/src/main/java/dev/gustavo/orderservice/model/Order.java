package dev.gustavo.orderservice.model;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ElementCollection
    @CollectionTable(name = "order_items", joinColumns = @JoinColumn(name = "order_id"))
    private List<ItemOrder> itens;

    @Column(nullable = false)
    private BigDecimal total;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    public Order(List<ItemOrder> itens) {
        this.itens = itens;
        this.status = OrderStatus.CREATED;
        this.total = BigDecimal.ZERO;
    }

    public void calculateTotal(Map<Long, BigDecimal> prices) {
        BigDecimal sum = BigDecimal.ZERO;

        for (ItemOrder item : itens) {
            BigDecimal price = prices.get(item.getProductId());
            BigDecimal itemTotal = price.multiply(BigDecimal.valueOf(item.getAmount()));
            sum = sum.add(itemTotal);
        }

        this.total = sum;
    }
}
