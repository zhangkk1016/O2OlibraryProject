package cn.hnust.book.contract;

import java.util.List;

import cn.hnust.basebiz.contract.IMvpPresenter;
import cn.hnust.basebiz.contract.IMvpView;
import cn.hnust.book.bean.FriendBean;

/**
 * Created by tjouyang on 2018/4/21.
 *
 * @author tjouyang
 */

public interface FriendContract {

    interface IView extends IMvpView {
        void queryFriendListSuccess(List<FriendBean.FriendData> datas);
    }

    interface IPresenter extends IMvpPresenter<IView> {
        void queryFriendList();
    }
}
