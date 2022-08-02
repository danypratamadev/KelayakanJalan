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

public class A6B4_Adapter extends RecyclerView.Adapter<A6B4_Adapter.A6B4ViewHolder> {

    private A6B4Activity a6B4Activity;
    private List<A6B4_Class> mdataList;
    private double DEV_KBK2, DEV_KBK3, DEV_KDR, DEV_KTJ, DEV_KFB, STD = 100;
    private String KTG_KBK2, KTG_KBK3, KTG_KDR, KTG_KTJ, KTG_BLT, KTG_KFB, KTG_KLF;
    private static DecimalFormat df = new DecimalFormat("0.0");

    public class A6B4ViewHolder extends RecyclerView.ViewHolder {

        private TextView kbk_data, kbk_dev, kbk2_data, kbk2_dev, kbk3_data, kbk3_dev, kdr_data, kdr_dev,
                ktj_data, ktj_dev, blt_hasil, kfb_data, kfb_dev, kfb_hasil, rec_data;
        private LinearLayout blt_back, kfb_back;
        private ImageView FT1, FT2;

        public A6B4ViewHolder(@NonNull View itemView) {
            super(itemView);

            kbk2_data = (TextView) itemView.findViewById(R.id.kbk2_data);
            kbk2_dev = (TextView) itemView.findViewById(R.id.kbk2_dev);
            kbk3_data = (TextView) itemView.findViewById(R.id.kbk3_data);
            kbk3_dev = (TextView) itemView.findViewById(R.id.kbk3_dev);
            kdr_data = (TextView) itemView.findViewById(R.id.kdr_data);
            kdr_dev = (TextView) itemView.findViewById(R.id.kdr_dev);
            ktj_data = (TextView) itemView.findViewById(R.id.ktj_data);
            ktj_dev = (TextView) itemView.findViewById(R.id.ktj_dev);
            blt_hasil = (TextView) itemView.findViewById(R.id.blt_hasil);

            kfb_data = (TextView) itemView.findViewById(R.id.kfb_data);
            kfb_dev = (TextView) itemView.findViewById(R.id.kfb_dev);
            kfb_hasil = (TextView) itemView.findViewById(R.id.kfb_hasil);

            rec_data = (TextView) itemView.findViewById(R.id.rec_data);

            FT1 = (ImageView) itemView.findViewById(R.id.FT1);
            FT2 = (ImageView) itemView.findViewById(R.id.FT2);

            blt_back = (LinearLayout) itemView.findViewById(R.id.blt_back);
            kfb_back = (LinearLayout) itemView.findViewById(R.id.kfb_back);

        }
    }

    public A6B4_Adapter(A6B4Activity a6B4Activity, List<A6B4_Class> mdataList) {
        this.a6B4Activity = a6B4Activity;
        this.mdataList = mdataList;
    }

