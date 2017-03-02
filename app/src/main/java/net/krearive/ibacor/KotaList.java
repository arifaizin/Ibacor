package net.krearive.ibacor;

public class KotaList {

    private String idKota, namaKota;

    public KotaList() {

    }

    public KotaList(String idKota, String namaKota) {
        this.idKota = idKota;
        this.namaKota = namaKota;
    }

    public String getIdKota() {
        return idKota;
    }

    public void setIdKota(String idKota) {
        this.idKota = idKota;
    }

    public String getNamaKota() {
        return namaKota;
    }

    public void setNamaKota(String namaKota) {
        this.namaKota = namaKota;
    }
}
