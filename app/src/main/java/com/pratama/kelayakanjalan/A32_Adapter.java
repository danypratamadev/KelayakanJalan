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

public class A32_Adapter extends RecyclerView.Adapter<A32_Adapter.A32ViewHolder> {

    private A32Activity a32Activity;
    private List<A32_Class> mdataList;
    private double DEV_FNG, DEV_KTP, DEV_KRP, STD = 100;
    private String KTG_FNG, KTG_KTP, KTG_KRP, KTG_KLF;

    public class A32ViewHolder extends RecyclerView.ViewHolder {

        private TextView fng_data, fng_dev, fng_hasil, ktp_data, ktp_dev, ktp_hasil, krp_data, krp_dev,
                krp_hasil, rec_data;
        private LinearLayout fng_back, ktp_back, krp_back;
        private ImageView FT1;

        public A32ViewHolder(@NonNull View itemView) {
            super(itemView);

            fng_data = (TextView) itemView.findViewById(R.id.fng_data);
            fng_dev = (TextView) itemView.findViewById(R.id.fng_dev);
            fng_hasil = (TextView) itemView.findViewById(R.id.fng_hasil);

            ktp_data = (TextView) itemView.findViewById(R.id.ktp_data);
            ktp_dev = (TextView) itemView.findViewById(R.id.ktp_dev);
            ktp_hasil = (TextView) itemView.findViewById(R.id.ktp_hasil);

            krp_data = (TextView) itemView.findViewById(R.id.krp_data);
            krp_dev= (TextView) itemView.findViewById(R.id.krp_dev);
            krp_hasil = (TextView) itemView.findViewById(R.id.krp_hasil);

            rec_data = (TextView) itemView.findViewById(R.id.rec_data);

            FT1 = (ImageView) itemView.findViewById(R.id.FT1);

            fng_back = (LinearLayout) itemView.findViewById(R.id.fng_back);
            ktp_back = (LinearLayout) itemView.findViewById(R.id.ktp_back);
            krp_back = (LinearLayout) itemView.findViewById(R.id.krp_back);

        }
    }

    public A32_Adapter(A32Activity a32Activity, List<A32_Class> mdataList) {
        this.a32Activity = a32Activity;
        this.mdataList = mdataList;
    }

