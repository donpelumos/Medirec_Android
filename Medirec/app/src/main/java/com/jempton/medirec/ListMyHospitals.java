package com.jempton.medirec;

/**
 * Created by BALE on 04/04/2017.
 */

public class ListMyHospitals{
    String hospitalName;
    String hospitalAddress;
    int hospitalImage;
    public ListMyHospitals(String hospitalName, String hospitalAddress, int hospitalImage){
        this.hospitalImage = hospitalImage;
        this.hospitalAddress = hospitalAddress;
        this.hospitalName = hospitalName;
    }
}