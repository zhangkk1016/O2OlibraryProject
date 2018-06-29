package cn.hnust.book.contract;

import cn.hnust.basebiz.contract.IMvpPresenter;
import cn.hnust.basebiz.contract.IMvpView;
import cn.hnust.book.bean.FriendBean;

/**
 * Created by tjouyang on 2018/4/22.
 *
 * @author tjouyang
 */

public interface AddNewFriendContract {

    interface IView extends IMvpView {
        void searchSuccess(FriendBean data);

        void addSuccess();
    }

    interface IPresenter extends IMvpPresenter<IView> {
        void searchStudent(int studentId);

        void addFriend(int studentId);
    }

}
