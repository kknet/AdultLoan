package hainu.com.adultloan.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Administrator on 2016/7/12.
 */
public class Message implements Parcelable {
    public String mesId;
    public String time;
    public String icon;
    public String message;
    public int tag;
    private int type;
    private int duration;


    public Message(String mesId, String time, String icon, String message, int tag) {
        this.mesId = mesId;
        this.time = time;
        this.icon = icon;
        this.message = message;
        this.tag = tag;
    }

    public Message(String time, String icon, String message, int tag, int type) {
        this.time = time;
        this.icon = icon;
        this.message = message;
        this.tag = tag;
        this.type = type;
    }

    public Message(String time, String icon, String message, int tag) {
        this.time = time;
        this.icon = icon;
        this.message = message;
        this.tag = tag;
    }

    public Message(String time, String icon, String message, int tag, int type, int duration) {
        this.time = time;
        this.icon = icon;
        this.message = message;
        this.tag = tag;
        this.type = type;
        this.duration = duration;
    }


    public Message(String mesId, String time, String icon, String message, int tag, int type, int duration) {
        this.mesId = mesId;
        this.time = time;
        this.icon = icon;
        this.message = message;
        this.tag = tag;
        this.type = type;
        this.duration = duration;
    }

    protected Message(Parcel in) {
        mesId = in.readString();
        time = in.readString();
        icon = in.readString();
        message = in.readString();
        tag = in.readInt();
        type = in.readInt();
        duration = in.readInt();
    }

    public static final Creator<Message> CREATOR = new Creator<Message>() {
        @Override
        public Message createFromParcel(Parcel in) {
            return new Message(in);
        }

        @Override
        public Message[] newArray(int size) {
            return new Message[size];
        }
    };

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getTag() {
        return tag;
    }

    public void setTag(int tag) {
        this.tag = tag;
    }

    public String getMesId() {
        return mesId;
    }

    public void setMesId(String mesId) {
        this.mesId = mesId;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mesId);
        dest.writeString(time);
        dest.writeString(icon);
        dest.writeString(message);
        dest.writeInt(tag);
        dest.writeInt(type);
        dest.writeInt(duration);
    }
}
