package cn.hnust.book.presenter;

import android.text.TextUtils;

import java.util.HashMap;

import cn.hnust.basebiz.base.BasePresenter;
import cn.hnust.book.bean.CommonResultBean;
import cn.hnust.book.bean.MineBookBean;
import cn.hnust.book.constants.Constants;
import cn.hnust.book.contract.BorrowReturnBookContract;
import cn.hnust.book.model.BorrowReturnBookModel;
import cn.hnust.book.utils.SpUtils;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;

/**
 * Created by tjouyang on 2018/4/28.
 *
 * @author tjouyang
 */

public class BorrowReturnBookPresenter extends BasePresenter<BorrowReturnBookContract.IView>
        implements BorrowReturnBookContract.IPresenter {
    private BorrowReturnBookModel model = new BorrowReturnBookModel();

    @Override
    public void queryBook(int bookId) {
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("bookId", bookId);
        addSubscribe(model.queryBook(hashMap).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<MineBookBean>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                mView.showErrorMsg(e.getMessage());
            }

            @Override
            public void onNext(MineBookBean mineBookBean) {
                if (TextUtils.equals(mineBookBean.getResult(), Constants.SUCCESS)) {
                    mView.queryBookSuccess(mineBookBean.getBookInformation());
                } else {
                    mView.showErrorMsg(mineBookBean.getResult());
                }
            }
        }));
    }

    @Override
    public void borrowBook(int bookId) {
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("bookId", bookId);
        hashMap.put("studentId", SpUtils.getInt(Constants.SP_KEY_STUDENT_ID));
        addSubscribe(model.borrowBook(hashMap).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<CommonResultBean>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                mView.showErrorMsg(e.getMessage());
            }

            @Override
            public void onNext(CommonResultBean mineBookBean) {
                if (TextUtils.equals(mineBookBean.getResult(), Constants.SUCCESS)) {
                    mView.borrowBookSuccess();
                } else {
                    mView.showErrorMsg(mineBookBean.getResult());
                }
            }
        }));
    }

    @Override
    public void returnBook(int bookId) {
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("bookId", bookId);
        addSubscribe(model.returnBook(hashMap).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<CommonResultBean>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                mView.showErrorMsg(e.getMessage());
            }

            @Override
            public void onNext(CommonResultBean mineBookBean) {
                if (TextUtils.equals(mineBookBean.getResult(), Constants.SUCCESS)) {
                    mView.returnBookSuccess();
                } else {
                    mView.showErrorMsg(mineBookBean.getResult());
                }
            }
        }));
    }
}
