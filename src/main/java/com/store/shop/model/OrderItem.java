package com.store.shop.model;

import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonProperty("item_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private ShoppingOrder order;
    @NotNull
//    @JsonIdentityReference(alwaysAsId = true)
    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    private Product product;
    @Min(value = 1, message = "quantity must be greater than zero")
    private int quantity;

    public OrderItem(ShoppingOrder order, Product product, Integer quantity) {
        this.order = order;
        this.product = product;
        this.quantity = quantity;
    }

    public int getPrice(){
        return product.getPrice();
    }

    @JsonProperty("product_id")
    public Long getProductId(){
        return product.getId();
    }
}