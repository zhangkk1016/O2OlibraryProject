package cn.hnust.book.presenter;

import android.text.TextUtils;

import java.util.HashMap;

import cn.hnust.basebiz.base.BasePresenter;
import cn.hnust.book.bean.NoticeBean;
import cn.hnust.book.constants.Constants;
import cn.hnust.book.contract.NoticeContract;
import cn.hnust.book.model.NoticeModel;
import cn.hnust.book.utils.SpUtils;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;

/**
 * Created by tjouyang on 2018/4/19.
 *
 * @author tjouyang
 */

public class NoticePresenter extends BasePresenter<NoticeContract.IView> implements NoticeContract.IPresenter {
    private NoticeModel mModel = new NoticeModel();

    @Override
    public void queryNotice() {
        int stuId = SpUtils.getInt(Constants.SP_KEY_STUDENT_ID);
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("studentId", stuId);
        addSubscribe(mModel.queryNotice(hashMap).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<NoticeBean>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                mView.showErrorMsg(e.getMessage());
            }

            @Override
            public void onNext(NoticeBean noticeBean) {
                if (TextUtils.equals(noticeBean.getResult(), Constants.SUCCESS)) {
                    mView.queryNoticeSuccess(noticeBean.getBookInformation());
                } else {
                    mView.showErrorMsg(noticeBean.getResult());
                }
            }
        }));
    }
}
