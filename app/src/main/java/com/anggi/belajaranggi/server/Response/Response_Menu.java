package com.anggi.belajaranggi.server.Response;

import com.anggi.belajaranggi.server.Menu_Item.Datamenu_Item;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Response_Menu {
    @SerializedName("menu")
    private List<Datamenu_Item> menu;

    @SerializedName("status")
    private boolean status;

    public void setMenu(List<Datamenu_Item> menu) {
        this.menu = menu;
    }

    public List<Datamenu_Item> getMenu()
    {
        return menu;
    }

    public void setStatus(boolean status){

        this.status = status;
    }

    public boolean isStatus(){

        return status;
    }

    @Override
    public String toString(){
        return
                "Response_Menu{" +
                        "menu = '" + menu+ '\'' +
                        ",status = '" + status + '\'' +
                        "}";
    }
}
