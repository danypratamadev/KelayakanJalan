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

public class A34_Adapter extends RecyclerView.Adapter<A34_Adapter.A34ViewHolder> {

    private A34Activity a34Activity;
    private List<A34_Class> mdataList;
    private double DEV_PTJ, DEV_KAL, DEV_LLE, STD = 100;
    private String KTG_PTJ, KTG_KAL, KTG_LLE, KTG_KLF;
    private static DecimalFormat df = new DecimalFormat("0.0");

    public class A34ViewHolder extends RecyclerView.ViewHolder {

        private TextView ptj_data, ptj_dev, ptj_hasil, kal_data, kal_dev, kal_hasil, lle_data, lle_dev,
                lle_hasil, rec_data;
        private LinearLayout ptj_back, kal_back, lle_back;
        private ImageView FT1, FT2;

        public A34ViewHolder(@NonNull View itemView) {
            super(itemView);

            ptj_data = (TextView) itemView.findViewById(R.id.ptj_data);
            ptj_dev = (TextView) itemView.findViewById(R.id.ptj_dev);
            ptj_hasil = (TextView) itemView.findViewById(R.id.ptj_hasil);

            kal_data = (TextView) itemView.findViewById(R.id.kal_data);
            kal_dev = (TextView) itemView.findViewById(R.id.kal_dev);
            kal_hasil = (TextView) itemView.findViewById(R.id.kal_hasil);

            lle_data = (TextView) itemView.findViewById(R.id.lle_data);
            lle_dev = (TextView) itemView.findViewById(R.id.lle_dev);
            lle_hasil = (TextView) itemView.findViewById(R.id.lle_hasil);

            rec_data = (TextView) itemView.findViewById(R.id.rec_data);

            FT1 = (ImageView) itemView.findViewById(R.id.FT1);
            FT2 = (ImageView) itemView.findViewById(R.id.FT2);

            ptj_back = (LinearLayout) itemView.findViewById(R.id.ptj_back);
            kal_back = (LinearLayout) itemView.findViewById(R.id.kal_back);
            lle_back = (LinearLayout) itemView.findViewById(R.id.lle_back);

        }
    }

    public A34_Adapter(A34Activity a34Activity, List<A34_Class> mdataList) {
        this.a34Activity = a34Activity;
        this.mdataList = mdataList;
    }

