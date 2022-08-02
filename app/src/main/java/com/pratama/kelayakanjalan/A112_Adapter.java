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

public class A112_Adapter extends RecyclerView.Adapter<A112_Adapter.A112ViewHolder> {

    private A112Activity a112Activity;
    private List<A112_Class> mdataList;
    private double DEV_LBB, DEV_KLB, DEV_PKB, DEV_PMB, DEV_KMB;
    private String KTG_LBB, KTG_KLB, KTG_PKB, KTG_PMB, KTG_KMB, KTG_KLF;
    private double STD = 100;
    private static DecimalFormat df = new DecimalFormat("0.0");

    public static class A112ViewHolder extends RecyclerView.ViewHolder {
        private TextView lbb_data, lbb_dev, lbb_hasil, klb_data, klb_dev, klb_hasil, pkb_data, pkb_dev, pkb_hasil, pmb_data, pmb_dev, pmb_hasil,
                kmb_data, kmb_dev, kmb_hasil, rec_data;
        private LinearLayout lbb_back, klb_back, pkb_back, pmb_back, kmb_back;
        private ImageView FT1, FT2;

        public A112ViewHolder(View itemView) {
            super(itemView);

            lbb_data = (TextView) itemView.findViewById(R.id.lbb_data);
            lbb_dev = (TextView) itemView.findViewById(R.id.lbb_dev);
            lbb_hasil = (TextView) itemView.findViewById(R.id.lbb_hasil);
            klb_data = (TextView) itemView.findViewById(R.id.klb_data);
            klb_dev = (TextView) itemView.findViewById(R.id.klb_dev);
            klb_hasil = (TextView) itemView.findViewById(R.id.klb_hasil);
            pkb_data = (TextView) itemView.findViewById(R.id.pkb_data);
            pkb_dev = (TextView) itemView.findViewById(R.id.pkb_dev);
            pkb_hasil = (TextView) itemView.findViewById(R.id.pkb_hasil);
            pmb_data = (TextView) itemView.findViewById(R.id.pmb_data);
            pmb_dev = (TextView) itemView.findViewById(R.id.pmb_dev);
            pmb_hasil = (TextView) itemView.findViewById(R.id.pmb_hasil);
            kmb_data = (TextView) itemView.findViewById(R.id.kmb_data);
            kmb_dev = (TextView) itemView.findViewById(R.id.kmb_dev);
            kmb_hasil = (TextView) itemView.findViewById(R.id.kmb_hasil);
            rec_data = (TextView) itemView.findViewById(R.id.rec_data);

            FT1 = (ImageView) itemView.findViewById(R.id.FT1);
            FT2 = (ImageView) itemView.findViewById(R.id.FT2);

            lbb_back = (LinearLayout) itemView.findViewById(R.id.lbb_back);
            klb_back = (LinearLayout) itemView.findViewById(R.id.klb_back);
            pkb_back = (LinearLayout) itemView.findViewById(R.id.pkb_back);
            pmb_back = (LinearLayout) itemView.findViewById(R.id.pmb_back);
            kmb_back = (LinearLayout) itemView.findViewById(R.id.kmb_back);

        }
    }

    public A112_Adapter(A112Activity a112Activity, List<A112_Class> dataList) {
        this.a112Activity = a112Activity;
        this.mdataList = dataList;
    }

