package io.mangonet.mgo.bcs.types.gas;

import java.math.BigInteger;
import java.util.List;
import java.util.Objects;

public class GasData {

    private final List<MgoObjectRef> payment;
    private final String owner;
    private final long price;
    private final BigInteger budget;
    
    public GasData(List<MgoObjectRef> payment, String owner, long price, BigInteger budget) {
        this.payment = Objects.requireNonNull(payment);
        this.owner = Objects.requireNonNull(owner);
        this.price = price;
        this.budget = budget;
    }
    
    public List<MgoObjectRef> getPayment() {
        return payment;
    }
    
    public String getOwner() {
        return owner;
    }
    
    public long getPrice() {
        return price;
    }
    
    public BigInteger getBudget() {
        return budget;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        GasData gasData = (GasData) obj;
        return price == gasData.price &&
               budget == gasData.budget &&
               Objects.equals(payment, gasData.payment) &&
               Objects.equals(owner, gasData.owner);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(payment, owner, price, budget);
    }
    
    @Override
    public String toString() {
        return "GasData{" +
               "payment=" + payment +
               ", owner='" + owner + '\'' +
               ", price=" + price +
               ", budget=" + budget +
               '}';
    }
} 