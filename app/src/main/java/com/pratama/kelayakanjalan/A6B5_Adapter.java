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
import androidx.arch.core.executor.TaskExecutor;
import androidx.recyclerview.widget.RecyclerView;

public class A6B5_Adapter extends RecyclerView.Adapter<A6B5_Adapter.A6B5ViewHolder> {

    private A6B5Activity a6B5Activity;
    private List<A6B5_Class> mdataList;
    private double DEV_KLJ, DEV_KBT, DEV_KWR, DEV_KTJ, DEV_KFT, STD = 100;
    private String KTG_KLJ, KTG_KBT, KTG_KWR, KTG_KTJ, KTG_BLT, KTG_KFT, KTG_KLF;
    private static DecimalFormat df = new DecimalFormat("0.0");

    public class A6B5ViewHolder extends RecyclerView.ViewHolder {

        private TextView klj_data, klj_dev, kbt_data, kbt_dev, kwr_data, kwr_dev,
                ktj_data, ktj_dev, blt_hasil, kft_data, kft_dev, kft_hasil, rec_data;
        private LinearLayout blt_back, kft_back;
        private ImageView FT1;

        public A6B5ViewHolder(@NonNull View itemView) {
            super(itemView);

            klj_data = (TextView) itemView.findViewById(R.id.klj_data);
            klj_dev = (TextView) itemView.findViewById(R.id.klj_dev);
            kbt_data = (TextView) itemView.findViewById(R.id.kbt_data);
            kbt_dev = (TextView) itemView.findViewById(R.id.kbt_dev);
            kwr_data = (TextView) itemView.findViewById(R.id.kwr_data);
            kwr_dev = (TextView) itemView.findViewById(R.id.kwr_dev);
            ktj_data = (TextView) itemView.findViewById(R.id.ktj_data);
            ktj_dev = (TextView) itemView.findViewById(R.id.ktj_dev);
            blt_hasil = (TextView) itemView.findViewById(R.id.blt_hasil);

            kft_data = (TextView) itemView.findViewById(R.id.kft_data);
            kft_dev = (TextView) itemView.findViewById(R.id.kft_dev);
            kft_hasil = (TextView) itemView.findViewById(R.id.kft_hasil);

            rec_data = (TextView) itemView.findViewById(R.id.rec_data);

            FT1 = (ImageView) itemView.findViewById(R.id.FT1);

            blt_back = (LinearLayout) itemView.findViewById(R.id.blt_back);
            kft_back = (LinearLayout) itemView.findViewById(R.id.kft_back);

        }
    }

    public A6B5_Adapter(A6B5Activity a6B5Activity, List<A6B5_Class> mdataList) {
        this.a6B5Activity = a6B5Activity;
        this.mdataList = mdataList;
    }

