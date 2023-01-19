package com.example.Chargebee_Apis.Chargebee_Apis.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "product")
public class Product {

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid",strategy = "uuid")
    private String product_id; //: "__test__8asukSOXduqmOY",

    private String product_name;

    @CreationTimestamp
    @Temporal(TemporalType.DATE)
    private Date created_at; //: 1612890916,

    private String product_code; //: "ab123",
    private boolean deleted; //: false,
    private Long product_price; //: 1612890916,

    private String object; //: "product",
    private String status; //: "active"

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
        private Date updated_at; //: 1612890917


}