    @NonNull
    @Override
    public A32ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.a32_layout, parent, false);
        A32_Adapter.A32ViewHolder a32 = new A32_Adapter.A32ViewHolder(view);
        return a32;
    }

    @Override
    public void onBindViewHolder(@NonNull A32ViewHolder holder, int position) {

        final A32_Class currentItem = mdataList.get(position);

        try {
            if(currentItem.getFNG() == -1){
                holder.fng_data.setText("-");
                DEV_FNG = -1;
                KTG_FNG = "-";
                holder.fng_dev.setText("-");
                holder.fng_hasil.setText(KTG_FNG);
                holder.fng_back.setBackground(a32Activity.getResources().getDrawable(R.drawable.null_style));
            } else {
                holder.fng_data.setText(String.valueOf(currentItem.getFNG())+"%");
                DEV_FNG = STD - currentItem.getFNG();
                if(DEV_FNG == 0){
                    KTG_FNG = "LF";
                    holder.fng_back.setBackground(a32Activity.getResources().getDrawable(R.drawable.success_style));
                } else {
                    KTG_FNG = "LS";
                    holder.fng_back.setBackground(a32Activity.getResources().getDrawable(R.drawable.failed_style));
                }
                holder.fng_dev.setText(String.valueOf(DEV_FNG)+"%");
                holder.fng_hasil.setText(KTG_FNG);
            }
        } catch (Exception e){
            Toast.makeText(a32Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            if(currentItem.getKTP() == -1){
                holder.ktp_data.setText("-");
                DEV_KTP = -1;
                KTG_KTP = "-";
                holder.ktp_dev.setText("-");
                holder.ktp_hasil.setText(KTG_KTP);
                holder.ktp_back.setBackground(a32Activity.getResources().getDrawable(R.drawable.null_style));
            } else {
                holder.ktp_data.setText(String.valueOf(currentItem.getKTP())+"%");
                DEV_KTP = STD - currentItem.getKTP();
                if(DEV_KTP == 0){
                    KTG_KTP = "LF";
                    holder.ktp_back.setBackground(a32Activity.getResources().getDrawable(R.drawable.success_style));
                } else {
                    KTG_KTP = "LS";
                    holder.ktp_back.setBackground(a32Activity.getResources().getDrawable(R.drawable.failed_style));
                }
                holder.ktp_dev.setText(String.valueOf(DEV_KTP)+"%");
                holder.ktp_hasil.setText(KTG_KTP);
            }
        } catch (Exception e){
            Toast.makeText(a32Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            if(currentItem.getKRP() == -1){
                holder.krp_data.setText("-");
                DEV_KRP = -1;
                KTG_KRP = "-";
                holder.krp_dev.setText("-");
                holder.krp_hasil.setText(KTG_KRP);
                holder.krp_back.setBackground(a32Activity.getResources().getDrawable(R.drawable.null_style));
            } else {
                holder.krp_data.setText(String.valueOf(currentItem.getKRP())+"%");
                DEV_KRP = STD - currentItem.getKRP();
                if(DEV_KRP == 0){
                    KTG_KRP = "LF";
                    holder.krp_back.setBackground(a32Activity.getResources().getDrawable(R.drawable.success_style));
                } else {
                    KTG_KRP = "LS";
                    holder.krp_back.setBackground(a32Activity.getResources().getDrawable(R.drawable.failed_style));
                }
                holder.krp_dev.setText(String.valueOf(DEV_KRP)+"%");
                holder.krp_hasil.setText(KTG_KRP);
            }
        } catch (Exception e){
            Toast.makeText(a32Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            if((KTG_FNG.equals("LF") || KTG_FNG.equals("-")) && (KTG_KTP.equals("LF") || KTG_KTP.equals("-")) &&
                    (KTG_KRP.equals("LF") || KTG_KRP.equals("-"))){

                if(KTG_FNG.equals("-") && KTG_KTP.equals("-") && KTG_KRP.equals("-")){
                    KTG_KLF = "Tidak Dinilai";
                } else {
                    KTG_KLF = "LF";
                }

            } else {
                KTG_KLF = "LS";
            }
        } catch (Exception e){
            Toast.makeText(a32Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            holder.rec_data.setText(currentItem.getREC());
        } catch (Exception e){
            Toast.makeText(a32Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            holder.FT1.setImageBitmap(BitmapFactory.decodeFile(currentItem.getDIR1()));
        } catch (Exception e){
            Toast.makeText(a32Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            if(currentItem.getUPLOAD().equals("F")){
                a32Activity.FAB_UPLOAD.hide();
                a32Activity.UPLOAD.setImageResource(R.drawable.ic_file_upload_white_18dp);
                a32Activity.FAB_UPLOAD.setEnabled(true);
            } else {
                a32Activity.UPLOAD.setImageResource(R.drawable.ic_done_white_18dp);
                a32Activity.FAB_UPLOAD.setEnabled(false);
            }
        } catch (Exception e){
            Toast.makeText(a32Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            a32Activity.ID = currentItem.getID();
            a32Activity.FNG_TXT = currentItem.getFNG();
            a32Activity.KTP_TXT = currentItem.getKTP();
            a32Activity.KRP_TXT = currentItem.getKRP();
            a32Activity.REC_TXT = currentItem.getREC();
            a32Activity.DIR1 = currentItem.getDIR1();

            a32Activity.DEV_FNG = DEV_FNG;
            a32Activity.DEV_KTP = DEV_KTP;
            a32Activity.DEV_KRP = DEV_KRP;

            a32Activity.KTG_FNG = KTG_FNG;
            a32Activity.KTG_KTP = KTG_KTP;
            a32Activity.KTG_KRP = KTG_KRP;
            a32Activity.KTG_KLF = KTG_KLF;

            if(KTG_KLF.equals("LS")){
                a32Activity.klf.setText(KTG_KLF);
                a32Activity.klf.setBackground(a32Activity.getResources().getDrawable(R.drawable.failed_style));
                Animation animation = AnimationUtils.loadAnimation(a32Activity.getApplicationContext(), R.anim.recycle_bottom);
                a32Activity.klf.startAnimation(animation);
            } else if(KTG_KLF.equals("Tidak Dinilai")){
                a32Activity.klf.setText("Tidak Dinilai");
                a32Activity.klf.setBackground(a32Activity.getResources().getDrawable(R.drawable.null_style));
                Animation animation = AnimationUtils.loadAnimation(a32Activity.getApplicationContext(), R.anim.recycle_bottom);
                a32Activity.klf.startAnimation(animation);
            } else {
                a32Activity.klf.setText(KTG_KLF);
                a32Activity.klf.setBackground(a32Activity.getResources().getDrawable(R.drawable.success_style));
                Animation animation = AnimationUtils.loadAnimation(a32Activity.getApplicationContext(), R.anim.recycle_bottom);
                a32Activity.klf.startAnimation(animation);
            }
        } catch (Exception e){
            Toast.makeText(a32Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public int getItemCount() {
        return mdataList.size();
    }

}
