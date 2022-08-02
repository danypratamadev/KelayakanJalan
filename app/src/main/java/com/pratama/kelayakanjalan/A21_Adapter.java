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

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class A21_Adapter extends RecyclerView.Adapter<A21_Adapter.A21ViewHolder> {

    private A21Activity a21Activity;
    private List<A21_Class> mdataList;
    private double DEV_KSP, STD = 100;
    private String KTG_KSP, KTG_KLF;

    public class A21ViewHolder extends RecyclerView.ViewHolder {

        private TextView ksp_data, ksp_dev, ksp_hasil, rec_data;
        private LinearLayout ksp_back;
        private ImageView FT1, FT2;

        public A21ViewHolder(@NonNull View itemView) {
            super(itemView);

            ksp_data = (TextView) itemView.findViewById(R.id.ksp_data);
            ksp_dev = (TextView) itemView.findViewById(R.id.ksp_dev);
            ksp_hasil = (TextView) itemView.findViewById(R.id.ksp_hasil);
            rec_data = (TextView) itemView.findViewById(R.id.rec_data);

            FT1 = (ImageView) itemView.findViewById(R.id.FT1);
            FT2 = (ImageView) itemView.findViewById(R.id.FT2);

            ksp_back = (LinearLayout) itemView.findViewById(R.id.ksp_back);

        }
    }

    public A21_Adapter(A21Activity a21Activity, List<A21_Class> mdataList) {
        this.a21Activity = a21Activity;
        this.mdataList = mdataList;
    }

    @NonNull
    @Override
    public A21_Adapter.A21ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.a21_layout, parent, false);
        A21_Adapter.A21ViewHolder rsu = new A21_Adapter.A21ViewHolder(view);
        return rsu;
    }

    @Override
    public void onBindViewHolder(@NonNull A21_Adapter.A21ViewHolder holder, int position) {

        final A21_Class currentItem = mdataList.get(position);

        /*if(a21Activity.KPR.equals("Jalan Bebas Hambatan (JBH)")){
            if(currentItem.getKSP().equals("Super Paved") || currentItem.getKSP().equals("Beton Semen") ||
                    currentItem.getKSP().equals("Baton Aspal")){
                DEV_KSP = 0;
                KTG_KSP = "LF";
                KTG_KLF = "LF";
                holder.ksp_back.setBackground(a21Activity.getResources().getDrawable(R.drawable.success_style));
            } else {
                DEV_KSP = 100;
                KTG_KSP = "LS";
                KTG_KLF = "LS";
                holder.ksp_back.setBackground(a21Activity.getResources().getDrawable(R.drawable.failed_style));
            }
        } else if(a21Activity.KPR.equals("Jalan Raya (JR)")){
            if(currentItem.getKSP().equals("Super Paved") || currentItem.getKSP().equals("Beton Semen") ||
                    currentItem.getKSP().equals("Baton Aspal") || currentItem.getKSP().equals("Perkerasan Beraspal")){
                DEV_KSP = 0;
                KTG_KSP = "LF";
                KTG_KLF = "LF";
                holder.ksp_back.setBackground(a21Activity.getResources().getDrawable(R.drawable.success_style));
            } else {
                DEV_KSP = 100;
                KTG_KSP = "LS";
                KTG_KLF = "LS";
                holder.ksp_back.setBackground(a21Activity.getResources().getDrawable(R.drawable.failed_style));
            }
        } else if(a21Activity.KPR.equals("Jalan Sedang (JS)")){
            if(currentItem.getKSP().equals("Super Paved") || currentItem.getKSP().equals("Beton Semen") ||
                    currentItem.getKSP().equals("Baton Aspal") || currentItem.getKSP().equals("Perkerasan Beraspal")){
                DEV_KSP = 0;
                KTG_KSP = "LF";
                KTG_KLF = "LF";
                holder.ksp_back.setBackground(a21Activity.getResources().getDrawable(R.drawable.success_style));
            } else {
                DEV_KSP = 100;
                KTG_KSP = "LS";
                KTG_KLF = "LS";
                holder.ksp_back.setBackground(a21Activity.getResources().getDrawable(R.drawable.failed_style));
            }
        } else {
            if(currentItem.getKSP().equals("Super Paved") || currentItem.getKSP().equals("Beton Semen") ||
                    currentItem.getKSP().equals("Baton Aspal") || currentItem.getKSP().equals("Perkerasan Beraspal") ||
                    currentItem.getKSP().equals("Jalan Krikil") || currentItem.getKSP().equals("Tanah")){
                DEV_KSP = 0;
                KTG_KSP = "LF";
                KTG_KLF = "LF";
                holder.ksp_back.setBackground(a21Activity.getResources().getDrawable(R.drawable.success_style));
            } else {
                DEV_KSP = 100;
                KTG_KSP = "LS";
                KTG_KLF = "LS";
                holder.ksp_back.setBackground(a21Activity.getResources().getDrawable(R.drawable.failed_style));
            }
        }*/

        try {
            if(currentItem.getKSP() == -1){
                DEV_KSP = -1;
                KTG_KSP = "-";
                KTG_KLF = "Tidak Dinilai";
                holder.ksp_data.setText("-");
                holder.ksp_dev.setText("-");
                holder.ksp_hasil.setText("-");
                holder.ksp_back.setBackground(a21Activity.getResources().getDrawable(R.drawable.null_style));
            } else {
                DEV_KSP = STD - currentItem.getKSP();
                if(DEV_KSP == 0){
                    KTG_KSP = "LF";
                    KTG_KLF = "LF";
                    holder.ksp_back.setBackground(a21Activity.getResources().getDrawable(R.drawable.success_style));
                } else {
                    KTG_KSP = "LS";
                    KTG_KLF = "LS";
                    holder.ksp_back.setBackground(a21Activity.getResources().getDrawable(R.drawable.failed_style));
                }
                holder.ksp_data.setText(String.valueOf(currentItem.getKSP())+"%");
                holder.ksp_dev.setText(String.valueOf(DEV_KSP)+"%");
                holder.ksp_hasil.setText(KTG_KSP);
            }
        } catch (Exception e){
            Toast.makeText(a21Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            holder.rec_data.setText(currentItem.getREC());
        } catch (Exception e){
            Toast.makeText(a21Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            holder.FT1.setImageBitmap(BitmapFactory.decodeFile(currentItem.getDIR1()));
        } catch (Exception e){
            Toast.makeText(a21Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            holder.FT2.setImageBitmap(BitmapFactory.decodeFile(currentItem.getDIR2()));
        } catch (Exception e){
            Toast.makeText(a21Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            if(currentItem.getUPLOAD().equals("F")){
                a21Activity.FAB_UPLOAD.hide();
                a21Activity.UPLOAD.setImageResource(R.drawable.ic_file_upload_white_18dp);
                a21Activity.FAB_UPLOAD.setEnabled(true);
            } else {
                a21Activity.UPLOAD.setImageResource(R.drawable.ic_done_white_18dp);
                a21Activity.FAB_UPLOAD.setEnabled(false);
            }
        } catch (Exception e){
            Toast.makeText(a21Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            a21Activity.ID = currentItem.getID();
            a21Activity.KSP_TXT = currentItem.getKSP();
            a21Activity.REC_TXT = currentItem.getREC();
            a21Activity.DIR1 = currentItem.getDIR1();
            a21Activity.DIR2 = currentItem.getDIR2();

            a21Activity.DEV_KSP = DEV_KSP;
            a21Activity.KTG_KSP = KTG_KSP;
            a21Activity.KTG_KLF = KTG_KLF;

            if(KTG_KLF.equals("LS")){
                a21Activity.klf.setText(KTG_KLF);
                a21Activity.klf.setBackground(a21Activity.getResources().getDrawable(R.drawable.failed_style));
                Animation animation = AnimationUtils.loadAnimation(a21Activity.getApplicationContext(), R.anim.recycle_bottom);
                a21Activity.klf.startAnimation(animation);
            } else if(KTG_KLF.equals("Tidak Dinilai")){
                a21Activity.klf.setText("Tidak Dinilai");
                a21Activity.klf.setBackground(a21Activity.getResources().getDrawable(R.drawable.null_style));
                Animation animation = AnimationUtils.loadAnimation(a21Activity.getApplicationContext(), R.anim.recycle_bottom);
                a21Activity.klf.startAnimation(animation);
            } else {
                a21Activity.klf.setText(KTG_KLF);
                a21Activity.klf.setBackground(a21Activity.getResources().getDrawable(R.drawable.success_style));
                Animation animation = AnimationUtils.loadAnimation(a21Activity.getApplicationContext(), R.anim.recycle_bottom);
                a21Activity.klf.startAnimation(animation);
            }
        } catch (Exception e){
            Toast.makeText(a21Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public int getItemCount() {
        return mdataList.size();
    }

}
