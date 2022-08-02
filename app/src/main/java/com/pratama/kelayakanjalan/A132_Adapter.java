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

public class A132_Adapter extends RecyclerView.Adapter<A132_Adapter.A132ViewHolder> {

    private A132Activity a132Activity;
    private List<A132_Class> mdataList;
    private double DEV_SKKB, DEV_LBL, DEV_PJA, DEV_PJS, DEV_PJL, DEV_PJP, DEV_PJS2, DEV_TMK;
    private String KTG_SKKB, KTG_LBL, KTG_PJA, KTG_PJS, KTG_PJL, KTG_PJP, KTG_PJS2, KTG_LPL, KTG_TMK, KTG_KLF;
    private int SKKB_IN;
    private static DecimalFormat df = new DecimalFormat("0.0");

    public class A132ViewHolder extends RecyclerView.ViewHolder {

        private TextView skkb_data, skkb_dev, skkb_hasil, lbl_data, lbl_dev, pja_data, pja_dev, pjs_data, pjs_dev,
                pjl_data, pjl_dev, pjp_data, pjp_dev, pjs2_data, pjs2_dev, lpl_hasil, tmk_data, tmk_dev, tmk_hasil, rec_data;
        private LinearLayout skkb_back, lpl_back, tmk_back;
        private ImageView FT1, FT2;

        public A132ViewHolder(@NonNull View itemView) {
            super(itemView);

            skkb_data = (TextView) itemView.findViewById(R.id.skkb_data);
            skkb_dev = (TextView) itemView.findViewById(R.id.skkb_dev);
            skkb_hasil = (TextView) itemView.findViewById(R.id.skkb_hasil);

            lbl_data = (TextView) itemView.findViewById(R.id.lbl_data);
            lbl_dev = (TextView) itemView.findViewById(R.id.lbl_dev);
            pja_data = (TextView) itemView.findViewById(R.id.pja_data);
            pja_dev = (TextView) itemView.findViewById(R.id.pja_dev);
            pjs_data = (TextView) itemView.findViewById(R.id.pjs_data);
            pjs_dev = (TextView) itemView.findViewById(R.id.pjs_dev);
            pjl_data = (TextView) itemView.findViewById(R.id.pjl_data);
            pjl_dev = (TextView) itemView.findViewById(R.id.pjl_dev);
            pjp_data = (TextView) itemView.findViewById(R.id.pjp_data);
            pjp_dev = (TextView) itemView.findViewById(R.id.pjp_dev);
            pjs2_data = (TextView) itemView.findViewById(R.id.pjs2_data);
            pjs2_dev = (TextView) itemView.findViewById(R.id.pjs2_dev);
            lpl_hasil = (TextView) itemView.findViewById(R.id.lpl_hasil);

            tmk_data = (TextView) itemView.findViewById(R.id.tmk_data);
            tmk_dev = (TextView) itemView.findViewById(R.id.tmk_dev);
            tmk_hasil = (TextView) itemView.findViewById(R.id.tmk_hasil);

            rec_data = (TextView) itemView.findViewById(R.id.rec_data);

            FT1 = (ImageView) itemView.findViewById(R.id.FT1);
            FT2 = (ImageView) itemView.findViewById(R.id.FT2);

            skkb_back = (LinearLayout) itemView.findViewById(R.id.skkb_back);
            lpl_back = (LinearLayout) itemView.findViewById(R.id.lpl_back);
            tmk_back = (LinearLayout) itemView.findViewById(R.id.tmk_back);

        }
    }

    public A132_Adapter(A132Activity a132Activity, List<A132_Class> mdataList) {
        this.a132Activity = a132Activity;
        this.mdataList = mdataList;
    }

