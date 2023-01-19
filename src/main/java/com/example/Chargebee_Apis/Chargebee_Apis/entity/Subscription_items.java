package com.example.Chargebee_Apis.Chargebee_Apis.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "subscription_items")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Subscription_items {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    private Long amount; //: 1000,
    private int billing_cycles; //: 1,
    private int free_quantity; //: 0,
    private String item_price_id; //: "basic-USD",
    private String item_type; //: "plan",
    private String object; //: "subscription_item",
    private int quantity; //: 1,
    private Long unit_price; //: 1000*/
}
