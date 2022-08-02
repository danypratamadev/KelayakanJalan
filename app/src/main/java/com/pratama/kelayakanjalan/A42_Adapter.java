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

public class A42_Adapter extends RecyclerView.Adapter<A42_Adapter.A42ViewHolder> {

    private A42Activity a42Activity;
    private List<A42_Class> mdataList;
    private double DEV_LBR, DEV_PFR, DEV_KTU, DEV_KTU2, DEV_KTU3, STD = 100;
    private String KTG_LBR, KTG_PFR, KTG_KTU, KTG_KTU2, KTG_KTU3, KTG_KTUU, KTG_KLF;
    private static DecimalFormat df = new DecimalFormat("0.0");

    public class A42ViewHolder extends RecyclerView.ViewHolder {

        private TextView lbr_data, lbr_dev, lbr_hasil, pfr_data, pfr_dev, pfr_hasil, ktu_data, ktu_dev,
                ktu2_data, ktu2_dev, ktu3_data, ktu3_dev, ktu_hasil, rec_data;
        private LinearLayout lbr_back, pfr_back, ktu_back;
        private ImageView FT1, FT2;

        public A42ViewHolder(@NonNull View itemView) {
            super(itemView);

            lbr_data = (TextView) itemView.findViewById(R.id.lbr_data);
            lbr_dev = (TextView) itemView.findViewById(R.id.lbr_dev);
            lbr_hasil = (TextView) itemView.findViewById(R.id.lbr_hasil);

            pfr_data = (TextView) itemView.findViewById(R.id.pfr_data);
            pfr_dev = (TextView) itemView.findViewById(R.id.pfr_dev);
            pfr_hasil = (TextView) itemView.findViewById(R.id.pfr_hasil);

            ktu_data = (TextView) itemView.findViewById(R.id.ktu_data);
            ktu_dev = (TextView) itemView.findViewById(R.id.ktu_dev);
            ktu2_data = (TextView) itemView.findViewById(R.id.ktu2_data);
            ktu2_dev = (TextView) itemView.findViewById(R.id.ktu2_dev);
            ktu3_data = (TextView) itemView.findViewById(R.id.ktu3_data);
            ktu3_dev = (TextView) itemView.findViewById(R.id.ktu3_dev);
            ktu_hasil = (TextView) itemView.findViewById(R.id.ktu_hasil);

            rec_data = (TextView) itemView.findViewById(R.id.rec_data);

            FT1 = (ImageView) itemView.findViewById(R.id.FT1);
            FT2 = (ImageView) itemView.findViewById(R.id.FT2);

            lbr_back = (LinearLayout) itemView.findViewById(R.id.lbr_back);
            pfr_back = (LinearLayout) itemView.findViewById(R.id.pfr_back);
            ktu_back = (LinearLayout) itemView.findViewById(R.id.ktu_back);

        }

    }

    public A42_Adapter(A42Activity a42Activity, List<A42_Class> mdataList) {
        this.a42Activity = a42Activity;
        this.mdataList = mdataList;
    }

