package cn.hnust.book.utils;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by tjouyang on 2018/4/10.
 * @author tjouyang
 */

public class TestOkHttp {
    public static void testBaidu() {
        try {
            OkHttpClient client = new OkHttpClient();//创建OkHttpClient对象
            Request request = new Request.Builder()
                    .url("http://www.baidu.com")//请求接口。如果需要传参拼接到接口后面。
                    .build();//创建Request 对象
            Response response = null;
            response = client.newCall(request).execute();//得到Response 对象
            if (response.isSuccessful()) {
                response.body();
                //此时的代码执行在子线程，修改UI的操作请使用handler跳转到UI线程。
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
