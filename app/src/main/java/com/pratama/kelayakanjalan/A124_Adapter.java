package com.pratama.kelayakanjalan;

import android.graphics.BitmapFactory;
import android.util.Log;
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

public class A124_Adapter extends RecyclerView.Adapter<A124_Adapter.A124ViewHolder> {

    private A124Activity a124Activity;
    private List<A124_Class> mdataList;
    private double DEV_BAP, DEV_BTA, STD = 100, DEV_SAJL;
    private String KTG_BAP, KTG_BTA, KTG_SAJL, KTG_KLF;
    private int SAJL_IN;
    private static DecimalFormat df = new DecimalFormat("0.0");

    public static class A124ViewHolder extends RecyclerView.ViewHolder {
        private TextView bap_data, bap_dev, bap_hasil, sajl_data, sajl_dev, sajl_hasil, bta_data,
                bta_dev, bta_hasil, rec_data;
        private LinearLayout bap_back, sajl_back, bta_back;
        private ImageView FT1, FT2, FT3, FT4;

        public A124ViewHolder(View itemView) {
            super(itemView);

            bap_data = (TextView) itemView.findViewById(R.id.bap_data);
            bap_dev = (TextView) itemView.findViewById(R.id.bap_dev);
            bap_hasil = (TextView) itemView.findViewById(R.id.bap_hasil);
            sajl_data = (TextView) itemView.findViewById(R.id.sajl_data);
            sajl_dev = (TextView) itemView.findViewById(R.id.sajl_dev);
            sajl_hasil = (TextView) itemView.findViewById(R.id.sajl_hasil);
            bta_data = (TextView) itemView.findViewById(R.id.bta_data);
            bta_dev = (TextView) itemView.findViewById(R.id.bta_dev);
            bta_hasil = (TextView) itemView.findViewById(R.id.bta_hasil);
            rec_data = (TextView) itemView.findViewById(R.id.rec_data);

            FT1 = (ImageView) itemView.findViewById(R.id.FT1);
            FT2 = (ImageView) itemView.findViewById(R.id.FT2);
            FT3 = (ImageView) itemView.findViewById(R.id.FT3);
            FT4 = (ImageView) itemView.findViewById(R.id.FT4);

            bap_back = (LinearLayout) itemView.findViewById(R.id.bap_back);
            sajl_back = (LinearLayout) itemView.findViewById(R.id.sajl_back);
            bta_back = (LinearLayout) itemView.findViewById(R.id.bta_back);

        }
    }

    public A124_Adapter(A124Activity a124Activity, List<A124_Class> dataList) {
        this.a124Activity = a124Activity;
        this.mdataList = dataList;
    }

