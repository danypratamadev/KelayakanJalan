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

public class A6B6_Adapter extends RecyclerView.Adapter<A6B6_Adapter.A6B6ViewHolder> {

    private A6B6Activity a6B6Activity;
    private List<A6B6_Class> mdataList;
    private double DEV_PPK, DEV_KFP, DEV_KFP2, DEV_KFP3, STD = 100;
    private String KTG_PPK, KTG_KFP, KTG_KFP2, KTG_KFP3, KTG_KFPP, KTG_KLF;
    private static DecimalFormat df = new DecimalFormat("0.0");

    public class A6B6ViewHolder extends RecyclerView.ViewHolder {

        private TextView ppk_data, ppk_dev, ppk_hasil, kfp_data, kfp_dev, kfp2_data, kfp2_dev, kfp3_data, kfp3_dev, kfp_hasil, rec_data;
        private LinearLayout ppk_back, kfp_back;
        private ImageView FT1;

        public A6B6ViewHolder(@NonNull View itemView) {
            super(itemView);

            ppk_data = (TextView) itemView.findViewById(R.id.ppk_data);
            ppk_dev = (TextView) itemView.findViewById(R.id.ppk_dev);
            ppk_hasil = (TextView) itemView.findViewById(R.id.ppk_hasil);

            kfp_data = (TextView) itemView.findViewById(R.id.kfp_data);
            kfp_dev = (TextView) itemView.findViewById(R.id.kfp_dev);
            kfp2_data = (TextView) itemView.findViewById(R.id.kfp2_data);
            kfp2_dev = (TextView) itemView.findViewById(R.id.kfp2_dev);
            kfp3_data = (TextView) itemView.findViewById(R.id.kfp3_data);
            kfp3_dev = (TextView) itemView.findViewById(R.id.kfp3_dev);
            kfp_hasil = (TextView) itemView.findViewById(R.id.kfp_hasil);

            rec_data = (TextView) itemView.findViewById(R.id.rec_data);

            FT1 = (ImageView) itemView.findViewById(R.id.FT1);

            ppk_back = (LinearLayout) itemView.findViewById(R.id.ppk_back);
            kfp_back = (LinearLayout) itemView.findViewById(R.id.kfp_back);

        }
    }

    public A6B6_Adapter(A6B6Activity a6B6Activity, List<A6B6_Class> mdataList) {
        this.a6B6Activity = a6B6Activity;
        this.mdataList = mdataList;
    }

