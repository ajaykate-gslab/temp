package com.example.Chargebee_Apis.Chargebee_Apis.specController;

import com.example.Chargebee_Apis.Chargebee_Apis.Services.api.FetchAllCustomersApi;
import com.example.Chargebee_Apis.Chargebee_Apis.Services.api.FetchCustomerByIdApi;
import com.example.Chargebee_Apis.Chargebee_Apis.Services.api.InsertCustomerApi;
import com.example.Chargebee_Apis.Chargebee_Apis.Services.controller.Customer;
import com.example.Chargebee_Apis.Chargebee_Apis.authenticator.HeaderAuth;
import com.example.Chargebee_Apis.Chargebee_Apis.repository.CustomerRepository;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/specApi/customer")    // issues with this apis
public class CustomerSpecController implements FetchCustomerByIdApi , FetchAllCustomersApi, InsertCustomerApi {

    @Autowired
    private HeaderAuth headerAuth;

    @Autowired
    private CustomerRepository customerRepository;

    @Value("${external.api.url}")
    private String baseUrl;

    //  ------ API TO FETCH CUSTOMER BY ID FROM CHARGEBEE --------
    @Override
    @GetMapping("/fetchById")
    public ResponseEntity<Customer> fetchCustomerByIdGet(String id) {
        HttpEntity<Customer> customerHttpEntity=headerAuth.customerSpecHeaderAuthorizer();
        RestTemplate customerRestTemplate=new RestTemplate();
        ResponseEntity customerResponseEntity=customerRestTemplate
                .exchange(baseUrl+"/customers/"+id, HttpMethod.GET,customerHttpEntity,Customer.class);
        Customer customer= (Customer) customerResponseEntity.getBody();
        return new ResponseEntity<>(customer, HttpStatus.OK);
    }
//---------------------------------------------------------------------------------------



    //  ------ API TO FETCH ALL CUSTOMER FROM CHARGEBEE --------
    @Override
    @GetMapping("/listAll")
    public ResponseEntity<Customer> fetchAllCustomersGet() {
        HttpEntity<Customer> customerHttpEntity=headerAuth.customerSpecHeaderAuthorizer();
        RestTemplate customerRestTemplate=new RestTemplate();
        ResponseEntity customerResponseEntity=customerRestTemplate
                .exchange(baseUrl+"/customers", HttpMethod.GET,customerHttpEntity,Customer.class);
        Customer customer= (Customer) customerResponseEntity.getBody();
        return new ResponseEntity<>(customer, HttpStatus.OK);
    }
//---------------------------------------------------------------------------------------


    //  ------ API TO INSERT CUSTOMER INTO CHARGEBEE API--------
    @Override
    @PostMapping("/insert")
    public ResponseEntity<Customer> insertCustomerPost(Customer body) {
        String plainCreds = "test_5aR6HbgMe6tGUihXcubo9x9OSB0KhxC6s:";
        byte[] plainCredsBytes = plainCreds.getBytes();
        String base64CredsBytes= Base64.encodeBase64String(plainCredsBytes);
        //byte[] base64CredsBytes = Base64.encodeBase64(plainCredsBytes);
        String base64Creds = new String(base64CredsBytes);

        HttpEntity<Customer> customerHttpEntity=headerAuth.customerSpecHeaderAuthorizer();
        RestTemplate customerRestTemplate=new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Basic " + base64Creds);
        MultiValueMap<String,String> map=new LinkedMultiValueMap<>();
        map.add("first_name",body.getFirstName());
        map.add("last_name",body.getLastName());
        map.add("email",body.getEmail());
        map.add("locale",body.getLocale());

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(map, headers);
        ResponseEntity<Customer> response = customerRestTemplate.
                postForEntity( baseUrl+"/customers", request ,Customer.class );
        return response;
    }
//---------------------------------------------------------------------------------------








/*
    @Override
    @GetMapping("/listAll")
    public ResponseEntity<Customer> fetchAllCustomersGet() {
        HttpEntity<Customer> customerHttpEntity=headerAuth.customerHeaderAuthorizerForSpec();
        RestTemplate customerRestTemplate=new RestTemplate();
        ResponseEntity responseEntity=customerRestTemplate
                .exchange(baseUrl+"/customers",HttpMethod.GET,customerHttpEntity, Customer.class);
        Customer customer= (Customer) responseEntity.getBody();
        return new ResponseEntity<>( customer,HttpStatus.OK);
    }

    @Override
    @PostMapping("/insert")
    public ResponseEntity<Customer> insertCustomerPost(Customer body) {

        String plainCreds = "test_5aR6HbgMe6tGUihXcubo9x9OSB0KhxC6s:";
        byte[] plainCredsBytes = plainCreds.getBytes();
        String base64CredsBytes= Base64.encodeBase64String(plainCredsBytes);
        //byte[] base64CredsBytes = Base64.encodeBase64(plainCredsBytes);
        String base64Creds = new String(base64CredsBytes);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Basic " + base64Creds);

        MultiValueMap<String,String> map=new LinkedMultiValueMap<>();
        map.add("first_name",body.getFirstName());
        map.add("last_name",body.getLastName());
        map.add("email",body.getEmail());
        map.add("locale",body.getLocale());
       *//* map.add("billing_address[first_name]",body.);
        map.add("billing_address[last_name]",body.billing_address.getLast_name());
        map.add("billing_address[line1]",body.billing_address.getLine1());
        map.add("billing_address[city]",body.billing_address.getCity());
        map.add("billing_address[state]",body.billing_address.getState());
        map.add("billing_address[zip]",body.billing_address.getZip());
        map.add("billing_address[country]",body.billing_address.getCountry());*//*

        RestTemplate customerRestTemplate=new RestTemplate();
        HttpEntity<MultiValueMap<String, String>> request1 = new HttpEntity<MultiValueMap<String, String>>(map, headers);
        ResponseEntity<Customer> response1 = customerRestTemplate.
                postForEntity( baseUrl+"/customers", request1 ,Customer.class );
        return response1;
    }
    */

}
