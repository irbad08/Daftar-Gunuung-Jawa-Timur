package com.example.daftargunuungjawatimur;

public class DataGunung {
    private String ID;
    private String NAMA_GUNUNG;
    private String ALAMAT;

    public DataGunung(String ID, String NAMA_GUNUNG, String ALAMAT) {
        this.ID = ID;
        this.NAMA_GUNUNG = NAMA_GUNUNG;
        this.ALAMAT = ALAMAT;
    }

    public DataGunung() {

    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getNAMA_GUNUNG() {
        return NAMA_GUNUNG;
    }

    public void setNAMA_GUNUNG(String NAMA_GUNUNG) {
        this.NAMA_GUNUNG = NAMA_GUNUNG;
    }

    public String getALAMAT() {
        return ALAMAT;
    }

    public void setALAMAT(String ALAMAT) {
        this.ALAMAT = ALAMAT;
    }
}
