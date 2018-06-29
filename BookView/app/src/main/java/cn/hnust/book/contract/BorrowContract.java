package cn.hnust.book.contract;

import cn.hnust.basebiz.contract.IMvpPresenter;
import cn.hnust.basebiz.contract.IMvpView;
import cn.hnust.book.bean.MessageBean;

/**
 * Created by tjouyang on 2018/4/22.
 *
 * @author tjouyang
 */

public interface BorrowContract {
    // 核心业务之一
    interface IView extends IMvpView {
        void borrowerSendMessageSuccess();

        void ownnerConfirmBorrowSuccess();

        void borrowerFinishSuccess();

        void ownnerFinishSuccess();

        void lookMessageSuccess(MessageBean bean);
    }

    interface IPresenter extends IMvpPresenter<IView> {
        // 6个逻辑。
        // 借书者发送消息借书
        void borrowerSendMessage(int receiveUserId, int bookId, String studentPhone, String message);

        // 查看消息
        void lookMessage(int bookId, int flag);

        // 被借书者确认借书
        void ownerConfirmBorrow(int bookId);

        // 借书者确认完成借书
        void borrowerFinish(int bookId);

        // 被借书这确认完成借书
        void ownerFinish(int bookId);
    }
}
