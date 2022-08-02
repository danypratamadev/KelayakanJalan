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

public class A114_Adapter extends RecyclerView.Adapter<A114_Adapter.A114ViewHolder>  {

    private A114Activity a114Activity;
    private List<A114_Class> mdataList;
    private double DEV_LSS, DEV_BSS2, DEV_FMA, STD = 100;
    private String KTG_LSS, KTG_BSS, KTG_FMA, KTG_KLF;
    private int BSS1_IN;
    private static DecimalFormat df = new DecimalFormat("0.0");

    public static class A114ViewHolder extends RecyclerView.ViewHolder {
        private TextView lss_data, lss_dev, lss_hasil, sbss_data, bss_data, bss_dev, bss_hasil,
                fma_data, fma_dev, fma_hasil, rec_data;
        private LinearLayout lss_back, bss_back, fma_back;
        private ImageView FT1, FT2;

        public A114ViewHolder(View itemView) {
            super(itemView);

            lss_data = (TextView) itemView.findViewById(R.id.lss_data);
            lss_dev = (TextView) itemView.findViewById(R.id.lss_dev);
            lss_hasil = (TextView) itemView.findViewById(R.id.lss_hasil);
            sbss_data = (TextView) itemView.findViewById(R.id.sbss_data);
            bss_data = (TextView) itemView.findViewById(R.id.bss_data);
            bss_dev = (TextView) itemView.findViewById(R.id.bss_dev);
            bss_hasil = (TextView) itemView.findViewById(R.id.bss_hasil);
            fma_data = (TextView) itemView.findViewById(R.id.fma_data);
            fma_dev = (TextView) itemView.findViewById(R.id.fma_dev);
            fma_hasil = (TextView) itemView.findViewById(R.id.fma_hasil);
            rec_data = (TextView) itemView.findViewById(R.id.rec_data);

            FT1 = (ImageView) itemView.findViewById(R.id.FT1);
            FT2 = (ImageView) itemView.findViewById(R.id.FT2);

            lss_back = (LinearLayout) itemView.findViewById(R.id.lss_back);
            bss_back = (LinearLayout) itemView.findViewById(R.id.bss_back);
            fma_back = (LinearLayout) itemView.findViewById(R.id.fma_back);

        }
    }

    public A114_Adapter(A114Activity a114Activity, List<A114_Class> dataList) {
        this.a114Activity = a114Activity;
        this.mdataList = dataList;
    }

