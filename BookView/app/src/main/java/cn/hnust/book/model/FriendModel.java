package cn.hnust.book.model;

import java.util.Map;

import cn.hnust.basebiz.net.NetworkHelper;
import cn.hnust.basebiz.net.OkHttpClientHelper;
import cn.hnust.book.bean.FriendBean;
import cn.hnust.book.constants.Constants;
import rx.Observable;

/**
 * Created by tjouyang on 2018/4/29.
 *
 * @author tjouyang
 */
public class FriendModel {
    public Observable<FriendBean> queryFriendList(Map<String, Object> param){
        return NetworkHelper.createObservable(Constants.URL_BASE + Constants.FRIEND_LIST, OkHttpClientHelper.METHOD_POST, param, FriendBean.class);
    }
}
