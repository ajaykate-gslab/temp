package com.example.Chargebee_Apis.Chargebee_Apis.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "billing_address")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Billing_address {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;
    private String first_name;
    private String last_name;
    private String line1;
    private String city;
    private String state;
    private String country;
    private String zip;
    private String object; //"billing_address"


}