    @NonNull
    @Override
    public A42_Adapter.A42ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.a42_layout, parent, false);
        A42_Adapter.A42ViewHolder a42 = new A42_Adapter.A42ViewHolder(view);
        return a42;
    }

    @Override
    public void onBindViewHolder(@NonNull A42_Adapter.A42ViewHolder holder, int position) {

        final A42_Class currentItem = mdataList.get(position);

        try {
            if(currentItem.getLBR() == -1){
                DEV_LBR = -1;
                KTG_LBR = "-";
                holder.lbr_data.setText("-");
                holder.lbr_dev.setText("-");
                holder.lbr_hasil.setText(KTG_LBR);
                holder.lbr_back.setBackground(a42Activity.getResources().getDrawable(R.drawable.null_style));
            } else {
                if(a42Activity.KPR.equals("Jalan Bebas Hambatan (JBH)")){
                    if(currentItem.getLBR() >= 30){
                        DEV_LBR = 0;
                        KTG_LBR = "LF";
                        holder.lbr_back.setBackground(a42Activity.getResources().getDrawable(R.drawable.success_style));
                    } else {
                        DEV_LBR = (currentItem.getLBR() - 30)/30;
                        DEV_LBR = (DEV_LBR * 100);
                        if(DEV_LBR < 0){
                            DEV_LBR = DEV_LBR * -1;
                        }
                        if(DEV_LBR > 100){
                            DEV_LBR = 100;
                        }
                        DEV_LBR = Double.parseDouble(df.format(DEV_LBR).replace(",", "."));
                        KTG_LBR = "LS";
                        holder.lbr_back.setBackground(a42Activity.getResources().getDrawable(R.drawable.failed_style));
                    }
                } else if(a42Activity.KPR.equals("Jalan Raya (JR)")){
                    if(currentItem.getLBR() >= 25){
                        DEV_LBR = 0;
                        KTG_LBR = "LF";
                        holder.lbr_back.setBackground(a42Activity.getResources().getDrawable(R.drawable.success_style));
                    } else {
                        DEV_LBR = (currentItem.getLBR() - 25)/25;
                        DEV_LBR = (DEV_LBR * 100);
                        if(DEV_LBR < 0){
                            DEV_LBR = DEV_LBR * -1;
                        }
                        if(DEV_LBR > 100){
                            DEV_LBR = 100;
                        }
                        DEV_LBR = Double.parseDouble(df.format(DEV_LBR).replace(",", "."));
                        KTG_LBR = "LS";
                        holder.lbr_back.setBackground(a42Activity.getResources().getDrawable(R.drawable.failed_style));
                    }
                } else if(a42Activity.KPR.equals("Jalan Sedang (JS)")){
                    if(currentItem.getLBR() >= 15){
                        DEV_LBR = 0;
                        KTG_LBR = "LF";
                        holder.lbr_back.setBackground(a42Activity.getResources().getDrawable(R.drawable.success_style));
                    } else {
                        DEV_LBR = (currentItem.getLBR() - 15)/15;
                        DEV_LBR = (DEV_LBR * 100);
                        if(DEV_LBR < 0){
                            DEV_LBR = DEV_LBR * -1;
                        }
                        if(DEV_LBR > 100){
                            DEV_LBR = 100;
                        }
                        DEV_LBR = Double.parseDouble(df.format(DEV_LBR).replace(",", "."));
                        KTG_LBR = "LS";
                        holder.lbr_back.setBackground(a42Activity.getResources().getDrawable(R.drawable.failed_style));
                    }
                } else {
                    if(currentItem.getLBR() >= 11){
                        DEV_LBR = 0;
                        KTG_LBR = "LF";
                        holder.lbr_back.setBackground(a42Activity.getResources().getDrawable(R.drawable.success_style));
                    } else {
                        DEV_LBR = (currentItem.getLBR() - 11)/11;
                        DEV_LBR = (DEV_LBR * 100);
                        if(DEV_LBR < 0){
                            DEV_LBR = DEV_LBR * -1;
                        }
                        if(DEV_LBR > 100){
                            DEV_LBR = 100;
                        }
                        DEV_LBR = Double.parseDouble(df.format(DEV_LBR).replace(",", "."));
                        KTG_LBR = "LS";
                        holder.lbr_back.setBackground(a42Activity.getResources().getDrawable(R.drawable.failed_style));
                    }
                }
                holder.lbr_data.setText(String.valueOf(currentItem.getLBR())+" m");
                holder.lbr_dev.setText(String.valueOf(DEV_LBR)+"%");
                holder.lbr_hasil.setText(KTG_LBR);
            }
        } catch (Exception e){
            Toast.makeText(a42Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            if(currentItem.getPFR() == -1){
                DEV_PFR = -1;
                KTG_PFR = "-";
                holder.pfr_data.setText("-");
                holder.pfr_dev.setText("-");
                holder.pfr_hasil.setText(KTG_PFR);
                holder.pfr_back.setBackground(a42Activity.getResources().getDrawable(R.drawable.null_style));
            } else {
                DEV_PFR = STD - currentItem.getPFR();
                if(DEV_PFR == 0){
                    KTG_PFR = "LF";
                    holder.pfr_back.setBackground(a42Activity.getResources().getDrawable(R.drawable.success_style));
                } else {
                    KTG_PFR = "LS";
                    holder.pfr_back.setBackground(a42Activity.getResources().getDrawable(R.drawable.failed_style));
                }
                holder.pfr_data.setText(String.valueOf(currentItem.getPFR())+"%");
                holder.pfr_dev.setText(String.valueOf(DEV_PFR)+"%");
                holder.pfr_hasil.setText(KTG_PFR);
            }
        } catch (Exception e){
            Toast.makeText(a42Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            if(currentItem.getSKTU().equals("Antar Kota")){
                if(currentItem.getKTU() == -1){
                    DEV_KTU = -1;
                    KTG_KTU = "-";
                    holder.ktu_data.setText("-");
                    holder.ktu_dev.setText("-");
                } else {
                    if(currentItem.getKTU() >= 3.4){
                        DEV_KTU = 0;
                        KTG_KTU = "LF";
                    } else {
                        DEV_KTU = (currentItem.getKTU() - 3.4)/3.4;
                        DEV_KTU = (DEV_KTU * 100);
                        if(DEV_KTU < 0){
                            DEV_KTU = DEV_KTU * -1;
                        }
                        if(DEV_KTU > 100){
                            DEV_KTU = 100;
                        }
                        DEV_KTU = Double.parseDouble(df.format(DEV_KTU).replace(",", "."));
                        KTG_KTU = "LS";
                    }
                    holder.ktu_data.setText(String.valueOf(currentItem.getKTU())+" m");
                    holder.ktu_dev.setText(String.valueOf(DEV_KTU)+"%");
                }
            } else {
                if(currentItem.getKTU() == -1){
                    DEV_KTU = -1;
                    KTG_KTU = "-";
                    holder.ktu_data.setText("-");
                    holder.ktu_dev.setText("-");
                } else {
                    if(currentItem.getKTU() >= 1){
                        DEV_KTU = 0;
                        KTG_KTU = "LF";
                    } else {
                        DEV_KTU = (currentItem.getKTU() - 1)/1;
                        DEV_KTU = (DEV_KTU * 100);
                        if(DEV_KTU < 0){
                            DEV_KTU = DEV_KTU * -1;
                        }
                        if(DEV_KTU > 100){
                            DEV_KTU = 100;
                        }
                        DEV_KTU = Double.parseDouble(df.format(DEV_KTU).replace(",", "."));
                        KTG_KTU = "LS";
                    }
                    holder.ktu_data.setText(String.valueOf(currentItem.getKTU())+" m");
                    holder.ktu_dev.setText(String.valueOf(DEV_KTU)+"%");
                }
            }
        } catch (Exception e){
            Toast.makeText(a42Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            if(currentItem.getSKTU().equals("Antar Kota")){
                if(currentItem.getKTU2() == -1){
                    DEV_KTU2 = -1;
                    KTG_KTU2 = "-";
                    holder.ktu2_data.setText("-");
                    holder.ktu2_dev.setText("-");
                } else {
                    if(currentItem.getKTU2() >= 4){
                        DEV_KTU2 = 0;
                        KTG_KTU2 = "LF";
                    } else {
                        DEV_KTU2 = (currentItem.getKTU2() - 4)/4;
                        DEV_KTU2 = (DEV_KTU2 * 100);
                        if(DEV_KTU2 < 0){
                            DEV_KTU2 = DEV_KTU2 * -1;
                        }
                        if(DEV_KTU2 > 100){
                            DEV_KTU2 = 100;
                        }
                        DEV_KTU2 = Double.parseDouble(df.format(DEV_KTU2).replace(",", "."));
                        KTG_KTU2 = "LS";
                    }
                    holder.ktu2_data.setText(String.valueOf(currentItem.getKTU2())+" m");
                    holder.ktu2_dev.setText(String.valueOf(DEV_KTU2)+"%");
                }
            } else {
                if(currentItem.getKTU2() == -1){
                    DEV_KTU2 = -1;
                    KTG_KTU2 = "-";
                    holder.ktu2_data.setText("-");
                    holder.ktu2_dev.setText("-");
                } else {
                    DEV_KTU2 = STD - currentItem.getKTU2();
                    if(DEV_KTU2 == 0){
                        KTG_KTU2 = "LF";
                    } else {
                        KTG_KTU2 = "LS";
                    }
                    holder.ktu2_data.setText(String.valueOf(currentItem.getKTU2())+"%");
                    holder.ktu2_dev.setText(String.valueOf(DEV_KTU2)+"%");
                }
            }
        } catch (Exception e){
            Toast.makeText(a42Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            if(currentItem.getSKTU().equals("Antar Kota")){
                if(currentItem.getKTU3() == -1){
                    DEV_KTU3 = -1;
                    KTG_KTU3 = "-";
                    holder.ktu3_data.setText("-");
                    holder.ktu3_dev.setText("-");
                } else {
                    DEV_KTU3 = STD - currentItem.getKTU3();
                    if(DEV_KTU3 == 0){
                        KTG_KTU3 = "LF";
                    } else {
                        KTG_KTU3 = "LS";
                    }
                    holder.ktu3_data.setText(String.valueOf(currentItem.getKTU3())+"%");
                    holder.ktu3_dev.setText(String.valueOf(DEV_KTU3)+"%");
                }
            } else {
                DEV_KTU3 = 0;
                KTG_KTU3 = "-";
            }
        } catch (Exception e){
            Toast.makeText(a42Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            if((KTG_KTU.equals("LF") || KTG_KTU.equals("-")) && (KTG_KTU2.equals("LF") || KTG_KTU2.equals("-"))
                    && (KTG_KTU3.equals("LF") || KTG_KTU3.equals("-"))){

                if(KTG_KTU.equals("-") && KTG_KTU2.equals("-") && KTG_KTU3.equals("-")){
                    KTG_KTUU = "-";
                    holder.ktu_back.setBackground(a42Activity.getResources().getDrawable(R.drawable.null_style));
                } else {
                    KTG_KTUU = "LF";
                    holder.ktu_back.setBackground(a42Activity.getResources().getDrawable(R.drawable.success_style));
                }

            } else {
                KTG_KTUU = "LS";
                holder.ktu_back.setBackground(a42Activity.getResources().getDrawable(R.drawable.failed_style));
            }
            holder.ktu_hasil.setText(KTG_KTUU);
        } catch (Exception e){
            Toast.makeText(a42Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            if((KTG_LBR.equals("LF") || KTG_LBR.equals("-")) & (KTG_PFR.equals("LF") || KTG_PFR.equals("-"))
                    && (KTG_KTUU.equals("LF") || KTG_KTUU.equals("-"))){

                if(KTG_LBR.equals("-") && KTG_PFR.equals("-") && KTG_KTUU.equals("-")){
                    KTG_KLF = "Tidak Dinilai";
                } else {
                    KTG_KLF = "LF";
                }

            } else {
                KTG_KLF = "LS";
            }
        } catch (Exception e){
            Toast.makeText(a42Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            holder.rec_data.setText(currentItem.getREC());
        } catch (Exception e){
            Toast.makeText(a42Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            holder.FT1.setImageBitmap(BitmapFactory.decodeFile(currentItem.getDIR1()));
        } catch (Exception e){
            Toast.makeText(a42Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            holder.FT2.setImageBitmap(BitmapFactory.decodeFile(currentItem.getDIR2()));
        } catch (Exception e){
            Toast.makeText(a42Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            if(currentItem.getUPLOAD().equals("F")){
                a42Activity.FAB_UPLOAD.hide();
                a42Activity.UPLOAD.setImageResource(R.drawable.ic_file_upload_white_18dp);
                a42Activity.FAB_UPLOAD.setEnabled(true);
            } else {
                a42Activity.UPLOAD.setImageResource(R.drawable.ic_done_white_18dp);
                a42Activity.FAB_UPLOAD.setEnabled(false);
            }
        } catch (Exception e){
            Toast.makeText(a42Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            a42Activity.ID = currentItem.getID();
            a42Activity.LBR_TXT = currentItem.getLBR();
            a42Activity.PFR_TXT = currentItem.getPFR();
            a42Activity.SKTU_TXT = currentItem.getSKTU();
            a42Activity.KTU_TXT = currentItem.getKTU();
            a42Activity.KTU2_TXT = currentItem.getKTU2();
            a42Activity.KTU3_TXT = currentItem.getKTU3();
            a42Activity.REC_TXT = currentItem.getREC();
            a42Activity.DIR1 = currentItem.getDIR1();
            a42Activity.DIR2 = currentItem.getDIR2();

            a42Activity.DEV_LBR = DEV_LBR;
            a42Activity.DEV_PFR = DEV_PFR;
            a42Activity.DEV_KTU = DEV_KTU;
            a42Activity.DEV_KTU2 = DEV_KTU2;
            a42Activity.DEV_KTU3 = DEV_KTU3;

            a42Activity.KTG_LBR = KTG_LBR;
            a42Activity.KTG_PFR = KTG_PFR;
            a42Activity.KTG_KTUU = KTG_KTUU;
            a42Activity.KTG_KLF = KTG_KLF;

            if(KTG_KLF.equals("LS")){
                a42Activity.klf.setText(KTG_KLF);
                a42Activity.klf.setBackground(a42Activity.getResources().getDrawable(R.drawable.failed_style));
                Animation animation = AnimationUtils.loadAnimation(a42Activity.getApplicationContext(), R.anim.recycle_bottom);
                a42Activity.klf.startAnimation(animation);
            } else if(KTG_KLF.equals("Tidak Dinilai")){
                a42Activity.klf.setText("Tidak Dinilai");
                a42Activity.klf.setBackground(a42Activity.getResources().getDrawable(R.drawable.null_style));
                Animation animation = AnimationUtils.loadAnimation(a42Activity.getApplicationContext(), R.anim.recycle_bottom);
                a42Activity.klf.startAnimation(animation);
            } else {
                a42Activity.klf.setText(KTG_KLF);
                a42Activity.klf.setBackground(a42Activity.getResources().getDrawable(R.drawable.success_style));
                Animation animation = AnimationUtils.loadAnimation(a42Activity.getApplicationContext(), R.anim.recycle_bottom);
                a42Activity.klf.startAnimation(animation);
            }
        } catch (Exception e){
            Toast.makeText(a42Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public int getItemCount() {
        return mdataList.size();
    }

}
