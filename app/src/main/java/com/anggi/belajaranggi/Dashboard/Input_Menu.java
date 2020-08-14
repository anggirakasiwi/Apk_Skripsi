package com.anggi.belajaranggi.Dashboard;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.anggi.belajaranggi.Dashboard.Input_Menu;
import com.anggi.belajaranggi.R;
import com.anggi.belajaranggi.Storage.ShareprefManager;
import com.anggi.belajaranggi.Storage.ShareprefManager_Coffeeshop;
import com.anggi.belajaranggi.server.Adapter.Adapter_Menu;
import com.anggi.belajaranggi.server.ApiServices;
import com.anggi.belajaranggi.server.Menu_Item.Datamenu_Item;
import com.anggi.belajaranggi.server.Network;
import com.anggi.belajaranggi.server.Response.Response_Menu;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Input_Menu extends AppCompatActivity {

    private RecyclerView recyclerView;
    private FloatingActionButton floatingActionButton;

    ShareprefManager_Coffeeshop shareprefManager_coffeeshop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input__menu);

        shareprefManager_coffeeshop=new ShareprefManager_Coffeeshop(this);

        recyclerView = (RecyclerView) findViewById(R.id.list_data);
        GridLayoutManager llm=new GridLayoutManager(Input_Menu.this,1);
        recyclerView.setLayoutManager(llm);
        tampildata();

        floatingActionButton = (FloatingActionButton) findViewById(R.id.fab);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Input_Menu.this,Input_Datamenu.class);
                startActivity(intent);
                finish();
            }
        });

    }

    private void tampildata() {
        String Id=shareprefManager_coffeeshop.getSpId();
        ApiServices api = Network.getInstance().getApi();
        Call<Response_Menu> menuCall = api.tampil_data(Id);
        menuCall.enqueue(new Callback<Response_Menu>() {
            @Override
            public void onResponse(Call<Response_Menu> call, Response<Response_Menu> response) {
                if (response.isSuccessful()){
                    Log.d("response api", response.body().toString());
                    List<Datamenu_Item> data_menu= response.body().getMenu();
                    boolean status = response.body().isStatus();
                    if (status){
                        Adapter_Menu adapter = new Adapter_Menu(Input_Menu.this, data_menu);
                        recyclerView.setAdapter(adapter);
                    } else {
                        Toast.makeText(Input_Menu.this, "Tidak Ada data Menu saat ini", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<Response_Menu> call, Throwable t) {
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
