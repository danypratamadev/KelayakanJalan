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

public class A121_Adapter extends RecyclerView.Adapter<A121_Adapter.A121ViewHolder> {

    private A121Activity a121Activity;
    private List<A121_Class> mdataList;
    private double DEV_PBJ, DEV_JPH, DEV_JPM, DEV_LKJ, STD = 100;
    private String KTG_PBJ, KTG_JPH, KTG_JPM, KTG_JPD, KTG_LKJ, KTG_KLF;
    private int SLKJ_IN;
    private static DecimalFormat df = new DecimalFormat("0.0");

    public static class A121ViewHolder extends RecyclerView.ViewHolder {
        private TextView pbj_data, pbj_dev, pbj_hasil, jph_data, jph_dev, jpm_data, jpm_dev,
                jpd_hasil, slkj_data, lkj_data, lkj_dev, lkj_hasil, rec_data;
        private LinearLayout pbj_back, jpd_back, lkj_back;
        private ImageView FT1, FT2;

        public A121ViewHolder(View itemView) {
            super(itemView);

            pbj_data = (TextView) itemView.findViewById(R.id.pbj_data);
            pbj_dev = (TextView) itemView.findViewById(R.id.pbj_dev);
            pbj_hasil = (TextView) itemView.findViewById(R.id.pbj_hasil);
            jph_data = (TextView) itemView.findViewById(R.id.jph_data);
            jph_dev = (TextView) itemView.findViewById(R.id.jph_dev);
            jpm_data = (TextView) itemView.findViewById(R.id.jpm_data);
            jpm_dev = (TextView) itemView.findViewById(R.id.jpm_dev);
            jpd_hasil = (TextView) itemView.findViewById(R.id.jpd_hasil);
            slkj_data = (TextView) itemView.findViewById(R.id.slkj_data);
            lkj_data = (TextView) itemView.findViewById(R.id.lkj_data);
            lkj_dev = (TextView) itemView.findViewById(R.id.lkj_dev);
            lkj_hasil = (TextView) itemView.findViewById(R.id.lkj_hasil);
            rec_data = (TextView) itemView.findViewById(R.id.rec_data);

            FT1 = (ImageView) itemView.findViewById(R.id.FT1);
            FT2 = (ImageView) itemView.findViewById(R.id.FT2);

            pbj_back = (LinearLayout) itemView.findViewById(R.id.pbj_back);
            jpd_back = (LinearLayout) itemView.findViewById(R.id.jpd_back);
            lkj_back = (LinearLayout) itemView.findViewById(R.id.lkj_back);

        }
    }

    public A121_Adapter(A121Activity a121Activity, List<A121_Class> dataList) {
        this.a121Activity = a121Activity;
        this.mdataList = dataList;
    }

