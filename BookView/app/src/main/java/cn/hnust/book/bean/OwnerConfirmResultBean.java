package cn.hnust.book.bean;

/**
 * Created by tjouyang on 2018/4/29.
 *
 * @author tjouyang
 */

public class OwnerConfirmResultBean {
    private String result;
    private String Message;
    private int status;


    @Override
    public String toString() {
        return "OwnerConfirmResultBean{" +
                "result='" + result + '\'' +
                ", Message='" + Message + '\'' +
                ", status=" + status +
                '}';
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

}
