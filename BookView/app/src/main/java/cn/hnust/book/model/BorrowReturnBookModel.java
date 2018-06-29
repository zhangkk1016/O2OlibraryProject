package cn.hnust.book.model;

import java.util.Map;

import cn.hnust.basebiz.net.NetworkHelper;
import cn.hnust.basebiz.net.OkHttpClientHelper;
import cn.hnust.book.bean.CommonResultBean;
import cn.hnust.book.bean.MineBookBean;
import cn.hnust.book.constants.Constants;
import rx.Observable;

/**
 * Created by tjouyang on 2018/4/29.
 *
 * @author tjouyang
 */
public class BorrowReturnBookModel {

    public Observable<CommonResultBean> borrowBook(Map<String, Object> params) {
        return NetworkHelper.createObservable(Constants.URL_BASE + Constants.BOOK_BORROW, OkHttpClientHelper.METHOD_POST, params, CommonResultBean.class);
    }

    public Observable<CommonResultBean> returnBook(Map<String, Object> params) {
        return NetworkHelper.createObservable(Constants.URL_BASE + Constants.BOOK_RETURN, OkHttpClientHelper.METHOD_POST, params, CommonResultBean.class);
    }

    public Observable<MineBookBean> queryBook(Map<String, Object> params) {
        return NetworkHelper.createObservable(Constants.URL_BASE + Constants.BOOK_QUERY, OkHttpClientHelper.METHOD_POST, params, MineBookBean.class);
    }
}
