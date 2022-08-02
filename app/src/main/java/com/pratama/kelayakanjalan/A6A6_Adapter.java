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

public class A6A6_Adapter extends RecyclerView.Adapter<A6A6_Adapter.A6A6ViewHolder> {

    private A6A6Activity a6A6Activity;
    private List<A6A6_Class> mdataList;
    private double DEV_LTA, DEV_LTL, DEV_LTL2, DEV_TPA, DEV_TPA2, DEV_TPA3, DEV_DLA, DEV_ICL, DEV_KAA, STD = 100;
    private String KTG_LTA, KTG_LTL, KTG_LTL2, KTG_TPA, KTG_TPA2, KTG_TPA3, KTG_LTAA, KTG_DLA, KTG_ICL, KTG_KAA, KTG_KLF;
    private static DecimalFormat df = new DecimalFormat("0.0");

    public class A6A6ViewHolder extends RecyclerView.ViewHolder {

        private TextView lta_data, ltl_data, ltl2_data, tpa_data, tpa2_data, tpa3_data, dla_data, icl_data, kaa_data,
                lta_dev, ltl_dev, ltl2_dev, tpa_dev, tpa2_dev, tpa3_dev, dla_dev, icl_dev, kaa_dev, lta_hasil, dla_hasil,
                icl_hasil, kaa_hasil, rec_data;

        private LinearLayout lta_back, dla_back, icl_back, kaa_back;
        private ImageView FT1, FT2;

        public A6A6ViewHolder(@NonNull View itemView) {
            super(itemView);

            lta_data = (TextView) itemView.findViewById(R.id.lta_data);
            lta_dev = (TextView) itemView.findViewById(R.id.lta_dev);
            ltl_data = (TextView) itemView.findViewById(R.id.ltl_data);
            ltl_dev = (TextView) itemView.findViewById(R.id.ltl_dev);
            ltl2_data = (TextView) itemView.findViewById(R.id.ltl2_data);
            ltl2_dev = (TextView) itemView.findViewById(R.id.ltl2_dev);
            tpa_data = (TextView) itemView.findViewById(R.id.tpa_data);
            tpa_dev = (TextView) itemView.findViewById(R.id.tpa_dev);
            tpa2_data = (TextView) itemView.findViewById(R.id.tpa2_data);
            tpa2_dev = (TextView) itemView.findViewById(R.id.tpa2_dev);
            tpa3_data = (TextView) itemView.findViewById(R.id.tpa3_data);
            tpa3_dev = (TextView) itemView.findViewById(R.id.tpa3_dev);
            lta_hasil = (TextView) itemView.findViewById(R.id.lta_hasil);

            dla_data = (TextView) itemView.findViewById(R.id.dla_data);
            dla_dev = (TextView) itemView.findViewById(R.id.dla_dev);
            dla_hasil = (TextView) itemView.findViewById(R.id.dla_hasil);

            icl_data = (TextView) itemView.findViewById(R.id.icl_data);
            icl_dev = (TextView) itemView.findViewById(R.id.icl_dev);
            icl_hasil = (TextView) itemView.findViewById(R.id.icl_hasil);

            kaa_data = (TextView) itemView.findViewById(R.id.kaa_data);
            kaa_dev = (TextView) itemView.findViewById(R.id.kaa_dev);
            kaa_hasil = (TextView) itemView.findViewById(R.id.kaa_hasil);

            rec_data = (TextView) itemView.findViewById(R.id.rec_data);

            FT1 = (ImageView) itemView.findViewById(R.id.FT1);
            FT2 = (ImageView) itemView.findViewById(R.id.FT2);

            lta_back = (LinearLayout) itemView.findViewById(R.id.lta_back);
            dla_back = (LinearLayout) itemView.findViewById(R.id.dla_back);
            icl_back = (LinearLayout) itemView.findViewById(R.id.icl_back);
            kaa_back = (LinearLayout) itemView.findViewById(R.id.kaa_back);

        }
    }

    public A6A6_Adapter(A6A6Activity a6A6Activity, List<A6A6_Class> mdataList) {
        this.a6A6Activity = a6A6Activity;
        this.mdataList = mdataList;
    }

