package cn.hnust.book.contract;

import java.util.List;

import cn.hnust.basebiz.contract.IMvpPresenter;
import cn.hnust.basebiz.contract.IMvpView;
import cn.hnust.book.bean.FriendBean;

/**
 * Created by tjouyang on 2018/4/22.
 *
 * @author tjouyang
 */

public interface NewFriendListContract {
    interface IView extends IMvpView {
        void queryFriendNewListSuccess(List<FriendBean.FriendData> datas);

        void refuseSuccess();

        void agreeSuccess();
    }

    interface IPresenter extends IMvpPresenter<IView> {
        void queryFriendNewList();

        void refuseFriend(int friendId);

        void agreeFriend(int friendId);
    }
}