    @NonNull
    @Override
    public A6B4_Adapter.A6B4ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.a6b4_layout, parent, false);
        A6B4_Adapter.A6B4ViewHolder a6b4 = new A6B4_Adapter.A6B4ViewHolder(view);
        return a6b4;
    }

    @Override
    public void onBindViewHolder(@NonNull A6B4_Adapter.A6B4ViewHolder holder, int position) {

        final A6B4_Class currentItem = mdataList.get(position);

        try {
            if(currentItem.getKBK2() == -1){
                DEV_KBK2 = -1;
                KTG_KBK2 = "-";
                holder.kbk2_data.setText("-");
                holder.kbk2_dev.setText("-");
            } else {
                if(currentItem.getKBK2() >= 0.2){
                    DEV_KBK2 = 0;
                    KTG_KBK2 = "LF";
                } else {
                    DEV_KBK2 = (currentItem.getKBK2() - 0.2)/0.2;
                    DEV_KBK2 = DEV_KBK2 * 100;
                    if(DEV_KBK2 < 0){
                        DEV_KBK2 = DEV_KBK2 * -1;
                    }
                    if(DEV_KBK2 > 100){
                        DEV_KBK2 = 100;
                    }
                    DEV_KBK2 = Double.parseDouble(df.format(DEV_KBK2).replace(",", "."));
                    KTG_KBK2 = "LS";
                }
                holder.kbk2_data.setText(String.valueOf(currentItem.getKBK2())+" m");
                holder.kbk2_dev.setText(String.valueOf(DEV_KBK2)+"%");
            }
        } catch (Exception e){
            Toast.makeText(a6B4Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            if(currentItem.getKBK3() == -1){
                DEV_KBK3 = -1;
                KTG_KBK3 = "-";
                holder.kbk3_data.setText("-");
                holder.kbk3_dev.setText("-");
            } else {
                if(currentItem.getKBK3() >= 0.5){
                    DEV_KBK3 = 0;
                    KTG_KBK3 = "LF";
                } else {
                    DEV_KBK3 = (currentItem.getKBK3() - 0.5)/0.5;
                    DEV_KBK3 = DEV_KBK3 * 100;
                    if(DEV_KBK3 < 0){
                        DEV_KBK3 = DEV_KBK3 * -1;
                    }
                    if(DEV_KBK3 > 100){
                        DEV_KBK3 = 100;
                    }
                    DEV_KBK3 = Double.parseDouble(df.format(DEV_KBK3).replace(",", "."));
                    KTG_KBK3 = "LS";
                }
                holder.kbk3_data.setText(String.valueOf(currentItem.getKBK3())+" m");
                holder.kbk3_dev.setText(String.valueOf(DEV_KBK2)+"%");
            }
        } catch (Exception e){
            Toast.makeText(a6B4Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            if(currentItem.getKDR() == -1){
                DEV_KDR = -1;
                KTG_KDR = "-";
                holder.kdr_data.setText("-");
                holder.kdr_dev.setText("-");
            } else {
                DEV_KDR = STD - currentItem.getKDR();
                if(DEV_KDR == 0){
                    KTG_KDR = "LF";
                } else {
                    KTG_KDR = "LS";
                }
                holder.kdr_data.setText(String.valueOf(currentItem.getKDR())+"%");
                holder.kdr_dev.setText(String.valueOf(DEV_KDR)+"%");
            }
        } catch (Exception e){
            Toast.makeText(a6B4Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
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
            Toast.makeText(a6B4Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            if((KTG_KBK2.equals("LF") || KTG_KBK2.equals("-")) &&
                    (KTG_KBK3.equals("LF") || KTG_KBK3.equals("-")) && (KTG_KDR.equals("LF") || KTG_KDR.equals("-")) &&
                    (KTG_KTJ.equals("LF") || KTG_KTJ.equals("-"))){

                if(KTG_KBK2.equals("-") && KTG_KBK3.equals("-") && KTG_KDR.equals("-") && KTG_KTJ.equals("-")){
                    KTG_BLT = "-";
                    holder.blt_back.setBackground(a6B4Activity.getResources().getDrawable(R.drawable.null_style));
                } else {
                    KTG_BLT = "LF";
                    holder.blt_back.setBackground(a6B4Activity.getResources().getDrawable(R.drawable.success_style));
                }

            } else {
                KTG_BLT = "LS";
                holder.blt_back.setBackground(a6B4Activity.getResources().getDrawable(R.drawable.failed_style));
            }
            holder.blt_hasil.setText(KTG_BLT);
        } catch (Exception e){
            Toast.makeText(a6B4Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            if(currentItem.getKFB() == -1){
                DEV_KFB = -1;
                KTG_KFB = "-";
                holder.kfb_data.setText("-");
                holder.kfb_dev.setText("-");
                holder.kfb_hasil.setText(KTG_KFB);
                holder.kfb_back.setBackground(a6B4Activity.getResources().getDrawable(R.drawable.null_style));
            } else {
                DEV_KFB = STD - currentItem.getKFB();
                if(DEV_KFB == 0){
                    KTG_KFB = "LF";
                    holder.kfb_back.setBackground(a6B4Activity.getResources().getDrawable(R.drawable.success_style));
                } else {
                    KTG_KFB = "LS";
                    holder.kfb_back.setBackground(a6B4Activity.getResources().getDrawable(R.drawable.failed_style));
                }
                holder.kfb_data.setText(String.valueOf(currentItem.getKFB())+"%");
                holder.kfb_dev.setText(String.valueOf(DEV_KFB)+"%");
                holder.kfb_hasil.setText(KTG_KFB);
            }
        } catch (Exception e){
            Toast.makeText(a6B4Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            if((KTG_BLT.equals("LF") || KTG_BLT.equals("-")) && (KTG_KFB.equals("LF") || KTG_KFB.equals("-"))){

                if(KTG_BLT.equals("-") && KTG_KFB.equals("-")){
                    KTG_KLF = "Tidak Dinilai";
                } else {
                    KTG_KLF = "LF";
                }

            } else {
                KTG_KLF = "LS";
            }
        } catch (Exception e){
            Toast.makeText(a6B4Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            holder.rec_data.setText(currentItem.getREC());
        } catch (Exception e){
            Toast.makeText(a6B4Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            holder.FT1.setImageBitmap(BitmapFactory.decodeFile(currentItem.getDIR1()));
        } catch (Exception e){
            Toast.makeText(a6B4Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            holder.FT2.setImageBitmap(BitmapFactory.decodeFile(currentItem.getDIR2()));
        } catch (Exception e){
            Toast.makeText(a6B4Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            if(currentItem.getUPLOAD().equals("F")){
                a6B4Activity.FAB_UPLOAD.hide();
                a6B4Activity.UPLOAD.setImageResource(R.drawable.ic_file_upload_white_18dp);
                a6B4Activity.FAB_UPLOAD.setEnabled(true);
            } else {
                a6B4Activity.UPLOAD.setImageResource(R.drawable.ic_done_white_18dp);
                a6B4Activity.FAB_UPLOAD.setEnabled(false);
            }
        } catch (Exception e){
            Toast.makeText(a6B4Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            a6B4Activity.ID = currentItem.getID();
            a6B4Activity.KBK2_TXT = currentItem.getKBK2();
            a6B4Activity.KBK3_TXT = currentItem.getKBK3();
            a6B4Activity.KDR_TXT = currentItem.getKDR();
            a6B4Activity.KTJ_TXT = currentItem.getKTJ();
            a6B4Activity.KFB_TXT = currentItem.getKFB();
            a6B4Activity.REC_TXT = currentItem.getREC();
            a6B4Activity.DIR1 = currentItem.getDIR1();
            a6B4Activity.DIR2 = currentItem.getDIR2();
            a6B4Activity.DEV_KBK2 = DEV_KBK2;
            a6B4Activity.DEV_KBK3 = DEV_KBK3;
            a6B4Activity.DEV_KDR = DEV_KDR;
            a6B4Activity.DEV_KTJ = DEV_KTJ;
            a6B4Activity.DEV_KFB = DEV_KFB;
            a6B4Activity.KTG_BLT = KTG_BLT;
            a6B4Activity.KTG_KFB = KTG_KFB;
            a6B4Activity.KTG_KLF = KTG_KLF;

            if(KTG_KLF.equals("LS")){
                a6B4Activity.klf.setText(KTG_KLF);
                a6B4Activity.klf.setBackground(a6B4Activity.getResources().getDrawable(R.drawable.failed_style));
                Animation animation = AnimationUtils.loadAnimation(a6B4Activity.getApplicationContext(), R.anim.recycle_bottom);
                a6B4Activity.klf.startAnimation(animation);
            } else if(KTG_KLF.equals("Tidak Dinilai")){
                a6B4Activity.klf.setText("Tidak Dinilai");
                a6B4Activity.klf.setBackground(a6B4Activity.getResources().getDrawable(R.drawable.null_style));
                Animation animation = AnimationUtils.loadAnimation(a6B4Activity.getApplicationContext(), R.anim.recycle_bottom);
                a6B4Activity.klf.startAnimation(animation);
            } else {
                a6B4Activity.klf.setText(KTG_KLF);
                a6B4Activity.klf.setBackground(a6B4Activity.getResources().getDrawable(R.drawable.success_style));
                Animation animation = AnimationUtils.loadAnimation(a6B4Activity.getApplicationContext(), R.anim.recycle_bottom);
                a6B4Activity.klf.startAnimation(animation);
            }
        } catch (Exception e){
            Toast.makeText(a6B4Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public int getItemCount() {
        return mdataList.size();
    }

}
