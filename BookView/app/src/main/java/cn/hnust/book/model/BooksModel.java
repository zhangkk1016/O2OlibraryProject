package cn.hnust.book.model;

import java.util.Map;

import cn.hnust.basebiz.net.NetworkHelper;
import cn.hnust.basebiz.net.OkHttpClientHelper;
import cn.hnust.book.bean.CommonResultBean;
import cn.hnust.book.bean.FindBookBean;
import cn.hnust.book.bean.MineBookBean;
import cn.hnust.book.constants.Constants;
import rx.Observable;

/**
 * Created by tjouyang on 2018/4/29.
 *
 * @author tjouyang
 */

public class BooksModel {
    public Observable<MineBookBean> queryMyBook(Map<String, Object> params) {
        return NetworkHelper.createObservable(Constants.URL_BASE + Constants.MY_LIST, OkHttpClientHelper.METHOD_POST,
                params, MineBookBean.class);
    }

    public Observable<MineBookBean> queryMyBook(String json) {
        return NetworkHelper.createObservable(Constants.URL_BASE + Constants.MY_LIST, OkHttpClientHelper.METHOD_POST,
                json, MineBookBean.class);
    }

    public Observable<CommonResultBean> publishBook(String json) {
        return NetworkHelper.createObservable(Constants.URL_BASE + Constants.RELEASE_SERVLET, OkHttpClientHelper.METHOD_POST,
                json, CommonResultBean.class);
    }

    public Observable<FindBookBean> findBook(Map<String, Object> params) {
        return NetworkHelper.createObservable(Constants.URL_BASE + Constants.SEARCH_BOOK_SERVLET, OkHttpClientHelper.METHOD_POST,
                params, FindBookBean.class);
    }
}
