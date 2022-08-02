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

public class A41_Adapter extends RecyclerView.Adapter<A41_Adapter.A41ViewHolder> {

    private A41Activity a41Activity;
    private List<A41_Class> mdataList;
    private double DEV_LBT, DEV_LBT2, DEV_LBT3, DEV_PFR, DEV_KLL, STD = 100;
    private String KTG_LBT, KTG_LBT2, KTG_LBT3, KTG_LBTT, KTG_PFR, KTG_KLL, KTG_KLF;
    private static DecimalFormat df = new DecimalFormat("0.0");

    public class A41ViewHolder extends RecyclerView.ViewHolder {

        private TextView lbt_data, lbt_dev, lbt2_data, lbt2_dev, lbt3_data, lbt3_dev, lbt_hasil, pfr_data,
                pfr_dev, pfr_hasil, kll_data, kll_dev, kll_hasil, rec_data;

        private LinearLayout lbt_back, pfr_back, kll_back;
        private ImageView FT1, FT2, FT3;

        public A41ViewHolder(@NonNull View itemView) {
            super(itemView);

            lbt_data = (TextView) itemView.findViewById(R.id.lbt_data);
            lbt_dev = (TextView) itemView.findViewById(R.id.lbt_dev);
            lbt2_data = (TextView) itemView.findViewById(R.id.lbt2_data);
            lbt2_dev = (TextView) itemView.findViewById(R.id.lbt2_dev);
            lbt3_data = (TextView) itemView.findViewById(R.id.lbt3_data);
            lbt3_dev = (TextView) itemView.findViewById(R.id.lbt3_dev);
            lbt_hasil = (TextView) itemView.findViewById(R.id.lbt_hasil);

            pfr_data = (TextView) itemView.findViewById(R.id.pfr_data);
            pfr_dev = (TextView) itemView.findViewById(R.id.pfr_dev);
            pfr_hasil = (TextView) itemView.findViewById(R.id.pfr_hasil);

            kll_data = (TextView) itemView.findViewById(R.id.kll_data);
            kll_dev = (TextView) itemView.findViewById(R.id.kll_dev);
            kll_hasil = (TextView) itemView.findViewById(R.id.kll_hasil);

            rec_data = (TextView) itemView.findViewById(R.id.rec_data);

            FT1 = (ImageView) itemView.findViewById(R.id.FT1);
            FT2 = (ImageView) itemView.findViewById(R.id.FT2);
            FT3 = (ImageView) itemView.findViewById(R.id.FT3);

            lbt_back = (LinearLayout) itemView.findViewById(R.id.lbt_back);
            pfr_back = (LinearLayout) itemView.findViewById(R.id.pfr_back);
            kll_back = (LinearLayout) itemView.findViewById(R.id.kll_back);

        }
    }

    public A41_Adapter(A41Activity a41Activity, List<A41_Class> mdataList) {
        this.a41Activity = a41Activity;
        this.mdataList = mdataList;
    }

