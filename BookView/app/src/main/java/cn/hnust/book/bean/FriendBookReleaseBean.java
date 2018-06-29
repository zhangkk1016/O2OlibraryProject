package cn.hnust.book.bean;

import java.util.List;

/**
 * Created by tjouyang on 2018/4/21.
 *
 * @author tjouyang
 */

public class FriendBookReleaseBean {

    private String result;
    private List<FriendBookReleaseData> information;

    public List<FriendBookReleaseData> getInformation() {
        return information;
    }

    public void setInformation(List<FriendBookReleaseData> information) {
        this.information = information;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public static class FriendBookReleaseData {
        private int bookId;
        private String bookName;
        private String friendName;

        @Override
        public String toString() {
            return "FriendBookReleaseData{" +
                    "bookId=" + bookId +
                    ", bookName='" + bookName + '\'' +
                    ", friendName='" + friendName + '\'' +
                    '}';
        }

        public int getBookId() {
            return bookId;
        }

        public void setBookId(int bookId) {
            this.bookId = bookId;
        }

        public String getBookName() {
            return bookName;
        }

        public void setBookName(String bookName) {
            this.bookName = bookName;
        }

        public String getFriendName() {
            return friendName;
        }

        public void setFriendName(String friendName) {
            this.friendName = friendName;
        }
    }
}
