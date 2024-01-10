package id.co.bni.qris.utils;

import com.google.gson.Gson;

public class RestUtil {
    public static <T> T jsonToObject(String strJson, Class<T> type) {
        Gson gson = new Gson();
        return gson.fromJson(strJson, type);
    }

    public static String toJson(Object object){
        Gson gson = new Gson();
        return gson.toJson(object);
    }
}
