package cn.hnust.book.view;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import butterknife.BindView;
import butterknife.OnClick;
import cn.hnust.basebiz.base.BaseActivity;
import cn.hnust.basebiz.utils.ToastUtils;
import cn.hnust.book.R;
import cn.hnust.book.constants.Constants;
import cn.hnust.book.contract.EmptyContract;
import cn.hnust.book.presenter.EmptyPresenter;
import cn.hnust.book.utils.LocationUtils;
import cn.hnust.book.utils.SpUtils;
import cn.hnust.book.view.adapter.MainFragmentPagerAdapter;

/**
 * Created by tjouyang on 2018/4/18.
 *
 * @author tjouyang
 */

public class MainActivity extends BaseActivity<EmptyContract.IView, EmptyContract.IPresenter> implements EmptyContract.IView {

    private boolean flag;
    private MainFragmentPagerAdapter adapter;


    @BindView(R.id.vp_container)
    ViewPager vpContainer;

    @OnClick(R.id.ibtn_books)
    public void onBooksClick() {
        vpContainer.setCurrentItem(0);
        adapter.getFragmentByPosition(0).setUserVisibleHint(true);
        changeTitle(0);
    }

    @OnClick(R.id.ibtn_notice)
    public void onNoticeClick() {
        vpContainer.setCurrentItem(1);
        adapter.getFragmentByPosition(1).setUserVisibleHint(true);
        changeTitle(1);
    }

    @OnClick(R.id.ibtn_friend_book)
    public void onFriendBookClick() {
        vpContainer.setCurrentItem(2);
        adapter.getFragmentByPosition(2).setUserVisibleHint(true);
        changeTitle(2);
    }

    @OnClick(R.id.ibtn_student_friend)
    public void onFriendClick() {
        vpContainer.setCurrentItem(3);
        adapter.getFragmentByPosition(3).setUserVisibleHint(true);
        changeTitle(3);
    }

    @NonNull
    @Override
    protected EmptyContract.IPresenter createPresenter() {
        return new EmptyPresenter();
    }

    @Override
    protected void onPrepareView() {
        adapter = new MainFragmentPagerAdapter(getSupportFragmentManager());
        vpContainer.setAdapter(adapter);
        vpContainer.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                changeTitle(position);
                adapter.getFragmentByPosition(position).setUserVisibleHint(true);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        mToolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.borrow_or_return_book:
                        startActivity(new Intent(MainActivity.this, BorrowReturnBookActivity.class));
                        break;
                    case R.id.re_login:
                        SpUtils.putBoolean(Constants.SP_KEY_LOGIN, false);
                        startActivity(new Intent(MainActivity.this, LoginActivity.class));
                        break;
                    default:
                        break;
                }
                return true;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    private void changeTitle(int position) {
        switch (position) {
            case 1:
                mToolbar.setTitle("通知列表");
                break;
            case 3:
                mToolbar.setTitle("联系人");
                break;
            case 0:
            case 2:
            default:
                mToolbar.setTitle("图书流转系统");
                break;
        }
    }

    @Override
    protected void onRequestData() {
        changeTitle(0); // 首先需要。。。
    }

    @Override
    protected void onResume() {
        super.onResume();
        initPermission();//针对6.0以上版本做权限适配
    }

    private void initPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            //检查权限
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED
                    || ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                //请求权限
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION}, 1);
            } else {
                flag = true;
            }
        } else {
            flag = true;
        }
    }

    /**
     * 权限的结果回调函数
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1) {
            flag = grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults[1] == PackageManager.PERMISSION_GRANTED;
            if (!flag) {
                ToastUtils.showToast("无定位权限");
            }
        }
    }

    @Override
    public int layoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        System.exit(0);
    }
}
