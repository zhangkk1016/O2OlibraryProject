package cn.hnust.book.view;

import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import cn.hnust.basebiz.base.BaseActivity;
import cn.hnust.basebiz.utils.ToastUtils;
import cn.hnust.book.R;
import cn.hnust.book.bean.FriendBean;
import cn.hnust.book.contract.NewFriendListContract;
import cn.hnust.book.presenter.NewFriendListPresenter;
import cn.hnust.book.view.adapter.RvNewFriendAdapter;
import cn.hnust.book.view.weidget.CustomDecoration;

/**
 * Created by tjouyang on 2018/4/22.
 * 申请列表
 *
 * @author tjouyang
 */
public class NewFriendListActivity extends BaseActivity<NewFriendListContract.IView, NewFriendListContract.IPresenter>
        implements NewFriendListContract.IView, RvNewFriendAdapter.OnBtnClickListener {

    @BindView(R.id.rv_new_friend_list)
    RecyclerView rvNewFriendList;
    private List<FriendBean.FriendData> mData = new ArrayList<>();

    @NonNull
    @Override
    protected NewFriendListContract.IPresenter createPresenter() {
        return new NewFriendListPresenter();
    }

    @Override
    protected void onPrepareView() {
        mToolbar.setTitle("申请列表");
        rvNewFriendList.setLayoutManager(new LinearLayoutManager(this));
        rvNewFriendList.addItemDecoration(new CustomDecoration(this, CustomDecoration.VERTICAL_LIST));
        rvNewFriendList.setAdapter(new RvNewFriendAdapter(mData, this));
    }

    @Override
    protected void onRequestData() {
        getPresenter().queryFriendNewList();
    }

    @Override
    public int layoutId() {
        return R.layout.activity_new_friend_list;
    }

    @Override
    public void queryFriendNewListSuccess(List<FriendBean.FriendData> datas) {
        hideLoadingDialog();
        mData.clear();
        mData.addAll(datas);
        rvNewFriendList.getAdapter().notifyDataSetChanged();
    }

    @Override
    public void refuseSuccess() {
        getPresenter().queryFriendNewList();
    }

    @Override
    public void agreeSuccess() {
        hideLoadingDialog();
        ToastUtils.showToast("添加好友成功");
    }

    @Override
    public void onRefuse(int position) {
        showLoadingDialog();
        getPresenter().refuseFriend(mData.get(position).getFriendId());
    }

    @Override
    public void onAgree(int position) {
        hideLoadingDialog();
        getPresenter().agreeFriend(mData.get(position).getFriendId());
    }
}
