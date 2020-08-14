package com.anggi.belajaranggi.Dashboard;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.anggi.belajaranggi.R;
import com.anggi.belajaranggi.Storage.Register_Coffeeshop;
import com.anggi.belajaranggi.server.ApiServices;
import com.anggi.belajaranggi.server.Network;
import com.anggi.belajaranggi.server.Response.ApiConfig;
import com.anggi.belajaranggi.server.Response.AppConfig;
import com.anggi.belajaranggi.server.Response.ServerResponse;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Input_Datamenu extends AppCompatActivity {
    String mediaPath, mediaPath1;
    EditText NamaMenu, Jenis, Harga,namaGambar;
    Button Submit, Batal, Upload;
    ProgressDialog loading;
    ImageView gambarmenu;
//    private Dialog progressDialog;
    String[] mediaColumns = {MediaStore.Video.Media._ID};
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input__datamenu);
        progressDialog =new ProgressDialog(Input_Datamenu.this);
        progressDialog.setMessage("Uploading...");

        NamaMenu=(EditText)findViewById(R.id.nama_menu);
        gambarmenu=(ImageView)findViewById(R.id.gambarMenu);
        Jenis=(EditText)findViewById(R.id.jenis);
       namaGambar=(EditText)findViewById(R.id.namagambar);
        Harga=(EditText)findViewById(R.id.harga);
        Submit=(Button)findViewById(R.id.btnSubmit);
        Batal=(Button)findViewById(R.id.btnBatal);
        Upload=(Button)findViewById(R.id.btnupload);

        Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uploadFile();
            }
        });

        Batal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(Input_Datamenu.this,Input_Menu.class);
                startActivity(intent);
                finish();
            }
        });

        Upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(galleryIntent, 0);
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        try {
            // When an Image is picked
            if (requestCode == 0 && resultCode == RESULT_OK && null != data) {

                // Get the Image from data
                Uri selectedImage = data.getData();
                String[] filePathColumn = {MediaStore.Images.Media.DATA};

                Cursor cursor = getContentResolver().query(selectedImage, filePathColumn, null, null, null);
                assert cursor != null;
                cursor.moveToFirst();

                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                mediaPath = cursor.getString(columnIndex);
                namaGambar.setText(mediaPath);
                // Set the Image in ImageView for Previewing the Media
                gambarmenu.setImageBitmap(BitmapFactory.decodeFile(mediaPath));
                cursor.close();

            } // When an Video is picked
            else if (requestCode == 1 && resultCode == RESULT_OK && null != data) {

                // Get the Video from data
                Uri selectedVideo = data.getData();
                String[] filePathColumn = {MediaStore.Video.Media.DATA};

                Cursor cursor = getContentResolver().query(selectedVideo, filePathColumn, null, null, null);
                assert cursor != null;
                cursor.moveToFirst();

                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);

//                mediaPath1 = cursor.getString(columnIndex);
//                str2.setText(mediaPath1);
//                // Set the Video Thumb in ImageView Previewing the Media
//                imgView.setImageBitmap(getThumbnailPathForLocalFile(MainActivity.this, selectedVideo));
                cursor.close();

            } else {
                Toast.makeText(this, "You haven't picked Image/Video", Toast.LENGTH_LONG).show();
            }
        } catch (Exception e) {
            Toast.makeText(this, "Something went wrong", Toast.LENGTH_LONG).show();
        }

    }
    // Providing Thumbnail For Selected Image
    public Bitmap getThumbnailPathForLocalFile(Activity context, Uri fileUri) {
        long fileId = getFileId(context, fileUri);
        return MediaStore.Video.Thumbnails.getThumbnail(context.getContentResolver(),
                fileId, MediaStore.Video.Thumbnails.MICRO_KIND, null);
    }
    // Getting Selected File ID
    public long getFileId(Activity context, Uri fileUri) {
        Cursor cursor = context.managedQuery(fileUri, mediaColumns, null, null, null);
        if (cursor.moveToFirst()) {
            int columnIndex = cursor.getColumnIndexOrThrow(MediaStore.Video.Media._ID);
            return cursor.getInt(columnIndex);
        }
        return 0;
    }

    private void Checkinput() {

        String nama_menu = NamaMenu.getText().toString();
        String jenis = Jenis.getText().toString();
        String harga = Harga.getText().toString();
        if (nama_menu.isEmpty()) {
            loading.dismiss();
            Toast.makeText(this, "Nama Menu Tidak Boleh Kosong", Toast.LENGTH_SHORT).show();
        } else if (jenis.isEmpty()) {
            loading.dismiss();
            Toast.makeText(this, "Jenis Menu Tidak Boleh Kosong", Toast.LENGTH_SHORT).show();
        } else if (harga.isEmpty()) {
            loading.dismiss();
            Toast.makeText(this, "Harga Menu Tidak Boleh Kosong", Toast.LENGTH_SHORT).show();
        } else {
//            uploadFile();
        }
    }

    private void uploadFile() {
        progressDialog.show();
        // Map is used to multipart the file using okhttp3.RequestBody
        File file = new File(String.valueOf(mediaPath));

        // Parsing any Media type file
        RequestBody requestBody = RequestBody.create(MediaType.parse("*/*"), file);
        MultipartBody.Part fileToUpload = MultipartBody.Part.createFormData("file", file.getName(), requestBody);
        RequestBody filename = RequestBody.create(MediaType.parse("text/plain"), file.getName());
        ApiConfig getResponse = AppConfig.getRetrofit().create(ApiConfig.class);
        Call<ResponseBody> call = getResponse.uploadFile(fileToUpload, filename);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                Toast.makeText(Input_Datamenu.this, ""+response, Toast.LENGTH_SHORT).show();
                ResponseBody responseBody=response.body();
                Log.v("data",responseBody.toString());
                if (response.isSuccessful()){
                    try {
                        JSONObject jsonRESULTS = new JSONObject(response.body().string());
                        if (jsonRESULTS.getString("success").equals("true")){
                            String pesan_login=jsonRESULTS.getString("message");
                            Toast.makeText(Input_Datamenu.this, ""+pesan_login, Toast.LENGTH_SHORT).show();
                            progressDialog.dismiss();
//                            Intent intent =new Intent(Input_Datamenu.this, Menu_Utama.class);
//                            startActivity(intent);
//                            finish();
                            Log.d("response api", jsonRESULTS.toString());
                        } else if (jsonRESULTS.getString("success").equals("true")){
                            String pesan_login=jsonRESULTS.getString("message");
                            Toast.makeText(Input_Datamenu.this, ""+pesan_login, Toast.LENGTH_SHORT).show();
                            Log.v("ini",pesan_login);
                            progressDialog.dismiss();
//                            InputGagal();
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
                    Log.v("response:","gagal");
                    InputGagal();
                    progressDialog.dismiss();
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });

    }

    private void RequestMenu(String nama_gambar) {
        String nama_menu = NamaMenu.getText().toString();
        String jenis = Jenis.getText().toString();
        String harga = Harga.getText().toString();
        retrofit2.Call<ResponseBody> call = Network.getInstance().getApi().userInput_Menu(nama_menu, jenis, harga,nama_gambar);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()){
                    try {
                        JSONObject jsonRESULTS = new JSONObject(response.body().string());
                        if (jsonRESULTS.getString("success").equals("true")){
                            String pesan_login=jsonRESULTS.getString("message");
                            Toast.makeText(Input_Datamenu.this, ""+pesan_login, Toast.LENGTH_SHORT).show();
                            Intent intent =new Intent(Input_Datamenu.this, Menu_Utama.class);
                            startActivity(intent);
                            finish();
                            Log.d("response api", jsonRESULTS.toString());
                        } else if (jsonRESULTS.getString("success").equals("true")){
                            String pesan_login=jsonRESULTS.getString("message");
                            Toast.makeText(Input_Datamenu.this, ""+pesan_login, Toast.LENGTH_SHORT).show();
                            Log.v("ini",pesan_login);
                            InputGagal();
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
                    InputGagal();
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });

    }

    private void InputGagal() {
        Toast.makeText(this, "GAGAL", Toast.LENGTH_SHORT).show();
    }

    public void goBackMenu(){
        startActivity(new Intent(this, Input_Menu.class));
        finish();
    }
}