    @NonNull
    @Override
    public A34_Adapter.A34ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.a34_layout, parent, false);
        A34_Adapter.A34ViewHolder a34 = new A34_Adapter.A34ViewHolder(view);
        return a34;
    }

    @Override
    public void onBindViewHolder(@NonNull A34_Adapter.A34ViewHolder holder, int position) {

        final A34_Class currentItem = mdataList.get(position);

        try {
            if(currentItem.getPTJ() == -1){
                holder.ptj_data.setText("-");
                DEV_PTJ = -1;
                KTG_PTJ = "-";
                holder.ptj_dev.setText("-");
                holder.ptj_hasil.setText(KTG_PTJ);
                holder.ptj_back.setBackground(a34Activity.getResources().getDrawable(R.drawable.null_style));
            } else {
                holder.ptj_data.setText(String.valueOf(currentItem.getPTJ())+"%");
                DEV_PTJ = STD - currentItem.getPTJ();
                if(DEV_PTJ == 0){
                    KTG_PTJ = "LF";
                    holder.ptj_back.setBackground(a34Activity.getResources().getDrawable(R.drawable.success_style));
                } else {
                    KTG_PTJ = "LS";
                    holder.ptj_back.setBackground(a34Activity.getResources().getDrawable(R.drawable.failed_style));
                }
                holder.ptj_dev.setText(String.valueOf(DEV_PTJ)+"%");
                holder.ptj_hasil.setText(KTG_PTJ);
            }
        } catch (Exception e){
            Toast.makeText(a34Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            if(currentItem.getKAL() == -1){
                holder.kal_data.setText("-");
                DEV_KAL = -1;
                KTG_KAL = "-";
                holder.kal_dev.setText("-");
                holder.kal_hasil.setText(KTG_KAL);
                holder.kal_back.setBackground(a34Activity.getResources().getDrawable(R.drawable.null_style));
            } else {
                holder.kal_data.setText(String.valueOf(currentItem.getKAL())+"%");
                DEV_KAL = STD - currentItem.getKAL();
                if(DEV_KAL == 0){
                    KTG_KAL = "LF";
                    holder.kal_back.setBackground(a34Activity.getResources().getDrawable(R.drawable.success_style));
                } else {
                    KTG_KAL = "LS";
                    holder.kal_back.setBackground(a34Activity.getResources().getDrawable(R.drawable.failed_style));
                }
                holder.kal_dev.setText(String.valueOf(DEV_KAL)+"%");
                holder.kal_hasil.setText(KTG_KAL);
            }
        } catch (Exception e){
            Toast.makeText(a34Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            if(currentItem.getLLE() == -1){
                holder.lle_data.setText("-");
                DEV_LLE = -1;
                KTG_LLE = "-";
                holder.lle_dev.setText("-");
                holder.lle_hasil.setText(KTG_LLE);
                holder.lle_back.setBackground(a34Activity.getResources().getDrawable(R.drawable.null_style));
            } else {
                holder.lle_data.setText(String.valueOf(currentItem.getLLE())+" m");
                if(currentItem.getLLE() >= 2){
                    DEV_LLE = 0;
                    KTG_LLE = "LF";
                    holder.lle_back.setBackground(a34Activity.getResources().getDrawable(R.drawable.success_style));
                } else {
                    DEV_LLE = (currentItem.getLLE() - 2)/2;
                    DEV_LLE = (DEV_LLE * 100);
                    if(DEV_LLE < 0){
                        DEV_LLE = DEV_LLE * -1;
                    }
                    if(DEV_LLE > 100){
                        DEV_LLE = 100;
                    }
                    DEV_LLE = Double.parseDouble(df.format(DEV_LLE).replace(",", "."));
                    KTG_LLE = "LS";
                    holder.lle_back.setBackground(a34Activity.getResources().getDrawable(R.drawable.failed_style));
                }
                holder.lle_dev.setText(String.valueOf(DEV_LLE)+"%");
                holder.lle_hasil.setText(KTG_LLE);
            }
        } catch (Exception e){
            Toast.makeText(a34Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            if((KTG_PTJ.equals("LF") || KTG_PTJ.equals("-")) && (KTG_KAL.equals("LF") || KTG_KAL.equals("-")) &&
                    (KTG_LLE.equals("LF") || KTG_LLE.equals("-"))){

                if(KTG_PTJ.equals("-") && KTG_KAL.equals("-") && KTG_LLE.equals("-")){
                    KTG_KLF = "Tidak Dinilai";
                } else {
                    KTG_KLF = "LF";
                }

            } else {
                KTG_KLF = "LS";
            }
        } catch (Exception e){
            Toast.makeText(a34Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            holder.rec_data.setText(currentItem.getREC());
        } catch (Exception e){
            Toast.makeText(a34Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            holder.FT1.setImageBitmap(BitmapFactory.decodeFile(currentItem.getDIR1()));
        } catch (Exception e){
            Toast.makeText(a34Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            holder.FT2.setImageBitmap(BitmapFactory.decodeFile(currentItem.getDIR2()));
        } catch (Exception e){
            Toast.makeText(a34Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            if(currentItem.getUPLOAD().equals("F")){
                a34Activity.FAB_UPLOAD.hide();
                a34Activity.UPLOAD.setImageResource(R.drawable.ic_file_upload_white_18dp);
                a34Activity.FAB_UPLOAD.setEnabled(true);
            } else {
                a34Activity.UPLOAD.setImageResource(R.drawable.ic_done_white_18dp);
                a34Activity.FAB_UPLOAD.setEnabled(false);
            }
        } catch (Exception e){
            Toast.makeText(a34Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            a34Activity.ID = currentItem.getID();
            a34Activity.PTJ_TXT = currentItem.getPTJ();
            a34Activity.KAL_TXT = currentItem.getKAL();
            a34Activity.LLE_TXT = currentItem.getLLE();
            a34Activity.REC_TXT = currentItem.getREC();
            a34Activity.DIR1 = currentItem.getDIR1();
            a34Activity.DIR2 = currentItem.getDIR2();

            a34Activity.DEV_PTJ = DEV_PTJ;
            a34Activity.DEV_KAL = DEV_KAL;
            a34Activity.DEV_LLE = DEV_LLE;

            a34Activity.KTG_PTJ = KTG_PTJ;
            a34Activity.KTG_KAL = KTG_KAL;
            a34Activity.KTG_LLE = KTG_LLE;
            a34Activity.KTG_KLF = KTG_KLF;

            if(KTG_KLF.equals("LS")){
                a34Activity.klf.setText(KTG_KLF);
                a34Activity.klf.setBackground(a34Activity.getResources().getDrawable(R.drawable.failed_style));
                Animation animation = AnimationUtils.loadAnimation(a34Activity.getApplicationContext(), R.anim.recycle_bottom);
                a34Activity.klf.startAnimation(animation);
            } else if(KTG_KLF.equals("Tidak Dinilai")){
                a34Activity.klf.setText("Tidak Dinilai");
                a34Activity.klf.setBackground(a34Activity.getResources().getDrawable(R.drawable.null_style));
                Animation animation = AnimationUtils.loadAnimation(a34Activity.getApplicationContext(), R.anim.recycle_bottom);
                a34Activity.klf.startAnimation(animation);
            } else {
                a34Activity.klf.setText(KTG_KLF);
                a34Activity.klf.setBackground(a34Activity.getResources().getDrawable(R.drawable.success_style));
                Animation animation = AnimationUtils.loadAnimation(a34Activity.getApplicationContext(), R.anim.recycle_bottom);
                a34Activity.klf.startAnimation(animation);
            }
        } catch (Exception e){
            Toast.makeText(a34Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public int getItemCount() {
        return mdataList.size();
    }

}
