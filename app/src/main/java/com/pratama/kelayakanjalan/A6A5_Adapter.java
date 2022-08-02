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

public class A6A5_Adapter extends RecyclerView.Adapter<A6A5_Adapter.A6A5ViewHolder> {

    private A6A5Activity a6A5Activity;
    private List<A6A5_Class> mdataList;
    private double DEV_LBT, DEV_LBT2, DEV_LBT3, DEV_BTK, DEV_BTK2, DEV_PKT, DEV_PKT2, DEV_PKT3, DEV_PKT4, DEV_FPC, STD = 100;
    private String KTG_LBT, KTG_LBT2, KTG_LBT3, KTG_LBTT, KTG_BTK, KTG_BTK2, KTG_BTKK, KTG_PKT, KTG_PKT2, KTG_PKT3, KTG_PKT4, KTG_PKTT, KTG_FPC, KTG_KLF;
    private static DecimalFormat df = new DecimalFormat("0.0");

    public class A6A5ViewHolder extends RecyclerView.ViewHolder {

        private TextView lbt_data, lbt2_data, lbt3_data, btk_data, btk2_data, pkt_data, pkt2_data, pkt3_data, pkt4_data, fpc_data,
                lbt_dev, lbt2_dev, lbt3_dev, btk_dev, btk2_dev, pkt_dev, pkt2_dev, pkt3_dev, pkt4_dev, fpc_dev, lbt_hasil, btk_hasil,
                pkt_hasil, fpc_hasil, rec_data;

        private LinearLayout lbt_back, btk_back, pkt_back, fpc_back;
        private ImageView FT1, FT2;

        public A6A5ViewHolder(@NonNull View itemView) {
            super(itemView);

            lbt_data = (TextView) itemView.findViewById(R.id.lbt_data);
            lbt_dev = (TextView) itemView.findViewById(R.id.lbt_dev);
            lbt2_data = (TextView) itemView.findViewById(R.id.lbt2_data);
            lbt2_dev = (TextView) itemView.findViewById(R.id.lbt2_dev);
            lbt3_data = (TextView) itemView.findViewById(R.id.lbt3_data);
            lbt3_dev = (TextView) itemView.findViewById(R.id.lbt3_dev);
            lbt_hasil = (TextView) itemView.findViewById(R.id.lbt_hasil);

            btk_data = (TextView) itemView.findViewById(R.id.btk_data);
            btk_dev = (TextView) itemView.findViewById(R.id.btk_dev);
            btk2_data = (TextView) itemView.findViewById(R.id.btk2_data);
            btk2_dev = (TextView) itemView.findViewById(R.id.btk2_dev);
            btk_hasil = (TextView) itemView.findViewById(R.id.btk_hasil);

            pkt_data = (TextView) itemView.findViewById(R.id.pkt_data);
            pkt_dev = (TextView) itemView.findViewById(R.id.pkt_dev);
            pkt2_data = (TextView) itemView.findViewById(R.id.pkt2_data);
            pkt2_dev = (TextView) itemView.findViewById(R.id.pkt2_dev);
            pkt3_data = (TextView) itemView.findViewById(R.id.pkt3_data);
            pkt3_dev = (TextView) itemView.findViewById(R.id.pkt3_dev);
            pkt4_data = (TextView) itemView.findViewById(R.id.pkt4_data);
            pkt4_dev = (TextView) itemView.findViewById(R.id.pkt4_dev);
            pkt_hasil = (TextView) itemView.findViewById(R.id.pkt_hasil);

            fpc_data = (TextView) itemView.findViewById(R.id.fpc_data);
            fpc_dev = (TextView) itemView.findViewById(R.id.fpc_dev);
            fpc_hasil = (TextView) itemView.findViewById(R.id.fpc_hasil);

            rec_data = (TextView) itemView.findViewById(R.id.rec_data);

            FT1 = (ImageView) itemView.findViewById(R.id.FT1);
            FT2 = (ImageView) itemView.findViewById(R.id.FT2);

            lbt_back = (LinearLayout) itemView.findViewById(R.id.lbt_back);
            btk_back = (LinearLayout) itemView.findViewById(R.id.btk_back);
            pkt_back = (LinearLayout) itemView.findViewById(R.id.pkt_back);
            fpc_back = (LinearLayout) itemView.findViewById(R.id.fpc_back);

        }
    }

