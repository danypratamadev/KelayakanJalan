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

public class A33_Adapter extends RecyclerView.Adapter<A33_Adapter.A33ViewHolder> {

    private A33Activity a33Activity;
    private List<A33_Class> mdataList;
    private double DEV_DTR, DEV_PGN, DEV_FMA, DEV_KRS, STD = 100;
    private String KTG_DTR, KTG_PGN, KTG_JPK, KTG_FMA, KTG_KRS, KTG_KLF;
    private static DecimalFormat df = new DecimalFormat("0.0");

    public class A33ViewHolder extends RecyclerView.ViewHolder {

        private TextView dtr_data, dtr_dev, pgn_data, pgn_dev, jpk_hasil,
                fma_data, fma_dev, fma_hasil, krs_data, krs_dev, krs_hasil, rec_data;

        private LinearLayout jpk_back, fma_back, krs_back;
        private ImageView FT1, FT2;

        public A33ViewHolder(@NonNull View itemView) {
            super(itemView);

            dtr_data = (TextView) itemView.findViewById(R.id.dtr_data);
            dtr_dev = (TextView) itemView.findViewById(R.id.dtr_dev);

            pgn_data = (TextView) itemView.findViewById(R.id.pgn_data);
            pgn_dev = (TextView) itemView.findViewById(R.id.pgn_dev);

            jpk_hasil = (TextView) itemView.findViewById(R.id.jpk_hasil);

            fma_data = (TextView) itemView.findViewById(R.id.fma_data);
            fma_dev = (TextView) itemView.findViewById(R.id.fma_dev);
            fma_hasil = (TextView) itemView.findViewById(R.id.fma_hasil);

            krs_data = (TextView) itemView.findViewById(R.id.krs_data);
            krs_dev = (TextView) itemView.findViewById(R.id.krs_dev);
            krs_hasil = (TextView) itemView.findViewById(R.id.krs_hasil);

            rec_data = (TextView) itemView.findViewById(R.id.rec_data);

            FT1 = (ImageView) itemView.findViewById(R.id.FT1);
            FT2 = (ImageView) itemView.findViewById(R.id.FT2);

            jpk_back = (LinearLayout) itemView.findViewById(R.id.jpk_back);
            fma_back = (LinearLayout) itemView.findViewById(R.id.fma_back);
            krs_back = (LinearLayout) itemView.findViewById(R.id.krs_back);

        }
    }

    public A33_Adapter(A33Activity a33Activity, List<A33_Class> mdataList) {
        this.a33Activity = a33Activity;
        this.mdataList = mdataList;
    }

