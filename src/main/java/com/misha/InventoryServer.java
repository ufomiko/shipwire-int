/**
 * Created by Michael Fomin on 8/26/15.
 */
package com.misha;

import java.util.ArrayList;
import java.util.List;

import static com.misha.Utils.fromJson;
import static spark.Spark.*;

/**
 * Inventory allocator as a lightweight HTTP server
 * accepts POST /order
 * and GET /hello
 */
public class InventoryServer {
    private static List<InventoryState> history = new ArrayList<>();

    public static void main(String[] args) {

        if (args.length != 1) {
            printUsage();
        } else {
            Inventory.getInstance().loadInventory(args[0]);
        }
        port(9090);

        before((request, response) -> {
            // ... check if authenticated
            if (Inventory.getInstance().inventoryEmpty()) {
                halt(410, "Inventory Empty!");
            }
        });
        get("/hello", (req, res) -> "Hello World");

        post("/order", (req, res) -> {
            res.type("application/json");
            final Stream stream = fromJson(req.body());
            if (stream != null) {
                processStream(stream);
                res.status(201);
                return "Orders accepted";
            } else {
                res.status(400);
                return "Invalid request";
            }
        });


    }

    private static void processStream(Stream stream) {
        System.out.println(stream);
        List<Orders> ordersFromStream = stream.getOrders();
        if (ordersFromStream != null) {
            for (Orders o : ordersFromStream) {
                // check the inventory, and halt if empty
                if (Inventory.getInstance().inventoryEmpty()) {
                    printHistory();
                    break;
                }
                processOrder(o);
            }
        } else {
            halt(400, "Badly formed request");
        }
    }

    private static void printHistory() {
        for (InventoryState inventoryState : history) {
            System.out.println(inventoryState);
        }
    }

    private static void processOrder(Orders anOrder) {

        int[] ordered = new int[5];
        int[] allocated = new int[5];
        int[] backordered = new int[5];

        List<Lines> lines = anOrder.getLines();

        for (Lines line : lines) {
            String product = line.getProduct();
            int quantity = line.getQuantity();
            if (quantity < 0) {
                halt(400, "Badly formed request. Illegal quantity: " + quantity);
            }
            Integer index = Inventory.PROD_TO_INEX_MAP.get(product);
            ordered[index] = quantity;
            int bk = Inventory.getInstance().allocate(product, quantity);
            if (bk > 0) {
                backordered[index] = bk;
            } else {
                allocated[index] = quantity;
            }
        }
        InventoryState state = new InventoryState(anOrder.getHeader(), ordered, allocated, backordered);
        history.add(state);

    }

    private static void printUsage() {
        System.out.println("Usage: java -jar inventory-server.jar <inventory_file>");
    }
}
