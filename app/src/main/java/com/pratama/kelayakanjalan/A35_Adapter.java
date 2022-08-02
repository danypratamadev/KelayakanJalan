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

public class A35_Adapter extends RecyclerView.Adapter<A35_Adapter.A35ViewHolder> {

    private A35Activity a35Activity;
    private List<A35_Class> mdataList;
    private double DEV_KKT, DEV_KEL, DEV_SAR, STD = 100;
    private String KTG_KKT, KTG_KEL, KTG_SAR, KTG_KLF;

    public class A35ViewHolder extends RecyclerView.ViewHolder {

        private TextView kkt_data, kkt_dev, kkt_hasil, kel_data, kel_dev, kel_hasil, sar_data, sar_dev,
                sar_hasil, rec_data;
        private LinearLayout kkt_back, kel_back, sar_back;
        private ImageView FT1, FT2;

        public A35ViewHolder(@NonNull View itemView) {
            super(itemView);

            kkt_data = (TextView) itemView.findViewById(R.id.kkt_data);
            kkt_dev = (TextView) itemView.findViewById(R.id.kkt_dev);
            kkt_hasil = (TextView) itemView.findViewById(R.id.kkt_hasil);

            kel_data = (TextView) itemView.findViewById(R.id.kel_data);
            kel_dev = (TextView) itemView.findViewById(R.id.kel_dev);
            kel_hasil = (TextView) itemView.findViewById(R.id.kel_hasil);

            sar_data = (TextView) itemView.findViewById(R.id.sar_data);
            sar_dev = (TextView) itemView.findViewById(R.id.sar_dev);
            sar_hasil = (TextView) itemView.findViewById(R.id.sar_hasil);

            rec_data = (TextView) itemView.findViewById(R.id.rec_data);

            FT1 = (ImageView) itemView.findViewById(R.id.FT1);
            FT2 = (ImageView) itemView.findViewById(R.id.FT2);

            kkt_back = (LinearLayout) itemView.findViewById(R.id.kkt_back);
            kel_back = (LinearLayout) itemView.findViewById(R.id.kel_back);
            sar_back = (LinearLayout) itemView.findViewById(R.id.sar_back);

        }
    }

    public A35_Adapter(A35Activity a35Activity, List<A35_Class> mdataList) {
        this.a35Activity = a35Activity;
        this.mdataList = mdataList;
    }

