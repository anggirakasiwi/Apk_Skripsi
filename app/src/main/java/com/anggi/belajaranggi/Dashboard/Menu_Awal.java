package com.anggi.belajaranggi.Dashboard;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


import com.anggi.belajaranggi.R;
import com.anggi.belajaranggi.Register;

public class Menu_Awal extends AppCompatActivity {
    Button Pengguna, Coffeeshop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu__awal);

        Pengguna=(Button)findViewById(R.id.btnpengguna);
        Coffeeshop=(Button)findViewById(R.id.btncoffeeshop);

        Pengguna.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(Menu_Awal.this, Menu_login.class);
                startActivity(intent);
                finish();
            }
        });

        Coffeeshop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(Menu_Awal.this,Login_Coffeeshop.class);
                startActivity(intent);
                finish();
            }
        });

    }
}
