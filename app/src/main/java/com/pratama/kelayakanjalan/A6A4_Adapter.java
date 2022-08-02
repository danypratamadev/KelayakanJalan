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

public class A6A4_Adapter extends RecyclerView.Adapter<A6A4_Adapter.A6A4ViewHolder> {

    private A6A4Activity a6A4Activity;
    private List<A6A4_Class> mdataList;
    private double DEV_JLK, DEV_TKM, DEV_DGP, DEV_DGL, DEV_DGB, DEV_DGJ, DEV_GPL, DEV_GPG, DEV_CLU,
            DEV_CLC, DEV_CSC, DEV_CBC, DEV_RPT, DEV_RPT2, DEV_RPT3, DEV_RPT4;
    private String KTG_JLK, KTG_TKM, KTG_DGP, KTG_DGL, KTG_DGB, KTG_DGJ, KTG_GPL, KTG_GPG, KTG_CLU,
            KTG_CLC, KTG_CSC, KTG_CBC, KTG_RPT, KTG_RPT2, KTG_RPT3, KTG_RPT4, KTG_DMU, KTG_KLF;
    private static DecimalFormat df = new DecimalFormat("0.0");

    public class A6A4ViewHolder extends RecyclerView.ViewHolder {

        private TextView jlk_data, tkm_data, dgp_data, dgl_data, dgb_data, dgj_data, gpl_data, gpg_data,
                clu_data, clc_data, csc_data, cbc_data, rpt_data, rpt2_data, rpt3_data, rpt4_data,
                jlk_dev, tkm_dev, dgp_dev, dgl_dev, dgb_dev, dgj_dev, gpl_dev, gpg_dev, clu_dev, clc_dev,
                csc_dev, cbc_dev, rpt_dev, rpt2_dev, rpt3_dev, rpt4_dev, jlk_hasil, tkm_hasil, dmu_hasil, rec_data;

        private LinearLayout jlk_back, tkm_back, dmu_back;
        private ImageView FT1, FT2;

        public A6A4ViewHolder(@NonNull View itemView) {
            super(itemView);

            jlk_data = (TextView) itemView.findViewById(R.id.jlk_data);
            jlk_dev = (TextView) itemView.findViewById(R.id.jlk_dev);
            jlk_hasil = (TextView) itemView.findViewById(R.id.jlk_hasil);

            tkm_data = (TextView) itemView.findViewById(R.id.tkm_data);
            tkm_dev = (TextView) itemView.findViewById(R.id.tkm_dev);
            tkm_hasil = (TextView) itemView.findViewById(R.id.tkm_hasil);

            dgp_data = (TextView) itemView.findViewById(R.id.dgp_data);
            dgp_dev = (TextView) itemView.findViewById(R.id.dgp_dev);
            dgl_data = (TextView) itemView.findViewById(R.id.dgl_data);
            dgl_dev = (TextView) itemView.findViewById(R.id.dgl_dev);
            dgb_data = (TextView) itemView.findViewById(R.id.dgb_data);
            dgb_dev = (TextView) itemView.findViewById(R.id.dgb_dev);
            dgj_data = (TextView) itemView.findViewById(R.id.dgj_data);
            dgj_dev = (TextView) itemView.findViewById(R.id.dgj_dev);
            gpl_data = (TextView) itemView.findViewById(R.id.gpl_data);
            gpl_dev = (TextView) itemView.findViewById(R.id.gpl_dev);
            gpg_data = (TextView) itemView.findViewById(R.id.gpg_data);
            gpg_dev = (TextView) itemView.findViewById(R.id.gpg_dev);
            clu_data = (TextView) itemView.findViewById(R.id.clu_data);
            clu_dev = (TextView) itemView.findViewById(R.id.clu_dev);
            clc_data = (TextView) itemView.findViewById(R.id.clc_data);
            clc_dev = (TextView) itemView.findViewById(R.id.clc_dev);
            csc_data = (TextView) itemView.findViewById(R.id.csc_data);
            csc_dev = (TextView) itemView.findViewById(R.id.csc_dev);
            cbc_data = (TextView) itemView.findViewById(R.id.cbc_data);
            cbc_dev = (TextView) itemView.findViewById(R.id.cbc_dev);
            rpt_data = (TextView) itemView.findViewById(R.id.rpt_data);
            rpt_dev = (TextView) itemView.findViewById(R.id.rpt_dev);
            rpt2_data = (TextView) itemView.findViewById(R.id.rpt2_data);
            rpt2_dev = (TextView) itemView.findViewById(R.id.rpt2_dev);
            rpt3_data = (TextView) itemView.findViewById(R.id.rpt3_data);
            rpt3_dev = (TextView) itemView.findViewById(R.id.rpt3_dev);
            rpt4_data = (TextView) itemView.findViewById(R.id.rpt4_data);
            rpt4_dev = (TextView) itemView.findViewById(R.id.rpt4_dev);
            dmu_hasil = (TextView) itemView.findViewById(R.id.dmu_hasil);

            rec_data = (TextView) itemView.findViewById(R.id.rec_data);

            FT1 = (ImageView) itemView.findViewById(R.id.FT1);
            FT2 = (ImageView) itemView.findViewById(R.id.FT2);

            jlk_back = (LinearLayout) itemView.findViewById(R.id.jlk_back);
            tkm_back = (LinearLayout) itemView.findViewById(R.id.tkm_back);
            dmu_back = (LinearLayout) itemView.findViewById(R.id.dmu_back);

        }
    }

