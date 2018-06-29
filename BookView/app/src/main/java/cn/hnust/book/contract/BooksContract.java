package cn.hnust.book.contract;

import java.util.List;

import cn.hnust.basebiz.contract.IMvpPresenter;
import cn.hnust.basebiz.contract.IMvpView;
import cn.hnust.book.bean.FindBookBean;
import cn.hnust.book.bean.MineBookBean;
import cn.hnust.book.bean.MyLocation;

/**
 * Created by tjouyang on 2018/4/18.
 *
 * @author tjouyang
 */

public interface BooksContract {

    interface IView extends IMvpView {
        /**
         *  发布成功
         */
        void publishSuccess();

        /**
         * 查询自己的book成功
         * @return
         */
        void queryBookSuccess(List<MineBookBean.MineBookData> data);

        void queryBookFail(String msg);

        void  findBookSuccess(List<FindBookBean.FindBookData> data);
    }

    interface IPresenter extends IMvpPresenter<IView> {
        /**
         * 查询我所借的书
         */
        void queryMyBook(MyLocation myLocation);

        /**
         * 发布
         */
        void publishBook(MineBookBean.MineBookData data);

        /**
         * 搜索
         * @param bookId
         */
        void findBook(int bookId);

        void returnBook(int bookId);
    }
}
