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

public class A6B2_Adapter extends RecyclerView.Adapter<A6B2_Adapter.A6B2ViewHolder> {

    private A6B2Activity a6B2Activity;
    private List<A6B2_Class> mdataList;
    private double DEV_KKH, DEV_DLD, DEV_DLD2, DEV_DLD3, DEV_DLL, DEV_DLL2, DEV_DLW, DEV_DLT, DEV_KFB, STD = 100;
    private String KTG_KKH, KTG_DLD, KTG_DLD2, KTG_DLD3, KTG_DLL, KTG_DLL2, KTG_DLW, KTG_DLT, KTG_DBT, KTG_KFB, KTG_KLF;
    private static DecimalFormat df = new DecimalFormat("0.0");

    public class A6B2ViewHolder extends RecyclerView.ViewHolder {

        private TextView kkh_data, kkh_dev, kkh_hasil, dld_data, dld_dev, dld2_data, dld2_dev, dld3_data, dld3_dev,
                dll_data, dll_dev, dll2_data, dll2_dev, dlw_data, dlw_dev, dlt_data, dlt_dev, dbt_hasil, kfb_data, kfb_dev, kfb_hasil, rec_data;
        private LinearLayout kkh_back, dbt_back, kfb_back;
        private ImageView FT1, FT2;

        public A6B2ViewHolder(@NonNull View itemView) {
            super(itemView);

            kkh_data = (TextView) itemView.findViewById(R.id.kkh_data);
            kkh_dev = (TextView) itemView.findViewById(R.id.kkh_dev);
            kkh_hasil = (TextView) itemView.findViewById(R.id.kkh_hasil);

            dld_data = (TextView) itemView.findViewById(R.id.dld_data);
            dld_dev = (TextView) itemView.findViewById(R.id.dld_dev);
            dld2_data = (TextView) itemView.findViewById(R.id.dld2_data);
            dld2_dev = (TextView) itemView.findViewById(R.id.dld2_dev);
            dld3_data = (TextView) itemView.findViewById(R.id.dld3_data);
            dld3_dev = (TextView) itemView.findViewById(R.id.dld3_dev);
            dll_data = (TextView) itemView.findViewById(R.id.dll_data);
            dll_dev = (TextView) itemView.findViewById(R.id.dll_dev);
            dll2_data = (TextView) itemView.findViewById(R.id.dll2_data);
            dll2_dev = (TextView) itemView.findViewById(R.id.dll2_dev);
            dlw_data = (TextView) itemView.findViewById(R.id.dlw_data);
            dlw_dev = (TextView) itemView.findViewById(R.id.dlw_dev);
            dlt_data = (TextView) itemView.findViewById(R.id.dlt_data);
            dlt_dev = (TextView) itemView.findViewById(R.id.dlt_dev);
            dbt_hasil = (TextView) itemView.findViewById(R.id.dbt_hasil);

            kfb_data = (TextView) itemView.findViewById(R.id.kfb_data);
            kfb_dev = (TextView) itemView.findViewById(R.id.kfb_dev);
            kfb_hasil = (TextView) itemView.findViewById(R.id.kfb_hasil);

            rec_data = (TextView) itemView.findViewById(R.id.rec_data);

            FT1 = (ImageView) itemView.findViewById(R.id.FT1);
            FT2 = (ImageView) itemView.findViewById(R.id.FT2);

            kkh_back = (LinearLayout) itemView.findViewById(R.id.kkh_back);
            dbt_back = (LinearLayout) itemView.findViewById(R.id.dbt_back);
            kfb_back = (LinearLayout) itemView.findViewById(R.id.kfb_back);

        }
    }

    public A6B2_Adapter(A6B2Activity a6B2Activity, List<A6B2_Class> mdataList) {
        this.a6B2Activity = a6B2Activity;
        this.mdataList = mdataList;
    }

