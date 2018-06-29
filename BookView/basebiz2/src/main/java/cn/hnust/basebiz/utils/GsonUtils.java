package cn.hnust.basebiz.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Map;

/**
 * Created by tjouyang on 2018/4/10.
 *
 * @author tjouyang
 */
public final class GsonUtils {

    private GsonUtils() {}

    static {
        GsonBuilder gb = new GsonBuilder();
        // 后台传过来的字段可能不规范，注册默认解析器
        gb.registerTypeAdapter(String.class, new StringConverter());
        gb.registerTypeAdapter(Boolean.class, new BooleanConverter());
        gb.registerTypeAdapter(boolean.class, new BooleanConverter());
        gb.registerTypeAdapter(Integer.class, new IntegerConverter());
        gb.registerTypeAdapter(int.class, new IntegerConverter());
        gb.registerTypeAdapter(Double.class, new DoubleConverter());
        gb.registerTypeAdapter(double.class, new DoubleConverter());
        mGson = gb.create();
    }

    private static Gson mGson;

    public static byte[] buildBody(Map params) {
        return mGson.toJson(params).getBytes();
    }

    public  static <T> T fromJson(String json, Class clazz) {
        return (T) mGson.fromJson(json, clazz);
    }

    public  static <T> ArrayList<T> fromJson(String json, Type listType) {
        return mGson.fromJson(json, listType);
    }

    public static String bean2Json(Object obj) {
        return mGson.toJson(obj);
    }
}
