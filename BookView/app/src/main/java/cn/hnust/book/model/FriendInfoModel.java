package cn.hnust.book.model;

import java.util.Map;

import cn.hnust.basebiz.net.NetworkHelper;
import cn.hnust.basebiz.net.OkHttpClientHelper;
import cn.hnust.book.bean.CommonResultBean;
import cn.hnust.book.bean.FriendInfoBean;
import cn.hnust.book.constants.Constants;
import rx.Observable;

/**
 * Created by tjouyang on 2018/4/29.
 *
 * @author tjouyang
 */
public class FriendInfoModel {
    public Observable<FriendInfoBean> queryFriendInfo (Map<String, Object> param) {
        return NetworkHelper.createObservable(Constants.URL_BASE + Constants.FRIEND_INFORMATION, OkHttpClientHelper.METHOD_POST, param,
                FriendInfoBean.class);
    }

    public Observable<CommonResultBean> deleteFriend(Map<String, Object> param) {
        return NetworkHelper.createObservable(Constants.URL_BASE + Constants.FRIEND_DELETE, OkHttpClientHelper.METHOD_POST, param,
                CommonResultBean.class);
    }
}
