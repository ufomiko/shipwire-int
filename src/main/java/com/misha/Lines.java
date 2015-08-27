/**
 * Created by Michael Fomin on 8/26/15.
 */
package com.misha;

/**
 * Represents one line in the order
 * (plural in the name - to correspond to the input JSON)
 */
public class Lines {
    private String Product;
    private int Quantity;

    public String getProduct() {
        return Product;
    }

    public void setProduct(String product) {
        Product = product;
    }

    public int getQuantity() {
        return Quantity;
    }

    public void setQuantity(int quantity) {
        Quantity = quantity;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Lines{");
        sb.append("Product='").append(Product).append('\'');
        sb.append(", Quantity='").append(Quantity).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
