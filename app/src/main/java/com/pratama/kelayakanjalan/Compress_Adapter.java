package com.pratama.kelayakanjalan;

import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.io.File;
import java.text.DecimalFormat;
import java.util.List;

public class Compress_Adapter extends RecyclerView.Adapter<Compress_Adapter.CompresViewHoler> {

    private List<Compress_Class> mdataList;
    private CompressActivity compressActivity;
    private static DecimalFormat df = new DecimalFormat("0.0");

    public class CompresViewHoler extends RecyclerView.ViewHolder {

        private ImageView img;
        private TextView size, ruas;

        public CompresViewHoler(@NonNull View itemView) {
            super(itemView);

            img = itemView.findViewById(R.id.img);
            size = itemView.findViewById(R.id.size);
            ruas = itemView.findViewById(R.id.ruas);

        }

    }

    public Compress_Adapter(List<Compress_Class> mdataList, CompressActivity compressActivity) {
        this.mdataList = mdataList;
        this.compressActivity = compressActivity;
    }

    @NonNull
    @Override
    public CompresViewHoler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.compress_layout, parent, false);
        Compress_Adapter.CompresViewHoler compress = new Compress_Adapter.CompresViewHoler(view);
        return compress;
    }

    @Override
    public void onBindViewHolder(@NonNull CompresViewHoler holder, int position) {

        Compress_Class currentItem = mdataList.get(position);

        //holder.img.setImageBitmap(BitmapFactory.decodeFile(currentItem.getDIRO()));

        //File file = new File(currentItem.getDIRO());

        //double size = file.length();
        //double kb = size/1024;
        //kb = Double.parseDouble(df.format(kb).replace(",", "."));

        //if(kb >= 1024){
        //    double mb = kb/1024;
        //    mb = Double.parseDouble(df.format(mb).replace(",", "."));

        //    holder.size.setText(String.valueOf(mb)+" MB");
        //} else {
        //    holder.size.setText(String.valueOf(kb)+" KB");
        //}

        holder.ruas.setText(currentItem.getNRU());

    }

    @Override
    public int getItemCount() {
        return mdataList.size();
    }

}
