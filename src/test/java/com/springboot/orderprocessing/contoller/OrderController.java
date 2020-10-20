package com.springboot.orderprocessing.contoller;


import com.springboot.orderprocessing.dto.OrderRequest;
import com.springboot.orderprocessing.exception.OrderInvalidException;
import com.springboot.orderprocessing.model.Customer;
import com.springboot.orderprocessing.model.Order;
import com.springboot.orderprocessing.model.ShippingMethod;
import com.springboot.orderprocessing.service.OrderService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDateTime;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@WebMvcTest(value = OrderController.class)
@WithMockUser
public class OrderController {


        @Autowired
        private MockMvc mockMvc;

        @MockBean
        private OrderService orderService;

        Order mockOrder = new Order("ffd3dc42", Order.Status.PROCESSED,
                new Customer("002", "abc", "def", "abcdef"),
                new ShippingMethod("124","Standard Shipping", 10.0, LocalDateTime.now(), LocalDateTime.now(), true),
                219.97, 76.98, 296.95, LocalDateTime.now(), LocalDateTime.now(), "227235");

        String exampleOrderJson = "{id: ffd3dc42,status: PROCESSED," +
                "customer: {id: 002,firstName: abc, lastName: def,username: abcdef}," +
                "shipping: {id: 124,name: Standard Shipping,charge: 10.0,createdDate: 2020-10-19T23:54:32.616,modifiedDate: 2020-10-19T23:54:32.616,deliverable: true}," +
                "subTotal: 219.97," +
                "tax: 76.98949999999999," +
                "total: 296.9595," +
                "createdDate: 2020-10-19T23:56:15.616," +
                "modifiedDate: 2020-10-19T23:56:15.616," +
                "confirmNumber: 227235}";

        String exampleOrderRequest = "{\n" +
                "    \"customerId\": \"002\",\n" +
                "    \"itemDetails\": [\n" +
                "        {\n" +
                "            \"itemId\": \"456\",\n" +
                "            \"quantity\": 2\n" +
                "        },\n" +
                "        {\n" +
                "            \"itemId\": \"457\",\n" +
                "            \"quantity\": 1\n" +
                "        }\n" +
                "    ],\n" +
                "    \"shippingDetails\": {\n" +
                "        \"shippingMethodId\": \"124\",\n" +
                "        \"shippingAddressId\": \"789\"\n" +
                "    },\n" +
                "    \"paymentDetails\": [\n" +
                "        {\n" +
                "            \"paymentId\": \"102\",\n" +
                "            \"charge\": 100\n" +
                "        },\n" +
                "        {\n" +
                "            \"paymentId\": \"103\",\n" +
                "            \"charge\": 110\n" +
                "        }\n" +
                "    ]\n" +
                "}";

        @Test
        public void createOrder() throws Exception {

            // studentService.addCourse to respond back with mockCourse
            Mockito.when(
                    orderService.createOrder(
                            Mockito.any(OrderRequest.class))).thenReturn(mockOrder);

            RequestBuilder requestBuilder = MockMvcRequestBuilders
                    .post("/order/create")
                    .accept(MediaType.APPLICATION_JSON);

            MvcResult result = mockMvc.perform(requestBuilder).andReturn();

            MockHttpServletResponse response = result.getResponse();

            assertEquals(HttpStatus.CREATED.value(), response.getStatus());

            assertEquals("http://localhost/api/order/create",
                    response.getHeader(HttpHeaders.LOCATION));

        }

    @Test
    public void getOrderById() throws Exception {

        Mockito.when(
                orderService.getOrderById(Mockito.anyString())).thenReturn(mockOrder);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
                "/api/order/ffd3dc42").accept(
                MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        JSONAssert.assertEquals(exampleOrderJson, result.getResponse()
                .getContentAsString(), false);
    }

}
