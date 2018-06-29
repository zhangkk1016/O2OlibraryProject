package cn.hnust.basebiz.base;

import android.app.ProgressDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import cn.hnust.basebiz.R;
import cn.hnust.basebiz.contract.IMvpPresenter;
import cn.hnust.basebiz.contract.IMvpView;
import cn.hnust.basebiz.utils.ToastUtils;

/**
 * Created by tjouyang on 2018/4/15.
 *
 * @author tjouyang
 */

public abstract class BaseActivity<V extends IMvpView, Presenter extends IMvpPresenter> extends AppCompatActivity implements IMvpView {

    protected Toolbar mToolbar; // 可供子类自定义标题栏

    private Presenter presenter;
    private Unbinder unbinder;
    private ProgressDialog progressDialog;

    @NonNull
    protected abstract Presenter createPresenter();

    /**
     * 初始化界面
     */
    protected abstract void onPrepareView();

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
        hideLoadingDialog();
        ToastUtils.showToast(msg);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("book", "onCreateStart");
        presenter = createPresenter();
        presenter.attachView(this);
        setContentView(R.layout.activity_base);
        LinearLayout myLayout = (LinearLayout) findViewById(R.id.baseLinearLayout);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        View contentView = getLayoutInflater().inflate(layoutId(), myLayout, false);
        LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        contentView.setLayoutParams(params);
        myLayout.addView(contentView);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        mToolbar.setNavigationIcon(R.mipmap.back);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 默认结束该Activity
                finish();
            }
        });
        mToolbar.setTitleTextColor(Color.WHITE);
        unbinder = ButterKnife.bind(this);
        onPrepareView();
        onRequestData();
        Log.d("book", "onCreateEnd");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.cancelAll();
        presenter.detachView();
        if (unbinder != null) {
            unbinder.unbind();
        }
    }

    protected Presenter getPresenter() {
        if (presenter == null) {
            presenter = createPresenter();
        }
        return presenter;
    }

    protected void showLoadingDialog(String msg) {
        if (progressDialog == null) {
            progressDialog = new ProgressDialog(this);
            progressDialog.setCanceledOnTouchOutside(false);
        }
        progressDialog.setMessage(msg);
        progressDialog.show();
    }

    protected void showLoadingDialog() {
        showLoadingDialog("正在加载数据");
    }

    protected void hideLoadingDialog() {
        if (progressDialog != null) {
            progressDialog.dismiss();
        }
    }
}
