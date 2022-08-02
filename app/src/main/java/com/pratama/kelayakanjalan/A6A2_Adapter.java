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

public class A6A2_Adapter extends RecyclerView.Adapter<A6A2_Adapter.A6A2ViewHolder> {

    private A6A2Activity a6A2Activity;
    private List<A6A2_Class> mdataList;
    private double DEV_UDR, DEV_UWR, DEV_UWR2, DEV_UWR3, DEV_UWR4, DEV_UWR5, DEV_LJP, DEV_LJP2, DEV_LJP3, DEV_LJJ,
            DEV_LJJ2, DEV_LJT, DEV_LJT2, DEV_LJT3, DEV_LJT4, DEV_PPR, DEV_PPR2, DEV_PPR3, DEV_PPR4, STD = 100;
    private String KTG_UDR, KTG_UWR, KTG_UWR2, KTG_UWR3, KTG_UWR4, KTG_UWR5, KTG_UKW, KTG_LJP, KTG_LJP2, KTG_LJP3, KTG_LJJ,
            KTG_LJJ2, KTG_LJT, KTG_LJT2, KTG_LJT3, KTG_LJT4, KTG_LRJ, KTG_PPR, KTG_PPR2, KTG_PPR3, KTG_PPR4, KTG_PPRR, KTG_KLF;
    private static DecimalFormat df = new DecimalFormat("0.0");

    public class A6A2ViewHolder extends RecyclerView.ViewHolder {

        private TextView udr_data, uwr_data, uwr2_data, uwr3_data, uwr4_data, uwr5_data, ljp_data, ljp2_data, ljp3_data, ljj_data,
                ljj2_data, ljt_data, ljt2_data, ljt3_data, ljt4_data, ppr_data, ppr2_data, ppr3_data, ppr4_data, udr_dev, uwr_dev,
                uwr2_dev, uwr3_dev, uwr4_dev, uwr5_dev, ljp_dev, ljp2_dev, ljp3_dev, ljj_dev, ljj2_dev, ljt_dev, ljt2_dev, ljt3_dev,
                ljt4_dev, ppr_dev, ppr2_dev, ppr3_dev, ppr4_dev, ukw_hasil, lrj_hasil, ppr_hasil, rec_data;

        private LinearLayout ukw_back, lrj_back, ppr_back;
        private ImageView FT1, FT2, FT3, FT4;

        public A6A2ViewHolder(@NonNull View itemView) {
            super(itemView);

            udr_data = (TextView) itemView.findViewById(R.id.udr_data);
            udr_dev = (TextView) itemView.findViewById(R.id.udr_dev);
            uwr_data = (TextView) itemView.findViewById(R.id.uwr_data);
            uwr_dev = (TextView) itemView.findViewById(R.id.uwr_dev);
            uwr2_data = (TextView) itemView.findViewById(R.id.uwr2_data);
            uwr2_dev = (TextView) itemView.findViewById(R.id.uwr2_dev);
            uwr3_data = (TextView) itemView.findViewById(R.id.uwr3_data);
            uwr3_dev = (TextView) itemView.findViewById(R.id.uwr3_dev);
            uwr4_data = (TextView) itemView.findViewById(R.id.uwr4_data);
            uwr4_dev = (TextView) itemView.findViewById(R.id.uwr4_dev);
            uwr5_data = (TextView) itemView.findViewById(R.id.uwr5_data);
            uwr5_dev = (TextView) itemView.findViewById(R.id.uwr5_dev);
            ukw_hasil = (TextView) itemView.findViewById(R.id.ukw_hasil);

            ljp_data = (TextView) itemView.findViewById(R.id.ljp_data);
            ljp_dev = (TextView) itemView.findViewById(R.id.ljp_dev);
            ljp2_data = (TextView) itemView.findViewById(R.id.ljp2_data);
            ljp2_dev = (TextView) itemView.findViewById(R.id.ljp2_dev);
            ljp3_data = (TextView) itemView.findViewById(R.id.ljp3_data);
            ljp3_dev = (TextView) itemView.findViewById(R.id.ljp3_dev);
            ljj_data = (TextView) itemView.findViewById(R.id.ljj_data);
            ljj_dev = (TextView) itemView.findViewById(R.id.ljj_dev);
            ljj2_data = (TextView) itemView.findViewById(R.id.ljj2_data);
            ljj2_dev = (TextView) itemView.findViewById(R.id.ljj2_dev);
            ljt_data = (TextView) itemView.findViewById(R.id.ljt_data);
            ljt_dev = (TextView) itemView.findViewById(R.id.ljt_dev);
            ljt2_data = (TextView) itemView.findViewById(R.id.ljt2_data);
            ljt2_dev = (TextView) itemView.findViewById(R.id.ljt2_dev);
            ljt3_data = (TextView) itemView.findViewById(R.id.ljt3_data);
            ljt3_dev = (TextView) itemView.findViewById(R.id.ljt3_dev);
            ljt4_data = (TextView) itemView.findViewById(R.id.ljt4_data);
            ljt4_dev = (TextView) itemView.findViewById(R.id.ljt4_dev);
            lrj_hasil = (TextView) itemView.findViewById(R.id.lrj_hasil);

            ppr_data = (TextView) itemView.findViewById(R.id.ppr_data);
            ppr_dev = (TextView) itemView.findViewById(R.id.ppr_dev);
            ppr2_data = (TextView) itemView.findViewById(R.id.ppr2_data);
            ppr2_dev = (TextView) itemView.findViewById(R.id.ppr2_dev);
            ppr3_data = (TextView) itemView.findViewById(R.id.ppr3_data);
            ppr3_dev = (TextView) itemView.findViewById(R.id.ppr3_dev);
            ppr4_data = (TextView) itemView.findViewById(R.id.ppr4_data);
            ppr4_dev = (TextView) itemView.findViewById(R.id.ppr4_dev);
            ppr_hasil = (TextView) itemView.findViewById(R.id.ppr_hasil);

            rec_data = (TextView) itemView.findViewById(R.id.rec_data);

            FT1 = (ImageView) itemView.findViewById(R.id.FT1);
            FT2 = (ImageView) itemView.findViewById(R.id.FT2);
            FT3 = (ImageView) itemView.findViewById(R.id.FT3);
            FT4 = (ImageView) itemView.findViewById(R.id.FT4);

            ukw_back = (LinearLayout) itemView.findViewById(R.id.ukw_back);
            lrj_back = (LinearLayout) itemView.findViewById(R.id.lrj_back);
            ppr_back = (LinearLayout) itemView.findViewById(R.id.ppr_back);

        }
    }

