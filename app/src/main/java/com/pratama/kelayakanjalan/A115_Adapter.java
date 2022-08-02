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

public class A115_Adapter extends RecyclerView.Adapter<A115_Adapter.A115ViewHolder> {

    private A115Activity a115Activity;
    private List<A115_Class> mdataList;
    private double DEV_LAP, DEV_LAP2, DEV_PKJ, STD = 100;
    private String KTG_LAP, KTG_LAP2, KTG_LAPP, KTG_PKJ, KTG_KLF;
    private int PKJ_IN;
    private static DecimalFormat df = new DecimalFormat("0.0");

    public static class A115ViewHolder extends RecyclerView.ViewHolder {
        private TextView lap_data, lap_dev, lap2_data, lap2_dev, lap_hasil, pkj_data, pkj_dev,
                pkj_hasil, rec_data;
        private LinearLayout lap_back, pkj_back;
        private ImageView FT1, FT2;

        public A115ViewHolder(View itemView) {
            super(itemView);

            lap_data = (TextView) itemView.findViewById(R.id.lap_data);
            lap_dev = (TextView) itemView.findViewById(R.id.lap_dev);
            lap2_data = (TextView) itemView.findViewById(R.id.lap2_data);
            lap2_dev = (TextView) itemView.findViewById(R.id.lap2_dev);
            lap_hasil = (TextView) itemView.findViewById(R.id.lap_hasil);

            pkj_data = (TextView) itemView.findViewById(R.id.pkj_data);
            pkj_dev = (TextView) itemView.findViewById(R.id.pkj_dev);
            pkj_hasil = (TextView) itemView.findViewById(R.id.pkj_hasil);

            rec_data = (TextView) itemView.findViewById(R.id.rec_data);

            FT1 = (ImageView) itemView.findViewById(R.id.FT1);
            FT2 = (ImageView) itemView.findViewById(R.id.FT2);

            lap_back = (LinearLayout) itemView.findViewById(R.id.lap_back);
            pkj_back = (LinearLayout) itemView.findViewById(R.id.pkj_back);

        }
    }

    public A115_Adapter(A115Activity a115Activity, List<A115_Class> dataList) {
        this.a115Activity = a115Activity;
        this.mdataList = dataList;
    }

