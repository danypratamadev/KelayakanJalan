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

public class A53_Adapter extends RecyclerView.Adapter<A53_Adapter.A53ViewHolder> {

    private A53Activity a53Activity;
    private List<A53_Class> mdataList;
    private double DEV_KML, DEV_KML2, DEV_KML3, DEV_BSP, STD = 100;
    private int KML_IN, KML2_IN, KML3_IN;
    private String KTG_KML, KTG_KML2, KTG_KML3, KTG_KMLL, KTG_BSP, KTG_KLF;
    private static DecimalFormat df = new DecimalFormat("0.0");

    public class A53ViewHolder extends RecyclerView.ViewHolder {

        private TextView kml_data, kml_dev, kml2_data, kml2_dev, kml3_data, kml3_dev, kml_hasil,
                bsp_data, bsp_dev, bsp_hasil, rec_data;
        private LinearLayout kml_back, bsp_back;
        private ImageView FT1, FT2;

        public A53ViewHolder(@NonNull View itemView) {
            super(itemView);

            kml_data = (TextView) itemView.findViewById(R.id.kml_data);
            kml_dev = (TextView) itemView.findViewById(R.id.kml_dev);
            kml2_data = (TextView) itemView.findViewById(R.id.kml2_data);
            kml2_dev = (TextView) itemView.findViewById(R.id.kml2_dev);
            kml3_data = (TextView) itemView.findViewById(R.id.kml3_data);
            kml3_dev = (TextView) itemView.findViewById(R.id.kml3_dev);
            kml_hasil = (TextView) itemView.findViewById(R.id.kml_hasil);

            bsp_data = (TextView) itemView.findViewById(R.id.bsp_data);
            bsp_dev = (TextView) itemView.findViewById(R.id.bsp_dev);
            bsp_hasil = (TextView) itemView.findViewById(R.id.bsp_hasil);

            rec_data = (TextView) itemView.findViewById(R.id.rec_data);

            FT1 = (ImageView) itemView.findViewById(R.id.FT1);
            FT2 = (ImageView) itemView.findViewById(R.id.FT2);

            kml_back = (LinearLayout) itemView.findViewById(R.id.kml_back);
            bsp_back = (LinearLayout) itemView.findViewById(R.id.bsp_back);

        }

    }

    public A53_Adapter(A53Activity a53Activity, List<A53_Class> mdataList) {
        this.a53Activity = a53Activity;
        this.mdataList = mdataList;
    }

