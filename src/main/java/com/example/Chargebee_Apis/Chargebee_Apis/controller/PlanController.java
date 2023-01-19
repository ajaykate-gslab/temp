package com.example.Chargebee_Apis.Chargebee_Apis.controller;

import com.example.Chargebee_Apis.Chargebee_Apis.entity.Plan;
import com.example.Chargebee_Apis.Chargebee_Apis.repository.PlanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/plan")
public class PlanController {

    @Autowired
    private PlanRepository planRepository;

    @PostMapping("/create")
    public ResponseEntity createPlan(@RequestBody Plan plan){
        return new ResponseEntity(planRepository.save(plan), HttpStatus.CREATED);
    }
}
