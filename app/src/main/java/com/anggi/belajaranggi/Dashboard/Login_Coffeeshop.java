package com.anggi.belajaranggi.Dashboard;

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

import com.anggi.belajaranggi.R;
import com.anggi.belajaranggi.Register;
import com.anggi.belajaranggi.Storage.Register_Coffeeshop;
import com.anggi.belajaranggi.Storage.ShareprefManager;
import com.anggi.belajaranggi.Storage.ShareprefManager_Coffeeshop;
import com.anggi.belajaranggi.server.Network;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Login_Coffeeshop extends AppCompatActivity {

    EditText Email, Password;
    Button Login;
    ProgressDialog loading;
    TextView BelumPunyaAkun;
    ShareprefManager_Coffeeshop shareprefManager_coffeeshop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login__coffeeshop);

        Email=(EditText)findViewById(R.id.edtemail);
        Password=(EditText)findViewById(R.id.edtpassword);
        Login=(Button)findViewById(R.id.btnSubmit);
        BelumPunyaAkun=(TextView)findViewById(R.id.belum);
        shareprefManager_coffeeshop = new ShareprefManager_Coffeeshop(Login_Coffeeshop.this);

        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Checkinput();
            }
        });

        BelumPunyaAkun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(Login_Coffeeshop.this, Register_Coffeeshop.class);
                startActivity(intent);
                finish();
            }
        });

        shareprefManager_coffeeshop = new ShareprefManager_Coffeeshop(Login_Coffeeshop.this);
        if (shareprefManager_coffeeshop.getSudahLogin()) {
            startActivity(new Intent(Login_Coffeeshop.this, Menu_Utama.class)
                    .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
            finish();
        }


    }

    private void Checkinput() {
        loading = ProgressDialog.show(Login_Coffeeshop.this,"Loading.....",null,true,true);
        String email = Email.getText().toString();
        String password = Password.getText().toString();
        if (email.isEmpty()) {
            loading.dismiss();
            Toast.makeText(this, "Email Tidak Boleh Kosong", Toast.LENGTH_SHORT).show();
        } else if (password.isEmpty()) {
            loading.dismiss();
            Toast.makeText(this, "Password Tidak Boleh Kosong", Toast.LENGTH_SHORT).show();
        } else {
            RequestLogin();
        }
    }

    private void RequestLogin() {
        String email = Email.getText().toString();
        String password = Password.getText().toString();
        retrofit2.Call<ResponseBody> call = Network.getInstance().getApi().userLoginCoffeeshop(email,password);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()){
                    try {
                        JSONObject jsonRESULTS = new JSONObject(response.body().string());
                        if (jsonRESULTS.getString("success").equals("true")){
                            loading.dismiss();
                            String pesan_login=jsonRESULTS.getString("message");
                            String  id_coffeeshop=jsonRESULTS.getString("id_coffeeshop");
                            String  nama=jsonRESULTS.getString("nama_coffeeshop");
                            String  alamat=jsonRESULTS.getString("alamat");
                            String  no_telpon=jsonRESULTS.getString("no_telpon");
                            String  email=jsonRESULTS.getString("email");
                            String  jam_buka=jsonRESULTS.getString("jam_buka");
                            String  jam_tutup=jsonRESULTS.getString("jam_tutup");
                            String  status=jsonRESULTS.getString("status");
                            String  gambar=jsonRESULTS.getString("gambar");
                            String  longitude=jsonRESULTS.getString("longitude");
                            String  latitude=jsonRESULTS.getString("latitude");
                            String  rating=jsonRESULTS.getString("rating");
                            shareprefManager_coffeeshop.saveSPString(ShareprefManager_Coffeeshop.SP_ID, id_coffeeshop);
                            shareprefManager_coffeeshop.saveSPString(ShareprefManager_Coffeeshop.SP_NAMA, nama);
                            shareprefManager_coffeeshop.saveSPString(ShareprefManager_Coffeeshop.SP_ALAMAT, alamat);
                            shareprefManager_coffeeshop.saveSPString(ShareprefManager_Coffeeshop.SP_NO_TELPON, no_telpon);
                            shareprefManager_coffeeshop.saveSPString(ShareprefManager_Coffeeshop.SP_EMAIL, email);
                            shareprefManager_coffeeshop.saveSPString(ShareprefManager_Coffeeshop.SP_JAMBUKA, jam_buka);
                            shareprefManager_coffeeshop.saveSPString(ShareprefManager_Coffeeshop.SP_JAMTUTUP, jam_tutup);
                            shareprefManager_coffeeshop.saveSPString(ShareprefManager_Coffeeshop.SP_STATUS, status);
                            shareprefManager_coffeeshop.saveSPString(ShareprefManager_Coffeeshop.SP_GAMBAR, gambar);
                            shareprefManager_coffeeshop.saveSPString(ShareprefManager_Coffeeshop.SP_LONGITUDE, longitude);
                            shareprefManager_coffeeshop.saveSPString(ShareprefManager_Coffeeshop.SP_LATITUDE, latitude);
                            shareprefManager_coffeeshop.saveSPString(ShareprefManager_Coffeeshop.SP_RATING, rating);
                            shareprefManager_coffeeshop.saveSPBoolean(ShareprefManager_Coffeeshop.SP_SUDAH_LOGIN, true);
                            Toast.makeText(Login_Coffeeshop.this, ""+pesan_login, Toast.LENGTH_SHORT).show();
                            Intent intent =new Intent(Login_Coffeeshop.this, Menu_Utama.class);
                            startActivity(intent);
                            finish();
                            Log.d("response api", jsonRESULTS.toString());
                        } else if (jsonRESULTS.getString("success").equals("false")){
                            loading.dismiss();
                            String pesan_login=jsonRESULTS.getString("message");
                            Toast.makeText(Login_Coffeeshop.this, ""+pesan_login, Toast.LENGTH_SHORT).show();
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
                    loading.dismiss();
                    e.printStackTrace();
                }

            }
        });
    }

    private void LoginGagal() {
    }

    private void LoginBerhasil() {
    }

    public void goBackMenu(){
        startActivity(new Intent(this, Menu_Awal.class));
        finish();
    }
}


