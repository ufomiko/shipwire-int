/**
 * Created by Michael Fomin on 8/26/15.
 */
package com.misha;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

/**
 * Data Source as a sample HTTP client
 */
public class DataSource {
    public static void main(String[] args) {
        String requestBody = "{\"Orders\": [{\"Header\": 1,\"Lines\": [{\"Product\": \"A\",\"Quantity\": \"1\"},{\"Product\": \"C\",\"Quantity\": \"1\"}]},{\"Header\": 2,\"Lines\": [{\"Product\": \"E\",\"Quantity\": \"5\"}]},   {\"Header\": 3,\"Lines\": [{\"Product\": \"D\",\"Quantity\": \"4\"}]},   {\"Header\": 4,\"Lines\": [{\"Product\": \"A\",\"Quantity\": \"1\"},{\"Product\": \"C\",\"Quantity\": \"1\"}]},   {\"Header\": 5,\"Lines\": [{\"Product\": \"B\",\"Quantity\": \"3\"}]},   {\"Header\": 6,\"Lines\": [{\"Product\": \"D\",\"Quantity\": \"4\"}]}]}";

        System.out.println("Sending the following request to the inventory: ");
        System.out.println(requestBody);
        try {
            HttpResponse<String> x = Unirest.post("http://localhost:9090/order")
                    .body(requestBody)
                    .asString();
            System.out.println("Response: " + x.getBody());
        } catch (UnirestException e) {
            e.printStackTrace();
        }
    }
}
