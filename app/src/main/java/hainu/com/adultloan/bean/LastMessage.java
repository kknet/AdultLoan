package hainu.com.adultloan.bean;

/**
 * Created by Administrator on 2016/7/13.
 */
public class LastMessage {
    public String phone;
    public String lastMessage;
    public String lastTime;
    public String iconUrl;
    public String nickName;
    public int messageCount;


    public LastMessage(String phone, String lastMessage, String lastTime, String iconUrl, String nickName, int messageCount) {
        this.phone = phone;
        this.lastMessage = lastMessage;
        this.lastTime = lastTime;
        this.iconUrl = iconUrl;
        this.nickName = nickName;
        this.messageCount = messageCount;
    }


    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getLastMessage() {
        return lastMessage;
    }

    public void setLastMessage(String lastMessage) {
        this.lastMessage = lastMessage;
    }

    public String getLastTime() {
        return lastTime;
    }

    public void setLastTime(String lastTime) {
        this.lastTime = lastTime;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public int getMessageCount() {
        return messageCount;
    }

    public void setMessageCount(int messageCount) {
        this.messageCount = messageCount;
    }

    @Override
    public String toString() {
        return "LastMessage{" +
                "phone='" + phone + '\'' +
                ", lastMessage='" + lastMessage + '\'' +
                ", lastTime='" + lastTime + '\'' +
                ", iconUrl='" + iconUrl + '\'' +
                ", nickName='" + nickName + '\'' +
                ", messageCount=" + messageCount +
                '}';
    }
}