    @NonNull
    @Override
    public A132_Adapter.A132ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.a132_layout, parent, false);
        A132_Adapter.A132ViewHolder a132 = new A132_Adapter.A132ViewHolder(view);
        return a132;
    }

    @Override
    public void onBindViewHolder(@NonNull A132_Adapter.A132ViewHolder holder, int position) {

        final A132_Class currentItem = mdataList.get(position);

        //Keperluan Keberadaannya

        try {
            if(currentItem.getSKKB().equals("Ada")){
                DEV_SKKB = 0;
                KTG_SKKB = "LF";
                SKKB_IN = 1;
                holder.skkb_back.setBackground(a132Activity.getResources().getDrawable(R.drawable.success_style));
                holder.skkb_data.setText(String.valueOf(SKKB_IN));
                holder.skkb_dev.setText(String.valueOf(DEV_SKKB)+"%");
            } else if(currentItem.getSKKB().equals("Tidak Ada")){
                DEV_SKKB = 100;
                KTG_SKKB = "LS";
                SKKB_IN = 2;
                holder.skkb_back.setBackground(a132Activity.getResources().getDrawable(R.drawable.failed_style));
                holder.skkb_data.setText(String.valueOf(SKKB_IN));
                holder.skkb_dev.setText(String.valueOf(DEV_SKKB)+"%");
            } else {
                DEV_SKKB = -1;
                KTG_SKKB = "-";
                SKKB_IN = 3;
                holder.skkb_back.setBackground(a132Activity.getResources().getDrawable(R.drawable.null_style));
                holder.skkb_data.setText(String.valueOf(SKKB_IN));
                holder.skkb_dev.setText("-");
            }
            holder.skkb_hasil.setText(KTG_SKKB);
        } catch (Exception e){
            Toast.makeText(a132Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        //Lebar dan Panjang lajur

        try {
            if(currentItem.getLBL() == -1){
                DEV_LBL = -1;
                KTG_LBL = "-";
                holder.lbl_data.setText("-");
                holder.lbl_dev.setText("-");
            } else {
                if(a132Activity.KPR.equals("Jalan Bebas Hambatan (JBH)")){
                    if(currentItem.getLBL() >= 3.5){
                        DEV_LBL = 0;
                        KTG_LBL = "LF";
                    } else {
                        DEV_LBL = (currentItem.getLBL() - 3.5)/3.5;
                        DEV_LBL = (DEV_LBL * 100);
                        if(DEV_LBL < 0){
                            DEV_LBL = DEV_LBL * -1;
                        }
                        if(DEV_LBL > 100){
                            DEV_LBL = 100;
                        }
                        DEV_LBL = Double.parseDouble(df.format(DEV_LBL).replace(",", "."));
                        KTG_LBL = "LS";
                    }
                } else if(a132Activity.KPR.equals("Jalan Raya (JR)")){
                    if(currentItem.getLBL() >= 3.5){
                        DEV_LBL = 0;
                        KTG_LBL = "LF";
                    } else {
                        DEV_LBL = (currentItem.getLBL() - 3.5)/3.5;
                        DEV_LBL = (DEV_LBL * 100);
                        if(DEV_LBL < 0){
                            DEV_LBL = DEV_LBL * -1;
                        }
                        if(DEV_LBL > 100){
                            DEV_LBL = 100;
                        }
                        DEV_LBL = Double.parseDouble(df.format(DEV_LBL).replace(",", "."));
                        KTG_LBL = "LS";
                    }
                }  else if(a132Activity.KPR.equals("Jalan Sedang (JS)")){
                    if(currentItem.getLBL() >= 3.5){
                        DEV_LBL = 0;
                        KTG_LBL = "LF";
                    } else {
                        DEV_LBL = (currentItem.getLBL() - 3.5)/3.5;
                        DEV_LBL = (DEV_LBL * 100);
                        if(DEV_LBL < 0){
                            DEV_LBL = DEV_LBL * -1;
                        }
                        if(DEV_LBL > 100){
                            DEV_LBL = 100;
                        }
                        DEV_LBL = Double.parseDouble(df.format(DEV_LBL).replace(",", "."));
                        KTG_LBL = "LS";
                    }
                }  else {
                    if(currentItem.getLBL() >= 2.75){
                        DEV_LBL = 0;
                        KTG_LBL = "LF";
                    } else {
                        DEV_LBL = (currentItem.getLBL() - 2.75)/2.75;
                        DEV_LBL = (DEV_LBL * 100);
                        if(DEV_LBL < 0){
                            DEV_LBL = DEV_LBL * -1;
                        }
                        if(DEV_LBL > 100){
                            DEV_LBL = 100;
                        }
                        DEV_LBL = Double.parseDouble(df.format(DEV_LBL).replace(",", "."));
                        KTG_LBL = "LS";
                    }
                }
                holder.lbl_data.setText(String.valueOf(currentItem.getLBL())+" m");
                holder.lbl_dev.setText(String.valueOf(DEV_LBL)+"%");
            }
        } catch (Exception e){
            Toast.makeText(a132Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            if(currentItem.getPJA() == -1){
                DEV_PJA = -1;
                KTG_PJA = "-";
                holder.pja_data.setText("-");
                holder.pja_dev.setText("-");
            } else {
                if(currentItem.getPJA() >= 30){
                    DEV_PJA = 0;
                    KTG_PJA = "LF";
                } else {
                    DEV_PJA = (currentItem.getPJA() - 30)/30;
                    DEV_PJA = (DEV_PJA * 100);
                    if(DEV_PJA < 0){
                        DEV_PJA = DEV_PJA * -1;
                    }
                    if(DEV_PJA > 100){
                        DEV_PJA = 100;
                    }
                    DEV_PJA = Double.parseDouble(df.format(DEV_PJA).replace(",", "."));
                    KTG_PJA = "LS";
                }
                holder.pja_data.setText(String.valueOf(currentItem.getPJA())+" m");
                holder.pja_dev.setText(String.valueOf(DEV_PJA)+"%");
            }
        } catch (Exception e){
            Toast.makeText(a132Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            if(currentItem.getPJS() == -1){
                DEV_PJS = -1;
                KTG_PJS = "-";
                holder.pjs_data.setText("-");
                holder.pjs_dev.setText("-");
            } else {
                if(currentItem.getPJS() >= 45){
                    DEV_PJS = 0;
                    KTG_PJS = "LF";
                } else {
                    DEV_PJS = (currentItem.getPJS() - 45)/45;
                    DEV_PJS = (DEV_PJS * 100);
                    if(DEV_PJS < 0){
                        DEV_PJS = DEV_PJS * -1;
                    }
                    if(DEV_PJS > 100){
                        DEV_PJS = 100;
                    }
                    DEV_PJS = Double.parseDouble(df.format(DEV_PJS).replace(",", "."));
                    KTG_PJS = "LS";
                }
                holder.pjs_data.setText(String.valueOf(currentItem.getPJS())+" m");
                holder.pjs_dev.setText(String.valueOf(DEV_PJS)+"%");
            }
        } catch (Exception e){
            Toast.makeText(a132Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            if(currentItem.getPJL() == -1){
                DEV_PJL = -1;
                KTG_PJL = "-";
                holder.pjl_data.setText("-");
                holder.pjl_dev.setText("-");
            } else {
                if(currentItem.getPJL() >= 200){
                    DEV_PJL = 0;
                    KTG_PJL = "LF";
                } else {
                    DEV_PJL = (currentItem.getPJL() - 200)/200;
                    DEV_PJL = (DEV_PJL * 100);
                    if(DEV_PJL < 0){
                        DEV_PJL = DEV_PJL * -1;
                    }
                    if(DEV_PJL > 100){
                        DEV_PJL = 100;
                    }
                    DEV_PJL = Double.parseDouble(df.format(DEV_PJL).replace(",", "."));
                    KTG_PJL = "LS";
                }
                holder.pjl_data.setText(String.valueOf(currentItem.getPJL())+" m");
                holder.pjl_dev.setText(String.valueOf(DEV_PJL)+"%");
            }
        } catch (Exception e){
            Toast.makeText(a132Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            if(currentItem.getPJP() == -1){
                DEV_PJP = -1;
                KTG_PJP = "-";
                holder.pjp_data.setText("-");
                holder.pjp_dev.setText("-");
            } else {
                if(currentItem.getPJP() >= 50){
                    DEV_PJP = 0;
                    KTG_PJP = "LF";
                } else {
                    DEV_PJP = (currentItem.getPJP() - 50)/50;
                    DEV_PJP = (DEV_PJP * 100);
                    if(DEV_PJP < 0){
                        DEV_PJP = DEV_PJP * -1;
                    }
                    if(DEV_PJP > 100){
                        DEV_PJP = 100;
                    }
                    DEV_PJP = Double.parseDouble(df.format(DEV_PJP).replace(",", "."));
                    KTG_PJP = "LS";
                }
                holder.pjp_data.setText(String.valueOf(currentItem.getPJP())+" m");
                holder.pjp_dev.setText(String.valueOf(DEV_PJP)+"%");
            }
        } catch (Exception e){
            Toast.makeText(a132Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            if(currentItem.getPJS2() == -1){
                DEV_PJS2 = -1;
                KTG_PJS2 = "-";
                holder.pjs2_data.setText("-");
                holder.pjs2_dev.setText("-");
            } else {
                if(currentItem.getPJS2() >= 45){
                    DEV_PJS2 = 0;
                    KTG_PJS2 = "LF";
                } else {
                    DEV_PJS2 = (currentItem.getPJS2() - 45)/45;
                    DEV_PJS2 = (DEV_PJS2 * 100);
                    if(DEV_PJS2 < 0){
                        DEV_PJS2 = DEV_PJS2 * -1;
                    }
                    if(DEV_PJS2 > 100){
                        DEV_PJS2 = 100;
                    }
                    DEV_PJS2 = Double.parseDouble(df.format(DEV_PJS2).replace(",", "."));
                    KTG_PJS2 = "LS";
                }
                holder.pjs2_data.setText(String.valueOf(currentItem.getPJS2())+" m");
                holder.pjs2_dev.setText(String.valueOf(DEV_PJS2)+"%");
            }
        } catch (Exception e){
            Toast.makeText(a132Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            if((KTG_LBL.equals("LF") || KTG_LBL.equals("-")) && (KTG_PJA.equals("LF") || KTG_PJA.equals("-"))
                    && (KTG_PJS.equals("LF") || KTG_PJS.equals("-")) && (KTG_PJL.equals("LF") || KTG_PJL.equals("-"))
                    && (KTG_PJP.equals("LF") || KTG_PJP.equals("-")) && (KTG_PJS2.equals("LF") || KTG_PJS2.equals("-"))){

                if(KTG_LBL.equals("-") && KTG_PJA.equals("-") && KTG_PJS.equals("-") && KTG_PJL.equals("-") &&
                        KTG_PJP.equals("-") && KTG_PJS2.equals("-")){
                    KTG_LPL = "-";
                    holder.lpl_back.setBackground(a132Activity.getResources().getDrawable(R.drawable.null_style));
                } else {
                    KTG_LPL = "LF";
                    holder.lpl_back.setBackground(a132Activity.getResources().getDrawable(R.drawable.success_style));
                }

            } else {
                KTG_LPL = "LS";
                holder.lpl_back.setBackground(a132Activity.getResources().getDrawable(R.drawable.failed_style));
            }
            holder.lpl_hasil.setText(KTG_LPL);
        } catch (Exception e){
            Toast.makeText(a132Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        //Taper Masuk dan Keluar

        try {
            if(currentItem.getTMK() == -1){
                DEV_TMK = -1;
                KTG_TMK = "-";
                holder.tmk_data.setText("-");
                holder.tmk_dev.setText("-");
                holder.tmk_hasil.setText(KTG_TMK);
                holder.tmk_back.setBackground(a132Activity.getResources().getDrawable(R.drawable.null_style));
            } else {
                if(currentItem.getTMK() >= 45){
                    DEV_TMK = 0;
                    KTG_TMK = "LF";
                    holder.tmk_back.setBackground(a132Activity.getResources().getDrawable(R.drawable.success_style));
                } else {
                    DEV_TMK = (currentItem.getTMK() - 45)/45;
                    DEV_TMK = (DEV_TMK * 100);
                    if(DEV_TMK < 0){
                        DEV_TMK = DEV_TMK * -1;
                    }
                    if(DEV_TMK > 100){
                        DEV_TMK = 100;
                    }
                    DEV_TMK = Double.parseDouble(df.format(DEV_TMK).replace(",", "."));
                    KTG_TMK = "LS";
                    holder.tmk_back.setBackground(a132Activity.getResources().getDrawable(R.drawable.failed_style));
                }
                holder.tmk_data.setText(String.valueOf(currentItem.getTMK())+" m");
                holder.tmk_dev.setText(String.valueOf(DEV_TMK)+"%");
                holder.tmk_hasil.setText(KTG_TMK);
            }
        } catch (Exception e){
            Toast.makeText(a132Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        //KLF

        try {
            if((KTG_SKKB.equals("LF") || KTG_SKKB.equals("-")) && (KTG_LPL.equals("LF") || KTG_LPL.equals("-"))
                    && (KTG_TMK.equals("LF") || KTG_TMK.equals("-"))){

                if(KTG_SKKB.equals("-") && KTG_LPL.equals("-") && KTG_TMK.equals("-")){
                    KTG_KLF = "Tidak Dinilai";
                } else {
                    KTG_KLF = "LF";
                }

            } else {
                KTG_KLF = "LS";
            }
        } catch (Exception e){
            Toast.makeText(a132Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            holder.rec_data.setText(currentItem.getREC());
        } catch (Exception e){
            Toast.makeText(a132Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            holder.FT1.setImageBitmap(BitmapFactory.decodeFile(currentItem.getDIR1()));
        } catch (Exception e){
            Toast.makeText(a132Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            holder.FT2.setImageBitmap(BitmapFactory.decodeFile(currentItem.getDIR2()));
        } catch (Exception e){
            Toast.makeText(a132Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        //FAB UPLOAD

        try {
            if(currentItem.getUPLOAD().equals("F")){
                a132Activity.FAB_UPLOAD.hide();
                a132Activity.UPLOAD.setImageResource(R.drawable.ic_file_upload_white_18dp);
                a132Activity.FAB_UPLOAD.setEnabled(true);
            } else {
                a132Activity.UPLOAD.setImageResource(R.drawable.ic_done_white_18dp);
                a132Activity.FAB_UPLOAD.setEnabled(false);
            }
        } catch (Exception e){
            Toast.makeText(a132Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        //Kirim ke Activity

        try {
            a132Activity.ID = currentItem.getID();
            a132Activity.SKKB_IN = SKKB_IN;
            a132Activity.SKKB_TXT = currentItem.getSKKB();
            a132Activity.LBL_TXT = currentItem.getLBL();
            a132Activity.PJA_TXT = currentItem.getPJA();
            a132Activity.PJS_TXT = currentItem.getPJS();
            a132Activity.PJL_TXT = currentItem.getPJL();
            a132Activity.PJP_TXT = currentItem.getPJP();
            a132Activity.PJS2_TXT = currentItem.getPJS2();
            a132Activity.TMK_TXT = currentItem.getTMK();
            a132Activity.REC_TXT = currentItem.getREC();
            a132Activity.DIR1 = currentItem.getDIR1();
            a132Activity.DIR2 = currentItem.getDIR2();

            a132Activity.DEV_SKKB = DEV_SKKB;
            a132Activity.DEV_LBL = DEV_LBL;
            a132Activity.DEV_PJA = DEV_PJA;
            a132Activity.DEV_PJS = DEV_PJS;
            a132Activity.DEV_PJL = DEV_PJL;
            a132Activity.DEV_PJP = DEV_PJP;
            a132Activity.DEV_PJS2 = DEV_PJS2;
            a132Activity.DEV_TMK = DEV_TMK;

            a132Activity.KTG_SKKB = KTG_SKKB;
            a132Activity.KTG_LPL = KTG_LPL;
            a132Activity.KTG_TMK = KTG_TMK;
            a132Activity.KTG_KLF = KTG_KLF;

            if(KTG_KLF.equals("LS")){
                a132Activity.klf.setText(KTG_KLF);
                a132Activity.klf.setBackground(a132Activity.getResources().getDrawable(R.drawable.failed_style));
                Animation animation = AnimationUtils.loadAnimation(a132Activity.getApplicationContext(), R.anim.recycle_bottom);
                a132Activity.klf.startAnimation(animation);
            } else if(KTG_KLF.equals("Tidak Dinilai")){
                a132Activity.klf.setText("Tidak Dinilai");
                a132Activity.klf.setBackground(a132Activity.getResources().getDrawable(R.drawable.null_style));
                Animation animation = AnimationUtils.loadAnimation(a132Activity.getApplicationContext(), R.anim.recycle_bottom);
                a132Activity.klf.startAnimation(animation);
            } else {
                a132Activity.klf.setText(KTG_KLF);
                a132Activity.klf.setBackground(a132Activity.getResources().getDrawable(R.drawable.success_style));
                Animation animation = AnimationUtils.loadAnimation(a132Activity.getApplicationContext(), R.anim.recycle_bottom);
                a132Activity.klf.startAnimation(animation);
            }
        } catch (Exception e){
            Toast.makeText(a132Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public int getItemCount() {
        return mdataList.size();
    }

}