    @NonNull
    @Override
    public A6B6_Adapter.A6B6ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.a6b6_layout, parent, false);
        A6B6_Adapter.A6B6ViewHolder a6b6 = new A6B6_Adapter.A6B6ViewHolder(view);
        return a6b6;
    }

    @Override
    public void onBindViewHolder(@NonNull A6B6_Adapter.A6B6ViewHolder holder, int position) {

        final A6B6_Class currentItem = mdataList.get(position);

        try {
            if(currentItem.getPPk() == -1){
                DEV_PPK = -1;
                KTG_PPK = "-";
                holder.ppk_data.setText("-");
                holder.ppk_dev.setText("-");
                holder.ppk_hasil.setText(KTG_PPK);
                holder.ppk_back.setBackground(a6B6Activity.getResources().getDrawable(R.drawable.null_style));
            } else {
                DEV_PPK = STD - currentItem.getPPk();
                if(DEV_PPK == 0){
                    KTG_PPK = "LF";
                    holder.ppk_back.setBackground(a6B6Activity.getResources().getDrawable(R.drawable.success_style));
                } else {
                    KTG_PPK = "LS";
                    holder.ppk_back.setBackground(a6B6Activity.getResources().getDrawable(R.drawable.failed_style));
                }
                holder.ppk_data.setText(String.valueOf(currentItem.getPPk())+"%");
                holder.ppk_dev.setText(String.valueOf(DEV_PPK)+"%");
                holder.ppk_hasil.setText(KTG_PPK);
            }
        } catch (Exception e){
            Toast.makeText(a6B6Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            if(currentItem.getKFP() == -1){
                DEV_KFP = -1;
                KTG_KFP = "-";
                holder.kfp_data.setText("-");
                holder.kfp_dev.setText("-");
            } else {
                DEV_KFP = STD - currentItem.getKFP();
                if(DEV_KFP == 0){
                    KTG_KFP = "LF";
                } else {
                    KTG_KFP = "LS";
                }
                holder.kfp_data.setText(String.valueOf(currentItem.getKFP())+"%");
                holder.kfp_dev.setText(String.valueOf(DEV_KFP)+"%");
            }
        } catch (Exception e){
            Toast.makeText(a6B6Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            if(currentItem.getKFP2() == -1){
                DEV_KFP2 = -1;
                KTG_KFP2 = "-";
                holder.kfp2_data.setText("-");
                holder.kfp2_dev.setText("-");
            } else {
                if(currentItem.getKFP2() >= 0.9){
                    DEV_KFP2 = 0;
                    KTG_KFP2 = "LF";
                } else {
                    DEV_KFP2 = (currentItem.getKFP2() - 0.9)/0.9;
                    DEV_KFP2 = DEV_KFP2 * 100;
                    if(DEV_KFP2 < 0){
                        DEV_KFP2 = DEV_KFP2 * -1;
                    }
                    if(DEV_KFP2 > 100){
                        DEV_KFP2 = 100;
                    }
                    DEV_KFP2 = Double.parseDouble(df.format(DEV_KFP2).replace(",", "."));
                    KTG_KFP2 = "LS";
                }
                holder.kfp2_data.setText(String.valueOf(currentItem.getKFP2())+" m");
                holder.kfp2_dev.setText(String.valueOf(DEV_KFP2)+"%");
            }
        } catch (Exception e){
            Toast.makeText(a6B6Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            if(currentItem.getKFP3() == -1){
                DEV_KFP3 = -1;
                KTG_KFP3 = "-";
                holder.kfp3_data.setText("-");
                holder.kfp3_dev.setText("-");
            } else {
                DEV_KFP3 = STD - currentItem.getKFP3();
                if(DEV_KFP3 == 0){
                    KTG_KFP3 = "LF";
                } else {
                    KTG_KFP3 = "LS";
                }
                holder.kfp3_data.setText(String.valueOf(currentItem.getKFP3())+"%");
                holder.kfp3_dev.setText(String.valueOf(DEV_KFP3)+"%");
            }
        } catch (Exception e){
            Toast.makeText(a6B6Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            if((KTG_KFP.equals("LF") || KTG_KFP.equals("-")) && (KTG_KFP2.equals("LF") || KTG_KFP2.equals("-")) && (KTG_KFP3.equals("LF") || KTG_KFP3.equals("-"))){

                if(KTG_KFP.equals("-") && KTG_KFP2.equals("-") && KTG_KFP3.equals("-")){
                    KTG_KFPP = "-";
                    holder.kfp_back.setBackground(a6B6Activity.getResources().getDrawable(R.drawable.null_style));
                } else {
                    KTG_KFPP = "LF";
                    holder.kfp_back.setBackground(a6B6Activity.getResources().getDrawable(R.drawable.success_style));
                }

            } else {
                KTG_KFPP = "LS";
                holder.kfp_back.setBackground(a6B6Activity.getResources().getDrawable(R.drawable.failed_style));
            }
            holder.kfp_hasil.setText(KTG_KFPP);
        } catch (Exception e){
            Toast.makeText(a6B6Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            if((KTG_PPK.equals("LF") || KTG_PPK.equals("-")) && (KTG_KFPP.equals("LF") || KTG_KFPP.equals("-"))){

                if(KTG_PPK.equals("-") && KTG_KFPP.equals("-")){
                    KTG_KLF = "Tidak Dinilai";
                } else {
                    KTG_KLF = "LF";
                }

            } else {
                KTG_KLF = "LS";
            }
        } catch (Exception e){
            Toast.makeText(a6B6Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            holder.rec_data.setText(currentItem.getREC());
        } catch (Exception e){
            Toast.makeText(a6B6Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            holder.FT1.setImageBitmap(BitmapFactory.decodeFile(currentItem.getDIR1()));
        } catch (Exception e){
            Toast.makeText(a6B6Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            if(currentItem.getUPLOAD().equals("F")){
                a6B6Activity.FAB_UPLOAD.hide();
                a6B6Activity.UPLOAD.setImageResource(R.drawable.ic_file_upload_white_18dp);
                a6B6Activity.FAB_UPLOAD.setEnabled(true);
            } else {
                a6B6Activity.UPLOAD.setImageResource(R.drawable.ic_done_white_18dp);
                a6B6Activity.FAB_UPLOAD.setEnabled(false);
            }
        } catch (Exception e){
            Toast.makeText(a6B6Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            a6B6Activity.ID = currentItem.getID();
            a6B6Activity.PPK_TXT = currentItem.getPPk();
            a6B6Activity.KFP_TXT = currentItem.getKFP();
            a6B6Activity.KFP2_TXT = currentItem.getKFP2();
            a6B6Activity.KFP3_TXT = currentItem.getKFP3();
            a6B6Activity.REC_TXT = currentItem.getREC();
            a6B6Activity.DIR1 = currentItem.getDIR1();

            a6B6Activity.DEV_PPK = DEV_PPK;
            a6B6Activity.DEV_KFP = DEV_KFP;
            a6B6Activity.DEV_KFP2 = DEV_KFP2;
            a6B6Activity.DEV_KFP3 = DEV_KFP3;

            a6B6Activity.KTG_PPK = KTG_PPK;
            a6B6Activity.KTG_KFPP = KTG_KFPP;
            a6B6Activity.KTG_KLF = KTG_KLF;

            if(KTG_KLF.equals("LS")){
                a6B6Activity.klf.setText(KTG_KLF);
                a6B6Activity.klf.setBackground(a6B6Activity.getResources().getDrawable(R.drawable.failed_style));
                Animation animation = AnimationUtils.loadAnimation(a6B6Activity.getApplicationContext(), R.anim.recycle_bottom);
                a6B6Activity.klf.startAnimation(animation);
            } else if(KTG_KLF.equals("Tidak Dinilai")){
                a6B6Activity.klf.setText("Tidak Dinilai");
                a6B6Activity.klf.setBackground(a6B6Activity.getResources().getDrawable(R.drawable.null_style));
                Animation animation = AnimationUtils.loadAnimation(a6B6Activity.getApplicationContext(), R.anim.recycle_bottom);
                a6B6Activity.klf.startAnimation(animation);
            } else {
                a6B6Activity.klf.setText(KTG_KLF);
                a6B6Activity.klf.setBackground(a6B6Activity.getResources().getDrawable(R.drawable.success_style));
                Animation animation = AnimationUtils.loadAnimation(a6B6Activity.getApplicationContext(), R.anim.recycle_bottom);
                a6B6Activity.klf.startAnimation(animation);
            }
        } catch (Exception e){
            Toast.makeText(a6B6Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public int getItemCount() {
        return mdataList.size();
    }

}