    public A6A2_Adapter(A6A2Activity a6A2Activity, List<A6A2_Class> mdataList) {
        this.a6A2Activity = a6A2Activity;
        this.mdataList = mdataList;
    }

    @NonNull
    @Override
    public A6A2_Adapter.A6A2ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.a6a2_layout, parent, false);
        A6A2_Adapter.A6A2ViewHolder a6a2 = new A6A2_Adapter.A6A2ViewHolder(view);
        return a6a2;
    }

    @Override
    public void onBindViewHolder(@NonNull A6A2_Adapter.A6A2ViewHolder holder, int position) {

        final A6A2_Class currentItem = mdataList.get(position);

        try {
            if(currentItem.getUDR() == -1){
                DEV_UDR = -1;
                KTG_UDR = "-";
                holder.udr_data.setText("-");
                holder.udr_dev.setText("-");
            } else {
                if(a6A2Activity.KCP <= 30){
                    if(currentItem.getUDR() >= 450){
                        DEV_UDR = 0;
                        KTG_UDR = "LF";
                    } else {
                        DEV_UDR = (currentItem.getUDR() - 450)/450;
                        DEV_UDR = DEV_UDR * 100;
                        if(DEV_UDR < 0){
                            DEV_UDR = DEV_UDR * -1;
                        }
                        if(DEV_UDR > 100){
                            DEV_UDR = 100;
                        }
                        DEV_UDR = Double.parseDouble(df.format(DEV_UDR).replace(",", "."));
                        KTG_UDR = "LS";
                    }
                } else if(a6A2Activity.KCP <= 60){
                    if(currentItem.getUDR() >= 600){
                        DEV_UDR = 0;
                        KTG_UDR = "LF";
                    } else {
                        DEV_UDR = (currentItem.getUDR() - 600)/600;
                        DEV_UDR = DEV_UDR * 100;
                        if(DEV_UDR < 0){
                            DEV_UDR = DEV_UDR * -1;
                        }
                        if(DEV_UDR > 100){
                            DEV_UDR = 100;
                        }
                        DEV_UDR = Double.parseDouble(df.format(DEV_UDR).replace(",", "."));
                        KTG_UDR = "LS";
                    }
                } else if(a6A2Activity.KCP <= 80){
                    if(currentItem.getUDR() >= 750){
                        DEV_UDR = 0;
                        KTG_UDR = "LF";
                    } else {
                        DEV_UDR = (currentItem.getUDR() - 750)/750;
                        DEV_UDR = DEV_UDR * 100;
                        if(DEV_UDR < 0){
                            DEV_UDR = DEV_UDR * -1;
                        }
                        if(DEV_UDR > 100){
                            DEV_UDR = 100;
                        }
                        DEV_UDR = Double.parseDouble(df.format(DEV_UDR).replace(",", "."));
                        KTG_UDR = "LS";
                    }
                } else {
                    if(currentItem.getUDR() >= 900){
                        DEV_UDR = 0;
                        KTG_UDR = "LF";
                    } else {
                        DEV_UDR = (currentItem.getUDR() - 900)/900;
                        DEV_UDR = DEV_UDR * 100;
                        if(DEV_UDR < 0){
                            DEV_UDR = DEV_UDR * -1;
                        }
                        if(DEV_UDR > 100){
                            DEV_UDR = 100;
                        }
                        DEV_UDR = Double.parseDouble(df.format(DEV_UDR).replace(",", "."));
                        KTG_UDR = "LS";
                    }
                }
                holder.udr_data.setText(String.valueOf(currentItem.getUDR())+" mm");
                holder.udr_dev.setText(String.valueOf(DEV_UDR)+"%");
            }
        } catch (Exception e){
            Toast.makeText(a6A2Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            if(currentItem.getUWR() == -1){
                DEV_UWR = -1;
                KTG_UWR = "-";
                holder.uwr_data.setText("-");
                holder.uwr_dev.setText("-");
            } else {
                DEV_UWR = STD - currentItem.getUWR();
                if(DEV_UWR == 0){
                    KTG_UWR = "LF";
                } else {
                    KTG_UWR = "LS";
                }
                holder.uwr_data.setText(String.valueOf(currentItem.getUWR())+"%");
                holder.uwr_dev.setText(String.valueOf(DEV_UWR)+"%");
            }
        } catch (Exception e){
            Toast.makeText(a6A2Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            if(currentItem.getUWR2() == -1){
                DEV_UWR2 = -1;
                KTG_UWR2 = "-";
                holder.uwr2_data.setText("-");
                holder.uwr2_dev.setText("-");
            } else {
                DEV_UWR2 = STD - currentItem.getUWR2();
                if(DEV_UWR2 == 0){
                    KTG_UWR2 = "LF";
                } else {
                    KTG_UWR2 = "LS";
                }
                holder.uwr2_data.setText(String.valueOf(currentItem.getUWR2())+"%");
                holder.uwr2_dev.setText(String.valueOf(DEV_UWR2)+"%");
            }
        } catch (Exception e){
            Toast.makeText(a6A2Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            if(currentItem.getUWR3() == -1){
                DEV_UWR3 = -1;
                KTG_UWR3 = "-";
                holder.uwr3_data.setText("-");
                holder.uwr3_dev.setText("-");
            } else {
                DEV_UWR3 = STD - currentItem.getUWR3();
                if(DEV_UWR3 == 0){
                    KTG_UWR3 = "LF";
                } else {
                    KTG_UWR3 = "LS";
                }
                holder.uwr3_data.setText(String.valueOf(currentItem.getUWR3())+"%");
                holder.uwr3_dev.setText(String.valueOf(DEV_UWR3)+"%");
            }
        } catch (Exception e){
            Toast.makeText(a6A2Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            if(currentItem.getUWR4() == -1){
                DEV_UWR4 = -1;
                KTG_UWR4 = "-";
                holder.uwr4_data.setText("-");
                holder.uwr4_dev.setText("-");
            } else {
                DEV_UWR4 = STD - currentItem.getUWR4();
                if(DEV_UWR4 == 0){
                    KTG_UWR4 = "LF";
                } else {
                    KTG_UWR4 = "LS";
                }
                holder.uwr4_data.setText(String.valueOf(currentItem.getUWR4())+"%");
                holder.uwr4_dev.setText(String.valueOf(DEV_UWR4)+"%");
            }
        } catch (Exception e){
            Toast.makeText(a6A2Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            if(currentItem.getUWR5() == -1){
                DEV_UWR5 = -1;
                KTG_UWR5 = "-";
                holder.uwr5_data.setText("-");
                holder.uwr5_dev.setText("-");
            } else {
                DEV_UWR5 = STD - currentItem.getUWR5();
                if(DEV_UWR5 == 0){
                    KTG_UWR5 = "LF";
                } else {
                    KTG_UWR5 = "LS";
                }
                holder.uwr5_data.setText(String.valueOf(currentItem.getUWR5())+"%");
                holder.uwr5_dev.setText(String.valueOf(DEV_UWR5)+"%");
            }
        } catch (Exception e){
            Toast.makeText(a6A2Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            if((KTG_UDR.equals("LF") || KTG_UDR.equals("-")) && (KTG_UWR.equals("LF") || KTG_UWR.equals("-")) && (KTG_UWR2.equals("LF") || KTG_UWR2.equals("-")) &&
                    (KTG_UWR3.equals("LF") || KTG_UWR3.equals("-")) && (KTG_UWR4.equals("LF") || KTG_UWR4.equals("-")) && (KTG_UWR5.equals("LF") || KTG_UWR5.equals("-"))){

                if(KTG_UDR.equals("-") && KTG_UWR.equals("-") && KTG_UWR2.equals("-") && KTG_UWR3.equals("-") && KTG_UWR4.equals("-") && KTG_UWR5.equals("-")){
                    KTG_UKW = "-";
                    holder.ukw_back.setBackground(a6A2Activity.getResources().getDrawable(R.drawable.null_style));
                } else {
                    KTG_UKW = "LF";
                    holder.ukw_back.setBackground(a6A2Activity.getResources().getDrawable(R.drawable.success_style));
                }

            } else {
                KTG_UKW = "LS";
                holder.ukw_back.setBackground(a6A2Activity.getResources().getDrawable(R.drawable.failed_style));
            }
            holder.ukw_hasil.setText(KTG_UKW);
        } catch (Exception e){
            Toast.makeText(a6A2Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            if(currentItem.getLJP() == -1){
                DEV_LJP = -1;
                KTG_LJP = "-";
                holder.ljp_data.setText("-");
                holder.ljp_dev.setText("-");
            } else {
                DEV_LJP = STD - currentItem.getLJP();
                if(DEV_LJP == 0){
                    KTG_LJP = "LF";
                } else {
                    KTG_LJP = "LS";
                }
                holder.ljp_data.setText(String.valueOf(currentItem.getLJP())+"%");
                holder.ljp_dev.setText(String.valueOf(DEV_LJP)+"%");
            }
        } catch (Exception e){
            Toast.makeText(a6A2Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            if(currentItem.getLJP2() == -1){
                DEV_LJP2 = -1;
                KTG_LJP2 = "-";
                holder.ljp2_data.setText("-");
                holder.ljp2_dev.setText("-");
            } else {
                DEV_LJP2 = STD - currentItem.getLJP2();
                if(DEV_LJP2 == 0){
                    KTG_LJP2 = "LF";
                } else {
                    KTG_LJP2 = "LS";
                }
                holder.ljp2_data.setText(String.valueOf(currentItem.getLJP2())+"%");
                holder.ljp2_dev.setText(String.valueOf(DEV_LJP2)+"%");
            }
        } catch (Exception e){
            Toast.makeText(a6A2Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            if(currentItem.getLJP3() == -1){
                DEV_LJP3 = -1;
                KTG_LJP3 = "-";
                holder.ljp3_data.setText("-");
                holder.ljp3_dev.setText("-");
            } else {
                DEV_LJP3 = STD - currentItem.getLJP3();
                if(DEV_LJP3 == 0){
                    KTG_LJP3 = "LF";
                } else {
                    KTG_LJP3 = "LS";
                }
                holder.ljp3_data.setText(String.valueOf(currentItem.getLJP3())+"%");
                holder.ljp3_dev.setText(String.valueOf(DEV_LJP3)+"%");
            }
        } catch (Exception e){
            Toast.makeText(a6A2Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            if(currentItem.getLJJ() == -1){
                DEV_LJJ = -1;
                KTG_LJJ = "-";
                holder.ljj_data.setText("-");
                holder.ljj_dev.setText("-");
            } else {
                if(currentItem.getLJJ() >= 0.6){
                    DEV_LJJ = 0;
                    KTG_LJJ = "LF";
                } else {
                    DEV_LJJ = (currentItem.getLJJ() - 0.6)/0.6;
                    DEV_LJJ = DEV_LJJ * 100;
                    if(DEV_LJJ < 0){
                        DEV_LJJ = DEV_LJJ * -1;
                    }
                    if(DEV_LJJ > 100){
                        DEV_LJJ = 100;
                    }
                    DEV_LJJ = Double.parseDouble(df.format(DEV_LJJ).replace(",", "."));
                    KTG_LJJ = "LS";
                }
                holder.ljj_data.setText(String.valueOf(currentItem.getLJJ())+" m");
                holder.ljj_dev.setText(String.valueOf(DEV_LJJ)+"%");
            }
        } catch (Exception e){
            Toast.makeText(a6A2Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            if(currentItem.getLJJ2() == -1){
                DEV_LJJ2 = -1;
                KTG_LJJ2 = "-";
                holder.ljj2_data.setText("-");
                holder.ljj2_dev.setText("-");
            } else {
                if(currentItem.getLJJ2() >= 0.3){
                    DEV_LJJ2 = 0;
                    KTG_LJJ2 = "LF";
                } else {
                    DEV_LJJ2 = (currentItem.getLJJ2() - 0.3)/0.3;
                    DEV_LJJ2 = DEV_LJJ2 * 100;
                    if(DEV_LJJ2 < 0){
                        DEV_LJJ2 = DEV_LJJ2 * -1;
                    }
                    if(DEV_LJJ2 > 100){
                        DEV_LJJ2 = 100;
                    }
                    DEV_LJJ2 = Double.parseDouble(df.format(DEV_LJJ2).replace(",", "."));
                    KTG_LJJ2 = "LS";
                }
                holder.ljj2_data.setText(String.valueOf(currentItem.getLJJ2())+" m");
                holder.ljj2_dev.setText(String.valueOf(DEV_LJJ2)+"%");
            }
        } catch (Exception e){
            Toast.makeText(a6A2Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            if(currentItem.getLJT() == -1){
                DEV_LJT = -1;
                KTG_LJT = "-";
                holder.ljt_data.setText("-");
                holder.ljt_dev.setText("-");
            } else {
                if(currentItem.getLJT() >= 1.75){
                    DEV_LJT = 0;
                    KTG_LJT = "LF";
                } else {
                    DEV_LJT = (currentItem.getLJT() - 1.75)/1.75;
                    DEV_LJT = DEV_LJT * 100;
                    if(DEV_LJT < 0){
                        DEV_LJT = DEV_LJT * -1;
                    }
                    if(DEV_LJT > 100){
                        DEV_LJT = 100;
                    }
                    DEV_LJT = Double.parseDouble(df.format(DEV_LJT).replace(",", "."));
                    KTG_LJT = "LS";
                }
                holder.ljt_data.setText(String.valueOf(currentItem.getLJT())+" m");
                holder.ljt_dev.setText(String.valueOf(DEV_LJT)+"%");
            }
        } catch (Exception e){
            Toast.makeText(a6A2Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            if(currentItem.getLJT2() == -1){
                DEV_LJT2 = -1;
                KTG_LJT2 = "-";
                holder.ljt2_data.setText("-");
                holder.ljt2_dev.setText("-");
            } else {
                if(currentItem.getLJT2() >= 2){
                    DEV_LJT2 = 0;
                    KTG_LJT2 = "LF";
                } else {
                    DEV_LJT2 = (currentItem.getLJT2() - 2)/2;
                    DEV_LJT2 = DEV_LJT2 * 100;
                    if(DEV_LJT2 < 0){
                        DEV_LJT2 = DEV_LJT2 * -1;
                    }
                    if(DEV_LJT2 > 100){
                        DEV_LJT2 = 100;
                    }
                    DEV_LJT2 = Double.parseDouble(df.format(DEV_LJT2).replace(",", "."));
                    KTG_LJT2 = "LS";
                }
                holder.ljt2_data.setText(String.valueOf(currentItem.getLJT2())+" m");
                holder.ljt2_dev.setText(String.valueOf(DEV_LJT2)+"%");
            }
        } catch (Exception e){
            Toast.makeText(a6A2Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            if(currentItem.getLJT3() == -1){
                DEV_LJT3 = -1;
                KTG_LJT3 = "-";
                holder.ljt3_data.setText("-");
                holder.ljt3_dev.setText("-");
            } else {
                if(currentItem.getLJT3() >= 1.2){
                    DEV_LJT3 = 0;
                    KTG_LJT3 = "LF";
                } else {
                    DEV_LJT3 = (currentItem.getLJT3() - 1.2)/1.2;
                    DEV_LJT3 = DEV_LJT3 * 100;
                    if(DEV_LJT3 < 0){
                        DEV_LJT3 = DEV_LJT3 * -1;
                    }
                    if(DEV_LJT3 > 100){
                        DEV_LJT3 = 100;
                    }
                    DEV_LJT3 = Double.parseDouble(df.format(DEV_LJT3).replace(",", "."));
                    KTG_LJT3 = "LS";
                }
                holder.ljt3_data.setText(String.valueOf(currentItem.getLJT3())+" m");
                holder.ljt3_dev.setText(String.valueOf(DEV_LJT3)+"%");
            }
        } catch (Exception e){
            Toast.makeText(a6A2Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            if(currentItem.getLJT4() == -1){
                DEV_LJT4 = -1;
                KTG_LJT4 = "-";
                holder.ljt4_data.setText("-");
                holder.ljt4_dev.setText("-");
            } else {
                if(currentItem.getLJT4() >= 5){
                    DEV_LJT4 = 0;
                    KTG_LJT4 = "LF";
                } else {
                    DEV_LJT4 = (currentItem.getLJT4() - 5)/5;
                    DEV_LJT4 = DEV_LJT4 * 100;
                    if(DEV_LJT4 < 0){
                        DEV_LJT4 = DEV_LJT4 * -1;
                    }
                    if(DEV_LJT4 > 100){
                        DEV_LJT4 = 100;
                    }
                    DEV_LJT4 = Double.parseDouble(df.format(DEV_LJT4).replace(",", "."));
                    KTG_LJT4 = "LS";
                }
                holder.ljt4_data.setText(String.valueOf(currentItem.getLJT4())+" m");
                holder.ljt4_dev.setText(String.valueOf(DEV_LJT4)+"%");
            }
        } catch (Exception e){
            Toast.makeText(a6A2Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            if((KTG_LJP.equals("LF") || KTG_LJP.equals("-")) && (KTG_LJP2.equals("LF") || KTG_LJP2.equals("-")) && (KTG_LJP3.equals("LF") || KTG_LJP3.equals("-")) &&
                    (KTG_LJJ.equals("LF") || KTG_LJJ.equals("-")) && (KTG_LJJ2.equals("LF") || KTG_LJJ2.equals("-")) && (KTG_LJT.equals("LF") || KTG_LJT.equals("-")) &&
                    (KTG_LJT2.equals("LF") || KTG_LJT2.equals("-")) && (KTG_LJT3.equals("LF") || KTG_LJT3.equals("-")) && (KTG_LJT4.equals("LF") || KTG_LJT4.equals("-"))){

                if(KTG_LJP.equals("-") && KTG_LJP2.equals("-") && KTG_LJP3.equals("-") && KTG_LJJ.equals("-") && KTG_LJJ2.equals("-") && KTG_LJT.equals("-") &&
                        KTG_LJT2.equals("-") && KTG_LJT3.equals("-") && KTG_LJT4.equals("-")){
                    KTG_LRJ = "-";
                    holder.lrj_back.setBackground(a6A2Activity.getResources().getDrawable(R.drawable.null_style));
                } else {
                    KTG_LRJ = "LF";
                    holder.lrj_back.setBackground(a6A2Activity.getResources().getDrawable(R.drawable.success_style));
                }

            } else {
                KTG_LRJ = "LS";
                holder.lrj_back.setBackground(a6A2Activity.getResources().getDrawable(R.drawable.failed_style));
            }
            holder.lrj_hasil.setText(KTG_LRJ);
        } catch (Exception e){
            Toast.makeText(a6A2Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            if(currentItem.getPPR() == -1){
                DEV_PPR = -1;
                KTG_PPR = "-";
                holder.ppr_data.setText("-");
                holder.ppr_dev.setText("-");
            } else {
                if(currentItem.getPPR() >= 0.6){
                    DEV_PPR = 0;
                    KTG_PPR = "LF";
                } else {
                    DEV_PPR = (currentItem.getPPR() - 0.6)/0.6;
                    DEV_PPR = DEV_PPR * 100;
                    if(DEV_PPR < 0){
                        DEV_PPR = DEV_PPR * -1;
                    }
                    if(DEV_PPR > 100){
                        DEV_PPR = 100;
                    }
                    DEV_PPR = Double.parseDouble(df.format(DEV_PPR).replace(",", "."));
                    KTG_PPR = "LS";
                }
                holder.ppr_data.setText(String.valueOf(currentItem.getPPR())+" m");
                holder.ppr_dev.setText(String.valueOf(DEV_PPR)+"%");
            }
        } catch (Exception e){
            Toast.makeText(a6A2Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            if(currentItem.getPPR2() == -1){
                DEV_PPR2 = -1;
                KTG_PPR2 = "-";
                holder.ppr2_data.setText("-");
                holder.ppr2_dev.setText("-");
            } else {
                if(currentItem.getPPR2() >= 0.25){
                    DEV_PPR2 = 0;
                    KTG_PPR2 = "LF";
                } else {
                    DEV_PPR2 = (currentItem.getPPR2() - 0.25)/0.25;
                    DEV_PPR2 = DEV_PPR2 * 100;
                    if(DEV_PPR2 < 0){
                        DEV_PPR2 = DEV_PPR2 * -1;
                    }
                    if(DEV_PPR2 > 100){
                        DEV_PPR2 = 100;
                    }
                    DEV_PPR2 = Double.parseDouble(df.format(DEV_PPR2).replace(",", "."));
                    KTG_PPR2 = "LS";
                }
                holder.ppr2_data.setText(String.valueOf(currentItem.getPPR2())+" m");
                holder.ppr2_dev.setText(String.valueOf(DEV_PPR2)+"%");
            }
        } catch (Exception e){
            Toast.makeText(a6A2Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            if(currentItem.getPPR3() == -1){
                DEV_PPR3 = -1;
                KTG_PPR3 = "-";
                holder.ppr3_data.setText("-");
                holder.ppr3_dev.setText("-");
            } else {
                DEV_PPR3 = STD - currentItem.getPPR3();
                if(DEV_PPR3 == 0){
                    KTG_PPR3 = "LF";
                } else {
                    KTG_PPR3 = "LS";
                }
                holder.ppr3_data.setText(String.valueOf(currentItem.getPPR3())+"%");
                holder.ppr3_dev.setText(String.valueOf(DEV_PPR3)+"%");
            }
        } catch (Exception e){
            Toast.makeText(a6A2Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            if(currentItem.getPPR4() == -1){
                DEV_PPR4 = -1;
                KTG_PPR4 = "-";
                holder.ppr4_data.setText("-");
                holder.ppr4_dev.setText("-");
            } else {
                DEV_PPR4 = STD - currentItem.getPPR4();
                if(DEV_PPR4 == 0){
                    KTG_PPR4 = "LF";
                } else {
                    KTG_PPR4 = "LS";
                }
                holder.ppr4_data.setText(String.valueOf(currentItem.getPPR4())+"%");
                holder.ppr4_dev.setText(String.valueOf(DEV_PPR4)+"%");
            }
        } catch (Exception e){
            Toast.makeText(a6A2Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            if((KTG_PPR.equals("LF") || KTG_PPR.equals("-")) && (KTG_PPR2.equals("LF") || KTG_PPR2.equals("-")) &&
                    (KTG_PPR3.equals("LF") || KTG_PPR3.equals("-")) && (KTG_PPR4.equals("LF") || KTG_PPR4.equals("-"))){

                if(KTG_PPR.equals("-") && KTG_PPR2.equals("-") && KTG_PPR3.equals("-") && KTG_PPR4.equals("-")){
                    KTG_PPRR = "-";
                    holder.ppr_back.setBackground(a6A2Activity.getResources().getDrawable(R.drawable.null_style));
                } else {
                    KTG_PPRR = "LF";
                    holder.ppr_back.setBackground(a6A2Activity.getResources().getDrawable(R.drawable.success_style));
                }

            } else {
                KTG_PPRR = "LS";
                holder.ppr_back.setBackground(a6A2Activity.getResources().getDrawable(R.drawable.failed_style));
            }
            holder.ppr_hasil.setText(KTG_PPRR);
        } catch (Exception e){
            Toast.makeText(a6A2Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            if((KTG_UKW.equals("LF") || KTG_UKW.equals("-")) && (KTG_LRJ.equals("LF") || KTG_LRJ.equals("-")) && (KTG_PPRR.equals("LF") || KTG_PPRR.equals("-"))){

                if(KTG_UKW.equals("-") && KTG_LRJ.equals("-") && KTG_PPRR.equals("-")){
                    KTG_KLF = "Tidak Dinilai";
                } else {
                    KTG_KLF = "LF";
                }

            } else {
                KTG_KLF = "LS";
            }
        } catch (Exception e){
            Toast.makeText(a6A2Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            holder.rec_data.setText(currentItem.getREC());
        } catch (Exception e){
            Toast.makeText(a6A2Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            holder.FT1.setImageBitmap(BitmapFactory.decodeFile(currentItem.getDIR1()));
        } catch (Exception e){
            Toast.makeText(a6A2Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            holder.FT2.setImageBitmap(BitmapFactory.decodeFile(currentItem.getDIR2()));
        } catch (Exception e){
            Toast.makeText(a6A2Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            holder.FT3.setImageBitmap(BitmapFactory.decodeFile(currentItem.getDIR3()));
        } catch (Exception e){
            Toast.makeText(a6A2Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            holder.FT4.setImageBitmap(BitmapFactory.decodeFile(currentItem.getDIR4()));
        } catch (Exception e){
            Toast.makeText(a6A2Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            if(currentItem.getUPLOAD().equals("F")){
                a6A2Activity.FAB_UPLOAD.hide();
                a6A2Activity.UPLOAD.setImageResource(R.drawable.ic_file_upload_white_18dp);
                a6A2Activity.FAB_UPLOAD.setEnabled(true);
            } else {
                a6A2Activity.UPLOAD.setImageResource(R.drawable.ic_done_white_18dp);
                a6A2Activity.FAB_UPLOAD.setEnabled(false);
            }
        } catch (Exception e){
            Toast.makeText(a6A2Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            a6A2Activity.ID = currentItem.getID();
            a6A2Activity.UDR_TXT = currentItem.getUDR();
            a6A2Activity.UWR_TXT = currentItem.getUWR();
            a6A2Activity.UWR2_TXT = currentItem.getUWR2();
            a6A2Activity.UWR3_TXT = currentItem.getUWR3();
            a6A2Activity.UWR4_TXT = currentItem.getUWR4();
            a6A2Activity.UWR5_TXT = currentItem.getUWR5();
            a6A2Activity.LJP_TXT = currentItem.getLJP();
            a6A2Activity.LJP2_TXT = currentItem.getLJP2();
            a6A2Activity.LJP3_TXT = currentItem.getLJP3();
            a6A2Activity.LJJ_TXT = currentItem.getLJJ();
            a6A2Activity.LJJ2_TXT = currentItem.getLJJ2();
            a6A2Activity.LJT_TXT = currentItem.getLJT();
            a6A2Activity.LJT2_TXT = currentItem.getLJT2();
            a6A2Activity.LJT3_TXT = currentItem.getLJT3();
            a6A2Activity.LJT4_TXT = currentItem.getLJT4();
            a6A2Activity.PPR_TXT = currentItem.getPPR();
            a6A2Activity.PPR2_TXT = currentItem.getPPR2();
            a6A2Activity.PPR3_TXT = currentItem.getPPR3();
            a6A2Activity.PPR4_TXT = currentItem.getPPR4();
            a6A2Activity.REC_TXT = currentItem.getREC();
            a6A2Activity.DIR1 = currentItem.getDIR1();
            a6A2Activity.DIR2 = currentItem.getDIR2();
            a6A2Activity.DIR3 = currentItem.getDIR3();
            a6A2Activity.DIR4 = currentItem.getDIR4();

            a6A2Activity.DEV_UDR = DEV_UDR;
            a6A2Activity.DEV_UWR = DEV_UWR;
            a6A2Activity.DEV_UWR2 = DEV_UWR2;
            a6A2Activity.DEV_UWR3 = DEV_UWR3;
            a6A2Activity.DEV_UWR4 = DEV_UWR4;
            a6A2Activity.DEV_UWR5 = DEV_UWR5;
            a6A2Activity.DEV_LJP = DEV_LJP;
            a6A2Activity.DEV_LJP2 = DEV_LJP2;
            a6A2Activity.DEV_LJP3 = DEV_LJP3;
            a6A2Activity.DEV_LJJ = DEV_LJJ;
            a6A2Activity.DEV_LJJ2 = DEV_LJJ2;
            a6A2Activity.DEV_LJT = DEV_LJT;
            a6A2Activity.DEV_LJT2 = DEV_LJT2;
            a6A2Activity.DEV_LJT3 = DEV_LJT3;
            a6A2Activity.DEV_LJT4 = DEV_LJT4;
            a6A2Activity.DEV_PPR = DEV_PPR;
            a6A2Activity.DEV_PPR2 = DEV_PPR2;
            a6A2Activity.DEV_PPR3 = DEV_PPR3;
            a6A2Activity.DEV_PPR4 = DEV_PPR4;

            a6A2Activity.KTG_UKW = KTG_UKW;
            a6A2Activity.KTG_LRJ = KTG_LRJ;
            a6A2Activity.KTG_PPRR = KTG_PPRR;
            a6A2Activity.KTG_KLF = KTG_KLF;

            if(KTG_KLF.equals("LS")){
                a6A2Activity.klf.setText(KTG_KLF);
                a6A2Activity.klf.setBackground(a6A2Activity.getResources().getDrawable(R.drawable.failed_style));
                Animation animation = AnimationUtils.loadAnimation(a6A2Activity.getApplicationContext(), R.anim.recycle_bottom);
                a6A2Activity.klf.startAnimation(animation);
            } else if(KTG_KLF.equals("Tidak Dinilai")){
                a6A2Activity.klf.setText("Tidak Dinilai");
                a6A2Activity.klf.setBackground(a6A2Activity.getResources().getDrawable(R.drawable.null_style));
                Animation animation = AnimationUtils.loadAnimation(a6A2Activity.getApplicationContext(), R.anim.recycle_bottom);
                a6A2Activity.klf.startAnimation(animation);
            } else {
                a6A2Activity.klf.setText(KTG_KLF);
                a6A2Activity.klf.setBackground(a6A2Activity.getResources().getDrawable(R.drawable.success_style));
                Animation animation = AnimationUtils.loadAnimation(a6A2Activity.getApplicationContext(), R.anim.recycle_bottom);
                a6A2Activity.klf.startAnimation(animation);
            }
        } catch (Exception e){
            Toast.makeText(a6A2Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public int getItemCount() {
        return mdataList.size();
    }

}