    @NonNull
    @Override
    public A35_Adapter.A35ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.a35_layout, parent, false);
        A35_Adapter.A35ViewHolder a35 = new A35_Adapter.A35ViewHolder(view);
        return a35;
    }

    @Override
    public void onBindViewHolder(@NonNull A35_Adapter.A35ViewHolder holder, int position) {

        final A35_Class currentItem = mdataList.get(position);

        try {
            if(currentItem.getKKT() == -1){
                holder.kkt_data.setText("-");
                DEV_KKT = -1;
                KTG_KKT = "-";
                holder.kkt_dev.setText("-");
                holder.kkt_hasil.setText(KTG_KKT);
                holder.kkt_back.setBackground(a35Activity.getResources().getDrawable(R.drawable.null_style));
            } else {
                holder.kkt_data.setText(String.valueOf(currentItem.getKKT())+"%");
                DEV_KKT = STD - currentItem.getKKT();
                if(DEV_KKT == 0){
                    KTG_KKT = "LF";
                    holder.kkt_back.setBackground(a35Activity.getResources().getDrawable(R.drawable.success_style));
                } else {
                    KTG_KKT = "LS";
                    holder.kkt_back.setBackground(a35Activity.getResources().getDrawable(R.drawable.failed_style));
                }
                holder.kkt_dev.setText(String.valueOf(DEV_KKT)+"%");
                holder.kkt_hasil.setText(KTG_KKT);
            }
        } catch (Exception e){
            Toast.makeText(a35Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            if(currentItem.getKEL() == -1){
                holder.kel_data.setText("-");
                DEV_KEL = -1;
                KTG_KEL = "-";
                holder.kel_dev.setText("-");
                holder.kel_hasil.setText(KTG_KEL);
                holder.kel_back.setBackground(a35Activity.getResources().getDrawable(R.drawable.null_style));
            } else {
                holder.kel_data.setText(String.valueOf(currentItem.getKEL())+"%");
                DEV_KEL = STD - currentItem.getKEL();
                if(DEV_KEL == 0){
                    KTG_KEL = "LF";
                    holder.kel_back.setBackground(a35Activity.getResources().getDrawable(R.drawable.success_style));
                } else {
                    KTG_KEL = "LS";
                    holder.kel_back.setBackground(a35Activity.getResources().getDrawable(R.drawable.failed_style));
                }
                holder.kel_dev.setText(String.valueOf(DEV_KEL)+"%");
                holder.kel_hasil.setText(KTG_KEL);
            }
        } catch (Exception e){
            Toast.makeText(a35Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            if(currentItem.getSAR() == -1){
                holder.sar_data.setText("-");
                DEV_SAR = -1;
                KTG_SAR = "-";
                holder.sar_dev.setText("-");
                holder.sar_hasil.setText(KTG_SAR);
                holder.sar_back.setBackground(a35Activity.getResources().getDrawable(R.drawable.null_style));
            } else {
                holder.sar_data.setText(String.valueOf(currentItem.getSAR())+"%");
                DEV_SAR = STD - currentItem.getSAR();
                if(DEV_SAR == 0){
                    KTG_SAR = "LF";
                    holder.sar_back.setBackground(a35Activity.getResources().getDrawable(R.drawable.success_style));
                } else {
                    KTG_SAR = "LS";
                    holder.sar_back.setBackground(a35Activity.getResources().getDrawable(R.drawable.failed_style));
                }
                holder.sar_dev.setText(String.valueOf(DEV_SAR)+"%");
                holder.sar_hasil.setText(KTG_SAR);
            }
        } catch (Exception e){
            Toast.makeText(a35Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            if((KTG_KKT.equals("LF") || KTG_KKT.equals("-")) && (KTG_KEL.equals("LF") || KTG_KEL.equals("-")) &&
                    (KTG_SAR.equals("LF") || KTG_SAR.equals("-"))){

                if(KTG_KKT.equals("-") && KTG_KEL.equals("-") && KTG_SAR.equals("-")){
                    KTG_KLF = "Tidak Dinilai";
                } else {
                    KTG_KLF = "LF";
                }

            } else {
                KTG_KLF = "LS";
            }
        } catch (Exception e){
            Toast.makeText(a35Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            holder.rec_data.setText(currentItem.getREC());
        } catch (Exception e){
            Toast.makeText(a35Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            holder.FT1.setImageBitmap(BitmapFactory.decodeFile(currentItem.getDIR1()));
        } catch (Exception e){
            Toast.makeText(a35Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            holder.FT2.setImageBitmap(BitmapFactory.decodeFile(currentItem.getDIR2()));
        } catch (Exception e){
            Toast.makeText(a35Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            if(currentItem.getUPLOAD().equals("F")){
                a35Activity.FAB_UPLOAD.hide();
                a35Activity.UPLOAD.setImageResource(R.drawable.ic_file_upload_white_18dp);
                a35Activity.FAB_UPLOAD.setEnabled(true);
            } else {
                a35Activity.UPLOAD.setImageResource(R.drawable.ic_done_white_18dp);
                a35Activity.FAB_UPLOAD.setEnabled(false);
            }
        } catch (Exception e){
            Toast.makeText(a35Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            a35Activity.ID = currentItem.getID();
            a35Activity.KKT_TXT = currentItem.getKKT();
            a35Activity.KEL_TXT = currentItem.getKEL();
            a35Activity.SAR_TXT = currentItem.getSAR();
            a35Activity.REC_TXT = currentItem.getREC();
            a35Activity.DIR1 = currentItem.getDIR1();
            a35Activity.DIR2 = currentItem.getDIR2();

            a35Activity.DEV_KKT = DEV_KKT;
            a35Activity.DEV_KEL = DEV_KEL;
            a35Activity.DEV_SAR = DEV_SAR;

            a35Activity.KTG_KKT = KTG_KKT;
            a35Activity.KTG_KEL = KTG_KEL;
            a35Activity.KTG_SAR = KTG_SAR;
            a35Activity.KTG_KLF = KTG_KLF;

            if(KTG_KLF.equals("LS")){
                a35Activity.klf.setText(KTG_KLF);
                a35Activity.klf.setBackground(a35Activity.getResources().getDrawable(R.drawable.failed_style));
                Animation animation = AnimationUtils.loadAnimation(a35Activity.getApplicationContext(), R.anim.recycle_bottom);
                a35Activity.klf.startAnimation(animation);
            } else if(KTG_KLF.equals("Tidak Dinilai")){
                a35Activity.klf.setText("Tidak Dinilai");
                a35Activity.klf.setBackground(a35Activity.getResources().getDrawable(R.drawable.null_style));
                Animation animation = AnimationUtils.loadAnimation(a35Activity.getApplicationContext(), R.anim.recycle_bottom);
                a35Activity.klf.startAnimation(animation);
            } else {
                a35Activity.klf.setText(KTG_KLF);
                a35Activity.klf.setBackground(a35Activity.getResources().getDrawable(R.drawable.success_style));
                Animation animation = AnimationUtils.loadAnimation(a35Activity.getApplicationContext(), R.anim.recycle_bottom);
                a35Activity.klf.startAnimation(animation);
            }
        } catch (Exception e){
            Toast.makeText(a35Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public int getItemCount() {
        return mdataList.size();
    }

}
