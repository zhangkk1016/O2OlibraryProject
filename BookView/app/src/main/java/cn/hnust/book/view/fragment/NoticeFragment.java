package cn.hnust.book.view.fragment;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import cn.hnust.basebiz.base.BaseFragment;
import cn.hnust.book.R;
import cn.hnust.book.bean.NoticeBean;
import cn.hnust.book.constants.Constants;
import cn.hnust.book.contract.NoticeContract;
import cn.hnust.book.presenter.NoticePresenter;
import cn.hnust.book.view.BorrowActivity;
import cn.hnust.book.view.adapter.RvNoticeAdapter;
import cn.hnust.book.view.weidget.CustomDecoration;

/**
 * Created by tjouyang on 2018/4/18.
 *
 * @author tjouyang
 */

public class NoticeFragment extends BaseFragment<NoticeContract.IView, NoticeContract.IPresenter>
        implements NoticeContract.IView, RvNoticeAdapter.OnItemClickListener, SwipeRefreshLayout.OnRefreshListener {

    @BindView(R.id.rv_fragment_notice)
    RecyclerView rvNotice;
    @BindView(R.id.srl_fragment_notice)
    SwipeRefreshLayout srlNotice;
    private List<NoticeBean.NoticeData> mDatas = new ArrayList<>();

    public static NoticeFragment newInstance() {
        return new NoticeFragment();
    }

    @NonNull
    @Override
    protected NoticeContract.IPresenter createPresenter() {
        return new NoticePresenter();
    }

    @Override
    protected void onPrepareView(View view) {
        rvNotice.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvNotice.addItemDecoration(new CustomDecoration(getActivity(), CustomDecoration.VERTICAL_LIST));
        rvNotice.setAdapter(new RvNoticeAdapter(mDatas, this));
        srlNotice.setOnRefreshListener(this);
    }

    @Override
    protected void onRequestData() {
        getPresenter().queryNotice();
    }

    @Override
    public int layoutId() {
        return R.layout.fragment_notice;
    }

    @Override
    public void queryNoticeSuccess(List<NoticeBean.NoticeData> datas) {
        srlNotice.setRefreshing(false);
        mDatas.clear();
        mDatas.addAll(datas);
        rvNotice.getAdapter().notifyDataSetChanged();
    }

    @Override
    public void showErrorMsg(String msg) {
        srlNotice.setRefreshing(false);
        super.showErrorMsg(msg);
    }

    @Override
    public void onItemClick(int position) {
        Intent intent = new Intent(getActivity(), BorrowActivity.class);
        intent.putExtra(Constants.INTENT_KEY_BORROW_FLAG, mDatas.get(position).getFlag());
        intent.putExtra(Constants.INTENT_KEY_BOOK_ID, mDatas.get(position).getBookId());
        startActivity(intent);
    }

    @Override
    public void onRefresh() {
        getPresenter().queryNotice();
    }
}
