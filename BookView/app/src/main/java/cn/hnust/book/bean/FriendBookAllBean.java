package cn.hnust.book.bean;

import java.util.List;

/**
 * Created by tjouyang on 2018/4/21.
 *
 * @author tjouyang
 */

public class FriendBookAllBean {

    private String result;
    private List<FriendBookAllData> information;

    public List<FriendBookAllData> getInformation() {
        return information;
    }

    public void setInformation(List<FriendBookAllData> information) {
        this.information = information;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }


    public static class FriendBookAllData {
        private int bookId;
        private String bookName;
        private String friendName;
        private int status;

        @Override
        public String toString() {
            return "FriendBookAllData{" +
                    "bookId=" + bookId +
                    ", bookName='" + bookName + '\'' +
                    ", friendName='" + friendName + '\'' +
                    ", status=" + status +
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

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

    }
}
