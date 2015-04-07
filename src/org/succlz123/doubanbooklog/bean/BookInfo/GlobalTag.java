package org.succlz123.doubanbooklog.bean.bookinfo;

import android.os.Parcel;
import android.os.Parcelable;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by fashi on 2015/4/1.
 */
public class GlobalTag implements Parcelable {

    private int count;//��ǩ����
    private String name;//��ǩ����
    private String title;//��ǩ����

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public static GlobalTag parseJson(JSONObject object) {
        try {
            GlobalTag globalTag = new GlobalTag();

            Integer count = object.getInt("count");
            String name = object.getString("name");
            String title = object.getString("title");

            globalTag.setCount(count);
            globalTag.setName(name);
            globalTag.setTitle(title);

            return globalTag;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.count);
        dest.writeString(this.name);
        dest.writeString(this.title);
    }

    public GlobalTag() {
    }

    private GlobalTag(Parcel in) {
        this.count = in.readInt();
        this.name = in.readString();
        this.title = in.readString();
    }

    public static final Parcelable.Creator<GlobalTag> CREATOR = new Parcelable.Creator<GlobalTag>() {
        public GlobalTag createFromParcel(Parcel source) {
            return new GlobalTag(source);
        }

        public GlobalTag[] newArray(int size) {
            return new GlobalTag[size];
        }
    };
}
