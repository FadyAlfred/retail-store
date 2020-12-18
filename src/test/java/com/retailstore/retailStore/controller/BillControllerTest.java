package com.retailstore.retailStore.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.retailstore.retailStore.interceptor.AuthenticationInterceptor;
import com.retailstore.retailStore.model.entity.Category;
import com.retailstore.retailStore.model.entity.Discount;
import com.retailstore.retailStore.model.entity.Product;
import com.retailstore.retailStore.model.entity.User;
import com.retailstore.retailStore.model.request.BillNetPayableRequestBody;
import com.retailstore.retailStore.model.response.BillNetPayableResponseBody;

import com.retailstore.retailStore.service.BillService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
public class BillControllerTest {

    private BillController billController;

    @Mock
    private BillService billService;

    private MockMvc mockMvc;
    private ObjectMapper objectMapper;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        billController = new BillController(billService);
        objectMapper = new ObjectMapper();
        mockMvc = MockMvcBuilders.standaloneSetup(billController).addInterceptors(new AuthenticationInterceptor()).build();
    }

    @Test
    public void getBillPayableAmountExpectUnauthorized() throws Exception {
        mockMvc.perform(get("/bill/payable")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isUnauthorized());
    }

    @Test
    public void getBillPayableAmountExpectBadRequest() throws Exception {
        mockMvc.perform(get("/bill/payable")
                .header("Authorization" , "5906873d-3507-4995-afbe-b731bd9121d5")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void getBillPayableAmountExpectOk() throws Exception {
        Discount employeeDiscount = new Discount(30);
        User user = new User("5906873d-3507-4995-afbe-b731bd9121d5", "Fady", true, false, employeeDiscount);
        Category category = new Category("Electronics", "electronics", true);
        Product product = new Product("Product 1", new BigDecimal(100), category);
        List<Map<String, Object>> listOfProductsBody = new ArrayList<Map<String, Object>>();
        listOfProductsBody.add(Map.of(
                "productId", "698047e1-3f95-49bc-bdfa-1611276c12ab",
                "quantity", 2
        ));
        when(billService.calculateNetPayableAmount(listOfProductsBody, user.getId())).thenReturn(new BillNetPayableResponseBody(65L));

        mockMvc.perform(get("/bill/payable")
                .header("Authorization" , "5906873d-3507-4995-afbe-b731bd9121d5")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(new BillNetPayableRequestBody(listOfProductsBody)))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }


}
