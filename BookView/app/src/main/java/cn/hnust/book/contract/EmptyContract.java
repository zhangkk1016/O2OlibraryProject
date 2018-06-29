package cn.hnust.book.contract;

import cn.hnust.basebiz.contract.IMvpPresenter;
import cn.hnust.basebiz.contract.IMvpView;

/**
 * Created by tjouyang on 2018/4/16.
 * 空逻辑
 * @author tjouyang
 */

public interface EmptyContract {

    interface IView extends IMvpView {

    }

    interface IPresenter extends IMvpPresenter<IView> {

    }
}
