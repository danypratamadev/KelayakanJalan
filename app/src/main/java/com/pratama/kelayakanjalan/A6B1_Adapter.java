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

public class A6B1_Adapter extends RecyclerView.Adapter<A6B1_Adapter.A6B1ViewHolder> {

    private A6B1Activity a6B1Activity;
    private List<A6B1_Class> mdataList;
    private double DEV_SKM, DEV_LBW, DEV_LBW2, DEV_LBW3, DEV_LBW4, DEV_KFB, STD = 100;
    private String KTG_SKM, KTG_LBW, KTG_LBW2, KTG_LBW3, KTG_LBW4, KTG_LBWW, KTG_KFB, KTG_KLF;
    private static DecimalFormat df = new DecimalFormat("0.0");

    public class A6B1ViewHolder extends RecyclerView.ViewHolder {

        private TextView skm_data, skm_dev, skm_hasil, lbw_data, lbw_dev, lbw2_data, lbw2_dev, lbw3_data,
                lbw3_dev, lbw4_data, lbw4_dev, lbw_hasil, kfb_data, kfb_dev, kfb_hasil, rec_data;
        private LinearLayout skm_back, lbw_back, kfb_back;
        private ImageView FT1, FT2;

        public A6B1ViewHolder(@NonNull View itemView) {
            super(itemView);

            skm_data = (TextView) itemView.findViewById(R.id.skm_data);
            skm_dev = (TextView) itemView.findViewById(R.id.skm_dev);
            skm_hasil = (TextView) itemView.findViewById(R.id.skm_hasil);

            lbw_data = (TextView) itemView.findViewById(R.id.lbw_data);
            lbw_dev = (TextView) itemView.findViewById(R.id.lbw_dev);
            lbw2_data = (TextView) itemView.findViewById(R.id.lbw2_data);
            lbw2_dev = (TextView) itemView.findViewById(R.id.lbw2_dev);
            lbw3_data = (TextView) itemView.findViewById(R.id.lbw3_data);
            lbw3_dev = (TextView) itemView.findViewById(R.id.lbw3_dev);
            lbw4_data = (TextView) itemView.findViewById(R.id.lbw4_data);
            lbw4_dev = (TextView) itemView.findViewById(R.id.lbw4_dev);
            lbw_hasil = (TextView) itemView.findViewById(R.id.lbw_hasil);

            kfb_data = (TextView) itemView.findViewById(R.id.kfb_data);
            kfb_dev = (TextView) itemView.findViewById(R.id.kfb_dev);
            kfb_hasil = (TextView) itemView.findViewById(R.id.kfb_hasil);

            rec_data = (TextView) itemView.findViewById(R.id.rec_data);

            FT1 = (ImageView) itemView.findViewById(R.id.FT1);
            FT2 = (ImageView) itemView.findViewById(R.id.FT2);

            skm_back = (LinearLayout) itemView.findViewById(R.id.skm_back);
            lbw_back = (LinearLayout) itemView.findViewById(R.id.lbw_back);
            kfb_back = (LinearLayout) itemView.findViewById(R.id.kfb_back);

        }
    }

    public A6B1_Adapter(A6B1Activity a6B1Activity, List<A6B1_Class> mdataList) {
        this.a6B1Activity = a6B1Activity;
        this.mdataList = mdataList;
    }

