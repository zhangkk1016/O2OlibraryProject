package cn.hnust.book.view;

import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.OnClick;
import cn.hnust.basebiz.base.BaseActivity;
import cn.hnust.basebiz.utils.ToastUtils;
import cn.hnust.book.R;
import cn.hnust.book.bean.FriendBean;
import cn.hnust.book.contract.AddNewFriendContract;
import cn.hnust.book.presenter.AddNewFriendPresenter;

/**
 * Created by tjouyang on 2018/4/22.
 * 添加新朋友
 *
 * @author tjouyang
 */
public class AddNewFriendActivity extends BaseActivity<AddNewFriendContract.IView, AddNewFriendContract.IPresenter>
        implements AddNewFriendContract.IView {
    @BindView(R.id.et_new_friend_search)
    EditText etNewFriendSearch;
    @BindView(R.id.btn_add_new_friend_search)
    Button btnAddNewFriendSearch;
    @BindView(R.id.tv_new_friend_search)
    TextView tvNewFriendSearch;
    @BindView(R.id.btn_add_new_friend_add)
    Button btnAddNewFriendAdd;
    @BindView(R.id.ll_new_friend_one)
    LinearLayout llNewFriendOne;
    private int friendId;

    @OnClick(R.id.btn_add_new_friend_search)
    public void onSearchClick() {
        showLoadingDialog();
        getPresenter().searchStudent(Integer.parseInt(etNewFriendSearch.getText().toString()));
    }

    @OnClick(R.id.btn_add_new_friend_add)
    public void onAddClick() {
        showLoadingDialog();
        getPresenter().addFriend(friendId);
    }

    @NonNull
    @Override
    protected AddNewFriendContract.IPresenter createPresenter() {
        return new AddNewFriendPresenter();
    }

    @Override
    protected void onPrepareView() {
        mToolbar.setTitle("添加联系人");
        llNewFriendOne.setVisibility(View.GONE);
    }

    @Override
    protected void onRequestData() {

    }

    @Override
    public int layoutId() {
        return R.layout.activity_add_new_friend;
    }

    @Override
    public void searchSuccess(FriendBean data) {
        llNewFriendOne.setVisibility(View.VISIBLE);
        tvNewFriendSearch.setText(data.getFriendName());
        friendId = data.getFriendId();
    }

    @Override
    public void addSuccess() {
        showErrorMsg("发送好友申请成功！");
    }
}