    @NonNull
    @Override
    public A121ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.a121_layout, parent, false);
        A121_Adapter.A121ViewHolder rsu = new A121_Adapter.A121ViewHolder(view);
        return rsu;
    }

    @Override
    public void onBindViewHolder(@NonNull A121ViewHolder holder, int position) {

        final A121_Class currentItem = mdataList.get(position);

        //Panjang Bagian Jalan yang Lurus

        try {
            if(currentItem.getPBJ() == -1){
                holder.pbj_data.setText("-");
                DEV_PBJ = -1;
                KTG_PBJ = "-";
                holder.pbj_dev.setText("-");
                holder.pbj_hasil.setText(KTG_PBJ);
                holder.pbj_back.setBackground(a121Activity.getResources().getDrawable(R.drawable.null_style));
            } else {
                holder.pbj_data.setText(String.valueOf(currentItem.getPBJ())+" m");
                if(a121Activity.FNG.equals("Arteri") && a121Activity.MJL.equals("Datar (D)")){
                    if(currentItem.getPBJ() <= 3000){
                        DEV_PBJ = 0;
                        KTG_PBJ = "LF";
                        holder.pbj_back.setBackground(a121Activity.getResources().getDrawable(R.drawable.success_style));
                    } else {
                        DEV_PBJ = (currentItem.getPBJ() - 3000)/3000;
                        DEV_PBJ = (DEV_PBJ * 100);
                        if(DEV_PBJ < 0){
                            DEV_PBJ = DEV_PBJ * -1;
                        }
                        if(DEV_PBJ > 100){
                            DEV_PBJ = 100;
                        }
                        DEV_PBJ = Double.parseDouble(df.format(DEV_PBJ).replace(",", "."));
                        KTG_PBJ = "LS";
                        holder.pbj_back.setBackground(a121Activity.getResources().getDrawable(R.drawable.failed_style));
                    }
                } else if(a121Activity.FNG.equals("Arteri") && a121Activity.MJL.equals("Bukit (B)")){
                    if(currentItem.getPBJ() <= 2500){
                        DEV_PBJ = 0;
                        KTG_PBJ = "LF";
                        holder.pbj_back.setBackground(a121Activity.getResources().getDrawable(R.drawable.success_style));
                    } else {
                        DEV_PBJ = (currentItem.getPBJ() - 2500)/2500;
                        DEV_PBJ = (DEV_PBJ * 100);
                        if(DEV_PBJ < 0){
                            DEV_PBJ = DEV_PBJ * -1;
                        }
                        if(DEV_PBJ > 100){
                            DEV_PBJ = 100;
                        }
                        DEV_PBJ = Double.parseDouble(df.format(DEV_PBJ).replace(",", "."));
                        KTG_PBJ = "LS";
                        holder.pbj_back.setBackground(a121Activity.getResources().getDrawable(R.drawable.failed_style));
                    }
                } else if(a121Activity.FNG.equals("Arteri") && a121Activity.MJL.equals("Gunung (G)")){
                    if(currentItem.getPBJ() <= 2000){
                        DEV_PBJ = 0;
                        KTG_PBJ = "LF";
                        holder.pbj_back.setBackground(a121Activity.getResources().getDrawable(R.drawable.success_style));
                    } else {
                        DEV_PBJ = (currentItem.getPBJ() - 2000)/2000;
                        DEV_PBJ = (DEV_PBJ * 100);
                        if(DEV_PBJ < 0){
                            DEV_PBJ = DEV_PBJ * -1;
                        }
                        if(DEV_PBJ > 100){
                            DEV_PBJ = 100;
                        }
                        DEV_PBJ = Double.parseDouble(df.format(DEV_PBJ).replace(",", "."));
                        KTG_PBJ = "LS";
                        holder.pbj_back.setBackground(a121Activity.getResources().getDrawable(R.drawable.failed_style));
                    }
                } else if(a121Activity.FNG.equals("Kolektor") && a121Activity.MJL.equals("Datar (D)")){
                    if(currentItem.getPBJ() <= 2000){
                        DEV_PBJ = 0;
                        KTG_PBJ = "LF";
                        holder.pbj_back.setBackground(a121Activity.getResources().getDrawable(R.drawable.success_style));
                    } else {
                        DEV_PBJ = (currentItem.getPBJ() - 2000)/2000;
                        DEV_PBJ = (DEV_PBJ * 100);
                        if(DEV_PBJ < 0){
                            DEV_PBJ = DEV_PBJ * -1;
                        }
                        if(DEV_PBJ > 100){
                            DEV_PBJ = 100;
                        }
                        DEV_PBJ = Double.parseDouble(df.format(DEV_PBJ));
                        KTG_PBJ = "LS";
                        holder.pbj_back.setBackground(a121Activity.getResources().getDrawable(R.drawable.failed_style));
                    }
                } else if(a121Activity.FNG.equals("Kolektor") && a121Activity.MJL.equals("Bukit (B)")){
                    if(currentItem.getPBJ() <= 1750){
                        DEV_PBJ = 0;
                        KTG_PBJ = "LF";
                        holder.pbj_back.setBackground(a121Activity.getResources().getDrawable(R.drawable.success_style));
                    } else {
                        DEV_PBJ = (currentItem.getPBJ() - 1750)/1750;
                        DEV_PBJ = (DEV_PBJ * 100);
                        if(DEV_PBJ < 0){
                            DEV_PBJ = DEV_PBJ * -1;
                        }
                        if(DEV_PBJ > 100){
                            DEV_PBJ = 100;
                        }
                        DEV_PBJ = Double.parseDouble(df.format(DEV_PBJ).replace(",", "."));
                        KTG_PBJ = "LS";
                        holder.pbj_back.setBackground(a121Activity.getResources().getDrawable(R.drawable.failed_style));
                    }
                } else {
                    if(currentItem.getPBJ() <= 1500){
                        DEV_PBJ = 0;
                        KTG_PBJ = "LF";
                        holder.pbj_back.setBackground(a121Activity.getResources().getDrawable(R.drawable.success_style));
                    } else {
                        DEV_PBJ = (currentItem.getPBJ() - 1500)/1500;
                        DEV_PBJ = (DEV_PBJ * 100);
                        if(DEV_PBJ < 0){
                            DEV_PBJ = DEV_PBJ * -1;
                        }
                        if(DEV_PBJ > 100){
                            DEV_PBJ = 100;
                        }
                        DEV_PBJ = Double.parseDouble(df.format(DEV_PBJ).replace(",", "."));
                        KTG_PBJ = "LS";
                        holder.pbj_back.setBackground(a121Activity.getResources().getDrawable(R.drawable.failed_style));
                    }
                }
                holder.pbj_dev.setText(String.valueOf(DEV_PBJ)+"%");
                holder.pbj_hasil.setText(KTG_PBJ);
            }
        } catch (Exception e){
            Toast.makeText(a121Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        //Jarak Pandang

        try {
            if(currentItem.getJPH() == -1){
                holder.jph_data.setText("-");
                holder.jph_dev.setText("-");
                DEV_JPH = -1;
                KTG_JPH = "-";
            } else {
                holder.jph_data.setText(String.valueOf(currentItem.getJPH())+" m");
                if(a121Activity.FNG.equals("Arteri")){
                    if(a121Activity.SJR.equals("Primer (Antar Kota)")){
                        if(currentItem.getSJPD().equals("Antar Kota")){
                            if(currentItem.getJPH() >= 75){
                                DEV_JPH = 0;
                                KTG_JPH = "LF";
                            } else {
                                DEV_JPH = (currentItem.getJPH() - 75)/75;
                                DEV_JPH = (DEV_JPH * 100);
                                if(DEV_JPH < 0){
                                    DEV_JPH = DEV_JPH * -1;
                                }
                                if(DEV_JPH > 100){
                                    DEV_JPH = 100;
                                }
                                DEV_JPH = Double.parseDouble(df.format(DEV_JPH).replace(",", "."));
                                KTG_JPH = "LS";
                            }
                        } else {
                            if(currentItem.getJPH() >= 75){
                                DEV_JPH = 0;
                                KTG_JPH = "LF";
                            } else {
                                DEV_JPH = (currentItem.getJPH() - 75)/75;
                                DEV_JPH = (DEV_JPH * 100);
                                if(DEV_JPH < 0){
                                    DEV_JPH = DEV_JPH * -1;
                                }
                                if(DEV_JPH > 100){
                                    DEV_JPH = 100;
                                }
                                DEV_JPH = Double.parseDouble(df.format(DEV_JPH).replace(",", "."));
                                KTG_JPH = "LS";
                            }
                        }
                    } else {
                        if(currentItem.getSJPD().equals("Antar Kota")){
                            if(currentItem.getJPH() >= 30){
                                DEV_JPH = 0;
                                KTG_JPH = "LF";
                            } else {
                                DEV_JPH = (currentItem.getJPH() - 30)/30;
                                DEV_JPH = (DEV_JPH * 100);
                                if(DEV_JPH < 0){
                                    DEV_JPH = DEV_JPH * -1;
                                }
                                if(DEV_JPH > 100){
                                    DEV_JPH = 100;
                                }
                                DEV_JPH = Double.parseDouble(df.format(DEV_JPH).replace(",", "."));
                                KTG_JPH = "LS";
                            }
                        } else {
                            if(currentItem.getJPH() >= 30){
                                DEV_JPH = 0;
                                KTG_JPH = "LF";
                            } else {
                                DEV_JPH = (currentItem.getJPH() - 30)/30;
                                DEV_JPH = (DEV_JPH * 100);
                                if(DEV_JPH < 0){
                                    DEV_JPH = DEV_JPH * -1;
                                }
                                if(DEV_JPH > 100){
                                    DEV_JPH = 100;
                                }
                                DEV_JPH = Double.parseDouble(df.format(DEV_JPH).replace(",", "."));
                                KTG_JPH = "LS";
                            }
                        }
                    }
                } else {
                    if(a121Activity.SJR.equals("Primer (Antar Kota)")){
                        if(currentItem.getSJPD().equals("Antar Kota")){
                            if(currentItem.getJPH() >= 40){
                                DEV_JPH = 0;
                                KTG_JPH = "LF";
                            } else {
                                DEV_JPH = (currentItem.getJPH() - 40)/40;
                                DEV_JPH = (DEV_JPH * 100);
                                if(DEV_JPH < 0){
                                    DEV_JPH = DEV_JPH * -1;
                                }
                                if(DEV_JPH > 100){
                                    DEV_JPH = 100;
                                }
                                DEV_JPH = Double.parseDouble(df.format(DEV_JPH).replace(",", "."));
                                KTG_JPH = "LS";
                            }
                        } else {
                            if(currentItem.getJPH() >= 40){
                                DEV_JPH = 0;
                                KTG_JPH = "LF";
                            } else {
                                DEV_JPH = (currentItem.getJPH() - 40)/40;
                                DEV_JPH = (DEV_JPH * 100);
                                if(DEV_JPH < 0){
                                    DEV_JPH = DEV_JPH * -1;
                                }
                                if(DEV_JPH > 100){
                                    DEV_JPH = 100;
                                }
                                DEV_JPH = Double.parseDouble(df.format(DEV_JPH).replace(",", "."));
                                KTG_JPH = "LS";
                            }
                        }
                    } else {
                        if(currentItem.getSJPD().equals("Antar Kota")){
                            if(currentItem.getJPH() >= 20){
                                DEV_JPH = 0;
                                KTG_JPH = "LF";
                            } else {
                                DEV_JPH = (currentItem.getJPH() - 20)/20;
                                DEV_JPH = (DEV_JPH * 100);
                                if(DEV_JPH < 0){
                                    DEV_JPH = DEV_JPH * -1;
                                }
                                if(DEV_JPH > 100){
                                    DEV_JPH = 100;
                                }
                                DEV_JPH = Double.parseDouble(df.format(DEV_JPH).replace(",", "."));
                                KTG_JPH = "LS";
                            }
                        } else {
                            if(currentItem.getJPH() >= 20){
                                DEV_JPH = 0;
                                KTG_JPH = "LF";
                            } else {
                                DEV_JPH = (currentItem.getJPH() - 20)/20;
                                DEV_JPH = (DEV_JPH * 100);
                                if(DEV_JPH < 0){
                                    DEV_JPH = DEV_JPH * -1;
                                }
                                if(DEV_JPH > 100){
                                    DEV_JPH = 100;
                                }
                                DEV_JPH = Double.parseDouble(df.format(DEV_JPH).replace(",", "."));
                                KTG_JPH = "LS";
                            }
                        }
                    }
                }
                holder.jph_dev.setText(String.valueOf(DEV_JPH)+"%");
            }
        } catch (Exception e){
            Toast.makeText(a121Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            if(currentItem.getJPM() == -1){
                holder.jpm_data.setText("-");
                holder.jpm_dev.setText("-");
                DEV_JPM = -1;
                KTG_JPM = "-";
            } else {
                holder.jpm_data.setText(String.valueOf(currentItem.getJPM())+" m");
                if(a121Activity.FNG.equals("Arteri")){
                    if(a121Activity.SJR.equals("Primer (Antar Kota)")){
                        if(currentItem.getSJPD().equals("Antar Kota")){
                            if(currentItem.getJPM() >= 250){
                                DEV_JPM = 0;
                                KTG_JPM = "LF";
                            } else {
                                DEV_JPM = (currentItem.getJPM() - 250)/250;
                                DEV_JPM = (DEV_JPM * 100);
                                if(DEV_JPM < 0){
                                    DEV_JPM = DEV_JPM * -1;
                                }
                                if(DEV_JPM > 100){
                                    DEV_JPM = 100;
                                }
                                DEV_JPM = Double.parseDouble(df.format(DEV_JPM).replace(",", "."));
                                KTG_JPM = "LS";
                            }
                        } else {
                            if(currentItem.getJPM() >= 350){
                                DEV_JPM = 0;
                                KTG_JPM = "LF";
                            } else {
                                DEV_JPM = (currentItem.getJPM() - 350)/350;
                                DEV_JPM = (DEV_JPM * 100);
                                if(DEV_JPM < 0){
                                    DEV_JPM = DEV_JPM * -1;
                                }
                                if(DEV_JPM > 100){
                                    DEV_JPM = 100;
                                }
                                DEV_JPM = Double.parseDouble(df.format(DEV_JPM).replace(",", "."));
                                KTG_JPM = "LS";
                            }
                        }
                    } else {
                        if(currentItem.getSJPD().equals("Antar Kota")){
                            if(currentItem.getJPM() >= 100){
                                DEV_JPM = 0;
                                KTG_JPM = "LF";
                            } else {
                                DEV_JPM = (currentItem.getJPM() - 100)/100;
                                DEV_JPM = (DEV_JPM * 100);
                                if(DEV_JPM < 0){
                                    DEV_JPM = DEV_JPM * -1;
                                }
                                if(DEV_JPM > 100){
                                    DEV_JPM = 100;
                                }
                                DEV_JPM = Double.parseDouble(df.format(DEV_JPM).replace(",", "."));
                                KTG_JPM = "LS";
                            }
                        } else {
                            if(currentItem.getJPM() >= 150){
                                DEV_JPM = 0;
                                KTG_JPM = "LF";
                            } else {
                                DEV_JPM = (currentItem.getJPM() - 150)/150;
                                DEV_JPM = (DEV_JPM * 100);
                                if(DEV_JPM < 0){
                                    DEV_JPM = DEV_JPM * -1;
                                }
                                if(DEV_JPM > 100){
                                    DEV_JPM = 100;
                                }
                                DEV_JPM = Double.parseDouble(df.format(DEV_JPM).replace(",", "."));
                                KTG_JPM = "LS";
                            }
                        }
                    }
                } else {
                    if(a121Activity.SJR.equals("Primer (Antar Kota)")){
                        if(currentItem.getSJPD().equals("Antar Kota")){
                            if(currentItem.getJPM() >= 150){
                                DEV_JPM = 0;
                                KTG_JPM = "LF";
                            } else {
                                DEV_JPM = (currentItem.getJPM() - 150)/150;
                                DEV_JPM = (DEV_JPM * 100);
                                if(DEV_JPM < 0){
                                    DEV_JPM = DEV_JPM * -1;
                                }
                                if(DEV_JPM > 100){
                                    DEV_JPM = 100;
                                }
                                DEV_JPM = Double.parseDouble(df.format(DEV_JPM).replace(",", "."));
                                KTG_JPM = "LS";
                            }
                        } else {
                            if(currentItem.getJPM() >= 200){
                                DEV_JPM = 0;
                                KTG_JPM = "LF";
                            } else {
                                DEV_JPM = (currentItem.getJPM() - 200)/200;
                                DEV_JPM = (DEV_JPM * 100);
                                if(DEV_JPM < 0){
                                    DEV_JPM = DEV_JPM * -1;
                                }
                                if(DEV_JPM > 100){
                                    DEV_JPM = 100;
                                }
                                DEV_JPM = Double.parseDouble(df.format(DEV_JPM).replace(",", "."));
                                KTG_JPM = "LS";
                            }
                        }
                    } else {
                        if(currentItem.getSJPD().equals("Antar Kota")){
                            if(currentItem.getJPM() >= 70){
                                DEV_JPM = 0;
                                KTG_JPM = "LF";
                            } else {
                                DEV_JPM = (currentItem.getJPM() - 70)/70;
                                DEV_JPM = (DEV_JPM * 100);
                                if(DEV_JPM < 0){
                                    DEV_JPM = DEV_JPM * -1;
                                }
                                if(DEV_JPM > 100){
                                    DEV_JPM = 100;
                                }
                                DEV_JPM = Double.parseDouble(df.format(DEV_JPM).replace(",", "."));
                                KTG_JPM = "LS";
                            }
                        } else {
                            if(currentItem.getJPM() >= 100){
                                DEV_JPM = 0;
                                KTG_JPM = "LF";
                            } else {
                                DEV_JPM = (currentItem.getJPM() - 100)/100;
                                DEV_JPM = (DEV_JPM * 100);
                                if(DEV_JPM < 0){
                                    DEV_JPM = DEV_JPM * -1;
                                }
                                if(DEV_JPM > 100){
                                    DEV_JPM = 100;
                                }
                                DEV_JPM = Double.parseDouble(df.format(DEV_JPM).replace(",", "."));
                                KTG_JPM = "LS";
                            }
                        }
                    }
                }
                holder.jpm_dev.setText(String.valueOf(DEV_JPM)+"%");
            }
        } catch (Exception e){
            Toast.makeText(a121Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            if(KTG_JPH.equals("LF") || KTG_JPH.equals("-") && (KTG_JPM.equals("LF") || KTG_JPM.equals("-"))){

                if(KTG_JPH.equals("-") && KTG_JPM.equals("-")) {
                    KTG_JPD = "-";
                    holder.jpd_back.setBackground(a121Activity.getResources().getDrawable(R.drawable.null_style));
                } else {
                    KTG_JPD = "LF";
                    holder.jpd_back.setBackground(a121Activity.getResources().getDrawable(R.drawable.success_style));
                }
            } else {
                KTG_JPD = "LS";
                holder.jpd_back.setBackground(a121Activity.getResources().getDrawable(R.drawable.failed_style));
            }

            if(KTG_JPD.equals("-")){
                holder.jpd_hasil.setText("-");
            } else {
                holder.jpd_hasil.setText(KTG_JPD);
            }
        } catch (Exception e){
            Toast.makeText(a121Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        //Lingkungan Jalan

        try {
            if(currentItem.getLKJ() == -1){
                holder.slkj_data.setText("-");
                DEV_LKJ = -1;
                KTG_LKJ = "-";
                holder.lkj_data.setText("-");
                holder.lkj_dev.setText("-");
                holder.lkj_hasil.setText(KTG_LKJ);
                holder.lkj_back.setBackground(a121Activity.getResources().getDrawable(R.drawable.null_style));
            } else {
                if(currentItem.getSLKJ().equals("Permukiman")){
                    SLKJ_IN = 1;
                } else if(currentItem.getSLKJ().equals("Komersial")) {
                    SLKJ_IN = 2;
                }  else {
                    SLKJ_IN = 3;
                }
                DEV_LKJ = STD - currentItem.getLKJ();
                if(DEV_LKJ == 0){
                    KTG_LKJ = "LF";
                    holder.lkj_back.setBackground(a121Activity.getResources().getDrawable(R.drawable.success_style));
                } else {
                    KTG_LKJ = "LS";
                    holder.lkj_back.setBackground(a121Activity.getResources().getDrawable(R.drawable.failed_style));
                }
                holder.slkj_data.setText("("+String.valueOf(SLKJ_IN)+") "+currentItem.getSLKJ());
                holder.lkj_data.setText(String.valueOf(currentItem.getLKJ())+"%");
                holder.lkj_dev.setText(String.valueOf(DEV_LKJ)+"%");
                holder.lkj_hasil.setText(KTG_LKJ);
            }
        } catch (Exception e){
            Toast.makeText(a121Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        //KLF

        try {
            if((KTG_PBJ.equals("LF") || KTG_PBJ.equals("-")) && (KTG_JPD.equals("LF") || KTG_JPD.equals("-"))
                    && (KTG_LKJ.equals("LF") || KTG_LKJ.equals("-"))) {

                if(KTG_PBJ.equals("-") && KTG_JPD.equals("-") && KTG_LKJ.equals("-")){
                    KTG_KLF = "Tidak Dinilai";
                } else {
                    KTG_KLF = "LF";
                }
            } else {
                KTG_KLF = "LS";
            }
        } catch (Exception e){
            Toast.makeText(a121Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            holder.rec_data.setText(currentItem.getREC());
        } catch (Exception e){
            Toast.makeText(a121Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            holder.FT1.setImageBitmap(BitmapFactory.decodeFile(currentItem.getDIR1()));
        } catch (Exception e){
            Toast.makeText(a121Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            holder.FT2.setImageBitmap(BitmapFactory.decodeFile(currentItem.getDIR2()));
        } catch (Exception e){
            Toast.makeText(a121Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        //FAB UPLOAD

        try {
            if(currentItem.getUPLOAD().equals("F")){
                a121Activity.FAB_UPLOAD.hide();
                a121Activity.UPLOAD.setImageResource(R.drawable.ic_file_upload_white_18dp);
                a121Activity.FAB_UPLOAD.setEnabled(true);
            } else {
                a121Activity.UPLOAD.setImageResource(R.drawable.ic_done_white_18dp);
                a121Activity.FAB_UPLOAD.setEnabled(false);
            }
        } catch (Exception e){
            Toast.makeText(a121Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        //Kirim ke Activity

        try {
            a121Activity.ID = currentItem.getID();
            a121Activity.PBJ_TXT = currentItem.getPBJ();
            a121Activity.JPH_TXT = currentItem.getJPH();
            a121Activity.JPM_TXT = currentItem.getJPM();
            a121Activity.LKJ_TXT = currentItem.getLKJ();
            a121Activity.SLKJ_IN = SLKJ_IN;
            a121Activity.SLKJ_TXT = currentItem.getSLKJ();
            a121Activity.SJKT_TXT = currentItem.getSJPD();
            a121Activity.REC_TXT = currentItem.getREC();
            a121Activity.DIR1 = currentItem.getDIR1();
            a121Activity.DIR2 = currentItem.getDIR2();

            a121Activity.DEV_PBJ = DEV_PBJ;
            a121Activity.DEV_JPH = DEV_JPH;
            a121Activity.DEV_JPM = DEV_JPM;
            a121Activity.DEV_LKJ = DEV_LKJ;

            a121Activity.KTG_PBJ = KTG_PBJ;
            a121Activity.KTG_JPD = KTG_JPD;
            a121Activity.KTG_LKJ = KTG_LKJ;
            a121Activity.KTG_KLF = KTG_KLF;

            if(KTG_KLF.equals("LS")){
                a121Activity.klf.setText(KTG_KLF);
                a121Activity.klf.setBackground(a121Activity.getResources().getDrawable(R.drawable.failed_style));
                Animation animation = AnimationUtils.loadAnimation(a121Activity.getApplicationContext(), R.anim.recycle_bottom);
                a121Activity.klf.startAnimation(animation);
            } else if(KTG_KLF.equals("Tidak Dinilai")){
                a121Activity.klf.setText("Tidak Dinilai");
                a121Activity.klf.setBackground(a121Activity.getResources().getDrawable(R.drawable.null_style));
                Animation animation = AnimationUtils.loadAnimation(a121Activity.getApplicationContext(), R.anim.recycle_bottom);
                a121Activity.klf.startAnimation(animation);
            } else {
                a121Activity.klf.setText(KTG_KLF);
                a121Activity.klf.setBackground(a121Activity.getResources().getDrawable(R.drawable.success_style));
                Animation animation = AnimationUtils.loadAnimation(a121Activity.getApplicationContext(), R.anim.recycle_bottom);
                a121Activity.klf.startAnimation(animation);
            }
        } catch (Exception e){
            Toast.makeText(a121Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public int getItemCount() {
        return mdataList.size();
    }

}
