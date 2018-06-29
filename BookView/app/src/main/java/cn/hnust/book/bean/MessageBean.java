package cn.hnust.book.bean;

/**
 * Created by tjouyang on 2018/4/29.
 *
 * @author tjouyang
 */
public class MessageBean {
//    {
//        "result":"Success",
//            "message":"请你借书给我好吗",
//            "startUserId":1405020301,
//            "time":"2018-04-28",
//            "studentPhone":"15811111111",
//            "status":0,
//            "bookId":10001
//    }
    private String result;
    private String message;
    private int status;
    private int bookId;
    private int startUserId;
    private String studentPhone;

    @Override
    public String toString() {
        return "MessageBean{" +
                "result='" + result + '\'' +
                ", message='" + message + '\'' +
                ", status=" + status +
                ", bookId=" + bookId +
                ", studentPhone='" + studentPhone + '\'' +
                '}';
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public String getStudentPhone() {
        return studentPhone;
    }

    public void setStudentPhone(String studentPhone) {
        this.studentPhone = studentPhone;
    }

    public int getStartUserId() {
        return startUserId;
    }

    public void setStartUserId(int startUserId) {
        this.startUserId = startUserId;
    }
}
