package com.anggi.belajaranggi.Storage;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.anggi.belajaranggi.Dashboard.Login_Coffeeshop;
import com.anggi.belajaranggi.Dashboard.Menu_login;
import com.anggi.belajaranggi.R;
import com.anggi.belajaranggi.Register;
import com.anggi.belajaranggi.server.Network;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Register_Coffeeshop extends AppCompatActivity {

    EditText NamaCoffeeshop, Alamat, Phonenumber, Email, Password;
    Button Submit;
    ProgressDialog loading;
    TextView SudahPunyaAkun;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register__coffeeshop);

        NamaCoffeeshop=(EditText)findViewById(R.id.edtnama_coffeeshop);
        Alamat=(EditText)findViewById(R.id.edtalamat);
        Phonenumber=(EditText)findViewById(R.id.edtno_telpon);
        Email=(EditText)findViewById(R.id.edtemail);
        Password=(EditText)findViewById(R.id.edtPassword);
        Submit=(Button)findViewById(R.id.btnSubmit);
        SudahPunyaAkun=(TextView)findViewById(R.id.sudah);

        Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Checkinput();
            }
        });

        SudahPunyaAkun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(Register_Coffeeshop.this,Login_Coffeeshop.class);
                startActivity(intent);
                finish();
            }
        });

    }

    private void Checkinput() {
        String nama_coffeeshop = NamaCoffeeshop.getText().toString();
        String alamat = Alamat.getText().toString();
        String phonenumber = Phonenumber.getText().toString();
        String email = Email.getText().toString();
        String password = Password.getText().toString();
        if (nama_coffeeshop.isEmpty()) {
            loading.dismiss();
            Toast.makeText(this, "Nama Coffee Shop Tidak Boleh Kosong", Toast.LENGTH_SHORT).show();
        } else if (alamat.isEmpty()) {
            loading.dismiss();
            Toast.makeText(this, "Alamat Tidak Boleh Kosong", Toast.LENGTH_SHORT).show();
        } else if (phonenumber.isEmpty()) {
            loading.dismiss();
            Toast.makeText(this, "Phone Number Tidak Boleh Kosong", Toast.LENGTH_SHORT).show();
        } else if (email.isEmpty()) {
            loading.dismiss();
            Toast.makeText(this, "Email Address Tidak Boleh Kosong", Toast.LENGTH_SHORT).show();
        } else if (password.isEmpty()) {
            loading.dismiss();
            Toast.makeText(this, "Password Tidak Boleh Kosong", Toast.LENGTH_SHORT).show();
        } else {
            RequestRegis();
        }
    }

    private void RequestRegis() {
        String nama_coffeeshop = NamaCoffeeshop.getText().toString();
        String alamat = Alamat.getText().toString();
        String phonenumber = Phonenumber.getText().toString();
        String email = Email.getText().toString();
        String password = Password.getText().toString();
        retrofit2.Call<ResponseBody> call = Network.getInstance().getApi().userRegisCoffeeshop(nama_coffeeshop, alamat, phonenumber, email, password);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()){
                    try {
                        JSONObject jsonRESULTS = new JSONObject(response.body().string());
                        if (jsonRESULTS.getString("success").equals("true")){
                            String pesan_login=jsonRESULTS.getString("message");
                            Toast.makeText(Register_Coffeeshop.this, ""+pesan_login, Toast.LENGTH_SHORT).show();
                            Intent intent =new Intent(Register_Coffeeshop.this, Login_Coffeeshop.class);
                            startActivity(intent);
                            finish();
                            Log.d("response api", jsonRESULTS.toString());
                        } else if (jsonRESULTS.getString("success").equals("true")){
                            String pesan_login=jsonRESULTS.getString("message");
                            Toast.makeText(Register_Coffeeshop.this, ""+pesan_login, Toast.LENGTH_SHORT).show();
                            Log.v("ini",pesan_login);
                            RegisGagal();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                }
            }
            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                try {
                    RegisGagal();
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });
    }

    private void RegisGagal() {
    }
}