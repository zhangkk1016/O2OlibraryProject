package cn.hnust.book.bean;

import java.util.List;

/**
 * Created by tjouyang on 2018/4/18.
 * ‘我的’ 借的书Bean
 * @author tjouyang
 */

public class MineBookBean {

    private String result;
    private List<MineBookData> bookInformation;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public List<MineBookData> getBookInformation() {
        return bookInformation;
    }

    public void setBookInformation(List<MineBookData> bookInformation) {
        this.bookInformation = bookInformation;
    }

    public static class MineBookData {
        private int bookId;
        private String bookName;
        private int status;

        @Override
        public String toString() {
            return "MineBookData{" +
                    "bookId=" + bookId +
                    ", bookName='" + bookName + '\'' +
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

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }
    }
}
