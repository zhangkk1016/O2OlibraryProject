package cn.hnust.book.presenter;

import android.text.TextUtils;

import java.util.HashMap;

import cn.hnust.basebiz.base.BasePresenter;
import cn.hnust.book.bean.CommonResultBean;
import cn.hnust.book.bean.FriendBean;
import cn.hnust.book.constants.Constants;
import cn.hnust.book.contract.AddNewFriendContract;
import cn.hnust.book.model.AddNewFriendModel;
import cn.hnust.book.utils.SpUtils;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;

/**
 * Created by tjouyang on 2018/4/22.
 *
 * @author tjouyang
 */

public class AddNewFriendPresenter extends BasePresenter<AddNewFriendContract.IView> implements AddNewFriendContract.IPresenter {
    private AddNewFriendModel mModel = new AddNewFriendModel();

    @Override
    public void searchStudent(int studentId) {
        HashMap<String, Object> param = new HashMap<>();
        param.put("friendId", studentId);
        addSubscribe(mModel.searchFriend(param).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<FriendBean>() {
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
                    mView.searchSuccess(friendBean);
                } else {
                    mView.showErrorMsg(friendBean.getResult());
                }
            }
        }));
    }

    @Override
    public void addFriend(int friendId) {
        HashMap<String, Object> param = new HashMap<>();
        param.put("friendId", friendId);
        param.put("studentId", SpUtils.getInt(Constants.SP_KEY_STUDENT_ID));
        addSubscribe(mModel.addFriend(param).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<CommonResultBean>() {
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
                    mView.addSuccess();
                } else {
                    mView.showErrorMsg(commonResultBean.getResult());
                }
            }
        }));
    }
}
