package com.pratama.kelayakanjalan;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.chauthai.overscroll.RecyclerViewBouncy;

import java.util.List;

public class Administrasi_Adapter extends RecyclerViewBouncy.Adapter<Administrasi_Adapter.AdminViewHolder> {

    private AdministrasiActivity administrasiActivity;
    private List<Administrasi_Class> mdataList;
    private String KTG_KLF1, KTG_KLF2, KTG_KLF3, KTG_KLF4, KTG_KLF5, KTG_KLF6, KTG_KLF;

    public class AdminViewHolder extends RecyclerView.ViewHolder {

        private TextView ktd1, kli1, lgt1, klf1, ktd2, kli2, lgt2, klf2, ktd3, kli3, lgt3, klf3, ktd4, kli4, lgt4, klf4,
                ktd5, kli5, lgt5, klf5, ktd6, kli6, lgt6, klf6, rec, rec2, rec3, rec4, rec5, rec6;

        private LinearLayout satu, dua, tiga, empat, lima, enam;

        public AdminViewHolder(@NonNull View itemView) {
            super(itemView);

            ktd1 = (TextView) itemView.findViewById(R.id.ktd);
            kli1 = (TextView) itemView.findViewById(R.id.kli);
            lgt1 = (TextView) itemView.findViewById(R.id.lgt);
            klf1 = (TextView) itemView.findViewById(R.id.klf1);
            rec = (TextView) itemView.findViewById(R.id.rec_data);

            ktd2 = (TextView) itemView.findViewById(R.id.ktd2);
            kli2 = (TextView) itemView.findViewById(R.id.kli2);
            lgt2 = (TextView) itemView.findViewById(R.id.lgt2);
            klf2 = (TextView) itemView.findViewById(R.id.klf2);
            rec2 = (TextView) itemView.findViewById(R.id.rec_data2);

            ktd3 = (TextView) itemView.findViewById(R.id.ktd3);
            kli3 = (TextView) itemView.findViewById(R.id.kli3);
            lgt3 = (TextView) itemView.findViewById(R.id.lgt3);
            klf3 = (TextView) itemView.findViewById(R.id.klf3);
            rec3 = (TextView) itemView.findViewById(R.id.rec_data3);

            ktd4 = (TextView) itemView.findViewById(R.id.ktd4);
            kli4 = (TextView) itemView.findViewById(R.id.kli4);
            lgt4 = (TextView) itemView.findViewById(R.id.lgt4);
            klf4 = (TextView) itemView.findViewById(R.id.klf4);
            rec4 = (TextView) itemView.findViewById(R.id.rec_data4);

            ktd5 = (TextView) itemView.findViewById(R.id.ktd5);
            kli5 = (TextView) itemView.findViewById(R.id.kli5);
            lgt5 = (TextView) itemView.findViewById(R.id.lgt5);
            klf5 = (TextView) itemView.findViewById(R.id.klf5);
            rec5 = (TextView) itemView.findViewById(R.id.rec_data5);

            ktd6 = (TextView) itemView.findViewById(R.id.ktd6);
            kli6 = (TextView) itemView.findViewById(R.id.kli6);
            lgt6 = (TextView) itemView.findViewById(R.id.lgt6);
            klf6 = (TextView) itemView.findViewById(R.id.klf6);
            rec6 = (TextView) itemView.findViewById(R.id.rec_data6);

            satu = (LinearLayout) itemView.findViewById(R.id.satu_back);
            dua = (LinearLayout) itemView.findViewById(R.id.dua_back);
            tiga = (LinearLayout) itemView.findViewById(R.id.tiga_back);
            empat = (LinearLayout) itemView.findViewById(R.id.empat_back);
            lima = (LinearLayout) itemView.findViewById(R.id.lima_back);
            enam = (LinearLayout) itemView.findViewById(R.id.enam_back);

        }

    }

    public Administrasi_Adapter(AdministrasiActivity administrasiActivity, List<Administrasi_Class> mdataList) {
        this.administrasiActivity = administrasiActivity;
        this.mdataList = mdataList;
    }

