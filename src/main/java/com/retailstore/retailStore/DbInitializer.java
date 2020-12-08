package com.retailstore.retailStore;

import com.retailstore.retailStore.dao.CategoryDao;
import com.retailstore.retailStore.dao.DiscountDao;
import com.retailstore.retailStore.dao.ProductDao;
import com.retailstore.retailStore.dao.UserDao;
import com.retailstore.retailStore.model.entity.Category;
import com.retailstore.retailStore.model.entity.Discount;
import com.retailstore.retailStore.model.entity.Product;
import com.retailstore.retailStore.model.entity.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class DbInitializer implements ApplicationRunner {
    private DiscountDao discountDao;
    private UserDao userDao;
    private CategoryDao categoryDao;
    private ProductDao productDao;

    @Value("${spring.jpa.hibernate.ddl-auto}")
    String ddlAuto;

    public DbInitializer(DiscountDao discountDao, UserDao userDao, CategoryDao categoryDao, ProductDao productDao) {
        this.userDao = userDao;
        this.categoryDao = categoryDao;
        this.productDao = productDao;
        this.discountDao = discountDao;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        if (ddlAuto.equals("create") || ddlAuto.equals("create-drop"))
            clear();
            insertTestData();
    }

    private void insertTestData() {
        Discount employeeDiscount = discountDao.save(new Discount(30));
        Discount affiliateDiscount = discountDao.save(new Discount(10));
        Discount loyaltyDiscount = discountDao.save(new Discount(5));

        userDao.save(new User("5906873d-3507-4995-afbe-b731bd9121d1", "Fady", true, false, employeeDiscount));
        userDao.save(new User("17b034e8-d9d7-4b0e-826d-c5fa756110bb", "John", false, true, affiliateDiscount));
        userDao.save(new User("d54f9b8a-4463-4859-8487-3e1aa836f62b", "Maria", false, false, loyaltyDiscount));
        userDao.save(new User("cceb42a0-9a27-47f8-bf4d-6c2362a56e6b", "Remoon", false, false));

        Category groceries = categoryDao.save(new Category("Groceries", "groceries", false));
        Category pharmacies = categoryDao.save(new Category("Pharmacy", "pharmacies ", true));
        Category clothing = categoryDao.save(new Category("Clothing ", "clothing ", true));
        Category jewelries = categoryDao.save(new Category("Jewelry", "jewelries", true));
        Category books = categoryDao.save(new Category("Book", "books", true));
        Category shoe = categoryDao.save(new Category("Shoe", "shoe", true));

        productDao.save(new Product("Product 1", new BigDecimal("50"), groceries));
        productDao.save(new Product("Product 2", new BigDecimal("80"), jewelries));
        productDao.save(new Product("Product 3", new BigDecimal("55"), books));
        productDao.save(new Product("Product 4", new BigDecimal("100"), pharmacies));
        productDao.save(new Product("Product 5", new BigDecimal("100"), clothing));
        productDao.save(new Product("Product 6", new BigDecimal("100"), shoe));
    }

    private void clear() {
        userDao.deleteAll();
        categoryDao.deleteAll();
        categoryDao.deleteAll();
    }
}
