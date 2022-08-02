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

public class A43_Adapter extends RecyclerView.Adapter<A43_Adapter.A43ViewHolder> {

    private A43Activity a43Activity;
    private List<A43_Class> mdataList;
    private double DEV_LRW, DEV_PRW, DEV_PRW2, DEV_PRW3, DEV_PPP, STD = 100;
    private String KTG_LRW, KTG_PRW, KTG_PRW2, KTG_PRW3, KTG_PRWW, KTG_PPP, KTG_KLF;
    private static DecimalFormat df = new DecimalFormat("0.0");

    public class A43ViewHolder extends RecyclerView.ViewHolder {

        private TextView lrw_data, lrw_dev, lrw_hasil, prw_data, prw_dev, prw2_data, prw2_dev, prw3_data, prw3_dev,
                prw_hasil, ppp_data, ppp_dev, ppp_hasil, rec_data;

        private LinearLayout lrw_back, prw_back, ppp_back;
        private ImageView FT1, FT2;

        public A43ViewHolder(@NonNull View itemView) {
            super(itemView);

            lrw_data = (TextView) itemView.findViewById(R.id.lrw_data);
            lrw_dev = (TextView) itemView.findViewById(R.id.lrw_dev);
            lrw_hasil = (TextView) itemView.findViewById(R.id.lrw_hasil);

            prw_data = (TextView) itemView.findViewById(R.id.prw_data);
            prw_dev = (TextView) itemView.findViewById(R.id.prw_dev);
            prw2_data = (TextView) itemView.findViewById(R.id.prw2_data);
            prw2_dev = (TextView) itemView.findViewById(R.id.prw2_dev);
            prw3_data = (TextView) itemView.findViewById(R.id.prw3_data);
            prw3_dev = (TextView) itemView.findViewById(R.id.prw3_dev);
            prw_hasil = (TextView) itemView.findViewById(R.id.prw_hasil);

            ppp_data = (TextView) itemView.findViewById(R.id.ppp_data);
            ppp_dev = (TextView) itemView.findViewById(R.id.ppp_dev);
            ppp_hasil = (TextView) itemView.findViewById(R.id.ppp_hasil);

            rec_data = (TextView) itemView.findViewById(R.id.rec_data);

            FT1 = (ImageView) itemView.findViewById(R.id.FT1);
            FT2 = (ImageView) itemView.findViewById(R.id.FT2);

            lrw_back = (LinearLayout) itemView.findViewById(R.id.lrw_back);
            prw_back = (LinearLayout) itemView.findViewById(R.id.prw_back);
            ppp_back = (LinearLayout) itemView.findViewById(R.id.ppp_back);

        }

    }

    public A43_Adapter(A43Activity a43Activity, List<A43_Class> mdataList) {
        this.a43Activity = a43Activity;
        this.mdataList = mdataList;
    }

