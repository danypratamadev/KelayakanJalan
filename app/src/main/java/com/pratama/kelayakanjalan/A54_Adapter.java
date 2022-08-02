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

public class A54_Adapter extends RecyclerView.Adapter<A54_Adapter.A54ViewHolder> {

    private A54Activity a54Activity;
    private List<A54_Class> mdataList;
    private double DEV_KML, DEV_KML2, DEV_KML3, DEV_MRK, DEV_MRK2, DEV_MRK3, DEV_MRK4, DEV_WRK, DEV_RBP, STD = 100;
    private int KML_IN, KML2_IN, KML3_IN, BPJ_IN;
    private String KTG_KML, KTG_KML2, KTG_KML3, KTG_KMLL, KTG_MRK, KTG_MRK2, KTG_MRK3, KTG_MRK4, KTG_MRKK, KTG_WRK, KTG_RBP, KTG_KLF;
    private static DecimalFormat df = new DecimalFormat("0.0");

    public class A54ViewHolder extends RecyclerView.ViewHolder {

        private TextView kml_data, kml_dev, kml2_data, kml2_dev, kml3_data, kml3_dev, kml_hasil,
                bpj_data, bpj_dev, bpj_hasil, mrk_data, mrk_dev,
                mrk2_data, mrk2_dev, mrk3_data, mrk3_dev, mrk4_data, mrk4_dev, mrk_hasil, wrk_data,
                wrk_dev, wrk_hasil, rbp_data, rbp_dev, rbp_hasil, rec_data;
        private LinearLayout kml_back, bpj_back, mrk_back, wrk_back, rbp_back;
        private ImageView FT1, FT2;

        public A54ViewHolder(@NonNull View itemView) {
            super(itemView);

            kml_data = (TextView) itemView.findViewById(R.id.kml_data);
            kml_dev = (TextView) itemView.findViewById(R.id.kml_dev);
            kml2_data = (TextView) itemView.findViewById(R.id.kml2_data);
            kml2_dev = (TextView) itemView.findViewById(R.id.kml2_dev);
            kml3_data = (TextView) itemView.findViewById(R.id.kml3_data);
            kml3_dev = (TextView) itemView.findViewById(R.id.kml3_dev);
            kml_hasil = (TextView) itemView.findViewById(R.id.kml_hasil);

            bpj_data = (TextView) itemView.findViewById(R.id.bpj_data);
            bpj_dev = (TextView) itemView.findViewById(R.id.bpj_dev);
            bpj_hasil = (TextView) itemView.findViewById(R.id.bpj_hasil);

            mrk_data = (TextView) itemView.findViewById(R.id.mrk_data);
            mrk_dev = (TextView) itemView.findViewById(R.id.mrk_dev);
            mrk2_data = (TextView) itemView.findViewById(R.id.mrk2_data);
            mrk2_dev = (TextView) itemView.findViewById(R.id.mrk2_dev);
            mrk3_data = (TextView) itemView.findViewById(R.id.mrk3_data);
            mrk3_dev = (TextView) itemView.findViewById(R.id.mrk3_dev);
            mrk4_data = (TextView) itemView.findViewById(R.id.mrk4_data);
            mrk4_dev = (TextView) itemView.findViewById(R.id.mrk4_dev);
            mrk_hasil = (TextView) itemView.findViewById(R.id.mrk_hasil);

            wrk_data = (TextView) itemView.findViewById(R.id.wrk_data);
            wrk_dev = (TextView) itemView.findViewById(R.id.wrk_dev);
            wrk_hasil = (TextView) itemView.findViewById(R.id.wrk_hasil);

            rbp_data = (TextView) itemView.findViewById(R.id.rbp_data);
            rbp_dev = (TextView) itemView.findViewById(R.id.rbp_dev);
            rbp_hasil = (TextView) itemView.findViewById(R.id.rbp_hasil);

            rec_data = (TextView) itemView.findViewById(R.id.rec_data);

            FT1 = (ImageView) itemView.findViewById(R.id.FT1);
            FT2 = (ImageView) itemView.findViewById(R.id.FT2);

            kml_back = (LinearLayout) itemView.findViewById(R.id.kml_back);
            bpj_back = (LinearLayout) itemView.findViewById(R.id.bpj_back);
            mrk_back = (LinearLayout) itemView.findViewById(R.id.mrk_back);
            wrk_back = (LinearLayout) itemView.findViewById(R.id.wrk_back);
            rbp_back = (LinearLayout) itemView.findViewById(R.id.rbp_back);

        }

    }

