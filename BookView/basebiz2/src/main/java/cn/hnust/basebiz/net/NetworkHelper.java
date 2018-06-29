package cn.hnust.basebiz.net;

import android.text.TextUtils;

import java.util.Map;

import cn.hnust.basebiz.utils.GsonUtils;
import okhttp3.Request;
import rx.Observable;
import rx.Subscriber;
import rx.schedulers.Schedulers;

/**
 * Created by tjouyang on 2018/4/10.
 *
 * @author tjouyang
 */

public final class NetworkHelper {

    public static <T> Observable<T> createObservable(final String url, final String method, final Map<String, Object> params, final Class<T> clazz) {
        return Observable.create(new Observable.OnSubscribe<T>() {
            @Override
            public void call(Subscriber<? super T> subscriber) {
                Request request = OkHttpClientHelper.getClient().getRequest(url, method, params);
                String json = OkHttpClientHelper.getClient().execute2String(request);
                setData(subscriber, json, clazz);
            }
        }).subscribeOn(Schedulers.io());
    }

    public static <T> Observable<T> createObservable(final String url, final String method, final String params, final Class<T> clazz) {
        return Observable.create(new Observable.OnSubscribe<T>() {
            @Override
            public void call(Subscriber<? super T> subscriber) {
                Request request = OkHttpClientHelper.getClient().getRequest(url, method, params);
                String json = OkHttpClientHelper.getClient().execute2String(request);
                setData(subscriber, json, clazz);
            }
        }).subscribeOn(Schedulers.io());
    }

    private static <T> void setData(Subscriber<? super T> subscriber, String json, Class<T> clazz) {
        if (TextUtils.isEmpty(json)) {
            subscriber.onError(new Throwable("not data"));
            subscriber.onCompleted();
            return;
        }
        try {
            T data = GsonUtils.fromJson(json, clazz);
            subscriber.onNext(data);
            subscriber.onCompleted();
        } catch (Exception e) {
            subscriber.onError(e);
            subscriber.onCompleted();
        }
    }



}
