package com.anggi.belajaranggi.Dashboard;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toolbar;
//import android.support.v7.widget.Toolbar;

import com.anggi.belajaranggi.R;

public class Menu_Utama extends AppCompatActivity {
    CardView data, inputmenu, akun;
    private Toolbar toolbarr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu__utama);
        Toolbar  toolbarr = (Toolbar)findViewById(R.id.toolbar);
//        getSupportActionBar().hide();//Ocultar ActivityBar anterior
//        setSupportActionBar(toolbarr); ;

        data = (CardView)findViewById(R.id.data);
        data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Menu_Utama.this,Data_Coffeeshop.class);
                startActivity(intent);
                finish();
            }
        });

        inputmenu = (CardView)findViewById(R.id.inputmenu);
        inputmenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Menu_Utama.this,Input_Menu.class);
                startActivity(intent);
                finish();
            }
        });

        akun = (CardView)findViewById(R.id.akunprofil);
        akun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Menu_Utama.this,Input_Datamenu.class);
                startActivity(intent);
                finish();
            }
        });

    }
}
