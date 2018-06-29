package cn.hnust.book.view;

import android.content.Intent;
import android.support.annotation.NonNull;

import cn.hnust.basebiz.base.BaseActivity;
import cn.hnust.book.R;
import cn.hnust.book.constants.Constants;
import cn.hnust.book.contract.EmptyContract;
import cn.hnust.book.presenter.EmptyPresenter;
import cn.hnust.book.utils.SpUtils;

public class PrepareActivity extends BaseActivity<EmptyContract.IView, EmptyContract.IPresenter> implements EmptyContract.IView {
    @NonNull
    @Override
    public EmptyContract.IPresenter createPresenter() {
        return new EmptyPresenter();
    }

    @Override
    protected void onPrepareView() {
//        DeviceBean deviceBean = new DeviceBean();
//        deviceBean.setDeviceId(MacUtils.getDeviceId(this));
//        deviceBean.setMacAddr(MacUtils.getMac());
//        Log.e("Activity", GsonUtils.bean2Json(deviceBean));
        if (SpUtils.getBoolean(Constants.SP_KEY_LOGIN)) {
            // 已登录，到主界面去
            startActivity(new Intent(this, MainActivity.class));
        } else {
            startActivity(new Intent(this, LoginActivity.class));
        }
    }

    @Override
    protected void onRequestData() {
        // 空
        finish();
    }

    @Override
    public int layoutId() {
        return R.layout.activity_prepare;
    }
}
