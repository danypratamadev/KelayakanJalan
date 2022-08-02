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

public class A6A1_Adapter extends RecyclerView.Adapter<A6A1_Adapter.A6A1ViewHolder> {

    private A6A1Activity a6A1Activity;
    private List<A6A1_Class> mdataList;
    private double DEV_GSP, DEV_GSJ, DEV_GSL, DEV_GPP, DEV_GPL, DEV_GPJ, DEV_YLP, DEV_YLL, DEV_YLJ, DEV_ZCP, DEV_ZCL, DEV_ZCA, DEV_ZCG,
            DEV_GPM, DEV_GPL2, DEV_GPP2, DEV_GPL3, DEV_MCU, DEV_MCD, DEV_MCS, DEV_MCJ, DEV_GSL2, DEV_TJP, DEV_TJL, DEV_YBL,
            DEV_WMG, DEV_WMJ, DEV_WML, DEV_KMB, STD = 100;
    private String KTG_GSP, KTG_GSJ, KTG_GSL, KTG_GSSS, KTG_GPP, KTG_GPL, KTG_GPJ, KTG_GPPP, KTG_YLP, KTG_YLL, KTG_YLJ, KTG_YLLL, KTG_ZCP, KTG_ZCL, KTG_ZCA, KTG_ZCG,
            KTG_ZCCC, KTG_GPM, KTG_GPL2, KTG_GPMM, KTG_GPP2, KTG_GPL3, KTG_GPDK, KTG_MCU, KTG_MCD, KTG_MCS, KTG_MCJ, KTG_MCCC, KTG_GSL2, KTG_TJP, KTG_TJL, KTG_TJPP, KTG_YBL,
            KTG_WMG, KTG_WMJ, KTG_WML, KTG_WMMM, KTG_UKW, KTG_KMB, KTG_KLF;
    private static DecimalFormat df = new DecimalFormat("0.0");

    public class A6A1ViewHolder extends RecyclerView.ViewHolder {

        private TextView gsp_data, gsj_data, gsl_data, gpp_data, gpl_data, gpj_data, ylp_data, yll_data, ylj_data, zcp_data, zcl_data, zca_data,
                zcg_data, gpm_data, gpl2_data, gpp2_data, gpl3_data, mcu_data, mcd_data, mcs_data, mcj_data, gsl2_data, tjp_data, tjl_data, ybl_data,
                wmg_data, wmj_data, wml_data, kmb_data, gsp_dev, gsj_dev, gsl_dev, gpp_dev, gpl_dev, gpj_dev, ylp_dev, yll_dev, ylj_dev, zcp_dev, zcl_dev,
                zca_dev, zcg_dev, gpm_dev, gpl2_dev, gpp2_dev, gpl3_dev, mcu_dev, mcd_dev, mcs_dev, mcj_dev, gsl2_dev, tjp_dev, tjl_dev, ybl_dev, wmg_dev,
                wmj_dev, wml_dev, kmb_dev, ukw_hasil, kmb_hasil, rec_data;

        private LinearLayout ukw_back, kmb_back;
        private ImageView FT1, FT2, FT3, FT4;

        public A6A1ViewHolder(@NonNull View itemView) {
            super(itemView);

            gsp_data = (TextView) itemView.findViewById(R.id.gsp_data);
            gsp_dev = (TextView) itemView.findViewById(R.id.gsp_dev);
            gsj_data = (TextView) itemView.findViewById(R.id.gsj_data);
            gsj_dev = (TextView) itemView.findViewById(R.id.gsj_dev);
            gsl_data = (TextView) itemView.findViewById(R.id.gsl_data);
            gsl_dev = (TextView) itemView.findViewById(R.id.gsl_dev);
            gpp_data = (TextView) itemView.findViewById(R.id.gpp_data);
            gpp_dev = (TextView) itemView.findViewById(R.id.gpp_dev);
            gpl_data = (TextView) itemView.findViewById(R.id.gpl_data);
            gpl_dev = (TextView) itemView.findViewById(R.id.gpl_dev);
            gpj_data = (TextView) itemView.findViewById(R.id.gpj_data);
            gpj_dev = (TextView) itemView.findViewById(R.id.gpj_dev);
            ylp_data = (TextView) itemView.findViewById(R.id.ylp_data);
            ylp_dev = (TextView) itemView.findViewById(R.id.ylp_dev);
            yll_data = (TextView) itemView.findViewById(R.id.yll_data);
            yll_dev = (TextView) itemView.findViewById(R.id.yll_dev);
            ylj_data = (TextView) itemView.findViewById(R.id.ylj_data);
            ylj_dev = (TextView) itemView.findViewById(R.id.ylj_dev);
            zcp_data = (TextView) itemView.findViewById(R.id.zcp_data);
            zcp_dev = (TextView) itemView.findViewById(R.id.zcp_dev);
            zcl_data = (TextView) itemView.findViewById(R.id.zcl_data);
            zcl_dev = (TextView) itemView.findViewById(R.id.zcl_dev);
            zca_data = (TextView) itemView.findViewById(R.id.zca_data);
            zca_dev = (TextView) itemView.findViewById(R.id.zca_dev);
            zcg_data = (TextView) itemView.findViewById(R.id.zcg_data);
            zcg_dev = (TextView) itemView.findViewById(R.id.zcg_dev);
            gpm_data = (TextView) itemView.findViewById(R.id.gpm_data);
            gpm_dev = (TextView) itemView.findViewById(R.id.gpm_dev);
            gpl2_data = (TextView) itemView.findViewById(R.id.gpl2_data);
            gpl2_dev = (TextView) itemView.findViewById(R.id.gpl2_dev);
            gpp2_data = (TextView) itemView.findViewById(R.id.gpp2_data);
            gpp2_dev = (TextView) itemView.findViewById(R.id.gpp2_dev);
            gpl3_data = (TextView) itemView.findViewById(R.id.gpl3_data);
            gpl3_dev = (TextView) itemView.findViewById(R.id.gpl3_dev);
            mcu_data = (TextView) itemView.findViewById(R.id.mcu_data);
            mcu_dev = (TextView) itemView.findViewById(R.id.mcu_dev);
            mcd_data = (TextView) itemView.findViewById(R.id.mcd_data);
            mcd_dev = (TextView) itemView.findViewById(R.id.mcd_dev);
            mcs_data = (TextView) itemView.findViewById(R.id.mcs_data);
            mcs_dev = (TextView) itemView.findViewById(R.id.mcs_dev);
            mcj_data = (TextView) itemView.findViewById(R.id.mcj_data);
            mcj_dev = (TextView) itemView.findViewById(R.id.mcj_dev);
            gsl2_data = (TextView) itemView.findViewById(R.id.gsl2_data);
            gsl2_dev = (TextView) itemView.findViewById(R.id.gsl2_dev);
            tjp_data = (TextView) itemView.findViewById(R.id.tjp_data);
            tjp_dev = (TextView) itemView.findViewById(R.id.tjp_dev);
            tjl_data = (TextView) itemView.findViewById(R.id.tjl_data);
            tjl_dev = (TextView) itemView.findViewById(R.id.tjl_dev);
            ybl_data = (TextView) itemView.findViewById(R.id.ybl_data);
            ybl_dev = (TextView) itemView.findViewById(R.id.ybl_dev);
            wmg_data = (TextView) itemView.findViewById(R.id.wmg_data);
            wmg_dev = (TextView) itemView.findViewById(R.id.wmg_dev);
            wmj_data = (TextView) itemView.findViewById(R.id.wmj_data);
            wmj_dev = (TextView) itemView.findViewById(R.id.wmj_dev);
            wml_data = (TextView) itemView.findViewById(R.id.wml_data);
            wml_dev = (TextView) itemView.findViewById(R.id.wml_dev);
            ukw_hasil = (TextView) itemView.findViewById(R.id.ukw_hasil);

            rec_data = (TextView) itemView.findViewById(R.id.rec_data);

            FT1 = (ImageView) itemView.findViewById(R.id.FT1);
            FT2 = (ImageView) itemView.findViewById(R.id.FT2);
            FT3 = (ImageView) itemView.findViewById(R.id.FT3);
            FT4 = (ImageView) itemView.findViewById(R.id.FT4);

            kmb_data = (TextView) itemView.findViewById(R.id.kmb_data);
            kmb_dev = (TextView) itemView.findViewById(R.id.kmb_dev);
            kmb_hasil = (TextView) itemView.findViewById(R.id.kmb_hasil);

            ukw_back = (LinearLayout) itemView.findViewById(R.id.ukw_back);
            kmb_back = (LinearLayout) itemView.findViewById(R.id.kmb_back);

        }
    }

