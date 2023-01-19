package com.example.Chargebee_Apis.Chargebee_Apis.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity(name = "subscription")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Subscription {

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid",strategy = "uuid")
    private String id; //: "__test__8asukSOXduqmOY",

    @CreationTimestamp
    @Temporal(TemporalType.DATE)
    private Date activated_at;

    @JsonIgnore
    private int billing_period; //: 1,

    @JsonIgnore
    private String billing_period_unit; //: "month",

    @CreationTimestamp
    @Temporal(TemporalType.DATE)
    private Date created_at; //: 1612890916,
    private String currency_code; //: "USD",
    //private String customer_id; //: "__test__8asukSOXdulGOV",
    private boolean deleted; //: false,
    private String object; //: "subscription",
    private String status; //: "active"
    private Long total_dues; //:1100,
    private Long updated_at; //: 1612890917
    private String product_code;

    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "subscription_items_id")
    private Subscription_items subscription_items;

    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "item_id")
    public Item items;

    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "product_id")
    public Product product;

    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "customer_id")
    public Customer customer;



}
