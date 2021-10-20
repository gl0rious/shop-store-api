package com.store.shop.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Formula;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShoppingCart {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonProperty("cart_id")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private User user;

    @JsonProperty("open_order")
    @OneToOne(fetch = FetchType.EAGER)
    private ShoppingOrder openOrder;

    @JsonProperty("total_amount")
    public int getTotalAmount(){
        return openOrder.getItems().stream().map(orderItem -> orderItem.getProduct().getPrice()*
                orderItem.getQuantity()).mapToInt(integer -> (int)integer).sum();
    }
}
