package cn.hnust.book.bean;

import java.util.List;

/**
 * Created by tjouyang on 2018/4/22.
 *
 * @author tjouyang
 */

public class FriendInfoBean {

    private String result;
    private String studentName;
    private int studentId;
    private String studentPhone;
    private List<MineBookBean.MineBookData> bookInformation;

    @Override
    public String toString() {
        return "FriendInfoBean{" +
                "result='" + result + '\'' +
                ", studentName='" + studentName + '\'' +
                ", studentId=" + studentId +
                ", studentPhone='" + studentPhone + '\'' +
                ", bookInformation=" + bookInformation +
                '}';
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public String getStudentPhone() {
        return studentPhone;
    }

    public void setStudentPhone(String studentPhone) {
        this.studentPhone = studentPhone;
    }

    public List<MineBookBean.MineBookData> getBookInformation() {
        return bookInformation;
    }

    public void setBookInformation(List<MineBookBean.MineBookData> bookInformation) {
        this.bookInformation = bookInformation;
    }
}

