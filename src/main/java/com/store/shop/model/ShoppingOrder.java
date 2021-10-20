package com.store.shop.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShoppingOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonProperty("order_id")
    private Long id;

    @JsonIgnore
    @OneToOne(fetch = FetchType.LAZY)
    private User user;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL,
                mappedBy = "order")
    private List<OrderItem> items;

    @LastModifiedDate
    @JsonProperty("order_date")
    private LocalDateTime orderDate;

    private boolean completed;
}