    @NonNull
    @Override
    public A41_Adapter.A41ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.a41_layout, parent, false);
        A41_Adapter.A41ViewHolder a41 = new A41_Adapter.A41ViewHolder(view);
        return a41;
    }

    @Override
    public void onBindViewHolder(@NonNull A41_Adapter.A41ViewHolder holder, int position) {

        final A41_Class currentItem = mdataList.get(position);

        try {
            if(currentItem.getLBT() == -1){
                DEV_LBT = -1;
                KTG_LBT = "-";
                holder.lbt_data.setText("-");
                holder.lbt_dev.setText("-");
            } else {
                if(a41Activity.KPR.equals("Jalan Bebas Hambatan (JBH)")){
                    if(currentItem.getSLBT().equals("2 x 14 m")){
                        if(currentItem.getLBT() >= 42.5){
                            DEV_LBT = 0;
                            KTG_LBT = "LF";
                        } else {
                            DEV_LBT = (currentItem.getLBT() - 42.5)/42.5;
                            DEV_LBT = (DEV_LBT * 100);
                            if(DEV_LBT < 0){
                                DEV_LBT = DEV_LBT * -1;
                            }
                            if(DEV_LBT > 100){
                                DEV_LBT = 100;
                            }
                            DEV_LBT = Double.parseDouble(df.format(DEV_LBT).replace(",", "."));
                            KTG_LBT = "LS";
                        }
                    } else if(currentItem.getSLBT().equals("2 x 11 m")){
                        if(currentItem.getLBT() >= 35.5){
                            DEV_LBT = 0;
                            KTG_LBT = "LF";
                        } else {
                            DEV_LBT = (currentItem.getLBT() - 35.5)/35.5;
                            DEV_LBT = (DEV_LBT * 100);
                            if(DEV_LBT < 0){
                                DEV_LBT = DEV_LBT * -1;
                            }
                            if(DEV_LBT > 100){
                                DEV_LBT = 100;
                            }
                            DEV_LBT = Double.parseDouble(df.format(DEV_LBT).replace(",", "."));
                            KTG_LBT = "LS";
                        }
                    } else if(currentItem.getSLBT().equals("2 x 7 m")){
                        if(currentItem.getLBT() >= 28.5){
                            DEV_LBT = 0;
                            KTG_LBT = "LF";
                        } else {
                            DEV_LBT = (currentItem.getLBT() - 28.5)/28.5;
                            DEV_LBT = (DEV_LBT * 100);
                            if(DEV_LBT < 0){
                                DEV_LBT = DEV_LBT * -1;
                            }
                            if(DEV_LBT > 100){
                                DEV_LBT = 100;
                            }
                            DEV_LBT = Double.parseDouble(df.format(DEV_LBT).replace(",", "."));
                            KTG_LBT = "LS";
                        }
                    } else {
                        DEV_LBT = 0;
                        KTG_LBT = "-";
                    }
                } else if(a41Activity.KPR.equals("Jalan Raya (JR)")){
                    if(currentItem.getSLBT().equals("2 x 14 m")){
                        if(currentItem.getLBT() >= 38.5){
                            DEV_LBT = 0;
                            KTG_LBT = "LF";
                        } else {
                            DEV_LBT = (currentItem.getLBT() - 38.5)/38.5;
                            DEV_LBT = (DEV_LBT * 100);
                            if(DEV_LBT < 0){
                                DEV_LBT = DEV_LBT * -1;
                            }
                            if(DEV_LBT > 100){
                                DEV_LBT = 100;
                            }
                            DEV_LBT = Double.parseDouble(df.format(DEV_LBT).replace(",", "."));
                            KTG_LBT = "LS";
                        }
                    } else if(currentItem.getSLBT().equals("2 x 11 m")){
                        if(currentItem.getLBT() >= 31){
                            DEV_LBT = 0;
                            KTG_LBT = "LF";
                        } else {
                            DEV_LBT = (currentItem.getLBT() - 31)/31;
                            DEV_LBT = (DEV_LBT * 100);
                            if(DEV_LBT < 0){
                                DEV_LBT = DEV_LBT * -1;
                            }
                            if(DEV_LBT > 100){
                                DEV_LBT = 100;
                            }
                            DEV_LBT = Double.parseDouble(df.format(DEV_LBT).replace(",", "."));
                            KTG_LBT = "LS";
                        }
                    } else if(currentItem.getSLBT().equals("2 x 7 m")){
                        if(currentItem.getLBT() >= 24){
                            DEV_LBT = 0;
                            KTG_LBT = "LF";
                        } else {
                            DEV_LBT = (currentItem.getLBT() - 24)/24;
                            DEV_LBT = (DEV_LBT * 100);
                            if(DEV_LBT < 0){
                                DEV_LBT = DEV_LBT * -1;
                            }
                            if(DEV_LBT > 100){
                                DEV_LBT = 100;
                            }
                            DEV_LBT = Double.parseDouble(df.format(DEV_LBT).replace(",", "."));
                            KTG_LBT = "LS";
                        }
                    } else {
                        DEV_LBT = 0;
                        KTG_LBT = "-";
                    }
                } else if(a41Activity.KPR.equals("Jalan Sedang (JS)")){
                    if(currentItem.getSLBT().equals("7 m")){
                        if(currentItem.getLBT() >= 13){
                            DEV_LBT = 0;
                            KTG_LBT = "LF";
                        } else {
                            DEV_LBT = (currentItem.getLBT() - 13)/13;
                            DEV_LBT = (DEV_LBT * 100);
                            if(DEV_LBT < 0){
                                DEV_LBT = DEV_LBT * -1;
                            }
                            if(DEV_LBT > 100){
                                DEV_LBT = 100;
                            }
                            DEV_LBT = Double.parseDouble(df.format(DEV_LBT).replace(",", "."));
                            KTG_LBT = "LS";
                        }
                    } else {
                        DEV_LBT = 0;
                        KTG_LBT = "-";
                    }
                } else {
                    if(currentItem.getSLBT().equals("5.5 m")){
                        if(currentItem.getLBT() >= 8.5){
                            DEV_LBT = 0;
                            KTG_LBT = "LF";
                        } else {
                            DEV_LBT = (currentItem.getLBT() - 8.5)/8.5;
                            DEV_LBT = (DEV_LBT * 100);
                            if(DEV_LBT < 0){
                                DEV_LBT = DEV_LBT * -1;
                            }
                            if(DEV_LBT > 100){
                                DEV_LBT = 100;
                            }
                            DEV_LBT = Double.parseDouble(df.format(DEV_LBT).replace(",", "."));
                            KTG_LBT = "LS";
                        }
                    } else if (currentItem.getSLBT().equals("2.5 m")){
                        if(currentItem.getLBT() >= 5.5){
                            DEV_LBT = 0;
                            KTG_LBT = "LF";
                        } else {
                            DEV_LBT = (currentItem.getLBT() - 5.5)/5.5;
                            DEV_LBT = (DEV_LBT * 100);
                            if(DEV_LBT < 0){
                                DEV_LBT = DEV_LBT * -1;
                            }
                            if(DEV_LBT > 100){
                                DEV_LBT = 100;
                            }
                            DEV_LBT = Double.parseDouble(df.format(DEV_LBT).replace(",", "."));
                            KTG_LBT = "LS";
                        }
                    } else {
                        DEV_LBT = 0;
                        KTG_LBT = "-";
                    }
                }
                holder.lbt_data.setText(String.valueOf(currentItem.getLBT())+" m");
                holder.lbt_dev.setText(String.valueOf(DEV_LBT)+"%");
            }
        } catch (Exception e){
            Toast.makeText(a41Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            if(currentItem.getLBT2() == -1){
                DEV_LBT2 = -1;
                KTG_LBT2 = "-";
                holder.lbt2_data.setText("-");
                holder.lbt2_dev.setText("-");
            } else {
                if(currentItem.getLBT2() >= 5){
                    DEV_LBT2 = 0;
                    KTG_LBT2 = "LF";
                } else {
                    DEV_LBT2 = (currentItem.getLBT2() - 5)/5;
                    DEV_LBT2 = (DEV_LBT2 * 100);
                    if(DEV_LBT2 < 0){
                        DEV_LBT2 = DEV_LBT2 * -1;
                    }
                    if(DEV_LBT2 > 100){
                        DEV_LBT2 = 100;
                    }
                    DEV_LBT2 = Double.parseDouble(df.format(DEV_LBT2).replace(",", "."));
                    KTG_LBT2 = "LS";
                }
                holder.lbt2_data.setText(String.valueOf(currentItem.getLBT2())+" m");
                holder.lbt2_dev.setText(String.valueOf(DEV_LBT2)+"%");
            }
        } catch (Exception e){
            Toast.makeText(a41Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            if(currentItem.getLBT3() == -1){
                DEV_LBT3 = -1;
                KTG_LBT3 = "-";
                holder.lbt3_data.setText("-");
                holder.lbt3_dev.setText("-");
            } else {
                if(currentItem.getLBT3() >= 1.5){
                    DEV_LBT3 = 0;
                    KTG_LBT3 = "LF";
                } else {
                    DEV_LBT3 = (currentItem.getLBT3() - 1.5)/1.5;
                    DEV_LBT3 = (DEV_LBT3 * 100);
                    if(DEV_LBT3 < 0){
                        DEV_LBT3 = DEV_LBT3 * -1;
                    }
                    if(DEV_LBT3 > 100){
                        DEV_LBT3 = 100;
                    }
                    DEV_LBT3 = Double.parseDouble(df.format(DEV_LBT3).replace(",", "."));
                    KTG_LBT3 = "LS";
                }
                holder.lbt3_data.setText(String.valueOf(currentItem.getLBT3())+" m");
                holder.lbt3_dev.setText(String.valueOf(DEV_LBT3)+"%");
            }
        } catch (Exception e){
            Toast.makeText(a41Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            if((KTG_LBT.equals("LF") || KTG_LBT.equals("-")) && (KTG_LBT2.equals("LF") || KTG_LBT2.equals("-"))
                    && (KTG_LBT3.equals("LF") || KTG_LBT3.equals("-"))){

                if(KTG_LBT.equals("-") && KTG_LBT2.equals("-") && KTG_LBT3.equals("-")){
                    KTG_LBTT = "-";
                    holder.lbt_back.setBackground(a41Activity.getResources().getDrawable(R.drawable.null_style));
                } else {
                    KTG_LBTT = "LF";
                    holder.lbt_back.setBackground(a41Activity.getResources().getDrawable(R.drawable.success_style));
                }

            } else {
                KTG_LBTT = "LS";
                holder.lbt_back.setBackground(a41Activity.getResources().getDrawable(R.drawable.failed_style));
            }
            holder.lbt_hasil.setText(KTG_LBTT);
        } catch (Exception e){
            Toast.makeText(a41Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            if(currentItem.getPFR() == -1){
                DEV_PFR = -1;
                KTG_PFR = "-";
                holder.pfr_data.setText("-");
                holder.pfr_dev.setText("-");
                holder.pfr_hasil.setText(KTG_PFR);
                holder.pfr_back.setBackground(a41Activity.getResources().getDrawable(R.drawable.null_style));
            } else {
                DEV_PFR = STD - currentItem.getPFR();
                if(DEV_PFR == 0){
                    KTG_PFR = "LF";
                    holder.pfr_back.setBackground(a41Activity.getResources().getDrawable(R.drawable.success_style));
                } else {
                    KTG_PFR = "LS";
                    holder.pfr_back.setBackground(a41Activity.getResources().getDrawable(R.drawable.failed_style));
                }
                holder.pfr_data.setText(String.valueOf(currentItem.getPFR())+"%");
                holder.pfr_dev.setText(String.valueOf(DEV_PFR)+"%");
                holder.pfr_hasil.setText(KTG_PFR);
            }
        } catch (Exception e){
            Toast.makeText(a41Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            if(currentItem.getKLL() == -1){
                DEV_KLL = -1;
                KTG_KLL = "-";
                holder.kll_data.setText("-");
                holder.kll_dev.setText("-");
                holder.kll_hasil.setText(KTG_KLL);
                holder.kll_back.setBackground(a41Activity.getResources().getDrawable(R.drawable.null_style));
            } else {
                DEV_KLL = STD - currentItem.getKLL();
                if(DEV_KLL == 0){
                    KTG_KLL = "LF";
                    holder.kll_back.setBackground(a41Activity.getResources().getDrawable(R.drawable.success_style));
                } else {
                    KTG_KLL = "LS";
                    holder.kll_back.setBackground(a41Activity.getResources().getDrawable(R.drawable.failed_style));
                }
                holder.kll_data.setText(String.valueOf(currentItem.getKLL())+"%");
                holder.kll_dev.setText(String.valueOf(DEV_KLL)+"%");
                holder.kll_hasil.setText(KTG_KLL);
            }
        } catch (Exception e){
            Toast.makeText(a41Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            if((KTG_LBTT.equals("LF") || KTG_LBTT.equals("-")) && (KTG_PFR.equals("LF") || KTG_PFR.equals("-"))
                    && (KTG_KLL.equals("LF") || KTG_KLL.equals("-"))){

                if(KTG_LBTT.equals("-") && KTG_PFR.equals("-") && KTG_KLL.equals("-")){
                    KTG_KLF = "Tidak Dinilai";
                } else {
                    KTG_KLF = "LF";
                }

            } else {
                KTG_KLF = "LS";
            }
        } catch (Exception e){
            Toast.makeText(a41Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            holder.rec_data.setText(currentItem.getREC());
        } catch (Exception e){
            Toast.makeText(a41Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            holder.FT1.setImageBitmap(BitmapFactory.decodeFile(currentItem.getDIR1()));
        } catch (Exception e){
            Toast.makeText(a41Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            holder.FT2.setImageBitmap(BitmapFactory.decodeFile(currentItem.getDIR2()));
        } catch (Exception e){
            Toast.makeText(a41Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            holder.FT3.setImageBitmap(BitmapFactory.decodeFile(currentItem.getDIR3()));
        } catch (Exception e){
            Toast.makeText(a41Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            if(currentItem.getUPLOAD().equals("F")){
                a41Activity.FAB_UPLOAD.hide();
                a41Activity.UPLOAD.setImageResource(R.drawable.ic_file_upload_white_18dp);
                a41Activity.FAB_UPLOAD.setEnabled(true);
            } else {
                a41Activity.UPLOAD.setImageResource(R.drawable.ic_done_white_18dp);
                a41Activity.FAB_UPLOAD.setEnabled(false);
            }
        } catch (Exception e){
            Toast.makeText(a41Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            a41Activity.ID = currentItem.getID();
            a41Activity.SLBT_TXT = currentItem.getSLBT();
            a41Activity.LBT_TXT = currentItem.getLBT();
            a41Activity.LBT2_TXT = currentItem.getLBT2();
            a41Activity.LBT3_TXT = currentItem.getLBT3();
            a41Activity.PFR_TXT = currentItem.getPFR();
            a41Activity.KLL_TXT = currentItem.getKLL();
            a41Activity.REC_TXT = currentItem.getREC();
            a41Activity.DIR1 = currentItem.getDIR1();
            a41Activity.DIR2 = currentItem.getDIR2();
            a41Activity.DIR3 = currentItem.getDIR3();

            a41Activity.DEV_LBT = DEV_LBT;
            a41Activity.DEV_LBT2 = DEV_LBT2;
            a41Activity.DEV_LBT3 = DEV_LBT3;
            a41Activity.DEV_PFR = DEV_PFR;
            a41Activity.DEV_KLL = DEV_KLL;

            a41Activity.KTG_LBTT = KTG_LBTT;
            a41Activity.KTG_PFR = KTG_PFR;
            a41Activity.KTG_KLL = KTG_KLL;
            a41Activity.KTG_KLF = KTG_KLF;

            if(KTG_KLF.equals("LS")){
                a41Activity.klf.setText(KTG_KLF);
                a41Activity.klf.setBackground(a41Activity.getResources().getDrawable(R.drawable.failed_style));
                Animation animation = AnimationUtils.loadAnimation(a41Activity.getApplicationContext(), R.anim.recycle_bottom);
                a41Activity.klf.startAnimation(animation);
            } else if(KTG_KLF.equals("Tidak Dinilai")){
                a41Activity.klf.setText("Tidak Dinilai");
                a41Activity.klf.setBackground(a41Activity.getResources().getDrawable(R.drawable.null_style));
                Animation animation = AnimationUtils.loadAnimation(a41Activity.getApplicationContext(), R.anim.recycle_bottom);
                a41Activity.klf.startAnimation(animation);
            } else {
                a41Activity.klf.setText(KTG_KLF);
                a41Activity.klf.setBackground(a41Activity.getResources().getDrawable(R.drawable.success_style));
                Animation animation = AnimationUtils.loadAnimation(a41Activity.getApplicationContext(), R.anim.recycle_bottom);
                a41Activity.klf.startAnimation(animation);
            }
        } catch (Exception e){
            Toast.makeText(a41Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public int getItemCount() {
        return mdataList.size();
    }

}
