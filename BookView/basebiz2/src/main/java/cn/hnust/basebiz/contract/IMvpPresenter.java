package cn.hnust.basebiz.contract;

/**
 * Created by tjouyang on 2018/4/15.
 *
 * @author tjouyang
 */

public interface IMvpPresenter<View extends IMvpView> {

    void attachView(View view);

    void detachView();

    void cancelAll();
}
