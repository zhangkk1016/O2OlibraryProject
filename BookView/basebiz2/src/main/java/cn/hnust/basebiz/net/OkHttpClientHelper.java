package cn.hnust.basebiz.net;

/**
 * Created by tjouyang on 2018/4/10.
 *
 * @author tjouyang
 */

import android.net.Uri;

import java.io.IOException;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import cn.hnust.basebiz.utils.GsonUtils;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by tjouyang on 2018/4/10.
 *
 * @author tjouyang
 *         <p>
 *         <p>
 *         <p>
 *         OkHttpClientHelper
 */
public class OkHttpClientHelper {

    public static final String METHOD_GET = "GET";
    public static final String METHOD_POST = "POST";
    public static final String METHOD_PUT = "PUT";
    public static final String METHOD_DELETE = "DELETE";

    private static final OkHttpClientHelper mClient = new OkHttpClientHelper();
    private static final OkHttpClient mOkHttpClient = new OkHttpClient();

    public static OkHttpClientHelper getClient() {
        return mClient;
    }

    /**
     * 同步模式
     *
     * @param request
     * @return String(json)
     */
    public String execute2String(Request request) {

        String result = null;
        try {
            Response response = mOkHttpClient.newCall(request).execute();
            if (response != null && response.isSuccessful()) {
                result = response.body().string();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 通过http请求的基本信息，创建一个Request对象
     */
    public Request getRequest(String url, String method, Map<String, Object> params) {
        if (params == null) {
            params = new TreeMap<>();
        }

        Request.Builder builder = new Request.Builder();

        if (OkHttpClientHelper.METHOD_GET.equalsIgnoreCase(method)) {
            //GET
            builder.url(initGetRequest(url, params)).get();
        } else if (OkHttpClientHelper.METHOD_POST.equalsIgnoreCase(method)) {
            //POST
            builder.url(url).post(initJSONRequestBody(params));
        } else if (OkHttpClientHelper.METHOD_PUT.equalsIgnoreCase(method)) {
            //PUT
            builder.url(url).put(initJSONRequestBody(params));
        } else if (OkHttpClientHelper.METHOD_DELETE.equalsIgnoreCase(method)) {
            //DELETE
            if (params.size() == 0) {
                builder.url(url).delete();
            } else {
                builder.url(url).delete(initJSONRequestBody(params));
            }
        }
        return builder.build();
    }

    public Request getRequest(String url, String method, String params) {
        Request.Builder builder = new Request.Builder();

        if (OkHttpClientHelper.METHOD_GET.equalsIgnoreCase(method)) {
            //GET
            builder.url(url).get();
        } else if (OkHttpClientHelper.METHOD_POST.equalsIgnoreCase(method)) {
            //POST
            builder.url(url).post(initJSONRequestBody(params));
        } else if (OkHttpClientHelper.METHOD_PUT.equalsIgnoreCase(method)) {
            //PUT
            builder.url(url).put(initJSONRequestBody(params));
        } else if (OkHttpClientHelper.METHOD_DELETE.equalsIgnoreCase(method)) {
            //DELETE
            builder.url(url).delete(initJSONRequestBody(params));
        }
        return builder.build();
    }

    /**
     * 初始化Body类型请求参数
     * init Body type params
     */
    private RequestBody initJSONRequestBody(Map<String, Object> params) {
        MediaType json = MediaType.parse("application/json; charset=utf-8");
        return RequestBody.create(json, GsonUtils.buildBody(params));
    }

    /**
     * 初始化Body类型请求参数
     * init Body type params
     */
    private RequestBody initJSONRequestBody(String jsonParams) {
        MediaType json = MediaType.parse("application/json; charset=utf-8");
        return RequestBody.create(json, jsonParams.getBytes());
    }

    /**
     * 初始化Get请求参数
     * init Get type params
     */
    private String initGetRequest(String url, Map<String, Object> params) {
        StringBuilder sb = new StringBuilder(url);
        //has params ?
        if (params.size() > 0) {
            sb.append('?');
            Set<Map.Entry<String, Object>> entries = params.entrySet();
            int count = 0;
            for (Map.Entry entry : entries) {
                count++;
                sb.append(entry.getKey()).append('=').append(Uri.encode(entry.getValue().toString()));
                if (count == params.size()) {
                    break;
                }
                sb.append('&');
            }
            url = new String(sb);
        }
        return url;
    }
}
