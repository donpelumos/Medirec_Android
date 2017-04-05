package com.jempton.medirec;

/**
 * Created by BALE on 04/04/2017.
 */

public class ListAddNewDoctor{
    String doctorName;
    int doctorImage;
    String hospitalName;
    int toAdd;
    public ListAddNewDoctor(String doctorName, String hospitalName, int doctorImage, int toAdd){
        this.doctorImage = doctorImage;
        this.doctorName = doctorName;
        this.hospitalName = hospitalName;
        this.toAdd = toAdd;
    }
}