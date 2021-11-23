package ru.gb.student.androidcalculator;

import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.RequiresApi;

public class AppSettings implements Parcelable {
    private boolean showBackground;
    private boolean circleKeyboardButtons;
    private int backgroundID;
    private int keyboardThemeID;

    public AppSettings(){}
    public AppSettings(boolean showBackground, boolean circleKeyboardButtons, int backgroundID, int keyboardThemeID){
        setShowBackground(showBackground);
        setCircleKeyboardButtons(circleKeyboardButtons);
        setBackgroundID(backgroundID);
        setKeyboardThemeID(keyboardThemeID);
    }

    @RequiresApi(api = Build.VERSION_CODES.Q)
    protected AppSettings(Parcel in){
        showBackground = in.readBoolean();
        circleKeyboardButtons = in.readBoolean();
        backgroundID = in.readInt();
        keyboardThemeID = in.readInt();
    }

    @RequiresApi(api = Build.VERSION_CODES.Q)
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeBoolean(showBackground);
        dest.writeBoolean(circleKeyboardButtons);
        dest.writeInt(backgroundID);
        dest.writeInt(keyboardThemeID);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<AppSettings> CREATOR = new Creator<AppSettings>() {
        @RequiresApi(api = Build.VERSION_CODES.Q)
        @Override
        public AppSettings createFromParcel(Parcel in) {
            return new AppSettings(in);
        }

        @Override
        public AppSettings[] newArray(int size) {
            return new AppSettings[size];
        }
    };

    public void setShowBackground(boolean showBackground) {
        this.showBackground = showBackground;
    }

    public void setCircleKeyboardButtons(boolean circleKeyboardButtons) {
        this.circleKeyboardButtons = circleKeyboardButtons;
    }

    public void setBackgroundID(int backgroundID) {
        this.backgroundID = backgroundID;
    }

    public void setKeyboardThemeID(int keyboardThemeID) {
        this.keyboardThemeID = keyboardThemeID;
    }

    public boolean isShowBackground() {
        return showBackground;
    }

    public boolean isCircleKeyboardButtons() {
        return circleKeyboardButtons;
    }

    public int getBackgroundID() {
        return backgroundID;
    }

    public int getKeyboardThemeID() {
        return keyboardThemeID;
    }
}
