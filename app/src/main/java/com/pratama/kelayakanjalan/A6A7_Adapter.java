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

public class A6A7_Adapter extends RecyclerView.Adapter<A6A7_Adapter.A6A7ViewHolder> {

    private A6A7Activity a6A7Activity;
    private List<A6A7_Class> mdataList;
    private double DEV_TPK, DEV_RMP, DEV_RMP2, DEV_PPB, DEV_LPP, DEV_LPP2, DEV_LPT, DEV_LPT2, DEV_LPJ, DEV_PCT, DEV_PCT2, DEV_PGD,
            DEV_PTB, DEV_PTB2, DEV_FPP, DEV_FPP2, DEV_FJP, DEV_FJP2, DEV_FPR, STD = 100;
    private String KTG_TPK, KTG_RMP, KTG_RMP2, KTG_RMPP, KTG_PPB, KTG_LPP, KTG_LPP2, KTG_LPT, KTG_LPT2, KTG_LPJ, KTG_LPJJ, KTG_PCT, KTG_PCT2, KTG_PGD, KTG_PPPP,
            KTG_PTB, KTG_PTB2, KTG_FPP, KTG_FPP2, KTG_FJP, KTG_FJP2, KTG_FPR, KTG_FPC, KTG_KLF;
    private static DecimalFormat df = new DecimalFormat("0.0");

    public class A6A7ViewHolder extends RecyclerView.ViewHolder {

        private TextView tpk_data, rmp_data, rmp2_data, ppb_data, lpp_data, lpp2_data, lpt_data, lpt2_data, lpj_data, pct_data, pct2_data, pgd_data, ptb_data, ptb2_data,
                fpp_data, fpp2_data, fjp_data, fjp2_data, fpr_data, tpk_dev, rmp_dev, rmp2_dev, ppb_dev, lpp_dev, lpp2_dev, lpt_dev, lpt2_dev, lpj_dev, pct_dev, pct2_dev, pgd_dev,
                ptb_dev, ptb2_dev, fpp_dev, fpp2_dev, fjp_dev, fjp2_dev, fpr_dev, tpk_hasil, rmp_hasil, ppb_hasil, lpj_hasil, pct_hasil, ptb_hasil, rec_data;

        private LinearLayout tpk_back, rmp_back, ppb_back, lpj_back, pct_back, ptb_back;
        private ImageView FT1, FT2, FT3, FT4;

