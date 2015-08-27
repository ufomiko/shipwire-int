/**
 * Created by Michael Fomin on 8/26/15.
 */
package com.misha;

import com.google.gson.Gson;
import spark.ResponseTransformer;

/**
 * JSON serialization methods
 */
public class Utils {

    /**
     * Converts Stream to JSON string
     * @param object
     * @return
     */
    public static String toJson(Object object) {
        return new Gson().toJson(object);
    }

    public static ResponseTransformer json() {
        return Utils::toJson;
    }

    /**
     * Creates Stream from its JSON representation
     * @param json
     * @return
     */
    public static Stream fromJson(String json) {
        return new Gson().fromJson(json, Stream.class);
    }

}