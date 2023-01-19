package com.example.Chargebee_Apis.Chargebee_Apis.authenticator;

import com.example.Chargebee_Apis.Chargebee_Apis.entity.Customer;
import com.example.Chargebee_Apis.Chargebee_Apis.entity.CustomerList;
import com.example.Chargebee_Apis.Chargebee_Apis.entity.ItemList;
import com.example.Chargebee_Apis.Chargebee_Apis.entity.Subscription;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

@Configuration
public class HeaderAuth {
    public ResponseEntity<String> HeaderAuthorizer(String url){
        String plainCreds = "test_fHO5Q5uFcuyPK93mD6vXcuJBM4psLTCTWv:";
        byte[] plainCredsBytes = plainCreds.getBytes();
        //byte[] base64CredsBytes = Base64.encodeBase64(plainCredsBytes);
        String base64CredsBytes=Base64.encodeBase64String(plainCredsBytes);
        String base64Creds = new String(base64CredsBytes);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Basic " + base64Creds);
        HttpEntity<String> request = new HttpEntity<String>(headers);
        RestTemplate restTemplate=new RestTemplate();
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, request, String.class);
        String a= response.getBody();
        return new ResponseEntity<String>(a, HttpStatus.ACCEPTED);
    }

    //---
    public HttpEntity<String> HeaderAuthorizer1(){
        String plainCreds = "test_5aR6HbgMe6tGUihXcubo9x9OSB0KhxC6s:";
        byte[] plainCredsBytes = plainCreds.getBytes();
        String base64CredsBytes=Base64.encodeBase64String(plainCredsBytes);
        //byte[] base64CredsBytes = Base64.encodeBase64(plainCredsBytes);
        String base64Creds = new String(base64CredsBytes);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Basic " + base64Creds);
        HttpEntity<String> request = new HttpEntity<String>(headers);
        return request;
    }

    //----
    public HttpEntity<Subscription> subscriptionHeaderAuthorizer(){
        String plainCreds = "test_5aR6HbgMe6tGUihXcubo9x9OSB0KhxC6s:";
        byte[] plainCredsBytes = plainCreds.getBytes();
        String base64CredsBytes=Base64.encodeBase64String(plainCredsBytes);
        //byte[] base64CredsBytes = Base64.encodeBase64(plainCredsBytes);
        String base64Creds = new String(base64CredsBytes);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Basic " + base64Creds);
        HttpEntity<Subscription> request = new HttpEntity<>(headers);
        return request;
    }

    //---
    public HttpEntity<CustomerList> customerListHeaderAuthorizer(){
        String plainCreds = "test_5aR6HbgMe6tGUihXcubo9x9OSB0KhxC6s:";
        byte[] plainCredsBytes = plainCreds.getBytes();
        String base64CredsBytes=Base64.encodeBase64String(plainCredsBytes);
        //byte[] base64CredsBytes = Base64.encodeBase64(plainCredsBytes);
        String base64Creds = new String(base64CredsBytes);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Basic " + base64Creds);
        HttpEntity<CustomerList> request = new HttpEntity<>(headers);
        return request;
    }
    //---
    public HttpEntity<Customer> customerHeaderAuthorizer(){
        String plainCreds = "test_5aR6HbgMe6tGUihXcubo9x9OSB0KhxC6s:";
        byte[] plainCredsBytes = plainCreds.getBytes();
        String base64CredsBytes=Base64.encodeBase64String(plainCredsBytes);
        //byte[] base64CredsBytes = Base64.encodeBase64(plainCredsBytes);
        String base64Creds = new String(base64CredsBytes);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Basic " + base64Creds);
        HttpEntity<Customer> request = new HttpEntity<>(headers);
        return request;
    }
    //---
    public HttpEntity<com.example.Chargebee_Apis.Chargebee_Apis.Services.controller.Customer> customerSpecHeaderAuthorizer(){
        String plainCreds = "test_5aR6HbgMe6tGUihXcubo9x9OSB0KhxC6s:";
        byte[] plainCredsBytes = plainCreds.getBytes();
        String base64CredsBytes=Base64.encodeBase64String(plainCredsBytes);
        //byte[] base64CredsBytes = Base64.encodeBase64(plainCredsBytes);
        String base64Creds = new String(base64CredsBytes);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Basic " + base64Creds);
        HttpEntity<com.example.Chargebee_Apis.Chargebee_Apis.Services.controller.Customer> request = new HttpEntity<>(headers);
        return request;
    }
    //---

    public HttpEntity<ItemList> itemsListHeaderAuthorizer(){
        String plainCreds = "test_5aR6HbgMe6tGUihXcubo9x9OSB0KhxC6s:";
        byte[] plainCredsBytes = plainCreds.getBytes();
        String base64CredsBytes=Base64.encodeBase64String(plainCredsBytes);
        //byte[] base64CredsBytes = Base64.encodeBase64(plainCredsBytes);
        String base64Creds = new String(base64CredsBytes);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Basic " + base64Creds);
        HttpEntity<ItemList> request = new HttpEntity<>(headers);
        return request;
    }

}
