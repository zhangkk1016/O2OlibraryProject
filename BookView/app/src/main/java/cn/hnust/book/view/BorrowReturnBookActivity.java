package cn.hnust.book.view;

import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import cn.hnust.basebiz.base.BaseActivity;
import cn.hnust.basebiz.utils.ToastUtils;
import cn.hnust.book.R;
import cn.hnust.book.bean.MineBookBean;
import cn.hnust.book.contract.BorrowReturnBookContract;
import cn.hnust.book.presenter.BorrowReturnBookPresenter;

/**
 * Created by tjouyang on 2018/4/28.
 *
 * @author tjouyang
 */
public class BorrowReturnBookActivity extends BaseActivity<BorrowReturnBookContract.IView, BorrowReturnBookContract.IPresenter>
        implements BorrowReturnBookContract.IView {
    @BindView(R.id.et_borrow_book_search)
    EditText etBorrowBookSearch;
    @BindView(R.id.btn_borrow_book_search)
    Button btnBorrowBookSearch;
    @BindView(R.id.tv_borrow_book_search_result)
    TextView tvBorrowBookSearchResult;
    @BindView(R.id.btn_borrow_book_borrow)
    Button btnBorrowBookBorrow;
    @BindView(R.id.btn_borrow_book_return)
    Button btnBorrowBookReturn;
    @BindView(R.id.ll_borrow_book_one)
    LinearLayout llBorrowBookOne;
    private int bookId;

    @OnClick(R.id.btn_borrow_book_search)
    public void onSearchClick() {
        showLoadingDialog();
        int bookId;
        try {
            bookId = Integer.parseInt(etBorrowBookSearch.getText().toString());
        } catch (NumberFormatException e) {
            bookId = -1;
        }
        getPresenter().queryBook(bookId);
    }

    @OnClick(R.id.btn_borrow_book_borrow)
    public void onBorrow() {
        showLoadingDialog();
        getPresenter().borrowBook(bookId);
    }

    @OnClick(R.id.btn_borrow_book_return)
    public void onReturn() {
        showLoadingDialog();
        getPresenter().returnBook(bookId);
    }

    @NonNull
    @Override
    protected BorrowReturnBookContract.IPresenter createPresenter() {
        return new BorrowReturnBookPresenter();
    }

    @Override
    protected void onPrepareView() {
        llBorrowBookOne.setVisibility(View.GONE);
    }

    @Override
    protected void onRequestData() {

    }

    @Override
    public int layoutId() {
        return R.layout.activity_borrow_book;
    }

    @Override
    public void queryBookSuccess(List<MineBookBean.MineBookData> datas) {
        if (datas == null || datas.isEmpty()) {
            showErrorMsg("null");
            return;
        }
        MineBookBean.MineBookData data = datas.get(0);
        llBorrowBookOne.setVisibility(View.VISIBLE);
        tvBorrowBookSearchResult.setText(data.getBookName());
        bookId = data.getBookId();
    }

    @Override
    public void borrowBookSuccess() {
        ToastUtils.showToast("借书成功！");
    }

    @Override
    public void returnBookSuccess() {
        ToastUtils.showToast("还书成功！");
    }
}
