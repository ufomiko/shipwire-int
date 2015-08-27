/**
 * Created by Michael Fomin on 8/26/15.
 */
package com.misha;

/**
 * Represents a state of the inventory: order header and ordered, allocated, and backordered products
 */
public class InventoryState {
    private int[] ordered;
    private int[] allocated;
    private int[] backordered;
    private int header;

    public InventoryState(int header, int[] ordered, int[] allocated, int[] backordered) {
        this.header = header;
        this.ordered = ordered;
        this.allocated = allocated;
        this.backordered = backordered;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append(header).append(": ");
        printArray(sb, ordered);
        sb.append("::");
        printArray(sb, allocated);
        sb.append("::");
        printArray(sb, backordered);
        return sb.toString();
    }

    private void printArray(StringBuilder sb, int[] values) {
        boolean isFirst = true;
        for (Integer i : values) {
            if (isFirst)
                isFirst = false;
            else
                sb.append(",");
            sb.append(i);
        }
    }
}