    @NonNull
    @Override
    public A53_Adapter.A53ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.a53_layout, parent, false);
        A53_Adapter.A53ViewHolder a53 = new A53_Adapter.A53ViewHolder(view);
        return a53;
    }

    @Override
    public void onBindViewHolder(@NonNull A53_Adapter.A53ViewHolder holder, int position) {

        final A53_Class currentItem = mdataList.get(position);

        //Kebutuhan Mananemen Lalu Lintas

        try {
            if(currentItem.getKML().equals("Ada")){
                KML_IN = 1;
                DEV_KML = STD - currentItem.getKML11();
                if(DEV_KML == 0){
                    KTG_KML = "LF";
                } else {
                    KTG_KML = "LS";
                }
                holder.kml_data.setText("("+String.valueOf(KML_IN)+") "+String.valueOf(currentItem.getKML11())+"%");
                holder.kml_dev.setText(String.valueOf(DEV_KML)+"%");
            } else if(currentItem.getKML().equals("Tidak Ada")){
                KML_IN = 2;
                DEV_KML = 100;
                KTG_KML = "LS";
                holder.kml_data.setText("("+String.valueOf(KML_IN)+") "+String.valueOf(currentItem.getKML11())+"%");
                holder.kml_dev.setText(String.valueOf(DEV_KML)+"%");
            } else {
                KML_IN = 3;
                DEV_KML = -1;
                KTG_KML = "-";
                holder.kml_data.setText("("+String.valueOf(KML_IN)+")");
                holder.kml_dev.setText("-");
            }
        } catch (Exception e){
            Toast.makeText(a53Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            if(currentItem.getKML2().equals("Ada")){
                KML2_IN = 1;
                DEV_KML2 = STD - currentItem.getKML22();
                if(DEV_KML2 == 0){
                    KTG_KML2 = "LF";
                } else {
                    KTG_KML2 = "LS";
                }
                holder.kml2_data.setText("("+String.valueOf(KML2_IN)+") "+String.valueOf(currentItem.getKML22())+"%");
                holder.kml2_dev.setText(String.valueOf(DEV_KML2)+"%");
            } else if(currentItem.getKML2().equals("Tidak Ada")){
                KML2_IN = 2;
                DEV_KML2 = 100;
                KTG_KML2 = "LS";
                holder.kml2_data.setText("("+String.valueOf(KML2_IN)+") "+String.valueOf(currentItem.getKML22())+"%");
                holder.kml2_dev.setText(String.valueOf(DEV_KML2)+"%");
            } else {
                KML2_IN = 3;
                DEV_KML2 = -1;
                KTG_KML2 = "-";
                holder.kml2_data.setText("("+String.valueOf(KML2_IN)+")");
                holder.kml2_dev.setText("-");
            }
        } catch (Exception e){
            Toast.makeText(a53Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            if(currentItem.getKML3().equals("Ada")){
                KML3_IN = 1;
                DEV_KML3 = STD - currentItem.getKML33();
                if(DEV_KML3 == 0){
                    KTG_KML3 = "LF";
                } else {
                    KTG_KML3 = "LS";
                }
                holder.kml3_data.setText("("+String.valueOf(KML3_IN)+") "+String.valueOf(currentItem.getKML33())+"%");
                holder.kml3_dev.setText(String.valueOf(DEV_KML3)+"%");
            } else if(currentItem.getKML3().equals("Tidak Ada")){
                KML3_IN = 2;
                DEV_KML3 = 100;
                KTG_KML3 = "LS";
                holder.kml3_data.setText("("+String.valueOf(KML3_IN)+") "+String.valueOf(currentItem.getKML33())+"%");
                holder.kml3_dev.setText(String.valueOf(DEV_KML3)+"%");
            } else {
                KML3_IN = 3;
                DEV_KML3 = -1;
                KTG_KML3 = "-";
                holder.kml3_data.setText("("+String.valueOf(KML3_IN)+")");
                holder.kml3_dev.setText("-");
            }
        } catch (Exception e){
            Toast.makeText(a53Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            if((KTG_KML.equals("LF") || KTG_KML.equals("-")) && (KTG_KML2.equals("LF") || KTG_KML2.equals("-"))
                    && (KTG_KML3.equals("LF") || KTG_KML3.equals("-"))){

                if(KTG_KML.equals("-") && KTG_KML2.equals("-") && KTG_KML3.equals("-")){
                    KTG_KMLL = "-";
                    holder.kml_back.setBackground(a53Activity.getResources().getDrawable(R.drawable.null_style));
                } else {
                    KTG_KMLL = "LF";
                    holder.kml_back.setBackground(a53Activity.getResources().getDrawable(R.drawable.success_style));
                }
            } else {
                KTG_KMLL = "LS";
                holder.kml_back.setBackground(a53Activity.getResources().getDrawable(R.drawable.failed_style));
            }
            holder.kml_hasil.setText(KTG_KMLL);
        } catch (Exception e){
            Toast.makeText(a53Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        //Bukaan pada Separator

        try {
            if(currentItem.getBSP() == -1){
                DEV_BSP = -1;
                KTG_BSP = "-";
                holder.bsp_data.setText("-");
                holder.bsp_dev.setText("-");
                holder.bsp_hasil.setText(KTG_BSP);
                holder.bsp_back.setBackground(a53Activity.getResources().getDrawable(R.drawable.null_style));
            } else {
                DEV_BSP = STD - currentItem.getBSP();
                if(DEV_BSP == 0){
                    KTG_BSP = "LF";
                    holder.bsp_back.setBackground(a53Activity.getResources().getDrawable(R.drawable.success_style));
                } else {
                    KTG_BSP = "LS";
                    holder.bsp_back.setBackground(a53Activity.getResources().getDrawable(R.drawable.failed_style));
                }
                holder.bsp_data.setText(String.valueOf(currentItem.getBSP())+"%");
                holder.bsp_dev.setText(String.valueOf(DEV_BSP)+"%");
            }
        } catch (Exception e){
            Toast.makeText(a53Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        //KLF

        try {
            if((KTG_KMLL.equals("LF") || KTG_KMLL.equals("-")) && (KTG_BSP.equals("LF") || KTG_BSP.equals("-"))){

                if(KTG_KMLL.equals("-") && KTG_BSP.equals("-")){
                    KTG_KLF = "Tidak Dinilai";
                } else {
                    KTG_KLF = "LF";
                }
            } else {
                KTG_KLF = "LS";
            }
        } catch (Exception e){
            Toast.makeText(a53Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            holder.rec_data.setText(currentItem.getREC());
        } catch (Exception e){
            Toast.makeText(a53Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            holder.FT1.setImageBitmap(BitmapFactory.decodeFile(currentItem.getDIR1()));
        } catch (Exception e){
            Toast.makeText(a53Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            holder.FT2.setImageBitmap(BitmapFactory.decodeFile(currentItem.getDIR2()));
        } catch (Exception e){
            Toast.makeText(a53Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        //FAB UPLOAD

        try {
            if(currentItem.getUPLOAD().equals("F")){
                a53Activity.FAB_UPLOAD.hide();
                a53Activity.UPLOAD.setImageResource(R.drawable.ic_file_upload_white_18dp);
                a53Activity.FAB_UPLOAD.setEnabled(true);
            } else {
                a53Activity.UPLOAD.setImageResource(R.drawable.ic_done_white_18dp);
                a53Activity.FAB_UPLOAD.setEnabled(false);
            }
        } catch (Exception e){
            Toast.makeText(a53Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        //Kirim ke Activity

        try {
            a53Activity.ID = currentItem.getID();
            a53Activity.KML_TXT = currentItem.getKML();
            a53Activity.KML2_TXT = currentItem.getKML2();
            a53Activity.KML3_TXT = currentItem.getKML3();
            a53Activity.KML_TXT11 = currentItem.getKML11();
            a53Activity.KML2_TXT22 = currentItem.getKML22();
            a53Activity.KML3_TXT33 = currentItem.getKML33();
            a53Activity.KML_IN = KML_IN;
            a53Activity.KML2_IN = KML2_IN;
            a53Activity.KML3_IN = KML3_IN;
            a53Activity.BSP_TXT = currentItem.getBSP();
            a53Activity.REC_TXT = currentItem.getREC();
            a53Activity.DIR1 = currentItem.getDIR1();
            a53Activity.DIR2 = currentItem.getDIR2();

            a53Activity.DEV_KML = DEV_KML;
            a53Activity.DEV_KML2 = DEV_KML2;
            a53Activity.DEV_KML3 = DEV_KML3;
            a53Activity.DEV_BSP = DEV_BSP;

            a53Activity.KTG_KMLL = KTG_KMLL;
            a53Activity.KTG_BSP = KTG_BSP;
            a53Activity.KTG_KLF = KTG_KLF;

            if(KTG_KLF.equals("LS")){
                a53Activity.klf.setText(KTG_KLF);
                a53Activity.klf.setBackground(a53Activity.getResources().getDrawable(R.drawable.failed_style));
                Animation animation = AnimationUtils.loadAnimation(a53Activity.getApplicationContext(), R.anim.recycle_bottom);
                a53Activity.klf.startAnimation(animation);
            } else if(KTG_KLF.equals("Tidak Dinilai")){
                a53Activity.klf.setText("Tidak Dinilai");
                a53Activity.klf.setBackground(a53Activity.getResources().getDrawable(R.drawable.null_style));
                Animation animation = AnimationUtils.loadAnimation(a53Activity.getApplicationContext(), R.anim.recycle_bottom);
                a53Activity.klf.startAnimation(animation);
            } else {
                a53Activity.klf.setText(KTG_KLF);
                a53Activity.klf.setBackground(a53Activity.getResources().getDrawable(R.drawable.success_style));
                Animation animation = AnimationUtils.loadAnimation(a53Activity.getApplicationContext(), R.anim.recycle_bottom);
                a53Activity.klf.startAnimation(animation);
            }
        } catch (Exception e){
            Toast.makeText(a53Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public int getItemCount() {
        return mdataList.size();
    }

}
