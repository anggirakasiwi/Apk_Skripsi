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

import com.anggi.belajaranggi.MainActivity;
import com.anggi.belajaranggi.R;
import com.anggi.belajaranggi.Register;
import com.anggi.belajaranggi.Storage.ShareprefManager;
import com.anggi.belajaranggi.server.Network;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Menu_login extends AppCompatActivity {
    EditText Username, Password;
    Button Login;
    ProgressDialog loading;
    TextView BelumPunyaAkun;
    ShareprefManager sharePrefManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_login);

//        Intent intent=new Intent(Menu_login.this, Register.class);
//        startActivity(intent);
//        finish();
        Username=(EditText)findViewById(R.id.edtusername);
        Password=(EditText)findViewById(R.id.edtpassword);
        Login=(Button)findViewById(R.id.btnSubmit);
        BelumPunyaAkun=(TextView)findViewById(R.id.belum);
        sharePrefManager = new ShareprefManager(Menu_login.this);

        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Checkinput();
            }
        });

        BelumPunyaAkun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(Menu_login.this,Register.class);
                startActivity(intent);
                finish();
            }
        });

        sharePrefManager = new ShareprefManager(Menu_login.this);
        if (sharePrefManager.getSudahLogin()){
            startActivity(new Intent(Menu_login.this, Dashboard.class)
                    .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
            finish();
        }

    }
    private void Checkinput() {
        loading = ProgressDialog.show(Menu_login.this,"Loading.....",null,true,true);
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
                            loading.dismiss();
                            String pesan_login=jsonRESULTS.getString("message");
                            String  nama=jsonRESULTS.getString("nama");
                            String  email=jsonRESULTS.getString("username");
                            sharePrefManager.saveSPString(ShareprefManager.SP_NAMA, nama);
                            sharePrefManager.saveSPString(ShareprefManager.SP_EMAIL, email);
                            sharePrefManager.saveSPBoolean(ShareprefManager.SP_SUDAH_LOGIN, true);
                            Toast.makeText(Menu_login.this, ""+pesan_login, Toast.LENGTH_SHORT).show();
                            Intent intent =new Intent(Menu_login.this, Menu_Utama.class);
                            startActivity(intent);
                            finish();
                            Log.d("response api", jsonRESULTS.toString());
                        } else if (jsonRESULTS.getString("success").equals("false")){
                            loading.dismiss();
                            String pesan_login=jsonRESULTS.getString("message");
                            Toast.makeText(Menu_login.this, ""+pesan_login, Toast.LENGTH_SHORT).show();
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
