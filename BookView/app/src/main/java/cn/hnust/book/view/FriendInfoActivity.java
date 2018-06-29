package cn.hnust.book.view;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import cn.hnust.basebiz.base.BaseActivity;
import cn.hnust.basebiz.utils.ToastUtils;
import cn.hnust.book.R;
import cn.hnust.book.bean.FriendInfoBean;
import cn.hnust.book.bean.MineBookBean;
import cn.hnust.book.constants.Constants;
import cn.hnust.book.contract.FriendInfoContract;
import cn.hnust.book.presenter.FriendInfoPresenter;
import cn.hnust.book.view.adapter.RvMineBookAdapter;

/**
 * Created by tjouyang on 2018/4/22.
 *
 * @author tjouyang
 */
public class FriendInfoActivity extends BaseActivity<FriendInfoContract.IView, FriendInfoContract.IPresenter>
        implements FriendInfoContract.IView, RvMineBookAdapter.OnPublishClickListener {
    @BindView(R.id.tv_friend_name)
    TextView tvFriendName;
    @BindView(R.id.tv_friend_id)
    TextView tvFriendId;
    @BindView(R.id.tv_phone)
    TextView tvPhone;
    @BindView(R.id.rv_books)
    RecyclerView rvBooks;
    private List<MineBookBean.MineBookData> mData = new ArrayList<>();
    private int friendId;

    @OnClick(R.id.btn_delete_friend)
    public void onDeleteClick() {
        showLoadingDialog();
        getPresenter().deleteFriend(friendId);
    }

    @NonNull
    @Override
    protected FriendInfoContract.IPresenter createPresenter() {
        return new FriendInfoPresenter();
    }

    @Override
    protected void onPrepareView() {
        mToolbar.setTitle("个人资料");
        rvBooks.setLayoutManager(new LinearLayoutManager(this));
        rvBooks.setAdapter(new RvMineBookAdapter(mData, this, R.string.btn_borrow));
    }

    @Override
    protected void onRequestData() {
        if (getIntent().hasExtra(Constants.INTENT_KEY_FRIEND_ID)) {
            friendId = getIntent().getIntExtra(Constants.INTENT_KEY_FRIEND_ID, -1);
            showLoadingDialog();
            getPresenter().queryFriendInfo(friendId);
        } else {
            ToastUtils.showToast(" 无 FriendId");
        }
    }

    @Override
    public int layoutId() {
        return R.layout.activity_friend_info;
    }

    @Override
    public void queryFriendInfoSuccess(FriendInfoBean data) {
        hideLoadingDialog();
        mData.clear();
        mData.addAll(data.getBookInformation());
        rvBooks.getAdapter().notifyDataSetChanged();
        tvFriendId.setText(String.valueOf(data.getStudentId()));
        tvFriendName.setText(data.getStudentName());
        tvPhone.setText(data.getStudentPhone());
    }

    @Override
    public void deleteFriendSuccess() {
        hideLoadingDialog();
        finish();
    }

    @Override
    public void onBtnClick(int position) {
        Intent intent = new Intent(this, BorrowActivity.class);
        intent.putExtra(Constants.INTENT_KEY_BORROW_FLAG, 0);
        intent.putExtra(Constants.INTENT_KEY_BOOK_ID, mData.get(position).getBookId());
        intent.putExtra(Constants.INTENT_KEY_RECEIVE_ID, friendId);
        startActivity(intent);
    }

    @Override
    public void onReturnClick(int position) {

    }
}
