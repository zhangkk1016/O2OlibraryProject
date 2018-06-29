package cn.hnust.book.bean;

import java.util.List;

/**
 * Created by tjouyang on 2018/4/18.
 *
 * @author tjouyang
 */
public class FindBookBean {

    private String result;
    private List<FindBookData> bookInformation;


    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public List<FindBookData> getBookInformation() {
        return bookInformation;
    }

    public void setBookInformation(List<FindBookData> bookInformation) {
        this.bookInformation = bookInformation;
    }


    public static class FindBookData {
//        "distance":2450,"address":"一教","resultNum":1,"status":3,"userId":1405020300,"longitude":11.22,"latitude":52.11,"bookName":"高数"
        private int bookId;
        private String bookName;
        private int status;
        private String address;
        private int userId;

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

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        @Override
        public String toString() {
            return "FindBookData{" +
                    "bookId=" + bookId +
                    ", bookName='" + bookName + '\'' +
                    ", status=" + status +
                    ", address='" + address + '\'' +
                    '}';
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }
    }
}
