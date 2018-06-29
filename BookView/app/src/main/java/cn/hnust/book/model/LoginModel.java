package cn.hnust.book.model;


import java.util.Map;

import cn.hnust.basebiz.net.NetworkHelper;
import cn.hnust.basebiz.net.OkHttpClientHelper;
import cn.hnust.book.bean.CommonResultBean;
import cn.hnust.book.constants.Constants;
import rx.Observable;

/**
 * Created by tjouyang on 2018/4/16.
 *
 * @author tjouyang
 */

public class LoginModel {
    public Observable<CommonResultBean> onLogin(Map<String, Object> params) {
        return NetworkHelper.createObservable(Constants.URL_BASE + Constants.LOGIN, OkHttpClientHelper.METHOD_POST, params, CommonResultBean.class);
    }
}
