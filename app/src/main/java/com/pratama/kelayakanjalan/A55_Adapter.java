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

public class A55_Adapter extends RecyclerView.Adapter<A55_Adapter.A55ViewHolder> {

    private A55Activity a55Activity;
    private List<A55_Class> mdataList;
    private double DEV_SKML, DEV_PKT, DEV_PKT2, DEV_PSP, DEV_UTR, STD = 100;
    private int SKML_IN;
    private String KTG_SKML, KTG_PKT, KTG_PKT2, KTG_PKTT, KTG_PSP, KTG_UTR, KTG_KLF;
    private static DecimalFormat df = new DecimalFormat("0.0");

    public class A55ViewHolder extends RecyclerView.ViewHolder {

        private TextView skml_data, skml_dev, skml_hasil, pkt_data, pkt_dev, pkt2_data, pkt2_dev,
                pkt_hasil, psp_data, psp_dev, psp_hasil, utr_data, utr_dev, utr_hasil, rec_data;

        private LinearLayout skml_back, pkt_back, psp_back, utr_back;
        private ImageView FT1, FT2;

        public A55ViewHolder(@NonNull View itemView) {
            super(itemView);

            skml_data = (TextView) itemView.findViewById(R.id.skml_data);
            skml_dev = (TextView) itemView.findViewById(R.id.skml_dev);
            skml_hasil = (TextView) itemView.findViewById(R.id.skml_hasil);

            pkt_data = (TextView) itemView.findViewById(R.id.pkt_data);
            pkt_dev = (TextView) itemView.findViewById(R.id.pkt_dev);
            pkt2_data = (TextView) itemView.findViewById(R.id.pkt2_data);
            pkt2_dev = (TextView) itemView.findViewById(R.id.pkt2_dev);
            pkt_hasil = (TextView) itemView.findViewById(R.id.pkt_hasil);

            psp_data = (TextView) itemView.findViewById(R.id.psp_data);
            psp_dev = (TextView) itemView.findViewById(R.id.psp_dev);
            psp_hasil = (TextView) itemView.findViewById(R.id.psp_hasil);

            utr_data = (TextView) itemView.findViewById(R.id.utr_data);
            utr_dev = (TextView) itemView.findViewById(R.id.utr_dev);
            utr_hasil = (TextView) itemView.findViewById(R.id.utr_hasil);

            rec_data = (TextView) itemView.findViewById(R.id.rec_data);

            FT1 = (ImageView) itemView.findViewById(R.id.FT1);
            FT2 = (ImageView) itemView.findViewById(R.id.FT2);

            skml_back = (LinearLayout) itemView.findViewById(R.id.skml_back);
            pkt_back = (LinearLayout) itemView.findViewById(R.id.pkt_back);
            psp_back = (LinearLayout) itemView.findViewById(R.id.psp_back);
            utr_back = (LinearLayout) itemView.findViewById(R.id.utr_back);

        }

    }

    public A55_Adapter(A55Activity a55Activity, List<A55_Class> mdataList) {
        this.a55Activity = a55Activity;
        this.mdataList = mdataList;
    }

