package cn.hnust.book.view.fragment;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import cn.hnust.basebiz.base.BaseFragment;
import cn.hnust.book.R;
import cn.hnust.book.bean.FriendBean;
import cn.hnust.book.constants.Constants;
import cn.hnust.book.contract.FriendContract;
import cn.hnust.book.presenter.FriendPresenter;
import cn.hnust.book.view.AddNewFriendActivity;
import cn.hnust.book.view.FriendInfoActivity;
import cn.hnust.book.view.NewFriendListActivity;
import cn.hnust.book.view.adapter.RvFriendAdapter;
import cn.hnust.book.view.weidget.CustomDecoration;

/**
 * Created by tjouyang on 2018/4/18.
 *
 * @author tjouyang
 */

public class FriendFragment extends BaseFragment<FriendContract.IView, FriendContract.IPresenter>
        implements FriendContract.IView, RvFriendAdapter.OnItemClickListener {

    @BindView(R.id.tv_fragment_add_new)
    TextView tvFragmentAddNew;
    @BindView(R.id.tv_fragment_new_list)
    TextView tvFragmentNewList;
    @BindView(R.id.rv_fragment_friends)
    RecyclerView rvFragmentFriends;

    private List<FriendBean.FriendData> mData = new ArrayList<>();

    public static FriendFragment newInstance() {
        return new FriendFragment();
    }

    @OnClick(R.id.tv_fragment_add_new)
    public void onAddNewFriendClick() {
        startActivity(new Intent(getActivity(), AddNewFriendActivity.class));
    }

    @OnClick(R.id.tv_fragment_new_list)
    public void onNewListClick() {
        startActivity(new Intent(getActivity(), NewFriendListActivity.class));
    }


    @NonNull
    @Override
    protected FriendPresenter createPresenter() {
        return new FriendPresenter();
    }

    @Override
    protected void onPrepareView(View view) {
        rvFragmentFriends.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvFragmentFriends.addItemDecoration(new CustomDecoration(getActivity(), CustomDecoration.VERTICAL_LIST));
        rvFragmentFriends.setAdapter(new RvFriendAdapter(mData, this));
    }

    @Override
    protected void onRequestData() {
        getPresenter().queryFriendList();
    }

    @Override
    public int layoutId() {
        return R.layout.fragment_friend;
    }

    @Override
    public void queryFriendListSuccess(List<FriendBean.FriendData> datas) {
        mData.clear();
        mData.addAll(datas);
        rvFragmentFriends.getAdapter().notifyDataSetChanged();
    }

    @Override
    public void onItemClick(int position) {
        FriendBean.FriendData data = mData.get(position);
        Intent intent = new Intent(getActivity(), FriendInfoActivity.class)
                .putExtra(Constants.INTENT_KEY_FRIEND_ID, data.getFriendId());
        startActivity(intent);
    }
}
