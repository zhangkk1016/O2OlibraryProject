package cn.hnust.book.model;

import java.util.Map;

import cn.hnust.basebiz.net.NetworkHelper;
import cn.hnust.basebiz.net.OkHttpClientHelper;
import cn.hnust.book.bean.CommonResultBean;
import cn.hnust.book.bean.FriendBean;
import cn.hnust.book.constants.Constants;
import rx.Observable;

/**
 * Created by tjouyang on 2018/4/29.
 *
 * @author tjouyang
 */
public class AddNewFriendModel {

    public Observable<FriendBean> searchFriend(Map<String, Object> param) {
        return NetworkHelper.createObservable(Constants.URL_BASE + Constants.FRIEND_SEARCH, OkHttpClientHelper.METHOD_POST,
                param, FriendBean.class);
    }

    public Observable<CommonResultBean> addFriend(Map<String, Object> params) {
        return NetworkHelper.createObservable(Constants.URL_BASE + Constants.FRIEND_ADD, OkHttpClientHelper.METHOD_POST, params, CommonResultBean.class);
    }
}