    @NonNull
    @Override
    public A55_Adapter.A55ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.a55_layout, parent, false);
        A55_Adapter.A55ViewHolder a55 = new A55_Adapter.A55ViewHolder(view);
        return a55;
    }

    @Override
    public void onBindViewHolder(@NonNull A55_Adapter.A55ViewHolder holder, int position) {

        final A55_Class currentItem = mdataList.get(position);

        //Kebutuhan Manajemen Lalu Lintas

        try {
            if(currentItem.getSKML().equals("Ada")){
                SKML_IN = 1;
                DEV_SKML = 0;
                KTG_SKML = "LF";
                holder.skml_back.setBackground(a55Activity.getResources().getDrawable(R.drawable.success_style));
            } else if(currentItem.getSKML().equals("Tidak Ada")){
                SKML_IN = 2;
                DEV_SKML = 100;
                KTG_SKML = "LS";
                holder.skml_back.setBackground(a55Activity.getResources().getDrawable(R.drawable.failed_style));
            } else {
                SKML_IN = 3;
                DEV_SKML = -1;
                KTG_SKML = "-";
                holder.skml_back.setBackground(a55Activity.getResources().getDrawable(R.drawable.null_style));
            }
            holder.skml_data.setText(String.valueOf(SKML_IN));
            holder.skml_dev.setText(String.valueOf(DEV_SKML)+"%");
            holder.skml_hasil.setText(KTG_SKML);
        } catch (Exception e){
            Toast.makeText(a55Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        //Perkerasan dan Kondisi

        try {
            if(currentItem.getPKT() == -1){
                DEV_PKT = -1;
                KTG_PKT = "-";
                holder.pkt_data.setText("-");
                holder.pkt_dev.setText("-");
            } else {
                DEV_PKT = STD - currentItem.getPKT();
                if(DEV_PKT == 0){
                    KTG_PKT = "LF";
                } else {
                    KTG_PKT = "LS";
                }
                holder.pkt_data.setText(String.valueOf(currentItem.getPKT())+"%");
                holder.pkt_dev.setText(String.valueOf(DEV_PKT)+"%");
            }
        } catch (Exception e){
            Toast.makeText(a55Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            if(currentItem.getPKT2() == -1){
                DEV_PKT2 = -1;
                KTG_PKT2 = "-";
                holder.pkt2_data.setText("-");
                holder.pkt2_dev.setText("-");
            } else {
                DEV_PKT2 = STD - currentItem.getPKT2();
                if(DEV_PKT2 == 0){
                    KTG_PKT2 = "LF";
                } else {
                    KTG_PKT2 = "LS";
                }
                holder.pkt2_data.setText(String.valueOf(currentItem.getPKT2())+"%");
                holder.pkt2_dev.setText(String.valueOf(DEV_PKT2)+"%");
            }
        } catch (Exception e){
            Toast.makeText(a55Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            if((KTG_PKT.equals("LF") || KTG_PKT.equals("-")) && (KTG_PKT2.equals("LF") || KTG_PKT2.equals("-"))){

                if(KTG_PKT.equals("-") && KTG_PKT2.equals("-")){
                    KTG_PKTT = "-";
                    holder.pkt_back.setBackground(a55Activity.getResources().getDrawable(R.drawable.null_style));
                } else {
                    KTG_PKTT = "LF";
                    holder.pkt_back.setBackground(a55Activity.getResources().getDrawable(R.drawable.success_style));
                }

            } else {
                KTG_PKTT = "LS";
                holder.pkt_back.setBackground(a55Activity.getResources().getDrawable(R.drawable.failed_style));
            }
            holder.pkt_hasil.setText(KTG_PKTT);
        } catch (Exception e){
            Toast.makeText(a55Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        //Pemanfaatan oleh selain Pejalan Kaki

        try {
            if(currentItem.getPSP() == -1){
                DEV_PSP = -1;
                KTG_PSP = "-";
                holder.psp_data.setText("-");
                holder.psp_dev.setText("-");
                holder.psp_hasil.setText(KTG_PSP);
                holder.psp_back.setBackground(a55Activity.getResources().getDrawable(R.drawable.null_style));
            } else {
                DEV_PSP = STD - currentItem.getPSP();
                if(DEV_PSP == 0){
                    KTG_PSP = "LF";
                    holder.psp_back.setBackground(a55Activity.getResources().getDrawable(R.drawable.success_style));
                } else {
                    KTG_PSP = "LS";
                    holder.psp_back.setBackground(a55Activity.getResources().getDrawable(R.drawable.failed_style));
                }
                holder.psp_data.setText(String.valueOf(currentItem.getPSP())+"%");
                holder.psp_dev.setText(String.valueOf(DEV_PSP)+"%");
                holder.psp_hasil.setText(KTG_PSP);
            }
        } catch (Exception e){
            Toast.makeText(a55Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        //Utilitas pada Trotoar

        try {
            if(currentItem.getUTR() == -1){
                DEV_UTR = -1;
                KTG_UTR = "-";
                holder.utr_data.setText("-");
                holder.utr_dev.setText("-");
                holder.utr_hasil.setText(KTG_UTR);
                holder.utr_back.setBackground(a55Activity.getResources().getDrawable(R.drawable.null_style));
            } else {
                DEV_UTR = STD - currentItem.getUTR();
                if(DEV_UTR == 0){
                    KTG_UTR = "LF";
                    holder.utr_back.setBackground(a55Activity.getResources().getDrawable(R.drawable.success_style));
                } else {
                    KTG_UTR = "LS";
                    holder.utr_back.setBackground(a55Activity.getResources().getDrawable(R.drawable.failed_style));
                }
                holder.utr_data.setText(String.valueOf(currentItem.getUTR())+"%");
                holder.utr_dev.setText(String.valueOf(DEV_UTR)+"%");
                holder.utr_hasil.setText(KTG_UTR);
            }
        } catch (Exception e){
            Toast.makeText(a55Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        //KLF

        try {
            if((KTG_SKML.equals("LF") || KTG_SKML.equals("-")) && (KTG_PKTT.equals("LF") || KTG_PKTT.equals("-"))
                    && (KTG_PSP.equals("LF") || KTG_PSP.equals("-")) && (KTG_UTR.equals("LF") || KTG_UTR.equals("-"))){

                if(KTG_SKML.equals("-") && KTG_PKTT.equals("-") && KTG_PSP.equals("-") && KTG_UTR.equals("-")){
                    KTG_KLF = "Tidak Dinilai";
                } else {
                    KTG_KLF = "LF";
                }

            } else {
                KTG_KLF = "LS";
            }
        } catch (Exception e){
            Toast.makeText(a55Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            holder.rec_data.setText(currentItem.getREC());
        } catch (Exception e){
            Toast.makeText(a55Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            holder.FT1.setImageBitmap(BitmapFactory.decodeFile(currentItem.getDIR1()));
        } catch (Exception e){
            Toast.makeText(a55Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            holder.FT2.setImageBitmap(BitmapFactory.decodeFile(currentItem.getDIR2()));
        } catch (Exception e){
            Toast.makeText(a55Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        //FAB UPLOAD

        try {
            if(currentItem.getUPLOAD().equals("F")){
                a55Activity.FAB_UPLOAD.hide();
                a55Activity.UPLOAD.setImageResource(R.drawable.ic_file_upload_white_18dp);
                a55Activity.FAB_UPLOAD.setEnabled(true);
            } else {
                a55Activity.UPLOAD.setImageResource(R.drawable.ic_done_white_18dp);
                a55Activity.FAB_UPLOAD.setEnabled(false);
            }
        } catch (Exception e){
            Toast.makeText(a55Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        //Kirim ke Activity

        try {
            a55Activity.ID = currentItem.getID();
            a55Activity.KML_TXT = currentItem.getSKML();
            a55Activity.SKML_IN = SKML_IN;
            a55Activity.PKT_TXT = currentItem.getPKT();
            a55Activity.PKT2_TXT = currentItem.getPKT2();
            a55Activity.PSP_TXT = currentItem.getPSP();
            a55Activity.UTR_TXT = currentItem.getUTR();
            a55Activity.REC_TXT = currentItem.getREC();
            a55Activity.DIR1 = currentItem.getDIR1();
            a55Activity.DIR2 = currentItem.getDIR2();

            a55Activity.DEV_SKML = DEV_SKML;
            a55Activity.DEV_PKT = DEV_PKT;
            a55Activity.DEV_PKT2 = DEV_PKT2;
            a55Activity.DEV_PSP = DEV_PSP;
            a55Activity.DEV_UTR = DEV_UTR;

            a55Activity.KTG_SKML = KTG_SKML;
            a55Activity.KTG_PKTT = KTG_PKTT;
            a55Activity.KTG_PSP = KTG_PSP;
            a55Activity.KTG_UTR = KTG_UTR;
            a55Activity.KTG_KLF = KTG_KLF;

            if(KTG_KLF.equals("LS")){
                a55Activity.klf.setText(KTG_KLF);
                a55Activity.klf.setBackground(a55Activity.getResources().getDrawable(R.drawable.failed_style));
                Animation animation = AnimationUtils.loadAnimation(a55Activity.getApplicationContext(), R.anim.recycle_bottom);
                a55Activity.klf.startAnimation(animation);
            } else if(KTG_KLF.equals("Tidak Dinilai")){
                a55Activity.klf.setText("Tidak Dinilai");
                a55Activity.klf.setBackground(a55Activity.getResources().getDrawable(R.drawable.null_style));
                Animation animation = AnimationUtils.loadAnimation(a55Activity.getApplicationContext(), R.anim.recycle_bottom);
                a55Activity.klf.startAnimation(animation);
            } else {
                a55Activity.klf.setText(KTG_KLF);
                a55Activity.klf.setBackground(a55Activity.getResources().getDrawable(R.drawable.success_style));
                Animation animation = AnimationUtils.loadAnimation(a55Activity.getApplicationContext(), R.anim.recycle_bottom);
                a55Activity.klf.startAnimation(animation);
            }
        } catch (Exception e){
            Toast.makeText(a55Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public int getItemCount() {
        return mdataList.size();
    }

}
