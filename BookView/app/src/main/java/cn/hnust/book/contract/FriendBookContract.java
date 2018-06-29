package cn.hnust.book.contract;

import java.util.List;

import cn.hnust.basebiz.contract.IMvpPresenter;
import cn.hnust.basebiz.contract.IMvpView;
import cn.hnust.book.bean.FriendBookAllBean;
import cn.hnust.book.bean.FriendBookReleaseBean;

/**
 * Created by tjouyang on 2018/4/21.
 *
 * @author tjouyang
 */

public interface FriendBookContract {

    interface IView extends IMvpView {
        void queryFriendBookAllSuccess(List<FriendBookAllBean.FriendBookAllData> datas);

        void queryFriendBookReleaseSuccess(List<FriendBookReleaseBean.FriendBookReleaseData> datas);
    }

    interface IPresenter extends IMvpPresenter<IView> {
        void queryFriendBookAll();

        void queryFriendBookRelease();
    }

}
