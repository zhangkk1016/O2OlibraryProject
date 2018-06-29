package cn.hnust.book.presenter;

import android.text.TextUtils;

import java.util.HashMap;

import cn.hnust.basebiz.base.BasePresenter;
import cn.hnust.book.bean.CommonResultBean;
import cn.hnust.book.constants.Constants;
import cn.hnust.book.contract.LoginContract;
import cn.hnust.book.model.LoginModel;
import cn.hnust.book.utils.SpUtils;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;

/**
 * Created by tjouyang on 2018/4/16.
 *
 * @author tjouyang
 */

public class LoginPresenter extends BasePresenter<LoginContract.IView> implements LoginContract.IPresenter {

    private LoginModel loginModel = new LoginModel();

    @Override
    public void onLogining(final String studentId, String studentName) {
        if (!check(studentId, studentName)) {
            return;
        }
        final int id;
        try {
            id = Integer.parseInt(studentId);
        } catch (NumberFormatException e) {
            mView.showErrorMsg(e.getMessage());
            return;
        }
        HashMap<String, Object> params = new HashMap<>();
        params.put("studentId", id);
        params.put("studentName", studentName);
        addSubscribe(loginModel.onLogin(params)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<CommonResultBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        mView.showErrorMsg(e.getMessage());
                    }

                    @Override
                    public void onNext(CommonResultBean commonResultBean) {
                        if (TextUtils.equals(commonResultBean.getResult(), Constants.SUCCESS)) {
                            SpUtils.putInt(Constants.SP_KEY_STUDENT_ID, id);
                            SpUtils.putBoolean(Constants.SP_KEY_LOGIN, true);
                            mView.loginSuccess();
                        } else {
                            mView.showErrorMsg(commonResultBean.getResult());
                        }
                    }
                }));
    }

    private boolean check(String studentId, String studentName) {
        if (TextUtils.isEmpty(studentId)) {
            mView.showErrorMsg("学号不能为空");
            return false;
        }
        if (TextUtils.isEmpty(studentName)) {
            mView.showErrorMsg("姓名不能为空");
            return false;
        }
        if (TextUtils.equals(studentId, "123456") && TextUtils.equals(studentName, "123")) {
            // 测试用
            SpUtils.putInt(Constants.SP_KEY_STUDENT_ID, 1405020329);
            SpUtils.putBoolean(Constants.SP_KEY_LOGIN, true);
            mView.loginSuccess();
            return false;
        }
        return true;
    }
}
