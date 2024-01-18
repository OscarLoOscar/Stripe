package com.example.shoppingcart.Redis.infra;

import com.nimbusds.jose.shaded.gson.Gson;
import com.nimbusds.jose.shaded.gson.GsonBuilder;

public class JsonUtil {
  private static Gson gson;

  static {
    GsonBuilder gsonBuilder = new GsonBuilder();
    gsonBuilder.setPrettyPrinting();
    gson = gsonBuilder.create();
  }

  private JsonUtil() {}

  public static String toJson(Object object) {
    return gson.toJson(object);
  }

  public static <T> T fromJson(String json, Class<T> classOfT) {
    return gson.fromJson(json, classOfT);
  }

}
