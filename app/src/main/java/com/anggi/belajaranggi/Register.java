package com.anggi.belajaranggi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.anggi.belajaranggi.Dashboard.Menu_login;
import com.anggi.belajaranggi.server.Network;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Register extends AppCompatActivity {

    EditText Fullname, Username, Phonenumber, Email, Password;
    Button Submit;
    ProgressDialog loading;
    TextView SudahPunyaAkun;
//    ProgressDialog loading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Fullname=(EditText)findViewById(R.id.edtfullname);
        Username=(EditText)findViewById(R.id.edtusername);
        Phonenumber=(EditText)findViewById(R.id.edtphonenumber);
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
                Intent intent =new Intent(Register.this,Menu_login.class);
                startActivity(intent);
                finish();
            }
        });

    }

    private void Checkinput() {
        String fullname = Fullname.getText().toString();
        String username = Username.getText().toString();
        String phonenumber = Phonenumber.getText().toString();
        String email = Email.getText().toString();
        String password = Password.getText().toString();
        if (fullname.isEmpty()) {
            loading.dismiss();
            Toast.makeText(this, "Fullname Tidak Boleh Kosong", Toast.LENGTH_SHORT).show();
        } else if (username.isEmpty()) {
            loading.dismiss();
            Toast.makeText(this, "Username Tidak Boleh Kosong", Toast.LENGTH_SHORT).show();
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
        String fullname = Fullname.getText().toString();
        String username = Username.getText().toString();
        String phonenumber = Phonenumber.getText().toString();
        String email = Email.getText().toString();
        String password = Password.getText().toString();
        retrofit2.Call<ResponseBody> call = Network.getInstance().getApi().userRegis(fullname, username, phonenumber, email, password);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()){
                    try {
                        JSONObject jsonRESULTS = new JSONObject(response.body().string());
                        if (jsonRESULTS.getString("success").equals("true")){
                            String pesan_login=jsonRESULTS.getString("message");
                            Toast.makeText(Register.this, ""+pesan_login, Toast.LENGTH_SHORT).show();
                            Intent intent =new Intent(Register.this, Menu_login.class);
                            startActivity(intent);
                            finish();
                            Log.d("response api", jsonRESULTS.toString());
                        } else if (jsonRESULTS.getString("success").equals("true")){
                            String pesan_login=jsonRESULTS.getString("message");
                            Toast.makeText(Register.this, ""+pesan_login, Toast.LENGTH_SHORT).show();
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
