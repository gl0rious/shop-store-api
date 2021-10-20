package com.store.shop.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Check;

import javax.validation.constraints.Min;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemInfo {
    @JsonProperty("item_id")
    private Long itemId;
    @JsonProperty("product_id")
    private long productId;
    @Min(value = 1, message = "quantity must be greater than zero")
    private int quantity;
}
