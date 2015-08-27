/**
 * Created by Michael Fomin on 8/26/15.
 */
package com.misha;

import java.util.List;

/**
 * Represents one stream, containing multiple orders
 */
public class Stream {
    private List<Orders> Orders;

    public List<Orders> getOrders() {
        return Orders;
    }

    public void setOrders(List<Orders> orders) {
        this.Orders = orders;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Stream{");
        sb.append("orders=").append(Orders);
        sb.append('}');
        return sb.toString();
    }
}