    @NonNull
    @Override
    public A6B2_Adapter.A6B2ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.a6b2_layout, parent, false);
        A6B2_Adapter.A6B2ViewHolder a6b2 = new A6B2_Adapter.A6B2ViewHolder(view);
        return a6b2;
    }

    @Override
    public void onBindViewHolder(@NonNull A6B2_Adapter.A6B2ViewHolder holder, int position) {

        final A6B2_Class currentItem = mdataList.get(position);

        try {
            if(currentItem.getKKH() == -1){
                DEV_KKH = -1;
                KTG_KKH = "-";
                holder.kkh_data.setText("-");
                holder.kkh_dev.setText("-");
                holder.kkh_hasil.setText(KTG_KKH);
                holder.kkh_back.setBackground(a6B2Activity.getResources().getDrawable(R.drawable.null_style));
            } else {
                DEV_KKH = STD - currentItem.getKKH();
                if(DEV_KKH == 0){
                    KTG_KKH = "LF";
                    holder.kkh_back.setBackground(a6B2Activity.getResources().getDrawable(R.drawable.success_style));
                } else {
                    KTG_KKH = "LS";
                    holder.kkh_back.setBackground(a6B2Activity.getResources().getDrawable(R.drawable.failed_style));
                }
                holder.kkh_data.setText(String.valueOf(currentItem.getKKH())+"%");
                holder.kkh_dev.setText(String.valueOf(DEV_KKH)+"%");
                holder.kkh_hasil.setText(KTG_KKH);
            }
        } catch (Exception e){
            Toast.makeText(a6B2Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            if(currentItem.getDLD() == -1){
                DEV_DLD = -1;
                KTG_DLD = "-";
                holder.dld_data.setText("-");
                holder.dld_dev.setText("-");
            } else {
                if(currentItem.getDLD() >= 1.05){
                    DEV_DLD = 0;
                    KTG_DLD = "LF";
                } else {
                    DEV_DLD = (currentItem.getDLD() - 1.05)/1.05;
                    DEV_DLD = DEV_DLD * 100;
                    if(DEV_DLD < 0){
                        DEV_DLD = DEV_DLD * -1;
                    }
                    if(DEV_DLD > 100){
                        DEV_DLD = 100;
                    }
                    DEV_DLD = Double.parseDouble(df.format(DEV_DLD).replace(",", "."));
                    KTG_DLD = "LS";
                }
                holder.dld_data.setText(String.valueOf(currentItem.getDLD())+" m");
                holder.dld_dev.setText(String.valueOf(DEV_DLD)+"%");
            }
        } catch (Exception e){
            Toast.makeText(a6B2Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            if(currentItem.getDLD2() == -1){
                DEV_DLD2 = -1;
                KTG_DLD2 = "-";
                holder.dld2_data.setText("-");
                holder.dld2_dev.setText("-");
            } else {
                if(currentItem.getDLD2() >= 0.3){
                    DEV_DLD2 = 0;
                    KTG_DLD2 = "LF";
                } else {
                    DEV_DLD2 = (currentItem.getDLD2() - 0.3)/0.3;
                    DEV_DLD2 = DEV_DLD2 * 100;
                    if(DEV_DLD2 < 0){
                        DEV_DLD2 = DEV_DLD2 * -1;
                    }
                    if(DEV_DLD2 > 100){
                        DEV_DLD2 = 100;
                    }
                    DEV_DLD2 = Double.parseDouble(df.format(DEV_DLD2).replace(",", "."));
                    KTG_DLD2 = "LS";
                }
                holder.dld2_data.setText(String.valueOf(currentItem.getDLD2())+" m");
                holder.dld2_dev.setText(String.valueOf(DEV_DLD2)+"%");
            }
        } catch (Exception e){
            Toast.makeText(a6B2Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            if(currentItem.getDLD3() == -1){
                DEV_DLD3 = -1;
                KTG_DLD3 = "-";
                holder.dld3_data.setText("-");
                holder.dld3_dev.setText("-");
            } else {
                if(currentItem.getDLD3() >= 0.229){
                    DEV_DLD3 = 0;
                    KTG_DLD3 = "LF";
                } else {
                    DEV_DLD3 = (currentItem.getDLD3() - 0.229)/0.229;
                    DEV_DLD3 = DEV_DLD3 * 100;
                    if(DEV_DLD3 < 0){
                        DEV_DLD3 = DEV_DLD3 * -1;
                    }
                    if(DEV_DLD3 > 100){
                        DEV_DLD3 = 100;
                    }
                    DEV_DLD3 = Double.parseDouble(df.format(DEV_DLD3).replace(",", "."));
                    KTG_DLD3 = "LS";
                }
                holder.dld3_data.setText(String.valueOf(currentItem.getDLD3())+"%");
                holder.dld3_dev.setText(String.valueOf(DEV_DLD3)+"%");
            }
        } catch (Exception e){
            Toast.makeText(a6B2Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            if(currentItem.getDLL() == -1){
                DEV_DLL = -1;
                KTG_DLL = "-";
                holder.dll_data.setText("-");
                holder.dll_dev.setText("-");
            } else {
                DEV_DLL = STD - currentItem.getDLL();
                if(DEV_DLL == 0){
                    KTG_DLL = "LF";
                } else {
                    KTG_DLL = "LS";
                }
                holder.dll_data.setText(String.valueOf(currentItem.getDLL())+"%");
                holder.dll_dev.setText(String.valueOf(DEV_DLL)+"%");
            }
        } catch (Exception e){
            Toast.makeText(a6B2Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            if(currentItem.getDLL2() == -1){
                DEV_DLL2 = -1;
                KTG_DLL2 = "-";
                holder.dll2_data.setText("-");
                holder.dll2_dev.setText("-");
            } else {
                if(currentItem.getDLL2() >= 0.6){
                    DEV_DLL2 = 0;
                    KTG_DLL2 = "LF";
                } else {
                    DEV_DLL2 = (currentItem.getDLL2() - 0.6)/0.6;
                    DEV_DLL2 = DEV_DLL2 * 100;
                    if(DEV_DLL2 < 0){
                        DEV_DLL2 = DEV_DLL2 * -1;
                    }
                    if(DEV_DLL2 > 100){
                        DEV_DLL2 = 100;
                    }
                    DEV_DLL2 = Double.parseDouble(df.format(DEV_DLL2).replace(",", "."));
                    KTG_DLL2 = "LS";
                }
                holder.dll2_data.setText(String.valueOf(currentItem.getDLL2())+" m");
                holder.dll2_dev.setText(String.valueOf(DEV_DLL2)+"%");
            }
        } catch (Exception e){
            Toast.makeText(a6B2Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            if(currentItem.getDLW() == -1){
                DEV_DLW = -1;
                KTG_DLW = "-";
                holder.dlw_data.setText("-");
                holder.dlw_dev.setText("-");
            } else {
                DEV_DLW = STD - currentItem.getDLW();
                if(DEV_DLW == 0){
                    KTG_DLW = "LF";
                } else {
                    KTG_DLW = "LS";
                }
                holder.dlw_data.setText(String.valueOf(currentItem.getDLW())+"%");
                holder.dlw_dev.setText(String.valueOf(DEV_DLW)+"%");
            }
        } catch (Exception e){
            Toast.makeText(a6B2Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            if(currentItem.getDLT() == -1){
                DEV_DLT = -1;
                KTG_DLT = "-";
                holder.dlt_data.setText("-");
                holder.dlt_dev.setText("-");
            } else {
                DEV_DLT = STD - currentItem.getDLT();
                if(DEV_DLT == 0){
                    KTG_DLT = "LF";
                } else {
                    KTG_DLT = "LS";
                }
                holder.dlt_data.setText(String.valueOf(currentItem.getDLT())+"%");
                holder.dlt_dev.setText(String.valueOf(DEV_DLT)+"%");
            }
        } catch (Exception e){
            Toast.makeText(a6B2Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            if((KTG_DLD.equals("LF") || KTG_DLD.equals("-")) && (KTG_DLD2.equals("LF") || KTG_DLD2.equals("-")) &&
                    (KTG_DLD3.equals("LF") || KTG_DLD3.equals("-")) && (KTG_DLL.equals("LF") || KTG_DLL.equals("-")) &&
                    (KTG_DLL2.equals("LF") || KTG_DLL2.equals("-")) && (KTG_DLW.equals("LF") || KTG_DLW.equals("-")) &&
                    (KTG_DLT.equals("LF") || KTG_DLT.equals("-"))){

                if(KTG_DLD.equals("-") && KTG_DLD2.equals("-") && KTG_DLD3.equals("-") && KTG_DLL.equals("-") && KTG_DLL2.equals("-") &&
                        KTG_DLW.equals("-") && KTG_DLT.equals("-")){

                    KTG_DBT = "-";
                    holder.dbt_back.setBackground(a6B2Activity.getResources().getDrawable(R.drawable.null_style));
                } else {
                    KTG_DBT = "LF";
                    holder.dbt_back.setBackground(a6B2Activity.getResources().getDrawable(R.drawable.success_style));
                }

            } else {
                KTG_DBT = "LS";
                holder.dbt_back.setBackground(a6B2Activity.getResources().getDrawable(R.drawable.failed_style));
            }
            holder.dbt_hasil.setText(KTG_DBT);
        } catch (Exception e){
            Toast.makeText(a6B2Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            if(currentItem.getKFB() == -1){
                DEV_KFB = -1;
                KTG_KFB = "-";
                holder.kfb_data.setText("-");
                holder.kfb_dev.setText("-");
                holder.kfb_hasil.setText(KTG_KFB);
                holder.kfb_back.setBackground(a6B2Activity.getDrawable(R.drawable.null_style));
            } else {
                DEV_KFB = STD - currentItem.getKFB();
                if(DEV_KFB == 0){
                    KTG_KFB = "LF";
                    holder.kfb_back.setBackground(a6B2Activity.getDrawable(R.drawable.success_style));
                } else {
                    KTG_KFB = "LS";
                    holder.kfb_back.setBackground(a6B2Activity.getDrawable(R.drawable.failed_style));
                }
                holder.kfb_data.setText(String.valueOf(currentItem.getKFB())+"%");
                holder.kfb_dev.setText(String.valueOf(DEV_KFB)+"%");
                holder.kfb_hasil.setText(KTG_KFB);
            }
        } catch (Exception e){
            Toast.makeText(a6B2Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            if((KTG_KKH.equals("LF") || KTG_KKH.equals("-")) && (KTG_DBT.equals("LF") || KTG_DBT.equals("-")) &&
                    (KTG_KFB.equals("LF") || KTG_KFB.equals("-"))){

                if(KTG_KKH.equals("-") && KTG_DBT.equals("-") && KTG_KFB.equals("-")){
                    KTG_KLF = "Tidak Dinilai";
                } else {
                    KTG_KLF = "LF";
                }

            } else {
                KTG_KLF = "LS";
            }
        } catch (Exception e){
            Toast.makeText(a6B2Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            holder.rec_data.setText(currentItem.getREC());
        } catch (Exception e){
            Toast.makeText(a6B2Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            holder.FT1.setImageBitmap(BitmapFactory.decodeFile(currentItem.getDIR1()));
        } catch (Exception e){
            Toast.makeText(a6B2Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            holder.FT2.setImageBitmap(BitmapFactory.decodeFile(currentItem.getDIR2()));
        } catch (Exception e){
            Toast.makeText(a6B2Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            if(currentItem.getUPLOAD().equals("F")){
                a6B2Activity.FAB_UPLOAD.hide();
                a6B2Activity.UPLOAD.setImageResource(R.drawable.ic_file_upload_white_18dp);
                a6B2Activity.FAB_UPLOAD.setEnabled(true);
            } else {
                a6B2Activity.UPLOAD.setImageResource(R.drawable.ic_done_white_18dp);
                a6B2Activity.FAB_UPLOAD.setEnabled(false);
            }
        } catch (Exception e){
            Toast.makeText(a6B2Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            a6B2Activity.ID = currentItem.getID();
            a6B2Activity.KKH_TXT = currentItem.getKKH();
            a6B2Activity.DLD_TXT = currentItem.getDLD();
            a6B2Activity.DLD2_TXT = currentItem.getDLD2();
            a6B2Activity.DLD3_TXT = currentItem.getDLD3();
            a6B2Activity.DLL_TXT = currentItem.getDLL();
            a6B2Activity.DLL2_TXT = currentItem.getDLL2();
            a6B2Activity.DLW_TXT = currentItem.getDLW();
            a6B2Activity.DLT_TXT = currentItem.getDLT();
            a6B2Activity.KFB_TXT = currentItem.getKFB();
            a6B2Activity.REC_TXT = currentItem.getREC();
            a6B2Activity.DIR1 = currentItem.getDIR1();
            a6B2Activity.DIR2 = currentItem.getDIR2();

            a6B2Activity.DEV_KKH = DEV_KKH;
            a6B2Activity.DEV_DLD = DEV_DLD;
            a6B2Activity.DEV_DLD2 = DEV_DLD2;
            a6B2Activity.DEV_DLD3 = DEV_DLD3;
            a6B2Activity.DEV_DLL = DEV_DLL;
            a6B2Activity.DEV_DLL2 = DEV_DLL2;
            a6B2Activity.DEV_DLW = DEV_DLW;
            a6B2Activity.DEV_DLT = DEV_DLT;
            a6B2Activity.DEV_KFB = DEV_KFB;

            a6B2Activity.KTG_KKH = KTG_KKH;
            a6B2Activity.KTG_DBT = KTG_DBT;
            a6B2Activity.KTG_KFB = KTG_KFB;
            a6B2Activity.KTG_KLF = KTG_KLF;

            if(KTG_KLF.equals("LS")){
                a6B2Activity.klf.setText(KTG_KLF);
                a6B2Activity.klf.setBackground(a6B2Activity.getResources().getDrawable(R.drawable.failed_style));
                Animation animation = AnimationUtils.loadAnimation(a6B2Activity.getApplicationContext(), R.anim.recycle_bottom);
                a6B2Activity.klf.startAnimation(animation);
            } else if(KTG_KLF.equals("Tidak Dinilai")){
                a6B2Activity.klf.setText("Tidak Dinilai");
                a6B2Activity.klf.setBackground(a6B2Activity.getResources().getDrawable(R.drawable.null_style));
                Animation animation = AnimationUtils.loadAnimation(a6B2Activity.getApplicationContext(), R.anim.recycle_bottom);
                a6B2Activity.klf.startAnimation(animation);
            } else {
                a6B2Activity.klf.setText(KTG_KLF);
                a6B2Activity.klf.setBackground(a6B2Activity.getResources().getDrawable(R.drawable.success_style));
                Animation animation = AnimationUtils.loadAnimation(a6B2Activity.getApplicationContext(), R.anim.recycle_bottom);
                a6B2Activity.klf.startAnimation(animation);
            }
        } catch (Exception e){
            Toast.makeText(a6B2Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public int getItemCount() {
        return mdataList.size();
    }

}
