package cn.hnust.book.view.fragment;

import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import cn.hnust.basebiz.base.BaseFragment;
import cn.hnust.book.R;
import cn.hnust.book.bean.FriendBookAllBean;
import cn.hnust.book.bean.FriendBookReleaseBean;
import cn.hnust.book.contract.FriendBookContract;
import cn.hnust.book.presenter.FriendBookPresenter;
import cn.hnust.book.view.adapter.RvFriendBookAllAdapter;
import cn.hnust.book.view.adapter.RvFriendBookReleaseAdapter;
import cn.hnust.book.view.weidget.CustomDecoration;

/**
 * Created by tjouyang on 2018/4/18.
 *
 * @author tjouyang
 */

public class FriendBookFragment extends BaseFragment<FriendBookContract.IView, FriendBookContract.IPresenter>
        implements FriendBookContract.IView, SwipeRefreshLayout.OnRefreshListener {
    @BindView(R.id.btn_fragment_book_all)
    Button btnFragmentBookAll;
    @BindView(R.id.btn_fragment_book_only_release)
    Button btnFragmentBookOnlyRelease;
    @BindView(R.id.rv_fragment_book_all)
    RecyclerView rvFragmentBookAll;
    @BindView(R.id.srl_fragment_book_all)
    SwipeRefreshLayout srlFragmentBookAll;
    @BindView(R.id.ll_fragment_book_all)
    LinearLayout llFragmentBookAll;
    @BindView(R.id.rv_fragment_book_release)
    RecyclerView rvFragmentBookRelease;
    @BindView(R.id.srl_fragment_book_release)
    SwipeRefreshLayout srlFragmentBookRelease;
    @BindView(R.id.ll_fragment_book_release)
    LinearLayout llFragmentBookRelease;

    private List<FriendBookAllBean.FriendBookAllData> allDatas = new ArrayList<>();
    private List<FriendBookReleaseBean.FriendBookReleaseData> releaseDatas = new ArrayList<>();

    @OnClick(R.id.btn_fragment_book_all)
    public void onAllClick() {
        handleClick(true);
    }

    private void handleClick(boolean isAllClick) {
        btnFragmentBookAll.setSelected(isAllClick);
        btnFragmentBookOnlyRelease.setSelected(!isAllClick);
        if (isAllClick) {
            llFragmentBookAll.setVisibility(View.VISIBLE);
            llFragmentBookRelease.setVisibility(View.GONE);
            srlFragmentBookAll.setRefreshing(true);
            getPresenter().queryFriendBookAll();
        } else {
            llFragmentBookAll.setVisibility(View.GONE);
            llFragmentBookRelease.setVisibility(View.VISIBLE);
            srlFragmentBookRelease.setRefreshing(true);
            getPresenter().queryFriendBookRelease();
        }
    }

    @OnClick(R.id.btn_fragment_book_only_release)
    public void onReleaseClick() {
        handleClick(false);
    }

    public static FriendBookFragment newInstance() {
        return new FriendBookFragment();
    }

    @NonNull
    @Override
    protected FriendBookPresenter createPresenter() {
        return new FriendBookPresenter();
    }

    @Override
    protected void onPrepareView(View view) {
        RecyclerView.ItemDecoration itemDecoration = new CustomDecoration(getActivity(), CustomDecoration.VERTICAL_LIST);

        rvFragmentBookRelease.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvFragmentBookRelease.addItemDecoration(itemDecoration);
        rvFragmentBookRelease.setAdapter(new RvFriendBookReleaseAdapter(releaseDatas));

        rvFragmentBookAll.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvFragmentBookAll.addItemDecoration(itemDecoration);
        rvFragmentBookAll.setAdapter(new RvFriendBookAllAdapter(allDatas));

        srlFragmentBookAll.setOnRefreshListener(this);
        srlFragmentBookRelease.setOnRefreshListener(this);

        llFragmentBookAll.setVisibility(View.VISIBLE);
        llFragmentBookRelease.setVisibility(View.GONE);
        btnFragmentBookAll.setSelected(true);
    }

    @Override
    protected void onRequestData() {
        if (btnFragmentBookAll.isSelected()) {
            handleClick(true);
        } else {
            handleClick(false);
        }
    }

    @Override
    public int layoutId() {
        return R.layout.fragment_friend_book;
    }

    @Override
    public void queryFriendBookAllSuccess(List<FriendBookAllBean.FriendBookAllData> datas) {
        allDatas.clear();
        allDatas.addAll(datas);
        rvFragmentBookAll.getAdapter().notifyDataSetChanged();
        srlFragmentBookAll.setRefreshing(false);
    }

    @Override
    public void queryFriendBookReleaseSuccess(List<FriendBookReleaseBean.FriendBookReleaseData> datas) {
        releaseDatas.clear();
        releaseDatas.addAll(datas);
        rvFragmentBookRelease.getAdapter().notifyDataSetChanged();
        srlFragmentBookRelease.setRefreshing(false);
    }

    @Override
    public void showErrorMsg(String msg) {
        srlFragmentBookRelease.setRefreshing(false);
        srlFragmentBookAll.setRefreshing(false);
        super.showErrorMsg(msg);
    }

    @Override
    public void onRefresh() {
        onRequestData();
    }
}
