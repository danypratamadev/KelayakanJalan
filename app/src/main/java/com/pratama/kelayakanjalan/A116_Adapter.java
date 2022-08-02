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

public class A116_Adapter extends RecyclerView.Adapter<A116_Adapter.A116ViewHolder> {

    private A116Activity a116Activity;
    private List<A116_Class> mdataList;
    private double DEV_JTJ, DEV_TVM, DEV_KTV, DEV_JTV, DEV_TTR, DEV_JTJ2, DEV_MTB, DEV_TMT, DEV_BGT, STD = 100;
    private String KTG_JTJ, KTG_TVM, KTG_KTV, KTG_JTV, KTG_TTR, KTG_RPM, KTG_JTJ2, KTG_MTB, KTG_TMT, KTG_BGT, KTG_PHB, KTG_KLF;
    private static DecimalFormat df = new DecimalFormat("0.0");

    public static class A116ViewHolder extends RecyclerView.ViewHolder {
        private TextView jtj_data, jtj_dev, jtj_hasil, tvm_data, tvm_dev, tvm_hasil, ktv_data, ktv_dev, ktv_hasil, jtv_data, jtv_dev, jtv_hasil,
                ttr_data, ttr_dev, ttr_hasil, jtj2_data, jtj2_dev, jtj2_hasil, mtb_data, mtb_dev, mtb_hasil, tmt_data, tmt_dev, tmt_hasil,
                bgt_data, bgt_dev, bgt_hasil, rec_data;
        private LinearLayout jtj_back, tvm_back, ktv_back, jtv_back, ttr_back, jtj2_back, mtb_back, tmt_back, bgt_back;
        private ImageView FT1, FT2, FT3;

        public A116ViewHolder(View itemView) {
            super(itemView);

            jtj_data = (TextView) itemView.findViewById(R.id.jtj_data);
            jtj_dev = (TextView) itemView.findViewById(R.id.jtj_dev);
            jtj_hasil = (TextView) itemView.findViewById(R.id.jtj_hasil);
            tvm_data = (TextView) itemView.findViewById(R.id.tvm_data);
            tvm_dev = (TextView) itemView.findViewById(R.id.tvm_dev);
            tvm_hasil = (TextView) itemView.findViewById(R.id.tvm_hasil);
            ktv_data = (TextView) itemView.findViewById(R.id.ktv_data);
            ktv_dev = (TextView) itemView.findViewById(R.id.ktv_dev);
            ktv_hasil = (TextView) itemView.findViewById(R.id.ktv_hasil);
            jtv_data = (TextView) itemView.findViewById(R.id.jtv_data);
            jtv_dev = (TextView) itemView.findViewById(R.id.jtv_dev);
            jtv_hasil = (TextView) itemView.findViewById(R.id.jtv_hasil);
            ttr_data = (TextView) itemView.findViewById(R.id.ttr_data);
            ttr_dev = (TextView) itemView.findViewById(R.id.ttr_dev);
            ttr_hasil = (TextView) itemView.findViewById(R.id.ttr_hasil);
            jtj2_data = (TextView) itemView.findViewById(R.id.jtj2_data);
            jtj2_dev = (TextView) itemView.findViewById(R.id.jtj2_dev);
            jtj2_hasil = (TextView) itemView.findViewById(R.id.jtj2_hasil);
            mtb_data = (TextView) itemView.findViewById(R.id.mtb_data);
            mtb_dev = (TextView) itemView.findViewById(R.id.mtb_dev);
            mtb_hasil = (TextView) itemView.findViewById(R.id.mtb_hasil);
            tmt_data = (TextView) itemView.findViewById(R.id.tmt_data);
            tmt_dev = (TextView) itemView.findViewById(R.id.tmt_dev);
            tmt_hasil = (TextView) itemView.findViewById(R.id.tmt_hasil);
            bgt_data = (TextView) itemView.findViewById(R.id.bgt_data);
            bgt_dev = (TextView) itemView.findViewById(R.id.bgt_dev);
            bgt_hasil = (TextView) itemView.findViewById(R.id.bgt_hasil);
            rec_data = (TextView) itemView.findViewById(R.id.rec_data);

            FT1 = (ImageView) itemView.findViewById(R.id.FT1);
            FT2 = (ImageView) itemView.findViewById(R.id.FT2);
            FT3 = (ImageView) itemView.findViewById(R.id.FT3);

            jtj_back = (LinearLayout) itemView.findViewById(R.id.jtj_back);
            tvm_back = (LinearLayout) itemView.findViewById(R.id.tvm_back);
            ktv_back = (LinearLayout) itemView.findViewById(R.id.ktv_back);
            jtv_back = (LinearLayout) itemView.findViewById(R.id.jtv_back);
            ttr_back = (LinearLayout) itemView.findViewById(R.id.ttr_back);
            jtj2_back = (LinearLayout) itemView.findViewById(R.id.jtj2_back);
            mtb_back = (LinearLayout) itemView.findViewById(R.id.mtb_back);
            tmt_back = (LinearLayout) itemView.findViewById(R.id.tmt_back);
            bgt_back = (LinearLayout) itemView.findViewById(R.id.bgt_back);

        }
    }

