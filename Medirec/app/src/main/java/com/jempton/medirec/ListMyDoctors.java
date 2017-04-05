package com.jempton.medirec;

/**
 * Created by BALE on 03/04/2017.
 */

public class ListMyDoctors {
    String doctorName;
    int doctorImage;
    String hospitalName;
    public ListMyDoctors(String doctorName, String hospitalName, int doctorImage){
        this.doctorImage = doctorImage;
        this.doctorName = doctorName;
        this.hospitalName = hospitalName;
    }
}
