package cn.hnust.basebiz;


import android.app.Application;
import android.util.Log;

import cn.hnust.basebiz.utils.AppUtils;

/**
 * Created by tjouyang on 2018/4/10.
 *
 * @author tjouyang
 */

public class BaseApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        AppUtils.init(this);
        Log.d("book", "onApplicationCreate");
    }
}
