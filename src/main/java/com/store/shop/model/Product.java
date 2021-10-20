package com.store.shop.model;

import com.fasterxml.jackson.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "product_id")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonProperty("product_id")
    private Long id;
    private String name;
    private int price;
    private String thumbnail;
    private String short_description;
    private String long_description;

    public Product(String name, Integer price, String thumbnail, String short_description, String long_description) {
        this.name = name;
        this.price = price;
        this.thumbnail = thumbnail;
        this.short_description = short_description;
        this.long_description = long_description;
    }
}