    public A54_Adapter(A54Activity a54Activity, List<A54_Class> mdataList) {
        this.a54Activity = a54Activity;
        this.mdataList = mdataList;
    }

    @NonNull
    @Override
    public A54_Adapter.A54ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.a54_layout, parent, false);
        A54_Adapter.A54ViewHolder a54 = new A54_Adapter.A54ViewHolder(view);
        return a54;
    }

    @Override
    public void onBindViewHolder(@NonNull A54_Adapter.A54ViewHolder holder, int position) {

        final A54_Class currentItem = mdataList.get(position);

        //Kebutuhan Manajemen Lalu Lintas

        try {
            if(currentItem.getKML().equals("Ada")){
                KML_IN = 1;
                DEV_KML = 0;
                KTG_KML = "LF";
                holder.kml_data.setText(String.valueOf(KML_IN));
                holder.kml_dev.setText(String.valueOf(DEV_KML)+"%");
            } else if(currentItem.getKML().equals("Tidak Ada")){
                KML_IN = 2;
                DEV_KML = 100;
                KTG_KML = "LS";
                holder.kml_data.setText(String.valueOf(KML_IN));
                holder.kml_dev.setText(String.valueOf(DEV_KML)+"%");
            } else {
                KML_IN = 3;
                DEV_KML = -1;
                KTG_KML = "-";
                holder.kml_data.setText(String.valueOf(KML_IN));
                holder.kml_dev.setText("-");
            }
        } catch (Exception e){
            Toast.makeText(a54Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            if(currentItem.getKML2().equals("Ada")){
                KML2_IN = 1;
                DEV_KML2 = 0;
                KTG_KML2 = "LF";
                holder.kml2_data.setText(String.valueOf(KML2_IN));
                holder.kml2_dev.setText(String.valueOf(DEV_KML2)+"%");
            } else if(currentItem.getKML2().equals("Tidak Ada")){
                KML2_IN = 2;
                DEV_KML2 = 100;
                KTG_KML2 = "LS";
                holder.kml2_data.setText(String.valueOf(KML2_IN));
                holder.kml2_dev.setText(String.valueOf(DEV_KML2)+"%");
            } else {
                KML2_IN = 3;
                DEV_KML2 = -1;
                KTG_KML2 = "-";
                holder.kml2_data.setText(String.valueOf(KML2_IN));
                holder.kml2_dev.setText("-");
            }
        } catch (Exception e){
            Toast.makeText(a54Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            if(currentItem.getKML3().equals("Ada")){
                KML3_IN = 1;
                DEV_KML3 = 0;
                KTG_KML3 = "LF";
                holder.kml3_data.setText(String.valueOf(KML3_IN));
                holder.kml3_dev.setText(String.valueOf(DEV_KML3)+"%");
            } else if(currentItem.getKML3().equals("Tidak Ada")){
                KML3_IN = 2;
                DEV_KML3 = 100;
                KTG_KML3 = "LS";
                holder.kml3_data.setText(String.valueOf(KML3_IN));
                holder.kml3_dev.setText(String.valueOf(DEV_KML3)+"%");
            } else {
                KML3_IN = 3;
                DEV_KML3 = -1;
                KTG_KML3 = "-";
                holder.kml3_data.setText(String.valueOf(KML3_IN));
                holder.kml3_dev.setText("-");
            }
        } catch (Exception e){
            Toast.makeText(a54Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            if((KTG_KML.equals("LF") || KTG_KML.equals("-")) && (KTG_KML2.equals("LF") || KTG_KML2.equals("-"))
                    && (KTG_KML3.equals("LF") || KTG_KML3.equals("-"))){

                if(KTG_KML.equals("-") && KTG_KML2.equals("-") && KTG_KML3.equals("-")){
                    KTG_KMLL = "-";
                    holder.kml_back.setBackground(a54Activity.getResources().getDrawable(R.drawable.null_style));
                } else {
                    KTG_KMLL = "LF";
                    holder.kml_back.setBackground(a54Activity.getResources().getDrawable(R.drawable.success_style));
                }
            } else {
                KTG_KMLL = "LS";
                holder.kml_back.setBackground(a54Activity.getResources().getDrawable(R.drawable.failed_style));
            }
            holder.kml_hasil.setText(KTG_KMLL);
        } catch (Exception e){
            Toast.makeText(a54Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        //Bentuk Pulau Jalan

        try {
            if(currentItem.getSBPJ().equals("Segitiga")){
                BPJ_IN = 1;
                holder.bpj_back.setBackground(a54Activity.getResources().getDrawable(R.drawable.success_style));
            } else if(currentItem.getSBPJ().equals("Persegi Panjang")) {
                BPJ_IN = 2;
                holder.bpj_back.setBackground(a54Activity.getResources().getDrawable(R.drawable.success_style));
            } else {
                BPJ_IN = 3;
                holder.bpj_back.setBackground(a54Activity.getResources().getDrawable(R.drawable.null_style));
            }
            holder.bpj_data.setText("("+String.valueOf(BPJ_IN)+") "+currentItem.getSBPJ());
            holder.bpj_dev.setText("-");
            holder.bpj_hasil.setText("-");
        } catch (Exception e){
            Toast.makeText(a54Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        //Marka

        try {
            if(!currentItem.getKML().equals("Ada") && !currentItem.getKML2().equals("Ada") && !currentItem.getKML3().equals("Ada")){

                DEV_MRK = -1;
                KTG_MRK = "-";
                holder.mrk_data.setText("-");
                holder.mrk_dev.setText("-");

                DEV_MRK2 = -1;
                KTG_MRK2 = "-";
                holder.mrk2_data.setText("-");
                holder.mrk2_dev.setText("-");

                DEV_MRK3 = -1;
                KTG_MRK3 = "-";
                holder.mrk3_data.setText("-");
                holder.mrk3_dev.setText("-");

                DEV_MRK4 = -1;
                KTG_MRK4 = "-";
                holder.mrk4_data.setText("-");
                holder.mrk4_dev.setText("-");

                DEV_WRK = -1;
                KTG_WRK = "-";
                holder.wrk_data.setText("-");
                holder.wrk_dev.setText("-");
                holder.wrk_hasil.setText(KTG_WRK);
                holder.wrk_back.setBackground(a54Activity.getResources().getDrawable(R.drawable.null_style));


            } else {

                if(currentItem.getMRK() == -1){
                    DEV_MRK = -1;
                    KTG_MRK = "-";
                    holder.mrk_data.setText("-");
                    holder.mrk_dev.setText("-");
                } else {
                    DEV_MRK = STD - currentItem.getMRK();
                    if(DEV_MRK == 0){
                        KTG_MRK = "LF";
                    } else {
                        KTG_MRK = "LS";
                    }
                    holder.mrk_data.setText(String.valueOf(currentItem.getMRK())+"%");
                    holder.mrk_dev.setText(String.valueOf(DEV_MRK)+"%");
                }

                if(currentItem.getMRK2() == -1){
                    DEV_MRK2 = -1;
                    KTG_MRK2 = "-";
                    holder.mrk2_data.setText("-");
                    holder.mrk2_dev.setText("-");
                } else {
                    DEV_MRK2 = STD - currentItem.getMRK2();
                    if(DEV_MRK2 == 0){
                        KTG_MRK2 = "LF";
                    } else {
                        KTG_MRK2 = "LS";
                    }
                    holder.mrk2_data.setText(String.valueOf(currentItem.getMRK2())+"%");
                    holder.mrk2_dev.setText(String.valueOf(DEV_MRK2)+"%");
                }

                if(currentItem.getMRK3() == -1){
                    DEV_MRK3 = -1;
                    KTG_MRK3 = "-";
                    holder.mrk3_data.setText("-");
                    holder.mrk3_dev.setText("-");
                } else {
                    DEV_MRK3 = STD - currentItem.getMRK3();
                    if(DEV_MRK3 == 0){
                        KTG_MRK3 = "LF";
                    } else {
                        KTG_MRK3 = "LS";
                    }
                    holder.mrk3_data.setText(String.valueOf(currentItem.getMRK3())+"%");
                    holder.mrk3_dev.setText(String.valueOf(DEV_MRK3)+"%");
                }

                if(currentItem.getMRK4() == -1){
                    DEV_MRK4 = -1;
                    KTG_MRK4 = "-";
                    holder.mrk4_data.setText("-");
                    holder.mrk4_dev.setText("-");
                } else {
                    DEV_MRK4 = STD - currentItem.getMRK4();
                    if(DEV_MRK4 == 0){
                        KTG_MRK4 = "LF";
                    } else {
                        KTG_MRK4 = "LS";
                    }
                    holder.mrk4_data.setText(String.valueOf(currentItem.getMRK4())+"%");
                    holder.mrk4_dev.setText(String.valueOf(DEV_MRK4)+"%");
                }

                //Warna Kerb

                try {
                    if(currentItem.getWRK() == -1){
                        DEV_WRK = -1;
                        KTG_WRK = "-";
                        holder.wrk_data.setText("-");
                        holder.wrk_dev.setText("-");
                        holder.wrk_hasil.setText(KTG_WRK);
                        holder.wrk_back.setBackground(a54Activity.getResources().getDrawable(R.drawable.null_style));
                    } else {
                        DEV_WRK = STD - currentItem.getWRK();
                        if(DEV_WRK == 0){
                            KTG_WRK = "LF";
                            holder.wrk_back.setBackground(a54Activity.getResources().getDrawable(R.drawable.success_style));
                        } else {
                            KTG_WRK = "LS";
                            holder.wrk_back.setBackground(a54Activity.getResources().getDrawable(R.drawable.failed_style));
                        }
                        holder.wrk_data.setText(String.valueOf(currentItem.getWRK())+"%");
                        holder.wrk_dev.setText(String.valueOf(DEV_WRK)+"%");
                        holder.wrk_hasil.setText(KTG_WRK);
                    }
                } catch (Exception e){
                    Toast.makeText(a54Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
                }

            }
        } catch (Exception e){
            Toast.makeText(a54Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            if((KTG_MRK.equals("LF") || KTG_MRK.equals("-")) && (KTG_MRK2.equals("LF") || KTG_MRK2.equals("-"))
                    && (KTG_MRK3.equals("LF") || KTG_MRK3.equals("-")) && (KTG_MRK4.equals("LF") || KTG_MRK4.equals("-"))){

                if(KTG_MRK.equals("-") && KTG_MRK2.equals("-") && KTG_MRK3.equals("-") && KTG_MRK4.equals("-")){
                    KTG_MRKK = "-";
                    holder.mrk_back.setBackground(a54Activity.getResources().getDrawable(R.drawable.null_style));
                } else {
                    KTG_MRKK = "LF";
                    holder.mrk_back.setBackground(a54Activity.getResources().getDrawable(R.drawable.success_style));
                }
            } else {
                KTG_MRKK = "LS";
                holder.mrk_back.setBackground(a54Activity.getResources().getDrawable(R.drawable.failed_style));
            }
            holder.mrk_hasil.setText(KTG_MRKK);
        } catch (Exception e){
            Toast.makeText(a54Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        //Rambu Pengarah

        try {
            if(currentItem.getRBP() == -1){
                DEV_RBP = -1;
                KTG_RBP = "-";
                holder.rbp_data.setText("-");
                holder.rbp_dev.setText("-");
                holder.rbp_hasil.setText(KTG_RBP);
                holder.rbp_back.setBackground(a54Activity.getResources().getDrawable(R.drawable.null_style));
            } else {
                DEV_RBP = STD - currentItem.getRBP();
                if(DEV_RBP == 0){
                    KTG_RBP = "LF";
                    holder.rbp_back.setBackground(a54Activity.getResources().getDrawable(R.drawable.success_style));
                } else {
                    KTG_RBP = "LS";
                    holder.rbp_back.setBackground(a54Activity.getResources().getDrawable(R.drawable.failed_style));
                }
                holder.rbp_data.setText(String.valueOf(currentItem.getRBP())+"%");
                holder.rbp_dev.setText(String.valueOf(DEV_RBP)+"%");
                holder.rbp_hasil.setText(KTG_RBP);
            }
        } catch (Exception e){
            Toast.makeText(a54Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        //KLF

        try {
            if((KTG_KMLL.equals("LF") || KTG_KMLL.equals("-")) && (KTG_MRKK.equals("LF") || KTG_MRKK.equals("-"))
                    && (KTG_WRK.equals("LF") || KTG_WRK.equals("-")) && (KTG_RBP.equals("LF") || KTG_RBP.equals("-"))){

                if(KTG_KMLL.equals("-") && KTG_MRKK.equals("-") && KTG_WRK.equals("-") && KTG_RBP.equals("-")){
                    KTG_KLF = "Tidak Dinilai";
                } else {
                    KTG_KLF = "LF";
                }

            } else {
                KTG_KLF = "LS";
            }
        } catch (Exception e){
            Toast.makeText(a54Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            holder.rec_data.setText(currentItem.getREC());
        } catch (Exception e){
            Toast.makeText(a54Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            holder.FT1.setImageBitmap(BitmapFactory.decodeFile(currentItem.getDIR1()));
        } catch (Exception e){
            Toast.makeText(a54Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            holder.FT2.setImageBitmap(BitmapFactory.decodeFile(currentItem.getDIR2()));
        } catch (Exception e){
            Toast.makeText(a54Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        //FAB UPLOAD

        try {
            if(currentItem.getUPLOAD().equals("F")){
                a54Activity.FAB_UPLOAD.hide();
                a54Activity.UPLOAD.setImageResource(R.drawable.ic_file_upload_white_18dp);
                a54Activity.FAB_UPLOAD.setEnabled(true);
            } else {
                a54Activity.UPLOAD.setImageResource(R.drawable.ic_done_white_18dp);
                a54Activity.FAB_UPLOAD.setEnabled(false);
            }
        } catch (Exception e){
            Toast.makeText(a54Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        //Kirim ke Activity

        try {
            a54Activity.ID = currentItem.getID();
            a54Activity.KML_IN = KML_IN;
            a54Activity.KML2_IN = KML2_IN;
            a54Activity.KML3_IN = KML3_IN;
            a54Activity.BPJ_IN = BPJ_IN;
            a54Activity.KML_TXT = currentItem.getKML();
            a54Activity.KML2_TXT = currentItem.getKML2();
            a54Activity.KML3_TXT = currentItem.getKML3();
            a54Activity.SBPJ_TXT = currentItem.getSBPJ();
            a54Activity.MRK_TXT = currentItem.getMRK();
            a54Activity.MRK2_TXT = currentItem.getMRK2();
            a54Activity.MRK3_TXT = currentItem.getMRK3();
            a54Activity.MRK4_TXT = currentItem.getMRK4();
            a54Activity.WRK_TXT = currentItem.getWRK();
            a54Activity.RBP_TXT = currentItem.getRBP();
            a54Activity.REC_TXT = currentItem.getREC();
            a54Activity.DIR1 = currentItem.getDIR1();
            a54Activity.DIR2 = currentItem.getDIR2();

            a54Activity.DEV_KML = DEV_KML;
            a54Activity.DEV_KML2 = DEV_KML2;
            a54Activity.DEV_KML3 = DEV_KML3;
            a54Activity.DEV_MRK = DEV_MRK;
            a54Activity.DEV_MRK2 = DEV_MRK2;
            a54Activity.DEV_MRK3 = DEV_MRK3;
            a54Activity.DEV_MRK4 = DEV_MRK4;
            a54Activity.DEV_WRK = DEV_WRK;
            a54Activity.DEV_RBP = DEV_RBP;

            a54Activity.KTG_KMLL = KTG_KMLL;
            a54Activity.KTG_MRKK = KTG_MRKK;
            a54Activity.KTG_WRK = KTG_WRK;
            a54Activity.KTG_RBP = KTG_RBP;
            a54Activity.KTG_KLF = KTG_KLF;

            if(KTG_KLF.equals("LS")){
                a54Activity.klf.setText(KTG_KLF);
                a54Activity.klf.setBackground(a54Activity.getResources().getDrawable(R.drawable.failed_style));
                Animation animation = AnimationUtils.loadAnimation(a54Activity.getApplicationContext(), R.anim.recycle_bottom);
                a54Activity.klf.startAnimation(animation);
            } else if(KTG_KLF.equals("Tidak Dinilai")){
                a54Activity.klf.setText("Tidak Dinilai");
                a54Activity.klf.setBackground(a54Activity.getResources().getDrawable(R.drawable.null_style));
                Animation animation = AnimationUtils.loadAnimation(a54Activity.getApplicationContext(), R.anim.recycle_bottom);
                a54Activity.klf.startAnimation(animation);
            } else {
                a54Activity.klf.setText(KTG_KLF);
                a54Activity.klf.setBackground(a54Activity.getResources().getDrawable(R.drawable.success_style));
                Animation animation = AnimationUtils.loadAnimation(a54Activity.getApplicationContext(), R.anim.recycle_bottom);
                a54Activity.klf.startAnimation(animation);
            }
        } catch (Exception e){
            Toast.makeText(a54Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public int getItemCount() {
        return mdataList.size();
    }

}