    @NonNull
    @Override
    public A112_Adapter.A112ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.a112_layout, parent, false);
        A112_Adapter.A112ViewHolder rsu = new A112_Adapter.A112ViewHolder(view);
        return rsu;
    }

    @Override
    public void onBindViewHolder(@NonNull A112_Adapter.A112ViewHolder holder, int position) {

        final A112_Class currentItem = mdataList.get(position);

        //Lebar Bahu

        try {
            if(currentItem.getLBB() == -1){
                holder.lbb_data.setText("-");
                DEV_LBB = -1;
                KTG_LBB = "-";
                holder.lbb_dev.setText("-");
                holder.lbb_hasil.setText(KTG_LBB);
                holder.lbb_back.setBackground(a112Activity.getResources().getDrawable(R.drawable.null_style));
            } else {
                holder.lbb_data.setText(String.valueOf(currentItem.getLBB())+" m");
                if(a112Activity.SJR.equals("Primer (Antar Kota)")){
                    if(a112Activity.KPR.equals("Jalan Bebas Hambatan (JBH)") && a112Activity.MJL.equals("Datar (D)")){
                        if(currentItem.getLBB() >= 3.5){
                            DEV_LBB = 0;
                            KTG_LBB = "LF";
                            holder.lbb_dev.setText(String.valueOf(DEV_LBB)+"%");
                            holder.lbb_hasil.setText(KTG_LBB);
                            holder.lbb_back.setBackground(a112Activity.getResources().getDrawable(R.drawable.success_style));
                        } else {
                            DEV_LBB = (currentItem.getLBB() - 3.5)/3.5;
                            DEV_LBB = (DEV_LBB * 100);
                            if(DEV_LBB < 0){
                                DEV_LBB = DEV_LBB * -1;
                            }
                            if(DEV_LBB > 100){
                                DEV_LBB = 100;
                            }
                            DEV_LBB = Double.parseDouble(df.format(DEV_LBB).replace(",", "."));
                            KTG_LBB = "LS";
                            holder.lbb_dev.setText(String.valueOf(DEV_LBB)+"%");
                            holder.lbb_hasil.setText(KTG_LBB);
                            holder.lbb_back.setBackground(a112Activity.getResources().getDrawable(R.drawable.failed_style));
                        }
                    } else if(a112Activity.KPR.equals("Jalan Bebas Hambatan (JBH)") && a112Activity.MJL.equals("Bukit (B)")){
                        if(currentItem.getLBB() >= 2.5){
                            DEV_LBB = 0;
                            KTG_LBB = "LF";
                            holder.lbb_dev.setText(String.valueOf(DEV_LBB)+"%");
                            holder.lbb_hasil.setText(KTG_LBB);
                            holder.lbb_back.setBackground(a112Activity.getResources().getDrawable(R.drawable.success_style));
                        } else {
                            DEV_LBB = (currentItem.getLBB() - 2.5)/2.5;
                            DEV_LBB = (DEV_LBB * 100);
                            if(DEV_LBB < 0){
                                DEV_LBB = DEV_LBB * -1;
                            }
                            if(DEV_LBB > 100){
                                DEV_LBB = 100;
                            }
                            DEV_LBB = Double.parseDouble(df.format(DEV_LBB).replace(",", "."));
                            KTG_LBB = "LS";
                            holder.lbb_dev.setText(String.valueOf(DEV_LBB)+"%");
                            holder.lbb_hasil.setText(KTG_LBB);
                            holder.lbb_back.setBackground(a112Activity.getResources().getDrawable(R.drawable.failed_style));
                        }
                    } else if(a112Activity.KPR.equals("Jalan Bebas Hambatan (JBH)") && a112Activity.MJL.equals("Gunung (G)")){
                        if(currentItem.getLBB() >= 2){
                            DEV_LBB = 0;
                            KTG_LBB = "LF";
                            holder.lbb_dev.setText(String.valueOf(DEV_LBB)+"%");
                            holder.lbb_hasil.setText(KTG_LBB);
                            holder.lbb_back.setBackground(a112Activity.getResources().getDrawable(R.drawable.success_style));
                        } else {
                            DEV_LBB = (currentItem.getLBB() - 2)/2;
                            DEV_LBB = (DEV_LBB * 100);
                            if(DEV_LBB < 0){
                                DEV_LBB = DEV_LBB * -1;
                            }
                            if(DEV_LBB > 100){
                                DEV_LBB = 100;
                            }
                            DEV_LBB = Double.parseDouble(df.format(DEV_LBB).replace(",", "."));
                            KTG_LBB = "LS";
                            holder.lbb_dev.setText(String.valueOf(DEV_LBB)+"%");
                            holder.lbb_hasil.setText(KTG_LBB);
                            holder.lbb_back.setBackground(a112Activity.getResources().getDrawable(R.drawable.failed_style));
                        }
                    } else if(a112Activity.KPR.equals("Jalan Raya (JR)") && a112Activity.MJL.equals("Datar (D)")){
                        if(currentItem.getLBB() >= 2){
                            DEV_LBB = 0;
                            KTG_LBB = "LF";
                            holder.lbb_dev.setText(String.valueOf(DEV_LBB)+"%");
                            holder.lbb_hasil.setText(KTG_LBB);
                            holder.lbb_back.setBackground(a112Activity.getResources().getDrawable(R.drawable.success_style));
                        } else {
                            DEV_LBB = (currentItem.getLBB() - 2)/2;
                            DEV_LBB = (DEV_LBB * 100);
                            if(DEV_LBB < 0){
                                DEV_LBB = DEV_LBB * -1;
                            }
                            if(DEV_LBB > 100){
                                DEV_LBB = 100;
                            }
                            DEV_LBB = Double.parseDouble(df.format(DEV_LBB).replace(",", "."));
                            KTG_LBB = "LS";
                            holder.lbb_dev.setText(String.valueOf(DEV_LBB)+"%");
                            holder.lbb_hasil.setText(KTG_LBB);
                            holder.lbb_back.setBackground(a112Activity.getResources().getDrawable(R.drawable.failed_style));
                        }
                    } else if(a112Activity.KPR.equals("Jalan Raya (JR)") && a112Activity.MJL.equals("Bukit (B)")){
                        if(currentItem.getLBB() >= 1.5){
                            DEV_LBB = 0;
                            KTG_LBB = "LF";
                            holder.lbb_dev.setText(String.valueOf(DEV_LBB)+"%");
                            holder.lbb_hasil.setText(KTG_LBB);
                            holder.lbb_back.setBackground(a112Activity.getResources().getDrawable(R.drawable.success_style));
                        } else {
                            DEV_LBB = (currentItem.getLBB() - 1.5)/1.5;
                            DEV_LBB = (DEV_LBB * 100);
                            if(DEV_LBB < 0){
                                DEV_LBB = DEV_LBB * -1;
                            }
                            if(DEV_LBB > 100){
                                DEV_LBB = 100;
                            }
                            DEV_LBB = Double.parseDouble(df.format(DEV_LBB).replace(",", "."));
                            KTG_LBB = "LS";
                            holder.lbb_dev.setText(String.valueOf(DEV_LBB)+"%");
                            holder.lbb_hasil.setText(KTG_LBB);
                            holder.lbb_back.setBackground(a112Activity.getResources().getDrawable(R.drawable.failed_style));
                        }
                    } else if(a112Activity.KPR.equals("Jalan Raya (JR)") && a112Activity.MJL.equals("Gunung (G)")){
                        if(currentItem.getLBB() >= 1){
                            DEV_LBB = 0;
                            KTG_LBB = "LF";
                            holder.lbb_dev.setText(String.valueOf(DEV_LBB)+"%");
                            holder.lbb_hasil.setText(KTG_LBB);
                            holder.lbb_back.setBackground(a112Activity.getResources().getDrawable(R.drawable.success_style));
                        } else {
                            DEV_LBB = (currentItem.getLBB() - 1)/1;
                            DEV_LBB = (DEV_LBB * 100);
                            if(DEV_LBB < 0){
                                DEV_LBB = DEV_LBB * -1;
                            }
                            if(DEV_LBB > 100){
                                DEV_LBB = 100;
                            }
                            DEV_LBB = Double.parseDouble(df.format(DEV_LBB).replace(",", "."));
                            KTG_LBB = "LS";
                            holder.lbb_dev.setText(String.valueOf(DEV_LBB)+"%");
                            holder.lbb_hasil.setText(KTG_LBB);
                            holder.lbb_back.setBackground(a112Activity.getResources().getDrawable(R.drawable.failed_style));
                        }
                    } else if(a112Activity.KPR.equals("Jalan Sedang (JS)") && a112Activity.MJL.equals("Datar (D)")){
                        if(currentItem.getLBB() >= 1){
                            DEV_LBB = 0;
                            KTG_LBB = "LF";
                            holder.lbb_dev.setText(String.valueOf(DEV_LBB)+"%");
                            holder.lbb_hasil.setText(KTG_LBB);
                            holder.lbb_back.setBackground(a112Activity.getResources().getDrawable(R.drawable.success_style));
                        } else {
                            DEV_LBB = (currentItem.getLBB() - 1)/1;
                            DEV_LBB = (DEV_LBB * 100);
                            if(DEV_LBB < 0){
                                DEV_LBB = DEV_LBB * -1;
                            }
                            if(DEV_LBB > 100){
                                DEV_LBB = 100;
                            }
                            DEV_LBB = Double.parseDouble(df.format(DEV_LBB).replace(",", "."));
                            KTG_LBB = "LS";
                            holder.lbb_dev.setText(String.valueOf(DEV_LBB)+"%");
                            holder.lbb_hasil.setText(KTG_LBB);
                            holder.lbb_back.setBackground(a112Activity.getResources().getDrawable(R.drawable.failed_style));
                        }
                    } else if(a112Activity.KPR.equals("Jalan Sedang (JS)") && a112Activity.MJL.equals("Bukit (B)")){
                        if(currentItem.getLBB() >= 1){
                            DEV_LBB = 0;
                            KTG_LBB = "LF";
                            holder.lbb_dev.setText(String.valueOf(DEV_LBB)+"%");
                            holder.lbb_hasil.setText(KTG_LBB);
                            holder.lbb_back.setBackground(a112Activity.getResources().getDrawable(R.drawable.success_style));
                        } else {
                            DEV_LBB = (currentItem.getLBB() - 1)/1;
                            DEV_LBB = (DEV_LBB * 100);
                            if(DEV_LBB < 0){
                                DEV_LBB = DEV_LBB * -1;
                            }
                            if(DEV_LBB > 100){
                                DEV_LBB = 100;
                            }
                            DEV_LBB = Double.parseDouble(df.format(DEV_LBB).replace(",", "."));
                            KTG_LBB = "LS";
                            holder.lbb_dev.setText(String.valueOf(DEV_LBB)+"%");
                            holder.lbb_hasil.setText(KTG_LBB);
                            holder.lbb_back.setBackground(a112Activity.getResources().getDrawable(R.drawable.failed_style));
                        }
                    } else if(a112Activity.KPR.equals("Jalan Sedang (JS)") && a112Activity.MJL.equals("Gunung (G)")){
                        if(currentItem.getLBB() >= 0.5){
                            DEV_LBB = 0;
                            KTG_LBB = "LF";
                            holder.lbb_dev.setText(String.valueOf(DEV_LBB)+"%");
                            holder.lbb_hasil.setText(KTG_LBB);
                            holder.lbb_back.setBackground(a112Activity.getResources().getDrawable(R.drawable.success_style));
                        } else {
                            DEV_LBB = (currentItem.getLBB() - 0.5)/0.5;
                            DEV_LBB = (DEV_LBB * 100);
                            if(DEV_LBB < 0){
                                DEV_LBB = DEV_LBB * -1;
                            }
                            if(DEV_LBB > 100){
                                DEV_LBB = 100;
                            }
                            DEV_LBB = Double.parseDouble(df.format(DEV_LBB).replace(",", "."));
                            KTG_LBB = "LS";
                            holder.lbb_dev.setText(String.valueOf(DEV_LBB)+"%");
                            holder.lbb_hasil.setText(KTG_LBB);
                            holder.lbb_back.setBackground(a112Activity.getResources().getDrawable(R.drawable.failed_style));
                        }
                    } else if(a112Activity.KPR.equals("Jalan Kecil (JK)") && a112Activity.MJL.equals("Datar (D)")){
                        if(currentItem.getLBB() >= 1){
                            DEV_LBB = 0;
                            KTG_LBB = "LF";
                            holder.lbb_dev.setText(String.valueOf(DEV_LBB)+"%");
                            holder.lbb_hasil.setText(KTG_LBB);
                            holder.lbb_back.setBackground(a112Activity.getResources().getDrawable(R.drawable.success_style));
                        } else {
                            DEV_LBB = (currentItem.getLBB() - 1)/1;
                            DEV_LBB = (DEV_LBB * 100);
                            if(DEV_LBB < 0){
                                DEV_LBB = DEV_LBB * -1;
                            }
                            if(DEV_LBB > 100){
                                DEV_LBB = 100;
                            }
                            DEV_LBB = Double.parseDouble(df.format(DEV_LBB).replace(",", "."));
                            KTG_LBB = "LS";
                            holder.lbb_dev.setText(String.valueOf(DEV_LBB)+"%");
                            holder.lbb_hasil.setText(KTG_LBB);
                            holder.lbb_back.setBackground(a112Activity.getResources().getDrawable(R.drawable.failed_style));
                        }
                    } else if(a112Activity.KPR.equals("Jalan Kecil (JK)") && a112Activity.MJL.equals("Bukit (B)")){
                        if(currentItem.getLBB() >= 1){
                            DEV_LBB = 0;
                            KTG_LBB = "LF";
                            holder.lbb_dev.setText(String.valueOf(DEV_LBB)+"%");
                            holder.lbb_hasil.setText(KTG_LBB);
                            holder.lbb_back.setBackground(a112Activity.getResources().getDrawable(R.drawable.success_style));
                        } else {
                            DEV_LBB = (currentItem.getLBB() - 1)/1;
                            DEV_LBB = (DEV_LBB * 100);
                            if(DEV_LBB < 0){
                                DEV_LBB = DEV_LBB * -1;
                            }
                            if(DEV_LBB > 100){
                                DEV_LBB = 100;
                            }
                            DEV_LBB = Double.parseDouble(df.format(DEV_LBB).replace(",", "."));
                            KTG_LBB = "LS";
                            holder.lbb_dev.setText(String.valueOf(DEV_LBB)+"%");
                            holder.lbb_hasil.setText(KTG_LBB);
                            holder.lbb_back.setBackground(a112Activity.getResources().getDrawable(R.drawable.failed_style));
                        }
                    } else if(a112Activity.KPR.equals("Jalan Kecil (JK)") && a112Activity.MJL.equals("Gunung (G)")){
                        if(currentItem.getLBB() >= 0.5){
                            DEV_LBB = 0;
                            KTG_LBB = "LF";
                            holder.lbb_dev.setText(String.valueOf(DEV_LBB)+"%");
                            holder.lbb_hasil.setText(KTG_LBB);
                            holder.lbb_back.setBackground(a112Activity.getResources().getDrawable(R.drawable.success_style));
                        } else {
                            DEV_LBB = (currentItem.getLBB() - 0.5)/0.5;
                            DEV_LBB = (DEV_LBB * 100);
                            if(DEV_LBB < 0){
                                DEV_LBB = DEV_LBB * -1;
                            }
                            if(DEV_LBB > 100){
                                DEV_LBB = 100;
                            }
                            DEV_LBB = Double.parseDouble(df.format(DEV_LBB).replace(",", "."));
                            KTG_LBB = "LS";
                            holder.lbb_dev.setText(String.valueOf(DEV_LBB)+"%");
                            holder.lbb_hasil.setText(KTG_LBB);
                            holder.lbb_back.setBackground(a112Activity.getResources().getDrawable(R.drawable.failed_style));
                        }
                    }

                } else {

                    if(a112Activity.KPR.equals("Jalan Bebas Hambatan (JBH)")){
                        if(currentItem.getLBB() >= 2.5){
                            DEV_LBB = 0;
                            KTG_LBB = "LF";
                            holder.lbb_dev.setText(String.valueOf(DEV_LBB)+"%");
                            holder.lbb_hasil.setText(KTG_LBB);
                            holder.lbb_back.setBackground(a112Activity.getResources().getDrawable(R.drawable.success_style));
                        } else {
                            DEV_LBB = (currentItem.getLBB() - 2.5)/2.5;
                            DEV_LBB = (DEV_LBB * 100);
                            if(DEV_LBB < 0){
                                DEV_LBB = DEV_LBB * -1;
                            }
                            if(DEV_LBB > 100){
                                DEV_LBB = 100;
                            }
                            DEV_LBB = Double.parseDouble(df.format(DEV_LBB).replace(",", "."));
                            KTG_LBB = "LS";
                            holder.lbb_dev.setText(String.valueOf(DEV_LBB)+"%");
                            holder.lbb_hasil.setText(KTG_LBB);
                            holder.lbb_back.setBackground(a112Activity.getResources().getDrawable(R.drawable.failed_style));
                        }
                    } else if(a112Activity.KPR.equals("Jalan Raya (JR)")){
                        if(currentItem.getLBB() >= 2){
                            DEV_LBB = 0;
                            KTG_LBB = "LF";
                            holder.lbb_dev.setText(String.valueOf(DEV_LBB)+"%");
                            holder.lbb_hasil.setText(KTG_LBB);
                            holder.lbb_back.setBackground(a112Activity.getResources().getDrawable(R.drawable.success_style));
                        } else {
                            DEV_LBB = (currentItem.getLBB() - 2)/2;
                            DEV_LBB = (DEV_LBB * 100);
                            if(DEV_LBB < 0){
                                DEV_LBB = DEV_LBB * -1;
                            }
                            if(DEV_LBB > 100){
                                DEV_LBB = 100;
                            }
                            DEV_LBB = Double.parseDouble(df.format(DEV_LBB).replace(",", "."));
                            KTG_LBB = "LS";
                            holder.lbb_dev.setText(String.valueOf(DEV_LBB)+"%");
                            holder.lbb_hasil.setText(KTG_LBB);
                            holder.lbb_back.setBackground(a112Activity.getResources().getDrawable(R.drawable.failed_style));
                        }
                    } else if(a112Activity.KPR.equals("Jalan Sedang (JS)")){
                        if(currentItem.getLBB() >= 1.5){
                            DEV_LBB = 0;
                            KTG_LBB = "LF";
                            holder.lbb_dev.setText(String.valueOf(DEV_LBB)+"%");
                            holder.lbb_hasil.setText(KTG_LBB);
                            holder.lbb_back.setBackground(a112Activity.getResources().getDrawable(R.drawable.success_style));
                        } else {
                            DEV_LBB = (currentItem.getLBB() - 1.5)/1.5;
                            DEV_LBB = (DEV_LBB * 100);
                            if(DEV_LBB < 0){
                                DEV_LBB = DEV_LBB * -1;
                            }
                            if(DEV_LBB > 100){
                                DEV_LBB = 100;
                            }
                            DEV_LBB = Double.parseDouble(df.format(DEV_LBB).replace(",", "."));
                            KTG_LBB = "LS";
                            holder.lbb_dev.setText(String.valueOf(DEV_LBB)+"%");
                            holder.lbb_hasil.setText(KTG_LBB);
                            holder.lbb_back.setBackground(a112Activity.getResources().getDrawable(R.drawable.failed_style));
                        }
                    } else if(a112Activity.KPR.equals("Jalan Kecil (JK)")){
                        if(currentItem.getLBB() >= 1){
                            DEV_LBB = 0;
                            KTG_LBB = "LF";
                            holder.lbb_dev.setText(String.valueOf(DEV_LBB)+"%");
                            holder.lbb_hasil.setText(KTG_LBB);
                            holder.lbb_back.setBackground(a112Activity.getResources().getDrawable(R.drawable.success_style));
                        } else {
                            DEV_LBB = (currentItem.getLBB() - 1)/1;
                            DEV_LBB = (DEV_LBB * 100);
                            if(DEV_LBB < 0){
                                DEV_LBB = DEV_LBB * -1;
                            }
                            if(DEV_LBB > 100){
                                DEV_LBB = 100;
                            }
                            DEV_LBB = Double.parseDouble(df.format(DEV_LBB).replace(",", "."));
                            KTG_LBB = "LS";
                            holder.lbb_dev.setText(String.valueOf(DEV_LBB)+"%");
                            holder.lbb_hasil.setText(KTG_LBB);
                            holder.lbb_back.setBackground(a112Activity.getResources().getDrawable(R.drawable.failed_style));
                        }
                    }

                }

            }
        } catch (Exception e){
            Toast.makeText(a112Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        //Keseragaman Lebar Bahu

        try {
            if(currentItem.getKLB() == -1){
                holder.klb_data.setText("-");
                DEV_KLB = -1;
                KTG_KLB = "-";
                holder.klb_dev.setText("-");
                holder.klb_hasil.setText(KTG_KLB);
                holder.klb_back.setBackground(a112Activity.getResources().getDrawable(R.drawable.null_style));
            } else {
                holder.klb_data.setText(String.valueOf(currentItem.getKLB())+"%");
                DEV_KLB = STD - currentItem.getKLB();
                if(DEV_KLB == 0){
                    KTG_KLB = "LF";
                    holder.klb_back.setBackground(a112Activity.getResources().getDrawable(R.drawable.success_style));
                } else {
                    KTG_KLB = "LS";
                    holder.klb_back.setBackground(a112Activity.getResources().getDrawable(R.drawable.failed_style));
                }
                holder.klb_dev.setText(String.valueOf(DEV_KLB)+"%");
                holder.klb_hasil.setText(KTG_KLB);
            }
        } catch (Exception e){
            Toast.makeText(a112Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        //Perkerasan Bahu

        try {
            if(currentItem.getPKB() == -1){
                holder.pkb_data.setText("-");
                DEV_PKB = -1;
                KTG_PKB = "-";
                holder.pkb_dev.setText("-");
                holder.pkb_hasil.setText(KTG_PKB);
                holder.pkb_back.setBackground(a112Activity.getResources().getDrawable(R.drawable.null_style));
            } else {
                holder.pkb_data.setText(String.valueOf(currentItem.getPKB())+"%");
                DEV_PKB = STD - currentItem.getPKB();
                if(DEV_PKB == 0){
                    KTG_PKB = "LF";
                    holder.pkb_back.setBackground(a112Activity.getResources().getDrawable(R.drawable.success_style));
                } else {
                    KTG_PKB = "LS";
                    holder.pkb_back.setBackground(a112Activity.getResources().getDrawable(R.drawable.failed_style));
                }
                holder.pkb_dev.setText(String.valueOf(DEV_PKB)+"%");
                holder.pkb_hasil.setText(KTG_PKB);
            }
        } catch (Exception e){
            Toast.makeText(a112Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        //Posisi Muka Bahu

        try {
            if(currentItem.getPMB() == -1){
                holder.pmb_data.setText("-");
                DEV_PMB = -1;
                KTG_PMB = "-";
                holder.pmb_dev.setText("-");
                holder.pmb_hasil.setText(KTG_PMB);
                holder.pmb_back.setBackground(a112Activity.getResources().getDrawable(R.drawable.null_style));
            } else {
                holder.pmb_data.setText(String.valueOf(currentItem.getPMB())+"%");
                DEV_PMB = STD - currentItem.getPMB();
                if(DEV_PMB == 0){
                    KTG_PMB = "LF";
                    holder.pmb_back.setBackground(a112Activity.getResources().getDrawable(R.drawable.success_style));
                } else {
                    KTG_PMB = "LS";
                    holder.pmb_back.setBackground(a112Activity.getResources().getDrawable(R.drawable.failed_style));
                }
                holder.pmb_dev.setText(String.valueOf(DEV_PMB));
                holder.pmb_hasil.setText(KTG_PMB);
            }
        } catch (Exception e){
            Toast.makeText(a112Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        //Kemiringan Melintang Bahu

        try {
            if(currentItem.getKMB() == -1){
                holder.kmb_data.setText("-");
                DEV_KMB = -1;
                KTG_KMB = "-";
                holder.kmb_dev.setText("-");
                holder.kmb_hasil.setText(KTG_KMB);
                holder.kmb_back.setBackground(a112Activity.getResources().getDrawable(R.drawable.null_style));
            } else {
                holder.kmb_data.setText(String.valueOf(currentItem.getKMB())+"%");
                if(a112Activity.KPR.equals("Jalan Bebas Hambatan (JBH)")){
                    if(currentItem.getKMB() >= 3 && currentItem.getKMB() <= 5){
                        DEV_KMB = 0;
                        KTG_KMB = "LF";
                        holder.kmb_back.setBackground(a112Activity.getResources().getDrawable(R.drawable.success_style));
                    } else{
                        if(currentItem.getKMB() < 3){
                            DEV_KMB = (currentItem.getKMB() - 3)/3;
                        } else {
                            DEV_KMB = (currentItem.getKMB() - 5)/5;
                        }
                        DEV_KMB = (DEV_KMB * 100);
                        if(DEV_KMB < 0){
                            DEV_KMB = DEV_KMB * -1;
                        }
                        if(DEV_KMB > 100){
                            DEV_KMB = 100;
                        }
                        DEV_KMB = Double.parseDouble(df.format(DEV_KMB).replace(",", "."));
                        KTG_KMB = "LS";
                        holder.kmb_back.setBackground(a112Activity.getResources().getDrawable(R.drawable.failed_style));
                    }
                } else {
                    if(currentItem.getKMB() >= 3 && currentItem.getKMB() <= 6){
                        DEV_KMB = 0;
                        KTG_KMB = "LF";
                        holder.kmb_back.setBackground(a112Activity.getResources().getDrawable(R.drawable.success_style));
                    } else{
                        if(currentItem.getKMB() < 3){
                            DEV_KMB = (currentItem.getKMB() - 3)/3;
                        } else {
                            DEV_KMB = (currentItem.getKMB() - 6)/6;
                        }
                        DEV_KMB = (DEV_KMB * 100);
                        if(DEV_KMB < 0){
                            DEV_KMB = DEV_KMB * -1;
                        }
                        if(DEV_KMB > 100){
                            DEV_KMB = 100;
                        }
                        DEV_KMB = Double.parseDouble(df.format(DEV_KMB).replace(",", "."));
                        KTG_KMB = "LS";
                        holder.kmb_back.setBackground(a112Activity.getResources().getDrawable(R.drawable.failed_style));
                    }
                }
                holder.kmb_dev.setText(String.valueOf(DEV_KMB));
                holder.kmb_hasil.setText(KTG_KMB);
            }
        } catch (Exception e){
            Toast.makeText(a112Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        //KLF

        try {
            if((KTG_LBB.equals("LF") || KTG_LBB.equals("-")) && (KTG_KLB.equals("LF") || KTG_KLB.equals("-"))
                    && (KTG_PKB.equals("LF") || KTG_PKB.equals("-")) && (KTG_PMB.equals("LF") || KTG_PMB.equals("-"))
                    && (KTG_KMB.equals("LF") || KTG_KMB.equals("-"))){

                if(KTG_LBB.equals("-") && KTG_KLB.equals("-") && KTG_PKB.equals("-") && KTG_PMB.equals("-") && KTG_KMB.equals("-")) {

                    KTG_KLF = "Tidak Dinilai";

                } else {

                    KTG_KLF = "LF";

                }

            }  else {

                KTG_KLF = "LS";

            }
        } catch (Exception e){
            Toast.makeText(a112Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            holder.rec_data.setText(currentItem.getREC());
        } catch (Exception e){
            Toast.makeText(a112Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            holder.FT1.setImageBitmap(BitmapFactory.decodeFile(currentItem.getDIR1()));
        } catch (Exception e){
            Toast.makeText(a112Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            holder.FT2.setImageBitmap(BitmapFactory.decodeFile(currentItem.getDIR2()));
        } catch (Exception e){
            Toast.makeText(a112Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        //FAB UPLOAD

        try {
            if(currentItem.getUPLOAD().equals("F")){
                a112Activity.FAB_UPLOAD.hide();
                a112Activity.UPLOAD.setImageResource(R.drawable.ic_file_upload_white_18dp);
                a112Activity.FAB_UPLOAD.setEnabled(true);
            } else {
                a112Activity.UPLOAD.setImageResource(R.drawable.ic_done_white_18dp);
                a112Activity.FAB_UPLOAD.setEnabled(false);
            }
        } catch (Exception e){
            Toast.makeText(a112Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        //Kirim ke Activity

        try {
            a112Activity.ID = currentItem.getID();
            a112Activity.LBB_TXT = currentItem.getLBB();
            a112Activity.KLB_TXT = currentItem.getKLB();
            a112Activity.PKB_TXT = currentItem.getPKB();
            a112Activity.PMB_TXT = currentItem.getPMB();
            a112Activity.KMB_TXT = currentItem.getKMB();
            a112Activity.REC_TXT = currentItem.getREC();
            a112Activity.DIR1 = currentItem.getDIR1();
            a112Activity.DIR2 = currentItem.getDIR2();

            a112Activity.DEV_LBB = DEV_LBB;
            a112Activity.DEV_KLB = DEV_KLB;
            a112Activity.DEV_PKB = DEV_PKB;
            a112Activity.DEV_PMB = DEV_PMB;
            a112Activity.DEV_KMB = DEV_KMB;

            a112Activity.KTG_LBB = KTG_LBB;
            a112Activity.KTG_KLB = KTG_KLB;
            a112Activity.KTG_PKB = KTG_PKB;
            a112Activity.KTG_PMB = KTG_PMB;
            a112Activity.KTG_KMB = KTG_KMB;
            a112Activity.KTG_KLF = KTG_KLF;

            if(KTG_KLF.equals("LS")){
                a112Activity.klf.setText(KTG_KLF);
                a112Activity.klf.setBackground(a112Activity.getResources().getDrawable(R.drawable.failed_style));
                Animation animation = AnimationUtils.loadAnimation(a112Activity.getApplicationContext(), R.anim.recycle_bottom);
                a112Activity.klf.startAnimation(animation);
            } else if(KTG_KLF.equals("Tidak Dinilai")){
                a112Activity.klf.setText("Tidak Dinilai");
                a112Activity.klf.setBackground(a112Activity.getResources().getDrawable(R.drawable.null_style));
                Animation animation = AnimationUtils.loadAnimation(a112Activity.getApplicationContext(), R.anim.recycle_bottom);
                a112Activity.klf.startAnimation(animation);
            } else {
                a112Activity.klf.setText(KTG_KLF);
                a112Activity.klf.setBackground(a112Activity.getResources().getDrawable(R.drawable.success_style));
                Animation animation = AnimationUtils.loadAnimation(a112Activity.getApplicationContext(), R.anim.recycle_bottom);
                a112Activity.klf.startAnimation(animation);
            }
        } catch (Exception e){
            Toast.makeText(a112Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public int getItemCount() {
        return mdataList.size();
    }
}
