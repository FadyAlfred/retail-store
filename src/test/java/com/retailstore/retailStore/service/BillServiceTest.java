package com.retailstore.retailStore.service;

import com.retailstore.retailStore.dao.ProductDao;
import com.retailstore.retailStore.dao.UserDao;
import com.retailstore.retailStore.model.entity.Category;
import com.retailstore.retailStore.model.entity.Discount;
import com.retailstore.retailStore.model.entity.Product;
import com.retailstore.retailStore.model.entity.User;
import com.retailstore.retailStore.model.response.BillNetPayableResponseBody;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@RunWith(SpringJUnit4ClassRunner.class)
public class BillServiceTest {
    BillService billService;

    @Mock
    private UserDao userDao;

    @Mock
    private ProductDao productDao;

    Product product1;
    Product product2;
    Product product3;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        billService = new BillService(userDao, productDao);
        Category groceries = new Category("Groceries", "groceries", false);
        Category pharmacies = new Category("Pharmacy", "pharmacies ", true);
        Category clothing = new Category("Clothing ", "clothing ", true);
        this.product1 = new Product(UUID.randomUUID(),"Product 1", new BigDecimal("50"), groceries);
        this.product2 = new Product(UUID.randomUUID(),"Product 2", new BigDecimal("100"), pharmacies);
        this.product3 = new Product(UUID.randomUUID(),"Product 3", new BigDecimal("80"), clothing);
    }

    @Test
    public void getPayAmountForEmployee() throws Exception{
        Discount discount = new Discount(30);
        User user = new User(UUID.randomUUID().toString(), "Fady", true, false, discount);
        List<Product> products =  new ArrayList<>();
        products.add(product1);
        products.add(product2);
        when(userDao.findById(UUID.fromString(user.getId()))).thenReturn(Optional.of(user));
        when(productDao.findAllById(Arrays.asList(product1.getId(), product2.getId()))).thenReturn(products);

        BillNetPayableResponseBody billNetPayableResponseBody = billService.calculateNetPayableAmount(Arrays.asList(
                Map.of("productId", product1.getId().toString(), "quantity", 1),
                Map.of("productId", product2.getId().toString(), "quantity", 1)
        ), user.getId());

        Assert.assertEquals(billNetPayableResponseBody.getAmount(), 115L);
    }

    @Test
    public void getPayAmountForAffiliate() throws Exception{
        Discount discount = new Discount(10);
        User user = new User(UUID.randomUUID().toString(), "Fady", false, true, discount);
        List<Product> products =  new ArrayList<>();
        products.add(product1);
        products.add(product2);
        products.add(product3);
        when(userDao.findById(UUID.fromString(user.getId()))).thenReturn(Optional.of(user));
        when(productDao.findAllById(Arrays.asList(product1.getId(), product2.getId(), product3.getId()))).thenReturn(products);

        BillNetPayableResponseBody billNetPayableResponseBody = billService.calculateNetPayableAmount(Arrays.asList(
                Map.of("productId", product1.getId().toString(), "quantity", 1),
                Map.of("productId", product2.getId().toString(), "quantity", 1),
                Map.of("productId", product3.getId().toString(), "quantity", 1)
        ), user.getId());

        Assert.assertEquals(billNetPayableResponseBody.getAmount(), 202L);
    }

    @Test
    public void getPayAmountForLoyalty() throws Exception{
        Discount discount = new Discount(5);
        User user = new User(UUID.randomUUID().toString(), "Fady", false, false, discount);
        List<Product> products =  new ArrayList<>();
        products.add(product1);
        products.add(product3);
        when(userDao.findById(UUID.fromString(user.getId()))).thenReturn(Optional.of(user));
        when(productDao.findAllById(Arrays.asList(product1.getId(), product3.getId()))).thenReturn(products);

        BillNetPayableResponseBody billNetPayableResponseBody = billService.calculateNetPayableAmount(Arrays.asList(
                Map.of("productId", product1.getId().toString(), "quantity", 1),
                Map.of("productId", product3.getId().toString(), "quantity", 2)
        ), user.getId());

        Assert.assertEquals(billNetPayableResponseBody.getAmount(), 192L);
    }

    @Test
    public void getPayAmountForNormal() throws Exception{
        User user = new User(UUID.randomUUID().toString(), "Fady", false, false);
        List<Product> products =  new ArrayList<>();
        products.add(product1);
        products.add(product2);
        products.add(product3);
        when(userDao.findById(UUID.fromString(user.getId()))).thenReturn(Optional.of(user));
        when(productDao.findAllById(Arrays.asList(product1.getId(), product2.getId(), product3.getId()))).thenReturn(products);

        BillNetPayableResponseBody billNetPayableResponseBody = billService.calculateNetPayableAmount(Arrays.asList(
                Map.of("productId", product1.getId().toString(), "quantity", 3),
                Map.of("productId", product2.getId().toString(), "quantity", 1),
                Map.of("productId", product3.getId().toString(), "quantity", 2)
        ), user.getId());

        Assert.assertEquals(billNetPayableResponseBody.getAmount(), 390L);
    }
}