        public A6A7ViewHolder(@NonNull View itemView) {
            super(itemView);

            tpk_data = (TextView) itemView.findViewById(R.id.tpk_data);
            tpk_dev = (TextView) itemView.findViewById(R.id.tpk_dev);
            tpk_hasil = (TextView) itemView.findViewById(R.id.tpk_hasil);

            rmp_data = (TextView) itemView.findViewById(R.id.rmp_data);
            rmp_dev = (TextView) itemView.findViewById(R.id.rmp_dev);
            rmp2_data = (TextView) itemView.findViewById(R.id.rmp2_data);
            rmp2_dev = (TextView) itemView.findViewById(R.id.rmp2_dev);
            rmp_hasil = (TextView) itemView.findViewById(R.id.rmp_hasil);

            ppb_data = (TextView) itemView.findViewById(R.id.ppb_data);
            ppb_dev = (TextView) itemView.findViewById(R.id.ppb_dev);
            ppb_hasil = (TextView) itemView.findViewById(R.id.ppb_hasil);

            lpp_data = (TextView) itemView.findViewById(R.id.lpp_data);
            lpp_dev = (TextView) itemView.findViewById(R.id.lpp_dev);
            lpp2_data = (TextView) itemView.findViewById(R.id.lpp2_data);
            lpp2_dev = (TextView) itemView.findViewById(R.id.lpp2_dev);
            lpt_data = (TextView) itemView.findViewById(R.id.lpt_data);
            lpt_dev = (TextView) itemView.findViewById(R.id.lpt_dev);
            lpt2_data = (TextView) itemView.findViewById(R.id.lpt2_data);
            lpt2_dev = (TextView) itemView.findViewById(R.id.lpt2_dev);
            lpj_data = (TextView) itemView.findViewById(R.id.lpj_data);
            lpj_dev = (TextView) itemView.findViewById(R.id.lpj_dev);
            lpj_hasil = (TextView) itemView.findViewById(R.id.lpj_hasil);

            pct_data = (TextView) itemView.findViewById(R.id.pct_data);
            pct_dev = (TextView) itemView.findViewById(R.id.pct_dev);
            pct2_data = (TextView) itemView.findViewById(R.id.pct2_data);
            pct2_dev = (TextView) itemView.findViewById(R.id.pct2_dev);
            pgd_data = (TextView) itemView.findViewById(R.id.pgd_data);
            pgd_dev = (TextView) itemView.findViewById(R.id.pgd_dev);
            pct_hasil = (TextView) itemView.findViewById(R.id.pct_hasil);

            ptb_data = (TextView) itemView.findViewById(R.id.ptb_data);
            ptb_dev = (TextView) itemView.findViewById(R.id.ptb_dev);
            ptb2_data = (TextView) itemView.findViewById(R.id.ptb2_data);
            ptb2_dev = (TextView) itemView.findViewById(R.id.ptb2_dev);
            fpp_data = (TextView) itemView.findViewById(R.id.fpp_data);
            fpp_dev = (TextView) itemView.findViewById(R.id.fpp_dev);
            fpp2_data = (TextView) itemView.findViewById(R.id.fpp2_data);
            fpp2_dev = (TextView) itemView.findViewById(R.id.fpp2_dev);
            fjp_data = (TextView) itemView.findViewById(R.id.fjp_data);
            fjp_dev = (TextView) itemView.findViewById(R.id.fjp_dev);
            fjp2_data = (TextView) itemView.findViewById(R.id.fjp2_data);
            fjp2_dev = (TextView) itemView.findViewById(R.id.fjp2_dev);
            fpr_data = (TextView) itemView.findViewById(R.id.fpr_data);
            fpr_dev = (TextView) itemView.findViewById(R.id.fpr_dev);
            ptb_hasil = (TextView) itemView.findViewById(R.id.ptb_hasil);

            rec_data = (TextView) itemView.findViewById(R.id.rec_data);

            FT1 = (ImageView) itemView.findViewById(R.id.FT1);
            FT2 = (ImageView) itemView.findViewById(R.id.FT2);
            FT3 = (ImageView) itemView.findViewById(R.id.FT3);
            FT4 = (ImageView) itemView.findViewById(R.id.FT4);

            tpk_back = (LinearLayout) itemView.findViewById(R.id.tpk_back);
            rmp_back = (LinearLayout) itemView.findViewById(R.id.rmp_back);
            ppb_back = (LinearLayout) itemView.findViewById(R.id.ppb_back);
            lpj_back = (LinearLayout) itemView.findViewById(R.id.lpj_back);
            pct_back = (LinearLayout) itemView.findViewById(R.id.pct_back);
            ptb_back = (LinearLayout) itemView.findViewById(R.id.ptb_back);

        }
    }

    public A6A7_Adapter(A6A7Activity a6A7Activity, List<A6A7_Class> mdataList) {
        this.a6A7Activity = a6A7Activity;
        this.mdataList = mdataList;
    }