    public A6A4_Adapter(A6A4Activity a6A4Activity, List<A6A4_Class> mdataList) {
        this.a6A4Activity = a6A4Activity;
        this.mdataList = mdataList;
    }

    @NonNull
    @Override
    public A6A4_Adapter.A6A4ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.a6a4_layout, parent, false);
        A6A4_Adapter.A6A4ViewHolder a6a4 = new A6A4_Adapter.A6A4ViewHolder(view);
        return a6a4;
    }

    @Override
    public void onBindViewHolder(@NonNull A6A4_Adapter.A6A4ViewHolder holder, int position) {

        final A6A4_Class currentItem = mdataList.get(position);

        try {
            if(currentItem.getJLK() == -1){
                DEV_JLK = -1;
                KTG_JLK = "-";
                holder.jlk_data.setText("-");
                holder.jlk_dev.setText("-");
                holder.jlk_hasil.setText(KTG_JLK);
                holder.jlk_back.setBackground(a6A4Activity.getResources().getDrawable(R.drawable.null_style));
            } else {
                if(currentItem.getJLK() >= 2.5){
                    DEV_JLK = 0;
                    KTG_JLK = "LF";
                    holder.jlk_back.setBackground(a6A4Activity.getResources().getDrawable(R.drawable.success_style));
                } else {
                    DEV_JLK = (currentItem.getJLK() - 2.5)/2.5;
                    DEV_JLK = DEV_JLK * 100;
                    if(DEV_JLK < 0){
                        DEV_JLK = DEV_JLK * -1;
                    }
                    if(DEV_JLK > 100){
                        DEV_JLK = 100;
                    }
                    DEV_JLK = Double.parseDouble(df.format(DEV_JLK).replace(",", "."));
                    KTG_JLK = "LS";
                    holder.jlk_back.setBackground(a6A4Activity.getResources().getDrawable(R.drawable.failed_style));
                }
                holder.jlk_data.setText(String.valueOf(currentItem.getJLK())+" m");
                holder.jlk_dev.setText(String.valueOf(DEV_JLK)+"%");
                holder.jlk_hasil.setText(KTG_JLK);
            }
        } catch (Exception e){
            Toast.makeText(a6A4Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            if(currentItem.getTKM() == -1){
                DEV_TKM = -1;
                KTG_TKM = "-";
                holder.tkm_data.setText("-");
                holder.tkm_dev.setText("-");
                holder.tkm_hasil.setText(KTG_TKM);
                holder.tkm_back.setBackground(a6A4Activity.getResources().getDrawable(R.drawable.null_style));
            } else {
                if(currentItem.getTKM() >= 0.12){
                    DEV_TKM = 0;
                    KTG_TKM = "LF";
                    holder.tkm_back.setBackground(a6A4Activity.getResources().getDrawable(R.drawable.success_style));
                } else {
                    DEV_TKM = (currentItem.getTKM() - 0.12)/0.12;
                    DEV_TKM = DEV_TKM * 100;
                    if(DEV_TKM < 0){
                        DEV_TKM = DEV_TKM * -1;
                    }
                    if(DEV_TKM > 100){
                        DEV_TKM = 100;
                    }
                    DEV_TKM = Double.parseDouble(df.format(DEV_TKM).replace(",", "."));
                    KTG_TKM = "LS";
                    holder.tkm_back.setBackground(a6A4Activity.getResources().getDrawable(R.drawable.failed_style));
                }
                holder.tkm_data.setText(String.valueOf(currentItem.getTKM())+" m");
                holder.tkm_dev.setText(String.valueOf(DEV_TKM)+"%");
                holder.tkm_hasil.setText(KTG_TKM);
            }
        } catch (Exception e){
            Toast.makeText(a6A4Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            if(currentItem.getDGP() == -1){
                DEV_DGP = -1;
                KTG_DGP = "-";
                holder.dgp_data.setText("-");
                holder.dgp_dev.setText("-");
            } else {
                if(currentItem.getDGP() == 3){
                    DEV_DGP = 0;
                    KTG_DGP = "LF";
                } else {
                    DEV_DGP = (currentItem.getDGP() - 3)/3;
                    DEV_DGP = DEV_DGP * 100;
                    if(DEV_DGP < 0){
                        DEV_DGP = DEV_DGP * -1;
                    }
                    if(DEV_DGP > 100){
                        DEV_DGP = 100;
                    }
                    DEV_DGP = Double.parseDouble(df.format(DEV_DGP).replace(",", "."));
                    KTG_DGP = "LS";
                }
                holder.dgp_data.setText(String.valueOf(currentItem.getDGP())+" m");
                holder.dgp_dev.setText(String.valueOf(DEV_DGP)+"%");
            }
        } catch (Exception e){
            Toast.makeText(a6A4Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            if(currentItem.getDGL() == -1){
                DEV_DGL = -1;
                KTG_DGL = "-";
                holder.dgl_data.setText("-");
                holder.dgl_dev.setText("-");
            } else {
                if(currentItem.getDGL() == 0.15){
                    DEV_DGL = 0;
                    KTG_DGL = "LF";
                } else {
                    DEV_DGL = (currentItem.getDGL() - 0.15)/0.15;
                    DEV_DGL = DEV_DGL * 100;
                    if(DEV_DGL < 0){
                        DEV_DGL = DEV_DGL * -1;
                    }
                    if(DEV_DGL > 100){
                        DEV_DGL = 100;
                    }
                    DEV_DGL = Double.parseDouble(df.format(DEV_DGL).replace(",", "."));
                    KTG_DGL = "LS";
                }
                holder.dgl_data.setText(String.valueOf(currentItem.getDGL())+" m");
                holder.dgl_dev.setText(String.valueOf(DEV_DGL)+"%");
            }
        } catch (Exception e){
            Toast.makeText(a6A4Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            if(currentItem.getDGB() == -1){
                DEV_DGB = -1;
                KTG_DGB = "-";
                holder.dgb_data.setText("-");
                holder.dgb_dev.setText("-");
            } else {
                if(currentItem.getDGB() == 1.5){
                    DEV_DGB = 0;
                    KTG_DGB = "LF";
                } else {
                    DEV_DGB = (currentItem.getDGB() - 1.5)/1.5;
                    DEV_DGB = DEV_DGB * 100;
                    if(DEV_DGB < 0){
                        DEV_DGB = DEV_DGB * -1;
                    }
                    if(DEV_DGB > 100){
                        DEV_DGB = 100;
                    }
                    DEV_DGB = Double.parseDouble(df.format(DEV_DGB).replace(",", "."));
                    KTG_DGB = "LS";
                }
                holder.dgb_data.setText(String.valueOf(currentItem.getDGB())+" m");
                holder.dgb_dev.setText(String.valueOf(DEV_DGB)+"%");
            }
        } catch (Exception e){
            Toast.makeText(a6A4Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            if(currentItem.getDGJ() == -1){
                DEV_DGJ = -1;
                KTG_DGJ = "-";
                holder.dgj_data.setText("-");
                holder.dgj_dev.setText("-");
            } else {
                if(currentItem.getDGJ() >= 50){
                    DEV_DGJ = 0;
                    KTG_DGJ = "LF";
                } else {
                    DEV_DGJ = (currentItem.getDGJ() - 50)/50;
                    DEV_DGJ = DEV_DGJ * 100;
                    if(DEV_DGJ < 0){
                        DEV_DGJ = DEV_DGJ * -1;
                    }
                    if(DEV_DGJ > 100){
                        DEV_DGJ = 100;
                    }
                    DEV_DGJ = Double.parseDouble(df.format(DEV_DGJ).replace(",", "."));
                    KTG_DGJ = "LS";
                }
                holder.dgj_data.setText(String.valueOf(currentItem.getDGJ())+" m");
                holder.dgj_dev.setText(String.valueOf(DEV_DGJ)+"%");
            }
        } catch (Exception e){
            Toast.makeText(a6A4Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
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
            Toast.makeText(a6A4Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            if(currentItem.getGPG() == -1){
                DEV_GPG = -1;
                KTG_GPG = "-";
                holder.gpg_data.setText("-");
                holder.gpg_dev.setText("-");
            } else {
                if(a6A4Activity.FNG.equals("Arteri")){
                    if(a6A4Activity.SJR.equals("Primer (Antar Kota)")){
                        if(currentItem.getGPG() == 18){
                            DEV_GPG = 0;
                            KTG_GPG = "LF";
                        } else {
                            DEV_GPG = (currentItem.getGPG() - 18)/18;
                            DEV_GPG = DEV_GPG * 100;
                            if(DEV_GPG < 0){
                                DEV_GPG = DEV_GPG * -1;
                            }
                            if(DEV_GPG > 100){
                                DEV_GPG = 100;
                            }
                            DEV_GPG = Double.parseDouble(df.format(DEV_GPG).replace(",", "."));
                            KTG_GPG = "LS";
                        }
                    } else {
                        if(currentItem.getGPG() == 9){
                            DEV_GPG = 0;
                            KTG_GPG = "LF";
                        } else {
                            DEV_GPG = (currentItem.getGPG() - 9)/9;
                            DEV_GPG = DEV_GPG * 100;
                            if(DEV_GPG < 0){
                                DEV_GPG = DEV_GPG * -1;
                            }
                            if(DEV_GPG > 100){
                                DEV_GPG = 100;
                            }
                            DEV_GPG = Double.parseDouble(df.format(DEV_GPG).replace(",", "."));
                            KTG_GPG = "LS";
                        }
                    }
                } else {
                    if(a6A4Activity.SJR.equals("Primer (Antar Kota)")){
                        if(currentItem.getGPG() == 12){
                            DEV_GPG = 0;
                            KTG_GPG = "LF";
                        } else {
                            DEV_GPG = (currentItem.getGPG() - 12)/12;
                            DEV_GPG = DEV_GPG * 100;
                            if(DEV_GPG < 0){
                                DEV_GPG = DEV_GPG * -1;
                            }
                            if(DEV_GPG > 100){
                                DEV_GPG = 100;
                            }
                            DEV_GPG = Double.parseDouble(df.format(DEV_GPG).replace(",", "."));
                            KTG_GPG = "LS";
                        }
                    } else {
                        if(currentItem.getGPG() == 6){
                            DEV_GPG = 0;
                            KTG_GPG = "LF";
                        } else {
                            DEV_GPG = (currentItem.getGPG() - 6)/6;
                            DEV_GPG = DEV_GPG * 100;
                            if(DEV_GPG < 0){
                                DEV_GPG = DEV_GPG * -1;
                            }
                            if(DEV_GPG > 100){
                                DEV_GPG = 100;
                            }
                            DEV_GPG = Double.parseDouble(df.format(DEV_GPG).replace(",", "."));
                            KTG_GPG = "LS";
                        }
                    }
                }
                holder.gpg_data.setText(String.valueOf(currentItem.getGPG())+" m");
                holder.gpg_dev.setText(String.valueOf(DEV_GPG)+"%");
            }
        } catch (Exception e){
            Toast.makeText(a6A4Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            if(currentItem.getCLU() == -1){
                DEV_CLU = -1;
                KTG_CLU = "-";
                holder.clu_data.setText("-");
                holder.clu_dev.setText("-");
            } else {
                if(currentItem.getCLU() == 0.15){
                    DEV_CLU = 0;
                    KTG_CLU = "LF";
                } else {
                    DEV_CLU = (currentItem.getCLU() - 0.15)/0.15;
                    DEV_CLU = DEV_CLU * 100;
                    if(DEV_CLU < 0){
                        DEV_CLU = DEV_CLU * -1;
                    }
                    if(DEV_CLU > 100){
                        DEV_CLU = 100;
                    }
                    DEV_CLU = Double.parseDouble(df.format(DEV_CLU).replace(",", "."));
                    KTG_CLU = "LS";
                }
                holder.clu_data.setText(String.valueOf(currentItem.getCLU())+" m");
                holder.clu_dev.setText(String.valueOf(DEV_CLU)+"%");
            }
        } catch (Exception e){
            Toast.makeText(a6A4Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            if(currentItem.getCLC() == -1){
                DEV_CLC = -1;
                KTG_CLC = "-";
                holder.clc_data.setText("-");
                holder.clc_dev.setText("-");
            } else {
                if(currentItem.getCLC() == 0.12){
                    DEV_CLC = 0;
                    KTG_CLC = "LF";
                } else {
                    DEV_CLC = (currentItem.getCLC() - 0.12)/0.12;
                    DEV_CLC = DEV_CLC * 100;
                    if(DEV_CLC < 0){
                        DEV_CLC = DEV_CLC * -1;
                    }
                    if(DEV_CLC > 100){
                        DEV_CLC = 100;
                    }
                    DEV_CLC = Double.parseDouble(df.format(DEV_CLC).replace(",", "."));
                    KTG_CLC = "LS";
                }
                holder.clc_data.setText(String.valueOf(currentItem.getCLC())+" m");
                holder.clc_dev.setText(String.valueOf(DEV_CLC)+"%");
            }
        } catch (Exception e){
            Toast.makeText(a6A4Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            if(currentItem.getCSC() == -1){
                DEV_CSC = -1;
                KTG_CSC = "-";
                holder.csc_data.setText("-");
                holder.csc_dev.setText("-");
            } else {
                if(currentItem.getCSC() == 45){
                    DEV_CSC = 0;
                    KTG_CSC = "LF";
                } else {
                    DEV_CSC = (currentItem.getCSC() - 45)/45;
                    DEV_CSC = DEV_CSC * 100;
                    if(DEV_CSC < 0){
                        DEV_CSC = DEV_CSC * -1;
                    }
                    if(DEV_CSC > 100){
                        DEV_CSC = 100;
                    }
                    DEV_CSC = Double.parseDouble(df.format(DEV_CSC).replace(",", "."));
                    KTG_CSC = "LS";
                }
                holder.csc_data.setText(String.valueOf(currentItem.getCSC())+" m");
                holder.csc_dev.setText(String.valueOf(DEV_CSC)+"%");
            }
        } catch (Exception e){
            Toast.makeText(a6A4Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            if(currentItem.getCBC() == -1){
                DEV_CBC = -1;
                KTG_CBC = "-";
                holder.cbc_data.setText("-");
                holder.cbc_dev.setText("-");
            } else {
                if(currentItem.getCBC() == 2){
                    DEV_CBC = 0;
                    KTG_CBC = "LF";
                } else {
                    DEV_CBC = (currentItem.getCBC() - 2)/2;
                    DEV_CBC = DEV_CBC * 100;
                    if(DEV_CBC < 0){
                        DEV_CBC = DEV_CBC * -1;
                    }
                    if(DEV_CBC > 100){
                        DEV_CBC = 100;
                    }
                    DEV_CBC = Double.parseDouble(df.format(DEV_CBC).replace(",", "."));
                    KTG_CBC = "LS";
                }
                holder.cbc_data.setText(String.valueOf(currentItem.getCBC())+" m");
                holder.cbc_dev.setText(String.valueOf(DEV_CBC)+"%");
            }
        } catch (Exception e){
            Toast.makeText(a6A4Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            if(currentItem.getRPT() == -1){
                DEV_RPT = -1;
                KTG_RPT = "-";
                holder.rpt_data.setText("-");
                holder.rpt_dev.setText("-");
            } else {
                if(currentItem.getRPT() == 1.2){
                    DEV_RPT = 0;
                    KTG_RPT = "LF";
                } else {
                    DEV_RPT = (currentItem.getRPT() - 1.2)/1.2;
                    DEV_RPT = DEV_RPT * 100;
                    if(DEV_RPT < 0){
                        DEV_RPT = DEV_RPT * -1;
                    }
                    if(DEV_RPT > 100){
                        DEV_RPT = 100;
                    }
                    DEV_RPT = Double.parseDouble(df.format(DEV_RPT).replace(",", "."));
                    KTG_RPT = "LS";
                }
                holder.rpt_data.setText(String.valueOf(currentItem.getRPT())+" m");
                holder.rpt_dev.setText(String.valueOf(DEV_RPT)+"%");
            }
        } catch (Exception e){
            Toast.makeText(a6A4Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            if(currentItem.getRPT2() == -1){
                DEV_RPT2 = -1;
                KTG_RPT2 = "-";
                holder.rpt2_data.setText("-");
                holder.rpt2_dev.setText("-");
            } else {
                if(currentItem.getRPT2() >= 0.45){
                    DEV_RPT2 = 0;
                    KTG_RPT2 = "LF";
                } else {
                    DEV_RPT2 = (currentItem.getRPT2() - 0.45)/0.45;
                    DEV_RPT2 = DEV_RPT2 * 100;
                    if(DEV_RPT2 < 0){
                        DEV_RPT2 = DEV_RPT2 * -1;
                    }
                    if(DEV_RPT2 > 100){
                        DEV_RPT2 = 100;
                    }
                    DEV_RPT2 = Double.parseDouble(df.format(DEV_RPT2).replace(",", "."));
                    KTG_RPT2 = "LS";
                }
                holder.rpt2_data.setText(String.valueOf(currentItem.getRPT2())+" m");
                holder.rpt2_dev.setText(String.valueOf(DEV_RPT2)+"%");
            }
        } catch (Exception e){
            Toast.makeText(a6A4Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            if(currentItem.getRPT3() == -1){
                DEV_RPT3 = -1;
                KTG_RPT3 = "-";
                holder.rpt3_data.setText("-");
                holder.rpt3_dev.setText("-");
            } else {
                if(currentItem.getRPT3() >= 1.2){
                    DEV_RPT3 = 0;
                    KTG_RPT3 = "LF";
                } else {
                    DEV_RPT3 = (currentItem.getRPT3() - 1.2)/1.2;
                    DEV_RPT3 = DEV_RPT3 * 100;
                    if(DEV_RPT3 < 0){
                        DEV_RPT3 = DEV_RPT3 * -1;
                    }
                    if(DEV_RPT3 > 100){
                        DEV_RPT3 = 100;
                    }
                    DEV_RPT3 = Double.parseDouble(df.format(DEV_RPT3).replace(",", "."));
                    KTG_RPT3 = "LS";
                }
                holder.rpt3_data.setText(String.valueOf(currentItem.getRPT3())+" m");
                holder.rpt3_dev.setText(String.valueOf(DEV_RPT3)+"%");
            }
        } catch (Exception e){
            Toast.makeText(a6A4Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            if(currentItem.getRPT4() == -1){
                DEV_RPT4 = -1;
                KTG_RPT4 = "-";
                holder.rpt4_data.setText("-");
                holder.rpt4_dev.setText("-");
            } else {
                if(currentItem.getRPT4() >= 0.45){
                    DEV_RPT4 = 0;
                    KTG_RPT4 = "LF";
                } else {
                    DEV_RPT4 = (currentItem.getRPT4() - 0.45)/0.45;
                    DEV_RPT4 = DEV_RPT4 * 100;
                    if(DEV_RPT4 < 0){
                        DEV_RPT4 = DEV_RPT4 * -1;
                    }
                    if(DEV_RPT4 > 100){
                        DEV_RPT4 = 100;
                    }
                    DEV_RPT4 = Double.parseDouble(df.format(DEV_RPT4).replace(",", "."));
                    KTG_RPT4 = "LS";
                }
                holder.rpt4_data.setText(String.valueOf(currentItem.getRPT4())+" m");
                holder.rpt4_dev.setText(String.valueOf(DEV_RPT4)+"%");
            }
        } catch (Exception e){
            Toast.makeText(a6A4Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            if((KTG_DGP.equals("LF") || KTG_DGP.equals("-")) && (KTG_DGL.equals("LF") || KTG_DGL.equals("-")) &&
                    (KTG_DGB.equals("LF") || KTG_DGB.equals("-")) && (KTG_DGJ.equals("LF") || KTG_DGJ.equals("-")) &&
                    (KTG_GPL.equals("LF") || KTG_GPL.equals("-")) &&(KTG_GPG.equals("LF") || KTG_GPG.equals("-")) &&
                    (KTG_CLU.equals("LF") || KTG_CLU.equals("-")) && (KTG_CLC.equals("LF") || KTG_CLC.equals("-")) &&
                    (KTG_CSC.equals("LF") || KTG_CSC.equals("-")) && (KTG_CBC.equals("LF") || KTG_CBC.equals("-")) &&
                    (KTG_RPT.equals("LF") || KTG_RPT.equals("-")) && (KTG_RPT2.equals("LF") || KTG_RPT2.equals("-")) &&
                    (KTG_RPT3.equals("LF") || KTG_RPT3.equals("-")) && (KTG_RPT4.equals("LF") || KTG_RPT4.equals("-"))){

                if(KTG_DGP.equals("-") && KTG_DGL.equals("-") && KTG_DGB.equals("-") && KTG_DGJ.equals("-") && KTG_GPL.equals("-") && KTG_GPG.equals("-") &&
                        KTG_CLU.equals("-") && KTG_CLC.equals("-") && KTG_CSC.equals("-") && KTG_CBC.equals("-") && KTG_RPT.equals("-") && KTG_RPT2.equals("-") &&
                        KTG_RPT3.equals("-") && KTG_RPT4.equals("-")){
                    KTG_DMU = "-";
                    holder.dmu_back.setBackground(a6A4Activity.getResources().getDrawable(R.drawable.null_style));
                } else {
                    KTG_DMU = "LF";
                    holder.dmu_back.setBackground(a6A4Activity.getResources().getDrawable(R.drawable.success_style));
                }

            } else {
                KTG_DMU = "LS";
                holder.dmu_back.setBackground(a6A4Activity.getResources().getDrawable(R.drawable.failed_style));
            }
            holder.dmu_hasil.setText(KTG_DMU);
        } catch (Exception e){
            Toast.makeText(a6A4Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            if((KTG_JLK.equals("LF") || KTG_JLK.equals("-")) && (KTG_TKM.equals("LF") || KTG_TKM.equals("-")) && (KTG_DMU.equals("LF") || KTG_DMU.equals("-"))){

                if(KTG_JLK.equals("-") && KTG_TKM.equals("-") && KTG_DMU.equals("-")){
                    KTG_KLF = "Tidak Dinilai";
                } else {
                    KTG_KLF = "LF";
                }

            } else {
                KTG_KLF = "LS";
            }
        } catch (Exception e){
            Toast.makeText(a6A4Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            holder.rec_data.setText(currentItem.getREC());
        } catch (Exception e){
            Toast.makeText(a6A4Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            holder.FT1.setImageBitmap(BitmapFactory.decodeFile(currentItem.getDIR1()));
        } catch (Exception e){
            Toast.makeText(a6A4Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            holder.FT2.setImageBitmap(BitmapFactory.decodeFile(currentItem.getDIR2()));
        } catch (Exception e){
            Toast.makeText(a6A4Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            if(currentItem.getUPLOAD().equals("F")){
                a6A4Activity.FAB_UPLOAD.hide();
                a6A4Activity.UPLOAD.setImageResource(R.drawable.ic_file_upload_white_18dp);
                a6A4Activity.FAB_UPLOAD.setEnabled(true);
            } else {
                a6A4Activity.UPLOAD.setImageResource(R.drawable.ic_done_white_18dp);
                a6A4Activity.FAB_UPLOAD.setEnabled(false);
            }
        } catch (Exception e){
            Toast.makeText(a6A4Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            a6A4Activity.ID = currentItem.getID();
            a6A4Activity.JLK_TXT = currentItem.getJLK();
            a6A4Activity.TKM_TXT = currentItem.getTKM();
            a6A4Activity.DGP_TXT = currentItem.getDGP();
            a6A4Activity.DGL_TXT = currentItem.getDGL();
            a6A4Activity.DGB_TXT = currentItem.getDGB();
            a6A4Activity.DGJ_TXT = currentItem.getDGJ();
            a6A4Activity.GPL_TXT = currentItem.getGPL();
            a6A4Activity.GPG_TXT = currentItem.getGPG();
            a6A4Activity.CLU_TXT = currentItem.getCLU();
            a6A4Activity.CLC_TXT = currentItem.getCLC();
            a6A4Activity.CSC_TXT = currentItem.getCSC();
            a6A4Activity.CBC_TXT = currentItem.getCBC();
            a6A4Activity.RPT_TXT = currentItem.getRPT();
            a6A4Activity.RPT2_TXT = currentItem.getRPT2();
            a6A4Activity.RPT3_TXT = currentItem.getRPT3();
            a6A4Activity.RPT4_TXT = currentItem.getRPT4();
            a6A4Activity.REC_TXT = currentItem.getREC();
            a6A4Activity.DIR1 = currentItem.getDIR1();
            a6A4Activity.DIR2 = currentItem.getDIR2();

            a6A4Activity.DEV_JLK = DEV_JLK;
            a6A4Activity.DEV_TKM = DEV_TKM;
            a6A4Activity.DEV_DGP = DEV_DGP;
            a6A4Activity.DEV_DGL = DEV_DGL;
            a6A4Activity.DEV_DGB = DEV_DGB;
            a6A4Activity.DEV_DGJ = DEV_DGJ;
            a6A4Activity.DEV_GPL = DEV_GPL;
            a6A4Activity.DEV_GPG = DEV_GPG;
            a6A4Activity.DEV_CLU = DEV_CLU;
            a6A4Activity.DEV_CLC = DEV_CLC;
            a6A4Activity.DEV_CSC = DEV_CSC;
            a6A4Activity.DEV_CBC = DEV_CBC;
            a6A4Activity.DEV_RPT = DEV_RPT;
            a6A4Activity.DEV_RPT2 = DEV_RPT2;
            a6A4Activity.DEV_RPT3 = DEV_RPT3;
            a6A4Activity.DEV_RPT4 = DEV_RPT4;

            a6A4Activity.KTG_JLK = KTG_JLK;
            a6A4Activity.KTG_TKM = KTG_TKM;
            a6A4Activity.KTG_DMU = KTG_DMU;
            a6A4Activity.KTG_KLF = KTG_KLF;

            if(KTG_KLF.equals("LS")){
                a6A4Activity.klf.setText(KTG_KLF);
                a6A4Activity.klf.setBackground(a6A4Activity.getResources().getDrawable(R.drawable.failed_style));
                Animation animation = AnimationUtils.loadAnimation(a6A4Activity.getApplicationContext(), R.anim.recycle_bottom);
                a6A4Activity.klf.startAnimation(animation);
            } else if(KTG_KLF.equals("Tidak Dinilai")){
                a6A4Activity.klf.setText("Tidak Dinilai");
                a6A4Activity.klf.setBackground(a6A4Activity.getResources().getDrawable(R.drawable.null_style));
                Animation animation = AnimationUtils.loadAnimation(a6A4Activity.getApplicationContext(), R.anim.recycle_bottom);
                a6A4Activity.klf.startAnimation(animation);
            } else {
                a6A4Activity.klf.setText(KTG_KLF);
                a6A4Activity.klf.setBackground(a6A4Activity.getResources().getDrawable(R.drawable.success_style));
                Animation animation = AnimationUtils.loadAnimation(a6A4Activity.getApplicationContext(), R.anim.recycle_bottom);
                a6A4Activity.klf.startAnimation(animation);
            }
        } catch (Exception e){
            Toast.makeText(a6A4Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public int getItemCount() {
        return mdataList.size();
    }

}