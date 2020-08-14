package com.anggi.belajaranggi.server.Menu_Item;

import com.google.gson.annotations.SerializedName;

public class Datamenu_Item {

    @SerializedName("id_coffeeshop")
    private String id_coffeeshop;

    @SerializedName("id_menu")
    private String id_menu;

    @SerializedName("nama_menu")
    private String nama_menu;

    @SerializedName("jenis")
    private String jenis;

    @SerializedName("harga")
    private String harga;

    @SerializedName("gambar")
    private String gambar;

    public void setId_coffeeshop(String id_coffeeshop){

        this.id_coffeeshop = id_coffeeshop;
    }

    public String getId_coffeeshop(){
        return id_coffeeshop;
    }

    public void setId_menu(String id_menu){

        this.id_menu = id_menu;
    }

    public String getId_menu(){
        return id_menu;
    }

    public void setNama_menu(String nama_menu){

        this.nama_menu = nama_menu;
    }

    public String getNama_menu(){
        return nama_menu;
    }

    public void setJenis(String jenis){

        this.jenis = jenis;
    }

    public String getJenis(){
        return jenis;
    }

    public void setHarga(String harga){

        this.harga = harga;
    }

    public String getHarga(){
        return harga;
    }

    public void setGambar(String gambar){

        this.gambar = gambar;
    }

    public String getGambar(){ return gambar; }

    @Override
    public String toString(){
        return
                "Datamenu_Item{" +
                        ",id_coffeeshop= '" + id_coffeeshop+ '\'' +
                        ",id_menu= '" + id_menu+ '\'' +
                        ",nama_menu= '" + nama_menu+ '\'' +
                        ",jenis= '" + jenis+ '\'' +
                        ",harga= '" + harga+ '\'' +
                        ",gambar= '" + gambar+ '\'' +
                        "}";
    }

}
