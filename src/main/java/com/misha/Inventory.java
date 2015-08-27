/**
 * Created by Michael Fomin on 8/26/15.
 */

package com.misha;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;

public class Inventory {
    public final static Map<String, Integer> PROD_TO_INEX_MAP = new HashMap<>(5);

    static {
        PROD_TO_INEX_MAP.put("A", 0);
        PROD_TO_INEX_MAP.put("B", 1);
        PROD_TO_INEX_MAP.put("C", 2);
        PROD_TO_INEX_MAP.put("D", 3);
        PROD_TO_INEX_MAP.put("E", 4);
    }

    private static Inventory instance = new Inventory();
    private final int[] products = new int[5];

    private Inventory() {
    }

    public static Inventory getInstance() {
        return instance;
    }

    /**
     * Checks if the inventory is empty
     *
     * @return true if inventory is empty
     */
    public boolean inventoryEmpty() {
        boolean toReturn = true;
        synchronized (products) {
            for (Integer next : products) {
                if (next > 0) {
                    toReturn = false;
                }
            }
        }
        return toReturn;
    }

    /**
     * Attempts to allocate a product.
     *
     * @param productCode string representing the product oode
     * @param quantity    product quantity
     * @return number of backordered products, equal to requested quantity - inventory
     */
    public int allocate(String productCode, int quantity) {
        int backorderedToReturn = 0;
        synchronized (products) {
            Integer index = PROD_TO_INEX_MAP.get(productCode);

            if (null != index) {
                if (products[index] - quantity >= 0) {
                    products[index] -= quantity;
                } else {
                    backorderedToReturn = quantity - products[index];
                }
            }
        }
        return backorderedToReturn;
    }

    /**
     * Load the inventory from the file
     * @param fileName file containing the inventory
     */
    public void loadInventory(String fileName) {
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {

            synchronized (products) {
                String line = br.readLine();
                while (line != null) {
                    if (!"".equals(line)) {
                        String[] split = line.split(" x ");
                        products[PROD_TO_INEX_MAP.get(split[0])] = Integer.parseInt(split[1]);
                    }
                    line = br.readLine();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
    }
}
