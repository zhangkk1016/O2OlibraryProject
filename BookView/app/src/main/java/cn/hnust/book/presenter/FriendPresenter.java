package cn.hnust.book.presenter;

import android.text.TextUtils;

import java.util.HashMap;

import cn.hnust.basebiz.base.BasePresenter;
import cn.hnust.book.bean.FriendBean;
import cn.hnust.book.constants.Constants;
import cn.hnust.book.contract.FriendContract;
import cn.hnust.book.model.FriendModel;
import cn.hnust.book.utils.SpUtils;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;

/**
 * Created by tjouyang on 2018/4/21.
 *
 * @author tjouyang
 */

public class FriendPresenter extends BasePresenter<FriendContract.IView> implements FriendContract.IPresenter {

    private FriendModel mModel = new FriendModel();

    @Override
    public void queryFriendList() {
        HashMap<String, Object> param = new HashMap<>();
        param.put("studentId", SpUtils.getInt(Constants.SP_KEY_STUDENT_ID));
        addSubscribe(mModel.queryFriendList(param).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<FriendBean>() {
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
                    mView.queryFriendListSuccess(friendBean.getInformation());
                } else {
                    mView.showErrorMsg(friendBean.getResult());
                }
            }
        }));
    }
}
