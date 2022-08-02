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

public class A52_Adapter extends RecyclerView.Adapter<A52_Adapter.A52ViewHolder> {

    private A52Activity a52Activity;
    private List<A52_Class> mdataList;
    private double DEV_KML, DEV_KML2, DEV_KML3, DEV_KML4, DEV_KML5, DEV_KML6, DEV_KRP, DEV_KRP2, DEV_KRP3, DEV_KRP4, DEV_KRP5, DEV_KRP6, STD = 100;
    private int KML_IN, KML2_IN, KML3_IN, KML4_IN, KML5_IN, KML6_IN;
    private String KTG_KML, KTG_KML2, KTG_KML3, KTG_KML4, KTG_KML5, KTG_KML6, KTG_KMLL, KTG_KRP, KTG_KRP2, KTG_KRP3, KTG_KRP4, KTG_KRP5, KTG_KRP6, KTG_KRPP, KTG_KLF;
    private static DecimalFormat df = new DecimalFormat("0.0");

    public class A52ViewHolder extends RecyclerView.ViewHolder {

        private TextView kml_data, kml_dev, kml2_data, kml2_dev, kml3_data, kml3_dev, kml4_data, kml4_dev,
                kml5_data, kml5_dev, kml6_data, kml6_dev, kml_hasil,
                krp_data, krp_dev, krp2_data, krp2_dev, krp3_data, krp3_dev, krp4_data, krp4_dev, krp5_data,
                krp5_dev, krp6_data, krp6_dev, krp_hasil, rec_data;

        private LinearLayout kml_back, krp_back;
        private ImageView FT1, FT2;

        public A52ViewHolder(@NonNull View itemView) {
            super(itemView);

            kml_data = (TextView) itemView.findViewById(R.id.kml_data);
            kml_dev = (TextView) itemView.findViewById(R.id.kml_dev);
            kml2_data = (TextView) itemView.findViewById(R.id.kml2_data);
            kml2_dev = (TextView) itemView.findViewById(R.id.kml2_dev);
            kml3_data = (TextView) itemView.findViewById(R.id.kml3_data);
            kml3_dev = (TextView) itemView.findViewById(R.id.kml3_dev);
            kml4_data = (TextView) itemView.findViewById(R.id.kml4_data);
            kml4_dev = (TextView) itemView.findViewById(R.id.kml4_dev);
            kml5_data = (TextView) itemView.findViewById(R.id.kml5_data);
            kml5_dev = (TextView) itemView.findViewById(R.id.kml5_dev);
            kml6_data = (TextView) itemView.findViewById(R.id.kml6_data);
            kml6_dev = (TextView) itemView.findViewById(R.id.kml6_dev);
            kml_hasil = (TextView) itemView.findViewById(R.id.kml_hasil);

            krp_data = (TextView) itemView.findViewById(R.id.krp_data);
            krp_dev = (TextView) itemView.findViewById(R.id.krp_dev);
            krp2_data = (TextView) itemView.findViewById(R.id.krp2_data);
            krp2_dev = (TextView) itemView.findViewById(R.id.krp2_dev);
            krp3_data = (TextView) itemView.findViewById(R.id.krp3_data);
            krp3_dev = (TextView) itemView.findViewById(R.id.krp3_dev);
            krp4_data = (TextView) itemView.findViewById(R.id.krp4_data);
            krp4_dev = (TextView) itemView.findViewById(R.id.krp4_dev);
            krp5_data = (TextView) itemView.findViewById(R.id.krp5_data);
            krp5_dev = (TextView) itemView.findViewById(R.id.krp5_dev);
            krp6_data = (TextView) itemView.findViewById(R.id.krp6_data);
            krp6_dev = (TextView) itemView.findViewById(R.id.krp6_dev);
            krp_hasil = (TextView) itemView.findViewById(R.id.krp_hasil);

            rec_data = (TextView) itemView.findViewById(R.id.rec_data);

            FT1 = (ImageView) itemView.findViewById(R.id.FT1);
            FT2 = (ImageView) itemView.findViewById(R.id.FT2);

            kml_back = (LinearLayout) itemView.findViewById(R.id.kml_back);
            krp_back = (LinearLayout) itemView.findViewById(R.id.krp_back);

        }

    }

