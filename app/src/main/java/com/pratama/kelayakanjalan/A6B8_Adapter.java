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

public class A6B8_Adapter extends RecyclerView.Adapter<A6B8_Adapter.A6B8ViewHolder> {

    private A6B8Activity a6B8Activity;
    private List<A6B8_Class> mdataList;
    private double DEV_RPP, DEV_RPP2, DEV_RPP3, DEV_RPP4, DEV_RPP5, DEV_RPP6, DEV_RPP7, DEV_PPJ, STD = 100;
    private String KTG_RPP, KTG_RPP2, KTG_RPP3, KTG_RPP4, KTG_RPP5, KTG_RPP6, KTG_RPP7, KTG_RPPP, KTG_PPJ, KTG_KLF;
    private static DecimalFormat df = new DecimalFormat("0.0");

    public class A6B8ViewHolder extends RecyclerView.ViewHolder {

        private TextView rpp_data, rpp_dev, rpp2_data, rpp2_dev, rpp3_data, rpp3_dev, rpp4_data, rpp4_dev,
                rpp5_data, rpp5_dev, rpp6_data, rpp6_dev, rpp7_data, rpp7_dev, rpp_hasil, ppj_data, ppj_dev, ppj_hasil, rec_data;
        private LinearLayout rpp_back, ppj_back;
        private ImageView FT1, FT2, FT3, FT4;

        public A6B8ViewHolder(@NonNull View itemView) {
            super(itemView);

            rpp_data = (TextView) itemView.findViewById(R.id.rpp_data);
            rpp_dev = (TextView) itemView.findViewById(R.id.rpp_dev);
            rpp2_data = (TextView) itemView.findViewById(R.id.rpp2_data);
            rpp2_dev = (TextView) itemView.findViewById(R.id.rpp2_dev);
            rpp3_data = (TextView) itemView.findViewById(R.id.rpp3_data);
            rpp3_dev = (TextView) itemView.findViewById(R.id.rpp3_dev);
            rpp4_data = (TextView) itemView.findViewById(R.id.rpp4_data);
            rpp4_dev = (TextView) itemView.findViewById(R.id.rpp4_dev);
            rpp5_data = (TextView) itemView.findViewById(R.id.rpp5_data);
            rpp5_dev = (TextView) itemView.findViewById(R.id.rpp5_dev);
            rpp6_data = (TextView) itemView.findViewById(R.id.rpp6_data);
            rpp6_dev = (TextView) itemView.findViewById(R.id.rpp6_dev);
            rpp7_data = (TextView) itemView.findViewById(R.id.rpp7_data);
            rpp7_dev = (TextView) itemView.findViewById(R.id.rpp7_dev);
            rpp_hasil = (TextView) itemView.findViewById(R.id.rpp_hasil);

            ppj_data = (TextView) itemView.findViewById(R.id.ppj_data);
            ppj_dev = (TextView) itemView.findViewById(R.id.ppj_dev);
            ppj_hasil = (TextView) itemView.findViewById(R.id.ppj_hasil);

            rec_data = (TextView) itemView.findViewById(R.id.rec_data);

            FT1 = (ImageView) itemView.findViewById(R.id.FT1);
            FT2 = (ImageView) itemView.findViewById(R.id.FT2);
            FT3 = (ImageView) itemView.findViewById(R.id.FT3);
            FT4 = (ImageView) itemView.findViewById(R.id.FT4);

            rpp_back = (LinearLayout) itemView.findViewById(R.id.rpp_back);
            ppj_back = (LinearLayout) itemView.findViewById(R.id.ppj_back);

        }

    }

    public A6B8_Adapter(A6B8Activity a6B8Activity, List<A6B8_Class> mdataList) {
        this.a6B8Activity = a6B8Activity;
        this.mdataList = mdataList;
    }

