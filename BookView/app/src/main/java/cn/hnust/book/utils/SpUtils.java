package cn.hnust.book.utils;

import android.content.Context;
import android.content.SharedPreferences;

import cn.hnust.basebiz.utils.AppUtils;
import cn.hnust.book.constants.Constants;

/**
 * Created by tjouyang on 2018/4/16.
 *
 * @author tjouyang
 */

public class SpUtils {
    private SpUtils() {}

    public static boolean getBoolean(String key) {
        SharedPreferences sharedPreferences = getSp();
        return sharedPreferences.getBoolean(key, false);
    }

    public static void putBoolean(String key, boolean value) {
        SharedPreferences sharedPreferences = getSp();
        sharedPreferences.edit().putBoolean(key, value).apply();
    }

    public static String getString(String key) {
        return getSp().getString(key, "");
    }

    public static void putString(String key, String value) {
        SharedPreferences sharedPreferences = getSp();
        sharedPreferences.edit().putString(key, value).apply();
    }

    public static int getInt(String key) {
        return getSp().getInt(key, 0);
    }

    public static void putInt(String key, int value) {
        SharedPreferences sharedPreferences = getSp();
        sharedPreferences.edit().putInt(key, value).apply();
    }

    private static SharedPreferences getSp() {
        return AppUtils.getContext().getSharedPreferences(Constants.SP_NAME, Context.MODE_PRIVATE);
    }
}