    @NonNull
    @Override
    public A6A7_Adapter.A6A7ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.a6a7_layout, parent, false);
        A6A7_Adapter.A6A7ViewHolder a6a7 = new A6A7_Adapter.A6A7ViewHolder(view);
        return a6a7;
    }

    @Override
    public void onBindViewHolder(@NonNull A6A7_Adapter.A6A7ViewHolder holder, int position) {

        final A6A7_Class currentItem = mdataList.get(position);

        try {
            if(currentItem.getTPK() == -1){
                DEV_TPK = -1;
                KTG_TPK = "-";
                holder.tpk_data.setText("-");
                holder.tpk_dev.setText("-");
                holder.tpk_hasil.setText(KTG_TPK);
                holder.tpk_back.setBackground(a6A7Activity.getResources().getDrawable(R.drawable.null_style));
            } else {
                DEV_TPK = STD - currentItem.getTPK();
                if(DEV_TPK == 0){
                    KTG_TPK = "LF";
                    holder.tpk_back.setBackground(a6A7Activity.getResources().getDrawable(R.drawable.success_style));
                } else {
                    KTG_TPK = "LS";
                    holder.tpk_back.setBackground(a6A7Activity.getResources().getDrawable(R.drawable.failed_style));
                }
                holder.tpk_data.setText(String.valueOf(currentItem.getTPK())+"%");
                holder.tpk_dev.setText(String.valueOf(DEV_TPK)+"%");
                holder.tpk_hasil.setText(KTG_TPK);
            }
        } catch (Exception e){
            Toast.makeText(a6A7Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            if(currentItem.getRMP() == -1){
                DEV_RMP = -1;
                KTG_RMP = "-";
                holder.rmp_data.setText("-");
                holder.rmp_dev.setText("-");
            } else {
                DEV_RMP = STD - currentItem.getRMP();
                if(DEV_RMP == 0){
                    KTG_RMP = "LF";
                } else {
                    KTG_RMP = "LS";
                }
                holder.rmp_data.setText(String.valueOf(currentItem.getRMP())+"%");
                holder.rmp_dev.setText(String.valueOf(DEV_RMP)+"%");
            }
        } catch (Exception e){
            Toast.makeText(a6A7Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            if(currentItem.getRMP2() == -1){
                DEV_RMP2 = -1;
                KTG_RMP2 = "-";
                holder.rmp2_data.setText("-");
                holder.rmp2_dev.setText("-");
            } else {
                DEV_RMP2 = STD - currentItem.getRMP2();
                if(DEV_RMP2 == 0){
                    KTG_RMP2 = "LF";
                } else {
                    KTG_RMP2 = "LS";
                }
                holder.rmp2_data.setText(String.valueOf(currentItem.getRMP2())+"%");
                holder.rmp2_dev.setText(String.valueOf(DEV_RMP2)+"%");
            }
        } catch (Exception e){
            Toast.makeText(a6A7Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            if((KTG_RMP.equals("LF") || KTG_RMP.equals("-")) && (KTG_RMP2.equals("LF") || KTG_RMP2.equals("-"))){
                if(KTG_RMP.equals("-") && KTG_RMP2.equals("-")){
                    KTG_RMPP = "-";
                    holder.rmp_back.setBackground(a6A7Activity.getResources().getDrawable(R.drawable.null_style));
                } else {
                    KTG_RMPP = "LF";
                    holder.rmp_back.setBackground(a6A7Activity.getResources().getDrawable(R.drawable.success_style));
                }
            } else {
                KTG_RMPP = "LS";
                holder.rmp_back.setBackground(a6A7Activity.getResources().getDrawable(R.drawable.failed_style));
            }
            holder.rmp_hasil.setText(KTG_RMPP);
        } catch (Exception e){
            Toast.makeText(a6A7Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            if(currentItem.getPPB() == -1){
                DEV_PPB = -1;
                KTG_PPB = "-";
                holder.ppb_data.setText("-");
                holder.ppb_dev.setText("-");
                holder.ppb_hasil.setText(KTG_PPB);
                holder.ppb_back.setBackground(a6A7Activity.getResources().getDrawable(R.drawable.null_style));
            } else {
                if(currentItem.getPPB() >= 53){
                    DEV_PPB = 0;
                    KTG_PPB = "LF";
                    holder.ppb_back.setBackground(a6A7Activity.getResources().getDrawable(R.drawable.success_style));
                } else {
                    DEV_PPB = (currentItem.getPPB() - 53)/53;
                    DEV_PPB = DEV_PPB * 100;
                    if(DEV_PPB < 0){
                        DEV_PPB = DEV_PPB * -1;
                    }
                    if(DEV_PPB > 100){
                        DEV_PPB = 100;
                    }
                    DEV_PPB = Double.parseDouble(df.format(DEV_PPB).replace(",", "."));
                    KTG_PPB = "LS";
                    holder.ppb_back.setBackground(a6A7Activity.getResources().getDrawable(R.drawable.failed_style));
                }
                holder.ppb_data.setText(String.valueOf(currentItem.getPPB())+" m");
                holder.ppb_dev.setText(String.valueOf(DEV_PPB)+"%");
                holder.ppb_hasil.setText(KTG_PPB);
            }
        } catch (Exception e){
            Toast.makeText(a6A7Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            if(currentItem.getLPP() == -1){
                DEV_LPP = -1;
                KTG_LPP = "-";
                holder.lpp_data.setText("-");
                holder.lpp_dev.setText("-");
            } else {
                if(currentItem.getLPP() >= 0.7){
                    DEV_LPP = 0;
                    KTG_LPP = "LF";
                } else {
                    DEV_LPP = (currentItem.getLPP() - 0.7)/0.7;
                    DEV_LPP = DEV_LPP * 100;
                    if(DEV_LPP < 0){
                        DEV_LPP = DEV_LPP * -1;
                    }
                    if(DEV_LPP > 100){
                        DEV_LPP = 100;
                    }
                    DEV_LPP = Double.parseDouble(df.format(DEV_LPP).replace(",", "."));
                    KTG_LPP = "LS";
                }
                holder.lpp_data.setText(String.valueOf(currentItem.getLPP())+" m");
                holder.lpp_dev.setText(String.valueOf(DEV_LPP)+"%");
            }
        } catch (Exception e){
            Toast.makeText(a6A7Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            if(currentItem.getLPP2() == -1){
                DEV_LPP2 = -1;
                KTG_LPP2 = "-";
                holder.lpp2_data.setText("-");
                holder.lpp2_dev.setText("-");
            } else {
                if(currentItem.getLPP2() >= 0.3){
                    DEV_LPP2 = 0;
                    KTG_LPP2 = "LF";
                } else {
                    DEV_LPP2 = (currentItem.getLPP2() - 0.3)/0.3;
                    DEV_LPP2 = DEV_LPP2 * 100;
                    if(DEV_LPP2 < 0){
                        DEV_LPP2 = DEV_LPP2 * -1;
                    }
                    if(DEV_LPP2 > 100){
                        DEV_LPP2 = 100;
                    }
                    DEV_LPP2 = Double.parseDouble(df.format(DEV_LPP2).replace(",", "."));
                    KTG_LPP2 = "LS";
                }
                holder.lpp2_data.setText(String.valueOf(currentItem.getLPP2())+" m");
                holder.lpp2_dev.setText(String.valueOf(DEV_LPP2)+"%");
            }
        } catch (Exception e){
            Toast.makeText(a6A7Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            if(currentItem.getLPT() == -1){
                DEV_LPT = -1;
                KTG_LPT = "-";
                holder.lpt_data.setText("-");
                holder.lpt_dev.setText("-");
            } else {
                if(currentItem.getLPT() >= 13){
                    DEV_LPT = 0;
                    KTG_LPT = "LF";
                } else {
                    DEV_LPT = (currentItem.getLPT() - 13)/13;
                    DEV_LPT = DEV_LPT * 100;
                    if(DEV_LPT < 0){
                        DEV_LPT = DEV_LPT * -1;
                    }
                    if(DEV_LPT > 100){
                        DEV_LPT = 100;
                    }
                    DEV_LPT = Double.parseDouble(df.format(DEV_LPT).replace(",", "."));
                    KTG_LPT = "LS";
                }
                holder.lpt_data.setText(String.valueOf(currentItem.getLPT())+" m");
                holder.lpt_dev.setText(String.valueOf(DEV_LPT)+"%");
            }
        } catch (Exception e){
            Toast.makeText(a6A7Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            if(currentItem.getLPT2() == -1){
                DEV_LPT2 = -1;
                KTG_LPT2 = "-";
                holder.lpt2_data.setText("-");
                holder.lpt2_dev.setText("-");
            } else {
                if(currentItem.getLPT2() >= 30){
                    DEV_LPT2 = 0;
                    KTG_LPT2 = "LF";
                } else {
                    DEV_LPT2 = (currentItem.getLPT2() - 30)/30;
                    DEV_LPT2 = DEV_LPT2 * 100;
                    if(DEV_LPT2 < 0){
                        DEV_LPT2 = DEV_LPT2 * -1;
                    }
                    if(DEV_LPT2 > 100){
                        DEV_LPT2 = 100;
                    }
                    DEV_LPT2 = Double.parseDouble(df.format(DEV_LPT2).replace(",", "."));
                    KTG_LPT2 = "LS";
                }
                holder.lpt2_data.setText(String.valueOf(currentItem.getLPT2())+" m");
                holder.lpt2_dev.setText(String.valueOf(DEV_LPT2)+"%");
            }
        } catch (Exception e){
            Toast.makeText(a6A7Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            if(currentItem.getLPJ() == -1){
                DEV_LPJ = -1;
                KTG_LPJ = "-";
                holder.lpj_data.setText("-");
                holder.lpj_dev.setText("-");
            } else {
                if(a6A7Activity.FNG.equals("Arteri")){
                    if(currentItem.getLPJ() <= 35){
                        DEV_LPJ = 0;
                        KTG_LPJ = "LF";
                    } else {
                        DEV_LPJ = (currentItem.getLPJ() - 35)/35;
                        DEV_LPJ = DEV_LPJ * 100;
                        if(DEV_LPJ < 0){
                            DEV_LPJ = DEV_LPJ * -1;
                        }
                        if(DEV_LPJ > 100){
                            DEV_LPJ = 100;
                        }
                        DEV_LPJ = Double.parseDouble(df.format(DEV_LPJ).replace(",", "."));
                        KTG_LPJ = "LS";
                    }
                } else if(a6A7Activity.FNG.equals("Kolektor")){
                    if(currentItem.getLPJ() <= 40){
                        DEV_LPJ = 0;
                        KTG_LPJ = "LF";
                    } else {
                        DEV_LPJ = (currentItem.getLPJ() - 40)/40;
                        DEV_LPJ = DEV_LPJ * 100;
                        if(DEV_LPJ < 0){
                            DEV_LPJ = DEV_LPJ * -1;
                        }
                        if(DEV_LPJ > 100){
                            DEV_LPJ = 100;
                        }
                        DEV_LPJ = Double.parseDouble(df.format(DEV_LPJ).replace(",", "."));
                        KTG_LPJ = "LS";
                    }
                } else {
                    if(currentItem.getLPJ() <= 60){
                        DEV_LPJ = 0;
                        KTG_LPJ = "LF";
                    } else {
                        DEV_LPJ = (currentItem.getLPJ() - 60)/60;
                        DEV_LPJ = DEV_LPJ * 100;
                        if(DEV_LPJ < 0){
                            DEV_LPJ = DEV_LPJ * -1;
                        }
                        if(DEV_LPJ > 100){
                            DEV_LPJ = 100;
                        }
                        DEV_LPJ = Double.parseDouble(df.format(DEV_LPJ).replace(",", "."));
                        KTG_LPJ = "LS";
                    }
                }
                holder.lpj_data.setText(String.valueOf(currentItem.getLPJ())+" m");
                holder.lpj_dev.setText(String.valueOf(DEV_LPJ)+"%");
            }
        } catch (Exception e){
            Toast.makeText(a6A7Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            if((KTG_LPP.equals("LF") || KTG_LPP.equals("-")) && (KTG_LPP2.equals("LF") || KTG_LPP2.equals("-")) && (KTG_LPT.equals("LF") || KTG_LPT.equals("-")) &&
                    (KTG_LPT2.equals("LF") || KTG_LPT2.equals("-")) && (KTG_LPJ.equals("LF") || KTG_LPJ.equals("-"))){

                if(KTG_LPP.equals("-") && KTG_LPP2.equals("-") && KTG_LPT.equals("-") && KTG_LPT2.equals("-") && KTG_LPJ.equals("-")){
                    KTG_LPJJ = "-";
                    holder.lpj_back.setBackground(a6A7Activity.getResources().getDrawable(R.drawable.null_style));
                } else {
                    KTG_LPJJ = "LF";
                    holder.lpj_back.setBackground(a6A7Activity.getResources().getDrawable(R.drawable.success_style));
                }

            } else {
                KTG_LPJJ = "LS";
                holder.lpj_back.setBackground(a6A7Activity.getResources().getDrawable(R.drawable.failed_style));
            }
            holder.lpj_hasil.setText(KTG_LPJJ);
        } catch (Exception e){
            Toast.makeText(a6A7Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            if(currentItem.getPCT() == -1){
                DEV_PCT = -1;
                KTG_PCT = "-";
                holder.pct_data.setText("-");
                holder.pct_dev.setText("-");
            } else {
                DEV_PCT = STD - currentItem.getPCT();
                if(DEV_PCT == 0){
                    KTG_PCT = "LF";
                } else {
                    KTG_PCT = "LS";
                }
                holder.pct_data.setText(String.valueOf(currentItem.getPCT())+"%");
                holder.pct_dev.setText(String.valueOf(DEV_PCT)+"%");
            }
        } catch (Exception e){
            Toast.makeText(a6A7Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            if(currentItem.getPCT2() == -1){
                DEV_PCT2 = -1;
                KTG_PCT2 = "-";
                holder.pct2_data.setText("-");
                holder.pct2_dev.setText("-");
            } else {
                DEV_PCT2 = STD - currentItem.getPCT2();
                if(DEV_PCT2 == 0){
                    KTG_PCT2 = "LF";
                } else {
                    KTG_PCT2 = "LS";
                }
                holder.pct2_data.setText(String.valueOf(currentItem.getPCT2())+"%");
                holder.pct2_dev.setText(String.valueOf(DEV_PCT2)+"%");
            }
        } catch (Exception e){
            Toast.makeText(a6A7Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            if(currentItem.getPGD() == -1){
                DEV_PGD = -1;
                KTG_PGD = "-";
                holder.pgd_data.setText("-");
                holder.pgd_dev.setText("-");
            } else {
                DEV_PGD = STD - currentItem.getPGD();
                if(DEV_PGD == 0){
                    KTG_PGD = "LF";
                } else {
                    KTG_PGD = "LS";
                }
                holder.pgd_data.setText(String.valueOf(currentItem.getPGD())+"%");
                holder.pgd_dev.setText(String.valueOf(DEV_PGD)+"%");
            }
        } catch (Exception e){
            Toast.makeText(a6A7Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            if((KTG_PCT.equals("LF") || KTG_PCT.equals("-")) && (KTG_PCT2.equals("LF") || KTG_PCT2.equals("-")) && (KTG_PGD.equals("LF") || KTG_PGD.equals("-"))){

                if(KTG_PCT.equals("-") && KTG_PCT2.equals("-") && KTG_PGD.equals("-")){
                    KTG_PPPP = "-";
                    holder.pct_back.setBackground(a6A7Activity.getResources().getDrawable(R.drawable.null_style));
                } else {
                    KTG_PPPP = "LF";
                    holder.pct_back.setBackground(a6A7Activity.getResources().getDrawable(R.drawable.success_style));
                }

            } else {
                KTG_PPPP = "LS";
                holder.pct_back.setBackground(a6A7Activity.getResources().getDrawable(R.drawable.failed_style));
            }
            holder.pct_hasil.setText(KTG_PPPP);
        } catch (Exception e){
            Toast.makeText(a6A7Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            if(currentItem.getPTB() == -1){
                DEV_PTB = -1;
                KTG_PTB = "-";
                holder.ptb_data.setText("-");
                holder.ptb_dev.setText("-");
            } else {
                if(currentItem.getPTB() >= 2.5){
                    DEV_PTB = 0;
                    KTG_PTB = "LF";
                } else {
                    DEV_PTB = (currentItem.getPTB() - 2.5)/2.5;
                    DEV_PTB = DEV_PTB * 100;
                    if(DEV_PTB < 0){
                        DEV_PTB = DEV_PTB * -1;
                    }
                    if(DEV_PTB > 100){
                        DEV_PTB = 100;
                    }
                    DEV_PTB = Double.parseDouble(df.format(DEV_PTB).replace(",", "."));
                    KTG_PTB = "LS";
                }
                holder.ptb_data.setText(String.valueOf(currentItem.getPTB())+" m");
                holder.ptb_dev.setText(String.valueOf(DEV_PTB)+"%");
            }
        } catch (Exception e){
            Toast.makeText(a6A7Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            if(currentItem.getPTB2() == -1){
                DEV_PTB2 = -1;
                KTG_PTB2 = "-";
                holder.ptb2_data.setText("-");
                holder.ptb2_dev.setText("-");
            } else {
                if(currentItem.getPTB2() >= 1.8){
                    DEV_PTB2 = 0;
                    KTG_PTB2 = "LF";
                } else {
                    DEV_PTB2 = (currentItem.getPTB2() - 1.8)/1.8;
                    DEV_PTB2 = DEV_PTB2 * 100;
                    if(DEV_PTB2 < 0){
                        DEV_PTB2 = DEV_PTB2 * -1;
                    }
                    if(DEV_PTB2 > 100){
                        DEV_PTB2 = 100;
                    }
                    DEV_PTB2 = Double.parseDouble(df.format(DEV_PTB2).replace(",", "."));
                    KTG_PTB2 = "LS";
                }
                holder.ptb2_data.setText(String.valueOf(currentItem.getPTB2())+" m");
                holder.ptb2_dev.setText(String.valueOf(DEV_PTB2)+"%");
            }
        } catch (Exception e){
            Toast.makeText(a6A7Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            if(currentItem.getFPP() == -1){
                DEV_FPP = -1;
                KTG_FPP = "-";
                holder.fpp_data.setText("-");
                holder.fpp_dev.setText("-");
            } else {
                DEV_FPP = STD - currentItem.getFPP();
                if(DEV_FPP == 0){
                    KTG_FPP = "LF";
                } else {
                    KTG_FPP = "LS";
                }
                holder.fpp_data.setText(String.valueOf(currentItem.getFPP())+"%");
                holder.fpp_dev.setText(String.valueOf(DEV_FPP)+"%");
            }
        } catch (Exception e){
            Toast.makeText(a6A7Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            if(currentItem.getFPP2() == -1){
                DEV_FPP2 = -1;
                KTG_FPP2 = "-";
                holder.fpp2_data.setText("-");
                holder.fpp2_dev.setText("-");
            } else {
                DEV_FPP2 = STD - currentItem.getFPP2();
                if(DEV_FPP2 == 0){
                    KTG_FPP2 = "LF";
                } else {
                    KTG_FPP2 = "LS";
                }
                holder.fpp2_data.setText(String.valueOf(currentItem.getFPP2())+"%");
                holder.fpp2_dev.setText(String.valueOf(DEV_FPP2)+"%");
            }
        } catch (Exception e){
            Toast.makeText(a6A7Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            if(currentItem.getFJP() == -1){
                DEV_FJP = -1;
                KTG_FJP = "-";
                holder.fjp_data.setText("-");
                holder.fjp_dev.setText("-");
            } else {
                DEV_FJP = STD - currentItem.getFJP();
                if(DEV_FJP == 0){
                    KTG_FJP = "LF";
                } else {
                    KTG_FJP = "LS";
                }
                holder.fjp_data.setText(String.valueOf(currentItem.getFJP())+"%");
                holder.fjp_dev.setText(String.valueOf(DEV_FJP)+"%");
            }
        } catch (Exception e){
            Toast.makeText(a6A7Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            if(currentItem.getFJP2() == -1){
                DEV_FJP2 = -1;
                KTG_FJP2 = "-";
                holder.fjp2_data.setText("-");
                holder.fjp2_dev.setText("-");
            } else {
                DEV_FJP2 = STD - currentItem.getFJP2();
                if(DEV_FJP2 == 0){
                    KTG_FJP2 = "LF";
                } else {
                    KTG_FJP2 = "LS";
                }
                holder.fjp2_data.setText(String.valueOf(currentItem.getFJP2())+"%");
                holder.fjp2_dev.setText(String.valueOf(DEV_FJP2)+"%");
            }
        } catch (Exception e){
            Toast.makeText(a6A7Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            if(currentItem.getFPR() == -1){
                DEV_FPR = -1;
                KTG_FPR = "-";
                holder.fpr_data.setText("-");
                holder.fpr_dev.setText("-");
            } else {
                DEV_FPR = STD - currentItem.getFPR();
                if(DEV_FPR == 0){
                    KTG_FPR = "LF";
                } else {
                    KTG_FPR = "LS";
                }
                holder.fpr_data.setText(String.valueOf(currentItem.getFPR())+"%");
                holder.fpr_dev.setText(String.valueOf(DEV_FPR)+"%");
            }
        } catch (Exception e){
            Toast.makeText(a6A7Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            if((KTG_PTB.equals("LF") || KTG_PTB.equals("-")) && (KTG_PTB2.equals("LF") || KTG_PTB2.equals("-")) && (KTG_FPP.equals("LF") || KTG_FPP.equals("-")) &&
                    (KTG_FPP2.equals("LF") || KTG_FPP2.equals("-")) && (KTG_FJP.equals("LF") || KTG_FJP.equals("-")) && (KTG_FJP2.equals("LF") || KTG_FJP2.equals("-")) &&
                    ((KTG_FPR.equals("LF") || KTG_FPR.equals("-")))){

                if(KTG_PTB.equals("-") && KTG_PTB2.equals("-") && KTG_FPP.equals("-") && KTG_FPP2.equals("-") && KTG_FJP.equals("-") && KTG_FJP2.equals("-") && KTG_FPR.equals("-")){
                    KTG_FPC = "-";
                    holder.ptb_back.setBackground(a6A7Activity.getResources().getDrawable(R.drawable.null_style));
                } else {
                    KTG_FPC = "LF";
                    holder.ptb_back.setBackground(a6A7Activity.getResources().getDrawable(R.drawable.success_style));
                }

            } else {
                KTG_FPC = "LS";
                holder.ptb_back.setBackground(a6A7Activity.getResources().getDrawable(R.drawable.failed_style));
            }
        } catch (Exception e){
            Toast.makeText(a6A7Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            if((KTG_TPK.equals("LF") || KTG_TPK.equals("-")) && (KTG_RMPP.equals("LF") || KTG_RMPP.equals("-")) && (KTG_PPB.equals("LF") || KTG_PPB.equals("-")) &&
                    (KTG_LPJJ.equals("LF") || KTG_LPJJ.equals("-")) && (KTG_PPPP.equals("LF") || KTG_PPPP.equals("-")) && (KTG_FPC.equals("LF") || KTG_FPC.equals("-"))){

                if(KTG_TPK.equals("-") && KTG_RMPP.equals("-") && KTG_PPB.equals("-") && KTG_LPJJ.equals("-") && KTG_PPPP.equals("-") && KTG_FPC.equals("-")){
                    KTG_KLF = "Tidak Dinilai";
                } else {
                    KTG_KLF = "LF";
                }

            } else {
                KTG_KLF = "LS";
            }
        } catch (Exception e){
            Toast.makeText(a6A7Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            holder.rec_data.setText(currentItem.getREC());
        } catch (Exception e){
            Toast.makeText(a6A7Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            holder.FT1.setImageBitmap(BitmapFactory.decodeFile(currentItem.getDIR1()));
        } catch (Exception e){
            Toast.makeText(a6A7Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            holder.FT2.setImageBitmap(BitmapFactory.decodeFile(currentItem.getDIR2()));
        } catch (Exception e){
            Toast.makeText(a6A7Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            holder.FT3.setImageBitmap(BitmapFactory.decodeFile(currentItem.getDIR3()));
        } catch (Exception e){
            Toast.makeText(a6A7Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            holder.FT4.setImageBitmap(BitmapFactory.decodeFile(currentItem.getDIR4()));
        } catch (Exception e){
            Toast.makeText(a6A7Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            if(currentItem.getUPLOAD().equals("F")){
                a6A7Activity.FAB_UPLOAD.hide();
                a6A7Activity.UPLOAD.setImageResource(R.drawable.ic_file_upload_white_18dp);
                a6A7Activity.FAB_UPLOAD.setEnabled(true);
            } else {
                a6A7Activity.UPLOAD.setImageResource(R.drawable.ic_done_white_18dp);
                a6A7Activity.FAB_UPLOAD.setEnabled(false);
            }
        } catch (Exception e){
            Toast.makeText(a6A7Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            a6A7Activity.ID = currentItem.getID();
            a6A7Activity.TPK_TXT = currentItem.getTPK();
            a6A7Activity.RMP_TXT = currentItem.getRMP();
            a6A7Activity.RMP2_TXT = currentItem.getRMP2();
            a6A7Activity.PPB_TXT = currentItem.getPPB();
            a6A7Activity.LPP_TXT = currentItem.getLPP();
            a6A7Activity.LPP2_TXT = currentItem.getLPP2();
            a6A7Activity.LPT_TXT = currentItem.getLPT();
            a6A7Activity.LPT2_TXT = currentItem.getLPT2();
            a6A7Activity.LPJ_TXT = currentItem.getLPJ();
            a6A7Activity.PCT_TXT = currentItem.getPCT();
            a6A7Activity.PCT2_TXT = currentItem.getPCT2();
            a6A7Activity.PGD_TXT = currentItem.getPGD();
            a6A7Activity.PTB_TXT = currentItem.getPTB();
            a6A7Activity.PTB2_TXT = currentItem.getPTB2();
            a6A7Activity.FPP_TXT = currentItem.getFPP();
            a6A7Activity.FPP2_TXT = currentItem.getFPP2();
            a6A7Activity.FJP_TXT = currentItem.getFJP();
            a6A7Activity.FJP2_TXT = currentItem.getFJP2();
            a6A7Activity.FPR_TXT = currentItem.getFPR();
            a6A7Activity.REC_TXT = currentItem.getREC();
            a6A7Activity.DIR1 = currentItem.getDIR1();
            a6A7Activity.DIR2 = currentItem.getDIR2();
            a6A7Activity.DIR3 = currentItem.getDIR3();
            a6A7Activity.DIR4 = currentItem.getDIR4();

            a6A7Activity.DEV_TPK = DEV_TPK;
            a6A7Activity.DEV_RMP = DEV_RMP;
            a6A7Activity.DEV_RMP2 = DEV_RMP2;
            a6A7Activity.DEV_PPB = DEV_PPB;
            a6A7Activity.DEV_LPP = DEV_LPP;
            a6A7Activity.DEV_LPP2 = DEV_LPP2;
            a6A7Activity.DEV_LPT = DEV_LPT;
            a6A7Activity.DEV_LPT2 = DEV_LPT2;
            a6A7Activity.DEV_LPJ = DEV_LPJ;
            a6A7Activity.DEV_PCT = DEV_PCT;
            a6A7Activity.DEV_PCT2 = DEV_PCT2;
            a6A7Activity.DEV_PGD = DEV_PGD;
            a6A7Activity.DEV_PTB = DEV_PTB;
            a6A7Activity.DEV_PTB2 = DEV_PTB2;
            a6A7Activity.DEV_FPP = DEV_FPP;
            a6A7Activity.DEV_FPP2 = DEV_FPP2;
            a6A7Activity.DEV_FJP = DEV_FJP;
            a6A7Activity.DEV_FJP2 = DEV_FJP2;
            a6A7Activity.DEV_FPR = DEV_FPR;

            a6A7Activity.KTG_TPK = KTG_TPK;
            a6A7Activity.KTG_RMPP = KTG_RMPP;
            a6A7Activity.KTG_PPB = KTG_PPB;
            a6A7Activity.KTG_LPJJ = KTG_LPJJ;
            a6A7Activity.KTG_PPPP = KTG_PPPP;
            a6A7Activity.KTG_FPC = KTG_FPC;
            a6A7Activity.KTG_KLF = KTG_KLF;

            if(KTG_KLF.equals("LS")){
                a6A7Activity.klf.setText(KTG_KLF);
                a6A7Activity.klf.setBackground(a6A7Activity.getResources().getDrawable(R.drawable.failed_style));
                Animation animation = AnimationUtils.loadAnimation(a6A7Activity.getApplicationContext(), R.anim.recycle_bottom);
                a6A7Activity.klf.startAnimation(animation);
            } else if(KTG_KLF.equals("Tidak Dinilai")){
                a6A7Activity.klf.setText("Tidak Dinilai");
                a6A7Activity.klf.setBackground(a6A7Activity.getResources().getDrawable(R.drawable.null_style));
                Animation animation = AnimationUtils.loadAnimation(a6A7Activity.getApplicationContext(), R.anim.recycle_bottom);
                a6A7Activity.klf.startAnimation(animation);
            } else {
                a6A7Activity.klf.setText(KTG_KLF);
                a6A7Activity.klf.setBackground(a6A7Activity.getResources().getDrawable(R.drawable.success_style));
                Animation animation = AnimationUtils.loadAnimation(a6A7Activity.getApplicationContext(), R.anim.recycle_bottom);
                a6A7Activity.klf.startAnimation(animation);
            }
        } catch (Exception e){
            Toast.makeText(a6A7Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public int getItemCount() {
        return mdataList.size();
    }

}
