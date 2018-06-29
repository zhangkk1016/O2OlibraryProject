package cn.hnust.book.contract;

import java.util.List;

import cn.hnust.basebiz.contract.IMvpPresenter;
import cn.hnust.basebiz.contract.IMvpView;
import cn.hnust.book.bean.MineBookBean;

/**
 * Created by tjouyang on 2018/4/28.
 *
 * @author tjouyang
 */

public interface BorrowReturnBookContract {

    interface IView extends IMvpView {
        void queryBookSuccess(List<MineBookBean.MineBookData> data);

        void borrowBookSuccess();

        void returnBookSuccess();
    }

    interface IPresenter extends IMvpPresenter<IView> {

        void queryBook(int bookId);

        void borrowBook(int bookId);

        void returnBook(int bookId);
    }
}
