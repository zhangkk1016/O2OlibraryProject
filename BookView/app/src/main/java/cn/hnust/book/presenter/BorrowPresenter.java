package cn.hnust.book.presenter;

import android.text.TextUtils;

import java.util.HashMap;

import cn.hnust.basebiz.base.BasePresenter;
import cn.hnust.book.bean.CommonResultBean;
import cn.hnust.book.bean.MessageBean;
import cn.hnust.book.bean.OwnerConfirmResultBean;
import cn.hnust.book.constants.Constants;
import cn.hnust.book.contract.BorrowContract;
import cn.hnust.book.model.BorrowModel;
import cn.hnust.book.utils.SpUtils;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.ActionN;

/**
 * Created by tjouyang on 2018/4/22.
 *
 * @author tjouyang
 */

public class BorrowPresenter extends BasePresenter<BorrowContract.IView> implements BorrowContract.IPresenter {
    private BorrowModel mBorrowModel = new BorrowModel();

    @Override
    public void borrowerSendMessage(int receiveUserId, int bookId, String studentPhone, String message) {
        HashMap<String, Object> params = new HashMap<>();
//        {
//            "startUserId":"1405020301",
//                "receiveUserId":"1405020300",
//                "bookId":"10001",
//                "studentPhone":"15811111111",
//                "message":"请你借书给我好吗"
//        }

        params.put("startUserId", SpUtils.getInt(Constants.SP_KEY_STUDENT_ID));
        params.put("receiveUserId", receiveUserId);
        params.put("bookId", bookId);
        params.put("studentPhone", studentPhone);
        params.put("message", message);
        mBorrowModel.sendMessage(params).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<CommonResultBean>() {
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
                    mView.borrowerSendMessageSuccess();
                } else {
                    mView.showErrorMsg(commonResultBean.getResult());
                }
            }
        });
    }

    @Override
    public void lookMessage(int bookId, int flag) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("bookId", bookId);
        params.put("flag", flag);
        params.put("studentId", SpUtils.getInt(Constants.SP_KEY_STUDENT_ID));
        addSubscribe(mBorrowModel.lookMessage(params).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<MessageBean>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                mView.showErrorMsg(e.getMessage());
            }

            @Override
            public void onNext(MessageBean messageBean) {
                if (TextUtils.equals(Constants.SUCCESS, messageBean.getResult())) {
                    mView.lookMessageSuccess(messageBean);
                } else {
                    mView.showErrorMsg(messageBean.getResult());
                }
            }
        }));
//        {
//            studentId:
//            bookId：
//            flag:0-借书者；1-拥有者
//        }

    }

    @Override
    public void ownerConfirmBorrow(int bookId) {
//        {
//            "bookId":"10001",
//                "studentId":"1405020300"
//        }

        HashMap<String, Object> params = new HashMap<>();
        params.put("bookId", bookId);
        params.put("studentId", SpUtils.getInt(Constants.SP_KEY_STUDENT_ID));
        addSubscribe(mBorrowModel.ownerConfirm(params).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<OwnerConfirmResultBean>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                mView.showErrorMsg(e.getMessage());
            }

            @Override
            public void onNext(OwnerConfirmResultBean ownerConfirmResultBean) {
                if (TextUtils.equals(ownerConfirmResultBean.getResult(), Constants.SUCCESS)) {
                    mView.ownnerConfirmBorrowSuccess();
                } else {
                    mView.showErrorMsg(ownerConfirmResultBean.getMessage());
                }
            }
        }));
    }

    @Override
    public void borrowerFinish(int bookId) {
//        {
//            "bookId":"10001",
//                "studentId":"1405020301"
//        }

        HashMap<String, Object> params = new HashMap<>();
        params.put("bookId", bookId);
        params.put("studentId", SpUtils.getInt(Constants.SP_KEY_STUDENT_ID));
        addSubscribe(mBorrowModel.borrowFinish(params).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<CommonResultBean>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                mView.showErrorMsg(e.getMessage());
            }

            @Override
            public void onNext(CommonResultBean commonResultBean) {
                if (TextUtils.equals(commonResultBean.getResult(), Constants.SUCCESS)) {
                    mView.borrowerFinishSuccess();
                } else {
                    mView.showErrorMsg(commonResultBean.getResult());
                }
            }
        }));
    }

    @Override
    public void ownerFinish(int bookId) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("bookId", bookId);
        params.put("studentId", SpUtils.getInt(Constants.SP_KEY_STUDENT_ID));
        addSubscribe(mBorrowModel.ownerFinish(params).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<OwnerConfirmResultBean>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                mView.showErrorMsg(e.getMessage());
            }

            @Override
            public void onNext(OwnerConfirmResultBean ownerConfirmResultBean) {
                if (TextUtils.equals(ownerConfirmResultBean.getResult(), Constants.SUCCESS)) {
                    mView.ownnerFinishSuccess();
                } else {
                    mView.showErrorMsg(ownerConfirmResultBean.getMessage()
                    );
                }
            }
        }));
    }
}