    @NonNull
    @Override
    public A6B5_Adapter.A6B5ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.a6b5_layout, parent, false);
        A6B5_Adapter.A6B5ViewHolder a6b5 = new A6B5_Adapter.A6B5ViewHolder(view);
        return a6b5;
    }

    @Override
    public void onBindViewHolder(@NonNull A6B5_Adapter.A6B5ViewHolder holder, int position) {

        final A6B5_Class currentItem = mdataList.get(position);

        try {
            if(currentItem.getKLJ() == -1){
                DEV_KLJ = -1;
                KTG_KLJ = "-";
                holder.klj_data.setText("-");
                holder.klj_dev.setText("-");
            } else {
                if(currentItem.getKLJ() >= 0.6){
                    DEV_KLJ = 0;
                    KTG_KLJ = "LF";
                } else {
                    DEV_KLJ = (currentItem.getKLJ() - 0.6)/0.6;
                    DEV_KLJ = DEV_KLJ * 100;
                    if(DEV_KLJ < 0){
                        DEV_KLJ = DEV_KLJ * -1;
                    }
                    if(DEV_KLJ > 100){
                        DEV_KLJ = 100;
                    }
                    DEV_KLJ = Double.parseDouble(df.format(DEV_KLJ).replace(",", "."));
                    KTG_KLJ = "LS";
                }
                holder.klj_data.setText(String.valueOf(currentItem.getKLJ())+" m");
                holder.klj_dev.setText(String.valueOf(DEV_KLJ)+"%");
            }
        } catch (Exception e){
            Toast.makeText(a6B5Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            if(currentItem.getKBT() == -1){
                DEV_KBT = -1;
                KTG_KBT = "-";
                holder.kbt_data.setText("-");
                holder.kbt_dev.setText("-");
            } else {
                DEV_KBT = STD - currentItem.getKBT();
                if(DEV_KBT == 0){
                    KTG_KBT = "LF";
                } else {
                    KTG_KBT = "LS";
                }
                holder.kbt_data.setText(String.valueOf(currentItem.getKBT())+"%");
                holder.kbt_dev.setText(String.valueOf(DEV_KBT)+"%");
            }
        } catch (Exception e){
            Toast.makeText(a6B5Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            if(currentItem.getKWR() == -1){
                DEV_KWR = -1;
                KTG_KWR = "-";
                holder.kwr_data.setText("-");
                holder.kwr_dev.setText("-");
            } else {
                DEV_KWR = STD - currentItem.getKWR();
                if(DEV_KWR == 0){
                    KTG_KWR = "LF";
                } else {
                    KTG_KWR = "LS";
                }
                holder.kwr_data.setText(String.valueOf(currentItem.getKWR())+"%");
                holder.kwr_dev.setText(String.valueOf(DEV_KWR)+"%");
            }
        } catch (Exception e){
            Toast.makeText(a6B5Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            if(currentItem.getKTJ() == -1){
                DEV_KTJ = -1;
                KTG_KTJ = "-";
                holder.ktj_data.setText("-");
                holder.ktj_dev.setText("-");
            } else {
                DEV_KTJ = STD - currentItem.getKTJ();
                if(DEV_KTJ == 0){
                    KTG_KTJ = "LF";
                } else {
                    KTG_KTJ = "LS";
                }
                holder.ktj_data.setText(String.valueOf(currentItem.getKTJ())+"%");
                holder.ktj_dev.setText(String.valueOf(DEV_KTJ)+"%");
            }
        } catch (Exception e){
            Toast.makeText(a6B5Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            if((KTG_KLJ.equals("LF") || KTG_KLJ.equals("-")) && (KTG_KBT.equals("LF") || KTG_KBT.equals("-")) &&
                    (KTG_KWR.equals("LF") || KTG_KWR.equals("-")) && (KTG_KTJ.equals("LF") || KTG_KTJ.equals("-"))){

                if(KTG_KTJ.equals("-") && KTG_KBT.equals("-") && KTG_KWR.equals("-") && KTG_KTJ.equals("-")){
                    KTG_BLT = "-";
                    holder.blt_back.setBackground(a6B5Activity.getResources().getDrawable(R.drawable.null_style));
                } else {
                    KTG_BLT = "LF";
                    holder.blt_back.setBackground(a6B5Activity.getResources().getDrawable(R.drawable.success_style));
                }

            } else {
                KTG_BLT = "LS";
                holder.blt_back.setBackground(a6B5Activity.getResources().getDrawable(R.drawable.failed_style));
            }
            holder.blt_hasil.setText(KTG_BLT);
        } catch (Exception e){
            Toast.makeText(a6B5Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            if(currentItem.getKFT() == -1){
                DEV_KFT = -1;
                KTG_KFT = "-";
                holder.kft_data.setText("-");
                holder.kft_dev.setText("-");
                holder.kft_hasil.setText(KTG_KFT);
                holder.kft_back.setBackground(a6B5Activity.getResources().getDrawable(R.drawable.null_style));
            } else {
                DEV_KFT = STD - currentItem.getKFT();
                if(DEV_KFT == 0){
                    KTG_KFT = "LF";
                    holder.kft_back.setBackground(a6B5Activity.getResources().getDrawable(R.drawable.success_style));
                } else {
                    KTG_KFT = "LS";
                    holder.kft_back.setBackground(a6B5Activity.getResources().getDrawable(R.drawable.failed_style));
                }
                holder.kft_data.setText(String.valueOf(currentItem.getKFT())+"%");
                holder.kft_dev.setText(String.valueOf(DEV_KFT)+"%");
                holder.kft_hasil.setText(KTG_KFT);
            }
        } catch (Exception e){
            Toast.makeText(a6B5Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            if((KTG_BLT.equals("LF") || KTG_BLT.equals("-")) && (KTG_KFT.equals("LF") || KTG_KFT.equals("-"))){

                if(KTG_BLT.equals("-") && KTG_KFT.equals("-")){
                    KTG_KLF = "Tidak Dinilai";
                } else {
                    KTG_KLF = "LF";
                }

            } else {
                KTG_KLF = "LS";
            }
        } catch (Exception e){
            Toast.makeText(a6B5Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            holder.rec_data.setText(currentItem.getREC());
        } catch (Exception e){
            Toast.makeText(a6B5Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            holder.FT1.setImageBitmap(BitmapFactory.decodeFile(currentItem.getDIR1()));
        } catch (Exception e){
            Toast.makeText(a6B5Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            if(currentItem.getUPLOAD().equals("F")){
                a6B5Activity.FAB_UPLOAD.hide();
                a6B5Activity.UPLOAD.setImageResource(R.drawable.ic_file_upload_white_18dp);
                a6B5Activity.FAB_UPLOAD.setEnabled(true);
            } else {
                a6B5Activity.UPLOAD.setImageResource(R.drawable.ic_done_white_18dp);
                a6B5Activity.FAB_UPLOAD.setEnabled(false);
            }
        } catch (Exception e){
            Toast.makeText(a6B5Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            a6B5Activity.ID = currentItem.getID();
            a6B5Activity.KLJ_TXT = currentItem.getKLJ();
            a6B5Activity.KBT_TXT = currentItem.getKBT();
            a6B5Activity.KWR_TXT = currentItem.getKWR();
            a6B5Activity.KTJ_TXT = currentItem.getKTJ();
            a6B5Activity.KFT_TXT = currentItem.getKFT();
            a6B5Activity.REC_TXT = currentItem.getREC();
            a6B5Activity.DIR1 = currentItem.getDIR1();

            a6B5Activity.DEV_KLJ = DEV_KLJ;
            a6B5Activity.DEV_KBT = DEV_KBT;
            a6B5Activity.DEV_KWR = DEV_KWR;
            a6B5Activity.DEV_KTJ = DEV_KTJ;
            a6B5Activity.DEV_KFT = DEV_KFT;

            a6B5Activity.KTG_BLT = KTG_BLT;
            a6B5Activity.KTG_KFT = KTG_KFT;
            a6B5Activity.KTG_KLF = KTG_KLF;

            if(KTG_KLF.equals("LS")){
                a6B5Activity.klf.setText(KTG_KLF);
                a6B5Activity.klf.setBackground(a6B5Activity.getResources().getDrawable(R.drawable.failed_style));
                Animation animation = AnimationUtils.loadAnimation(a6B5Activity.getApplicationContext(), R.anim.recycle_bottom);
                a6B5Activity.klf.startAnimation(animation);
            } else if(KTG_KLF.equals("Tidak Dinilai")){
                a6B5Activity.klf.setText("Tidak Dinilai");
                a6B5Activity.klf.setBackground(a6B5Activity.getResources().getDrawable(R.drawable.null_style));
                Animation animation = AnimationUtils.loadAnimation(a6B5Activity.getApplicationContext(), R.anim.recycle_bottom);
                a6B5Activity.klf.startAnimation(animation);
            } else {
                a6B5Activity.klf.setText(KTG_KLF);
                a6B5Activity.klf.setBackground(a6B5Activity.getResources().getDrawable(R.drawable.success_style));
                Animation animation = AnimationUtils.loadAnimation(a6B5Activity.getApplicationContext(), R.anim.recycle_bottom);
                a6B5Activity.klf.startAnimation(animation);
            }
        } catch (Exception e){
            Toast.makeText(a6B5Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public int getItemCount() {
        return mdataList.size();
    }


}
