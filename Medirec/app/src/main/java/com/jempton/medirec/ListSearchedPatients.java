package com.jempton.medirec;

/**
 * Created by BALE on 07/04/2017.
 */

public class ListSearchedPatients {
    String patientName;
    int patientImage;
    int patientAccessType;
    int backgroundColorType;
    public ListSearchedPatients(String patientName, int patientImage, int patientAccessType){
        this.patientImage = patientImage;
        this.patientName = patientName;
        this.patientAccessType = patientAccessType;
    }
}