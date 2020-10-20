package com.springboot.orderprocessing.service.impl;

import com.springboot.orderprocessing.dto.ItemDetails;
import com.springboot.orderprocessing.dto.OrderRequest;
import com.springboot.orderprocessing.dto.OrderStatusUpdate;
import com.springboot.orderprocessing.dto.PaymentDetails;
import com.springboot.orderprocessing.exception.OrderInvalidException;
import com.springboot.orderprocessing.model.*;
import com.springboot.orderprocessing.repository.OrderRepository;
import com.springboot.orderprocessing.service.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final CustomerService customerService;
    private final ItemService itemService;
    private final ShippingMethodService shippingMethodService;
    private final ShippingAddressService shippingAddressService;
    private final BillingService billingService;
    private final OrderItemService orderItemService;
    private static final double TAX_PERCENTAGE = 0.35;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository, CustomerService customerService, ItemService itemService, ShippingMethodService shippingMethodService, ShippingAddressService shippingAddressService, BillingService billingService, OrderItemService orderItemService) {
        this.orderRepository = orderRepository;
        this.customerService = customerService;
        this.itemService = itemService;
        this.shippingMethodService = shippingMethodService;
        this.shippingAddressService = shippingAddressService;
        this.billingService = billingService;
        this.orderItemService = orderItemService;
    }


    @Override
    public Order createOrder(OrderRequest orderRequest) throws OrderInvalidException {
        Optional<Customer> customer = this.customerService.findByCustomerId(orderRequest.getCustomerId());
        if (!customer.isPresent()) {
            throw new OrderInvalidException("Customer Not Found for customer id: " + orderRequest.getCustomerId());
        }
        List<String> listOfIds = orderRequest.getItemDetails()
                .stream()
                .map(ItemDetails::getItemId)
                .collect(Collectors.toList());
        List<Item> items = itemService.findAllByIds(listOfIds);
        if (items.isEmpty() || items.size() != listOfIds.size()) {
            throw new OrderInvalidException("Items Not Found for ids: " + listOfIds);
        }
        Optional<ShippingMethod> shippingMethod = shippingMethodService.getShippingMethodById(orderRequest.getShippingDetails().getShippingMethodId());
        if (!shippingMethod.isPresent()) {
            throw new OrderInvalidException("Shipping Method Not Found for shipping method id: " + orderRequest.getShippingDetails().getShippingMethodId());
        }

        if(shippingMethod.get().isDeliverable()){
            //shipping address by shippingaddressId
            Optional<ShippingAddress> shippingAddress = shippingAddressService.getShippingAddressById(orderRequest.getShippingDetails().getShippingAddressId());
            if (!shippingAddress.isPresent()) {
                throw new OrderInvalidException("Shipping Address Not Found for shipping address id: " + orderRequest.getShippingDetails().getShippingAddressId());
            }
        }
        //get all payments by paymentIds in order request
        List<String> paymentIds = orderRequest.getPaymentDetails()
                .stream()
                .map(PaymentDetails::getPaymentId)
                .collect(Collectors.toList());

        //call payment service and pass payment id's to get list of payments
        List<Billing> payments = billingService.findAllByIds(paymentIds);
        if (payments.isEmpty() || payments.size() != paymentIds.size()) {
            throw new OrderInvalidException("Payment Info Not Found for the some/all of payment ids: " + paymentIds);
        }
        //once all the data on order request is verified, calculate subTotal, tax and shipping and set all the properties on
        //the new order object
        Map<String, List<Item>> itemPricingMap = items.stream().collect(Collectors.groupingBy(Item::getId));
        double subTotal = getSubTotal(itemPricingMap, orderRequest.getItemDetails()) + shippingMethod.get().getCharge();
        Order orderObject = new Order();
        orderObject.setId(UUID.randomUUID().toString());
        orderObject.setCustomer(customer.get());
        orderObject.setSubTotal(subTotal);
        orderObject.setTax(subTotal * TAX_PERCENTAGE);
        orderObject.setCreatedDate(LocalDateTime.now());
        orderObject.setModifiedDate(LocalDateTime.now());
        orderObject.setShipping(shippingMethod.get());
        orderObject.setStatus(Order.Status.PROCESSING);
        orderObject.setTotal(subTotal + orderObject.getTax());

        //call order service and save
        orderRepository.save(orderObject);
        //now create OrderItemObject for every item in the itemdetails in the order request and set the quantity taken from order request
        List<OrderItem> orderItemList = orderRequest.getItemDetails().stream().map(itemDetail -> {
            OrderItem orderItem = new OrderItem();
            OrderItemPK orderItemPK = new OrderItemPK();
            orderItemPK.setOrderId(orderObject.getId());
            orderItemPK.setItemId(itemDetail.getItemId());
            orderItem.setId(orderItemPK);
            orderItem.setItem(itemPricingMap.get(itemDetail.getItemId()).get(0));
            orderItem.setQuantity(itemDetail.getQuantity());
            orderItem.setOrder(orderObject);
            return orderItem;
        }).collect(Collectors.toList());
        orderItemService.saveAll(orderItemList);
        orderObject.setConfirmNumber(createConfirmationNumber());
        orderObject.setStatus(Order.Status.PROCESSED);
        orderRepository.save(orderObject);
        return orderObject;
    }

    @Override
    public List<Order> createOrder(List<OrderRequest> orderRequests) throws OrderInvalidException {
        List<Order> orderList = new ArrayList<>();
        orderRequests.forEach(orderRequest -> {
            try {
                Order order = createOrder(orderRequest);
                orderList.add(order);
            } catch (OrderInvalidException e) {
                log.error("Failed to process order request: {}", orderRequest);
            }
        });
        return orderList;
    }

    @Override
    public Order getOrderById(String orderId) throws OrderInvalidException {
        return orderRepository.findById(orderId).orElseThrow(() -> new OrderInvalidException("Order Not Found for this Id::" + orderId));
    }

    @Override
    public Order cancelOrder(String id) throws OrderInvalidException {
        Order order = orderRepository.findById(id).orElseThrow(() -> new OrderInvalidException("Order Not Found for this id" + id));
        order.setStatus(Order.Status.CANCELLED);
        order.setModifiedDate(LocalDateTime.now());
        order.setConfirmNumber(createConfirmationNumber());
        this.orderRepository.save(order);
        return order;
    }

    @Override
    public List<Order> updateStatus(List<OrderStatusUpdate> orderStatusUpdates) {
        List<Order> orderList = new ArrayList<>();
        orderStatusUpdates.forEach(orderStatusUpdate -> {
            Optional<Order> order = orderRepository.findById(orderStatusUpdate.getOrderId());
            if (!order.isPresent()){
                log.error("The requested order does not exist for id: {}", orderStatusUpdate.getOrderId());
            }else {
                order.get().setStatus(orderStatusUpdate.getStatus());
                orderList.add(order.get());
            }
        });
        orderRepository.saveAll(orderList);
        return orderList;
    }

    private Double getSubTotal(Map<String,List<Item>> itemPricingMap, List<ItemDetails> itemDetails){
        return itemDetails
                .stream()
                .map(itemDetail -> itemPricingMap.get(itemDetail.getItemId()).get(0).getPrice() * itemDetail.getQuantity())
                .mapToDouble(Double::doubleValue)
                .sum();
    }

    private String createConfirmationNumber(){
        Random rnd = new Random();
        int number = rnd.nextInt(999999);
        // this will convert any number sequence into 6 character.
        return String.format("%06d", number);
    }

}
