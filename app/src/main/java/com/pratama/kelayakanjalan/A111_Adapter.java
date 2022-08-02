package com.pratama.kelayakanjalan;

import android.graphics.BitmapFactory;
import android.util.Log;
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

public class A111_Adapter extends RecyclerView.Adapter<A111_Adapter.A111ViewHolder> {

    private A111Activity a111Activity;
    private List<A111_Class> mdataList;
    private double DEV_ALL, DEV_JLA, DEV_LLA, DEV_KLL, DEV_KML;
    private String KTG_ALL, KTG_JLA, KTG_LLA, KTG_KLL, KTG_KML, KTG_KLF;
    private double STD = 100;
    private static DecimalFormat df = new DecimalFormat("0.0");

    public static class A111ViewHolder extends RecyclerView.ViewHolder {

        private TextView fj_data, fj_dev, fj_hasil, all_data, all_dev, all_hasil, jla_data, jla_dev, jla_hasil, lla_data, lla_dev, lla_hasil,
                kll_data, kll_dev, kll_hasil, kml_data, kml_dev, kml_hasil, rec_data;
        private LinearLayout fj_back, all_back, jla_back, lla_back, kll_back, kml_back;
        private ImageView FT1, FT2, FT3;

        public A111ViewHolder(View itemView) {
            super(itemView);

            fj_data = (TextView) itemView.findViewById(R.id.fj_data);
            fj_dev = (TextView) itemView.findViewById(R.id.fj_dev);
            fj_hasil = (TextView) itemView.findViewById(R.id.fj_hasil);
            all_data = (TextView) itemView.findViewById(R.id.all_data);
            all_dev = (TextView) itemView.findViewById(R.id.all_dev);
            all_hasil = (TextView) itemView.findViewById(R.id.all_hasil);
            jla_data = (TextView) itemView.findViewById(R.id.jla_data);
            jla_dev = (TextView) itemView.findViewById(R.id.jla_dev);
            jla_hasil = (TextView) itemView.findViewById(R.id.jla_hasil);
            lla_data = (TextView) itemView.findViewById(R.id.lla_data);
            lla_dev = (TextView) itemView.findViewById(R.id.lla_dev);
            lla_hasil = (TextView) itemView.findViewById(R.id.lla_hasil);
            kll_data = (TextView) itemView.findViewById(R.id.kll_data);
            kll_dev = (TextView) itemView.findViewById(R.id.kll_dev);
            kll_hasil = (TextView) itemView.findViewById(R.id.kll_hasil);
            kml_data = (TextView) itemView.findViewById(R.id.kml_data);
            kml_dev = (TextView) itemView.findViewById(R.id.kml_dev);
            kml_hasil = (TextView) itemView.findViewById(R.id.kml_hasil);
            rec_data = (TextView) itemView.findViewById(R.id.rec_data);

            FT1 = (ImageView) itemView.findViewById(R.id.FT1);
            FT2 = (ImageView) itemView.findViewById(R.id.FT2);
            FT3 = (ImageView) itemView.findViewById(R.id.FT3);

            fj_back = (LinearLayout) itemView.findViewById(R.id.fj_back);
            all_back = (LinearLayout) itemView.findViewById(R.id.all_back);
            jla_back = (LinearLayout) itemView.findViewById(R.id.jla_back);
            lla_back = (LinearLayout) itemView.findViewById(R.id.lla_back);
            kll_back = (LinearLayout) itemView.findViewById(R.id.kll_back);
            kml_back = (LinearLayout) itemView.findViewById(R.id.kml_back);

        }
    }

    public A111_Adapter(A111Activity a111Activity, List<A111_Class> dataList) {
        this.a111Activity = a111Activity;
        this.mdataList = dataList;
    }

