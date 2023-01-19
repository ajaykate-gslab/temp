package com.example.Chargebee_Apis.Chargebee_Apis.controller;

import com.example.Chargebee_Apis.Chargebee_Apis.authenticator.HeaderAuth;
import com.example.Chargebee_Apis.Chargebee_Apis.entity.Customer;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
public class CustomerController {

    @Autowired
    private HeaderAuth headerAuth;

    RestTemplate restTemplate=new RestTemplate();

    @GetMapping("/api/customer/listAll")
    public ResponseEntity<String> listCustomers(){
        String url="https://myecom-test.chargebee.com/api/v2/customers";
        HttpEntity<String> request= headerAuth.HeaderAuthorizer1();
        RestTemplate restTemplate=new RestTemplate();
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, request, String.class);
        String a= response.getBody();
        return response;
    }
    //-------------------------------------------------------------------------------------

    /*@PostMapping("/api/customer/create")*/
    @PostMapping(path = "/api/customer/create", consumes = {MediaType.APPLICATION_JSON_VALUE,
            MediaType.MULTIPART_FORM_DATA_VALUE}, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Customer> createCustomer(@RequestBody Customer customer){
        String url="https://myecom-test.chargebee.com/api/v2/customers";

        String plainCreds = "test_5aR6HbgMe6tGUihXcubo9x9OSB0KhxC6s:";
        byte[] plainCredsBytes = plainCreds.getBytes();
        String base64CredsBytes=Base64.encodeBase64String(plainCredsBytes);
        //byte[] base64CredsBytes = Base64.encodeBase64(plainCredsBytes);
        String base64Creds = new String(base64CredsBytes);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Basic " + base64Creds);

        MultiValueMap<String, String> map= new LinkedMultiValueMap<>();
        map.add("first_name",customer.getFirst_name());
        map.add("last_name",customer.getLast_name());
        map.add("email",customer.getEmail());
        map.add("locale",customer.getLocale());
        map.add("billing_address[first_name]",customer.billing_address.getFirst_name());
        map.add("billing_address[last_name]",customer.billing_address.getLast_name());
        map.add("billing_address[line1]",customer.billing_address.getLine1());
        map.add("billing_address[city]",customer.billing_address.getCity());
        map.add("billing_address[state]",customer.billing_address.getState());
        map.add("billing_address[zip]",customer.billing_address.getZip());
        map.add("billing_address[country]",customer.billing_address.getCountry());

        HttpEntity<MultiValueMap<String, String>> request1 = new HttpEntity<MultiValueMap<String, String>>(map, headers);
        ResponseEntity<Customer> response1 = restTemplate.postForEntity( url, request1 , Customer.class );

       /* HttpEntity<Customer> request = new HttpEntity<>(customer,headers);*/

        RestTemplate restTemplate=new RestTemplate();
        /*ResponseEntity<Customer> response = restTemplate.exchange(url, HttpMethod.POST, request, Customer.class);
        */
      //  ResponseEntity<Customer> response=restTemplate.postForEntity(url,request,Customer.class);
        //response1.getBody();
        return new ResponseEntity(customer,HttpStatus.CREATED);
    }
   /* //@PostMapping("/insert")
    public ResponseEntity<Customer> create(@RequestBody Customer customer){
            return new ResponseEntity<Customer>(customerRepository.save(customer), HttpStatus.CREATED);
    }*/

    //------------------------------

    @PostMapping(path = "/getParameters", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Customer> fetchParameter(@RequestParam MultiValueMap<String,String> paramMap){
        String url="https://myecom-test.chargebee.com/api/v2/customers";
        HttpHeaders httpHeaders=new HttpHeaders();

        System.out.println(paramMap);

        HttpEntity<String> request=headerAuth.HeaderAuthorizer1();

        /*HttpEntity request1 = new HttpEntity<>(request, httpHeaders);
        ResponseEntity<Customer> response = restTemplate.postForEntity( url, request1 , Customer.class );
*/

        HttpEntity<MultiValueMap<String, String>> request1 = new HttpEntity<MultiValueMap<String, String>>(paramMap, httpHeaders);
        ResponseEntity<Customer> response1 = restTemplate.postForEntity( url, request1 , Customer.class );

        return new ResponseEntity(request1,HttpStatus.OK);
    }

    //-----


}
