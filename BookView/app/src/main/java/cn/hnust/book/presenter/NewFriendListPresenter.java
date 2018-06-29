package cn.hnust.book.presenter;

import android.text.TextUtils;

import java.util.HashMap;
import java.util.Map;

import cn.hnust.basebiz.base.BasePresenter;
import cn.hnust.book.bean.CommonResultBean;
import cn.hnust.book.bean.FriendBean;
import cn.hnust.book.constants.Constants;
import cn.hnust.book.contract.NewFriendListContract;
import cn.hnust.book.model.NewFriendModel;
import cn.hnust.book.utils.SpUtils;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;

/**
 * Created by tjouyang on 2018/4/22.
 *
 * @author tjouyang
 */

public class NewFriendListPresenter extends BasePresenter<NewFriendListContract.IView> implements NewFriendListContract.IPresenter {

    private NewFriendModel mModel = new NewFriendModel();

    @Override
    public void queryFriendNewList() {
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("studentId", SpUtils.getInt(Constants.SP_KEY_STUDENT_ID));
        addSubscribe(mModel.queryApplyList(hashMap).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<FriendBean>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                mView.showErrorMsg(e.getMessage());
            }

            @Override
            public void onNext(FriendBean friendBean) {
                if (TextUtils.equals(friendBean.getResult(), Constants.SUCCESS)) {
                    mView.queryFriendNewListSuccess(friendBean.getInformation());
                } else {
                    mView.showErrorMsg(friendBean.getResult());
                }
            }
        }));
    }

    @Override
    public void refuseFriend(int friendId) {
        Map<String, Object> param = buildBody("n", friendId);
        addSubscribe(mModel.allowFriend(param).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<CommonResultBean>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                mView.showErrorMsg(e.getMessage());
            }

            @Override
            public void onNext(CommonResultBean friendBean) {
                if (TextUtils.equals(friendBean.getResult(), Constants.SUCCESS)) {
                    mView.refuseSuccess();
                } else {
                    mView.showErrorMsg(friendBean.getResult());
                }
            }
        }));
    }

    @Override
    public void agreeFriend(int friendId) {
        Map<String, Object> param = buildBody("y", friendId);
        addSubscribe(mModel.allowFriend(param).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<CommonResultBean>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                mView.showErrorMsg(e.getMessage());
            }

            @Override
            public void onNext(CommonResultBean friendBean) {
                if (TextUtils.equals(friendBean.getResult(), Constants.SUCCESS)) {
                    mView.agreeSuccess();
                } else {
                    mView.showErrorMsg(friendBean.getResult());
                }
            }
        }));
    }

    private Map<String, Object> buildBody(String y, int friendId) {
//        {
//            "friendId":"1405020300",
//                "studentId":"1405020301",
//                "friendAllow":"y"
//        }

        HashMap<String, Object> ret = new HashMap<>();
        ret.put("friendId", friendId);
        ret.put("studentId", SpUtils.getInt(Constants.SP_KEY_STUDENT_ID));
        ret.put("friendAllow", y);
        return ret;
    }
}
