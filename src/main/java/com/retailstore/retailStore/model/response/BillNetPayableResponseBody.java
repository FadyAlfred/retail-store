package com.retailstore.retailStore.model.response;

import java.math.BigDecimal;
import java.util.Objects;

public class BillNetPayableResponseBody {
    private long amount;

    public BillNetPayableResponseBody() {
    }

    public BillNetPayableResponseBody(long amount) {
        this.amount = amount;
    }

    public long getAmount() {
        return amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BillNetPayableResponseBody that = (BillNetPayableResponseBody) o;
        return amount == that.amount;
    }

    @Override
    public int hashCode() {
        return Objects.hash(amount);
    }
}
