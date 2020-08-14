package com.anggi.belajaranggi.server.Menu_Item;

import com.google.gson.annotations.SerializedName;

public class Coffeeshop_Item {
    @SerializedName("id_Coffeeshop")
    private String id_coffeeshop;

    @SerializedName("nama_coffeeshop")
    private String nama_coffeeshop;

    @SerializedName("alamat")
    private String alamat;

    @SerializedName("no_telpon")
    private String no_telpon;

    @SerializedName("email")
    private String email;

    @SerializedName("jam_buka")
    private String jam_buka;

    @SerializedName("jam_tutup")
    private String jam_tutup;

    @SerializedName("status")
    private String status;

    @SerializedName("gambar")
    private String gambar;

    @SerializedName("longitude")
    private String longitude;

    @SerializedName("latitude")
    private String latitude;

    @SerializedName("rating")
    private String rating;

    public void setId_coffeeshop(String id_coffeeshop){

        this.id_coffeeshop = id_coffeeshop;
    }

    public String getId_coffeeshop(){
        return id_coffeeshop;
    }

    public void setNama_coffeeshop(String nama_coffeeshop){

        this.nama_coffeeshop = nama_coffeeshop;
    }

    public String getNama_coffeeshop(){
        return nama_coffeeshop;
    }

    public void setAlamat(String alamat){

        this.alamat = alamat;
    }

    public String getAlamat(){
        return alamat;
    }

    public void setNo_telpon(String no_telpon){

        this.no_telpon = no_telpon;
    }

    public String getNo_telpon(){
        return no_telpon;
    }

    public void setEmail(String email){

        this.email = email;
    }

    public String getEmail(){
        return email;
    }

    public void setJam_buka(String jam_buka){

        this.jam_buka = jam_buka;
    }

    public String getJam_buka(){
        return jam_buka;
    }

    public void setJam_tutup(String jam_tutup){

        this.jam_tutup = jam_tutup;
    }

    public String getJam_tutup(){
        return jam_tutup;
    }

    public void setStatus(String status){

        this.status = status;
    }

    public String getStatus(){
        return status;
    }

    public void setGambar(String gambar){

        this.gambar = gambar;
    }

    public String getGambar(){
        return gambar;
    }

    public void setLongitude(String longitude){

        this.longitude = longitude;
    }

    public String getLongitude(){
        return longitude;
    }

    public void setLatitude(String latitude){

        this.latitude = latitude;
    }

    public String getLatitude(){
        return latitude;
    }

    public void setRating(String rating){

        this.rating = rating;
    }

    public String getRating(){
        return rating;
    }

    @Override
    public String toString(){
        return
                "Coffeeshop_Item{" +
                        ",id_coffeeshop= '" + id_coffeeshop+ '\'' +
                        ",nama_coffeeshop= '" + nama_coffeeshop+ '\'' +
                        ",alamat= '" + alamat+ '\'' +
                        ",no_telpon= '" + no_telpon+ '\'' +
                        ",email= '" + email+ '\'' +
                        ",jam_buka= '" + jam_buka+ '\'' +
                        ",jam_tutup= '" + jam_tutup+ '\'' +
                        ",status= '" + status+ '\'' +
                        ",gambar= '" + gambar+ '\'' +
                        ",longitude= '" + longitude+ '\'' +
                        ",latitude= '" + latitude+ '\'' +
                        ",rating= '" + rating+ '\'' +
                        "}";
    }

}
