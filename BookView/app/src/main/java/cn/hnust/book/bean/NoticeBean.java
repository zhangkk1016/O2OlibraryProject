package cn.hnust.book.bean;

import java.util.List;

/**
 * Created by tjouyang on 2018/4/19.
 *
 * @author tjouyang
 */

public class NoticeBean {

    private String result;
    private List<NoticeData> bookInformation;

    public List<NoticeData> getBookInformation() {
        return bookInformation;
    }

    public void setBookInformation(List<NoticeData> bookInformation) {
        this.bookInformation = bookInformation;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public static class NoticeData {
        private int bookId;
        private int status;
        private int flag;

        @Override
        public String toString() {
            return "NoticeData{" +
                    "bookId=" + bookId +
                    ", status=" + status +
                    ", flag=" + flag +
                    '}';
        }

        public int getBookId() {
            return bookId;
        }

        public void setBookId(int bookId) {
            this.bookId = bookId;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public int getFlag() {
            return flag;
        }

        public void setFlag(int flag) {
            this.flag = flag;
        }
    }
}
