package cn.hnust.book.contract;

import java.util.List;

import cn.hnust.basebiz.contract.IMvpPresenter;
import cn.hnust.basebiz.contract.IMvpView;
import cn.hnust.book.bean.NoticeBean;

/**
 * Created by tjouyang on 2018/4/19.
 *
 * @author tjouyang
 */

public interface NoticeContract {

    interface IView extends IMvpView {
        void queryNoticeSuccess(List<NoticeBean.NoticeData> datas);
    }

    interface IPresenter extends IMvpPresenter<IView> {
        void queryNotice();
    }
}