    @NonNull
    @Override
    public A124_Adapter.A124ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.a124_layout, parent, false);
        A124_Adapter.A124ViewHolder rsu = new A124_Adapter.A124ViewHolder(view);
        return rsu;
    }

    @Override
    public void onBindViewHolder(@NonNull A124_Adapter.A124ViewHolder holder, int position) {

        final A124_Class currentItem = mdataList.get(position);

        //Banyaknya Akses persil

        try {
            if(currentItem.getBAP() == -1){
                holder.bap_data.setText("-");
                DEV_BAP = -1;
                KTG_BAP = "-";
                holder.bap_dev.setText("-");
                holder.bap_hasil.setText(KTG_BAP);
                holder.bap_back.setBackground(a124Activity.getResources().getDrawable(R.drawable.null_style));
            } else {
                if(a124Activity.FNG.equals("Arteri")){
                    holder.bap_data.setText(String.valueOf(currentItem.getBAP())+"/1 Km");
                    if(a124Activity.KPR.equals("Jalan Bebas Hambatan (JBH)")){
                        DEV_BAP = -1;
                        KTG_BAP = "-";
                        holder.bap_back.setBackground(a124Activity.getResources().getDrawable(R.drawable.null_style));
                    } else if(a124Activity.KPR.equals("Jalan Raya (JR)")){
                        if(currentItem.getBAP() == 1){
                            DEV_BAP = 0;
                            KTG_BAP = "LF";
                            holder.bap_back.setBackground(a124Activity.getResources().getDrawable(R.drawable.success_style));
                        } else {
                            DEV_BAP = (currentItem.getBAP() - 1)/1;
                            DEV_BAP = (DEV_BAP * 100);
                            if(DEV_BAP < 0){
                                DEV_BAP = DEV_BAP * -1;
                            }
                            if(DEV_BAP > 100){
                                DEV_BAP = 100;
                            }
                            DEV_BAP = Double.parseDouble(df.format(DEV_BAP).replace(",", "."));
                            KTG_BAP = "LS";
                            holder.bap_back.setBackground(a124Activity.getResources().getDrawable(R.drawable.failed_style));
                        }
                    }  else if(a124Activity.KPR.equals("Jalan Sedang (JS)")){
                        if(currentItem.getBAP() == 1){
                            DEV_BAP = 0;
                            KTG_BAP = "LF";
                            holder.bap_back.setBackground(a124Activity.getResources().getDrawable(R.drawable.success_style));
                        } else {
                            DEV_BAP = (currentItem.getBAP() - 1)/1;
                            DEV_BAP = (DEV_BAP * 100);
                            if(DEV_BAP < 0){
                                DEV_BAP = DEV_BAP * -1;
                            }
                            if(DEV_BAP > 100){
                                DEV_BAP = 100;
                            }
                            DEV_BAP = Double.parseDouble(df.format(DEV_BAP).replace(",", "."));
                            KTG_BAP = "LS";
                            holder.bap_back.setBackground(a124Activity.getResources().getDrawable(R.drawable.failed_style));
                        }
                    }  else {
                        DEV_BAP = -1;
                        KTG_BAP = "-";
                        holder.bap_back.setBackground(a124Activity.getResources().getDrawable(R.drawable.null_style));
                    }
                } else if(a124Activity.FNG.equals("Kolektor")){
                    holder.bap_data.setText(String.valueOf(currentItem.getBAP())+"/0.5 Km");
                    if(a124Activity.KPR.equals("Jalan Bebas Hambatan (JBH)")){
                        DEV_BAP = -1;
                        KTG_BAP = "-";
                        holder.bap_back.setBackground(a124Activity.getResources().getDrawable(R.drawable.null_style));
                    } else if(a124Activity.KPR.equals("Jalan Raya (JR)")){
                        if(currentItem.getBAP() == 1){
                            DEV_BAP = 0;
                            KTG_BAP = "LF";
                            holder.bap_back.setBackground(a124Activity.getResources().getDrawable(R.drawable.success_style));
                        } else {
                            DEV_BAP = (currentItem.getBAP() - 1)/1;
                            DEV_BAP = (DEV_BAP * 100);
                            if(DEV_BAP < 0){
                                DEV_BAP = DEV_BAP * -1;
                            }
                            if(DEV_BAP > 100){
                                DEV_BAP = 100;
                            }
                            DEV_BAP = Double.parseDouble(df.format(DEV_BAP).replace(",", "."));
                            KTG_BAP = "LS";
                            holder.bap_back.setBackground(a124Activity.getResources().getDrawable(R.drawable.failed_style));
                        }
                    }  else if(a124Activity.KPR.equals("Jalan Sedang (JS)")){
                        if(currentItem.getBAP() == 1){
                            DEV_BAP = 0;
                            KTG_BAP = "LF";
                            holder.bap_back.setBackground(a124Activity.getResources().getDrawable(R.drawable.success_style));
                        } else {
                            DEV_BAP = (currentItem.getBAP() - 1)/1;
                            DEV_BAP = (DEV_BAP * 100);
                            if(DEV_BAP < 0){
                                DEV_BAP = DEV_BAP * -1;
                            }
                            if(DEV_BAP > 100){
                                DEV_BAP = 100;
                            }
                            DEV_BAP = Double.parseDouble(df.format(DEV_BAP).replace(",", "."));
                            KTG_BAP = "LS";
                            holder.bap_back.setBackground(a124Activity.getResources().getDrawable(R.drawable.failed_style));
                        }
                    }  else {
                        DEV_BAP = -1;
                        KTG_BAP = "-";
                        holder.bap_back.setBackground(a124Activity.getResources().getDrawable(R.drawable.null_style));
                    }
                }
                holder.bap_dev.setText(String.valueOf(DEV_BAP)+"%");
                holder.bap_hasil.setText(KTG_BAP);
            }
        } catch (Exception e){
            Toast.makeText(a124Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        //Akses ke Jalan Utama

        try {
            if(currentItem.getSAJL().equals("Melalui Bukaan pada samping ke Jalur Utama")){
                SAJL_IN = 1;
                DEV_SAJL = 0;
                KTG_SAJL = "LF";
                holder.sajl_back.setBackground(a124Activity.getResources().getDrawable(R.drawable.success_style));
            } else if(currentItem.getSAJL().equals("Langsung dengan dilengkapi Fasilitas Manajemen Lalu Lintas")){
                SAJL_IN = 2;
                DEV_SAJL = 0;
                KTG_SAJL = "LF";
                holder.sajl_back.setBackground(a124Activity.getResources().getDrawable(R.drawable.success_style));
            } else if(currentItem.getSAJL().equals("Langsung tanpa dilengkapi Fasilitas Manajemen Lalu Lintas")){
                SAJL_IN = 3;
                DEV_SAJL = 100;
                KTG_SAJL = "LS";
                holder.sajl_back.setBackground(a124Activity.getResources().getDrawable(R.drawable.failed_style));
            } else {
                SAJL_IN = 4;
                DEV_SAJL = 100;
                KTG_SAJL = "LS";
                holder.sajl_back.setBackground(a124Activity.getResources().getDrawable(R.drawable.failed_style));
            }
            holder.sajl_data.setText("("+String.valueOf(SAJL_IN)+") "+currentItem.getSAJL());
            holder.sajl_dev.setText(String.valueOf(DEV_SAJL)+"%");
            holder.sajl_hasil.setText(KTG_SAJL);
        } catch (Exception e){
            Toast.makeText(a124Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        //Bentuk Akses

        try {
            if(currentItem.getBTA() == -1){
                holder.bta_data.setText("-");
                DEV_BTA = -1;
                KTG_BTA = "-";
                holder.bta_dev.setText("-");
                holder.bta_hasil.setText(KTG_BTA);
                holder.bta_back.setBackground(a124Activity.getResources().getDrawable(R.drawable.null_style));
            } else {
                holder.bta_data.setText(String.valueOf(currentItem.getBTA())+"%");
                if(a124Activity.FNG.equals("Arteri")){
                    if(a124Activity.SJR.equals("Primer (Antar Kota)")){
                        DEV_BTA = STD - currentItem.getBTA();
                        if(DEV_BTA == 0){
                            KTG_BTA = "LF";
                            holder.bta_back.setBackground(a124Activity.getResources().getDrawable(R.drawable.success_style));
                        } else {
                            KTG_BTA = "LS";
                            holder.bta_back.setBackground(a124Activity.getResources().getDrawable(R.drawable.failed_style));
                        }
                    } else {
                        DEV_BTA = STD - currentItem.getBTA();
                        if(DEV_BTA == 0){
                            KTG_BTA = "LF";
                            holder.bta_back.setBackground(a124Activity.getResources().getDrawable(R.drawable.success_style));
                        } else {
                            KTG_BTA = "LS";
                            holder.bta_back.setBackground(a124Activity.getResources().getDrawable(R.drawable.failed_style));
                        }
                    }
                } else {
                    if(a124Activity.SJR.equals("Primer (Antar Kota)")){
                        DEV_BTA = STD - currentItem.getBTA();
                        if(DEV_BTA == 0){
                            KTG_BTA = "LF";
                            holder.bta_back.setBackground(a124Activity.getResources().getDrawable(R.drawable.success_style));
                        } else {
                            KTG_BTA = "LS";
                            holder.bta_back.setBackground(a124Activity.getResources().getDrawable(R.drawable.failed_style));
                        }
                    } else {
                        DEV_BTA = STD - currentItem.getBTA();
                        if(DEV_BTA == 0){
                            KTG_BTA = "LF";
                            holder.bta_back.setBackground(a124Activity.getResources().getDrawable(R.drawable.success_style));
                        } else {
                            KTG_BTA = "LS";
                            holder.bta_back.setBackground(a124Activity.getResources().getDrawable(R.drawable.failed_style));
                        }
                    }
                }
                holder.bta_dev.setText(String.valueOf(DEV_BTA)+"%");
                holder.bta_hasil.setText(KTG_BTA);
            }
        } catch (Exception e){
            Toast.makeText(a124Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        //KLF

        try {
            if((KTG_BAP.equals("LF") || KTG_BAP.equals("-")) && (KTG_SAJL.equals("LF") ||
                    KTG_SAJL.equals("-")) && (KTG_BTA.equals("LF") || KTG_BTA.equals("-"))){

                if(KTG_BAP.equals("-") && KTG_SAJL.equals("-") && KTG_BTA.equals("-")){
                    KTG_KLF = "Tidak Dinilai";
                } else {
                    KTG_KLF = "LF";
                }
            }  else {
                KTG_KLF = "LS";
            }
        } catch (Exception e){
            Toast.makeText(a124Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            holder.rec_data.setText(currentItem.getREC());
        } catch (Exception e){
            Toast.makeText(a124Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            holder.FT1.setImageBitmap(BitmapFactory.decodeFile(currentItem.getDIR1()));
        } catch (Exception e){
            Toast.makeText(a124Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            holder.FT2.setImageBitmap(BitmapFactory.decodeFile(currentItem.getDIR2()));
        } catch (Exception e){
            Toast.makeText(a124Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            holder.FT3.setImageBitmap(BitmapFactory.decodeFile(currentItem.getDIR3()));
        } catch (Exception e){
            Toast.makeText(a124Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            holder.FT4.setImageBitmap(BitmapFactory.decodeFile(currentItem.getDIR4()));
        } catch (Exception e){
            Toast.makeText(a124Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        //FAB UPLOAD

        try {
            if(currentItem.getUPLOAD().equals("F")){
                a124Activity.FAB_UPLOAD.hide();
                a124Activity.UPLOAD.setImageResource(R.drawable.ic_file_upload_white_18dp);
                a124Activity.FAB_UPLOAD.setEnabled(true);
            } else {
                a124Activity.UPLOAD.setImageResource(R.drawable.ic_done_white_18dp);
                a124Activity.FAB_UPLOAD.setEnabled(false);
            }
        } catch (Exception e){
            Toast.makeText(a124Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        //Kirim ke Activity

        try {
            a124Activity.ID = currentItem.getID();
            a124Activity.BAP_TXT = currentItem.getBAP();
            a124Activity.SAJL_IN = SAJL_IN;
            a124Activity.SAJL_TXT = currentItem.getSAJL();
            a124Activity.BTA_TXT = currentItem.getBTA();
            a124Activity.REC_TXT = currentItem.getREC();
            a124Activity.DIR1 = currentItem.getDIR1();
            a124Activity.DIR2 = currentItem.getDIR2();
            a124Activity.DIR3 = currentItem.getDIR3();
            a124Activity.DIR4 = currentItem.getDIR4();

            a124Activity.DEV_BAP = DEV_BAP;
            a124Activity.DEV_SAJL = DEV_SAJL;
            a124Activity.DEV_BTA = DEV_BTA;

            a124Activity.KTG_BAP = KTG_BAP;
            a124Activity.KTG_SAJL = KTG_SAJL;
            a124Activity.KTG_BTA = KTG_BTA;
            a124Activity.KTG_KLF = KTG_KLF;

            if(KTG_KLF.equals("LS")){
                a124Activity.klf.setText(KTG_KLF);
                a124Activity.klf.setBackground(a124Activity.getResources().getDrawable(R.drawable.failed_style));
                Animation animation = AnimationUtils.loadAnimation(a124Activity.getApplicationContext(), R.anim.recycle_bottom);
                a124Activity.klf.startAnimation(animation);
            } else if(KTG_KLF.equals("Tidak Dinilai")){
                a124Activity.klf.setText("Tidak Dinilai");
                a124Activity.klf.setBackground(a124Activity.getResources().getDrawable(R.drawable.null_style));
                Animation animation = AnimationUtils.loadAnimation(a124Activity.getApplicationContext(), R.anim.recycle_bottom);
                a124Activity.klf.startAnimation(animation);
            } else {
                a124Activity.klf.setText(KTG_KLF);
                a124Activity.klf.setBackground(a124Activity.getResources().getDrawable(R.drawable.success_style));
                Animation animation = AnimationUtils.loadAnimation(a124Activity.getApplicationContext(), R.anim.recycle_bottom);
                a124Activity.klf.startAnimation(animation);
            }
        } catch (Exception e){
            Toast.makeText(a124Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public int getItemCount() {
        return mdataList.size();
    }
}