    public A6A5_Adapter(A6A5Activity a6A5Activity, List<A6A5_Class> mdataList) {
        this.a6A5Activity = a6A5Activity;
        this.mdataList = mdataList;
    }

    @NonNull
    @Override
    public A6A5_Adapter.A6A5ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.a6a5_layout, parent, false);
        A6A5_Adapter.A6A5ViewHolder a6a5 = new A6A5_Adapter.A6A5ViewHolder(view);
        return a6a5;
    }

    @Override
    public void onBindViewHolder(@NonNull A6A5_Adapter.A6A5ViewHolder holder, int position) {

        final A6A5_Class currentItem = mdataList.get(position);

        try {
            if(currentItem.getLBT() == -1){
                DEV_LBT = -1;
                KTG_LBT = "-";
                holder.lbt_data.setText("-");
                holder.lbt_dev.setText("-");
            } else {
                if(currentItem.getLBT() >= 1){
                    DEV_LBT = 0;
                    KTG_LBT = "LF";
                } else {
                    DEV_LBT = (currentItem.getLBT() - 1)/1;
                    DEV_LBT = DEV_LBT * 100;
                    if(DEV_LBT < 0){
                        DEV_LBT = DEV_LBT * -1;
                    }
                    if(DEV_LBT > 100){
                        DEV_LBT = 100;
                    }
                    DEV_LBT = Double.parseDouble(df.format(DEV_LBT).replace(",", "."));
                    KTG_LBT = "LS";
                }
                holder.lbt_data.setText(String.valueOf(currentItem.getLBT())+" m");
                holder.lbt_dev.setText(String.valueOf(DEV_LBT)+"%");
            }
        } catch (Exception e){
            Toast.makeText(a6A5Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            if(currentItem.getLBT2() == -1){
                DEV_LBT2 = -1;
                KTG_LBT2 = "-";
                holder.lbt2_data.setText("-");
                holder.lbt2_dev.setText("-");
            } else {
                if(currentItem.getLBT2() >= 1.5){
                    DEV_LBT2 = 0;
                    KTG_LBT2 = "LF";
                } else {
                    DEV_LBT2 = (currentItem.getLBT2() - 1.5)/1.5;
                    DEV_LBT2 = DEV_LBT2 * 100;
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
            Toast.makeText(a6A5Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            if(currentItem.getLBT3() == -1){
                DEV_LBT3 = -1;
                KTG_LBT3 = "-";
                holder.lbt3_data.setText("-");
                holder.lbt3_dev.setText("-");
            } else {
                if(currentItem.getLBT3() >= 2){
                    DEV_LBT3 = 0;
                    KTG_LBT3 = "LF";
                } else {
                    DEV_LBT3 = (currentItem.getLBT3() - 2)/2;
                    DEV_LBT3 = DEV_LBT3 * 100;
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
            Toast.makeText(a6A5Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            if((KTG_LBT.equals("LF") || KTG_LBT.equals("-")) && (KTG_LBT2.equals("LF") || KTG_LBT2.equals("-")) && (KTG_LBT3.equals("LF") || KTG_LBT3.equals("-"))){

                if(KTG_LBT.equals("-") && KTG_LBT2.equals("-") && KTG_LBT3.equals("-")){
                    KTG_LBTT = "-";
                    holder.lbt_back.setBackground(a6A5Activity.getResources().getDrawable(R.drawable.null_style));
                } else {
                    KTG_LBTT = "LF";
                    holder.lbt_back.setBackground(a6A5Activity.getResources().getDrawable(R.drawable.success_style));
                }

            } else {
                KTG_LBTT = "LS";
                holder.lbt_back.setBackground(a6A5Activity.getResources().getDrawable(R.drawable.failed_style));
            }
            holder.lbt_hasil.setText(KTG_LBTT);
        } catch (Exception e){
            Toast.makeText(a6A5Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            if(currentItem.getBTK() == -1){
                DEV_BTK = -1;
                KTG_BTK = "-";
                holder.btk_data.setText("-");
                holder.btk_dev.setText("-");
            } else {
                DEV_BTK = STD - currentItem.getBTK();
                if(DEV_BTK == 0){
                    KTG_BTK = "LF";
                } else {
                    KTG_BTK = "LS";
                }
                holder.btk_data.setText(String.valueOf(currentItem.getBTK())+"%");
                holder.btk_dev.setText(String.valueOf(DEV_BTK)+"%");
            }
        } catch (Exception e){
            Toast.makeText(a6A5Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            if(currentItem.getBTK2() == -1){
                DEV_BTK2 = -1;
                KTG_BTK2 = "-";
                holder.btk2_data.setText("-");
                holder.btk2_dev.setText("-");
            } else {
                if(currentItem.getBTK2() >= 0.25){
                    DEV_BTK2 = 0;
                    KTG_BTK2 = "LF";
                } else {
                    DEV_BTK2 = (currentItem.getBTK2() - 0.25)/0.25;
                    DEV_BTK2 = DEV_BTK2 * 100;
                    if(DEV_BTK2 < 0){
                        DEV_BTK2 = DEV_BTK2 * -1;
                    }
                    if(DEV_BTK2 > 100){
                        DEV_BTK2 = 100;
                    }
                    DEV_BTK2 = Double.parseDouble(df.format(DEV_BTK2).replace(",", "."));
                    KTG_BTK2 = "LS";
                }
                holder.btk2_data.setText(String.valueOf(currentItem.getBTK2())+" m");
                holder.btk2_dev.setText(String.valueOf(DEV_BTK2)+"%");
            }
        } catch (Exception e){
            Toast.makeText(a6A5Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            if((KTG_BTK.equals("LF") || KTG_BTK.equals("-")) && (KTG_BTK2.equals("LF") || KTG_BTK2.equals("-"))){

                if(KTG_BTK.equals("-") && KTG_BTK2.equals("-")){
                    KTG_BTKK = "-";
                    holder.btk_back.setBackground(a6A5Activity.getResources().getDrawable(R.drawable.null_style));
                } else {
                    KTG_BTKK = "LF";
                    holder.btk_back.setBackground(a6A5Activity.getResources().getDrawable(R.drawable.success_style));
                }

            } else {
                KTG_BTKK = "LS";
                holder.btk_back.setBackground(a6A5Activity.getResources().getDrawable(R.drawable.failed_style));
            }
            holder.btk_hasil.setText(KTG_BTKK);
        } catch (Exception e){
            Toast.makeText(a6A5Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            if(currentItem.getPKT() == -1){
                DEV_PKT = -1;
                KTG_PKT = "-";
                holder.pkt_data.setText("-");
                holder.pkt_dev.setText("-");
            } else {
                DEV_PKT = STD - currentItem.getPKT();
                if(DEV_PKT == 0){
                    KTG_PKT = "LF";
                } else {
                    KTG_PKT = "LS";
                }
                holder.pkt_data.setText(String.valueOf(currentItem.getPKT())+"%");
                holder.pkt_dev.setText(String.valueOf(DEV_PKT)+"%");
            }
        } catch (Exception e){
            Toast.makeText(a6A5Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            if(currentItem.getPKT2() == -1){
                DEV_PKT2 = -1;
                KTG_PKT2 = "-";
                holder.pkt2_data.setText("-");
                holder.pkt2_dev.setText("-");
            } else {
                DEV_PKT2 = STD - currentItem.getPKT2();
                if(DEV_PKT2 == 0){
                    KTG_PKT2 = "LF";
                } else {
                    KTG_PKT2 = "LS";
                }
                holder.pkt2_data.setText(String.valueOf(currentItem.getPKT2())+"%");
                holder.pkt2_dev.setText(String.valueOf(DEV_PKT2)+"%");
            }
        } catch (Exception e){
            Toast.makeText(a6A5Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            if(currentItem.getPKT3() == -1){
                DEV_PKT3 = -1;
                KTG_PKT3 = "-";
                holder.pkt3_data.setText("-");
                holder.pkt3_dev.setText("-");
            } else {
                DEV_PKT3 = STD - currentItem.getPKT3();
                if(DEV_PKT3 == 0){
                    KTG_PKT3 = "LF";
                } else {
                    KTG_PKT3 = "LS";
                }
                holder.pkt3_data.setText(String.valueOf(currentItem.getPKT3())+"%");
                holder.pkt3_dev.setText(String.valueOf(DEV_PKT3)+"%");
            }
        } catch (Exception e){
            Toast.makeText(a6A5Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            if(currentItem.getPKT4() == -1){
                DEV_PKT4 = -1;
                KTG_PKT4 = "-";
                holder.pkt4_data.setText("-");
                holder.pkt4_dev.setText("-");
            } else {
                DEV_PKT4 = STD - currentItem.getPKT4();
                if(DEV_PKT4 == 0){
                    KTG_PKT4 = "LF";
                } else {
                    KTG_PKT4 = "LS";
                }
                holder.pkt4_data.setText(String.valueOf(currentItem.getPKT4())+"%");
                holder.pkt4_dev.setText(String.valueOf(DEV_PKT4)+"%");
            }
        } catch (Exception e){
            Toast.makeText(a6A5Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            if((KTG_PKT.equals("LF") || KTG_PKT.equals("-")) && (KTG_PKT2.equals("LF") || KTG_PKT2.equals("-")) &&
                    (KTG_PKT3.equals("LF") || KTG_PKT3.equals("-")) && (KTG_PKT4.equals("LF") || KTG_PKT4.equals("-"))){

                if(KTG_PKT.equals("-") && KTG_PKT2.equals("-") && KTG_PKT3.equals("-") && KTG_PKT4.equals("-")){
                    KTG_PKTT = "-";
                    holder.pkt_back.setBackground(a6A5Activity.getResources().getDrawable(R.drawable.null_style));
                } else {
                    KTG_PKTT = "LF";
                    holder.pkt_back.setBackground(a6A5Activity.getResources().getDrawable(R.drawable.success_style));
                }

            } else {
                KTG_PKTT = "LS";
                holder.pkt_back.setBackground(a6A5Activity.getResources().getDrawable(R.drawable.failed_style));
            }
            holder.pkt_hasil.setText(KTG_PKTT);
        } catch (Exception e){
            Toast.makeText(a6A5Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            if(currentItem.getFPC() == -1){
                DEV_FPC = -1;
                KTG_FPC = "-";
                holder.fpc_data.setText("-");
                holder.fpc_dev.setText("-");
                holder.fpc_hasil.setText(KTG_FPC);
                holder.fpc_back.setBackground(a6A5Activity.getResources().getDrawable(R.drawable.null_style));
            } else {
                DEV_FPC = STD - currentItem.getFPC();
                if(DEV_FPC == 0){
                    KTG_FPC = "LF";
                    holder.fpc_back.setBackground(a6A5Activity.getResources().getDrawable(R.drawable.success_style));
                } else {
                    KTG_FPC = "LS";
                    holder.fpc_back.setBackground(a6A5Activity.getResources().getDrawable(R.drawable.failed_style));
                }
                holder.fpc_data.setText(String.valueOf(currentItem.getFPC())+"%");
                holder.fpc_dev.setText(String.valueOf(DEV_FPC)+"%");
                holder.fpc_hasil.setText(KTG_FPC);
            }
        } catch (Exception e){
            Toast.makeText(a6A5Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            if((KTG_LBTT.equals("LF") || KTG_LBTT.equals("-")) && (KTG_BTKK.equals("LF") || KTG_BTKK.equals("-")) &&
                    (KTG_PKTT.equals("LF") || KTG_PKTT.equals("-")) && (KTG_FPC.equals("LF") || KTG_FPC.equals("-"))){

                if(KTG_LBTT.equals("-") && KTG_BTKK.equals("-") && KTG_PKTT.equals("-") && KTG_FPC.equals("-")){
                    KTG_KLF = "Tidak Dinilai";
                } else {
                    KTG_KLF = "LF";
                }

            } else {
                KTG_KLF = "LS";
            }
        } catch (Exception e){
            Toast.makeText(a6A5Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            holder.rec_data.setText(currentItem.getREC());
        } catch (Exception e){
            Toast.makeText(a6A5Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            holder.FT1.setImageBitmap(BitmapFactory.decodeFile(currentItem.getDIR1()));
        } catch (Exception e){
            Toast.makeText(a6A5Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            holder.FT2.setImageBitmap(BitmapFactory.decodeFile(currentItem.getDIR2()));
        } catch (Exception e){
            Toast.makeText(a6A5Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            if(currentItem.getUPLOAD().equals("F")){
                a6A5Activity.FAB_UPLOAD.hide();
                a6A5Activity.UPLOAD.setImageResource(R.drawable.ic_file_upload_white_18dp);
                a6A5Activity.FAB_UPLOAD.setEnabled(true);
            } else {
                a6A5Activity.UPLOAD.setImageResource(R.drawable.ic_done_white_18dp);
                a6A5Activity.FAB_UPLOAD.setEnabled(false);
            }
        } catch (Exception e){
            Toast.makeText(a6A5Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            a6A5Activity.ID = currentItem.getID();
            a6A5Activity.LBT_TXT = currentItem.getLBT();
            a6A5Activity.LBT2_TXT = currentItem.getLBT2();
            a6A5Activity.LBT3_TXT = currentItem.getLBT3();
            a6A5Activity.BTK_TXT = currentItem.getBTK();
            a6A5Activity.BTK2_TXT = currentItem.getBTK2();
            a6A5Activity.PKT_TXT = currentItem.getPKT();
            a6A5Activity.PKT2_TXT = currentItem.getPKT2();
            a6A5Activity.PKT3_TXT = currentItem.getPKT3();
            a6A5Activity.PKT4_TXT = currentItem.getPKT4();
            a6A5Activity.FPC_TXT = currentItem.getFPC();
            a6A5Activity.REC_TXT = currentItem.getREC();
            a6A5Activity.DIR1 = currentItem.getDIR1();
            a6A5Activity.DIR2 = currentItem.getDIR2();

            a6A5Activity.DEV_LBT = DEV_LBT;
            a6A5Activity.DEV_LBT2 = DEV_LBT2;
            a6A5Activity.DEV_LBT3 = DEV_LBT3;
            a6A5Activity.DEV_BTK = DEV_BTK;
            a6A5Activity.DEV_BTK2 = DEV_BTK2;
            a6A5Activity.DEV_PKT = DEV_PKT;
            a6A5Activity.DEV_PKT2 = DEV_PKT2;
            a6A5Activity.DEV_PKT3 = DEV_PKT3;
            a6A5Activity.DEV_PKT4 = DEV_PKT4;
            a6A5Activity.DEV_FPC = DEV_FPC;

            a6A5Activity.KTG_LBTT = KTG_LBTT;
            a6A5Activity.KTG_BTKK = KTG_BTKK;
            a6A5Activity.KTG_PKTT = KTG_PKTT;
            a6A5Activity.KTG_FPC = KTG_FPC;
            a6A5Activity.KTG_KLF = KTG_KLF;

            if(KTG_KLF.equals("LS")){
                a6A5Activity.klf.setText(KTG_KLF);
                a6A5Activity.klf.setBackground(a6A5Activity.getResources().getDrawable(R.drawable.failed_style));
                Animation animation = AnimationUtils.loadAnimation(a6A5Activity.getApplicationContext(), R.anim.recycle_bottom);
                a6A5Activity.klf.startAnimation(animation);
            } else if(KTG_KLF.equals("Tidak Dinilai")){
                a6A5Activity.klf.setText("Tidak Dinilai");
                a6A5Activity.klf.setBackground(a6A5Activity.getResources().getDrawable(R.drawable.null_style));
                Animation animation = AnimationUtils.loadAnimation(a6A5Activity.getApplicationContext(), R.anim.recycle_bottom);
                a6A5Activity.klf.startAnimation(animation);
            } else {
                a6A5Activity.klf.setText(KTG_KLF);
                a6A5Activity.klf.setBackground(a6A5Activity.getResources().getDrawable(R.drawable.success_style));
                Animation animation = AnimationUtils.loadAnimation(a6A5Activity.getApplicationContext(), R.anim.recycle_bottom);
                a6A5Activity.klf.startAnimation(animation);
            }
        } catch (Exception e){
            Toast.makeText(a6A5Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public int getItemCount() {
        return mdataList.size();
    }

}