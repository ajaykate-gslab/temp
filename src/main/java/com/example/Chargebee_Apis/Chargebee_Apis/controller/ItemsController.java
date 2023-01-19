package com.example.Chargebee_Apis.Chargebee_Apis.controller;

import com.example.Chargebee_Apis.Chargebee_Apis.authenticator.HeaderAuth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
public class ItemsController {

    @Autowired
    private HeaderAuth headerAuth;

    @Value("${external.api.url}")
    String baseUrl;

    //------- API TO RETRIVE AN ITEM FRO CHARGEEBEE API ------------
    @GetMapping("/item/retriveItem/{id}")
    public ResponseEntity<String> RetriveItem(@PathVariable(required = false) String id){
        HttpEntity<String > request=headerAuth.HeaderAuthorizer1();
        //HttpHeaders httpHeaders=httpEntity.getHeaders();
        RestTemplate restTemplate=new RestTemplate();
        ResponseEntity<String> responseEntity=restTemplate.exchange(baseUrl+"/items/"+id, HttpMethod.GET,request,String.class);
        System.out.println("************ %%%%     : "+responseEntity);
        return responseEntity;
    }

    //----------------------------------------------------------------

}
