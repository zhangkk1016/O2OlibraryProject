package cn.hnust.book.bean;

import java.util.List;

/**
 * Created by tjouyang on 2018/4/21.
 * 朋友列表和申请列表可以共用这个bean
 * @author tjouyang
 */

public class FriendBean {

    private String result;
    private int friendId;
    private String friendName;

    private List<FriendData> information;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public int getFriendId() {
        return friendId;
    }

    public void setFriendId(int friendId) {
        this.friendId = friendId;
    }

    public String getFriendName() {
        return friendName;
    }

    public void setFriendName(String friendName) {
        this.friendName = friendName;
    }

    public List<FriendData> getInformation() {
        return information;
    }

    public void setInformation(List<FriendData> information) {
        this.information = information;
    }

    public static class FriendData {
        private int friendId;
        private String friendName;

        @Override
        public String toString() {
            return "FriendData{" +
                    "friendId=" + friendId +
                    ", friendName='" + friendName + '\'' +
                    '}';
        }

        public int getFriendId() {
            return friendId;
        }

        public void setFriendId(int friendId) {
            this.friendId = friendId;
        }

        public String getFriendName() {
            return friendName;
        }

        public void setFriendName(String friendName) {
            this.friendName = friendName;
        }
    }
}
