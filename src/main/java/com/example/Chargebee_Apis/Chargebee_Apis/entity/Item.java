package com.example.Chargebee_Apis.Chargebee_Apis.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
public class Item {

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid",strategy = "uuid")
    public String item_id;


    public String id;
    public String name;
    public String external_name;
    public String description;
    public String status;
    public double resource_version;
    public double updated_at;
    public String item_family_id;
    public String type;
    public boolean is_shippable;
    public boolean is_giftable;
    public boolean enabled_for_checkout;
    public boolean enabled_in_portal;
    public String item_applicability;
    public boolean metered;
    public String channel;
    public String object;


}

