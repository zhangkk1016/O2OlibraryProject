package cn.hnust.book.model;

import java.util.HashMap;
import java.util.Map;

import cn.hnust.basebiz.net.NetworkHelper;
import cn.hnust.basebiz.net.OkHttpClientHelper;
import cn.hnust.book.bean.CommonResultBean;
import cn.hnust.book.bean.MessageBean;
import cn.hnust.book.bean.OwnerConfirmResultBean;
import cn.hnust.book.constants.Constants;
import rx.Observable;

/**
 * Created by tjouyang on 2018/4/29.
 *
 * @author tjouyang
 */
public class BorrowModel {

    public Observable<CommonResultBean> sendMessage(Map<String, Object> params) {
        return NetworkHelper.createObservable(Constants.URL_BASE + Constants.SEND_MESSAGE, OkHttpClientHelper.METHOD_POST, params, CommonResultBean.class);
    }

    public Observable<OwnerConfirmResultBean> ownerConfirm(Map<String, Object> params) {
        return NetworkHelper.createObservable(Constants.URL_BASE + Constants.OWNER_CONFIRM, OkHttpClientHelper.METHOD_POST, params, OwnerConfirmResultBean.class);
    }

    public Observable<CommonResultBean> borrowFinish(Map<String, Object> params) {
        return NetworkHelper.createObservable(Constants.URL_BASE + Constants.BORROWER_FINISH, OkHttpClientHelper.METHOD_POST, params, CommonResultBean.class);
    }

    public Observable<OwnerConfirmResultBean> ownerFinish(Map<String, Object> params) {
        return NetworkHelper.createObservable(Constants.URL_BASE + Constants.OWNER_FINISH, OkHttpClientHelper.METHOD_POST, params, OwnerConfirmResultBean.class);
    }

    public Observable<MessageBean> lookMessage(HashMap<String, Object> params) {
        return NetworkHelper.createObservable(Constants.URL_BASE + Constants.LOOK_MESSAGE, OkHttpClientHelper.METHOD_POST, params,
                MessageBean.class);
    }
}
