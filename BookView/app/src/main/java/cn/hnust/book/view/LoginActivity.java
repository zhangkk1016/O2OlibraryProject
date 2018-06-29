package cn.hnust.book.view;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.OnClick;
import cn.hnust.basebiz.base.BaseActivity;
import cn.hnust.book.R;
import cn.hnust.book.contract.LoginContract;
import cn.hnust.book.presenter.LoginPresenter;

/**
 * Created by tjouyang on 2018/4/17.
 *
 * @author tjouyang
 */

public class LoginActivity extends BaseActivity<LoginContract.IView, LoginContract.IPresenter> implements LoginContract.IView{
    @BindView(R.id.et_student_id)
    EditText etStudentId;
    @BindView(R.id.et_student_name)
    EditText etStudentName;

    @OnClick(R.id.btn_login)
    public void onLoginClick() {
        showLoadingDialog();
        getPresenter().onLogining(etStudentId.getText().toString(), etStudentName.getText().toString());
    }


    @NonNull
    @Override
    protected LoginContract.IPresenter createPresenter() {
        return new LoginPresenter();
    }

    @Override
    protected void onPrepareView() {

    }

    @Override
    protected void onRequestData() {

    }

    @Override
    public int layoutId() {
        return R.layout.activity_login;
    }

    @Override
    public void loginSuccess() {
        hideLoadingDialog();
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
