package cn.hnust.book.presenter;

import android.text.TextUtils;

import java.util.HashMap;

import cn.hnust.basebiz.base.BasePresenter;
import cn.hnust.book.bean.FriendBookAllBean;
import cn.hnust.book.bean.FriendBookReleaseBean;
import cn.hnust.book.constants.Constants;
import cn.hnust.book.contract.FriendBookContract;
import cn.hnust.book.model.FriendBookModel;
import cn.hnust.book.utils.SpUtils;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;

/**
 * Created by tjouyang on 2018/4/21.
 *
 * @author tjouyang
 */

public class FriendBookPresenter extends BasePresenter<FriendBookContract.IView> implements FriendBookContract.IPresenter {

    private FriendBookModel mModel = new FriendBookModel();

    @Override
    public void queryFriendBookAll() {
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("studentId", SpUtils.getInt(Constants.SP_KEY_STUDENT_ID));
        addSubscribe(mModel.queryAll(hashMap).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<FriendBookAllBean>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                mView.showErrorMsg(e.getMessage());
            }

            @Override
            public void onNext(FriendBookAllBean friendBookAllBean) {
                if (TextUtils.equals(friendBookAllBean.getResult(), Constants.SUCCESS)) {
                    mView.queryFriendBookAllSuccess(friendBookAllBean.getInformation());
                } else {
                    mView.showErrorMsg(friendBookAllBean.getResult());
                }
            }
        }));
    }

    @Override
    public void queryFriendBookRelease() {
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("studentId", SpUtils.getInt(Constants.SP_KEY_STUDENT_ID));
        addSubscribe(mModel.queryRelease(hashMap).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<FriendBookReleaseBean>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                mView.showErrorMsg(e.getMessage());
            }

            @Override
            public void onNext(FriendBookReleaseBean friendBookAllBean) {
                if (TextUtils.equals(friendBookAllBean.getResult(), Constants.SUCCESS)) {
                    mView.queryFriendBookReleaseSuccess(friendBookAllBean.getInformation());
                } else {
                    mView.showErrorMsg(friendBookAllBean.getResult());
                }
            }
        }));
    }
}
