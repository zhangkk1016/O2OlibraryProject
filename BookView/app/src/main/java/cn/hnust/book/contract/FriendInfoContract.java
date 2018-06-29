package cn.hnust.book.contract;

import cn.hnust.basebiz.contract.IMvpPresenter;
import cn.hnust.basebiz.contract.IMvpView;
import cn.hnust.book.bean.FriendInfoBean;

/**
 * Created by tjouyang on 2018/4/22.
 *
 * @author tjouyang
 */

public interface FriendInfoContract {
    interface IView extends IMvpView {
        void queryFriendInfoSuccess(FriendInfoBean data);

        void deleteFriendSuccess();
    }

    interface IPresenter extends IMvpPresenter<IView> {
        void queryFriendInfo(int friendId);

        void deleteFriend(int friendId);
    }
}