    @NonNull
    @Override
    public A115_Adapter.A115ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.a115_layout, parent, false);
        A115_Adapter.A115ViewHolder rsu = new A115_Adapter.A115ViewHolder(view);
        return rsu;
    }

    @Override
    public void onBindViewHolder(@NonNull A115_Adapter.A115ViewHolder holder, int position) {

        final A115_Class currentItem = mdataList.get(position);

        //Lebar Ambang Pengaman

        try {
            if(currentItem.getLAP() == -1){
                holder.lap_data.setText("-");
                DEV_LAP = -1;
                KTG_LAP = "-";
                holder.lap_dev.setText("-");
            } else {
                holder.lap_data.setText(String.valueOf(currentItem.getLAP())+" m");
                if(currentItem.getLAP() >= 1){
                    DEV_LAP = 0;
                    KTG_LAP = "LF";
                } else {
                    DEV_LAP = (currentItem.getLAP() - 1)/1;
                    DEV_LAP = (DEV_LAP * 100);
                    if(DEV_LAP < 0){
                        DEV_LAP = DEV_LAP * -1;
                    }
                    if(DEV_LAP > 100){
                        DEV_LAP = 100;
                    }
                    DEV_LAP = Double.parseDouble(df.format(DEV_LAP).replace(",", "."));
                    KTG_LAP = "LS";
                }
                holder.lap_dev.setText(String.valueOf(DEV_LAP)+"%");
            }
        } catch (Exception e){
            Toast.makeText(a115Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            if(currentItem.getLAP2() == -1){
                DEV_LAP2 = -1;
                KTG_LAP2 = "-";
                holder.lap2_data.setText("-");
                holder.lap2_dev.setText("-");
            } else {
                DEV_LAP2 = STD - currentItem.getLAP2();
                if(DEV_LAP2 == 0){
                    KTG_LAP2 = "LF";
                } else {
                    KTG_LAP2 = "LS";
                }
                holder.lap2_data.setText(String.valueOf(currentItem.getLAP2())+"%");
                holder.lap2_dev.setText(String.valueOf(DEV_LAP2)+"%");
            }
        } catch (Exception e){
            Toast.makeText(a115Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            if((KTG_LAP.equals("LF") || KTG_LAP.equals("-") || KTG_LAP.equals("LS")) && (KTG_LAP2.equals("LF") || KTG_LAP2.equals("-"))){

                if(KTG_LAP.equals("-") && KTG_LAP2.equals("-")){
                    KTG_LAPP = "-";
                    holder.lap_back.setBackground(a115Activity.getResources().getDrawable(R.drawable.null_style));
                } else {
                    KTG_LAPP = "LF";
                    holder.lap_back.setBackground(a115Activity.getResources().getDrawable(R.drawable.success_style));
                }

            } else {
                KTG_LAPP = "LS";
                holder.lap_back.setBackground(a115Activity.getResources().getDrawable(R.drawable.failed_style));
            }
            holder.lap_hasil.setText(KTG_LAPP);
        } catch (Exception e){
            Toast.makeText(a115Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        //Pengamanan Konstruksi Jalan

        try {
            if(currentItem.getPKJ().equals("Tembok Penahan")){
                PKJ_IN = 1;
                DEV_PKJ = 0;
                KTG_PKJ = "LF";
            } else if(currentItem.getPKJ().equals("Brojong")){
                PKJ_IN = 2;
                DEV_PKJ = 0;
                KTG_PKJ = "LF";
            } else if(currentItem.getPKJ().equals("Tiang")){
                PKJ_IN = 3;
                DEV_PKJ = 0;
                KTG_PKJ = "LF";
            } else if(currentItem.getPKJ().equals("Teknik Pengaturan Tanah")){
                PKJ_IN = 4;
                DEV_PKJ = 0;
                KTG_PKJ = "LF";
            } else if(currentItem.getPKJ().equals("Dinding Penopang Jalan Batu")){
                PKJ_IN = 5;
                DEV_PKJ = 0;
                KTG_PKJ = "LF";
            } else if(currentItem.getPKJ().equals("Tidak Ada, Tidak Diperlukan")){
                PKJ_IN = 6;
                DEV_PKJ = -1;
                KTG_PKJ = "-";
            } else {
                PKJ_IN = 7;
                DEV_PKJ = 100;
                KTG_PKJ = "LS";
            }

            if(PKJ_IN == 6){
                holder.pkj_data.setText("("+String.valueOf(PKJ_IN)+") "+currentItem.getPKJ());
                holder.pkj_dev.setText("-");
                holder.pkj_hasil.setText(KTG_PKJ);
                holder.pkj_back.setBackground(a115Activity.getResources().getDrawable(R.drawable.null_style));
            } else {
                holder.pkj_data.setText("("+String.valueOf(PKJ_IN)+") "+currentItem.getPKJ());
                holder.pkj_dev.setText(String.valueOf(DEV_PKJ)+"%");
                holder.pkj_hasil.setText(KTG_PKJ);
                holder.pkj_back.setBackground(a115Activity.getResources().getDrawable(R.drawable.success_style));
            }
        } catch (Exception e){
            Toast.makeText(a115Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        //KLF

        try {
            if((KTG_LAPP.equals("LF") || KTG_LAPP.equals("-")) && (KTG_PKJ.equals("LF") || KTG_PKJ.equals("-"))){

                if(KTG_LAPP.equals("-") && KTG_PKJ.equals("-")){
                    KTG_KLF = "Tidak Dinilai";
                } else {
                    KTG_KLF = "LF";
                }

            } else {
                KTG_KLF = "LS";
            }
        } catch (Exception e){
            Toast.makeText(a115Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            holder.rec_data.setText(currentItem.getREC());
        } catch (Exception e){
            Toast.makeText(a115Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            holder.FT1.setImageBitmap(BitmapFactory.decodeFile(currentItem.getDIR1()));
        } catch (Exception e){
            Toast.makeText(a115Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            holder.FT2.setImageBitmap(BitmapFactory.decodeFile(currentItem.getDIR2()));
        } catch (Exception e){
            Toast.makeText(a115Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        //FAB UPLOAD

        try {
            if(currentItem.getUPLOAD().equals("F")){
                a115Activity.FAB_UPLOAD.hide();
                a115Activity.UPLOAD.setImageResource(R.drawable.ic_file_upload_white_18dp);
                a115Activity.FAB_UPLOAD.setEnabled(true);
            } else {
                a115Activity.UPLOAD.setImageResource(R.drawable.ic_done_white_18dp);
                a115Activity.FAB_UPLOAD.setEnabled(false);
            }
        } catch (Exception e){
            Toast.makeText(a115Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        //Kirim ke Activity

        try {
            a115Activity.ID = currentItem.getID();
            a115Activity.LAP_TXT = currentItem.getLAP();
            a115Activity.LAP2_TXT = currentItem.getLAP2();
            a115Activity.PKJ_IN = PKJ_IN;
            a115Activity.PKJ_TXT = currentItem.getPKJ();
            a115Activity.DEV_LAP = DEV_LAP;
            a115Activity.DEV_LAP2 = DEV_LAP2;
            a115Activity.DEV_PKJ = DEV_PKJ;
            a115Activity.KTG_LAPP = KTG_LAPP;
            a115Activity.KTG_PKJ = KTG_PKJ;
            a115Activity.REC_TXT = currentItem.getREC();
            a115Activity.DIR1 = currentItem.getDIR1();
            a115Activity.DIR2 = currentItem.getDIR2();
            a115Activity.KTG_KLF = KTG_KLF;

            if(KTG_KLF.equals("LS")){
                a115Activity.klf.setText(KTG_KLF);
                a115Activity.klf.setBackground(a115Activity.getResources().getDrawable(R.drawable.failed_style));
                Animation animation = AnimationUtils.loadAnimation(a115Activity.getApplicationContext(), R.anim.recycle_bottom);
                a115Activity.klf.startAnimation(animation);
            } else if(KTG_KLF.equals("Tidak Dinilai")){
                a115Activity.klf.setText("Tidak Dinilai");
                a115Activity.klf.setBackground(a115Activity.getResources().getDrawable(R.drawable.null_style));
                Animation animation = AnimationUtils.loadAnimation(a115Activity.getApplicationContext(), R.anim.recycle_bottom);
                a115Activity.klf.startAnimation(animation);
            } else {
                a115Activity.klf.setText(KTG_KLF);
                a115Activity.klf.setBackground(a115Activity.getResources().getDrawable(R.drawable.success_style));
                Animation animation = AnimationUtils.loadAnimation(a115Activity.getApplicationContext(), R.anim.recycle_bottom);
                a115Activity.klf.startAnimation(animation);
            }
        } catch (Exception e){
            Toast.makeText(a115Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public int getItemCount() {
        return mdataList.size();
    }
}
