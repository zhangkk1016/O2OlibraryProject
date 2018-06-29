package cn.hnust.basebiz.base;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import cn.hnust.basebiz.contract.IMvpPresenter;
import cn.hnust.basebiz.contract.IMvpView;
import cn.hnust.basebiz.utils.ToastUtils;

/**
 * Created by tjouyang on 2018/4/18.
 * 默认会保存Fragment实例
 *
 * @author tjouyang
 */

public abstract class BaseFragment<V extends IMvpView, Presenter extends IMvpPresenter> extends Fragment implements IMvpView {
    private Presenter presenter;
    private Unbinder unbinder;
    protected boolean isInit = false;
    private View mView;

    @NonNull
    protected abstract Presenter createPresenter();

    /**
     * 初始化界面
     */
    protected abstract void onPrepareView(View view);

    /**
     * 一般用于网络请求，在初始化界面之后调用
     */
    protected abstract void onRequestData();

    /**
     * 必须返回layoutId
     *
     * @return @ResId
     */
    public abstract int layoutId();

    @Override
    public void showErrorMsg(String msg) {
        // 若不重写，默认为弹出toast
        ToastUtils.showToast(msg);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        isInit = false;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        Log.d("book", "frag create start");
        if (!isInit) {
            getPresenter().attachView(this);
            mView = inflater.inflate(layoutId(), container, false);
            unbinder = ButterKnife.bind(this, mView);
            onPrepareView(mView);
            isInit = true;
        }
        return mView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        isCanLoadData();
        Log.d("book", "frag create end");
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        isCanLoadData();
    }

    /**
     * 是否可以加载数据
     * 可以加载数据的条件：
     * 1.视图已经初始化
     * 2.视图对用户可见
     */
    private void isCanLoadData() {
        if (!isInit) {
            return;
        }
        if (getUserVisibleHint()) {
            onRequestData();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    protected Presenter getPresenter() {
        if (presenter == null) {
            presenter = createPresenter();
        }
        return presenter;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.cancelAll();
        isInit = false;
        if (unbinder != null) {
            unbinder.unbind();
        }
        mView = null;
        getPresenter().detachView();
        presenter = null;
    }
}
