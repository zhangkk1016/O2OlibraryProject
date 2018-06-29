package cn.hnust.book.presenter;

import android.text.TextUtils;

import java.util.HashMap;

import cn.hnust.basebiz.base.BasePresenter;
import cn.hnust.book.bean.CommonResultBean;
import cn.hnust.book.bean.FriendInfoBean;
import cn.hnust.book.constants.Constants;
import cn.hnust.book.contract.FriendInfoContract;
import cn.hnust.book.model.FriendInfoModel;
import cn.hnust.book.utils.SpUtils;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;

/**
 * Created by tjouyang on 2018/4/22.
 *
 * @author tjouyang
 */

public class FriendInfoPresenter extends BasePresenter<FriendInfoContract.IView> implements FriendInfoContract.IPresenter {

    private FriendInfoModel mModel = new FriendInfoModel();

    @Override
    public void queryFriendInfo(int friendId) {
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("friendId", friendId);
        addSubscribe(mModel.queryFriendInfo(hashMap).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<FriendInfoBean>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                mView.showErrorMsg(e.getMessage());
            }

            @Override
            public void onNext(FriendInfoBean friendInfoBean) {
                if (TextUtils.equals(friendInfoBean.getResult(), Constants.SUCCESS)) {
                    mView.queryFriendInfoSuccess(friendInfoBean);
                } else {
                    mView.showErrorMsg(friendInfoBean.getResult());
                }
            }
        }));
    }

    @Override
    public void deleteFriend(int friendId) {
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("friendId", friendId);
        hashMap.put("studentId", SpUtils.getInt(Constants.SP_KEY_STUDENT_ID));
        addSubscribe(mModel.deleteFriend(hashMap).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<CommonResultBean>() {
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
                    mView.deleteFriendSuccess();
                } else {
                    mView.showErrorMsg(commonResultBean.getResult());
                }
            }
        }));
    }

}
