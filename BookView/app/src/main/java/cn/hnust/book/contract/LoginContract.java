package cn.hnust.book.contract;

import cn.hnust.basebiz.contract.IMvpPresenter;
import cn.hnust.basebiz.contract.IMvpView;

/**
 * Created by tjouyang on 2018/4/16.
 *
 * @author tjouyang
 */

public interface LoginContract {
    interface IView extends IMvpView {
        void loginSuccess();
    }

    interface IPresenter extends IMvpPresenter<IView> {
        void onLogining(String userId, String userName);
    }
}
