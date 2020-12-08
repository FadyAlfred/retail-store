package com.retailstore.retailStore.service;

import com.retailstore.retailStore.Config;
import com.retailstore.retailStore.dao.ProductDao;
import com.retailstore.retailStore.dao.UserDao;
import com.retailstore.retailStore.exception.CriticalException;
import com.retailstore.retailStore.model.entity.Discount;
import com.retailstore.retailStore.model.entity.Product;
import com.retailstore.retailStore.model.entity.User;
import com.retailstore.retailStore.model.response.BillNetPayableResponseBody;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

@Service
public class BillService {
    UserDao userDao;
    ProductDao productDao;

    public BillService(UserDao userDao, ProductDao productDao) {
        this.userDao = userDao;
        this.productDao = productDao;
    }

    public BillNetPayableResponseBody calculateNetPayableAmount(List<Map<String, Object>> items, String userId){
        User user = getUser(userId);
        int discountPercent;
        Discount discount = user.getDiscount();
        if (discount != null) discountPercent = discount.getValue();
        else discountPercent = 0;
        List<UUID> productsIds = getProductIds(items);
        List<Product> products = productDao.findAllById(productsIds);
        Map<String, Object> productMap = getProductMap(items);
        long payableAmount = getNetPayableAmount(products, productMap, discountPercent);
        return new BillNetPayableResponseBody(payableAmount);
    }

    private User getUser(String id){
        Optional<User> userOptional = userDao.findById(UUID.fromString(id));
        if (userOptional.isEmpty()) throw new CriticalException("User Not Found");
        return userOptional.get();
    }

    private List<UUID> getProductIds(List<Map<String, Object>> items){
        List<UUID> productsIds = new ArrayList<>();
        for (Map<String, Object> item: items){
            productsIds.add((UUID.fromString((String) item.get("productId"))));
        }
        return productsIds;
    }

    private Map<String, Object> getProductMap(List<Map<String, Object>> items){
        Map<String, Object> productMap = new HashMap<>();
        for (Map<String, Object> item: items){
            productMap.put((String) item.get("productId"), (int) item.get("quantity"));
        }
        return productMap;
    }

    private long getNetPayableAmount(List<Product> products, Map<String, Object> productMap,
                                           int discountPercent){
        BigDecimal payableBill = new BigDecimal(0);
        float pricePercent = (float) (100-discountPercent)/100;


        for (Product product: products){
            int quantity = (int) productMap.get(product.getId().toString());
            double priceAfterDiscount;
            if (product.getCategory().isDiscountAllowed()){
                priceAfterDiscount = quantity*product.getPrice().doubleValue()*pricePercent;
            }
            else priceAfterDiscount = quantity*product.getPrice().doubleValue();
            payableBill = payableBill.add(BigDecimal.valueOf(priceAfterDiscount));
        }
        int hundredDollarDiscount = Config.HUNDRED_DOLLAR_DISCOUNT;

        double totalBillDiscount = Math.floor(payableBill.divide(new BigDecimal(100)).doubleValue())*hundredDollarDiscount;
        BigDecimal finalBill = payableBill.subtract(BigDecimal.valueOf(totalBillDiscount));
        return finalBill.longValue();
    }
}