    @NonNull
    @Override
    public A6B8_Adapter.A6B8ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.a6b8_layout, parent, false);
        A6B8_Adapter.A6B8ViewHolder a6b8 = new A6B8_Adapter.A6B8ViewHolder(view);
        return a6b8;
    }

    @Override
    public void onBindViewHolder(@NonNull A6B8_Adapter.A6B8ViewHolder holder, int position) {

        final A6B8_Class currentItem = mdataList.get(position);

        try {
            if(currentItem.getRPP() == -1){
                DEV_RPP = -1;
                KTG_RPP = "-";
                holder.rpp_data.setText("-");
                holder.rpp_dev.setText("-");
            } else {
                if(currentItem.getRPP() >= 0.6){
                    DEV_RPP = 0;
                    KTG_RPP = "LF";
                } else {
                    DEV_RPP = (currentItem.getRPP() - 0.6)/0.6;
                    DEV_RPP = DEV_RPP * 100;
                    if(DEV_RPP < 0){
                        DEV_RPP = DEV_RPP * -1;
                    }
                    if(DEV_RPP > 100){
                        DEV_RPP = 100;
                    }
                    DEV_RPP = Double.parseDouble(df.format(DEV_RPP).replace(",", "."));
                    KTG_RPP = "LF";
                }
                holder.rpp_data.setText(String.valueOf(currentItem.getRPP())+" m");
                holder.rpp_dev.setText(String.valueOf(DEV_RPP)+"%");
            }
        } catch (Exception e){
            Toast.makeText(a6B8Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            if(currentItem.getRPP2() == -1){
                DEV_RPP2 = -1;
                KTG_RPP2 = "-";
                holder.rpp2_data.setText("-");
                holder.rpp2_dev.setText("-");
            } else {
                if(currentItem.getRPP2() >= 0.7){
                    DEV_RPP2 = 0;
                    KTG_RPP2 = "LF";
                } else {
                    DEV_RPP2 = (currentItem.getRPP2() - 0.7)/0.7;
                    DEV_RPP2 = DEV_RPP2 * 100;
                    if(DEV_RPP2 < 0){
                        DEV_RPP2 = DEV_RPP2 * -1;
                    }
                    if(DEV_RPP2 > 100){
                        DEV_RPP2 = 100;
                    }
                    DEV_RPP2 = Double.parseDouble(df.format(DEV_RPP2).replace(",", "."));
                    KTG_RPP2 = "LF";
                }
                holder.rpp2_data.setText(String.valueOf(currentItem.getRPP2())+" m");
                holder.rpp2_dev.setText(String.valueOf(DEV_RPP2)+"%");
            }
        } catch (Exception e){
            Toast.makeText(a6B8Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            if(currentItem.getRPP3() == -1){
                DEV_RPP3 = -1;
                KTG_RPP3 = "-";
                holder.rpp3_data.setText("-");
                holder.rpp3_dev.setText("-");
            } else {
                if(currentItem.getRPP3() >= 0.9){
                    DEV_RPP3 = 0;
                    KTG_RPP3 = "LF";
                } else {
                    DEV_RPP3 = (currentItem.getRPP3() - 0.9)/0.9;
                    DEV_RPP3 = DEV_RPP3 * 100;
                    if(DEV_RPP3 < 0){
                        DEV_RPP3 = DEV_RPP3 * -1;
                    }
                    if(DEV_RPP3 > 100){
                        DEV_RPP3 = 100;
                    }
                    DEV_RPP3 = Double.parseDouble(df.format(DEV_RPP3).replace(",", "."));
                    KTG_RPP3 = "LF";
                }
                holder.rpp3_data.setText(String.valueOf(currentItem.getRPP3())+" m");
                holder.rpp3_dev.setText(String.valueOf(DEV_RPP3)+"%");
            }
        } catch (Exception e){
            Toast.makeText(a6B8Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            if(currentItem.getRPP4() == -1){
                DEV_RPP4 = -1;
                KTG_RPP4 = "-";
                holder.rpp4_data.setText("-");
                holder.rpp4_dev.setText("-");
            } else {
                if(currentItem.getRPP4() <= 4){
                    DEV_RPP4 = 0;
                    KTG_RPP4 = "LF";
                } else {
                    DEV_RPP4 = (currentItem.getRPP4() - 4)/4;
                    DEV_RPP4 = DEV_RPP4 * 100;
                    if(DEV_RPP4 < 0){
                        DEV_RPP4 = DEV_RPP4 * -1;
                    }
                    if(DEV_RPP4 > 100){
                        DEV_RPP4 = 100;
                    }
                    DEV_RPP4 = Double.parseDouble(df.format(DEV_RPP4).replace(",", "."));
                    KTG_RPP4 = "LF";
                }
                holder.rpp4_data.setText(String.valueOf(currentItem.getRPP4())+" m");
                holder.rpp4_dev.setText(String.valueOf(DEV_RPP4)+"%");
            }
        } catch (Exception e){
            Toast.makeText(a6B8Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            if(currentItem.getRPP5() == -1){
                DEV_RPP5 = -1;
                KTG_RPP5 = "-";
                holder.rpp5_data.setText("-");
                holder.rpp5_dev.setText("-");
            } else {
                DEV_RPP5 = STD - currentItem.getRPP5();
                if(DEV_RPP5 == 0){
                    KTG_RPP5 = "LF";
                } else {
                    KTG_RPP5 = "LS";
                }
                holder.rpp5_data.setText(String.valueOf(currentItem.getRPP5())+"%");
                holder.rpp5_dev.setText(String.valueOf(DEV_RPP5)+"%");
            }
        } catch (Exception e){
            Toast.makeText(a6B8Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            if(currentItem.getRPP6() == -1){
                DEV_RPP6 = -1;
                KTG_RPP6 = "-";
                holder.rpp6_data.setText("-");
                holder.rpp6_dev.setText("-");
            } else {
                DEV_RPP6 = STD - currentItem.getRPP6();
                if(DEV_RPP6 == 0){
                    KTG_RPP6 = "LF";
                } else {
                    KTG_RPP6 = "LS";
                }
                holder.rpp6_data.setText(String.valueOf(currentItem.getRPP6())+"%");
                holder.rpp6_dev.setText(String.valueOf(DEV_RPP6)+"%");
            }
        } catch (Exception e){
            Toast.makeText(a6B8Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            if(currentItem.getRPP7() == -1){
                DEV_RPP7 = -1;
                KTG_RPP7 = "-";
                holder.rpp7_data.setText("-");
                holder.rpp7_dev.setText("-");
            } else {
                DEV_RPP7 = STD - currentItem.getRPP7();
                if(DEV_RPP7 == 0){
                    KTG_RPP7 = "LF";
                } else {
                    KTG_RPP7 = "LS";
                }
                holder.rpp7_data.setText(String.valueOf(currentItem.getRPP7())+"%");
                holder.rpp7_dev.setText(String.valueOf(DEV_RPP7)+"%");
            }
        } catch (Exception e){
            Toast.makeText(a6B8Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            if((KTG_RPP.equals("LF") || KTG_RPP.equals("-")) && (KTG_RPP2.equals("LF") || KTG_RPP2.equals("-")) && (KTG_RPP3.equals("LF") || KTG_RPP3.equals("-")) &&
                    (KTG_RPP4.equals("LF") || KTG_RPP4.equals("-")) && (KTG_RPP5.equals("LF") || KTG_RPP5.equals("-")) && (KTG_RPP6.equals("LF") || KTG_RPP6.equals("-")) &&
                    (KTG_RPP7.equals("LF") || KTG_RPP7.equals("-"))){

                if(KTG_RPP.equals("-") && KTG_RPP2.equals("-") && KTG_RPP3.equals("-") && KTG_RPP4.equals("-") &&
                        KTG_RPP5.equals("-") && KTG_RPP6.equals("-") && KTG_RPP7.equals("-")){
                    KTG_RPPP = "-";
                    holder.rpp_back.setBackground(a6B8Activity.getResources().getDrawable(R.drawable.null_style));
                } else {
                    KTG_RPPP = "LF";
                    holder.rpp_back.setBackground(a6B8Activity.getResources().getDrawable(R.drawable.success_style));
                }

            } else {
                KTG_RPPP = "LS";
                holder.rpp_back.setBackground(a6B8Activity.getResources().getDrawable(R.drawable.failed_style));
            }
            holder.rpp_hasil.setText(KTG_RPPP);
        } catch (Exception e){
            Toast.makeText(a6B8Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            if(currentItem.getPPJ() == -1){
                DEV_PPJ = -1;
                KTG_PPJ = "-";
                holder.ppj_data.setText("-");
                holder.ppj_dev.setText("-");
                holder.ppj_hasil.setText(KTG_PPJ);
                holder.ppj_back.setBackground(a6B8Activity.getResources().getDrawable(R.drawable.null_style));
            } else {
                DEV_PPJ = STD - currentItem.getPPJ();
                if(DEV_PPJ == 0){
                    KTG_PPJ = "LF";
                    holder.ppj_back.setBackground(a6B8Activity.getResources().getDrawable(R.drawable.success_style));
                } else {
                    KTG_PPJ = "LS";
                    holder.ppj_back.setBackground(a6B8Activity.getResources().getDrawable(R.drawable.failed_style));
                }
                holder.ppj_data.setText(String.valueOf(currentItem.getPPJ())+"%");
                holder.ppj_dev.setText(String.valueOf(DEV_PPJ)+"%");
                holder.ppj_hasil.setText(KTG_PPJ);
            }
        } catch (Exception e){
            Toast.makeText(a6B8Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            if((KTG_RPPP.equals("LF") || KTG_RPPP.equals("-")) && (KTG_PPJ.equals("LF") || KTG_PPJ.equals("-"))){

                if(KTG_RPPP.equals("-") && KTG_PPJ.equals("-")){
                    KTG_KLF = "Tidak Dinilai";
                } else {
                    KTG_KLF = "LF";
                }

            } else {
                KTG_KLF = "LS";
            }
        } catch (Exception e){
            Toast.makeText(a6B8Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            holder.rec_data.setText(currentItem.getREC());
        } catch (Exception e){
            Toast.makeText(a6B8Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            holder.FT1.setImageBitmap(BitmapFactory.decodeFile(currentItem.getDIR1()));
        } catch (Exception e){
            Toast.makeText(a6B8Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            holder.FT2.setImageBitmap(BitmapFactory.decodeFile(currentItem.getDIR2()));
        } catch (Exception e){
            Toast.makeText(a6B8Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            holder.FT3.setImageBitmap(BitmapFactory.decodeFile(currentItem.getDIR3()));
        } catch (Exception e){
            Toast.makeText(a6B8Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            holder.FT4.setImageBitmap(BitmapFactory.decodeFile(currentItem.getDIR4()));
        } catch (Exception e){
            Toast.makeText(a6B8Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            if(currentItem.getUPLOAD().equals("F")){
                a6B8Activity.FAB_UPLOAD.hide();
                a6B8Activity.UPLOAD.setImageResource(R.drawable.ic_file_upload_white_18dp);
                a6B8Activity.FAB_UPLOAD.setEnabled(true);
            } else {
                a6B8Activity.UPLOAD.setImageResource(R.drawable.ic_done_white_18dp);
                a6B8Activity.FAB_UPLOAD.setEnabled(false);
            }
        } catch (Exception e){
            Toast.makeText(a6B8Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            a6B8Activity.ID = currentItem.getID();
            a6B8Activity.RPP_TXT = currentItem.getRPP();
            a6B8Activity.RPP2_TXT = currentItem.getRPP2();
            a6B8Activity.RPP3_TXT = currentItem.getRPP3();
            a6B8Activity.RPP4_TXT = currentItem.getRPP4();
            a6B8Activity.RPP5_TXT = currentItem.getRPP5();
            a6B8Activity.RPP6_TXT = currentItem.getRPP6();
            a6B8Activity.RPP7_TXT = currentItem.getRPP7();
            a6B8Activity.PPJ_TXT = currentItem.getPPJ();
            a6B8Activity.REC_TXT = currentItem.getREC();
            a6B8Activity.DIR1 = currentItem.getDIR1();
            a6B8Activity.DIR2 = currentItem.getDIR2();
            a6B8Activity.DIR3 = currentItem.getDIR3();
            a6B8Activity.DIR4 = currentItem.getDIR4();

            a6B8Activity.DEV_RPP = DEV_RPP;
            a6B8Activity.DEV_RPP2 = DEV_RPP2;
            a6B8Activity.DEV_RPP3 = DEV_RPP3;
            a6B8Activity.DEV_RPP4 = DEV_RPP4;
            a6B8Activity.DEV_RPP5 = DEV_RPP5;
            a6B8Activity.DEV_RPP6 = DEV_RPP6;
            a6B8Activity.DEV_RPP7 = DEV_RPP7;
            a6B8Activity.DEV_PPJ = DEV_PPJ;

            a6B8Activity.KTG_RPPP = KTG_RPPP;
            a6B8Activity.KTG_PPJ = KTG_PPJ;
            a6B8Activity.KTG_KLF = KTG_KLF;

            if(KTG_KLF.equals("LS")){
                a6B8Activity.klf.setText(KTG_KLF);
                a6B8Activity.klf.setBackground(a6B8Activity.getResources().getDrawable(R.drawable.failed_style));
                Animation animation = AnimationUtils.loadAnimation(a6B8Activity.getApplicationContext(), R.anim.recycle_bottom);
                a6B8Activity.klf.startAnimation(animation);
            } else if(KTG_KLF.equals("Tidak Dinilai")){
                a6B8Activity.klf.setText("Tidak Dinilai");
                a6B8Activity.klf.setBackground(a6B8Activity.getResources().getDrawable(R.drawable.null_style));
                Animation animation = AnimationUtils.loadAnimation(a6B8Activity.getApplicationContext(), R.anim.recycle_bottom);
                a6B8Activity.klf.startAnimation(animation);
            } else {
                a6B8Activity.klf.setText(KTG_KLF);
                a6B8Activity.klf.setBackground(a6B8Activity.getResources().getDrawable(R.drawable.success_style));
                Animation animation = AnimationUtils.loadAnimation(a6B8Activity.getApplicationContext(), R.anim.recycle_bottom);
                a6B8Activity.klf.startAnimation(animation);
            }
        } catch (Exception e){
            Toast.makeText(a6B8Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public int getItemCount() {
        return mdataList.size();
    }

}
