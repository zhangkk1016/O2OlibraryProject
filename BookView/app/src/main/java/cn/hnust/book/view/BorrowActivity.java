package cn.hnust.book.view;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.widget.Button;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.OnClick;
import cn.hnust.basebiz.base.BaseActivity;
import cn.hnust.basebiz.utils.ToastUtils;
import cn.hnust.book.R;
import cn.hnust.book.bean.MessageBean;
import cn.hnust.book.constants.Constants;
import cn.hnust.book.contract.BorrowContract;
import cn.hnust.book.presenter.BorrowPresenter;
import cn.hnust.book.utils.SpUtils;

/**
 * Created by tjouyang on 2018/4/22.
 *
 * @author tjouyang
 */
public class BorrowActivity extends BaseActivity<BorrowContract.IView, BorrowContract.IPresenter> implements BorrowContract.IView{
    private static final int BORROWER = 0;
    private static final int OWNER = 1;

    @BindView(R.id.et_borrow_stu_id)
    EditText etBorrowStuId;
    @BindView(R.id.et_borrow_stu_phone)
    EditText etBorrowStuPhone;
    @BindView(R.id.et_borrow_stu_message)
    EditText etBorrowStuMessage;
    @BindView(R.id.btn_borrow_send_or_confirm)
    Button btnBorrowSendOrConfirm;
    @BindView(R.id.btn_borrow_finish)
    Button btnBorrowFinish;

    private int receiveUserId;
    private int flag = BORROWER;
    private int bookId;

    @OnClick(R.id.btn_borrow_send_or_confirm)
    public void onFirstButtonClick() {
        showLoadingDialog();
        if (flag == BORROWER) {
            getPresenter().borrowerSendMessage(receiveUserId, bookId, etBorrowStuPhone.getText().toString(), etBorrowStuMessage.getText().toString());
        } else {
            getPresenter().ownerConfirmBorrow(bookId);
        }
    }

    @OnClick(R.id.btn_borrow_finish)
    public void onSecondButonClick() {
        showLoadingDialog();
        if (flag == BORROWER) {
            getPresenter().borrowerFinish(bookId);
        } else {
            getPresenter().ownerFinish(bookId);
        }
    }

    @NonNull
    @Override
    protected BorrowContract.IPresenter createPresenter() {
        return new BorrowPresenter();
    }

    @Override
    protected void onPrepareView() {
        Intent intent = getIntent();
        if (intent.hasExtra(Constants.INTENT_KEY_RECEIVE_ID)) {
            receiveUserId = intent.getIntExtra(Constants.INTENT_KEY_RECEIVE_ID, 0);
        }
        if (intent.hasExtra(Constants.INTENT_KEY_BORROW_FLAG)) {
            flag = intent.getIntExtra(Constants.INTENT_KEY_BORROW_FLAG, BORROWER);
        }
        if (intent.hasExtra(Constants.INTENT_KEY_BOOK_ID)) {
            bookId = intent.getIntExtra(Constants.INTENT_KEY_BOOK_ID, 0);
        }
        etBorrowStuId.setText(String.valueOf(SpUtils.getInt(Constants.SP_KEY_STUDENT_ID)));
        btnBorrowFinish.setText("完成");
        mToolbar.setTitle("" + bookId + "未借");
    }

    @Override
    protected void onRequestData() {
        showLoadingDialog();
        if (flag == OWNER) {
            btnBorrowSendOrConfirm.setText("确认");
        } else {
            btnBorrowSendOrConfirm.setText("发送");
        }
        getPresenter().lookMessage(bookId, flag);
    }

    @Override
    public int layoutId() {
        return R.layout.activity_borrow;
    }

    @Override
    public void borrowerSendMessageSuccess() {
        hideLoadingDialog();
        ToastUtils.showToast("发送通知成功");
    }

    @Override
    public void ownnerConfirmBorrowSuccess() {
        hideLoadingDialog();
        ToastUtils.showToast("操作成功");
    }

    @Override
    public void borrowerFinishSuccess() {
        hideLoadingDialog();
        ToastUtils.showToast("操作成功");
    }

    @Override
    public void ownnerFinishSuccess() {
        hideLoadingDialog();
        ToastUtils.showToast("操作成功");
    }

    @Override
    public void lookMessageSuccess(MessageBean bean) {
        hideLoadingDialog();
        mToolbar.setTitle(bean.getBookId() + "-状态" + bean.getStatus());
        etBorrowStuId.setText(String.valueOf(bean.getStartUserId()));
        etBorrowStuPhone.setText(String.valueOf(bean.getStudentPhone()));
        etBorrowStuMessage.setText(String.valueOf(bean.getMessage()));
    }
}
