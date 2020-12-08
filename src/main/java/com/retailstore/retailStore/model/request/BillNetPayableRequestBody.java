package com.retailstore.retailStore.model.request;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Map;
import java.util.Objects;


public class BillNetPayableRequestBody {
    public static final String INVALID_BILL = "InvalidBill";

    @NotNull(message = INVALID_BILL)
    private List<Map<String, Object>>  items;

    public BillNetPayableRequestBody() {
    }

    public BillNetPayableRequestBody(@NotNull List<Map<String, Object>> items) {
        this.items = items;
    }

    public List<Map<String, Object>> getItems() {
        return items;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BillNetPayableRequestBody that = (BillNetPayableRequestBody) o;
        return Objects.equals(items, that.items);
    }

    @Override
    public int hashCode() {
        return Objects.hash(items);
    }
}
