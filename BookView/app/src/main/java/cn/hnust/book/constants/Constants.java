package cn.hnust.book.constants;

/**
 * Created by tjouyang on 2018/4/16.
 *
 * @author tjouyang
 */

public final class Constants {



    private Constants() {

    }

    // 网络相关开始
    public static final String SUCCESS = "Success";
    public static final String URL_BASE = "http://192.168.191.1:8080/";
    public static final String LOGIN = "book/login";
    public static final String MY_LIST = "book/myList";
    public static final String RELEASE_SERVLET = "book/release";
    public static final String SEARCH_BOOK_SERVLET = "book/searchBook";
    public static final String SEND_MESSAGE = "book/postMessageByBorrower";
    public static final String OWNER_CONFIRM = "book/ownnerComfirm";
    public static final String BORROWER_FINISH = "book/borrowerFinish";
    public static final String OWNER_FINISH = "book/ownnerFinish";
    public static final String MESSAGE_LIST = "book/messageList";
    public static final String LOOK_MESSAGE = "book/observe";
    public static final String FRIEND_SEARCH = "book/friendSearch";
    public static final String FRIEND_ADD = "book/friendAdd";
    public static final String FRIEND_APPLY_LIST = "book/friendApplyList";
    public static final String FRIEND_ALLOW = "book/friendAllow";
    public static final String FRIEND_LIST = "book/friendList";
    public static final String FRIEND_INFORMATION = "book/friendInformation";
    public static final String FRIEND_DELETE = "book/friendDelete";
    public static final String FRIEND_BOOK_LIST = "book/friendBookList";
    public static final String FRIEND_BOOK_RELEASE_LIST = "book/friendReleaseBookList";
    public static final String BOOK_BORROW = "book/borrow";
    public static final String BOOK_RETURN = "book/giveBack";
    public static final String BOOK_QUERY = "book/queryBook";
    // 网络相关结束

    // intent 传参开始
    public static final String INTENT_KEY_LOGIN = "";
    public static final String INTENT_KEY_FRIEND_ID = "friend_id";
    public static final String INTENT_KEY_RECEIVE_ID = "receive_id";
    public static final String INTENT_KEY_BORROW_FLAG = "borrow_flag";
    public static final String INTENT_KEY_BOOK_ID = "book_id";
    // intent 传参结束

    // 存sp的key开始
    public static final String SP_NAME = "my_private_name"; // sp
    public static final String SP_KEY_LOGIN = "is_login"; // 是否已登录
    public static final String SP_KEY_STUDENT_ID = "student_id"; // id
    // 存sp的key结束

    // 分隔符
    public static final String SPLIT_MIDDLE_LINE = "-";
}
