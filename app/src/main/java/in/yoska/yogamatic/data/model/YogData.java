package in.yoska.yogamatic.data.model;

import android.os.Parcel;
import android.os.Parcelable;

public class YogData implements Parcelable {
    String category;
    String ailment;
    String imageName;
    String description;
    String type;

    public YogData(){
        this.type = "image";//"video"
    }

    protected YogData(Parcel in) {
        category = in.readString();
        ailment = in.readString();
        imageName = in.readString();
        description = in.readString();
        type = in.readString();
    }

    public static final Creator<YogData> CREATOR = new Creator<YogData>() {
        @Override
        public YogData createFromParcel(Parcel in) {
            return new YogData(in);
        }

        @Override
        public YogData[] newArray(int size) {
            return new YogData[size];
        }
    };

    public String getAilment() {
        return ailment;
    }

    public void setAilment(String ailment) {
        this.ailment = ailment;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public int describeContents() {
        return this.hashCode();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(category);
        dest.writeString(ailment);
        dest.writeString(type);
        dest.writeString(imageName);
        dest.writeString(description);
    }
}

