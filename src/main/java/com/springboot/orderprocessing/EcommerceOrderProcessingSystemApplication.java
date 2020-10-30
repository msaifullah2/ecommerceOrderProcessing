package com.springboot.orderprocessing;

import com.springboot.orderprocessing.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

@SpringBootApplication
@EnableSwagger2
public class EcommerceOrderProcessingSystemApplication {

    @Autowired
    private ShippingMethodRepository shippingMethodRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private BillingRepository billingRepository;

    @Autowired
    private ShippingAddressRepository shippingAddressRepository;

    @Autowired
    private ItemRepository itemRepository;


    public static void main(String[] args) {
        SpringApplication.run(EcommerceOrderProcessingSystemApplication.class, args);
    }

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .paths(PathSelectors.ant("/api/order/*"))
                .apis(RequestHandlerSelectors.basePackage("com.springboot.orderprocessing"))
                .build().apiInfo(apiInfo());
    }

    private ApiInfo apiInfo(){
        return new ApiInfo(
                "Ecommerce Order Processing API",
                "API Design for Order Processing",
                "1.0",
                "Copyrights Reserved",
                new springfox.documentation.service.Contact("Mohammed Saif", "https://www.abc.com/saifullahmohammed/", "abc@gmail.com"),
                "API License",
                "https://www.abc.com/saifullahmohammed/",
                Collections.emptyList());
    }

//    Customer customer1 = Customer.builder().id("001").firstName("Mohammed").lastName("Saif").username("msaifullah").build();
//    Customer customer2 = Customer.builder().id("002").firstName("abc").lastName("def").username("abcdef").build();
//    Customer customer3 = Customer.builder().id("003").firstName("ghi").lastName("jkl").username("ghijkl").build();
//    Customer customer4 = Customer.builder().id("004").firstName("lmn").lastName("opq").username("lmnopq").build();
//    Customer customer5 = Customer.builder().id("005").firstName("John").lastName("Parker").username("jparker").build();
//    Customer customer6 = Customer.builder().id("006").firstName("Jason").lastName("Smith").username("jsmith").build();
//
//    @Bean
//    CommandLineRunner dataSetup() {
//        return args ->
//        {
//            createCustomer();
//            createShippingMethods();
//            createItems();
//            createShippingAddress();
//            createBilling();
//        };
//    }
//
//    public void createCustomer() {
//        List<Customer> customerList = new ArrayList<>();
//
//        customerList.add(customer1);
//        customerList.add(customer2);
//        customerList.add(customer3);
//        customerList.add(customer4);
//        customerList.add(customer5);
//        customerList.add(customer6);
//        customerRepository.deleteAll();
//        customerRepository.saveAll(customerList);
//    }
//
//    private void createShippingMethods() {
//        List<ShippingMethod> shippingMethodList = new ArrayList<>();
//        ShippingMethod curbsidePickup = ShippingMethod.builder()
//                .id("123").name("Curbside Pickup").charge(0.0).build();
//        ShippingMethod standarShipping = ShippingMethod.builder()
//                .id("124").name("Standard Shipping").isDeliverable(true).charge(10.0).build();
//        ShippingMethod thirdParyDelivery = ShippingMethod.builder()
//                .id("125").name("Third Party Shipping").isDeliverable(true).charge(5.0).build();
//        ShippingMethod expressDelivery = ShippingMethod.builder()
//                .id("126").name("Express Delivery").isDeliverable(true).charge(0.0).build();
//        shippingMethodList.add(curbsidePickup);
//        shippingMethodList.add(standarShipping);
//        shippingMethodList.add(thirdParyDelivery);
//        shippingMethodList.add(expressDelivery);
//        shippingMethodRepository.deleteAll();
//        shippingMethodRepository.saveAll(shippingMethodList);
//    }
//
//    private void createItems() {
//        List<Item> itemList = new ArrayList<>();
//        Item item1 = Item.builder().id("456").name("Echo Dot 3").manufacturer("Amazon").price(29.99).build();
//        Item item2 = Item.builder().id("457").name("Vaccum Cleaner").manufacturer("Shark").price(149.99).build();
//        Item item3 = Item.builder().id("458").name("Smart Bulb").manufacturer("Amazon").price(9.99).build();
//        Item item4 = Item.builder().id("459").name("Echo Dot 2").manufacturer("Amazon").price(19.99).build();
//        itemList.add(item1);
//        itemList.add(item2);
//        itemList.add(item3);
//        itemList.add(item4);
//        itemRepository.deleteAll();
//        itemRepository.saveAll(itemList);
//    }
//
//    private void createShippingAddress() {
//        List<ShippingAddress> shippingAddressList = new ArrayList<>();
//        ShippingAddress shippingAddress1 = ShippingAddress.builder().id("789").addressLine1("1414 Wimpole St").addressLine2("D206").city("Mundelein").State("IL").zipCode(60060).build();
//        ShippingAddress shippingAddress2 = ShippingAddress.builder().id("790").addressLine1("12800 Wimpole St").addressLine2("B101").city("Vernon Hills").State("IL").zipCode(60060).build();
//        ShippingAddress shippingAddress3 = ShippingAddress.builder().id("791").addressLine1("1444 Canterbury Ln").addressLine2("A208").city("Mundelein").State("IL").zipCode(60060).build();
//        ShippingAddress shippingAddress4 = ShippingAddress.builder().id("792").addressLine1("417 Summit Ave").city("Arlington").State("TX").zipCode(76013).build();
//        shippingAddressList.add(shippingAddress1);
//        shippingAddressList.add(shippingAddress2);
//        shippingAddressList.add(shippingAddress3);
//        shippingAddressList.add(shippingAddress4);
//        shippingAddressRepository.deleteAll();
//        shippingAddressRepository.saveAll(shippingAddressList);
//    }
//
//    private void createBilling() {
//        List<Billing> billingList = new ArrayList<>();
//        Billing discover = Billing.builder().id("101").cardNumber("1234567890").addressLine1("1414 Wimpole St").addressLine2("D206").city("Mundelein").state("IL").zip(60060).cardType(Billing.CardType.CREDIT_CARD).cvv("123").customer(customer1).build();
//        Billing masterCard = Billing.builder().id("102").cardNumber("2134567891").addressLine1("12800 Wimpole St").addressLine2("B101").city("Vernon Hills").state("IL").zip(60060).cardType(Billing.CardType.CREDIT_CARD).cvv("001").customer(customer2).build();
//        Billing visaDebit = Billing.builder().id("103").cardNumber("1324567892").addressLine1("1444 Canterbury Ln").addressLine2("A208").city("Mundelein").state("IL").zip(60060).cardType(Billing.CardType.DEBIT_CARD).cvv("101").customer(customer2).build();
//        Billing wellsFargo = Billing.builder().id("104").cardNumber("1243567893").addressLine1("417 Summit Ave").city("Arlington").state("TX").zip(76013).cardType(Billing.CardType.DEBIT_CARD).cvv("984").customer(customer4).build();
//        billingList.add(discover);
//        billingList.add(masterCard);
//        billingList.add(visaDebit);
//        billingList.add(wellsFargo);
//        billingRepository.deleteAll();
//        billingRepository.saveAll(billingList);
//    }

}
