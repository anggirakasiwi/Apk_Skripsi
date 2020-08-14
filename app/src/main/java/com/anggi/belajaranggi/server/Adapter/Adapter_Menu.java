package com.anggi.belajaranggi.server.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.anggi.belajaranggi.R;
import com.anggi.belajaranggi.server.Menu_Item.Coffeeshop_Item;
import com.anggi.belajaranggi.server.Menu_Item.Datamenu_Item;
import com.anggi.belajaranggi.server.Network;
import com.squareup.picasso.Picasso;

import java.util.List;

public class Adapter_Menu extends RecyclerView.Adapter<Adapter_Menu.MyViewHolder>{
    Context context;
    List<Datamenu_Item> menu;



    public Adapter_Menu(Context context, List<Datamenu_Item> data_menu) {
        this.context = context;
        this.menu= data_menu;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_menu, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        // Set widget
        holder.nama.setText(menu.get(position).getNama_menu());
        holder.jenis.setText(menu.get(position).getJenis());
        holder.harga.setText(menu.get(position).getHarga());
        final String urlGambar = Network.BASE_URL+"/Images/" + menu.get(position).getGambar();
        Picasso.with(context).load(urlGambar).into(holder.gambar_menu);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//
//                Intent varIntent = new Intent(context, Detail_Menu.class);
//                varIntent.putExtra("ID", menu.get(position).getId());
//                varIntent.putExtra("NAMA", menu.get(position).getNama());
//                varIntent.putExtra("HARGA", menu.get(position).getHarga());
//                varIntent.putExtra("DESKRIPSI", menu.get(position).getDeskripsi());
//                varIntent.putExtra("GAMBAR_MENU", urlGambar);
//                varIntent.putExtra("GAMBAR", menu.get(position).getFoto());
//                context.startActivity(varIntent);
            }
        });
    }
    @Override
    public int getItemCount() {
        return menu.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView gambar_menu;
        TextView nama, jenis, harga;
        public MyViewHolder(View itemView) {
            super(itemView);
            gambar_menu = (ImageView) itemView.findViewById(R.id.gambar);
            nama = (TextView) itemView.findViewById(R.id.nama_menu);
            jenis = (TextView) itemView.findViewById(R.id.jenis);
            harga = (TextView) itemView.findViewById(R.id.harga);
        }
    }
}