    @NonNull
    @Override
    public A6A6_Adapter.A6A6ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.a6a6_layout, parent, false);
        A6A6_Adapter.A6A6ViewHolder a6a6 = new A6A6_Adapter.A6A6ViewHolder(view);
        return a6a6;
    }

    @Override
    public void onBindViewHolder(@NonNull A6A6_Adapter.A6A6ViewHolder holder, int position) {

        final A6A6_Class currentItem = mdataList.get(position);

        try {
            if(currentItem.getLTA() == -1){
                DEV_LTA = -1;
                KTG_LTA = "-";
                holder.lta_data.setText("-");
                holder.lta_dev.setText("-");
            } else {
                DEV_LTA = STD - currentItem.getLTA();
                if(DEV_LTA == 0){
                    KTG_LTA = "LF";
                } else {
                    KTG_LTA = "LS";
                }
                holder.lta_data.setText(String.valueOf(currentItem.getLTA())+"%");
                holder.lta_dev.setText(String.valueOf(DEV_LTA)+"%");
            }
        } catch (Exception e){
            Toast.makeText(a6A6Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            if(currentItem.getLTL() == -1){
                DEV_LTL = -1;
                KTG_LTL = "-";
                holder.ltl_data.setText("-");
                holder.ltl_dev.setText("-");
            } else {
                if(currentItem.getLTL() >= 0.6){
                    DEV_LTL = 0;
                    KTG_LTL = "LF";
                } else {
                    DEV_LTL = (currentItem.getLTL() - 0.6)/0.6;
                    DEV_LTL = DEV_LTL * 100;
                    if(DEV_LTL < 0){
                        DEV_LTL = DEV_LTL * -1;
                    }
                    if(DEV_LTL > 100){
                        DEV_LTL = 100;
                    }
                    DEV_LTL = Double.parseDouble(df.format(DEV_LTL).replace(",", "."));
                    KTG_LTL = "LS";
                }
                holder.ltl_data.setText(String.valueOf(currentItem.getLTL())+" m");
                holder.ltl_dev.setText(String.valueOf(DEV_LTL)+"%");
            }
        } catch (Exception e){
            Toast.makeText(a6A6Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            if(currentItem.getLTL2() == -1){
                DEV_LTL2 = -1;
                KTG_LTL2 = "-";
                holder.ltl2_data.setText("-");
                holder.ltl2_dev.setText("-");
            } else {
                if(currentItem.getLTL2() >= 0.3){
                    DEV_LTL2 = 0;
                    KTG_LTL2 = "LF";
                } else {
                    DEV_LTL2 = (currentItem.getLTL2() - 0.3)/0.3;
                    DEV_LTL2 = DEV_LTL2 * 100;
                    if(DEV_LTL2 < 0){
                        DEV_LTL2 = DEV_LTL2 * -1;
                    }
                    if(DEV_LTL2 > 100){
                        DEV_LTL2 = 100;
                    }
                    DEV_LTL2 = Double.parseDouble(df.format(DEV_LTL2).replace(",", "."));
                    KTG_LTL2 = "LS";
                }
                holder.ltl2_data.setText(String.valueOf(currentItem.getLTL2())+" m");
                holder.ltl2_dev.setText(String.valueOf(DEV_LTL2)+"%");
            }
        } catch (Exception e){
            Toast.makeText(a6A6Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            if(currentItem.getTPA() == -1){
                DEV_TPA = -1;
                KTG_TPA = "-";
                holder.tpa_data.setText("-");
                holder.tpa_dev.setText("-");
            } else {
                if(currentItem.getTPA() >= 3){
                    DEV_TPA = 0;
                    KTG_TPA = "LF";
                } else {
                    DEV_TPA = (currentItem.getTPA() - 3)/3;
                    DEV_TPA = DEV_TPA * 100;
                    if(DEV_TPA < 0){
                        DEV_TPA = DEV_TPA * -1;
                    }
                    if(DEV_TPA > 100){
                        DEV_TPA = 100;
                    }
                    DEV_TPA = Double.parseDouble(df.format(DEV_TPA).replace(",", "."));
                    KTG_TPA = "LS";
                }
                holder.tpa_data.setText(String.valueOf(currentItem.getTPA())+" m");
                holder.tpa_dev.setText(String.valueOf(DEV_TPA)+"%");
            }
        } catch (Exception e){
            Toast.makeText(a6A6Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            if(currentItem.getTPA2() == -1){
                DEV_TPA2 = -1;
                KTG_TPA2 = "-";
                holder.tpa2_data.setText("-");
                holder.tpa2_dev.setText("-");
            } else {
                if(currentItem.getTPA2() >= 5){
                    DEV_TPA2 = 0;
                    KTG_TPA2 = "LF";
                } else {
                    DEV_TPA2 = (currentItem.getTPA2() - 5)/5;
                    DEV_TPA2 = DEV_TPA2 * 100;
                    if(DEV_TPA2 < 0){
                        DEV_TPA2 = DEV_TPA2 * -1;
                    }
                    if(DEV_TPA2 > 100){
                        DEV_TPA2 = 100;
                    }
                    DEV_TPA2 = Double.parseDouble(df.format(DEV_TPA2).replace(",", "."));
                    KTG_TPA2 = "LS";
                }
                holder.tpa2_data.setText(String.valueOf(currentItem.getTPA2())+" m");
                holder.tpa2_dev.setText(String.valueOf(DEV_TPA2)+"%");
            }
        } catch (Exception e){
            Toast.makeText(a6A6Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            if(currentItem.getTPA3() == -1){
                DEV_TPA3 = -1;
                KTG_TPA3 = "-";
                holder.tpa3_data.setText("-");
                holder.tpa3_dev.setText("-");
            } else {
                if(currentItem.getTPA3() >= 1.75){
                    DEV_TPA3 = 0;
                    KTG_TPA3 = "LF";
                } else {
                    DEV_TPA3 = (currentItem.getTPA3() - 1.75)/1.75;
                    DEV_TPA3 = DEV_TPA3 * 100;
                    if(DEV_TPA3 < 0){
                        DEV_TPA3 = DEV_TPA3 * -1;
                    }
                    if(DEV_TPA3 > 100){
                        DEV_TPA3 = 100;
                    }
                    DEV_TPA3 = Double.parseDouble(df.format(DEV_TPA3).replace(",", "."));
                    KTG_TPA3 = "LS";
                }
                holder.tpa3_data.setText(String.valueOf(currentItem.getTPA3())+" m");
                holder.tpa3_dev.setText(String.valueOf(DEV_TPA3)+"%");
            }
        } catch (Exception e){
            Toast.makeText(a6A6Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            if((KTG_LTA.equals("LF") || KTG_LTA.equals("-")) && (KTG_LTL.equals("LF") || KTG_LTL.equals("-")) &&
                    (KTG_LTL2.equals("LF") || KTG_LTL2.equals("-")) && (KTG_TPA.equals("LF") || KTG_TPA.equals("-")) &&
                    (KTG_TPA2.equals("LF") || KTG_TPA2.equals("-")) && (KTG_TPA3.equals("LF") || KTG_TPA3.equals("-"))){

                if(KTG_LTA.equals("-") && KTG_LTL.equals("-") && KTG_LTL2.equals("-") &&
                        KTG_TPA.equals("-") && KTG_TPA2.equals("-") && KTG_TPA3.equals("-")){
                    KTG_LTAA = "-";
                    holder.lta_back.setBackground(a6A6Activity.getResources().getDrawable(R.drawable.null_style));
                } else {
                    KTG_LTAA = "LF";
                    holder.lta_back.setBackground(a6A6Activity.getResources().getDrawable(R.drawable.success_style));
                }
            } else {
                KTG_LTAA = "LS";
                holder.lta_back.setBackground(a6A6Activity.getResources().getDrawable(R.drawable.failed_style));
            }
            holder.lta_hasil.setText(KTG_LTAA);
        } catch (Exception e){
            Toast.makeText(a6A6Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            if(currentItem.getDLA() == -1){
                DEV_DLA = -1;
                KTG_DLA = "-";
                holder.dla_data.setText("-");
                holder.dla_dev.setText("-");
                holder.dla_hasil.setText(KTG_DLA);
                holder.dla_back.setBackground(a6A6Activity.getResources().getDrawable(R.drawable.null_style));
            } else {
                if(currentItem.getDLA() >= 0.2 && currentItem.getDLA() <= 0.3){
                    DEV_DLA = 0;
                    KTG_DLA = "LF";
                    holder.dla_back.setBackground(a6A6Activity.getResources().getDrawable(R.drawable.success_style));
                } else {
                    if(currentItem.getDLA() < 0.2){
                        DEV_DLA = (currentItem.getDLA() - 0.2)/0.2;
                    } else {
                        DEV_DLA = (currentItem.getDLA() - 0.3)/0.3;
                    }
                    DEV_DLA = DEV_DLA * 100;
                    if(DEV_DLA < 0){
                        DEV_DLA = DEV_DLA * -1;
                    }
                    if(DEV_DLA > 100){
                        DEV_DLA = 100;
                    }
                    DEV_DLA = Double.parseDouble(df.format(DEV_DLA).replace(",", "."));
                    KTG_DLA = "LS";
                    holder.dla_back.setBackground(a6A6Activity.getResources().getDrawable(R.drawable.failed_style));
                }
                holder.dla_data.setText(String.valueOf(currentItem.getDLA())+" m");
                holder.dla_dev.setText(String.valueOf(DEV_DLA)+"%");
                holder.dla_hasil.setText(KTG_DLA);
            }
        } catch (Exception e){
            Toast.makeText(a6A6Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            if(currentItem.getICL() == -1){
                DEV_ICL = -1;
                KTG_ICL = "-";
                holder.icl_data.setText("-");
                holder.icl_dev.setText("-");
                holder.icl_hasil.setText(KTG_ICL);
                holder.icl_back.setBackground(a6A6Activity.getResources().getDrawable(R.drawable.null_style));
            } else {
                DEV_ICL = STD - currentItem.getICL();
                if(DEV_ICL == 0){
                    KTG_ICL = "LF";
                    holder.icl_back.setBackground(a6A6Activity.getResources().getDrawable(R.drawable.success_style));
                } else {
                    KTG_ICL = "LS";
                    holder.icl_back.setBackground(a6A6Activity.getResources().getDrawable(R.drawable.failed_style));
                }
                holder.icl_data.setText(String.valueOf(currentItem.getICL())+"%");
                holder.icl_dev.setText(String.valueOf(DEV_ICL)+"%");
                holder.icl_hasil.setText(KTG_ICL);
            }
        } catch (Exception e){
            Toast.makeText(a6A6Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            if(currentItem.getKAA() == -1){
                DEV_KAA = -1;
                KTG_KAA = "-";
                holder.kaa_data.setText("-");
                holder.kaa_dev.setText("-");
                holder.kaa_hasil.setText(KTG_ICL);
                holder.kaa_back.setBackground(a6A6Activity.getResources().getDrawable(R.drawable.null_style));
            } else {
                DEV_KAA = STD - currentItem.getKAA();
                if(DEV_KAA == 0){
                    KTG_KAA = "LF";
                    holder.kaa_back.setBackground(a6A6Activity.getResources().getDrawable(R.drawable.success_style));
                } else {
                    KTG_KAA = "LS";
                    holder.kaa_back.setBackground(a6A6Activity.getResources().getDrawable(R.drawable.failed_style));
                }
                holder.kaa_data.setText(String.valueOf(currentItem.getKAA())+"%");
                holder.kaa_dev.setText(String.valueOf(DEV_KAA)+"%");
                holder.kaa_hasil.setText(KTG_KAA);
            }
        } catch (Exception e){
            Toast.makeText(a6A6Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            if((KTG_LTAA.equals("LF") || KTG_LTAA.equals("-")) && (KTG_DLA.equals("LF") || KTG_DLA.equals("-")) &&
                    (KTG_ICL.equals("LF") || KTG_ICL.equals("-")) && (KTG_KAA.equals("LF") || KTG_KAA.equals("-"))){

                if(KTG_LTAA.equals("-") && KTG_DLA.equals("-") && KTG_ICL.equals("-") && KTG_KAA.equals("-")){
                    KTG_KLF = "Tidak Dinilai";
                } else {
                    KTG_KLF = "LF";
                }

            } else {
                KTG_KLF = "LS";
            }
        } catch (Exception e){
            Toast.makeText(a6A6Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            holder.rec_data.setText(currentItem.getREC());
        } catch (Exception e){
            Toast.makeText(a6A6Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            holder.FT1.setImageBitmap(BitmapFactory.decodeFile(currentItem.getDIR1()));
        } catch (Exception e){
            Toast.makeText(a6A6Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            holder.FT2.setImageBitmap(BitmapFactory.decodeFile(currentItem.getDIR2()));
        } catch (Exception e){
            Toast.makeText(a6A6Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            if(currentItem.getUPLOAD().equals("F")){
                a6A6Activity.FAB_UPLOAD.hide();
                a6A6Activity.UPLOAD.setImageResource(R.drawable.ic_file_upload_white_18dp);
                a6A6Activity.FAB_UPLOAD.setEnabled(true);
            } else {
                a6A6Activity.UPLOAD.setImageResource(R.drawable.ic_done_white_18dp);
                a6A6Activity.FAB_UPLOAD.setEnabled(false);
            }
        } catch (Exception e){
            Toast.makeText(a6A6Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            a6A6Activity.ID = currentItem.getID();
            a6A6Activity.LTA_TXT = currentItem.getLTA();
            a6A6Activity.LTL_TXT = currentItem.getLTL();
            a6A6Activity.LTL2_TXT = currentItem.getLTL2();
            a6A6Activity.TPA_TXT = currentItem.getTPA();
            a6A6Activity.TPA2_TXT = currentItem.getTPA2();
            a6A6Activity.TPA3_TXT = currentItem.getTPA3();
            a6A6Activity.DLA_TXT = currentItem.getDLA();
            a6A6Activity.ICL_TXT = currentItem.getICL();
            a6A6Activity.KAA_TXT = currentItem.getKAA();
            a6A6Activity.REC_TXT = currentItem.getREC();
            a6A6Activity.DIR1 = currentItem.getDIR1();
            a6A6Activity.DIR2 = currentItem.getDIR2();

            a6A6Activity.DEV_LTA = DEV_LTA;
            a6A6Activity.DEV_LTL = DEV_LTL;
            a6A6Activity.DEV_LTL2 = DEV_LTL2;
            a6A6Activity.DEV_TPA = DEV_TPA;
            a6A6Activity.DEV_TPA2 = DEV_TPA2;
            a6A6Activity.DEV_TPA3 = DEV_TPA3;
            a6A6Activity.DEV_DLA = DEV_DLA;
            a6A6Activity.DEV_ICL = DEV_ICL;
            a6A6Activity.DEV_KAA = DEV_KAA;

            a6A6Activity.KTG_LTAA = KTG_LTAA;
            a6A6Activity.KTG_DLA = KTG_DLA;
            a6A6Activity.KTG_ICL = KTG_ICL;
            a6A6Activity.KTG_KAA = KTG_KAA;
            a6A6Activity.KTG_KLF = KTG_KLF;

            if(KTG_KLF.equals("LS")){
                a6A6Activity.klf.setText(KTG_KLF);
                a6A6Activity.klf.setBackground(a6A6Activity.getResources().getDrawable(R.drawable.failed_style));
                Animation animation = AnimationUtils.loadAnimation(a6A6Activity.getApplicationContext(), R.anim.recycle_bottom);
                a6A6Activity.klf.startAnimation(animation);
            } else if(KTG_KLF.equals("Tidak Dinilai")){
                a6A6Activity.klf.setText("Tidak Dinilai");
                a6A6Activity.klf.setBackground(a6A6Activity.getResources().getDrawable(R.drawable.null_style));
                Animation animation = AnimationUtils.loadAnimation(a6A6Activity.getApplicationContext(), R.anim.recycle_bottom);
                a6A6Activity.klf.startAnimation(animation);
            } else {
                a6A6Activity.klf.setText(KTG_KLF);
                a6A6Activity.klf.setBackground(a6A6Activity.getResources().getDrawable(R.drawable.success_style));
                Animation animation = AnimationUtils.loadAnimation(a6A6Activity.getApplicationContext(), R.anim.recycle_bottom);
                a6A6Activity.klf.startAnimation(animation);
            }
        } catch (Exception e){
            Toast.makeText(a6A6Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public int getItemCount() {
        return mdataList.size();
    }

}