    @NonNull
    @Override
    public A111ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.a111_layout, parent, false);
        A111_Adapter.A111ViewHolder rsu = new A111_Adapter.A111ViewHolder(view);
        return rsu;
    }

    @Override
    public void onBindViewHolder(@NonNull A111ViewHolder holder, int position) {

        final A111_Class currentItem = mdataList.get(position);

        //Fungsi Jalan

        try {
            holder.fj_data.setText(currentItem.getFJL());
        } catch (Exception e){
            Toast.makeText(a111Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        //Arus Lalu Lintas yang dilayani

        try {
            if(currentItem.getALL() == -1){
                holder.all_data.setText("-");
                DEV_ALL = -1;
                KTG_ALL = "-";
                holder.all_dev.setText("-");
                holder.all_hasil.setText(KTG_ALL);
                holder.all_back.setBackground(a111Activity.getResources().getDrawable(R.drawable.null_style));
            } else {
                holder.all_data.setText(String.valueOf(currentItem.getALL()));
                if(a111Activity.KPR.equals("Jalan Bebas Hambatan (JBH)") && a111Activity.MJL.equals("Datar (D)")){
                    if(currentItem.getALL() <= 78000){
                        DEV_ALL = 0;
                        KTG_ALL = "LF";
                        holder.all_dev.setText(String.valueOf(DEV_ALL)+"%");
                        holder.all_hasil.setText(KTG_ALL);
                        holder.all_back.setBackground(a111Activity.getResources().getDrawable(R.drawable.success_style));
                    } else {
                        DEV_ALL = (currentItem.getALL() - 78000)/78000;
                        DEV_ALL = (DEV_ALL * 100);
                        if(DEV_ALL < 0){
                            DEV_ALL = DEV_ALL * -1;
                        }
                        if(DEV_ALL > 100){
                            DEV_ALL = 100;
                        }
                        DEV_ALL = Double.parseDouble(df.format(DEV_ALL).replace(",", "."));
                        KTG_ALL = "LS";
                        holder.all_dev.setText(String.valueOf(DEV_ALL)+"%");
                        holder.all_hasil.setText(KTG_ALL);
                        holder.all_back.setBackground(a111Activity.getResources().getDrawable(R.drawable.failed_style));
                    }
                } else if(a111Activity.KPR.equals("Jalan Bebas Hambatan (JBH)") && a111Activity.MJL.equals("Bukit (B)")){
                    if(currentItem.getALL() <= 77000){
                        DEV_ALL = 0;
                        KTG_ALL = "LF";
                        holder.all_dev.setText(String.valueOf(DEV_ALL)+"%");
                        holder.all_hasil.setText(KTG_ALL);
                        holder.all_back.setBackground(a111Activity.getResources().getDrawable(R.drawable.success_style));
                    } else {
                        DEV_ALL = (currentItem.getALL() - 77000)/77000;
                        DEV_ALL = (DEV_ALL * 100);
                        if(DEV_ALL < 0){
                            DEV_ALL = DEV_ALL * -1;
                        }
                        if(DEV_ALL > 100){
                            DEV_ALL = 100;
                        }
                        DEV_ALL = Double.parseDouble(df.format(DEV_ALL).replace(",", "."));
                        KTG_ALL = "LS";
                        holder.all_dev.setText(String.valueOf(DEV_ALL)+"%");
                        holder.all_hasil.setText(KTG_ALL);
                        holder.all_back.setBackground(a111Activity.getResources().getDrawable(R.drawable.failed_style));
                    }
                } else if(a111Activity.KPR.equals("Jalan Bebas Hambatan (JBH)") && a111Activity.MJL.equals("Gunung (G)")){
                    if(currentItem.getALL() <= 73000){
                        DEV_ALL = 0;
                        KTG_ALL = "LF";
                        holder.all_dev.setText(String.valueOf(DEV_ALL)+"%");
                        holder.all_hasil.setText(KTG_ALL);
                        holder.all_back.setBackground(a111Activity.getResources().getDrawable(R.drawable.success_style));
                    } else {
                        DEV_ALL = (currentItem.getALL() - 73000)/73000;
                        DEV_ALL = (DEV_ALL * 100);
                        if(DEV_ALL < 0){
                            DEV_ALL = DEV_ALL * -1;
                        }
                        if(DEV_ALL > 100){
                            DEV_ALL = 100;
                        }
                        DEV_ALL = Double.parseDouble(df.format(DEV_ALL).replace(",", "."));
                        KTG_ALL = "LS";
                        holder.all_dev.setText(String.valueOf(DEV_ALL)+"%");
                        holder.all_hasil.setText(KTG_ALL);
                        holder.all_back.setBackground(a111Activity.getResources().getDrawable(R.drawable.failed_style));
                    }
                } else if(a111Activity.KPR.equals("Jalan Raya (JR)") && a111Activity.MJL.equals("Datar (D)")){
                    if(currentItem.getALL() <= 61000){
                        DEV_ALL = 0;
                        KTG_ALL = "LF";
                        holder.all_dev.setText(String.valueOf(DEV_ALL)+"%");
                        holder.all_hasil.setText(KTG_ALL);
                        holder.all_back.setBackground(a111Activity.getResources().getDrawable(R.drawable.success_style));
                    } else {
                        DEV_ALL = (currentItem.getALL() - 61000)/61000;
                        DEV_ALL = (DEV_ALL * 100);
                        if(DEV_ALL < 0){
                            DEV_ALL = DEV_ALL * -1;
                        }
                        if(DEV_ALL > 100){
                            DEV_ALL = 100;
                        }
                        DEV_ALL = Double.parseDouble(df.format(DEV_ALL).replace(",", "."));
                        KTG_ALL = "LS";
                        holder.all_dev.setText(String.valueOf(DEV_ALL)+"%");
                        holder.all_hasil.setText(KTG_ALL);
                        holder.all_back.setBackground(a111Activity.getResources().getDrawable(R.drawable.failed_style));
                    }
                } else if(a111Activity.KPR.equals("Jalan Raya (JR)") && a111Activity.MJL.equals("Bukit (B)")){
                    if(currentItem.getALL() <= 59000){
                        DEV_ALL = 0;
                        KTG_ALL = "LF";
                        holder.all_dev.setText(String.valueOf(DEV_ALL)+"%");
                        holder.all_hasil.setText(KTG_ALL);
                        holder.all_back.setBackground(a111Activity.getResources().getDrawable(R.drawable.success_style));
                    } else {
                        DEV_ALL = (currentItem.getALL() - 59000)/59000;
                        DEV_ALL = (DEV_ALL * 100);
                        if(DEV_ALL < 0){
                            DEV_ALL = DEV_ALL * -1;
                        }
                        if(DEV_ALL > 100){
                            DEV_ALL = 100;
                        }
                        DEV_ALL = Double.parseDouble(df.format(DEV_ALL).replace(",", "."));
                        KTG_ALL = "LS";
                        holder.all_dev.setText(String.valueOf(DEV_ALL)+"%");
                        holder.all_hasil.setText(KTG_ALL);
                        holder.all_back.setBackground(a111Activity.getResources().getDrawable(R.drawable.failed_style));
                    }
                } else if(a111Activity.KPR.equals("Jalan Raya (JR)") && a111Activity.MJL.equals("Gunung (G)")){
                    if(currentItem.getALL() <= 58000){
                        DEV_ALL = 0;
                        KTG_ALL = "LF";
                        holder.all_dev.setText(String.valueOf(DEV_ALL)+"%");
                        holder.all_hasil.setText(KTG_ALL);
                        holder.all_back.setBackground(a111Activity.getResources().getDrawable(R.drawable.success_style));
                    } else {
                        DEV_ALL = (currentItem.getALL() - 58000)/58000;
                        DEV_ALL = (DEV_ALL * 100);
                        if(DEV_ALL < 0){
                            DEV_ALL = DEV_ALL * -1;
                        }
                        if(DEV_ALL > 100){
                            DEV_ALL = 100;
                        }
                        DEV_ALL = Double.parseDouble(df.format(DEV_ALL).replace(",", "."));
                        KTG_ALL = "LS";
                        holder.all_dev.setText(String.valueOf(DEV_ALL)+"%");
                        holder.all_hasil.setText(KTG_ALL);
                        holder.all_back.setBackground(a111Activity.getResources().getDrawable(R.drawable.failed_style));
                    }
                } else if(a111Activity.KPR.equals("Jalan Sedang (JS)") && a111Activity.MJL.equals("Datar (D)")){
                    if(currentItem.getALL() <= 22000){
                        DEV_ALL = 0;
                        KTG_ALL = "LF";
                        holder.all_dev.setText(String.valueOf(DEV_ALL)+"%");
                        holder.all_hasil.setText(KTG_ALL);
                        holder.all_back.setBackground(a111Activity.getResources().getDrawable(R.drawable.success_style));
                    } else {
                        DEV_ALL = (currentItem.getALL() - 22000)/22000;
                        DEV_ALL = (DEV_ALL * 100);
                        if(DEV_ALL < 0){
                            DEV_ALL = DEV_ALL * -1;
                        }
                        if(DEV_ALL > 100){
                            DEV_ALL = 100;
                        }
                        DEV_ALL = Double.parseDouble(df.format(DEV_ALL).replace(",", "."));
                        KTG_ALL = "LS";
                        holder.all_dev.setText(String.valueOf(DEV_ALL)+"%");
                        holder.all_hasil.setText(KTG_ALL);
                        holder.all_back.setBackground(a111Activity.getResources().getDrawable(R.drawable.failed_style));
                    }
                } else if(a111Activity.KPR.equals("Jalan Sedang (JS)") && a111Activity.MJL.equals("Bukit (B)")){
                    if(currentItem.getALL() <= 21500){
                        DEV_ALL = 0;
                        KTG_ALL = "LF";
                        holder.all_dev.setText(String.valueOf(DEV_ALL)+"%");
                        holder.all_hasil.setText(KTG_ALL);
                        holder.all_back.setBackground(a111Activity.getResources().getDrawable(R.drawable.success_style));
                    } else {
                        DEV_ALL = (currentItem.getALL() - 21500)/21500;
                        DEV_ALL = (DEV_ALL * 100);
                        if(DEV_ALL < 0){
                            DEV_ALL = DEV_ALL * -1;
                        }
                        if(DEV_ALL > 100){
                            DEV_ALL = 100;
                        }
                        DEV_ALL = Double.parseDouble(df.format(DEV_ALL).replace(",", "."));
                        KTG_ALL = "LS";
                        holder.all_dev.setText(String.valueOf(DEV_ALL)+"%");
                        holder.all_hasil.setText(KTG_ALL);
                        holder.all_back.setBackground(a111Activity.getResources().getDrawable(R.drawable.failed_style));
                    }
                } else if(a111Activity.KPR.equals("Jalan Sedang (JS)") && a111Activity.MJL.equals("Gunung (G)")){
                    if(currentItem.getALL() <= 20800){
                        DEV_ALL = 0;
                        KTG_ALL = "LF";
                        holder.all_dev.setText(String.valueOf(DEV_ALL)+"%");
                        holder.all_hasil.setText(KTG_ALL);
                        holder.all_back.setBackground(a111Activity.getResources().getDrawable(R.drawable.success_style));
                    } else {
                        DEV_ALL = (currentItem.getALL() - 20800)/20800;
                        DEV_ALL = (DEV_ALL * 100);
                        if(DEV_ALL < 0){
                            DEV_ALL = DEV_ALL * -1;
                        }
                        if(DEV_ALL > 100){
                            DEV_ALL = 100;
                        }
                        DEV_ALL = Double.parseDouble(df.format(DEV_ALL).replace(",", "."));
                        KTG_ALL = "LS";
                        holder.all_dev.setText(String.valueOf(DEV_ALL)+"%");
                        holder.all_hasil.setText(KTG_ALL);
                        holder.all_back.setBackground(a111Activity.getResources().getDrawable(R.drawable.failed_style));
                    }
                } else if(a111Activity.KPR.equals("Jalan Kecil (JK)") && a111Activity.MJL.equals("Datar (D)")){
                    if(currentItem.getALL() <= 17000){
                        DEV_ALL = 0;
                        KTG_ALL = "LF";
                        holder.all_dev.setText(String.valueOf(DEV_ALL)+"%");
                        holder.all_hasil.setText(KTG_ALL);
                        holder.all_back.setBackground(a111Activity.getResources().getDrawable(R.drawable.success_style));
                    } else {
                        DEV_ALL = (currentItem.getALL() - 17000)/17000;
                        DEV_ALL = (DEV_ALL * 100);
                        if(DEV_ALL < 0){
                            DEV_ALL = DEV_ALL * -1;
                        }
                        if(DEV_ALL > 100){
                            DEV_ALL = 100;
                        }
                        DEV_ALL = Double.parseDouble(df.format(DEV_ALL).replace(",", "."));
                        KTG_ALL = "LS";
                        holder.all_dev.setText(String.valueOf(DEV_ALL)+"%");
                        holder.all_hasil.setText(KTG_ALL);
                        holder.all_back.setBackground(a111Activity.getResources().getDrawable(R.drawable.failed_style));
                    }
                } else if(a111Activity.KPR.equals("Jalan Kecil (JK)") && a111Activity.MJL.equals("Bukit (B)")){
                    if(currentItem.getALL() <= 16300){
                        DEV_ALL = 0;
                        KTG_ALL = "LF";
                        holder.all_dev.setText(String.valueOf(DEV_ALL)+"%");
                        holder.all_hasil.setText(KTG_ALL);
                        holder.all_back.setBackground(a111Activity.getResources().getDrawable(R.drawable.success_style));
                    } else {
                        DEV_ALL = (currentItem.getALL() - 16300)/16300;
                        DEV_ALL = (DEV_ALL * 100);
                        if(DEV_ALL < 0){
                            DEV_ALL = DEV_ALL * -1;
                        }
                        if(DEV_ALL > 100){
                            DEV_ALL = 100;
                        }
                        DEV_ALL = Double.parseDouble(df.format(DEV_ALL).replace(",", "."));
                        KTG_ALL = "LS";
                        holder.all_dev.setText(String.valueOf(DEV_ALL)+"%");
                        holder.all_hasil.setText(KTG_ALL);
                        holder.all_back.setBackground(a111Activity.getResources().getDrawable(R.drawable.failed_style));
                    }
                } else if(a111Activity.KPR.equals("Jalan Kecil (JK)") && a111Activity.MJL.equals("Gunung (G)")){
                    if(currentItem.getALL() <= 15800){
                        DEV_ALL = 0;
                        KTG_ALL = "LF";
                        holder.all_dev.setText(String.valueOf(DEV_ALL)+"%");
                        holder.all_hasil.setText(KTG_ALL);
                        holder.all_back.setBackground(a111Activity.getResources().getDrawable(R.drawable.success_style));
                    } else {
                        DEV_ALL = (currentItem.getALL() - 15800)/15800;
                        DEV_ALL = (DEV_ALL * 100);
                        if(DEV_ALL < 0){
                            DEV_ALL = DEV_ALL * -1;
                        }
                        if(DEV_ALL > 100){
                            DEV_ALL = 100;
                        }
                        DEV_ALL = Double.parseDouble(df.format(DEV_ALL).replace(",", "."));
                        KTG_ALL = "LS";
                        holder.all_dev.setText(String.valueOf(DEV_ALL)+"%");
                        holder.all_hasil.setText(KTG_ALL);
                        holder.all_back.setBackground(a111Activity.getResources().getDrawable(R.drawable.failed_style));
                    }
                }

            }
        } catch (Exception e){
            Toast.makeText(a111Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        //Jumlah Lajur

        try {
            if(currentItem.getJLA() == -1){
                holder.jla_data.setText("-");
                holder.jla_dev.setText("-");
                DEV_JLA = -1;
                KTG_JLA = "-";
                holder.jla_hasil.setText(KTG_JLA);
                holder.jla_back.setBackground(a111Activity.getResources().getDrawable(R.drawable.null_style));
            } else {
                DEV_JLA = STD - currentItem.getJLA();
                if(DEV_JLA == 0){
                    KTG_JLA = "LF";
                    holder.jla_back.setBackground(a111Activity.getResources().getDrawable(R.drawable.success_style));
                } else {
                    KTG_JLA = "LS";
                    holder.jla_back.setBackground(a111Activity.getResources().getDrawable(R.drawable.failed_style));
                }
                holder.jla_data.setText(String.valueOf(currentItem.getJLA())+"%");
                holder.jla_dev.setText(String.valueOf(DEV_JLA)+"%");
                holder.jla_hasil.setText(KTG_JLA);

            }
        } catch (Exception e){
            Toast.makeText(a111Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        //Lebar setiap Lajur

        try {
            if(currentItem.getLSL() == -1){
                holder.lla_data.setText("-");
                holder.lla_dev.setText("-");
                DEV_LLA = -1;
                KTG_LLA = "-";
                holder.lla_hasil.setText(KTG_LLA);
                holder.lla_back.setBackground(a111Activity.getResources().getDrawable(R.drawable.null_style));
            } else {
                holder.lla_data.setText(String.valueOf(currentItem.getLSL())+" m");
                if(currentItem.getALL() < 3000) {
                    if(currentItem.getLSL() >= 2.25) {
                        DEV_LLA = 0;
                        KTG_LLA = "LF";
                        holder.lla_dev.setText(String.valueOf(DEV_LLA)+"%");
                        holder.lla_hasil.setText(KTG_LLA);
                        holder.lla_back.setBackground(a111Activity.getResources().getDrawable(R.drawable.success_style));
                    } else {
                        DEV_LLA = (currentItem.getLSL() - 2.25)/2.25;
                        DEV_LLA = (DEV_LLA * 100);
                        if(DEV_LLA < 0){
                            DEV_LLA = DEV_LLA * -1;
                        }
                        if(DEV_LLA > 100){
                            DEV_LLA = 100;
                        }
                        DEV_LLA = Double.parseDouble(df.format(DEV_LLA).replace(",", "."));
                        KTG_LLA = "LS";
                        holder.lla_dev.setText(String.valueOf(DEV_LLA)+"%");
                        holder.lla_hasil.setText(KTG_LLA);
                        holder.lla_back.setBackground(a111Activity.getResources().getDrawable(R.drawable.failed_style));
                    }
                } else if(currentItem.getALL() >= 3000 && currentItem.getALL() <= 17000){
                    if(currentItem.getLSL() >= 2.75){
                        DEV_LLA = 0;
                        KTG_LLA = "LF";
                        holder.lla_dev.setText(String.valueOf(DEV_LLA)+"%");
                        holder.lla_hasil.setText(KTG_LLA);
                        holder.lla_back.setBackground(a111Activity.getResources().getDrawable(R.drawable.success_style));
                    } else {
                        DEV_LLA = (currentItem.getLSL() - 2.75)/2.75;
                        DEV_LLA = (DEV_LLA * 100);
                        if(DEV_ALL < 0){
                            DEV_ALL = DEV_ALL * -1;
                        }
                        if(DEV_LLA > 100){
                            DEV_LLA = 100;
                        }
                        DEV_LLA = Double.parseDouble(df.format(DEV_LLA).replace(",", "."));
                        KTG_LLA = "LS";
                        holder.lla_dev.setText(String.valueOf(DEV_LLA)+"%");
                        holder.lla_hasil.setText(KTG_LLA);
                        holder.lla_back.setBackground(a111Activity.getResources().getDrawable(R.drawable.failed_style));
                    }
                } else if(currentItem.getALL() > 17000){
                    if(currentItem.getLSL() >= 3.5){
                        DEV_LLA = 0;
                        KTG_LLA = "LF";
                        holder.lla_dev.setText(String.valueOf(DEV_LLA)+"%");
                        holder.lla_hasil.setText(KTG_LLA);
                        holder.lla_back.setBackground(a111Activity.getResources().getDrawable(R.drawable.success_style));
                    } else {
                        DEV_LLA = (currentItem.getLSL() - 3.5)/3.5;
                        DEV_LLA = (DEV_LLA * 100);
                        if(DEV_ALL < 0){
                            DEV_ALL = DEV_ALL * -1;
                        }
                        if(DEV_LLA > 100){
                            DEV_LLA = 100;
                        }
                        DEV_LLA = Double.parseDouble(df.format(DEV_LLA).replace(",", "."));
                        KTG_LLA = "LS";
                        holder.lla_dev.setText(String.valueOf(DEV_LLA)+"%");
                        holder.lla_hasil.setText(KTG_LLA);
                        holder.lla_back.setBackground(a111Activity.getResources().getDrawable(R.drawable.failed_style));
                    }
                }
            }
        } catch (Exception e){
            Toast.makeText(a111Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        //Keseragaman Lebar Lajur

        try {
            if(currentItem.getKLL() == -1){
                holder.kll_data.setText("-");
                holder.kll_dev.setText("-");
                DEV_KLL = -1;
                KTG_KLL = "-";
                holder.kll_hasil.setText(KTG_KLL);
                holder.kll_back.setBackground(a111Activity.getResources().getDrawable(R.drawable.null_style));
            } else {
                holder.kll_data.setText(String.valueOf(currentItem.getKLL())+"%");
                DEV_KLL = STD - currentItem.getKLL();
                if(DEV_KLL == 0){
                    KTG_KLL = "LF";
                    holder.kll_dev.setText(String.valueOf(DEV_KLL)+"%");
                    holder.kll_hasil.setText(KTG_KLL);
                    holder.kll_back.setBackground(a111Activity.getResources().getDrawable(R.drawable.success_style));
                } else {
                    KTG_KLL = "LS";
                    holder.kll_dev.setText(String.valueOf(DEV_KLL)+"%");
                    holder.kll_hasil.setText(KTG_KLL);
                    holder.kll_back.setBackground(a111Activity.getResources().getDrawable(R.drawable.failed_style));
                }

            }
        } catch (Exception e){
            Toast.makeText(a111Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        //Kemiringan Melintang

        try {
            if(currentItem.getKML() == -1){
                holder.kml_data.setText("-");
                holder.kml_dev.setText("-");
                DEV_KML = -1;
                KTG_KML = "-";
                holder.kml_hasil.setText(KTG_KML);
                holder.kml_back.setBackground(a111Activity.getResources().getDrawable(R.drawable.null_style));
            } else {
                holder.kml_data.setText(String.valueOf(currentItem.getKML())+"%");
                if(currentItem.getKML() >= 2 && currentItem.getKML() <= 3){
                    DEV_KML = 0;
                    KTG_KML = "LF";
                    holder.kml_dev.setText(String.valueOf(DEV_KML)+"%");
                    holder.kml_hasil.setText(KTG_KML);
                    holder.kml_back.setBackground(a111Activity.getResources().getDrawable(R.drawable.success_style));
                } else {
                    if(currentItem.getKML() < 2){
                        DEV_KML = (currentItem.getKML() - 2)/2;
                    } else {
                        DEV_KML = (currentItem.getKML() - 3)/3;
                    }
                    DEV_KML = (DEV_KML * 100);
                    if(DEV_KML < 0){
                        DEV_KML = DEV_KML * -1;
                    }
                    if(DEV_KML > 100){
                        DEV_KML = 100;
                    }
                    DEV_KML = Double.parseDouble(df.format(DEV_KML).replace(",", "."));
                    KTG_KML = "LS";
                    holder.kml_dev.setText(String.valueOf(DEV_KML)+"%");
                    holder.kml_hasil.setText(KTG_KML);
                    holder.kml_back.setBackground(a111Activity.getResources().getDrawable(R.drawable.failed_style));
                }

            }
        } catch (Exception e){
            Toast.makeText(a111Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        //KLF

        try {
            if((KTG_ALL.equals("LF") || KTG_ALL.equals("-"))  && (KTG_JLA.equals("LF") || KTG_JLA.equals("-")) &&
                    (KTG_LLA.equals("LF") || KTG_LLA.equals("-")) && (KTG_KLL.equals("LF") || KTG_KLL.equals("-")) && (KTG_KML.equals("LF") || KTG_KML.equals("-"))){

                if(KTG_ALL.equals("-") && KTG_JLA.equals("-") && KTG_LLA.equals("-") && KTG_KLL.equals("-") && KTG_KML.equals("-")) {
                    KTG_KLF = "Tidak Dinilai";
                } else {
                    KTG_KLF = "LF";
                }

            } else {
                KTG_KLF = "LS";
            }
        } catch (Exception e){
            Toast.makeText(a111Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            holder.rec_data.setText(currentItem.getREC());
        } catch (Exception e){
            Toast.makeText(a111Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            holder.FT1.setImageBitmap(BitmapFactory.decodeFile(currentItem.getDIR1()));
        } catch (Exception e){
            Toast.makeText(a111Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            holder.FT2.setImageBitmap(BitmapFactory.decodeFile(currentItem.getDIR2()));
        } catch (Exception e){
            Toast.makeText(a111Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            holder.FT3.setImageBitmap(BitmapFactory.decodeFile(currentItem.getDIR3()));
        } catch (Exception e){
            Toast.makeText(a111Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        //FAB UPLOAD

        try {
            if(currentItem.getUPLOAD().equals("F")){
                a111Activity.FAB_UPLOAD.hide();
                a111Activity.UPLOAD.setImageResource(R.drawable.ic_file_upload_white_18dp);
                a111Activity.FAB_UPLOAD.setEnabled(true);
            } else {
                a111Activity.UPLOAD.setImageResource(R.drawable.ic_done_white_18dp);
                a111Activity.FAB_UPLOAD.setEnabled(false);
            }
        } catch (Exception e){
            Toast.makeText(a111Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        //Kirim ke Activity

        try {
            a111Activity.ID = currentItem.getID();
            a111Activity.FJL_TXT = currentItem.getFJL();
            a111Activity.ALL_TXT = currentItem.getALL();
            a111Activity.JLA_TXT = currentItem.getJLA();
            a111Activity.LLA_TXT = currentItem.getLSL();
            a111Activity.KLL_TXT = currentItem.getKLL();
            a111Activity.KML_TXT = currentItem.getKML();
            a111Activity.REC_TXT = currentItem.getREC();
            a111Activity.DIR1 = currentItem.getDIR1();
            a111Activity.DIR2 = currentItem.getDIR2();
            a111Activity.DIR3 = currentItem.getDIR3();

            a111Activity.DEV_ALL = DEV_ALL;
            a111Activity.DEV_JLA = DEV_JLA;
            a111Activity.DEV_LLA = DEV_LLA;
            a111Activity.DEV_KLL = DEV_KLL;
            a111Activity.DEV_KML = DEV_KML;

            a111Activity.KTG_ALL = KTG_ALL;
            a111Activity.KTG_JLA = KTG_JLA;
            a111Activity.KTG_LLA = KTG_LLA;
            a111Activity.KTG_KLL = KTG_KLL;
            a111Activity.KTG_KML = KTG_KML;
            a111Activity.KTG_KLF = KTG_KLF;

            if(KTG_KLF.equals("LS")){
                a111Activity.klf.setText("LS");
                a111Activity.klf.setBackground(a111Activity.getResources().getDrawable(R.drawable.failed_style));
                Animation animation = AnimationUtils.loadAnimation(a111Activity.getApplicationContext(), R.anim.recycle_bottom);
                a111Activity.klf.startAnimation(animation);
            } else if(KTG_KLF.equals("Tidak Dinilai")){
                a111Activity.klf.setText("Tidak Dinilai");
                a111Activity.klf.setBackground(a111Activity.getResources().getDrawable(R.drawable.null_style));
                Animation animation = AnimationUtils.loadAnimation(a111Activity.getApplicationContext(), R.anim.recycle_bottom);
                a111Activity.klf.startAnimation(animation);
            } else {
                a111Activity.klf.setText("LF");
                a111Activity.klf.setBackground(a111Activity.getResources().getDrawable(R.drawable.success_style));
                Animation animation = AnimationUtils.loadAnimation(a111Activity.getApplicationContext(), R.anim.recycle_bottom);
                a111Activity.klf.startAnimation(animation);
            }
        } catch (Exception e){
            Toast.makeText(a111Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public int getItemCount() {
        return mdataList.size();
    }

}