    @NonNull
    @Override
    public A33_Adapter.A33ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.a33_layout, parent, false);
        A33_Adapter.A33ViewHolder a33 = new A33_Adapter.A33ViewHolder(view);
        return a33;
    }

    @Override
    public void onBindViewHolder(@NonNull A33_Adapter.A33ViewHolder holder, int position) {

        final A33_Class currentItem = mdataList.get(position);

        try {
            if(currentItem.getDTR() == -1){
                holder.dtr_data.setText("-");
                holder.dtr_dev.setText("-");
                DEV_DTR = -1;
                KTG_DTR = "-";
            } else {
                holder.dtr_data.setText(String.valueOf(currentItem.getDTR())+"/Km");
                if(currentItem.getDTR() <= 10){
                    DEV_DTR = 0;
                    KTG_DTR = "LF";
                } else {
                    DEV_DTR = (currentItem.getDTR() - 10)/10;
                    DEV_DTR = (DEV_DTR * 100);
                    if(DEV_DTR < 0){
                        DEV_DTR = DEV_DTR * -1;
                    }
                    if(DEV_DTR > 100){
                        DEV_DTR = 100;
                    }
                    DEV_DTR = Double.parseDouble(df.format(DEV_DTR).replace(",", "."));
                    KTG_DTR = "LS";
                }
                holder.dtr_dev.setText(String.valueOf(DEV_DTR)+"%");
            }
        } catch (Exception e){
            Toast.makeText(a33Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            if(currentItem.getPGN() == -1){
                holder.pgn_data.setText("-");
                holder.pgn_dev.setText("-");
                DEV_PGN = -1;
                KTG_PGN = "-";
            } else {
                holder.pgn_data.setText(String.valueOf(currentItem.getPGN())+"/Km");
                if(currentItem.getPGN() <= 5){
                    DEV_PGN = 0;
                    KTG_PGN = "LF";
                } else {
                    DEV_PGN = (currentItem.getPGN() - 5)/5;
                    DEV_PGN = (DEV_PGN * 100);
                    if(DEV_PGN < 0){
                        DEV_PGN = DEV_PGN * -1;
                    }
                    if(DEV_PGN > 100){
                        DEV_PGN = 100;
                    }
                    DEV_PGN = Double.parseDouble(df.format(DEV_PGN).replace(",", "."));
                    KTG_PGN = "LS";
                }
                holder.pgn_dev.setText(String.valueOf(DEV_PGN)+"%");
            }
        } catch (Exception e){
            Toast.makeText(a33Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            if((KTG_DTR.equals("LF") || KTG_DTR.equals("-")) && (KTG_PGN.equals("LF") || KTG_PGN.equals("-"))){

                if(KTG_DTR.equals("-") && KTG_PGN.equals("-")){
                    KTG_JPK = "-";
                    holder.jpk_back.setBackground(a33Activity.getResources().getDrawable(R.drawable.null_style));
                } else {
                    KTG_JPK = "LF";
                    holder.jpk_back.setBackground(a33Activity.getResources().getDrawable(R.drawable.success_style));
                }

            } else {
                KTG_JPK = "LS";
                holder.jpk_back.setBackground(a33Activity.getResources().getDrawable(R.drawable.failed_style));
            }
            holder.jpk_hasil.setText(KTG_JPK);
        } catch (Exception e){
            Toast.makeText(a33Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            if(currentItem.getFMA() == -1){
                holder.fma_data.setText("-");
                DEV_FMA = -1;
                KTG_FMA = "-";
                holder.fma_dev.setText("-");
                holder.fma_hasil.setText(KTG_FMA);
                holder.fma_back.setBackground(a33Activity.getResources().getDrawable(R.drawable.null_style));
            } else {
                holder.fma_data.setText(String.valueOf(currentItem.getFMA())+"%");
                DEV_FMA = STD - currentItem.getFMA();
                if(DEV_FMA == 0){
                    KTG_FMA = "LF";
                    holder.fma_back.setBackground(a33Activity.getResources().getDrawable(R.drawable.success_style));
                } else {
                    KTG_FMA = "LS";
                    holder.fma_back.setBackground(a33Activity.getResources().getDrawable(R.drawable.failed_style));
                }
                holder.fma_dev.setText(String.valueOf(DEV_FMA)+"%");
                holder.fma_hasil.setText(KTG_FMA);
            }
        } catch (Exception e){
            Toast.makeText(a33Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            if(currentItem.getKRS() == -1){
                holder.krs_data.setText("-");
                DEV_KRS = -1;
                KTG_KRS = "-";
                holder.krs_dev.setText("-");
                holder.krs_hasil.setText(KTG_KRS);
                holder.krs_back.setBackground(a33Activity.getResources().getDrawable(R.drawable.null_style));
            } else {
                holder.krs_data.setText(String.valueOf(currentItem.getKRS())+"%");
                DEV_KRS = STD - currentItem.getKRS();
                if(DEV_KRS == 0){
                    KTG_KRS = "LF";
                    holder.krs_back.setBackground(a33Activity.getResources().getDrawable(R.drawable.success_style));
                } else {
                    KTG_KRS = "LS";
                    holder.krs_back.setBackground(a33Activity.getResources().getDrawable(R.drawable.failed_style));
                }
                holder.krs_dev.setText(String.valueOf(DEV_KRS)+"%");
                holder.krs_hasil.setText(KTG_KRS);
            }
        } catch (Exception e){
            Toast.makeText(a33Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            if((KTG_JPK.equals("LF") || KTG_JPK.equals("-")) && (KTG_FMA.equals("LF") || KTG_FMA.equals("-")) && (KTG_KRS.equals("LF") || KTG_KRS.equals("-"))){

                if(KTG_JPK.equals("-") && KTG_FMA.equals("-") && KTG_KRS.equals("-")){
                    KTG_KLF = "Tidak Dinilai";
                } else {
                    KTG_KLF = "LF";
                }

            } else {
                KTG_KLF = "LS";
            }
        } catch (Exception e){
            Toast.makeText(a33Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            holder.rec_data.setText(currentItem.getREC());
        } catch (Exception e){
            Toast.makeText(a33Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            holder.FT1.setImageBitmap(BitmapFactory.decodeFile(currentItem.getDIR1()));
        } catch (Exception e){
            Toast.makeText(a33Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            holder.FT2.setImageBitmap(BitmapFactory.decodeFile(currentItem.getDIR2()));
        } catch (Exception e){
            Toast.makeText(a33Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            if(currentItem.getUPLOAD().equals("F")){
                a33Activity.FAB_UPLOAD.hide();
                a33Activity.UPLOAD.setImageResource(R.drawable.ic_file_upload_white_18dp);
                a33Activity.FAB_UPLOAD.setEnabled(true);
            } else {
                a33Activity.UPLOAD.setImageResource(R.drawable.ic_done_white_18dp);
                a33Activity.FAB_UPLOAD.setEnabled(false);
            }
        } catch (Exception e){
            Toast.makeText(a33Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            a33Activity.ID = currentItem.getID();
            a33Activity.DTR_TXT = currentItem.getDTR();
            a33Activity.PGN_TXT = currentItem.getPGN();
            a33Activity.FMA_TXT = currentItem.getFMA();
            a33Activity.KRS_TXT = currentItem.getKRS();
            a33Activity.REC_TXT = currentItem.getREC();
            a33Activity.DIR1 = currentItem.getDIR1();
            a33Activity.DIR2 = currentItem.getDIR2();

            a33Activity.DEV_DTR = DEV_DTR;
            a33Activity.DEV_PGN = DEV_PGN;
            a33Activity.DEV_FMA = DEV_FMA;
            a33Activity.DEV_KRS = DEV_KRS;

            a33Activity.KTG_JPK = KTG_JPK;
            a33Activity.KTG_FMA = KTG_FMA;
            a33Activity.KTG_KRS = KTG_KRS;
            a33Activity.KTG_KLF = KTG_KLF;

            if(KTG_KLF.equals("LS")){
                a33Activity.klf.setText(KTG_KLF);
                a33Activity.klf.setBackground(a33Activity.getResources().getDrawable(R.drawable.failed_style));
                Animation animation = AnimationUtils.loadAnimation(a33Activity.getApplicationContext(), R.anim.recycle_bottom);
                a33Activity.klf.startAnimation(animation);
            } else if(KTG_KLF.equals("Tidak Dinilai")){
                a33Activity.klf.setText("Tidak Dinilai");
                a33Activity.klf.setBackground(a33Activity.getResources().getDrawable(R.drawable.null_style));
                Animation animation = AnimationUtils.loadAnimation(a33Activity.getApplicationContext(), R.anim.recycle_bottom);
                a33Activity.klf.startAnimation(animation);
            } else {
                a33Activity.klf.setText(KTG_KLF);
                a33Activity.klf.setBackground(a33Activity.getResources().getDrawable(R.drawable.success_style));
                Animation animation = AnimationUtils.loadAnimation(a33Activity.getApplicationContext(), R.anim.recycle_bottom);
                a33Activity.klf.startAnimation(animation);
            }
        } catch (Exception e){
            Toast.makeText(a33Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public int getItemCount() {
        return mdataList.size();
    }

}
