package com.jempton.medirec;

/**
 * Created by BALE on 15/01/2017.
 */

public class ListNotifications {
    String notificationName;
    String notificationTime;
    int notificationImage;
    int notificationChecked;
    int backgroundColorType;
    public ListNotifications(String notificationName, int notificationImage, String notificationTime, int notificationChecked,
                             int backgroundColorType){
        this.notificationImage = notificationImage;
        this.notificationName = notificationName;
        this.notificationTime = notificationTime;
        this.notificationChecked = notificationChecked;
        this.backgroundColorType = backgroundColorType;
    }
}
