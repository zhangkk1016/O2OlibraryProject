package cn.hnust.book.model;

import java.util.Map;

import cn.hnust.basebiz.net.NetworkHelper;
import cn.hnust.basebiz.net.OkHttpClientHelper;
import cn.hnust.book.bean.FriendBookAllBean;
import cn.hnust.book.bean.FriendBookReleaseBean;
import cn.hnust.book.constants.Constants;
import rx.Observable;

/**
 * Created by tjouyang on 2018/4/29.
 *
 * @author tjouyang
 */
public class FriendBookModel {

    public Observable<FriendBookAllBean> queryAll(Map<String, Object> param) {
        return NetworkHelper.createObservable(Constants.URL_BASE + Constants.FRIEND_BOOK_LIST, OkHttpClientHelper.METHOD_POST,
                param, FriendBookAllBean.class);
    }

    public Observable<FriendBookReleaseBean> queryRelease(Map<String, Object> param) {
        return NetworkHelper.createObservable(Constants.URL_BASE + Constants.FRIEND_BOOK_RELEASE_LIST, OkHttpClientHelper.METHOD_POST,
                param, FriendBookReleaseBean.class);
    }
}
