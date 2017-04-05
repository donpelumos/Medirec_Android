package com.jempton.medirec;

/**
 * Created by BALE on 21/03/2017.
 */

public class ListMessages {
    String doctorName;
    String dateTime;
    int messageCount;
    public ListMessages(String doctorName, String dateTime, int messageCount){
        this.doctorName = doctorName;
        this.dateTime = dateTime;
        this.messageCount = messageCount;
    }
}
