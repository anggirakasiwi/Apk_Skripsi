package com.anggi.belajaranggi.Dashboard;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.anggi.belajaranggi.R;
import com.anggi.belajaranggi.server.Adapter.Adapter_Coffeeshop;
import com.anggi.belajaranggi.server.ApiServices;
import com.anggi.belajaranggi.server.Menu_Item.Coffeeshop_Item;
import com.anggi.belajaranggi.server.Network;
import com.anggi.belajaranggi.server.Response.Response_Coffeeshop;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Data_Coffeeshop extends AppCompatActivity {

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data__coffeeshop);

        recyclerView = (RecyclerView) findViewById(R.id.list_data);
        GridLayoutManager llm=new GridLayoutManager(Data_Coffeeshop.this,1);
        recyclerView.setLayoutManager(llm);
        tampildata();

    }

    private void tampildata() {
        ApiServices api = Network.getInstance().getApi();
        Call<Response_Coffeeshop> menuCall = api.tampil_semuadata();
        menuCall.enqueue(new Callback<Response_Coffeeshop>() {
            @Override
            public void onResponse(Call<Response_Coffeeshop> call, Response<Response_Coffeeshop> response) {
                if (response.isSuccessful()){
                    Log.d("response api", response.body().toString());
                    List<Coffeeshop_Item> data_menu= response.body().getMenu();
                    boolean status = response.body().isStatus();
                    if (status){
                        Adapter_Coffeeshop adapter = new Adapter_Coffeeshop(Data_Coffeeshop.this, data_menu);
                        recyclerView.setAdapter(adapter);
                    } else {
                        Toast.makeText(Data_Coffeeshop.this, "Tidak Ada data Menu saat ini", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<Response_Coffeeshop> call, Throwable t) {
                // print ke log jika Error
                t.printStackTrace();
            }
        });
    }
    public void onBackPressed() {
        super.onBackPressed();
        goBackMenu();
    }

    public void goBackMenu(){
        startActivity(new Intent(this, Menu_Utama.class));
        finish();
    }
}
