package com.pratama.kelayakanjalan;

import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class A141_Adapter extends RecyclerView.Adapter<A141_Adapter.A141ViewHolder> {

    private A141Activity a141Activity;
    private List<A141_Class> mdataList;
    private double DEV_OKVL, STD = 100, DEV_OKVM;
    private String KTG_OKVL, KTG_OKVM, KTG_KLF;

    public static class A141ViewHolder extends RecyclerView.ViewHolder {

        private TextView okvl_data, okvl_dev, okvl_hasil, okvm_data, okvm_dev, okvm_hasil, rec_data;
        private LinearLayout okvl_back, okvm_back;
        private ImageView FT1, FT2, FT3;

        public A141ViewHolder(View itemView) {
            super(itemView);

            okvl_data = (TextView) itemView.findViewById(R.id.okvl_data);
            okvl_dev = (TextView) itemView.findViewById(R.id.okvl_dev);
            okvl_hasil = (TextView) itemView.findViewById(R.id.okvl_hasil);
            okvm_data = (TextView) itemView.findViewById(R.id.okvm_data);
            okvm_dev = (TextView) itemView.findViewById(R.id.okvm_dev);
            okvm_hasil = (TextView) itemView.findViewById(R.id.okvm_hasil);

            rec_data = (TextView) itemView.findViewById(R.id.rec_data);

            FT1 = (ImageView) itemView.findViewById(R.id.FT1);
            FT2 = (ImageView) itemView.findViewById(R.id.FT2);
            FT3 = (ImageView) itemView.findViewById(R.id.FT3);

            okvl_back = (LinearLayout) itemView.findViewById(R.id.okvl_back);
            okvm_back = (LinearLayout) itemView.findViewById(R.id.okvm_back);

        }
    }

    public A141_Adapter(A141Activity a141Activity, List<A141_Class> dataList) {
        this.a141Activity = a141Activity;
        this.mdataList = dataList;
    }

    @NonNull
    @Override
    public A141_Adapter.A141ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.a141_layout, parent, false);
        A141_Adapter.A141ViewHolder rsu = new A141_Adapter.A141ViewHolder(view);
        return rsu;
    }

    @Override
    public void onBindViewHolder(@NonNull A141_Adapter.A141ViewHolder holder, int position) {

        final A141_Class currentItem = mdataList.get(position);

        //Overlaping Kurva Vertikal pada Jalan Lurus

        try {
            if(currentItem.getOKVL() == -1){
                holder.okvl_data.setText("-");
                DEV_OKVL = -1;
                KTG_OKVL = "-";
                holder.okvl_dev.setText("-");
                holder.okvl_hasil.setText(KTG_OKVL);
                holder.okvl_back.setBackground(a141Activity.getResources().getDrawable(R.drawable.null_style));
            } else {
                holder.okvl_data.setText(String.valueOf(currentItem.getOKVL())+"%");
                DEV_OKVL = STD - currentItem.getOKVL();
                if(DEV_OKVL == 0){
                    KTG_OKVL = "LF";
                    holder.okvl_back.setBackground(a141Activity.getResources().getDrawable(R.drawable.success_style));
                } else {
                    KTG_OKVL = "LS";
                    holder.okvl_back.setBackground(a141Activity.getResources().getDrawable(R.drawable.failed_style));
                }
                holder.okvl_dev.setText(String.valueOf(DEV_OKVL)+"%");
                holder.okvl_hasil.setText(KTG_OKVL);
            }
        } catch (Exception e){
            Toast.makeText(a141Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        //Overlaping Kurva Vertikal pada Jalan Menikung

        try {
            if(currentItem.getOKVM() == -1){
                holder.okvm_data.setText("-");
                DEV_OKVM = -1;
                KTG_OKVM = "-";
                holder.okvm_dev.setText("-");
                holder.okvm_hasil.setText(KTG_OKVM);
                holder.okvm_back.setBackground(a141Activity.getResources().getDrawable(R.drawable.null_style));
            } else {
                holder.okvm_data.setText(String.valueOf(currentItem.getOKVM())+"%");
                DEV_OKVM = STD - currentItem.getOKVM();
                if(DEV_OKVM == 0){
                    KTG_OKVM = "LF";
                    holder.okvm_back.setBackground(a141Activity.getResources().getDrawable(R.drawable.success_style));
                } else {
                    KTG_OKVM = "LS";
                    holder.okvm_back.setBackground(a141Activity.getResources().getDrawable(R.drawable.failed_style));
                }
                holder.okvm_dev.setText(String.valueOf(DEV_OKVM)+"%");
                holder.okvm_hasil.setText(KTG_OKVM);
            }
        } catch (Exception e){
            Toast.makeText(a141Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        //KLF

        try {
            if((KTG_OKVL.equals("LF") || KTG_OKVL.equals("-")) && (KTG_OKVM.equals("LF") || KTG_OKVM.equals("-"))){

                if(KTG_OKVL.equals("-") && KTG_OKVM.equals("-")){
                    KTG_KLF = "Tidak Dinilai";
                } else {
                    KTG_KLF = "LF";
                }

            }  else {
                KTG_KLF = "LS";
            }
        } catch (Exception e){
            Toast.makeText(a141Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            holder.rec_data.setText(currentItem.getREC());
        } catch (Exception e){
            Toast.makeText(a141Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            holder.FT1.setImageBitmap(BitmapFactory.decodeFile(currentItem.getDIR1()));
        } catch (Exception e){
            Toast.makeText(a141Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            holder.FT2.setImageBitmap(BitmapFactory.decodeFile(currentItem.getDIR2()));
        } catch (Exception e){
            Toast.makeText(a141Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            holder.FT3.setImageBitmap(BitmapFactory.decodeFile(currentItem.getDIR3()));
        } catch (Exception e){
            Toast.makeText(a141Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        //FAB UPLOAD

        try {
            if(currentItem.getUPLOAD().equals("F")){
                a141Activity.FAB_UPLOAD.hide();
                a141Activity.UPLOAD.setImageResource(R.drawable.ic_file_upload_white_18dp);
                a141Activity.FAB_UPLOAD.setEnabled(true);
            } else {
                a141Activity.UPLOAD.setImageResource(R.drawable.ic_done_white_18dp);
                a141Activity.FAB_UPLOAD.setEnabled(false);
            }
        } catch (Exception e){
            Toast.makeText(a141Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        //Kirim ke Activity

        try {
            a141Activity.ID = currentItem.getID();
            a141Activity.OKVL_TXT = currentItem.getOKVL();
            a141Activity.OKVM_TXT = currentItem.getOKVM();
            a141Activity.REC_TXT = currentItem.getREC();
            a141Activity.DIR1 = currentItem.getDIR1();
            a141Activity.DIR2 = currentItem.getDIR2();
            a141Activity.DIR3 = currentItem.getDIR3();

            a141Activity.DEV_OKVL = DEV_OKVL;
            a141Activity.DEV_OKVM = DEV_OKVM;

            a141Activity.KTG_OKVL = KTG_OKVL;
            a141Activity.KTG_OKVM = KTG_OKVM;
            a141Activity.KTG_KLF = KTG_KLF;

            if(KTG_KLF.equals("LS")){
                a141Activity.klf.setText(KTG_KLF);
                a141Activity.klf.setBackground(a141Activity.getResources().getDrawable(R.drawable.failed_style));
                Animation animation = AnimationUtils.loadAnimation(a141Activity.getApplicationContext(), R.anim.recycle_bottom);
                a141Activity.klf.startAnimation(animation);
            } else if(KTG_KLF.equals("Tidak Dinilai")){
                a141Activity.klf.setText("Tidak Dinilai");
                a141Activity.klf.setBackground(a141Activity.getResources().getDrawable(R.drawable.null_style));
                Animation animation = AnimationUtils.loadAnimation(a141Activity.getApplicationContext(), R.anim.recycle_bottom);
                a141Activity.klf.startAnimation(animation);
            } else {
                a141Activity.klf.setText(KTG_KLF);
                a141Activity.klf.setBackground(a141Activity.getResources().getDrawable(R.drawable.success_style));
                Animation animation = AnimationUtils.loadAnimation(a141Activity.getApplicationContext(), R.anim.recycle_bottom);
                a141Activity.klf.startAnimation(animation);
            }
        } catch (Exception e){
            Toast.makeText(a141Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public int getItemCount() {
        return mdataList.size();
    }
}
