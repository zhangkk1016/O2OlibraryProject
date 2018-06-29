package cn.hnust.book.view.fragment;

import android.support.v4.app.Fragment;

/**
 * Created by tjouyang on 2018/4/18.
 *
 * @author tjouyang
 */
public class MainFragmentFactory {
    public static Fragment newInstance(int position) {
        switch (position) {
            case 0:
                return BooksFragment.newInstance();
            case 1:
                return NoticeFragment.newInstance();
            case 2:
                return FriendBookFragment.newInstance();
            case 3:
                return FriendFragment.newInstance();
            default:
                return null;
        }
    }
}