    @NonNull
    @Override
    public A6B1_Adapter.A6B1ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.a6b1_layout, parent, false);
        A6B1_Adapter.A6B1ViewHolder a6b1 = new A6B1_Adapter.A6B1ViewHolder(view);
        return a6b1;
    }

    @Override
    public void onBindViewHolder(@NonNull A6B1_Adapter.A6B1ViewHolder holder, int position) {

        final A6B1_Class currentItem = mdataList.get(position);

        try {
            if(currentItem.getSKM() == -1){
                DEV_SKM = -1;
                KTG_SKM = "-";
                holder.skm_data.setText("-");
                holder.skm_dev.setText("-");
                holder.skm_hasil.setText(KTG_SKM);
                holder.skm_back.setBackground(a6B1Activity.getResources().getDrawable(R.drawable.null_style));
            } else {
                DEV_SKM = STD - currentItem.getSKM();
                if(DEV_SKM == 0){
                    KTG_SKM = "LF";
                    holder.skm_back.setBackground(a6B1Activity.getResources().getDrawable(R.drawable.success_style));
                } else {
                    KTG_SKM = "LS";
                    holder.skm_back.setBackground(a6B1Activity.getResources().getDrawable(R.drawable.failed_style));
                }
                holder.skm_data.setText(String.valueOf(currentItem.getSKM())+"%");
                holder.skm_dev.setText(String.valueOf(DEV_SKM)+"%");
                holder.skm_hasil.setText(KTG_SKM);
            }
        } catch (Exception e){
            Toast.makeText(a6B1Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            if(currentItem.getLBW() == -1){
                DEV_LBW = -1;
                KTG_LBW = "-";
                holder.lbw_data.setText("-");
                holder.lbw_dev.setText("-");
            } else {
                DEV_LBW = STD - currentItem.getLBW();
                if(DEV_LBW == 0){
                    KTG_LBW = "LF";
                } else {
                    KTG_LBW = "LS";
                }
                holder.lbw_data.setText(String.valueOf(currentItem.getLBW())+"%");
                holder.lbw_dev.setText(String.valueOf(DEV_LBW)+"%");
            }
        } catch (Exception e){
            Toast.makeText(a6B1Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            if(currentItem.getLBW2() == -1){
                DEV_LBW2 = -1;
                KTG_LBW2 = "-";
                holder.lbw2_data.setText("-");
                holder.lbw2_dev.setText("-");
            } else {
                if(currentItem.getLBW2() <= 20){
                    DEV_LBW2 = 0;
                    KTG_LBW2 = "LF";
                } else {
                    DEV_LBW2 = (currentItem.getLBW2() - 20)/20;
                    DEV_LBW2 = DEV_LBW2 * 100;
                    if(DEV_LBW2 < 0){
                        DEV_LBW2 = DEV_LBW2 * -1;
                    }
                    if(DEV_LBW2 > 100){
                        DEV_LBW2 = 100;
                    }
                    DEV_LBW2 = Double.parseDouble(df.format(DEV_LBW2).replace(",", "."));
                    KTG_LBW2 = "LS";
                }
                holder.lbw2_data.setText(String.valueOf(currentItem.getLBW2())+" m");
                holder.lbw2_dev.setText(String.valueOf(DEV_LBW2)+"%");
            }
        } catch (Exception e){
            Toast.makeText(a6B1Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            if(currentItem.getLBW3() == -1){
                DEV_LBW3 = -1;
                KTG_LBW3 = "-";
                holder.lbw3_data.setText("-");
                holder.lbw3_dev.setText("-");
            } else {
                DEV_LBW3 = STD - currentItem.getLBW3();
                if(DEV_LBW3 == 0){
                    KTG_LBW3 = "LF";
                } else {
                    KTG_LBW3 = "LS";
                }
                holder.lbw3_data.setText(String.valueOf(currentItem.getLBW3())+"%");
                holder.lbw3_dev.setText(String.valueOf(DEV_LBW3)+"%");
            }
        } catch (Exception e){
            Toast.makeText(a6B1Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            if(currentItem.getLBW4() == -1){
                DEV_LBW4 = -1;
                KTG_LBW4 = "-";
                holder.lbw4_data.setText("-");
                holder.lbw4_dev.setText("-");
            } else {
                DEV_LBW4 = STD - currentItem.getLBW4();
                if(DEV_LBW4 == 0){
                    KTG_LBW4 = "LF";
                } else {
                    KTG_LBW4 = "LS";
                }
                holder.lbw4_data.setText(String.valueOf(currentItem.getLBW4())+"%");
                holder.lbw4_dev.setText(String.valueOf(DEV_LBW4)+"%");
            }
        } catch (Exception e){
            Toast.makeText(a6B1Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            if((KTG_LBW.equals("LF") || KTG_LBW.equals("-")) && (KTG_LBW2.equals("LF") || KTG_LBW2.equals("-")) &&
                    (KTG_LBW3.equals("LF") || KTG_LBW3.equals("-")) && (KTG_LBW4.equals("LF") || KTG_LBW4.equals("-"))){

                if(KTG_LBW.equals("-") && KTG_LBW2.equals("-") && KTG_LBW3.equals("-") && KTG_LBW4.equals("-")){
                    KTG_LBWW = "-";
                    holder.lbw_back.setBackground(a6B1Activity.getResources().getDrawable(R.drawable.null_style));
                } else {
                    KTG_LBWW = "LF";
                    holder.lbw_back.setBackground(a6B1Activity.getResources().getDrawable(R.drawable.success_style));
                }

            } else {
                KTG_LBWW = "LS";
                holder.lbw_back.setBackground(a6B1Activity.getResources().getDrawable(R.drawable.failed_style));
            }
            holder.lbw_hasil.setText(KTG_LBWW);
        } catch (Exception e){
            Toast.makeText(a6B1Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            if(currentItem.getKFB() == -1){
                DEV_KFB = -1;
                KTG_KFB = "-";
                holder.kfb_data.setText("-");
                holder.kfb_dev.setText("-");
                holder.kfb_hasil.setText("-");
                holder.kfb_back.setBackground(a6B1Activity.getResources().getDrawable(R.drawable.null_style));
            } else {
                DEV_KFB = STD - currentItem.getKFB();
                if(DEV_KFB == 0){
                    KTG_KFB = "LF";
                    holder.kfb_back.setBackground(a6B1Activity.getResources().getDrawable(R.drawable.success_style));
                } else {
                    KTG_KFB = "LS";
                    holder.kfb_back.setBackground(a6B1Activity.getResources().getDrawable(R.drawable.failed_style));
                }
                holder.kfb_data.setText(String.valueOf(currentItem.getKFB())+"%");
                holder.kfb_dev.setText(String.valueOf(DEV_KFB)+"%");
                holder.kfb_hasil.setText(KTG_KFB);
            }
        } catch (Exception e){
            Toast.makeText(a6B1Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            if((KTG_SKM.equals("LF") || KTG_SKM.equals("-")) && (KTG_LBWW.equals("LF") || KTG_LBWW.equals("-")) &&
                    (KTG_KFB.equals("LF") || KTG_KFB.equals("-"))){

                if(KTG_SKM.equals("-") && KTG_LBWW.equals("-") && KTG_KFB.equals("-")){
                    KTG_KLF = "Tidak Dinilai";
                } else {
                    KTG_KLF = "LF";
                }

            } else {
                KTG_KLF = "LS";
            }
        } catch (Exception e){
            Toast.makeText(a6B1Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            holder.rec_data.setText(currentItem.getREC());
        } catch (Exception e){
            Toast.makeText(a6B1Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            holder.FT1.setImageBitmap(BitmapFactory.decodeFile(currentItem.getDIR1()));
        } catch (Exception e){
            Toast.makeText(a6B1Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            holder.FT2.setImageBitmap(BitmapFactory.decodeFile(currentItem.getDIR2()));
        } catch (Exception e){
            Toast.makeText(a6B1Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            if(currentItem.getUPLOAD().equals("F")){
                a6B1Activity.FAB_UPLOAD.hide();
                a6B1Activity.UPLOAD.setImageResource(R.drawable.ic_file_upload_white_18dp);
                a6B1Activity.FAB_UPLOAD.setEnabled(true);
            } else {
                a6B1Activity.UPLOAD.setImageResource(R.drawable.ic_done_white_18dp);
                a6B1Activity.FAB_UPLOAD.setEnabled(false);
            }
        } catch (Exception e){
            Toast.makeText(a6B1Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            a6B1Activity.ID = currentItem.getID();
            a6B1Activity.SKM_TXT = currentItem.getSKM();
            a6B1Activity.LBW_TXT = currentItem.getLBW();
            a6B1Activity.LBW2_TXT = currentItem.getLBW2();
            a6B1Activity.LBW3_TXT = currentItem.getLBW3();
            a6B1Activity.LBW4_TXT = currentItem.getLBW4();
            a6B1Activity.KFB_TXT = currentItem.getKFB();
            a6B1Activity.REC_TXT = currentItem.getREC();
            a6B1Activity.DIR1 = currentItem.getDIR1();
            a6B1Activity.DIR2 = currentItem.getDIR2();

            a6B1Activity.DEV_SKM = DEV_SKM;
            a6B1Activity.DEV_LBW = DEV_LBW;
            a6B1Activity.DEV_LBW2 = DEV_LBW2;
            a6B1Activity.DEV_LBW3 = DEV_LBW3;
            a6B1Activity.DEV_LBW4 = DEV_LBW4;
            a6B1Activity.DEV_KFB = DEV_KFB;

            a6B1Activity.KTG_SKM = KTG_SKM;
            a6B1Activity.KTG_LBWW = KTG_LBWW;
            a6B1Activity.KTG_KFB = KTG_KFB;
            a6B1Activity.KTG_KLF = KTG_KLF;

            if(KTG_KLF.equals("LS")){
                a6B1Activity.klf.setText(KTG_KLF);
                a6B1Activity.klf.setBackground(a6B1Activity.getResources().getDrawable(R.drawable.failed_style));
                Animation animation = AnimationUtils.loadAnimation(a6B1Activity.getApplicationContext(), R.anim.recycle_bottom);
                a6B1Activity.klf.startAnimation(animation);
            } else if(KTG_KLF.equals("Tidak Dinilai")){
                a6B1Activity.klf.setText("Tidak Dinilai");
                a6B1Activity.klf.setBackground(a6B1Activity.getResources().getDrawable(R.drawable.null_style));
                Animation animation = AnimationUtils.loadAnimation(a6B1Activity.getApplicationContext(), R.anim.recycle_bottom);
                a6B1Activity.klf.startAnimation(animation);
            } else {
                a6B1Activity.klf.setText(KTG_KLF);
                a6B1Activity.klf.setBackground(a6B1Activity.getResources().getDrawable(R.drawable.success_style));
                Animation animation = AnimationUtils.loadAnimation(a6B1Activity.getApplicationContext(), R.anim.recycle_bottom);
                a6B1Activity.klf.startAnimation(animation);
            }
        } catch (Exception e){
            Toast.makeText(a6B1Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public int getItemCount() {
        return mdataList.size();
    }

}
