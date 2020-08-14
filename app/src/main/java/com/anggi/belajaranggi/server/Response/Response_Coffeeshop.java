package com.anggi.belajaranggi.server.Response;

import com.anggi.belajaranggi.server.Menu_Item.Coffeeshop_Item;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Response_Coffeeshop {
    @SerializedName("menu")
    private List<Coffeeshop_Item> menu;

    @SerializedName("status")
    private boolean status;

    public void setMenu(List<Coffeeshop_Item> menu) {
        this.menu = menu;
    }

    public List<Coffeeshop_Item> getMenu()
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
