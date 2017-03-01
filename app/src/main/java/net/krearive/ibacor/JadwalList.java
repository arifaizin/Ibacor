package net.krearive.ibacor;

import java.util.ArrayList;

/**
 * Created by Win8 on 23/02/2017.
 */

public class JadwalList {
    private String bioskop, harga;
    private ArrayList<String> dataJam;

    public String getBioskop() {
        return bioskop;
    }

    public void setBioskop(String bioskop) {
        this.bioskop = bioskop;
    }

    public ArrayList<String> getDataJam() {
        return dataJam;
    }

    public void setDataJam(ArrayList<String> dataJam) {
        this.dataJam = dataJam;
    }

    public String getHarga() {
        return harga;
    }

    public void setHarga(String harga) {
        this.harga = harga;
    }
}