    public A6A1_Adapter(A6A1Activity a6A1Activity, List<A6A1_Class> mdataList) {
        this.a6A1Activity = a6A1Activity;
        this.mdataList = mdataList;
    }

    @NonNull
    @Override
    public A6A1_Adapter.A6A1ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.a6a1_layout, parent, false);
        A6A1_Adapter.A6A1ViewHolder a6a1 = new A6A1_Adapter.A6A1ViewHolder(view);
        return a6a1;
    }

    @Override
    public void onBindViewHolder(@NonNull A6A1_Adapter.A6A1ViewHolder holder, int position) {

        final A6A1_Class currentItem = mdataList.get(position);

        try {
            if(currentItem.getGSP() == -1){
                DEV_GSP = -1;
                KTG_GSP = "-";
                holder.gsp_data.setText("-");
                holder.gsp_dev.setText("-");
            } else {
                if(a6A1Activity.KCP <= 60){
                    if(currentItem.getGSP() == 3){
                        DEV_GSP = 0;
                        KTG_GSP = "LF";
                    } else {
                        DEV_GSP = (currentItem.getGSP() - 3)/3;
                        DEV_GSP = DEV_GSP * 100;
                        if(DEV_GSP < 0){
                            DEV_GSP = DEV_GSP * -1;
                        }
                        if(DEV_GSP > 100){
                            DEV_GSP = 100;
                        }
                        DEV_GSP = Double.parseDouble(df.format(DEV_GSP).replace(",", "."));
                        KTG_GSP = "LS";
                    }
                } else {
                    if(currentItem.getGSP() == 5){
                        DEV_GSP = 0;
                        KTG_GSP = "LF";
                    } else {
                        DEV_GSP = (currentItem.getGSP() - 5)/5;
                        DEV_GSP = DEV_GSP * 100;
                        if(DEV_GSP < 0){
                            DEV_GSP = DEV_GSP * -1;
                        }
                        if(DEV_GSP > 100){
                            DEV_GSP = 100;
                        }
                        DEV_GSP = Double.parseDouble(df.format(DEV_GSP).replace(",", "."));
                        KTG_GSP = "LS";
                    }
                }
                holder.gsp_data.setText(String.valueOf(currentItem.getGSP())+" m");
                holder.gsp_dev.setText(String.valueOf(DEV_GSP)+"%");
            }
        } catch (Exception e){
            Toast.makeText(a6A1Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            if(currentItem.getGSJ() == -1){
                DEV_GSJ = -1;
                KTG_GSJ = "-";
                holder.gsj_data.setText("-");
                holder.gsj_dev.setText("-");
            } else {
                if(a6A1Activity.KCP <= 60){
                    if(currentItem.getGSJ() == 5){
                        DEV_GSJ = 0;
                        KTG_GSJ = "LF";
                    } else {
                        DEV_GSJ = (currentItem.getGSJ() - 5)/5;
                        DEV_GSJ = DEV_GSJ * 100;
                        if(DEV_GSJ < 0){
                            DEV_GSJ = DEV_GSJ * -1;
                        }
                        if(DEV_GSJ > 100){
                            DEV_GSJ = 100;
                        }
                        DEV_GSJ = Double.parseDouble(df.format(DEV_GSJ).replace(",", "."));
                        KTG_GSJ = "LS";
                    }
                } else {
                    if(currentItem.getGSJ() == 8){
                        DEV_GSJ = 0;
                        KTG_GSJ = "LF";
                    } else {
                        DEV_GSJ = (currentItem.getGSJ() - 8)/8;
                        DEV_GSJ = DEV_GSJ * 100;
                        if(DEV_GSJ < 0){
                            DEV_GSJ = DEV_GSJ * -1;
                        }
                        if(DEV_GSJ > 100){
                            DEV_GSJ = 100;
                        }
                        DEV_GSJ = Double.parseDouble(df.format(DEV_GSJ).replace(",", "."));
                        KTG_GSJ = "LS";
                    }
                }
                holder.gsj_data.setText(String.valueOf(currentItem.getGSJ())+" m");
                holder.gsj_dev.setText(String.valueOf(DEV_GSJ)+"%");
            }
        } catch (Exception e){
            Toast.makeText(a6A1Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            if(currentItem.getGSL() == -1){
                DEV_GSL = -1;
                KTG_GSL = "-";
                holder.gsl_data.setText("-");
                holder.gsl_dev.setText("-");
            } else {
                if(currentItem.getGSL() == 0.12){
                    DEV_GSL = 0;
                    KTG_GSL = "LF";
                } else {
                    DEV_GSL = (currentItem.getGSL() - 0.12)/0.12;
                    DEV_GSL = DEV_GSL * 100;
                    if(DEV_GSL < 0){
                        DEV_GSL = DEV_GSL * -1;
                    }
                    if(DEV_GSL > 100){
                        DEV_GSL = 100;
                    }
                    DEV_GSL = Double.parseDouble(df.format(DEV_GSL).replace(",", "."));
                    KTG_GSL = "LS";
                }
                holder.gsl_data.setText(String.valueOf(currentItem.getGSL())+" m");
                holder.gsl_dev.setText(String.valueOf(DEV_GSL)+"%");
            }
        } catch (Exception e){
            Toast.makeText(a6A1Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            if((KTG_GSP.equals("LF") || KTG_GSP.equals("-")) && (KTG_GSJ.equals("LF") || KTG_GSJ.equals("-")) && (KTG_GSL.equals("LF") || KTG_GSL.equals("-"))){

                if(KTG_GSP.equals("-") && KTG_GSJ.equals("-") && KTG_GSL.equals("-")){
                    KTG_GSSS = "-";
                } else {
                    KTG_GSSS = "LF";
                }

            } else {
                KTG_GSSS = "LS";
            }
        } catch (Exception e){
            Toast.makeText(a6A1Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            if(currentItem.getGPP() == -1){
                DEV_GPP = -1;
                KTG_GPP = "-";
                holder.gpp_data.setText("-");
                holder.gpp_dev.setText("-");
            } else {
                if(currentItem.getGPP() == 3){
                    DEV_GPP = 0;
                    KTG_GPP = "LF";
                } else {
                    DEV_GPP = (currentItem.getGPP() - 3)/3;
                    DEV_GPP = DEV_GPP * 100;
                    if(DEV_GPP < 0){
                        DEV_GPP = DEV_GPP * -1;
                    }
                    if(DEV_GPP > 100){
                        DEV_GPP = 100;
                    }
                    DEV_GPP = Double.parseDouble(df.format(DEV_GPP).replace(",", "."));
                }
                holder.gpp_data.setText(String.valueOf(currentItem.getGPP())+" m");
                holder.gpp_dev.setText(String.valueOf(DEV_GPP)+"%");
            }
        } catch (Exception e){
            Toast.makeText(a6A1Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            if(currentItem.getGPL() == -1){
                DEV_GPL = -1;
                KTG_GPL = "-";
                holder.gpl_data.setText("-");
                holder.gpl_dev.setText("-");
            } else {
                if(currentItem.getGPL() == 0.15){
                    DEV_GPL = 0;
                    KTG_GPL = "LF";
                } else {
                    DEV_GPL = (currentItem.getGPL() - 0.15)/0.15;
                    DEV_GPL = DEV_GPL * 100;
                    if(DEV_GPL < 0){
                        DEV_GPL = DEV_GPL * -1;
                    }
                    if(DEV_GPL > 100){
                        DEV_GPL = 100;
                    }
                    DEV_GPL = Double.parseDouble(df.format(DEV_GPL).replace(",", "."));
                    KTG_GPL = "LS";
                }
                holder.gpl_data.setText(String.valueOf(currentItem.getGPL())+" m");
                holder.gpl_dev.setText(String.valueOf(DEV_GPL)+"%");
            }
        } catch (Exception e){
            Toast.makeText(a6A1Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            if(currentItem.getGPJ() == -1){
                DEV_GPJ = -1;
                KTG_GPJ = "-";
                holder.gpj_data.setText("-");
                holder.gpj_dev.setText("-");
            } else {
                if(currentItem.getGPJ() == 1.5){
                    DEV_GPJ = 0;
                    KTG_GPJ = "LF";
                } else {
                    DEV_GPJ = (currentItem.getGPJ() - 1.5)/1.5;
                    DEV_GPJ = DEV_GPJ * 100;
                    if(DEV_GPJ < 0){
                        DEV_GPJ = DEV_GPJ * -1;
                    }
                    if(DEV_GPJ > 100){
                        DEV_GPJ = 100;
                    }
                    DEV_GPJ = Double.parseDouble(df.format(DEV_GPJ).replace(",", "."));
                    KTG_GPJ = "LS";
                }
                holder.gpj_data.setText(String.valueOf(currentItem.getGPJ())+" m");
                holder.gpj_dev.setText(String.valueOf(DEV_GPJ)+"%");
            }
        } catch (Exception e){
            Toast.makeText(a6A1Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            if((KTG_GPP.equals("LF") || KTG_GPP.equals("-")) && (KTG_GPL.equals("LF") || KTG_GPL.equals("-")) && (KTG_GPJ.equals("LF") || KTG_GPJ.equals("-"))){

                if(KTG_GPP.equals("-") && KTG_GPL.equals("-") && KTG_GPJ.equals("-")){
                    KTG_GPPP = "-";
                } else {
                    KTG_GPPP = "LF";
                }

            } else {
                KTG_GPPP = "LS";
            }
        } catch (Exception e){
            Toast.makeText(a6A1Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            if(currentItem.getYLP() == -1){
                DEV_YLP = -1;
                KTG_YLP = "-";
                holder.ylp_data.setText("-");
                holder.ylp_dev.setText("-");
            } else {
                if(currentItem.getYLP() == 0.6){
                    DEV_YLP = 0;
                    KTG_YLP = "LF";
                } else {
                    DEV_YLP = (currentItem.getYLP() - 0.6)/0.6;
                    DEV_YLP = DEV_YLP * 100;
                    if(DEV_YLP < 0){
                        DEV_YLP = DEV_YLP * -1;
                    }
                    if(DEV_YLP > 100){
                        DEV_YLP = 100;
                    }
                    DEV_YLP = Double.parseDouble(df.format(DEV_YLP).replace(",", "."));
                    KTG_YLP = "LS";
                }
                holder.ylp_data.setText(String.valueOf(currentItem.getYLP())+" m");
                holder.ylp_dev.setText(String.valueOf(DEV_YLP)+"%");
            }
        } catch (Exception e){
            Toast.makeText(a6A1Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            if(currentItem.getYLL() == -1){
                DEV_YLL = -1;
                KTG_YLL = "-";
                holder.yll_data.setText("-");
                holder.yll_dev.setText("-");
            } else {
                if(currentItem.getYLL() == 0.3){
                    DEV_YLL = 0;
                    KTG_YLL = "LF";
                } else {
                    DEV_YLL = (currentItem.getYLL() - 0.3)/0.3;
                    DEV_YLL = DEV_YLL * 100;
                    if(DEV_YLL < 0){
                        DEV_YLL = DEV_YLL * -1;
                    }
                    if(DEV_YLL > 100){
                        DEV_YLL = 100;
                    }
                    DEV_YLL = Double.parseDouble(df.format(DEV_YLL).replace(",", "."));
                    KTG_YLL = "LS";
                }
                holder.yll_data.setText(String.valueOf(currentItem.getYLL())+" m");
                holder.yll_dev.setText(String.valueOf(DEV_YLL)+"%");
            }
        } catch (Exception e){
            Toast.makeText(a6A1Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            if(currentItem.getYLJ() == -1){
                DEV_YLJ = -1;
                KTG_YLJ = "-";
                holder.ylj_data.setText("-");
                holder.ylj_dev.setText("-");
            } else {
                if(currentItem.getYLJ() == 0.3){
                    DEV_YLJ = 0;
                    KTG_YLJ = "LF";
                } else {
                    DEV_YLJ = (currentItem.getYLJ() - 0.3)/0.3;
                    DEV_YLJ = DEV_YLJ * 100;
                    if(DEV_YLJ < 0){
                        DEV_YLJ = DEV_YLJ * -1;
                    }
                    if(DEV_YLJ > 100){
                        DEV_YLJ = 100;
                    }
                    DEV_YLJ = Double.parseDouble(df.format(DEV_YLJ).replace(",", "."));
                    KTG_YLJ = "LS";
                }
                holder.ylj_data.setText(String.valueOf(currentItem.getYLJ())+" m");
                holder.ylj_dev.setText(String.valueOf(DEV_YLJ)+"%");
            }
        } catch (Exception e){
            Toast.makeText(a6A1Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            if((KTG_YLP.equals("LF") || KTG_YLP.equals("-")) && (KTG_YLL.equals("LF") || KTG_YLL.equals("-")) && (KTG_YLJ.equals("LF") || KTG_YLJ.equals("-"))){

                if(KTG_YLP.equals("-") && KTG_YLL.equals("-") && KTG_YLJ.equals("-")){
                    KTG_YLLL = "-";
                } else {
                    KTG_YLLL = "LF";
                }

            } else {
                KTG_YLLL = "LS";
            }
        } catch (Exception e){
            Toast.makeText(a6A1Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            if(currentItem.getZCP() == -1){
                DEV_ZCP = -1;
                KTG_ZCP = "-";
                holder.zcp_data.setText("-");
                holder.zcp_dev.setText("-");
            } else {
                if(currentItem.getZCP() >= 2.5){
                    DEV_ZCP = 0;
                    KTG_ZCP = "LF";
                } else {
                    DEV_ZCP = (currentItem.getZCP() - 2.5)/2.5;
                    DEV_ZCP = DEV_ZCP * 100;
                    if(DEV_ZCP < 0){
                        DEV_ZCP = DEV_ZCP * -1;
                    }
                    if(DEV_ZCP > 100){
                        DEV_ZCP = 100;
                    }
                    DEV_ZCP = Double.parseDouble(df.format(DEV_ZCP).replace(",", "."));
                    KTG_ZCP = "LS";
                }
                holder.zcp_data.setText(String.valueOf(currentItem.getZCP())+" m");
                holder.zcp_dev.setText(String.valueOf(DEV_ZCP)+"%");
            }
        } catch (Exception e){
            Toast.makeText(a6A1Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            if(currentItem.getZCL() == -1){
                DEV_ZCL = -1;
                KTG_ZCL = "-";
                holder.zcl_data.setText("-");
                holder.zcl_dev.setText("-");
            } else {
                if(currentItem.getZCL() == 0.3){
                    DEV_ZCL = 0;
                    KTG_ZCL = "LF";
                } else {
                    DEV_ZCL = (currentItem.getZCL() - 0.3)/0.3;
                    DEV_ZCL = DEV_ZCL * 100;
                    if(DEV_ZCL < 0){
                        DEV_ZCL = DEV_ZCL * -1;
                    }
                    if(DEV_ZCL > 100){
                        DEV_ZCL = 100;
                    }
                    DEV_ZCL = Double.parseDouble(df.format(DEV_ZCL).replace(",", "."));
                    KTG_ZCL = "LS";
                }
                holder.zcl_data.setText(String.valueOf(currentItem.getZCL())+" m");
                holder.zcl_dev.setText(String.valueOf(DEV_ZCL)+"%");
            }
        } catch (Exception e){
            Toast.makeText(a6A1Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            if(currentItem.getZCA() == -1){
                DEV_ZCA = -1;
                KTG_ZCA = "-";
                holder.zca_data.setText("-");
                holder.zca_dev.setText("-");
            } else {
                if(currentItem.getZCA() == 0.3){
                    DEV_ZCA = 0;
                    KTG_ZCA = "LF";
                } else {
                    DEV_ZCA = (currentItem.getZCA() - 0.3)/0.3;
                    DEV_ZCA = DEV_ZCA * 100;
                    if(DEV_ZCA < 0){
                        DEV_ZCA = DEV_ZCA * -1;
                    }
                    if(DEV_ZCA > 100){
                        DEV_ZCA = 100;
                    }
                    DEV_ZCA = Double.parseDouble(df.format(DEV_ZCA).replace(",", "."));
                    KTG_ZCA = "LS";
                }
                holder.zca_data.setText(String.valueOf(currentItem.getZCA())+" m");
                holder.zca_dev.setText(String.valueOf(DEV_ZCA)+"%");
            }
        } catch (Exception e){
            Toast.makeText(a6A1Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            if(currentItem.getZCG() == -1){
                DEV_ZCG = -1;
                KTG_ZCG = "-";
                holder.zcg_data.setText("-");
                holder.zcg_dev.setText("-");
            } else {
                if(currentItem.getZCG() == 1){
                    DEV_ZCG = 0;
                    KTG_ZCG = "LF";
                } else {
                    DEV_ZCG = (currentItem.getZCG() - 1)/1;
                    DEV_ZCG = DEV_ZCG * 100;
                    if(DEV_ZCG < 0){
                        DEV_ZCG = DEV_ZCG * -1;
                    }
                    if(DEV_ZCG > 100){
                        DEV_ZCG = 100;
                    }
                    DEV_ZCG = Double.parseDouble(df.format(DEV_ZCG).replace(",", "."));
                    KTG_ZCG = "LS";
                }
                holder.zcg_data.setText(String.valueOf(currentItem.getZCG())+" m");
                holder.zcg_dev.setText(String.valueOf(DEV_ZCG)+"%");
            }
        } catch (Exception e){
            Toast.makeText(a6A1Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            if((KTG_ZCP.equals("LF") || KTG_ZCP.equals("-")) && (KTG_ZCL.equals("LF") || KTG_ZCL.equals("-")) &&
                    (KTG_ZCA.equals("LF") || KTG_ZCA.equals("-")) && (KTG_ZCG.equals("LF") || KTG_ZCG.equals("-"))){

                if(KTG_ZCP.equals("-") && KTG_ZCL.equals("-") && KTG_ZCA.equals("-") && KTG_ZCG.equals("-")){
                    KTG_ZCCC = "-";
                } else {
                    KTG_ZCCC = "LF";
                }

            } else {
                KTG_ZCCC = "LS";
            }
        } catch (Exception e){
            Toast.makeText(a6A1Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            if(currentItem.getGPM() == -1){
                DEV_GPM = -1;
                KTG_GPM = "-";
                holder.gpm_data.setText("-");
                holder.gpm_dev.setText("-");
            } else {
                if(currentItem.getGPM() >= 20){
                    DEV_GPM = 0;
                    KTG_GPM = "LF";
                } else {
                    DEV_GPM = (currentItem.getGPM() - 20)/20;
                    DEV_GPM = DEV_GPM * 100;
                    if(DEV_GPM < 0){
                        DEV_GPM = DEV_GPM * -1;
                    }
                    if(DEV_GPM > 100){
                        DEV_GPM = 100;
                    }
                    DEV_GPM = Double.parseDouble(df.format(DEV_GPM).replace(",", "."));
                    KTG_GPM = "LS";
                }
                holder.gpm_data.setText(String.valueOf(currentItem.getGPM())+" m");
                holder.gpm_dev.setText(String.valueOf(DEV_GPM)+"%");
            }
        } catch (Exception e){
            Toast.makeText(a6A1Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            if(currentItem.getGPL2() == -1){
                DEV_GPL2 = -1;
                KTG_GPL2 = "-";
                holder.gpl2_data.setText("-");
                holder.gpl2_dev.setText("-");
            } else {
                if(currentItem.getGPL2() == 0.12){
                    DEV_GPL2 = 0;
                    KTG_GPL2 = "LF";
                } else {
                    DEV_GPL2 = (currentItem.getGPL2() - 0.12)/0.12;
                    DEV_GPL2 = DEV_GPL2 * 100;
                    if(DEV_GPL2 < 0){
                        DEV_GPL2 = DEV_GPL2 * -1;
                    }
                    if(DEV_GPL2 > 100){
                        DEV_GPL2 = 100;
                    }
                    DEV_GPL2 = Double.parseDouble(df.format(DEV_GPL2).replace(",", "."));
                    KTG_GPL2 = "LS";
                }
                holder.gpl2_data.setText(String.valueOf(currentItem.getGPL2())+" m");
                holder.gpl2_dev.setText(String.valueOf(DEV_GPL2)+"%");
            }
        } catch (Exception e){
            Toast.makeText(a6A1Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            if((KTG_GPM.equals("LF") || KTG_GPM.equals("-")) && (KTG_GPL2.equals("LF") || KTG_GPL2.equals("-"))){

                if(KTG_GPM.equals("-") && KTG_GPL2.equals("-")){
                    KTG_GPMM = "-";
                } else {
                    KTG_GPMM = "LF";
                }

            } else {
                KTG_GPMM = "LS";
            }
        } catch (Exception e){
            Toast.makeText(a6A1Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            if(currentItem.getGPP2() == -1){
                DEV_GPP2 = -1;
                KTG_GPP2 = "-";
                holder.gpp2_data.setText("-");
                holder.gpp2_dev.setText("-");
            } else {
                if(currentItem.getGPP2() == 6){
                    DEV_GPP2 = 0;
                    KTG_GPP2 = "LF";
                } else {
                    DEV_GPP2 = (currentItem.getGPP2() - 6)/6;
                    DEV_GPP2 = DEV_GPP2 * 100;
                    if(DEV_GPP2 < 0){
                        DEV_GPP2 = DEV_GPP2 * -1;
                    }
                    if(DEV_GPP2 > 100){
                        DEV_GPP2 = 100;
                    }
                    DEV_GPP2 = Double.parseDouble(df.format(DEV_GPP2).replace(",", "."));
                    KTG_GPP2 = "LS";
                }
                holder.gpp2_data.setText(String.valueOf(currentItem.getGPP2())+" m");
                holder.gpp2_dev.setText(String.valueOf(DEV_GPP2)+"%");
            }
        } catch (Exception e){
            Toast.makeText(a6A1Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            if(currentItem.getGPL3() == -1){
                DEV_GPL3 = -1;
                KTG_GPL3 = "-";
                holder.gpl3_data.setText("-");
                holder.gpl3_dev.setText("-");
            } else {
                if(currentItem.getGPL3() == 0.15){
                    DEV_GPL3 = 0;
                    KTG_GPL3 = "LF";
                } else {
                    DEV_GPL3 = (currentItem.getGPL3() - 0.15)/0.15;
                    DEV_GPL3 = DEV_GPL3 * 100;
                    if(DEV_GPL3 < 0){
                        DEV_GPL3 = DEV_GPL3 * -1;
                    }
                    if(DEV_GPL3 > 100){
                        DEV_GPL3 = 100;
                    }
                    DEV_GPL3 = Double.parseDouble(df.format(DEV_GPL3).replace(",", "."));
                    KTG_GPL3 = "LS";
                }
                holder.gpl3_data.setText(String.valueOf(currentItem.getGPL3())+" m");
                holder.gpl3_dev.setText(String.valueOf(DEV_GPL3)+"%");
            }
        } catch (Exception e){
            Toast.makeText(a6A1Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            if((KTG_GPP2.equals("LF") || KTG_GPP2.equals("-")) && (KTG_GPL3.equals("LF") || KTG_GPL3.equals("-"))){

                if(KTG_GPP2.equals("-") && KTG_GPL3.equals("-")){
                    KTG_GPDK = "-";
                } else {
                    KTG_GPDK = "LF";
                }

            } else {
                KTG_GPDK = "LS";
            }
        } catch (Exception e){
            Toast.makeText(a6A1Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            if(currentItem.getMCU() == -1){
                DEV_MCU = -1;
                KTG_MCU = "-";
                holder.mcu_data.setText("-");
                holder.mcu_dev.setText("-");
            } else {
                if(currentItem.getMCU() == 0.15){
                    DEV_MCU = 0;
                    KTG_MCU = "LF";
                } else {
                    DEV_MCU = (currentItem.getMCU() - 0.15)/0.15;
                    DEV_MCU = DEV_MCU * 100;
                    if(DEV_MCU < 0){
                        DEV_MCU = DEV_MCU * -1;
                    }
                    if(DEV_MCU > 100){
                        DEV_MCU = 100;
                    }
                    DEV_MCU = Double.parseDouble(df.format(DEV_MCU).replace(",", "."));
                    KTG_MCU = "LS";
                }
                holder.mcu_data.setText(String.valueOf(currentItem.getMCU())+" m");
                holder.mcu_dev.setText(String.valueOf(DEV_MCU)+"%");
            }
        } catch (Exception e){
            Toast.makeText(a6A1Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            if(currentItem.getMCD() == -1){
                DEV_MCD = -1;
                KTG_MCD = "-";
                holder.mcd_data.setText("-");
                holder.mcd_dev.setText("-");
            } else {
                if(currentItem.getMCD() == 0.12){
                    DEV_MCD = 0;
                    KTG_MCD = "LF";
                } else {
                    DEV_MCD = (currentItem.getMCD() - 0.12)/0.12;
                    DEV_MCD = DEV_MCD * 100;
                    if(DEV_MCD < 0){
                        DEV_MCD = DEV_MCD * -1;
                    }
                    if(DEV_MCD > 100){
                        DEV_MCD = 100;
                    }
                    DEV_MCD = Double.parseDouble(df.format(DEV_MCD).replace(",", "."));
                    KTG_MCD = "LS";
                }
                holder.mcd_data.setText(String.valueOf(currentItem.getMCD())+" m");
                holder.mcd_dev.setText(String.valueOf(DEV_MCD)+"%");
            }
        } catch (Exception e){
            Toast.makeText(a6A1Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            if(currentItem.getMCS() == -1){
                DEV_MCS = -1;
                KTG_MCS = "-";
                holder.mcs_data.setText("-");
                holder.mcs_dev.setText("-");
            } else {
                if(currentItem.getMCS() == 0.3){
                    DEV_MCS = 0;
                    KTG_MCS = "LF";
                } else {
                    DEV_MCS = (currentItem.getMCS() - 0.3)/0.3;
                    DEV_MCS = DEV_MCS * 100;
                    if(DEV_MCS < 0){
                        DEV_MCS = DEV_MCS * -1;
                    }
                    if(DEV_MCS > 100){
                        DEV_MCS = 100;
                    }
                    DEV_MCS = Double.parseDouble(df.format(DEV_MCS).replace(",", "."));
                    KTG_MCS = "LS";
                }
                holder.mcs_data.setText(String.valueOf(currentItem.getMCS())+" m");
                holder.mcs_dev.setText(String.valueOf(DEV_MCS)+"%");
            }
        } catch (Exception e){
            Toast.makeText(a6A1Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            if(currentItem.getMCJ() == -1){
                DEV_MCJ = -1;
                KTG_MCJ = "-";
                holder.mcj_data.setText("-");
                holder.mcj_dev.setText("-");
            } else {
                if(currentItem.getMCJ() == 2){
                    DEV_MCJ = 0;
                    KTG_MCJ = "LF";
                } else {
                    DEV_MCJ = (currentItem.getMCJ() - 2)/2;
                    DEV_MCJ = DEV_MCJ * 100;
                    if(DEV_MCJ < 0){
                        DEV_MCJ = DEV_MCJ * -1;
                    }
                    if(DEV_MCJ > 100){
                        DEV_MCJ = 100;
                    }
                    DEV_MCJ = Double.parseDouble(df.format(DEV_MCJ).replace(",", "."));
                    KTG_MCJ = "LS";
                }
                holder.mcj_data.setText(String.valueOf(currentItem.getMCJ())+" m");
                holder.mcj_dev.setText(String.valueOf(DEV_MCJ)+"%");
            }
        } catch (Exception e){
            Toast.makeText(a6A1Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            if((KTG_MCU.equals("LF") || KTG_MCU.equals("-")) && (KTG_MCD.equals("LF") || KTG_MCD.equals("-")) &&
                    (KTG_MCS.equals("LF") || KTG_MCS.equals("-")) && (KTG_MCJ.equals("LF") || KTG_MCJ.equals("-"))){

                if(KTG_MCU.equals("-") && KTG_MCD.equals("-") && KTG_MCS.equals("-") && KTG_MCJ.equals("-")){
                    KTG_MCCC = "-";
                } else {
                    KTG_MCCC = "LF";
                }

            } else {
                KTG_MCCC = "LS";
            }
        } catch (Exception e){
            Toast.makeText(a6A1Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            if(currentItem.getGSL2() == -1){
                DEV_GSL2 = -1;
                KTG_GSL2 = "-";
                holder.gsl2_data.setText("-");
                holder.gsl2_dev.setText("-");
            } else {
                if(currentItem.getGSL2() == 0.3){
                    DEV_GSL2 = 0;
                    KTG_GSL2 = "LF";
                } else {
                    DEV_GSL2 = (currentItem.getGSL2() - 0.3)/0.3;
                    DEV_GSL2 = DEV_GSL2 * 100;
                    if(DEV_GSL2 < 0){
                        DEV_GSL2 = DEV_GSL2 * -1;
                    }
                    if(DEV_GSL2 > 100){
                        DEV_GSL2 = 100;
                    }
                    DEV_GSL2 = Double.parseDouble(df.format(DEV_GSL2).replace(",", "."));
                    KTG_GSL2 = "LS";
                }
                holder.gsl2_data.setText(String.valueOf(currentItem.getGSL2())+" m");
                holder.gsl2_dev.setText(String.valueOf(DEV_GSL2)+"%");
            }
        } catch (Exception e){
            Toast.makeText(a6A1Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            if(currentItem.getTJP() == -1){
                DEV_TJP = -1;
                KTG_TJP = "-";
                holder.tjp_data.setText("-");
                holder.tjp_dev.setText("-");
            } else {
                if(currentItem.getTJP() == 5){
                    DEV_TJP = 0;
                    KTG_TJP = "LF";
                } else {
                    DEV_TJP = (currentItem.getTJP() - 5)/5;
                    DEV_TJP = DEV_TJP * 100;
                    if(DEV_TJP < 0){
                        DEV_TJP = DEV_TJP * -1;
                    }
                    if(DEV_TJP > 100){
                        DEV_TJP = 100;
                    }
                    DEV_TJP = Double.parseDouble(df.format(DEV_TJP).replace(",", "."));
                    KTG_TJP = "LS";
                }
                holder.tjp_data.setText(String.valueOf(currentItem.getTJP())+" m");
                holder.tjp_dev.setText(String.valueOf(DEV_TJP)+"%");
            }
        } catch (Exception e){
            Toast.makeText(a6A1Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            if(currentItem.getTJL() == -1){
                DEV_TJL = -1;
                KTG_TJL = "-";
                holder.tjl_data.setText("-");
                holder.tjl_dev.setText("-");
            } else {
                if(currentItem.getTJL() == 0.3){
                    DEV_TJL = 0;
                    KTG_TJL = "LF";
                } else {
                    DEV_TJL = (currentItem.getTJL() - 0.3)/0.3;
                    DEV_TJL = DEV_TJL * 100;
                    if(DEV_TJL < 0){
                        DEV_TJL = DEV_TJL * -1;
                    }
                    if(DEV_TJL > 100){
                        DEV_TJL = 100;
                    }
                    DEV_TJL = Double.parseDouble(df.format(DEV_TJL).replace(",", "."));
                    KTG_TJL = "LS";
                }
                holder.tjl_data.setText(String.valueOf(currentItem.getTJL())+" m");
                holder.tjl_dev.setText(String.valueOf(DEV_TJL)+"%");
            }
        } catch (Exception e){
            Toast.makeText(a6A1Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            if((KTG_TJP.equals("LF") || KTG_TJP.equals("-")) && (KTG_TJL.equals("LF") || KTG_TJL.equals("-"))){

                if(KTG_TJP.equals("-") && KTG_TJL.equals("-")){
                    KTG_TJPP = "-";
                } else {
                    KTG_TJPP = "LF";
                }

            } else {
                KTG_TJPP = "LS";
            }
        } catch (Exception e){
            Toast.makeText(a6A1Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            if(currentItem.getYBL() == -1){
                DEV_YBL = -1;
                KTG_YBL = "-";
                holder.ybl_data.setText("-");
                holder.ybl_dev.setText("-");
            } else {
                if(currentItem.getYBL() == 0.1){
                    DEV_YBL = 0;
                    KTG_YBL = "LF";
                } else {
                    DEV_YBL = (currentItem.getYBL() - 0.1)/0.1;
                    DEV_YBL = DEV_YBL * 100;
                    if(DEV_YBL < 0){
                        DEV_YBL = DEV_YBL * -1;
                    }
                    if(DEV_YBL > 100){
                        DEV_YBL = 100;
                    }
                    DEV_YBL = Double.parseDouble(df.format(DEV_YBL).replace(",", "."));
                    KTG_YBL = "LS";
                }
                holder.ybl_data.setText(String.valueOf(currentItem.getYBL())+" m");
                holder.ybl_dev.setText(String.valueOf(DEV_YBL)+"%");
            }
        } catch (Exception e){
            Toast.makeText(a6A1Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            if(currentItem.getWMG() == -1){
                DEV_WMG = -1;
                KTG_WMG = "-";
                holder.wmg_data.setText("-");
                holder.wmg_dev.setText("-");
            } else {
                DEV_WMG = STD - currentItem.getWMG();
                if(DEV_WMG == 0){
                    KTG_WMG = "LF";
                } else {
                    KTG_WMG = "LS";
                }
                holder.wmg_data.setText(String.valueOf(currentItem.getWMG())+"%");
                holder.wmg_dev.setText(String.valueOf(DEV_WMG)+"%");
            }
        } catch (Exception e){
            Toast.makeText(a6A1Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            if(currentItem.getWMJ() == -1){
                DEV_WMJ = -1;
                KTG_WMJ = "-";
                holder.wmj_data.setText("-");
                holder.wmj_dev.setText("-");
            } else {
                DEV_WMJ = STD - currentItem.getWMJ();
                if(DEV_WMJ == 0){
                    KTG_WMJ = "LF";
                } else {
                    KTG_WMJ = "LS";
                }
                holder.wmj_data.setText(String.valueOf(currentItem.getWMJ())+"%");
                holder.wmj_dev.setText(String.valueOf(DEV_WMJ)+"%");
            }
        } catch (Exception e){
            Toast.makeText(a6A1Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            if(currentItem.getWML() == -1){
                DEV_WML = -1;
                KTG_WML = "-";
                holder.wml_data.setText("-");
                holder.wml_dev.setText("-");
            } else {
                DEV_WML = STD - currentItem.getWML();
                if(DEV_WML == 0){
                    KTG_WML = "LF";
                } else {
                    KTG_WML = "LS";
                }
                holder.wml_data.setText(String.valueOf(currentItem.getWML())+"%");
                holder.wml_dev.setText(String.valueOf(DEV_WML)+"%");
            }
        } catch (Exception e){
            Toast.makeText(a6A1Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            if((KTG_WMG.equals("LF") || KTG_WMG.equals("-")) && (KTG_WMJ.equals("LF") || KTG_WMJ.equals("-")) && (KTG_WML.equals("LF") || KTG_WML.equals("-"))){

                if(KTG_WMG.equals("-") && KTG_WMJ.equals("-") && KTG_WML.equals("-")){
                    KTG_WMMM = "-";
                } else {
                    KTG_WMMM = "LF";
                }

            } else {
                KTG_WMMM = "LS";
            }
        } catch (Exception e){
            Toast.makeText(a6A1Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            if((KTG_GSSS.equals("LF") || KTG_GSSS.equals("-")) && (KTG_GPPP.equals("LF") || KTG_GPPP.equals("-")) && (KTG_YLLL.equals("LF") || KTG_YLLL.equals("-")) &&
                    (KTG_ZCCC.equals("LF") || KTG_ZCCC.equals("-")) && (KTG_GPMM.equals("LF") || KTG_GPMM.equals("-")) && (KTG_GPDK.equals("LF") || KTG_GPDK.equals("-")) &&
                    (KTG_MCCC.equals("LF") || KTG_MCCC.equals("-")) && (KTG_GSL2.equals("LF") || KTG_GSL2.equals("-")) && (KTG_TJPP.equals("LF") || KTG_TJPP.equals("-")) &&
                    (KTG_YBL.equals("LF") || KTG_YBL.equals("-")) && (KTG_WMMM.equals("LF") || KTG_WMMM.equals("-"))){

                if(KTG_GSSS.equals("-") && KTG_GPPP.equals("-") && KTG_YLLL.equals("-") && KTG_ZCCC.equals("-") && KTG_GPMM.equals("-") && KTG_GPDK.equals("-") &&
                        KTG_MCCC.equals("-") && KTG_GSL2.equals("-") && KTG_TJPP.equals("-") && KTG_YBL.equals("-") && KTG_WMMM.equals("-")){
                    KTG_UKW = "-";
                    holder.ukw_back.setBackground(a6A1Activity.getResources().getDrawable(R.drawable.null_style));
                } else {
                    KTG_UKW = "LF";
                    holder.ukw_back.setBackground(a6A1Activity.getResources().getDrawable(R.drawable.success_style));
                }

            } else {
                KTG_UKW = "LS";
                holder.ukw_back.setBackground(a6A1Activity.getResources().getDrawable(R.drawable.failed_style));
            }
            holder.ukw_hasil.setText(KTG_UKW);
        } catch (Exception e){
            Toast.makeText(a6A1Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            if(currentItem.getKMB() == -1){
                DEV_KMB = -1;
                KTG_KMB = "-";
                holder.kmb_data.setText("-");
                holder.kmb_dev.setText("-");
                holder.kmb_hasil.setText(KTG_KMB);
                holder.kmb_back.setBackground(a6A1Activity.getResources().getDrawable(R.drawable.null_style));
            } else {
                DEV_KMB = STD - currentItem.getKMB();
                if(DEV_KMB == 0){
                    KTG_KMB = "LF";
                    holder.kmb_back.setBackground(a6A1Activity.getResources().getDrawable(R.drawable.success_style));
                } else {
                    KTG_KMB = "LS";
                    holder.kmb_back.setBackground(a6A1Activity.getResources().getDrawable(R.drawable.failed_style));
                }
                holder.kmb_data.setText(String.valueOf(currentItem.getKMB())+"%");
                holder.kmb_dev.setText(String.valueOf(DEV_KMB)+"%");
                holder.kmb_hasil.setText(KTG_KMB);
            }
        } catch (Exception e){
            Toast.makeText(a6A1Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            if((KTG_UKW.equals("LF") || KTG_UKW.equals("-")) && (KTG_KMB.equals("LF") || KTG_KMB.equals("-"))){

                if(KTG_UKW.equals("-") && KTG_KMB.equals("-")){
                    KTG_KLF = "-";
                } else {
                    KTG_KLF = "LF";
                }

            } else {
                KTG_KLF = "LS";
            }
        } catch (Exception e){
            Toast.makeText(a6A1Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            holder.rec_data.setText(currentItem.getREC());
        } catch (Exception e){
            Toast.makeText(a6A1Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            holder.FT1.setImageBitmap(BitmapFactory.decodeFile(currentItem.getDIR1()));
        } catch (Exception e){
            Toast.makeText(a6A1Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            holder.FT2.setImageBitmap(BitmapFactory.decodeFile(currentItem.getDIR2()));
        } catch (Exception e){
            Toast.makeText(a6A1Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            holder.FT3.setImageBitmap(BitmapFactory.decodeFile(currentItem.getDIR3()));
        } catch (Exception e){
            Toast.makeText(a6A1Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            holder.FT4.setImageBitmap(BitmapFactory.decodeFile(currentItem.getDIR4()));
        } catch (Exception e){
            Toast.makeText(a6A1Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            if(currentItem.getUPLOAD().equals("F")){
                a6A1Activity.FAB_UPLOAD.hide();
                a6A1Activity.UPLOAD.setImageResource(R.drawable.ic_file_upload_white_18dp);
                a6A1Activity.FAB_UPLOAD.setEnabled(true);
            } else {
                a6A1Activity.UPLOAD.setImageResource(R.drawable.ic_done_white_18dp);
                a6A1Activity.FAB_UPLOAD.setEnabled(false);
            }
        } catch (Exception e){
            Toast.makeText(a6A1Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            a6A1Activity.ID = currentItem.getID();
            a6A1Activity.GSP_TXT = currentItem.getGSP();
            a6A1Activity.GSJ_TXT = currentItem.getGSJ();
            a6A1Activity.GSL_TXT = currentItem.getGSL();
            a6A1Activity.GPP_TXT = currentItem.getGPP();
            a6A1Activity.GPL_TXT = currentItem.getGPL();
            a6A1Activity.GPJ_TXT = currentItem.getGPJ();
            a6A1Activity.YLP_TXT = currentItem.getYLP();
            a6A1Activity.YLL_TXT = currentItem.getYLL();
            a6A1Activity.YLJ_TXT = currentItem.getYLJ();
            a6A1Activity.ZCP_TXT = currentItem.getZCP();
            a6A1Activity.ZCL_TXT = currentItem.getZCL();
            a6A1Activity.ZCA_TXT = currentItem.getZCA();
            a6A1Activity.ZCG_TXT = currentItem.getZCG();
            a6A1Activity.GPM_TXT = currentItem.getGPM();
            a6A1Activity.GPL2_TXT = currentItem.getGPL2();
            a6A1Activity.GPP2_TXT = currentItem.getGPP2();
            a6A1Activity.GPL3_TXT = currentItem.getGPL3();
            a6A1Activity.MCU_TXT = currentItem.getMCU();
            a6A1Activity.MCD_TXT = currentItem.getMCD();
            a6A1Activity.MCS_TXT = currentItem.getMCS();
            a6A1Activity.MCJ_TXT = currentItem.getMCJ();
            a6A1Activity.GSL2_TXT = currentItem.getGSL2();
            a6A1Activity.TJP_TXT = currentItem.getTJP();
            a6A1Activity.TJL_TXT = currentItem.getTJL();
            a6A1Activity.YBL_TXT = currentItem.getYBL();
            a6A1Activity.WMG_TXT = currentItem.getWMG();
            a6A1Activity.WMJ_TXT = currentItem.getWMJ();
            a6A1Activity.WML_TXT = currentItem.getWML();
            a6A1Activity.KMB_TXT = currentItem.getKMB();
            a6A1Activity.REC_TXT = currentItem.getREC();
            a6A1Activity.DIR1 = currentItem.getDIR1();
            a6A1Activity.DIR2 = currentItem.getDIR2();
            a6A1Activity.DIR3 = currentItem.getDIR3();
            a6A1Activity.DIR4 = currentItem.getDIR4();

            a6A1Activity.DEV_GSP = DEV_GSP;
            a6A1Activity.DEV_GSJ = DEV_GSJ;
            a6A1Activity.DEV_GSL = DEV_GSL;
            a6A1Activity.DEV_GPP = DEV_GPP;
            a6A1Activity.DEV_GPL = DEV_GPL;
            a6A1Activity.DEV_GPJ = DEV_GPJ;
            a6A1Activity.DEV_YLP = DEV_YLP;
            a6A1Activity.DEV_YLL = DEV_YLL;
            a6A1Activity.DEV_YLJ = DEV_YLJ;
            a6A1Activity.DEV_ZCP = DEV_ZCP;
            a6A1Activity.DEV_ZCL = DEV_ZCL;
            a6A1Activity.DEV_ZCA = DEV_ZCA;
            a6A1Activity.DEV_ZCG = DEV_ZCG;
            a6A1Activity.DEV_GPM = DEV_GPM;
            a6A1Activity.DEV_GPL2 = DEV_GPL2;
            a6A1Activity.DEV_GPP2 = DEV_GPP2;
            a6A1Activity.DEV_GPL3 = DEV_GPL3;
            a6A1Activity.DEV_MCU = DEV_MCU;
            a6A1Activity.DEV_MCD = DEV_MCD;
            a6A1Activity.DEV_MCS = DEV_MCS;
            a6A1Activity.DEV_MCJ = DEV_MCJ;
            a6A1Activity.DEV_GSL2 = DEV_GSL2;
            a6A1Activity.DEV_TJP = DEV_TJP;
            a6A1Activity.DEV_TJL = DEV_TJL;
            a6A1Activity.DEV_YBL = DEV_YBL;
            a6A1Activity.DEV_WMG = DEV_WMG;
            a6A1Activity.DEV_WMJ = DEV_WMJ;
            a6A1Activity.DEV_WML = DEV_WML;
            a6A1Activity.DEV_KMB = DEV_KMB;

            a6A1Activity.KTG_UKW = KTG_UKW;
            a6A1Activity.KTG_KMB = KTG_KMB;
            a6A1Activity.KTG_KLF = KTG_KLF;

            if(KTG_KLF.equals("LS")){
                a6A1Activity.klf.setText(KTG_KLF);
                a6A1Activity.klf.setBackground(a6A1Activity.getResources().getDrawable(R.drawable.failed_style));
                Animation animation = AnimationUtils.loadAnimation(a6A1Activity.getApplicationContext(), R.anim.recycle_bottom);
                a6A1Activity.klf.startAnimation(animation);
            } else if(KTG_KLF.equals("Tidak Dinilai")){
                a6A1Activity.klf.setText("Tidak Dinilai");
                a6A1Activity.klf.setBackground(a6A1Activity.getResources().getDrawable(R.drawable.null_style));
                Animation animation = AnimationUtils.loadAnimation(a6A1Activity.getApplicationContext(), R.anim.recycle_bottom);
                a6A1Activity.klf.startAnimation(animation);
            } else {
                a6A1Activity.klf.setText(KTG_KLF);
                a6A1Activity.klf.setBackground(a6A1Activity.getResources().getDrawable(R.drawable.success_style));
                Animation animation = AnimationUtils.loadAnimation(a6A1Activity.getApplicationContext(), R.anim.recycle_bottom);
                a6A1Activity.klf.startAnimation(animation);
            }
        } catch (Exception e){
            Toast.makeText(a6A1Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public int getItemCount() {
        return mdataList.size();
    }

}