    @NonNull
    @Override
    public A114_Adapter.A114ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.a114_layout, parent, false);
        A114_Adapter.A114ViewHolder rsu = new A114_Adapter.A114ViewHolder(view);
        return rsu;
    }

    @Override
    public void onBindViewHolder(@NonNull A114_Adapter.A114ViewHolder holder, int position) {

        final A114_Class currentItem = mdataList.get(position);

        //Lebar dan dimensi Selokan Samping

        try {
            if(currentItem.getLSS() == -1){
                holder.lss_data.setText("-");
                DEV_LSS = -1;
                KTG_LSS = "-";
                holder.lss_dev.setText("-");
                holder.lss_hasil.setText(KTG_LSS);
                holder.lss_back.setBackground(a114Activity.getResources().getDrawable(R.drawable.null_style));
            } else {
                holder.lss_data.setText(String.valueOf(currentItem.getLSS())+" m");
                if(currentItem.getLSS() >= 1){
                    DEV_LSS = 0;
                    KTG_LSS = "LF";
                    holder.lss_back.setBackground(a114Activity.getResources().getDrawable(R.drawable.success_style));
                } else {
                    DEV_LSS = (currentItem.getLSS() - 1)/1;
                    DEV_LSS = (DEV_LSS * 100);
                    if(DEV_LSS < 0){
                        DEV_LSS = DEV_LSS * -1;
                    }
                    if(DEV_LSS > 100){
                        DEV_LSS = 100;
                    }
                    DEV_LSS = Double.parseDouble(df.format(DEV_LSS).replace(",", "."));
                    KTG_LSS = "LS";
                    holder.lss_back.setBackground(a114Activity.getResources().getDrawable(R.drawable.failed_style));
                }
                holder.lss_dev.setText(String.valueOf(DEV_LSS)+"%");
                holder.lss_hasil.setText(KTG_LSS);
            }
        } catch (Exception e){
            Toast.makeText(a114Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        //Bentuk Selokan Samping

        try {
            if(currentItem.getBSS2() == -1){
                holder.sbss_data.setText("-");
                holder.bss_data.setText("-");
                DEV_BSS2 = -1;
                KTG_BSS = "-";
                holder.bss_dev.setText("-");
                holder.bss_hasil.setText(KTG_BSS);
                holder.bss_back.setBackground(a114Activity.getResources().getDrawable(R.drawable.null_style));
            } else {
                if(currentItem.getBSS1().equals("Trapesium")){
                    BSS1_IN = 1;
                    holder.sbss_data.setText("("+String.valueOf(BSS1_IN)+") "+currentItem.getBSS1());
                } else if(currentItem.getBSS1().equals("Segitiga")) {
                    BSS1_IN = 2;
                    holder.sbss_data.setText("("+String.valueOf(BSS1_IN)+") "+currentItem.getBSS1());
                } else if(currentItem.getBSS1().equals("Segiempat")) {
                    BSS1_IN = 3;
                    holder.sbss_data.setText("("+String.valueOf(BSS1_IN)+") "+currentItem.getBSS1());
                } else if(currentItem.getBSS1().equals("Lingkaran")){
                    BSS1_IN = 4;
                    holder.sbss_data.setText("("+String.valueOf(BSS1_IN)+") "+currentItem.getBSS1());
                } else {
                    BSS1_IN = 5;
                    holder.sbss_data.setText("("+String.valueOf(BSS1_IN)+") "+currentItem.getBSS1());
                }
                holder.bss_data.setText(String.valueOf(currentItem.getBSS2())+"%");
                DEV_BSS2 = STD - currentItem.getBSS2();
                if(DEV_BSS2 == 0){
                    KTG_BSS = "LF";
                    holder.bss_back.setBackground(a114Activity.getResources().getDrawable(R.drawable.success_style));
                } else {
                    KTG_BSS = "LS";
                    holder.bss_back.setBackground(a114Activity.getResources().getDrawable(R.drawable.failed_style));
                }

                holder.bss_dev.setText(String.valueOf(DEV_BSS2)+"%");
                holder.bss_hasil.setText(KTG_BSS);

            }
        } catch (Exception e){
            Toast.makeText(a114Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        //Fungsi Mengalirkan Air

        try {
            if(currentItem.getFMA() == -1){
                holder.fma_data.setText("-");
                DEV_FMA = -1;
                KTG_FMA = "-";
                holder.fma_dev.setText("-");
                holder.fma_hasil.setText(KTG_FMA);
                holder.fma_back.setBackground(a114Activity.getResources().getDrawable(R.drawable.null_style));
            } else {
                holder.fma_data.setText(String.valueOf(currentItem.getFMA())+"%");
                DEV_FMA = STD - currentItem.getFMA();
                if(DEV_FMA == 0){
                    KTG_FMA = "LF";
                    holder.fma_back.setBackground(a114Activity.getResources().getDrawable(R.drawable.success_style));
                } else {
                    KTG_FMA = "LS";
                    holder.fma_back.setBackground(a114Activity.getResources().getDrawable(R.drawable.failed_style));
                }
                holder.fma_dev.setText(String.valueOf(DEV_FMA)+"%");
                holder.fma_hasil.setText(KTG_FMA);
            }
        } catch (Exception e){
            Toast.makeText(a114Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        //KLF

        try {
            if((KTG_LSS.equals("LF") || KTG_LSS.equals("-")) && (KTG_BSS.equals("LF") || KTG_BSS.equals("-")) && (KTG_FMA.equals("LF") || KTG_FMA.equals("-"))){

                if(KTG_LSS.equals("-") && KTG_BSS.equals("-")&& KTG_FMA.equals("-")){
                    KTG_KLF = "Tidak Dinilai";
                } else {
                    KTG_KLF = "LF";
                }

            } else {
                KTG_KLF = "LS";
            }
        } catch (Exception e){
            Toast.makeText(a114Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            holder.rec_data.setText(currentItem.getREC());
        } catch (Exception e){
            Toast.makeText(a114Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            holder.FT1.setImageBitmap(BitmapFactory.decodeFile(currentItem.getDIR1()));
        } catch (Exception e){
            Toast.makeText(a114Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            holder.FT2.setImageBitmap(BitmapFactory.decodeFile(currentItem.getDIR2()));
        } catch (Exception e){
            Toast.makeText(a114Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        //FAB UPLOAD

        try {
            if(currentItem.getUPLOAD().equals("F")){
                a114Activity.FAB_UPLOAD.hide();
                a114Activity.UPLOAD.setImageResource(R.drawable.ic_file_upload_white_18dp);
                a114Activity.FAB_UPLOAD.setEnabled(true);
            } else {
                a114Activity.UPLOAD.setImageResource(R.drawable.ic_done_white_18dp);
                a114Activity.FAB_UPLOAD.setEnabled(false);
            }
        } catch (Exception e){
            Toast.makeText(a114Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        //Kirim ke Activity

        try {
            a114Activity.ID = currentItem.getID();
            a114Activity.LSS_TXT = currentItem.getLSS();
            a114Activity.BSS1_IN = BSS1_IN;
            a114Activity.BSS1_TXT = currentItem.getBSS1();
            a114Activity.BSS2_TXT = currentItem.getBSS2();
            a114Activity.FMA_TXT = currentItem.getFMA();
            a114Activity.REC_TXT = currentItem.getREC();
            a114Activity.DIR1 = currentItem.getDIR1();
            a114Activity.DIR2 = currentItem.getDIR2();

            a114Activity.DEV_LSS = DEV_LSS;
            a114Activity.DEV_BSS2 = DEV_BSS2;
            a114Activity.DEV_FMA = DEV_FMA;

            a114Activity.KTG_LSS = KTG_LSS;
            a114Activity.KTG_BSS = KTG_BSS;
            a114Activity.KTG_FMA = KTG_FMA;
            a114Activity.KTG_KLF = KTG_KLF;

            if(KTG_KLF.equals("LS")){
                a114Activity.klf.setText(KTG_KLF);
                a114Activity.klf.setBackground(a114Activity.getResources().getDrawable(R.drawable.failed_style));
                Animation animation = AnimationUtils.loadAnimation(a114Activity.getApplicationContext(), R.anim.recycle_bottom);
                a114Activity.klf.startAnimation(animation);
            } else if(KTG_KLF.equals("Tidak Dinilai")){
                a114Activity.klf.setText("Tidak Dinilai");
                a114Activity.klf.setBackground(a114Activity.getResources().getDrawable(R.drawable.null_style));
                Animation animation = AnimationUtils.loadAnimation(a114Activity.getApplicationContext(), R.anim.recycle_bottom);
                a114Activity.klf.startAnimation(animation);
            } else {
                a114Activity.klf.setText(KTG_KLF);
                a114Activity.klf.setBackground(a114Activity.getResources().getDrawable(R.drawable.success_style));
                Animation animation = AnimationUtils.loadAnimation(a114Activity.getApplicationContext(), R.anim.recycle_bottom);
                a114Activity.klf.startAnimation(animation);
            }
        } catch (Exception e){
            Toast.makeText(a114Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public int getItemCount() {
        return mdataList.size();
    }
}
