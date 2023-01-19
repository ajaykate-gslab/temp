package com.example.Chargebee_Apis.Chargebee_Apis.specController;

import com.example.Chargebee_Apis.Chargebee_Apis.Services.api.FetchAllSubscriptionApi;
import com.example.Chargebee_Apis.Chargebee_Apis.Services.api.InsertSubscriptionApi;
import com.example.Chargebee_Apis.Chargebee_Apis.Services.controller.Customer;
import com.example.Chargebee_Apis.Chargebee_Apis.Services.controller.Items;
import com.example.Chargebee_Apis.Chargebee_Apis.Services.controller.Product;
import com.example.Chargebee_Apis.Chargebee_Apis.Services.controller.Subscription;
import com.example.Chargebee_Apis.Chargebee_Apis.authenticator.HeaderAuth;
import com.example.Chargebee_Apis.Chargebee_Apis.entity.ItemList;
import com.example.Chargebee_Apis.Chargebee_Apis.repository.CustomerRepository;
import com.example.Chargebee_Apis.Chargebee_Apis.repository.PlanRepository;
import com.example.Chargebee_Apis.Chargebee_Apis.repository.ProductRepository;
import com.example.Chargebee_Apis.Chargebee_Apis.repository.SubscriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/apispec/subscription") // issues with this apis
public class SubscriptionSpecController implements FetchAllSubscriptionApi, InsertSubscriptionApi {

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


    //---------------------------- API  TO lIST ALL SUBSCRIPTIONS from localhost ----------------------------------
    @Override
    @GetMapping("/listAll")
    public ResponseEntity<Subscription> fetchAllSubscriptionGet() {
        return new ResponseEntity<Subscription>((Subscription) subscriptionRepository.findAll(), HttpStatus.OK);
    }
//------------------------------------------------------------------------------------------------------------------------------------


    //--------- API TO INSERT SUBSCRIPTION AT LOCALHOST (using product from local and Item & Customer from chargebee) --------
    @Override
    @PostMapping("/insert")
    public ResponseEntity<Subscription> insertSubscriptionPost(Subscription body) {
        Items items = (Items) body.getItems();
        String item_id=items.getId();
        Customer customer= (Customer) body.getCustomer();
        String customer_id=customer.getId();
        Product product = (Product) body.getProduct();
        String product_id= product.getProductId();
        HttpEntity<ItemList> itemHttpEntity=headerAuth.itemsListHeaderAuthorizer();
        RestTemplate ItemRestTemplate=new RestTemplate();
        ResponseEntity<ItemList> ItemResponseEntity=ItemRestTemplate
                .exchange(baseUrl+"/items/"+item_id, HttpMethod.GET,itemHttpEntity,ItemList.class);


        return null;
    }
    /*
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
    * */
}
