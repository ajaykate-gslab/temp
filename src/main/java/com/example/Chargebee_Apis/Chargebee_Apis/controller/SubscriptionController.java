package com.example.Chargebee_Apis.Chargebee_Apis.controller;

import com.example.Chargebee_Apis.Chargebee_Apis.authenticator.HeaderAuth;
import com.example.Chargebee_Apis.Chargebee_Apis.entity.*;
import com.example.Chargebee_Apis.Chargebee_Apis.repository.CustomerRepository;
import com.example.Chargebee_Apis.Chargebee_Apis.repository.PlanRepository;
import com.example.Chargebee_Apis.Chargebee_Apis.repository.ProductRepository;
import com.example.Chargebee_Apis.Chargebee_Apis.repository.SubscriptionRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@RestController
@RequestMapping("/api/subscription")
public class SubscriptionController {

    @Autowired
    private HeaderAuth headerAuth;
    @Autowired
    private PlanRepository planRepository;
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private SubscriptionRepository subscriptionRepository;

    @Value("${external.api.url}")
    private String baseUrl;

    //------------------------------ API  TO LIST ALL SUBSCRIPTIONS ---------------------------------
    @GetMapping("/listAll")
    public ResponseEntity<String> listSubscriptions(){
        HttpEntity<String> request= headerAuth.HeaderAuthorizer1();
        RestTemplate restTemplate=new RestTemplate();
        ResponseEntity<String> response = restTemplate.exchange(baseUrl+"/subscriptions", HttpMethod.GET, request, String.class);
        return response;
    }


    //------------------------- API  TO CREATE SUBSCRIPTIONS(at localhost) USING ITEM(of chargebee) --------------------------------------

    @PostMapping("/create")
    public ResponseEntity<Subscription> createSubscription(@RequestBody Subscription subscription) throws JsonProcessingException {
        String item_Id = subscription.items.getId();
        String product_id = subscription.product.getProduct_id();
        String customer_id = subscription.customer.getId();

        // Item API authenticated by key
        HttpEntity<ItemList> itemListHttpEntity=headerAuth.itemsListHeaderAuthorizer();

        //Item api consumed
        RestTemplate ItemRestTemplate=new RestTemplate();
        ResponseEntity<ItemList> ItemResponseEntity=ItemRestTemplate
                .exchange(baseUrl+"/items/"+item_Id, HttpMethod.GET,itemListHttpEntity,ItemList.class);
        ItemList itemResponse=ItemResponseEntity.getBody();
        Item item= itemResponse.getItem();
        subscription.setItems(item);

        //consumed customer API to get customer byId
        HttpEntity<CustomerList> customerHttpEntity=headerAuth.customerListHeaderAuthorizer();
        RestTemplate customerRestTemplate=new RestTemplate();
        ResponseEntity<CustomerList> customerResponseEntity = customerRestTemplate
                .exchange(baseUrl+"/customers/"+customer_id,HttpMethod.GET,customerHttpEntity,CustomerList.class);
        CustomerList customerList=customerResponseEntity.getBody();
        Customer customer=customerList.getCustomer();
        subscription.setCustomer(customer);

        //product checked ,present or not
        Optional<Product> optionalProduct= Optional.of(productRepository.getById(product_id));
        if(optionalProduct.isPresent()){
            Product product=optionalProduct.get();
            subscription.setProduct(product);
        }
        else {
            return new ResponseEntity<Subscription>(HttpStatus.NOT_FOUND);
        }
        Subscription result =subscriptionRepository.save(subscription);
        return new ResponseEntity<Subscription>(result,HttpStatus.CREATED);
    }

    //--------------------------- sample testing api ----------------------------------------------------------------------------------------------------------------
    @PostMapping("/createe/{item_id}")
    public ResponseEntity<Subscription> createSubscriptionUsingItem1(@RequestBody Subscription subscription,
                                                                     @PathVariable(required = false) String item_id){
        HttpEntity<ItemList> request=headerAuth.itemsListHeaderAuthorizer();
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<ItemList> responseEntity=restTemplate.exchange(baseUrl+"items/"+item_id,HttpMethod.GET,request,ItemList.class);
        ItemList itemList=responseEntity.getBody();
        Item item=itemList.getItem();

        subscription.setItems(item);
        return new ResponseEntity<>(subscriptionRepository.save(subscription),HttpStatus.CREATED);
    }

    //-----------------------------------------------------------------------------------------------------------------------------------------------


}