    @NonNull
    @Override
    public A43_Adapter.A43ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.a43_layout, parent, false);
        A43_Adapter.A43ViewHolder a43 = new A43_Adapter.A43ViewHolder(view);
        return a43;
    }

    @Override
    public void onBindViewHolder(@NonNull A43_Adapter.A43ViewHolder holder, int position) {

        final A43_Class currentItem = mdataList.get(position);

        try {
            if(currentItem.getLRW() == -1){
                DEV_LRW = -1;
                KTG_LRW = "-";
                holder.lrw_data.setText("-");
                holder.lrw_dev.setText("-");
                holder.lrw_hasil.setText(KTG_LRW);
                holder.lrw_back.setBackground(a43Activity.getResources().getDrawable(R.drawable.null_style));
            } else {
                if(a43Activity.SJR.equals("Primer (Antar Kota)")){
                    if(a43Activity.FNG.equals("Arteri")){
                        if(currentItem.getLRW() >= 15){
                            DEV_LRW = 0;
                            KTG_LRW = "LF";
                            holder.lrw_back.setBackground(a43Activity.getResources().getDrawable(R.drawable.success_style));
                        } else {
                            DEV_LRW = (currentItem.getLRW() - 15)/15;
                            DEV_LRW = (DEV_LRW * 100);
                            if(DEV_LRW < 0){
                                DEV_LRW = DEV_LRW * -1;
                            }
                            if(DEV_LRW > 100){
                                DEV_LRW = 100;
                            }
                            DEV_LRW = Double.parseDouble(df.format(DEV_LRW).replace(",", "."));
                            KTG_LRW = "LS";
                            holder.lrw_back.setBackground(a43Activity.getResources().getDrawable(R.drawable.failed_style));
                        }
                    } else if(a43Activity.FNG.equals("Kolektor")){
                        if(currentItem.getLRW() >= 10){
                            DEV_LRW = 0;
                            KTG_LRW = "LF";
                            holder.lrw_back.setBackground(a43Activity.getResources().getDrawable(R.drawable.success_style));
                        } else {
                            DEV_LRW = (currentItem.getLRW() - 10)/10;
                            DEV_LRW = (DEV_LRW * 100);
                            if(DEV_LRW < 0){
                                DEV_LRW = DEV_LRW * -1;
                            }
                            if(DEV_LRW > 100){
                                DEV_LRW = 100;
                            }
                            DEV_LRW = Double.parseDouble(df.format(DEV_LRW).replace(",", "."));
                            KTG_LRW = "LS";
                            holder.lrw_back.setBackground(a43Activity.getResources().getDrawable(R.drawable.failed_style));
                        }
                    } else if(a43Activity.FNG.equals("Lokal")){
                        if(currentItem.getLRW() >= 7){
                            DEV_LRW = 0;
                            KTG_LRW = "LF";
                            holder.lrw_back.setBackground(a43Activity.getResources().getDrawable(R.drawable.success_style));
                        } else {
                            DEV_LRW = (currentItem.getLRW() - 7)/7;
                            DEV_LRW = (DEV_LRW * 100);
                            if(DEV_LRW < 0){
                                DEV_LRW = DEV_LRW * -1;
                            }
                            if(DEV_LRW > 100){
                                DEV_LRW = 100;
                            }
                            DEV_LRW = Double.parseDouble(df.format(DEV_LRW).replace(",", "."));
                            KTG_LRW = "LS";
                            holder.lrw_back.setBackground(a43Activity.getResources().getDrawable(R.drawable.failed_style));
                        }
                    } else if(a43Activity.FNG.equals("Lingkungan")){
                        if(currentItem.getLRW() >= 5){
                            DEV_LRW = 0;
                            KTG_LRW = "LF";
                            holder.lrw_back.setBackground(a43Activity.getResources().getDrawable(R.drawable.success_style));
                        } else {
                            DEV_LRW = (currentItem.getLRW() - 5)/5;
                            DEV_LRW = (DEV_LRW * 100);
                            if(DEV_LRW < 0){
                                DEV_LRW = DEV_LRW * -1;
                            }
                            if(DEV_LRW > 100){
                                DEV_LRW = 100;
                            }
                            DEV_LRW = Double.parseDouble(df.format(DEV_LRW).replace(",", "."));
                            KTG_LRW = "LS";
                            holder.lrw_back.setBackground(a43Activity.getResources().getDrawable(R.drawable.failed_style));
                        }
                    } else {

                    }
                } else {
                    if(a43Activity.FNG.equals("Arteri")){
                        if(currentItem.getLRW() >= 15){
                            DEV_LRW = 0;
                            KTG_LRW = "LF";
                            holder.lrw_back.setBackground(a43Activity.getResources().getDrawable(R.drawable.success_style));
                        } else {
                            DEV_LRW = (currentItem.getLRW() - 15)/15;
                            DEV_LRW = (DEV_LRW * 100);
                            if(DEV_LRW < 0){
                                DEV_LRW = DEV_LRW * -1;
                            }
                            if(DEV_LRW > 100){
                                DEV_LRW = 100;
                            }
                            DEV_LRW = Double.parseDouble(df.format(DEV_LRW).replace(",", "."));
                            KTG_LRW = "LS";
                            holder.lrw_back.setBackground(a43Activity.getResources().getDrawable(R.drawable.failed_style));
                        }
                    } else if(a43Activity.FNG.equals("Kolektor")){
                        if(currentItem.getLRW() >= 5){
                            DEV_LRW = 0;
                            KTG_LRW = "LF";
                            holder.lrw_back.setBackground(a43Activity.getResources().getDrawable(R.drawable.success_style));
                        } else {
                            DEV_LRW = (currentItem.getLRW() - 5)/5;
                            DEV_LRW = (DEV_LRW * 100);
                            if(DEV_LRW < 0){
                                DEV_LRW = DEV_LRW * -1;
                            }
                            if(DEV_LRW > 100){
                                DEV_LRW = 100;
                            }
                            DEV_LRW = Double.parseDouble(df.format(DEV_LRW).replace(",", "."));
                            KTG_LRW = "LS";
                            holder.lrw_back.setBackground(a43Activity.getResources().getDrawable(R.drawable.failed_style));
                        }
                    } else if(a43Activity.FNG.equals("Lokal")){
                        if(currentItem.getLRW() >= 3){
                            DEV_LRW = 0;
                            KTG_LRW = "LF";
                            holder.lrw_back.setBackground(a43Activity.getResources().getDrawable(R.drawable.success_style));
                        } else {
                            DEV_LRW = (currentItem.getLRW() - 3)/3;
                            DEV_LRW = (DEV_LRW * 100);
                            if(DEV_LRW < 0){
                                DEV_LRW = DEV_LRW * -1;
                            }
                            if(DEV_LRW > 100){
                                DEV_LRW = 100;
                            }
                            DEV_LRW = Double.parseDouble(df.format(DEV_LRW).replace(",", "."));
                            KTG_LRW = "LS";
                            holder.lrw_back.setBackground(a43Activity.getResources().getDrawable(R.drawable.failed_style));
                        }
                    } else if(a43Activity.FNG.equals("Lingkungan")){
                        if(currentItem.getLRW() >= 2){
                            DEV_LRW = 0;
                            KTG_LRW = "LF";
                            holder.lrw_back.setBackground(a43Activity.getResources().getDrawable(R.drawable.success_style));
                        } else {
                            DEV_LRW = (currentItem.getLRW() - 2)/2;
                            DEV_LRW = (DEV_LRW * 100);
                            if(DEV_LRW < 0){
                                DEV_LRW = DEV_LRW * -1;
                            }
                            if(DEV_LRW > 100){
                                DEV_LRW = 100;
                            }
                            DEV_LRW = Double.parseDouble(df.format(DEV_LRW).replace(",", "."));
                            KTG_LRW = "LS";
                            holder.lrw_back.setBackground(a43Activity.getResources().getDrawable(R.drawable.failed_style));
                        }
                    } else {

                    }
                }
                holder.lrw_data.setText(String.valueOf(currentItem.getLRW())+" m");
                holder.lrw_dev.setText(String.valueOf(DEV_LRW)+"%");
                holder.lrw_hasil.setText(KTG_LRW);
            }
        } catch (Exception e){
            Toast.makeText(a43Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            if(currentItem.getPRW() == -1){
                DEV_PRW = -1;
                KTG_PRW = "-";
                holder.prw_data.setText("-");
                holder.prw_dev.setText("-");
            } else {
                DEV_PRW = STD - currentItem.getPRW();
                if(DEV_PRW == 0){
                    KTG_PRW = "LF";
                } else {
                    KTG_PRW = "LS";
                }
                holder.prw_data.setText(String.valueOf(currentItem.getPRW())+"%");
                holder.prw_dev.setText(String.valueOf(DEV_PRW)+"%");
            }
        } catch (Exception e){
            Toast.makeText(a43Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            if(currentItem.getPRW2() == -1){
                DEV_PRW2 = -1;
                KTG_PRW2 = "-";
                holder.prw2_data.setText("-");
                holder.prw2_dev.setText("-");
            } else {
                DEV_PRW2 = STD - currentItem.getPRW2();
                if(DEV_PRW2 == 0){
                    KTG_PRW2 = "LF";
                } else {
                    KTG_PRW2 = "LS";
                }
                holder.prw2_data.setText(String.valueOf(currentItem.getPRW2())+"%");
                holder.prw2_dev.setText(String.valueOf(DEV_PRW2)+"%");
            }
        } catch (Exception e){
            Toast.makeText(a43Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            if(currentItem.getPRW3() == -1){
                DEV_PRW3 = -1;
                KTG_PRW3 = "-";
                holder.prw3_data.setText("-");
                holder.prw3_dev.setText("-");
            } else {
                DEV_PRW3 = STD - currentItem.getPRW3();
                if(DEV_PRW3 == 0){
                    KTG_PRW3 = "LF";
                } else {
                    KTG_PRW3 = "LS";
                }
                holder.prw3_data.setText(String.valueOf(currentItem.getPRW3())+"%");
                holder.prw3_dev.setText(String.valueOf(DEV_PRW3)+"%");
            }
        } catch (Exception e){
            Toast.makeText(a43Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            if((KTG_PRW.equals("LF") || KTG_PRW.equals("-")) && (KTG_PRW2.equals("LF") || KTG_PRW2.equals("-"))
                    && (KTG_PRW3.equals("LF") || KTG_PRW3.equals("-"))){

                if(KTG_PRW.equals("-") && KTG_PRW2.equals("-") && KTG_PRW3.equals("-")){
                    KTG_PRWW = "-";
                    holder.prw_back.setBackground(a43Activity.getResources().getDrawable(R.drawable.null_style));
                } else {
                    KTG_PRWW = "LF";
                    holder.prw_back.setBackground(a43Activity.getResources().getDrawable(R.drawable.success_style));
                }

            } else {
                KTG_PRWW = "LS";
                holder.prw_back.setBackground(a43Activity.getResources().getDrawable(R.drawable.failed_style));
            }
            holder.prw_hasil.setText(KTG_PRWW);
        } catch (Exception e){
            Toast.makeText(a43Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            if(currentItem.getPPP() == -1){
                DEV_PPP = -1;
                KTG_PPP = "-";
                holder.ppp_data.setText("-");
                holder.ppp_dev.setText("-");
                holder.ppp_hasil.setText(KTG_PPP);
                holder.ppp_back.setBackground(a43Activity.getResources().getDrawable(R.drawable.null_style));
            } else {
                if(a43Activity.FNG.equals("Arteri") && a43Activity.SJR.equals("Primer (Antar Kota)")){
                    if(currentItem.getPPP() >= 4){
                        DEV_PPP = 0;
                        KTG_PPP = "LF";
                        holder.ppp_back.setBackground(a43Activity.getResources().getDrawable(R.drawable.success_style));
                    } else {
                        DEV_PPP = (currentItem.getPPP() - 4)/4;
                        DEV_PPP = (DEV_PPP * 100);
                        if(DEV_PPP < 0){
                            DEV_PPP = DEV_PPP * -1;
                        }
                        if(DEV_PPP > 100){
                            DEV_PPP = 100;
                        }
                        DEV_PPP = Double.parseDouble(df.format(DEV_PPP).replace(",", "."));
                        KTG_PPP = "LS";
                        holder.ppp_back.setBackground(a43Activity.getResources().getDrawable(R.drawable.failed_style));
                    }
                } else if(a43Activity.FNG.equals("Arteri") && a43Activity.SJR.equals("Sekunder (Dalam Kota)")){
                    if(currentItem.getPPP() >= 2){
                        DEV_PPP = 0;
                        KTG_PPP = "LF";
                        holder.ppp_back.setBackground(a43Activity.getResources().getDrawable(R.drawable.success_style));
                    } else {
                        DEV_PPP = (currentItem.getPPP() - 2)/2;
                        DEV_PPP = (DEV_PPP * 100);
                        if(DEV_PPP < 0){
                            DEV_PPP = DEV_PPP * -1;
                        }
                        if(DEV_PPP > 100){
                            DEV_PPP = 100;
                        }
                        DEV_PPP = Double.parseDouble(df.format(DEV_PPP).replace(",", "."));
                        KTG_PPP = "LS";
                        holder.ppp_back.setBackground(a43Activity.getResources().getDrawable(R.drawable.failed_style));
                    }
                } else if(a43Activity.FNG.equals("Kolektor") && a43Activity.SJR.equals("Primer (Antar Kota)")){
                    if(currentItem.getPPP() >= 1){
                        DEV_PPP = 0;
                        KTG_PPP = "LF";
                        holder.ppp_back.setBackground(a43Activity.getResources().getDrawable(R.drawable.success_style));
                    } else {
                        DEV_PPP = (currentItem.getPPP() - 1)/1;
                        DEV_PPP = (DEV_PPP * 100);
                        if(DEV_PPP < 0){
                            DEV_PPP = DEV_PPP * -1;
                        }
                        if(DEV_PPP > 100){
                            DEV_PPP = 100;
                        }
                        DEV_PPP = Double.parseDouble(df.format(DEV_PPP).replace(",", "."));
                        KTG_PPP = "LS";
                        holder.ppp_back.setBackground(a43Activity.getResources().getDrawable(R.drawable.failed_style));
                    }
                } else {
                    if(currentItem.getPPP() >= 0){
                        DEV_PPP = 0;
                        KTG_PPP = "LF";
                        holder.ppp_back.setBackground(a43Activity.getResources().getDrawable(R.drawable.success_style));
                    } else {
                        DEV_PPP = (currentItem.getPPP() - 1)/1;
                        DEV_PPP = (DEV_PPP * 100);
                        if(DEV_PPP < 0){
                            DEV_PPP = DEV_PPP * -1;
                        }
                        if(DEV_PPP > 100){
                            DEV_PPP = 100;
                        }
                        DEV_PPP = Double.parseDouble(df.format(DEV_PPP).replace(",", "."));
                        KTG_PPP = "LS";
                        holder.ppp_back.setBackground(a43Activity.getResources().getDrawable(R.drawable.failed_style));
                    }
                }
                holder.ppp_data.setText(String.valueOf(currentItem.getPPP())+" m");
                holder.ppp_dev.setText(String.valueOf(DEV_PPP)+"%");
                holder.ppp_hasil.setText(KTG_PPP);
            }
        } catch (Exception e){
            Toast.makeText(a43Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            if((KTG_LRW.equals("LF") || KTG_LRW.equals("-")) && (KTG_PRWW.equals("LF") || KTG_PRWW.equals("-"))
                    && (KTG_PPP.equals("LF") || KTG_PPP.equals("-"))){

                if(KTG_LRW.equals("-") && KTG_PRWW.equals("-") && KTG_PPP.equals("-")){
                    KTG_KLF = "Tidak Dinilai";
                } else {
                    KTG_KLF = "LF";
                }

            } else {
                KTG_KLF = "LS";
            }
        } catch (Exception e){
            Toast.makeText(a43Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            holder.rec_data.setText(currentItem.getREC());
        } catch (Exception e){
            Toast.makeText(a43Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            holder.FT1.setImageBitmap(BitmapFactory.decodeFile(currentItem.getDIR1()));
        } catch (Exception e){
            Toast.makeText(a43Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            holder.FT2.setImageBitmap(BitmapFactory.decodeFile(currentItem.getDIR2()));
        } catch (Exception e){
            Toast.makeText(a43Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            if(currentItem.getUPLOAD().equals("F")){
                a43Activity.FAB_UPLOAD.hide();
                a43Activity.UPLOAD.setImageResource(R.drawable.ic_file_upload_white_18dp);
                a43Activity.FAB_UPLOAD.setEnabled(true);
            } else {
                a43Activity.UPLOAD.setImageResource(R.drawable.ic_done_white_18dp);
                a43Activity.FAB_UPLOAD.setEnabled(false);
            }
        } catch (Exception e){
            Toast.makeText(a43Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            a43Activity.ID = currentItem.getID();
            a43Activity.LRW_TXT = currentItem.getLRW();
            a43Activity.PRW_TXT = currentItem.getPRW();
            a43Activity.PRW2_TXT = currentItem.getPRW2();
            a43Activity.PRW3_TXT = currentItem.getPRW3();
            a43Activity.PPP_TXT = currentItem.getPPP();
            a43Activity.REC_TXT = currentItem.getREC();
            a43Activity.DIR1 = currentItem.getDIR1();
            a43Activity.DIR2 = currentItem.getDIR2();

            a43Activity.DEV_LRW = DEV_LRW;
            a43Activity.DEV_PRW = DEV_PRW;
            a43Activity.DEV_PRW2 = DEV_PRW2;
            a43Activity.DEV_PRW3 = DEV_PRW3;
            a43Activity.DEV_PPP = DEV_PPP;

            a43Activity.KTG_LRW = KTG_LRW;
            a43Activity.KTG_PRWW = KTG_PRWW;
            a43Activity.KTG_PPP = KTG_PPP;
            a43Activity.KTG_KLF = KTG_KLF;

            if(KTG_KLF.equals("LS")){
                a43Activity.klf.setText(KTG_KLF);
                a43Activity.klf.setBackground(a43Activity.getResources().getDrawable(R.drawable.failed_style));
                Animation animation = AnimationUtils.loadAnimation(a43Activity.getApplicationContext(), R.anim.recycle_bottom);
                a43Activity.klf.startAnimation(animation);
            } else if(KTG_KLF.equals("Tidak Dinilai")){
                a43Activity.klf.setText("Tidak Dinilai");
                a43Activity.klf.setBackground(a43Activity.getResources().getDrawable(R.drawable.null_style));
                Animation animation = AnimationUtils.loadAnimation(a43Activity.getApplicationContext(), R.anim.recycle_bottom);
                a43Activity.klf.startAnimation(animation);
            } else {
                a43Activity.klf.setText(KTG_KLF);
                a43Activity.klf.setBackground(a43Activity.getResources().getDrawable(R.drawable.success_style));
                Animation animation = AnimationUtils.loadAnimation(a43Activity.getApplicationContext(), R.anim.recycle_bottom);
                a43Activity.klf.startAnimation(animation);
            }
        } catch (Exception e){
            Toast.makeText(a43Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public int getItemCount() {
        return mdataList.size();
    }

}
