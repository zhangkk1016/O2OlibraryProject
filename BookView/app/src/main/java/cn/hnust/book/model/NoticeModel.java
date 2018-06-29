package cn.hnust.book.model;

import java.util.Map;

import cn.hnust.basebiz.net.NetworkHelper;
import cn.hnust.basebiz.net.OkHttpClientHelper;
import cn.hnust.book.bean.NoticeBean;
import cn.hnust.book.constants.Constants;
import rx.Observable;

/**
 * Created by tjouyang on 2018/4/29.
 *
 * @author tjouyang
 */
public class NoticeModel {
    public Observable<NoticeBean> queryNotice(Map<String, Object> param) {
        return NetworkHelper.createObservable(Constants.URL_BASE + Constants.MESSAGE_LIST, OkHttpClientHelper.METHOD_POST,
                param, NoticeBean.class);
    }
}