    @NonNull
    @Override
    public AdminViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.admin_layout, parent, false);
        Administrasi_Adapter.AdminViewHolder admin = new Administrasi_Adapter.AdminViewHolder(view);
        return admin;
    }

    @Override
    public void onBindViewHolder(@NonNull AdminViewHolder holder, int position) {

        Administrasi_Class currentItem = mdataList.get(position);

        try {
            if(currentItem.getKTD().equals("Ada") && currentItem.getKLI().equals("Lengkap") && currentItem.getLGT().equals("Legal")){
                KTG_KLF1 = "LF";
                holder.satu.setBackground(administrasiActivity.getResources().getDrawable(R.drawable.success_style));
            } else {
                KTG_KLF1 = "LS";
                holder.satu.setBackground(administrasiActivity.getResources().getDrawable(R.drawable.failed_style));
            }
            holder.ktd1.setText(currentItem.getKTD());
            holder.kli1.setText(currentItem.getKLI());
            holder.lgt1.setText(currentItem.getLGT());
            holder.rec.setText(currentItem.getREC());
            holder.klf1.setText(KTG_KLF1);
        } catch (Exception e){
            Toast.makeText(administrasiActivity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            if(currentItem.getKTD2().equals("Ada") && currentItem.getKLI2().equals("Lengkap") && currentItem.getLGT2().equals("Legal")){
                KTG_KLF2 = "LF";
                holder.dua.setBackground(administrasiActivity.getResources().getDrawable(R.drawable.success_style));
            } else {
                KTG_KLF2 = "LS";
                holder.dua.setBackground(administrasiActivity.getResources().getDrawable(R.drawable.failed_style));
            }
            holder.ktd2.setText(currentItem.getKTD2());
            holder.kli2.setText(currentItem.getKLI2());
            holder.lgt2.setText(currentItem.getLGT2());
            holder.rec2.setText(currentItem.getREC2());
            holder.klf2.setText(KTG_KLF2);
        } catch (Exception e){
            Toast.makeText(administrasiActivity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            if(currentItem.getKTD3().equals("Ada") && currentItem.getKLI3().equals("Lengkap") && currentItem.getLGT3().equals("Legal")){
                KTG_KLF3 = "LF";
                holder.tiga.setBackground(administrasiActivity.getResources().getDrawable(R.drawable.success_style));
            } else {
                KTG_KLF3 = "LS";
                holder.tiga.setBackground(administrasiActivity.getResources().getDrawable(R.drawable.failed_style));
            }
            holder.ktd3.setText(currentItem.getKTD3());
            holder.kli3.setText(currentItem.getKLI3());
            holder.lgt3.setText(currentItem.getLGT3());
            holder.rec3.setText(currentItem.getREC3());
            holder.klf3.setText(KTG_KLF3);
        } catch (Exception e){
            Toast.makeText(administrasiActivity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            if(currentItem.getKTD4().equals("Ada") && currentItem.getKLI4().equals("Lengkap") && currentItem.getLGT4().equals("Legal")){
                KTG_KLF4 = "LF";
                holder.empat.setBackground(administrasiActivity.getResources().getDrawable(R.drawable.success_style));
            } else {
                KTG_KLF4 = "LS";
                holder.empat.setBackground(administrasiActivity.getResources().getDrawable(R.drawable.failed_style));
            }
            holder.ktd4.setText(currentItem.getKTD4());
            holder.kli4.setText(currentItem.getKLI4());
            holder.lgt4.setText(currentItem.getLGT4());
            holder.rec4.setText(currentItem.getREC4());
            holder.klf4.setText(KTG_KLF4);
        } catch (Exception e){
            Toast.makeText(administrasiActivity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            if(currentItem.getKTD5().equals("Ada") && currentItem.getKLI5().equals("Lengkap") && currentItem.getLGT5().equals("Legal")){
                KTG_KLF5 = "LF";
                holder.lima.setBackground(administrasiActivity.getResources().getDrawable(R.drawable.success_style));
            } else {
                KTG_KLF5 = "LS";
                holder.lima.setBackground(administrasiActivity.getResources().getDrawable(R.drawable.failed_style));
            }
            holder.ktd5.setText(currentItem.getKTD5());
            holder.kli5.setText(currentItem.getKLI5());
            holder.lgt5.setText(currentItem.getLGT5());
            holder.rec5.setText(currentItem.getREC5());
            holder.klf5.setText(KTG_KLF5);
        } catch (Exception e){
            Toast.makeText(administrasiActivity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            if(currentItem.getKTD6().equals("Ada") && currentItem.getKLI6().equals("Lengkap") && currentItem.getLGT6().equals("Legal")){
                KTG_KLF6 = "LF";
                holder.enam.setBackground(administrasiActivity.getResources().getDrawable(R.drawable.success_style));
            } else {
                KTG_KLF6 = "LS";
                holder.enam.setBackground(administrasiActivity.getResources().getDrawable(R.drawable.failed_style));
            }
            holder.ktd6.setText(currentItem.getKTD6());
            holder.kli6.setText(currentItem.getKLI6());
            holder.lgt6.setText(currentItem.getLGT6());
            holder.rec6.setText(currentItem.getREC6());
            holder.klf6.setText(KTG_KLF6);
        } catch (Exception e){
            Toast.makeText(administrasiActivity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            if(KTG_KLF1.equals("LF") && KTG_KLF2.equals("LF") && KTG_KLF3.equals("LF") && KTG_KLF4.equals("LF") &&
                    KTG_KLF5.equals("LF") && KTG_KLF6.equals("LF")){
                KTG_KLF = "LF";
            } else {
                KTG_KLF = "LS";
            }
        } catch (Exception e){
            Toast.makeText(administrasiActivity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try{
            if(currentItem.getUPLOAD().equals("F")){
                administrasiActivity.FAB_UPLOAD.hide();
                administrasiActivity.UPLOAD.setImageResource(R.drawable.ic_file_upload_white_18dp);
                administrasiActivity.FAB_UPLOAD.setEnabled(true);
            } else {
                administrasiActivity.UPLOAD.setImageResource(R.drawable.ic_done_white_18dp);
                administrasiActivity.FAB_UPLOAD.setEnabled(false);
            }
        } catch (Exception e){
            Toast.makeText(administrasiActivity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            administrasiActivity.ID = currentItem.getID();
            administrasiActivity.SKTD_TXT = currentItem.getKTD();
            administrasiActivity.SKLI_TXT = currentItem.getKLI();
            administrasiActivity.SLGT_TXT = currentItem.getLGT();
            administrasiActivity.REC_TXT = currentItem.getREC();
            administrasiActivity.SKTD2_TXT = currentItem.getKTD2();
            administrasiActivity.SKLI2_TXT = currentItem.getKLI2();
            administrasiActivity.SLGT2_TXT = currentItem.getLGT2();
            administrasiActivity.REC_TXT2 = currentItem.getREC2();
            administrasiActivity.SKTD3_TXT = currentItem.getKTD3();
            administrasiActivity.SKLI3_TXT = currentItem.getKLI3();
            administrasiActivity.SLGT3_TXT = currentItem.getLGT3();
            administrasiActivity.REC_TXT3 = currentItem.getREC3();
            administrasiActivity.SKTD4_TXT = currentItem.getKTD4();
            administrasiActivity.SKLI4_TXT = currentItem.getKLI4();
            administrasiActivity.SLGT4_TXT = currentItem.getLGT4();
            administrasiActivity.REC_TXT4 = currentItem.getREC4();
            administrasiActivity.SKTD5_TXT = currentItem.getKTD5();
            administrasiActivity.SKLI5_TXT = currentItem.getKLI5();
            administrasiActivity.SLGT5_TXT = currentItem.getLGT5();
            administrasiActivity.REC_TXT5 = currentItem.getREC5();
            administrasiActivity.SKTD6_TXT = currentItem.getKTD6();
            administrasiActivity.SKLI6_TXT = currentItem.getKLI6();
            administrasiActivity.SLGT6_TXT = currentItem.getLGT6();
            administrasiActivity.REC_TXT6 = currentItem.getREC6();
            administrasiActivity.KTG_KLF1 = KTG_KLF1;
            administrasiActivity.KTG_KLF2 = KTG_KLF2;
            administrasiActivity.KTG_KLF3 = KTG_KLF3;
            administrasiActivity.KTG_KLF4 = KTG_KLF4;
            administrasiActivity.KTG_KLF5 = KTG_KLF5;
            administrasiActivity.KTG_KLF6 = KTG_KLF6;
            administrasiActivity.KTG_KLF = KTG_KLF;

            if(KTG_KLF.equals("LS")){
                administrasiActivity.klf.setText(KTG_KLF);
                administrasiActivity.klf.setBackground(administrasiActivity.getResources().getDrawable(R.drawable.failed_style));
                Animation animation = AnimationUtils.loadAnimation(administrasiActivity.getApplicationContext(), R.anim.recycle_bottom);
                administrasiActivity.klf.startAnimation(animation);
            } else if(KTG_KLF.equals("Tidak Dinilai")){
                administrasiActivity.klf.setText("Tidak Dinilai");
                administrasiActivity.klf.setBackground(administrasiActivity.getResources().getDrawable(R.drawable.null_style));
                Animation animation = AnimationUtils.loadAnimation(administrasiActivity.getApplicationContext(), R.anim.recycle_bottom);
                administrasiActivity.klf.startAnimation(animation);
            } else {
                administrasiActivity.klf.setText(KTG_KLF);
                administrasiActivity.klf.setBackground(administrasiActivity.getResources().getDrawable(R.drawable.success_style));
                Animation animation = AnimationUtils.loadAnimation(administrasiActivity.getApplicationContext(), R.anim.recycle_bottom);
                administrasiActivity.klf.startAnimation(animation);
            }
        } catch (Exception e){
            Toast.makeText(administrasiActivity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public int getItemCount() {
        return mdataList.size();
    }

}
