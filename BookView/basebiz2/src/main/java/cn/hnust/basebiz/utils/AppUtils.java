package cn.hnust.basebiz.utils;

import android.content.Context;

/**
 * Created by tjouyang on 2018/4/15.
 *
 * @author tjouyang
 */
public class AppUtils {
    private AppUtils() {

    }

    private static Context context;

    public static void init(Context ctx) {
        context = ctx;
    }

    public static Context getContext() {
        return context;
    }


}
