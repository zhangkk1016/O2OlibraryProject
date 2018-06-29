package cn.hnust.basebiz.base;

import java.util.ArrayList;
import java.util.List;

import cn.hnust.basebiz.contract.IMvpPresenter;
import cn.hnust.basebiz.contract.IMvpView;
import rx.Subscription;

/**
 * Created by tjouyang on 2018/4/15.
 *
 * @author tjouyang
 */

public abstract class BasePresenter<View extends IMvpView> implements IMvpPresenter<View>{
    protected View mView;
    private List<Subscription> subs = new ArrayList<>();
    @Override
    public void attachView(View view) {
        this.mView = view;
    }

    @Override
    public void detachView() {
        mView = null;
    }

    public void addSubscribe(Subscription subscription) {
        subs.add(subscription);
    }

    @Override
    public void cancelAll() {
        for (Subscription subscription : subs) {
            if (subscription != null) {
                if (!subscription.isUnsubscribed()) {
                    subscription.unsubscribe();
                }
            }
        }
     }
}
