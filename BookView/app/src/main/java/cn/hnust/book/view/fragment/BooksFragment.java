package cn.hnust.book.view.fragment;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import cn.hnust.basebiz.base.BaseFragment;
import cn.hnust.basebiz.utils.ToastUtils;
import cn.hnust.book.R;
import cn.hnust.book.bean.FindBookBean;
import cn.hnust.book.bean.MineBookBean;
import cn.hnust.book.bean.MyLocation;
import cn.hnust.book.constants.Constants;
import cn.hnust.book.contract.BooksContract;
import cn.hnust.book.presenter.BooksPresenter;
import cn.hnust.book.utils.LocationUtils;
import cn.hnust.book.utils.SpUtils;
import cn.hnust.book.view.BorrowActivity;
import cn.hnust.book.view.adapter.RvMarketAdapter;
import cn.hnust.book.view.adapter.RvMineBookAdapter;

/**
 * Created by tjouyang on 2018/4/18.
 *
 * @author tjouyang
 */

public class BooksFragment extends BaseFragment<BooksContract.IView, BooksContract.IPresenter>
        implements BooksContract.IView, SwipeRefreshLayout.OnRefreshListener, RvMineBookAdapter.OnPublishClickListener, RvMarketAdapter.OnOperateClickListener, BDLocationListener {
    @BindView(R.id.ll_fragment_mine)
    View mine;
    @BindView(R.id.ll_fragment_market)
    View market;
    @BindView(R.id.et_fragment_books_search)
    EditText etSearch;
    @BindView(R.id.srl_fragment_books_mine)
    SwipeRefreshLayout srlMine;
    @BindView(R.id.rv_fragment_books_mine)
    RecyclerView rvMine;
    @BindView(R.id.rv_fragment_books_market)
    RecyclerView rvMarket;
    @BindView(R.id.btn_mine)
    Button btnMine;
    @BindView(R.id.btn_market)
    Button btnMarket;


    List<MineBookBean.MineBookData> mBooks = new ArrayList<>();
    List<FindBookBean.FindBookData> mMarket = new ArrayList<>();


    @OnClick(R.id.btn_mine)
    public void onBtnMine() {
        market.setVisibility(View.GONE);
        btnMarket.setSelected(false);
        mine.setVisibility(View.VISIBLE);
        btnMine.setSelected(true);
    }

    @OnClick(R.id.btn_market)
    public void onBtnMarket() {
        market.setVisibility(View.VISIBLE);
        btnMarket.setSelected(true);
        mine.setVisibility(View.GONE);
        btnMine.setSelected(false);
    }

    @OnClick(R.id.btn_fragment_books_search)
    public void onBtnSearch() {
        int bookId;
        try {
            bookId = Integer.parseInt(etSearch.getText().toString());
        } catch (NumberFormatException e) {
            bookId = -1;
        }
        getPresenter().findBook(bookId);
    }


    public static BooksFragment newInstance() {
        return new BooksFragment();
    }

    @NonNull
    @Override
    protected BooksContract.IPresenter createPresenter() {
        return new BooksPresenter();
    }

    @Override
    protected void onPrepareView(View view) {
        srlMine.setOnRefreshListener(this);
        rvMine.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvMarket.setLayoutManager(new LinearLayoutManager(getActivity()));
        onBtnMine();
        rvMine.setAdapter(new RvMineBookAdapter(mBooks, this));
        rvMarket.setAdapter(new RvMarketAdapter(mMarket, this));
        LocationUtils.getInstance().registerNotify(this);
    }

    @Override
    protected void onRequestData() {
        if (btnMine.isSelected()) {
            LocationUtils.getInstance().startMonitor();
        }
    }

    @Override
    public int layoutId() {
        return R.layout.fragment_books;
    }

    @Override
    public void publishSuccess() {
        ToastUtils.showToast("发布成功！");
    }

    @Override
    public void queryBookSuccess(List<MineBookBean.MineBookData> data) {
        mBooks.clear();
        mBooks.addAll(data);
        rvMine.getAdapter().notifyDataSetChanged();
        srlMine.setRefreshing(false);
    }

    @Override
    public void queryBookFail(String msg) {
        srlMine.setRefreshing(false);
        showErrorMsg(msg);
    }

    @Override
    public void findBookSuccess(List<FindBookBean.FindBookData> data) {
        mMarket.clear();
        mMarket.addAll(data);
        rvMarket.getAdapter().notifyDataSetChanged();
    }

    @Override
    public void onRefresh() {
        LocationUtils.getInstance().startMonitor();
    }

    @Override
    public void onBtnClick(int position) {
        getPresenter().publishBook(mBooks.get(position));
    }

    @Override
    public void onReturnClick(int position) {
        getPresenter().returnBook(mBooks.get(position).getBookId());
    }

    @Override
    public void onOperateClickListener(int position) {
        Intent intent = new Intent(getActivity(), BorrowActivity.class);
        intent.putExtra(Constants.INTENT_KEY_RECEIVE_ID, mMarket.get(position).getUserId());
        intent.putExtra(Constants.INTENT_KEY_BOOK_ID, mMarket.get(position).getBookId());
        startActivity(intent);
    }

    @Override
    public void onReceiveLocation(BDLocation bdLocation) {
        if (bdLocation == null) {
            return;
        }
        ToastUtils.showToast("定位成功: longitude=" + bdLocation.getLongitude()
                + " latitude=" + bdLocation.getLatitude());
        // 转成自己的Location， 利用Gson把百度地图Location转Json时，字段被加密了不好分析
        MyLocation myLocation = new MyLocation();
        myLocation.setLatituede(bdLocation.getLatitude());
        myLocation.setLongitude(bdLocation.getLongitude());
        myLocation.setProvince(bdLocation.getProvince());
        myLocation.setCity(bdLocation.getCity());
        myLocation.setCityCode(bdLocation.getCityCode());
        myLocation.setDistrict(bdLocation.getDistrict());
        myLocation.setStreet(bdLocation.getStreet());
        myLocation.setStreet_number(bdLocation.getStreetNumber());
        myLocation.setFloor(bdLocation.getFloor());
        myLocation.setAddress(bdLocation.getAddrStr());
        myLocation.setStudentId(SpUtils.getInt(Constants.SP_KEY_STUDENT_ID));
        getPresenter().queryMyBook(myLocation);
        LocationUtils.getInstance().stopMonitor();
    }
}
