package com.example.medical;

public class model
{
  String Hospital,pincode,NumofBed,purl;
    model()
    {

    }
    public model(String Hospital, String pincode, String NumofBed, String purl) {
        this.Hospital = Hospital;
        this.pincode = pincode;
        this.NumofBed = NumofBed;
        this.purl = purl;
    }

    public String getHospital() {
        return Hospital;
    }

    public void setHospital(String Hospital) {
        this.Hospital = Hospital;
    }

    public String getpincode() {
        return pincode;
    }

    public void setpincode(String pincode) {
        this.pincode = pincode;
    }

    public String getNumofBed() {
        return NumofBed;
    }

    public void setNumofBed(String NumofBed) {
        this.NumofBed = NumofBed;
    }

    public String getPurl() {
        return purl;
    }

    public void setPurl(String purl) {
        this.purl = purl;
    }
}
