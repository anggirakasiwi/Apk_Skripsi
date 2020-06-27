package com.anggi.belajaranggi;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DialogFragment;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.anggi.belajaranggi.Dashboard.Menu_Utama;
import com.anggi.belajaranggi.server.Network;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    EditText Username, Password;
    Button Login;
    ProgressDialog loading;
    TextView BelumPunyaAkun;
    ProgressDialog Loading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent=new Intent(MainActivity.this, Register.class);
        startActivity(intent);
        finish();
        Username=(EditText)findViewById(R.id.edtusername);
        Password=(EditText)findViewById(R.id.edtpassword);
        Login=(Button)findViewById(R.id.btnSubmit);
        BelumPunyaAkun=(TextView)findViewById(R.id.belum);
        
        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Checkinput();
            }
        });
        BelumPunyaAkun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(MainActivity.this,Register.class);
                startActivity(intent);
                finish();
            }
        });
        
    }
    private void Checkinput() {
        Loading.show();
        String username = Username.getText().toString();
        String password = Password.getText().toString();
        if (username.isEmpty()) {
            loading.dismiss();
            Toast.makeText(this, "Username Tidak Boleh Kosong", Toast.LENGTH_SHORT).show();
        } else if (password.isEmpty()) {
            loading.dismiss();
            Toast.makeText(this, "Password Tidak Boleh Kosong", Toast.LENGTH_SHORT).show();
        } else {
            RequestLogin();
        }
    }

    private void RequestLogin() {
        String username = Username.getText().toString();
        String password = Password.getText().toString();
        retrofit2.Call<ResponseBody> call = Network.getInstance().getApi().userLogin(username,password);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()){
                    try {
                        JSONObject jsonRESULTS = new JSONObject(response.body().string());
                        if (jsonRESULTS.getString("success").equals("true")){
                            Loading.dismiss();
                            String pesan_login=jsonRESULTS.getString("message");
                            Toast.makeText(MainActivity.this, ""+pesan_login, Toast.LENGTH_SHORT).show();
                            Intent intent =new Intent(MainActivity.this, Menu_Utama.class);
                            startActivity(intent);
                            finish();
                            Log.d("response api", jsonRESULTS.toString());
                        } else if (jsonRESULTS.getString("success").equals("false")){
                            Loading.dismiss();
                            String pesan_login=jsonRESULTS.getString("message");
                            Toast.makeText(MainActivity.this, ""+pesan_login, Toast.LENGTH_SHORT).show();
                            Log.v("ini",pesan_login);
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
                    LoginGagal();
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });
    }

    private void LoginGagal() {
    }

    private void LoginBerhasil() {
    }
}