    public A116_Adapter(A116Activity a116Activity, List<A116_Class> dataList) {
        this.a116Activity = a116Activity;
        this.mdataList = dataList;
    }

    @NonNull
    @Override
    public A116_Adapter.A116ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.a116_layout, parent, false);
        A116_Adapter.A116ViewHolder rsu = new A116_Adapter.A116ViewHolder(view);
        return rsu;
    }

    @Override
    public void onBindViewHolder(@NonNull A116_Adapter.A116ViewHolder holder, int position) {

        final A116_Class currentItem = mdataList.get(position);

        //Rel Pengaman

        try {
            if(currentItem.getJTJ() == -1){
                holder.jtj_data.setText("-");
                DEV_JTJ = -1;
                KTG_JTJ = "-";
                holder.jtj_dev.setText("-");
                holder.jtj_hasil.setText(KTG_JTJ);
                holder.jtj_back.setBackground(a116Activity.getResources().getDrawable(R.drawable.null_style));
            } else {
                holder.jtj_data.setText(String.valueOf(currentItem.getJTJ())+" m");
                if(currentItem.getJTJ() >= 0.6){
                    DEV_JTJ = 0;
                    KTG_JTJ = "LF";
                    holder.jtj_back.setBackground(a116Activity.getResources().getDrawable(R.drawable.success_style));
                } else {
                    DEV_JTJ = (currentItem.getJTJ() - 0.6)/0.6;
                    DEV_JTJ = (DEV_JTJ * 100);
                    if(DEV_JTJ < 0){
                        DEV_JTJ = DEV_JTJ * -1;
                    }
                    if(DEV_JTJ > 100){
                        DEV_JTJ = 100;
                    }
                    DEV_JTJ = Double.parseDouble(df.format(DEV_JTJ).replace(",", "."));
                    KTG_JTJ = "LS";
                    holder.jtj_back.setBackground(a116Activity.getResources().getDrawable(R.drawable.failed_style));
                }
                holder.jtj_dev.setText(String.valueOf(DEV_JTJ)+"%");
                holder.jtj_hasil.setText(KTG_JTJ);
            }
        } catch (Exception e){
            Toast.makeText(a116Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            if(currentItem.getTVM() == -1){
                holder.tvm_data.setText("-");
                DEV_TVM = -1;
                KTG_TVM = "-";
                holder.tvm_dev.setText("-");
                holder.tvm_hasil.setText(KTG_TVM);
                holder.tvm_back.setBackground(a116Activity.getResources().getDrawable(R.drawable.null_style));
            } else {
                holder.tvm_data.setText(String.valueOf(currentItem.getTVM())+" m");
                if(currentItem.getTVM() >= 0.7){
                    DEV_TVM = 0;
                    KTG_TVM = "LF";
                    holder.tvm_back.setBackground(a116Activity.getResources().getDrawable(R.drawable.success_style));
                } else {
                    DEV_TVM = (currentItem.getTVM() - 0.7)/0.7;
                    DEV_TVM = (DEV_TVM * 100);
                    if(DEV_TVM < 0){
                        DEV_TVM = DEV_TVM * -1;
                    }
                    if(DEV_TVM > 100){
                        DEV_TVM = 100;
                    }
                    DEV_TVM = Double.parseDouble(df.format(DEV_TVM).replace(",", "."));
                    KTG_TVM = "LS";
                    holder.tvm_back.setBackground(a116Activity.getResources().getDrawable(R.drawable.failed_style));
                }
                holder.tvm_dev.setText(String.valueOf(DEV_TVM)+"%");
                holder.tvm_hasil.setText(KTG_TVM);
            }
        } catch (Exception e){
            Toast.makeText(a116Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            if(currentItem.getKTV() == -1){
                holder.ktv_data.setText("-");
                DEV_KTV = -1;
                KTG_KTV = "-";
                holder.ktv_dev.setText("-");
                holder.ktv_hasil.setText(KTG_KTV);
                holder.ktv_back.setBackground(a116Activity.getResources().getDrawable(R.drawable.null_style));
            } else {
                holder.ktv_data.setText(String.valueOf(currentItem.getKTV())+" m");
                if(currentItem.getKTV() >= 0.9){
                    DEV_KTV = 0;
                    KTG_KTV = "LF";
                    holder.ktv_back.setBackground(a116Activity.getResources().getDrawable(R.drawable.success_style));
                } else {
                    DEV_KTV = (currentItem.getKTV() - 0.9)/0.9;
                    DEV_KTV = (DEV_KTV * 100);
                    if(DEV_KTV < 0){
                        DEV_KTV = DEV_KTV * -1;
                    }
                    if(DEV_KTV > 100){
                        DEV_KTV = 100;
                    }
                    DEV_KTV = Double.parseDouble(df.format(DEV_KTV).replace(",", "."));
                    KTG_KTV = "LS";
                    holder.ktv_back.setBackground(a116Activity.getResources().getDrawable(R.drawable.failed_style));
                }
                holder.ktv_dev.setText(String.valueOf(DEV_KTV)+"%");
                holder.ktv_hasil.setText(KTG_KTV);
            }
        } catch (Exception e){
            Toast.makeText(a116Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            if(currentItem.getJTV() == -1){
                holder.jtv_data.setText("-");
                DEV_JTV = -1;
                KTG_JTV = "-";
                holder.jtv_dev.setText("-");
                holder.jtv_hasil.setText(KTG_JTV);
                holder.jtv_back.setBackground(a116Activity.getResources().getDrawable(R.drawable.null_style));
            } else {
                holder.jtv_data.setText(String.valueOf(currentItem.getJTV())+" m");
                if(currentItem.getJTV() > 0 && currentItem.getJTV() <= 4){
                    DEV_JTV = 0;
                    KTG_JTV = "LF";
                    holder.jtv_back.setBackground(a116Activity.getResources().getDrawable(R.drawable.success_style));
                } else {
                    DEV_JTV = (currentItem.getJTV() - 4)/4;
                    DEV_JTV = (DEV_JTV * 100);
                    if(DEV_JTV < 0){
                        DEV_JTV = DEV_JTV * -1;
                    }
                    if(DEV_JTV > 100){
                        DEV_JTV = 100;
                    }
                    DEV_JTV = Double.parseDouble(df.format(DEV_JTV).replace(",", "."));
                    KTG_JTV = "LS";
                    holder.jtv_back.setBackground(a116Activity.getResources().getDrawable(R.drawable.failed_style));
                }
                holder.jtv_dev.setText(String.valueOf(DEV_JTV)+"%");
                holder.jtv_hasil.setText(KTG_JTV);
            }
        } catch (Exception e){
            Toast.makeText(a116Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            if(currentItem.getTTR() == -1){
                holder.ttr_data.setText("-");
                DEV_TTR = -1;
                KTG_TTR = "-";
                holder.ttr_dev.setText("-");
                holder.ttr_hasil.setText(KTG_TTR);
                holder.ttr_back.setBackground(a116Activity.getResources().getDrawable(R.drawable.null_style));
            } else {
                holder.ttr_data.setText(String.valueOf(currentItem.getTTR())+"%");
                DEV_TTR = STD - currentItem.getTTR();
                if(DEV_TTR == 0){
                    KTG_TTR = "LF";
                    holder.ttr_back.setBackground(a116Activity.getResources().getDrawable(R.drawable.success_style));
                } else {
                    KTG_TTR = "LS";
                    holder.ttr_back.setBackground(a116Activity.getResources().getDrawable(R.drawable.failed_style));
                }
                holder.ttr_dev.setText(String.valueOf(DEV_TTR)+"%");
                holder.ttr_hasil.setText(KTG_TTR);
            }
        } catch (Exception e){
            Toast.makeText(a116Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        //Penghalang Beton

        try {
            if(currentItem.getJTJ2() == -1){
                holder.jtj2_data.setText("-");
                DEV_JTJ2 = -1;
                KTG_JTJ2 = "-";
                holder.jtj2_dev.setText("-");
                holder.jtj2_hasil.setText(KTG_JTJ2);
                holder.jtj2_back.setBackground(a116Activity.getResources().getDrawable(R.drawable.null_style));
            } else {
                holder.jtj2_data.setText(String.valueOf(currentItem.getJTJ2())+" m");
                if(currentItem.getJTJ2() >= 0.6){
                    DEV_JTJ2 = 0;
                    KTG_JTJ2 = "LF";
                    holder.jtj2_back.setBackground(a116Activity.getResources().getDrawable(R.drawable.success_style));
                } else {
                    DEV_JTJ2 = (currentItem.getJTJ2() - 0.6)/0.6;
                    DEV_JTJ2 = (DEV_JTJ2 * 100);
                    if(DEV_JTJ2 < 0){
                        DEV_JTJ2 = DEV_JTJ2 * -1;
                    }
                    if(DEV_JTJ2 > 100){
                        DEV_JTJ2 = 100;
                    }
                    DEV_JTJ2 = Double.parseDouble(df.format(DEV_JTJ2).replace(",", "."));
                    KTG_JTJ2 = "LS";
                    holder.jtj2_back.setBackground(a116Activity.getResources().getDrawable(R.drawable.failed_style));
                }
                holder.jtj2_dev.setText(String.valueOf(DEV_JTJ2)+"%");
                holder.jtj2_hasil.setText(KTG_JTJ2);
            }
        } catch (Exception e){
            Toast.makeText(a116Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            if(currentItem.getMTB().equals("-")){
                holder.mtb_data.setText("-");
                DEV_MTB = -1;
                KTG_MTB = "-";
                holder.mtb_dev.setText("-");
                holder.mtb_hasil.setText(KTG_MTB);
                holder.mtb_back.setBackground(a116Activity.getResources().getDrawable(R.drawable.null_style));
            } else {
                holder.mtb_data.setText(currentItem.getMTB());
                if(currentItem.getMTB().equals("K-350")){
                    DEV_MTB = 0;
                    KTG_MTB = "LF";
                    holder.mtb_back.setBackground(a116Activity.getResources().getDrawable(R.drawable.success_style));
                } else {
                    DEV_MTB = 100;
                    KTG_MTB = "LS";
                    holder.mtb_back.setBackground(a116Activity.getResources().getDrawable(R.drawable.failed_style));
                }
                holder.mtb_dev.setText(String.valueOf(DEV_MTB)+"%");
                holder.mtb_hasil.setText(KTG_MTB);
            }
        } catch (Exception e){
            Toast.makeText(a116Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            if(currentItem.getTMT() == -1){
                holder.tmt_data.setText("-");
                DEV_TMT = -1;
                KTG_TMT = "-";
                holder.tmt_dev.setText("-");
                holder.tmt_hasil.setText(KTG_TMT);
                holder.tmt_back.setBackground(a116Activity.getResources().getDrawable(R.drawable.null_style));
            } else {
                holder.tmt_data.setText(String.valueOf(currentItem.getTMT())+" m");
                if(currentItem.getTMT() >= 0.85){
                    DEV_TMT = 0;
                    KTG_TMT = "LF";
                    holder.tmt_back.setBackground(a116Activity.getResources().getDrawable(R.drawable.success_style));
                } else {
                    DEV_TMT = (currentItem.getTMT() - 0.85)/0.85;
                    DEV_TMT = (DEV_TMT * 100);
                    if(DEV_TMT < 0){
                        DEV_TMT = DEV_TMT * -1;
                    }
                    if(DEV_TMT > 100){
                        DEV_TMT = 100;
                    }
                    DEV_TMT = Double.parseDouble(df.format(DEV_TMT).replace(",", "."));
                    KTG_TMT = "LS";
                    holder.tmt_back.setBackground(a116Activity.getResources().getDrawable(R.drawable.failed_style));
                }
                holder.tmt_dev.setText(String.valueOf(DEV_TMT)+"%");
                holder.tmt_hasil.setText(KTG_TMT);
            }
        } catch (Exception e){
            Toast.makeText(a116Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            if(currentItem.getBGT() == -1){
                holder.bgt_data.setText("-");
                DEV_BGT = -1;
                KTG_BGT = "-";
                holder.bgt_dev.setText("-");
                holder.bgt_hasil.setText(KTG_BGT);
                holder.bgt_back.setBackground(a116Activity.getResources().getDrawable(R.drawable.null_style));
            } else {
                holder.bgt_data.setText(String.valueOf(currentItem.getBGT())+" m");
                if(currentItem.getBGT() > 0 && currentItem.getBGT() <= 0.18){
                    DEV_BGT = 0;
                    KTG_BGT = "LF";
                    holder.bgt_back.setBackground(a116Activity.getResources().getDrawable(R.drawable.success_style));
                } else {
                    DEV_BGT = (currentItem.getBGT() - 0.18)/0.18;
                    DEV_BGT = (DEV_BGT * 100);
                    if(DEV_BGT < 0){
                        DEV_BGT = DEV_BGT * -1;
                    }
                    if(DEV_BGT > 100){
                        DEV_BGT = 100;
                    }
                    DEV_BGT = Double.parseDouble(df.format(DEV_BGT).replace(",", "."));
                    KTG_BGT = "LS";
                    holder.bgt_back.setBackground(a116Activity.getResources().getDrawable(R.drawable.failed_style));
                }
                holder.bgt_dev.setText(String.valueOf(DEV_BGT)+"%");
                holder.bgt_hasil.setText(KTG_BGT);
            }
        } catch (Exception e){
            Toast.makeText(a116Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            if((KTG_JTJ.equals("LF") || KTG_JTJ.equals("-")) && (KTG_TVM.equals("LF") || KTG_TVM.equals("-"))
                    && (KTG_KTV.equals("LF") || KTG_KTV.equals("-")) && (KTG_JTV.equals("LF") || KTG_JTV.equals("-"))
                    && (KTG_TTR.equals("LF") || KTG_TTR.equals("-"))){

                if(KTG_JTJ.equals("-") && KTG_TVM.equals("-") && KTG_KTV.equals("-") && KTG_JTV.equals("-") &&
                        KTG_TTR.equals("-")){
                    KTG_RPM = "-";
                } else {
                    KTG_RPM = "LF";
                }

            } else {
                KTG_RPM ="LS";
            }
        } catch (Exception e){
            Toast.makeText(a116Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            if((KTG_JTJ2.equals("LF") || KTG_JTJ2.equals("-")) && (KTG_MTB.equals("LF") || KTG_MTB.equals("-")) &&
                    (KTG_TMT.equals("LF") || KTG_TMT.equals("-")) && (KTG_BGT.equals("LF") || KTG_BGT.equals("-"))){

                if(KTG_JTJ2.equals("-") && KTG_MTB.equals("-") && KTG_TMT.equals("-") && KTG_BGT.equals("-")){
                    KTG_PHB = "-";
                } else {
                    KTG_PHB = "LF";
                }

            } else {
                KTG_PHB = "LS";
            }
        } catch (Exception e){
            Toast.makeText(a116Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        //KLF

        try {
            if((KTG_JTJ.equals("LF") || KTG_JTJ.equals("-")) && (KTG_TVM.equals("LF") || KTG_TVM.equals("-"))
                    && (KTG_KTV.equals("LF") || KTG_KTV.equals("-")) && (KTG_JTV.equals("LF") || KTG_JTV.equals("-"))
                    && (KTG_TTR.equals("LF") || KTG_TTR.equals("-")) && (KTG_JTJ2.equals("LF") || KTG_JTJ2.equals("-"))
                    && (KTG_MTB.equals("LF") || KTG_MTB.equals("-")) && (KTG_TMT.equals("LF") || KTG_TMT.equals("-"))
                    && (KTG_BGT.equals("LF") || KTG_BGT.equals("-"))){

                if(KTG_JTJ.equals("-") && KTG_TVM.equals("-") && KTG_KTV.equals("-") && KTG_JTV.equals("-") &&
                        KTG_TTR.equals("-") && KTG_JTJ2.equals("-") && KTG_MTB.equals("-") && KTG_TMT.equals("-") && KTG_BGT.equals("-")){
                    KTG_KLF = "Tidak Dinilai";
                } else {
                    KTG_KLF = "LF";
                }

            } else {
                KTG_KLF = "LS";
            }
        } catch (Exception e){
            Toast.makeText(a116Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            holder.rec_data.setText(currentItem.getREC());
        } catch (Exception e){
            Toast.makeText(a116Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            holder.FT1.setImageBitmap(BitmapFactory.decodeFile(currentItem.getDIR1()));
        } catch (Exception e){
            Toast.makeText(a116Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            holder.FT2.setImageBitmap(BitmapFactory.decodeFile(currentItem.getDIR2()));
        } catch (Exception e){
            Toast.makeText(a116Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            holder.FT3.setImageBitmap(BitmapFactory.decodeFile(currentItem.getDIR3()));
        } catch (Exception e){
            Toast.makeText(a116Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        //FAB UPLOAD

        try {
            if(currentItem.getUPLOAD().equals("F")){
                a116Activity.FAB_UPLOAD.hide();
                a116Activity.UPLOAD.setImageResource(R.drawable.ic_file_upload_white_18dp);
                a116Activity.FAB_UPLOAD.setEnabled(true);
            } else {
                a116Activity.UPLOAD.setImageResource(R.drawable.ic_done_white_18dp);
                a116Activity.FAB_UPLOAD.setEnabled(false);
            }
        } catch (Exception e){
            Toast.makeText(a116Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        //Kirim ke Activity

        try {
            a116Activity.ID = currentItem.getID();
            a116Activity.JTJ_TXT = currentItem.getJTJ();
            a116Activity.TVM_TXT = currentItem.getTVM();
            a116Activity.KTV_TXT = currentItem.getKTV();
            a116Activity.JTV_TXT = currentItem.getJTV();
            a116Activity.TTR_TXT = currentItem.getTTR();
            a116Activity.JTJ2_TXT = currentItem.getJTJ2();
            a116Activity.MTB_TXT = currentItem.getMTB();
            a116Activity.TMT_TXT = currentItem.getTMT();
            a116Activity.BGT_TXT = currentItem.getBGT();
            a116Activity.REC_TXT = currentItem.getREC();

            a116Activity.DEV_JTJ = DEV_JTJ;
            a116Activity.DEV_TVM = DEV_TVM;
            a116Activity.DEV_KTV = DEV_KTV;
            a116Activity.DEV_JTV = DEV_JTV;
            a116Activity.DEV_TTR = DEV_TTR;
            a116Activity.DEV_JTJ2 = DEV_JTJ2;
            a116Activity.DEV_MTB = DEV_MTB;
            a116Activity.DEV_TMT = DEV_TMT;
            a116Activity.DEV_BGT = DEV_BGT;

            a116Activity.KTG_RPM = KTG_RPM;
            a116Activity.KTG_PHB = KTG_PHB;
            a116Activity.KTG_KLF = KTG_KLF;

            a116Activity.DIR1 = currentItem.getDIR1();
            a116Activity.DIR2 = currentItem.getDIR2();
            a116Activity.DIR3 = currentItem.getDIR3();

            if(KTG_KLF.equals("LS")){
                a116Activity.klf.setText(KTG_KLF);
                a116Activity.klf.setBackground(a116Activity.getResources().getDrawable(R.drawable.failed_style));
                Animation animation = AnimationUtils.loadAnimation(a116Activity.getApplicationContext(), R.anim.recycle_bottom);
                a116Activity.klf.startAnimation(animation);
            } else if(KTG_KLF.equals("Tidak Dinilai")){
                a116Activity.klf.setText("Tidak Dinilai");
                a116Activity.klf.setBackground(a116Activity.getResources().getDrawable(R.drawable.null_style));
                Animation animation = AnimationUtils.loadAnimation(a116Activity.getApplicationContext(), R.anim.recycle_bottom);
                a116Activity.klf.startAnimation(animation);
            } else {
                a116Activity.klf.setText(KTG_KLF);
                a116Activity.klf.setBackground(a116Activity.getResources().getDrawable(R.drawable.success_style));
                Animation animation = AnimationUtils.loadAnimation(a116Activity.getApplicationContext(), R.anim.recycle_bottom);
                a116Activity.klf.startAnimation(animation);
            }
        } catch (Exception e){
            Toast.makeText(a116Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public int getItemCount() {
        return mdataList.size();
    }
}