    public A52_Adapter(A52Activity a52Activity, List<A52_Class> mdataList) {
        this.a52Activity = a52Activity;
        this.mdataList = mdataList;
    }

    @NonNull
    @Override
    public A52_Adapter.A52ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.a52_layout, parent, false);
        A52_Adapter.A52ViewHolder a52 = new A52_Adapter.A52ViewHolder(view);
        return a52;
    }

    @Override
    public void onBindViewHolder(@NonNull A52_Adapter.A52ViewHolder holder, int position) {

        final A52_Class currentItem = mdataList.get(position);

        //Kebutuhan Manajemen Lalu Lintas

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
            Toast.makeText(a52Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
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
            Toast.makeText(a52Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
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
            Toast.makeText(a52Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            if(currentItem.getKML4().equals("Ada")){
                KML4_IN = 1;
                DEV_KML4 = STD - currentItem.getKML44();
                if(DEV_KML4 == 0){
                    KTG_KML4 = "LF";
                } else {
                    KTG_KML4 = "LS";
                }
                holder.kml4_data.setText("("+String.valueOf(KML4_IN)+") "+String.valueOf(currentItem.getKML44())+"%");
                holder.kml4_dev.setText(String.valueOf(DEV_KML4)+"%");
            } else if(currentItem.getKML4().equals("Tidak Ada")){
                KML4_IN = 2;
                DEV_KML4 = 100;
                KTG_KML4 = "LS";
                holder.kml4_data.setText("("+String.valueOf(KML4_IN)+") "+String.valueOf(currentItem.getKML44())+"%");
                holder.kml4_dev.setText(String.valueOf(DEV_KML4)+"%");
            } else {
                KML4_IN = 3;
                DEV_KML4 = -1;
                KTG_KML4 = "-";
                holder.kml4_data.setText("("+String.valueOf(KML4_IN)+")");
                holder.kml4_dev.setText("-");
            }
        } catch (Exception e){
            Toast.makeText(a52Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            if(currentItem.getKML5().equals("Ada")){
                KML5_IN = 1;
                DEV_KML5 = STD - currentItem.getKML55();
                if(DEV_KML5 == 0){
                    KTG_KML5 = "LF";
                } else {
                    KTG_KML5 = "LS";
                }
                holder.kml5_data.setText("("+String.valueOf(KML5_IN)+") "+String.valueOf(currentItem.getKML55())+"%");
                holder.kml5_dev.setText(String.valueOf(DEV_KML5)+"%");
            } else if(currentItem.getKML5().equals("Tidak Ada")){
                KML5_IN = 2;
                DEV_KML5 = 100;
                KTG_KML5 = "LS";
                holder.kml5_data.setText("("+String.valueOf(KML5_IN)+") "+String.valueOf(currentItem.getKML55())+"%");
                holder.kml5_dev.setText(String.valueOf(DEV_KML5)+"%");
            } else {
                KML5_IN = 3;
                DEV_KML5 = -1;
                KTG_KML5 = "-";
                holder.kml5_data.setText("("+String.valueOf(KML5_IN)+")");
                holder.kml5_dev.setText("-");
            }
        } catch (Exception e){
            Toast.makeText(a52Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            if(currentItem.getKML6().equals("Ada")){
                KML6_IN = 1;
                DEV_KML6 = STD - currentItem.getKML66();
                if(DEV_KML6 == 0){
                    KTG_KML6 = "LF";
                } else {
                    KTG_KML6 = "LS";
                }
                holder.kml6_data.setText("("+String.valueOf(KML6_IN)+") "+String.valueOf(currentItem.getKML66())+"%");
                holder.kml6_dev.setText(String.valueOf(DEV_KML6)+"%");
            } else if(currentItem.getKML6().equals("Tidak Ada")){
                KML6_IN = 2;
                DEV_KML6 = 100;
                KTG_KML6 = "LS";
                holder.kml6_data.setText("("+String.valueOf(KML6_IN)+") "+String.valueOf(currentItem.getKML66())+"%");
                holder.kml6_dev.setText(String.valueOf(DEV_KML6)+"%");
            } else {
                KML6_IN = 3;
                DEV_KML6 = -1;
                KTG_KML6 = "-";
                holder.kml6_data.setText("("+String.valueOf(KML6_IN)+")");
                holder.kml6_dev.setText("-");
            }
        } catch (Exception e){
            Toast.makeText(a52Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            if((KTG_KML.equals("LF") || KTG_KML.equals("-")) && (KTG_KML2.equals("LF") || KTG_KML2.equals("-"))
                    && (KTG_KML3.equals("LF") || KTG_KML3.equals("-")) && (KTG_KML4.equals("LF") || KTG_KML4.equals("-"))
                    && (KTG_KML5.equals("LF") || KTG_KML5.equals("-")) && (KTG_KML6.equals("LF") || KTG_KML6.equals("-"))){

                if(KTG_KML.equals("-") && KTG_KML2.equals("-") && KTG_KML3.equals("-") && KTG_KML4.equals("-") && KTG_KML5.equals("-") && KTG_KML6.equals("-")){
                    KTG_KMLL = "-";
                    holder.kml_back.setBackground(a52Activity.getResources().getDrawable(R.drawable.null_style));
                } else {
                    KTG_KMLL = "LF";
                    holder.kml_back.setBackground(a52Activity.getResources().getDrawable(R.drawable.success_style));
                }
            } else {
                KTG_KMLL = "LS";
                holder.kml_back.setBackground(a52Activity.getResources().getDrawable(R.drawable.failed_style));
            }
            holder.kml_hasil.setText(KTG_KMLL);
        } catch (Exception e){
            Toast.makeText(a52Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        //Ketepatan Jenis Rambu dan Penempatanya

        try {
            if(currentItem.getKRP() == -1){
                DEV_KRP = -1;
                KTG_KRP = "-";
                holder.krp_data.setText("-");
                holder.krp_dev.setText("-");
            } else {
                DEV_KRP = STD - currentItem.getKRP();
                if(DEV_KRP == 0){
                    KTG_KRP = "LF";
                } else {
                    KTG_KRP = "LS";
                }
                holder.krp_data.setText(String.valueOf(currentItem.getKRP())+"%");
                holder.krp_dev.setText(String.valueOf(DEV_KRP)+"%");
            }
        } catch (Exception e){
            Toast.makeText(a52Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            if(currentItem.getKRP2() == -1){
                DEV_KRP2 = -1;
                KTG_KRP2 = "-";
                holder.krp2_data.setText("-");
                holder.krp2_dev.setText("-");
            } else {
                DEV_KRP2 = STD - currentItem.getKRP2();
                if(DEV_KRP2 == 0){
                    KTG_KRP2 = "LF";
                } else {
                    KTG_KRP2 = "LS";
                }
                holder.krp2_data.setText(String.valueOf(currentItem.getKRP2())+"%");
                holder.krp2_dev.setText(String.valueOf(DEV_KRP2)+"%");
            }
        } catch (Exception e){
            Toast.makeText(a52Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            if(currentItem.getKRP3() == -1){
                DEV_KRP3 = -1;
                KTG_KRP3 = "-";
                holder.krp3_data.setText("-");
                holder.krp3_dev.setText("-");
            } else {
                DEV_KRP3 = STD - currentItem.getKRP3();
                if(DEV_KRP3 == 0){
                    KTG_KRP3 = "LF";
                } else {
                    KTG_KRP3 = "LS";
                }
                holder.krp3_data.setText(String.valueOf(currentItem.getKRP3())+"%");
                holder.krp3_dev.setText(String.valueOf(DEV_KRP3)+"%");
            }
        } catch (Exception e){
            Toast.makeText(a52Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            if(currentItem.getKRP4() == -1){
                DEV_KRP4 = -1;
                KTG_KRP4 = "-";
                holder.krp4_data.setText("-");
                holder.krp4_dev.setText("-");
            } else {
                DEV_KRP4 = STD - currentItem.getKRP4();
                if(DEV_KRP4 == 0){
                    KTG_KRP4 = "LF";
                } else {
                    KTG_KRP4 = "LS";
                }
                holder.krp4_data.setText(String.valueOf(currentItem.getKRP4())+"%");
                holder.krp4_dev.setText(String.valueOf(DEV_KRP4)+"%");
            }
        } catch (Exception e){
            Toast.makeText(a52Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            if(currentItem.getKRP5() == -1){
                DEV_KRP5 = -1;
                KTG_KRP5 = "-";
                holder.krp5_data.setText("-");
                holder.krp5_dev.setText("-");
            } else {
                DEV_KRP5 = STD - currentItem.getKRP5();
                if(DEV_KRP5 == 0){
                    KTG_KRP5 = "LF";
                } else {
                    KTG_KRP5 = "LS";
                }
                holder.krp5_data.setText(String.valueOf(currentItem.getKRP5())+"%");
                holder.krp5_dev.setText(String.valueOf(DEV_KRP5)+"%");
            }
        } catch (Exception e){
            Toast.makeText(a52Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            if(currentItem.getKRP6() == -1){
                DEV_KRP6 = -1;
                KTG_KRP6 = "-";
                holder.krp6_data.setText("-");
                holder.krp6_dev.setText("-");
            } else {
                DEV_KRP6 = STD - currentItem.getKRP6();
                if(DEV_KRP6 == 0){
                    KTG_KRP6 = "LF";
                } else {
                    KTG_KRP6 = "LS";
                }
                holder.krp6_data.setText(String.valueOf(currentItem.getKRP6())+"%");
                holder.krp6_dev.setText(String.valueOf(DEV_KRP6)+"%");
            }
        } catch (Exception e){
            Toast.makeText(a52Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            if((KTG_KRP.equals("LF") || KTG_KRP.equals("-")) && (KTG_KRP2.equals("LF") || KTG_KRP2.equals("-"))
                    && (KTG_KRP3.equals("LF") || KTG_KRP3.equals("-")) && (KTG_KRP4.equals("LF") || KTG_KRP4.equals("-"))
                    && (KTG_KRP5.equals("LF") || KTG_KRP5.equals("-")) && (KTG_KRP6.equals("LF") || KTG_KRP6.equals("-"))){

                if(KTG_KRP.equals("-") && KTG_KRP2.equals("-") && KTG_KRP3.equals("-") && KTG_KRP4.equals("-") && KTG_KRP5.equals("-") && KTG_KRP6.equals("-")){
                    KTG_KRPP = "-";
                    holder.krp_back.setBackground(a52Activity.getResources().getDrawable(R.drawable.null_style));
                } else {
                    KTG_KRPP = "LF";
                    holder.krp_back.setBackground(a52Activity.getResources().getDrawable(R.drawable.success_style));
                }
            } else {
                KTG_KRPP = "LS";
                holder.krp_back.setBackground(a52Activity.getResources().getDrawable(R.drawable.failed_style));
            }
            holder.krp_hasil.setText(KTG_KRPP);
        } catch (Exception e){
            Toast.makeText(a52Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        //KLF

        try {
            if((KTG_KMLL.equals("LF") || KTG_KMLL.equals("-")) && (KTG_KRPP.equals("LF") || KTG_KRPP.equals("-"))){

                if(KTG_KMLL.equals("-") && KTG_KRPP.equals("-")){
                    KTG_KLF = "Tidak Dinilai";
                } else {
                    KTG_KLF = "LF";
                }
            } else {
                KTG_KLF = "LS";
            }
        } catch (Exception e){
            Toast.makeText(a52Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            holder.rec_data.setText(currentItem.getREC());
        } catch (Exception e){
            Toast.makeText(a52Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            holder.FT1.setImageBitmap(BitmapFactory.decodeFile(currentItem.getDIR1()));
        } catch (Exception e){
            Toast.makeText(a52Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            holder.FT2.setImageBitmap(BitmapFactory.decodeFile(currentItem.getDIR2()));
        } catch (Exception e){
            Toast.makeText(a52Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        //FAB UPLOAD

        try {
            if(currentItem.getUPLOAD().equals("F")){
                a52Activity.FAB_UPLOAD.hide();
                a52Activity.UPLOAD.setImageResource(R.drawable.ic_file_upload_white_18dp);
                a52Activity.FAB_UPLOAD.setEnabled(true);
            } else {
                a52Activity.UPLOAD.setImageResource(R.drawable.ic_done_white_18dp);
                a52Activity.FAB_UPLOAD.setEnabled(false);
            }
        } catch (Exception e){
            Toast.makeText(a52Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        //Kirim ke Activity

        try {
            a52Activity.ID = currentItem.getID();
            a52Activity.KML_TXT = currentItem.getKML();
            a52Activity.KML2_TXT = currentItem.getKML2();
            a52Activity.KML3_TXT = currentItem.getKML3();
            a52Activity.KML4_TXT = currentItem.getKML4();
            a52Activity.KML5_TXT = currentItem.getKML5();
            a52Activity.KML6_TXT = currentItem.getKML6();
            a52Activity.KML_TXT11 = currentItem.getKML11();
            a52Activity.KML2_TXT22 = currentItem.getKML22();
            a52Activity.KML3_TXT33 = currentItem.getKML33();
            a52Activity.KML4_TXT44 = currentItem.getKML44();
            a52Activity.KML5_TXT55 = currentItem.getKML55();
            a52Activity.KML6_TXT66 = currentItem.getKML66();
            a52Activity.KML_IN = KML_IN;
            a52Activity.KML2_IN = KML2_IN;
            a52Activity.KML3_IN = KML3_IN;
            a52Activity.KML4_IN = KML4_IN;
            a52Activity.KML5_IN = KML5_IN;
            a52Activity.KML6_IN = KML6_IN;
            a52Activity.KRP_TXT = currentItem.getKRP();
            a52Activity.KRP2_TXT = currentItem.getKRP2();
            a52Activity.KRP3_TXT = currentItem.getKRP3();
            a52Activity.KRP4_TXT = currentItem.getKRP4();
            a52Activity.KRP5_TXT = currentItem.getKRP5();
            a52Activity.KRP6_TXT = currentItem.getKRP6();
            a52Activity.REC_TXT = currentItem.getREC();
            a52Activity.DIR1 = currentItem.getDIR1();
            a52Activity.DIR2 = currentItem.getDIR2();

            a52Activity.DEV_KML = DEV_KML;
            a52Activity.DEV_KML2 = DEV_KML2;
            a52Activity.DEV_KML3 = DEV_KML3;
            a52Activity.DEV_KML4 = DEV_KML4;
            a52Activity.DEV_KML5 = DEV_KML5;
            a52Activity.DEV_KML6 = DEV_KML6;
            a52Activity.DEV_KRP = DEV_KRP;
            a52Activity.DEV_KRP2 = DEV_KRP2;
            a52Activity.DEV_KRP3 = DEV_KRP3;
            a52Activity.DEV_KRP4 = DEV_KRP4;
            a52Activity.DEV_KRP5 = DEV_KRP5;
            a52Activity.DEV_KRP6 = DEV_KRP6;

            a52Activity.KTG_KMLL = KTG_KMLL;
            a52Activity.KTG_KRPP = KTG_KRPP;
            a52Activity.KTG_KLF = KTG_KLF;

            if(KTG_KLF.equals("LS")){
                a52Activity.klf.setText(KTG_KLF);
                a52Activity.klf.setBackground(a52Activity.getResources().getDrawable(R.drawable.failed_style));
                Animation animation = AnimationUtils.loadAnimation(a52Activity.getApplicationContext(), R.anim.recycle_bottom);
                a52Activity.klf.startAnimation(animation);
            } else if(KTG_KLF.equals("Tidak Dinilai")){
                a52Activity.klf.setText("Tidak Dinilai");
                a52Activity.klf.setBackground(a52Activity.getResources().getDrawable(R.drawable.null_style));
                Animation animation = AnimationUtils.loadAnimation(a52Activity.getApplicationContext(), R.anim.recycle_bottom);
                a52Activity.klf.startAnimation(animation);
            } else {
                a52Activity.klf.setText(KTG_KLF);
                a52Activity.klf.setBackground(a52Activity.getResources().getDrawable(R.drawable.success_style));
                Animation animation = AnimationUtils.loadAnimation(a52Activity.getApplicationContext(), R.anim.recycle_bottom);
                a52Activity.klf.startAnimation(animation);
            }
        } catch (Exception e){
            Toast.makeText(a52Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public int getItemCount() {
        return mdataList.size();
    }

}
