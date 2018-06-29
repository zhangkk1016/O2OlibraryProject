package cn.hnust.basebiz.utils;

import android.widget.Toast;

/**
 * Created by tjouyang on 2018/4/15.
 *
 * @author tjouyang
 */

public class ToastUtils {
    private ToastUtils() {
    }

    public static void showToast(String msg) {
        // 这里可以考虑自定义toast，这里先使用原生的
        Toast.makeText(AppUtils.getContext(), msg, Toast.LENGTH_SHORT).show();
    }
}
