package com.example.Chargebee_Apis.Chargebee_Apis.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "plan")
public class Plan {

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid",strategy = "uuid")
    private String id; //: "sub_free",

    private String charge_model; //: "per_unit",
    private boolean enabled_in_hosted_pages; //: true,
    private boolean enabled_in_portal; //: true,
    private int free_quantity; //: 0,
    private String name; //: "sub_Free",
    private String object; //: "plan",
    private int period; //: 1,
    private String period_unit; //: "month",
    private double price; //: 0,
    private String status ;//: "active",
    private boolean taxable ;//: true
}
