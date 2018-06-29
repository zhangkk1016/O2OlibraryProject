package cn.hnust.book.presenter;

import android.text.TextUtils;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import cn.hnust.basebiz.base.BasePresenter;
import cn.hnust.basebiz.utils.GsonUtils;
import cn.hnust.book.bean.CommonResultBean;
import cn.hnust.book.bean.FindBookBean;
import cn.hnust.book.bean.MineBookBean;
import cn.hnust.book.bean.MyLocation;
import cn.hnust.book.constants.Constants;
import cn.hnust.book.contract.BooksContract;
import cn.hnust.book.model.BooksModel;
import cn.hnust.book.model.BorrowReturnBookModel;
import cn.hnust.book.utils.SpUtils;
import cn.hnust.book.view.fragment.BooksFragment;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;

/**
 * Created by tjouyang on 2018/4/18.
 *
 * @author tjouyang
 */

public class BooksPresenter extends BasePresenter<BooksContract.IView> implements BooksContract.IPresenter {
    private BooksModel booksModel = new BooksModel();

    @Override
    public void queryMyBook(MyLocation myLocation) {
        String json = GsonUtils.bean2Json(myLocation);
        Log.e(BooksFragment.class.getSimpleName(), json);
        addSubscribe(booksModel.queryMyBook(json).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<MineBookBean>() {
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
                    mView.queryBookFail(mineBookBean.getResult());
                }
            }
        }));

    }

    @Override
    public void publishBook(MineBookBean.MineBookData data) {
        String json = GsonUtils.bean2Json(data);
        addSubscribe(booksModel.publishBook(json).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<CommonResultBean>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                mView.showErrorMsg(e.getMessage());
            }

            @Override
            public void onNext(CommonResultBean commonResultBean) {
                if (TextUtils.equals(Constants.SUCCESS, commonResultBean.getResult())) {
                    mView.publishSuccess();
                } else {
                    mView.showErrorMsg("发布失败！");
                }
            }
        }));
    }

    @Override
    public void findBook(final int bookId) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("bookId", bookId);
        params.put("studentId", SpUtils.getInt(Constants.SP_KEY_STUDENT_ID));
        addSubscribe(booksModel.findBook(params).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<FindBookBean>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                mView.showErrorMsg(e.getMessage());
            }

            @Override
            public void onNext(FindBookBean findBookData) {
                if (TextUtils.equals(findBookData.getResult(), Constants.SUCCESS)) {
                    mView.findBookSuccess(findBookData.getBookInformation());
                } else {
                    mView.showErrorMsg(findBookData.getResult());
                }
            }
        }));
    }

    @Override
    public void returnBook(int bookId) {
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("bookId", bookId);
        addSubscribe(new BorrowReturnBookModel().returnBook(hashMap).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<CommonResultBean>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                mView.showErrorMsg(e.getMessage());
            }

            @Override
            public void onNext(CommonResultBean commonResultBean) {
                mView.showErrorMsg(commonResultBean.getResult());
            }
        }));
    }
}
