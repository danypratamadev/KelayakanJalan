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

public class A6A3_Adapter extends RecyclerView.Adapter<A6A3_Adapter.A6A3ViewHolder> {

    private A6A3Activity a6A3Activity;
    private List<A6A3_Class> mdataList;
    private double DEV_BUS, DEV_BUT, DEV_BUL, DEV_LUB, DEV_LUL, DEV_LUJ, STD = 100;
    private String KTG_BUS, KTG_BUT, KTG_BUL, KTG_BUSS, KTG_LUB, KTG_LUL, KTG_LUJ, KTG_LUBB, KTG_KLF;
    private static DecimalFormat df = new DecimalFormat("0.0");

    public class A6A3ViewHolder extends RecyclerView.ViewHolder {

        private TextView bus_data, but_data, bul_data, lub_data, lul_data, luj_data, bus_dev,
                but_dev, bul_dev, lub_dev, lul_dev, luj_dev, bus_hasil, lub_hasil, rec_data;

        private LinearLayout bus_back, lub_back;
        private ImageView FT1, FT2;

        public A6A3ViewHolder(@NonNull View itemView) {
            super(itemView);

            bus_data = (TextView) itemView.findViewById(R.id.bus_data);
            bus_dev = (TextView) itemView.findViewById(R.id.bus_dev);
            but_data = (TextView) itemView.findViewById(R.id.but_data);
            but_dev = (TextView) itemView.findViewById(R.id.but_dev);
            bul_data = (TextView) itemView.findViewById(R.id.bul_data);
            bul_dev = (TextView) itemView.findViewById(R.id.bul_dev);
            bus_hasil = (TextView) itemView.findViewById(R.id.bus_hasil);

            lub_data = (TextView) itemView.findViewById(R.id.lub_data);
            lub_dev = (TextView) itemView.findViewById(R.id.lub_dev);
            lul_data = (TextView) itemView.findViewById(R.id.lul_data);
            lul_dev = (TextView) itemView.findViewById(R.id.lul_dev);
            luj_data = (TextView) itemView.findViewById(R.id.luj_data);
            luj_dev = (TextView) itemView.findViewById(R.id.luj_dev);
            lub_hasil = (TextView) itemView.findViewById(R.id.lub_hasil);

            rec_data = (TextView) itemView.findViewById(R.id.rec_data);

            FT1 = (ImageView) itemView.findViewById(R.id.FT1);
            FT2 = (ImageView) itemView.findViewById(R.id.FT2);

            bus_back = (LinearLayout) itemView.findViewById(R.id.bus_back);
            lub_back = (LinearLayout) itemView.findViewById(R.id.lub_back);

        }
    }

    public A6A3_Adapter(A6A3Activity a6A3Activity, List<A6A3_Class> mdataList) {
        this.a6A3Activity = a6A3Activity;
        this.mdataList = mdataList;
    }

    @NonNull
    @Override
    public A6A3_Adapter.A6A3ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.a6a3_layout, parent, false);
        A6A3_Adapter.A6A3ViewHolder a6a3 = new A6A3_Adapter.A6A3ViewHolder(view);
        return a6a3;
    }

    @Override
    public void onBindViewHolder(@NonNull A6A3_Adapter.A6A3ViewHolder holder, int position) {

        final A6A3_Class currentItem = mdataList.get(position);

        try {
            if(currentItem.getBUS() == -1){
                DEV_BUS = -1;
                KTG_BUS = "-";
                holder.bus_data.setText("-");
                holder.bus_dev.setText("-");
            } else {
                DEV_BUS = STD - currentItem.getBUS();
                if(DEV_BUS == 0){
                    KTG_BUS = "LF";
                } else {
                    KTG_BUS = "LS";
                }
                holder.bus_data.setText(String.valueOf(currentItem.getBUS())+"%");
                holder.bus_dev.setText(String.valueOf(DEV_BUS)+"%");
            }
        } catch (Exception e){
            Toast.makeText(a6A3Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            if(currentItem.getBUT() == -1){
                DEV_BUT = -1;
                KTG_BUT = "-";
                holder.but_data.setText("-");
                holder.but_dev.setText("-");
            } else {
                if(a6A3Activity.FNG.equals("Arteri")){
                    if(a6A3Activity.SJR.equals("Primer (Antar Kota)")){
                        if(currentItem.getBUT() >= 0.18){
                            DEV_BUT = 0;
                            KTG_BUT = "LF";
                        } else {
                            DEV_BUT = (currentItem.getBUT() - 0.18)/0.18;
                            DEV_BUT = DEV_BUT * 100;
                            if(DEV_BUT < 0){
                                DEV_BUT = DEV_BUT * -1;
                            }
                            if(DEV_BUT > 100){
                                DEV_BUT = 100;
                            }
                            DEV_BUT = Double.parseDouble(df.format(DEV_BUT).replace(",", "."));
                            KTG_BUT = "LS";
                        }
                    } else {
                        if(currentItem.getBUT() >= 0.18){
                            DEV_BUT = 0;
                            KTG_BUT = "LF";
                        } else {
                            DEV_BUT = (currentItem.getBUT() - 0.18)/0.18;
                            DEV_BUT = DEV_BUT * 100;
                            if(DEV_BUT < 0){
                                DEV_BUT = DEV_BUT * -1;
                            }
                            if(DEV_BUT > 100){
                                DEV_BUT = 100;
                            }
                            DEV_BUT = Double.parseDouble(df.format(DEV_BUT).replace(",", "."));
                            KTG_BUT = "LS";
                        }
                    }
                } else {
                    if(a6A3Activity.SJR.equals("Primer (Antar Kota)")){
                        if(currentItem.getBUT() >= 0.18){
                            DEV_BUT = 0;
                            KTG_BUT = "LF";
                        } else {
                            DEV_BUT = (currentItem.getBUT() - 0.18)/0.18;
                            DEV_BUT = DEV_BUT * 100;
                            if(DEV_BUT < 0){
                                DEV_BUT = DEV_BUT * -1;
                            }
                            if(DEV_BUT > 100){
                                DEV_BUT = 100;
                            }
                            DEV_BUT = Double.parseDouble(df.format(DEV_BUT).replace(",", "."));
                            KTG_BUT = "LS";
                        }
                    } else {
                        if(currentItem.getBUT() >= 0.18){
                            DEV_BUT = 0;
                            KTG_BUT = "LF";
                        } else {
                            DEV_BUT = (currentItem.getBUT() - 0.18)/0.18;
                            DEV_BUT = DEV_BUT * 100;
                            if(DEV_BUT < 0){
                                DEV_BUT = DEV_BUT * -1;
                            }
                            if(DEV_BUT > 100){
                                DEV_BUT = 100;
                            }
                            DEV_BUT = Double.parseDouble(df.format(DEV_BUT).replace(",", "."));
                            KTG_BUT = "LS";
                        }
                    }
                }
                holder.but_data.setText(String.valueOf(currentItem.getBUT())+" m");
                holder.but_dev.setText(String.valueOf(DEV_BUT)+"%");
            }
        } catch (Exception e){
            Toast.makeText(a6A3Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            if(currentItem.getBUL() == -1){
                DEV_BUL = -1;
                KTG_BUL = "-";
                holder.bul_data.setText("-");
                holder.bul_dev.setText("-");
            } else {
                if(a6A3Activity.FNG.equals("Arteri")){
                    if(a6A3Activity.SJR.equals("Primer (Antar Kota)")){
                        if(currentItem.getBUL() >= 2){
                            DEV_BUL = 0;
                            KTG_BUL = "LF";
                        } else {
                            DEV_BUL = (currentItem.getBUL() - 2)/2;
                            DEV_BUL = DEV_BUL * 100;
                            if(DEV_BUL < 0){
                                DEV_BUL = DEV_BUL * -1;
                            }
                            if(DEV_BUL > 100){
                                DEV_BUL = 100;
                            }
                            DEV_BUL = Double.parseDouble(df.format(DEV_BUL).replace(",", "."));
                            KTG_BUL = "LS";
                        }
                    } else {
                        if(currentItem.getBUL() >= 1){
                            DEV_BUL = 0;
                            KTG_BUL = "LF";
                        } else {
                            DEV_BUL = (currentItem.getBUL() - 1)/1;
                            DEV_BUL = DEV_BUL * 100;
                            if(DEV_BUL < 0){
                                DEV_BUL = DEV_BUL * -1;
                            }
                            if(DEV_BUL > 100){
                                DEV_BUL = 100;
                            }
                            DEV_BUL = Double.parseDouble(df.format(DEV_BUL).replace(",", "."));
                            KTG_BUL = "LS";
                        }
                    }
                } else {
                    if(a6A3Activity.SJR.equals("Primer (Antar Kota)")){
                        if(currentItem.getBUL() >= 1.25){
                            DEV_BUL = 0;
                            KTG_BUL = "LF";
                        } else {
                            DEV_BUL = (currentItem.getBUL() - 1.25)/1.25;
                            DEV_BUL = DEV_BUL * 100;
                            if(DEV_BUL < 0){
                                DEV_BUL = DEV_BUL * -1;
                            }
                            if(DEV_BUL > 100){
                                DEV_BUL = 100;
                            }
                            DEV_BUL = Double.parseDouble(df.format(DEV_BUL).replace(",", "."));
                            KTG_BUL = "LS";
                        }
                    } else {
                        if(currentItem.getBUL() >= 1){
                            DEV_BUL = 0;
                            KTG_BUL = "LF";
                        } else {
                            DEV_BUL = (currentItem.getBUL() - 1)/1;
                            DEV_BUL = DEV_BUL * 100;
                            if(DEV_BUL < 0){
                                DEV_BUL = DEV_BUL * -1;
                            }
                            if(DEV_BUL > 100){
                                DEV_BUL = 100;
                            }
                            DEV_BUL = Double.parseDouble(df.format(DEV_BUL).replace(",", "."));
                            KTG_BUL = "LS";
                        }
                    }
                }
                holder.bul_data.setText(String.valueOf(currentItem.getBUL())+" m");
                holder.bul_dev.setText(String.valueOf(DEV_BUL)+"%");
            }
        } catch (Exception e){
            Toast.makeText(a6A3Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            if((KTG_BUS.equals("LF") || KTG_BUS.equals("-")) && (KTG_BUT.equals("LF") || KTG_BUT.equals("-")) &&
                    (KTG_BUL.equals("LF") || KTG_BUL.equals("-"))){

                if(KTG_BUS.equals("-") && KTG_BUT.equals("-") && KTG_BUL.equals("-")){
                    KTG_BUSS = "-";
                    holder.bus_back.setBackground(a6A3Activity.getResources().getDrawable(R.drawable.null_style));
                } else {
                    KTG_BUSS = "LF";
                    holder.bus_back.setBackground(a6A3Activity.getResources().getDrawable(R.drawable.success_style));
                }

            } else {
                KTG_BUSS = "LS";
                holder.bus_back.setBackground(a6A3Activity.getResources().getDrawable(R.drawable.failed_style));
            }
            holder.bus_hasil.setText(KTG_BUSS);
        } catch (Exception e){
            Toast.makeText(a6A3Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            if(currentItem.getLUB() == -1){
                DEV_LUB = -1;
                KTG_LUB = "-";
                holder.lub_data.setText("-");
                holder.lub_dev.setText("-");
            } else {
                DEV_LUB = STD - currentItem.getLUB();
                if(DEV_LUB == 0){
                    KTG_LUB = "LF";
                } else {
                    KTG_LUB = "LS";
                }
                holder.lub_data.setText(String.valueOf(currentItem.getLUB())+"%");
                holder.lub_dev.setText(String.valueOf(DEV_LUB)+"%");
            }
        } catch (Exception e){
            Toast.makeText(a6A3Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            if(currentItem.getLUL() == -1){
                DEV_LUL = -1;
                KTG_LUL = "-";
                holder.lul_data.setText("-");
                holder.lul_dev.setText("-");
            } else {
                if(a6A3Activity.FNG.equals("Arteri")){
                    if(a6A3Activity.SJR.equals("Primer (Antar Kota)")){
                        if(currentItem.getLUL() >= 7){
                            DEV_LUL = 0;
                            KTG_LUL = "LF";
                        } else {
                            DEV_LUL = (currentItem.getLUL() - 7)/7;
                            DEV_LUL = DEV_LUL * 100;
                            if(DEV_LUL < 0){
                                DEV_LUL = DEV_LUL * -1;
                            }
                            if(DEV_LUL > 100){
                                DEV_LUL = 100;
                            }
                            DEV_LUL = Double.parseDouble(df.format(DEV_LUL).replace(",", "."));
                            KTG_LUL = "LS";
                        }
                    } else {
                        if(currentItem.getLUL() >= 5){
                            DEV_LUL = 0;
                            KTG_LUL = "LF";
                        } else {
                            DEV_LUL = (currentItem.getLUL() - 5)/5;
                            DEV_LUL = DEV_LUL * 100;
                            if(DEV_LUL < 0){
                                DEV_LUL = DEV_LUL * -1;
                            }
                            if(DEV_LUL > 100){
                                DEV_LUL = 100;
                            }
                            DEV_LUL = Double.parseDouble(df.format(DEV_LUL).replace(",", "."));
                            KTG_LUL = "LS";
                        }
                    }
                } else {
                    if(a6A3Activity.SJR.equals("Primer (Antar Kota)")){
                        if(currentItem.getLUL() >= 7){
                            DEV_LUL = 0;
                            KTG_LUL = "LF";
                        } else {
                            DEV_LUL = (currentItem.getLUL() - 7)/7;
                            DEV_LUL = DEV_LUL * 100;
                            if(DEV_LUL < 0){
                                DEV_LUL = DEV_LUL * -1;
                            }
                            if(DEV_LUL > 100){
                                DEV_LUL = 100;
                            }
                            DEV_LUL = Double.parseDouble(df.format(DEV_LUL).replace(",", "."));
                            KTG_LUL = "LS";
                        }
                    } else {
                        if(currentItem.getLUL() >= 5){
                            DEV_LUL = 0;
                            KTG_LUL = "LF";
                        } else {
                            DEV_LUL = (currentItem.getLUL() - 5)/5;
                            DEV_LUL = DEV_LUL * 100;
                            if(DEV_LUL < 0){
                                DEV_LUL = DEV_LUL * -1;
                            }
                            if(DEV_LUL > 100){
                                DEV_LUL = 100;
                            }
                            DEV_LUL = Double.parseDouble(df.format(DEV_LUL).replace(",", "."));
                            KTG_LUL = "LS";
                        }
                    }
                }
                holder.lul_data.setText(String.valueOf(currentItem.getLUL())+" m");
                holder.lul_dev.setText(String.valueOf(DEV_LUL)+"%");
            }
        } catch (Exception e){
            Toast.makeText(a6A3Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            if(currentItem.getLUJ() == -1){
                DEV_LUJ = -1;
                KTG_LUJ = "-";
                holder.luj_data.setText("-");
                holder.luj_dev.setText("-");
            } else {
                if(a6A3Activity.FNG.equals("Arteri")){
                    if(a6A3Activity.SJR.equals("Primer (Antar Kota)")){
                        if(currentItem.getLUJ() <= 400){
                            DEV_LUJ = 0;
                            KTG_LUJ = "LF";
                        } else {
                            DEV_LUJ = (currentItem.getLUJ() - 400)/400;
                            DEV_LUJ = DEV_LUJ * 100;
                            if(DEV_LUJ < 0){
                                DEV_LUJ = DEV_LUJ * -1;
                            }
                            if(DEV_LUJ > 100){
                                DEV_LUJ = 100;
                            }
                            DEV_LUJ = Double.parseDouble(df.format(DEV_LUJ).replace(",", "."));
                            KTG_LUJ = "LS";
                        }
                    } else {
                        if(currentItem.getLUJ() <= 350){
                            DEV_LUJ = 0;
                            KTG_LUJ = "LF";
                        } else {
                            DEV_LUJ = (currentItem.getLUJ() - 350)/350;
                            DEV_LUJ = DEV_LUJ * 100;
                            if(DEV_LUJ < 0){
                                DEV_LUJ = DEV_LUJ * -1;
                            }
                            if(DEV_LUJ > 100){
                                DEV_LUJ = 100;
                            }
                            DEV_LUJ = Double.parseDouble(df.format(DEV_LUJ).replace(",", "."));
                            KTG_LUJ = "LS";
                        }
                    }
                } else {
                    if(a6A3Activity.SJR.equals("Primer (Antar Kota)")){
                        if(currentItem.getLUJ() <= 300){
                            DEV_LUJ = 0;
                            KTG_LUJ = "LF";
                        } else {
                            DEV_LUJ = (currentItem.getLUJ() - 300)/300;
                            DEV_LUJ = DEV_LUJ * 100;
                            if(DEV_LUJ < 0){
                                DEV_LUJ = DEV_LUJ * -1;
                            }
                            if(DEV_LUJ > 100){
                                DEV_LUJ = 100;
                            }
                            DEV_LUJ = Double.parseDouble(df.format(DEV_LUJ).replace(",", "."));
                            KTG_LUJ = "LS";
                        }
                    } else {
                        if(currentItem.getLUJ() <= 250){
                            DEV_LUJ = 0;
                            KTG_LUJ = "LF";
                        } else {
                            DEV_LUJ = (currentItem.getLUJ() - 250)/250;
                            DEV_LUJ = DEV_LUJ * 100;
                            if(DEV_LUJ < 0){
                                DEV_LUJ = DEV_LUJ * -1;
                            }
                            if(DEV_LUJ > 100){
                                DEV_LUJ = 100;
                            }
                            DEV_LUJ = Double.parseDouble(df.format(DEV_LUJ).replace(",", "."));
                            KTG_LUJ = "LS";
                        }
                    }
                }
                holder.luj_data.setText(String.valueOf(currentItem.getLUJ())+" m");
                holder.luj_dev.setText(String.valueOf(DEV_LUJ)+"%");
            }
        } catch (Exception e){
            Toast.makeText(a6A3Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            if((KTG_LUB.equals("LF") || KTG_LUB.equals("-")) && (KTG_LUL.equals("LF") || KTG_LUL.equals("-")) &&
                    (KTG_LUJ.equals("LF") || KTG_LUJ.equals("-"))){

                if(KTG_LUB.equals("-") && KTG_LUL.equals("-") && KTG_LUJ.equals("-")){
                    KTG_LUBB = "-";
                    holder.lub_back.setBackground(a6A3Activity.getResources().getDrawable(R.drawable.null_style));
                } else {
                    KTG_LUBB = "LF";
                    holder.lub_back.setBackground(a6A3Activity.getResources().getDrawable(R.drawable.success_style));
                }

            } else {
                KTG_LUBB = "LS";
                holder.lub_back.setBackground(a6A3Activity.getResources().getDrawable(R.drawable.failed_style));
            }
            holder.lub_hasil.setText(KTG_LUBB);
        } catch (Exception e){
            Toast.makeText(a6A3Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            if((KTG_BUSS.equals("LF") || KTG_BUSS.equals("-")) && (KTG_LUBB.equals("LF") || KTG_LUBB.equals("-"))){

                if(KTG_BUSS.equals("-") && KTG_LUBB.equals("-")){
                    KTG_KLF = "Tidak Dinilai";
                } else {
                    KTG_KLF = "LF";
                }

            } else {
                KTG_KLF = "LS";
            }
        } catch (Exception e){
            Toast.makeText(a6A3Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            holder.rec_data.setText(currentItem.getREC());
        } catch (Exception e){
            Toast.makeText(a6A3Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            holder.FT1.setImageBitmap(BitmapFactory.decodeFile(currentItem.getDIR1()));
        } catch (Exception e){
            Toast.makeText(a6A3Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            holder.FT2.setImageBitmap(BitmapFactory.decodeFile(currentItem.getDIR2()));
        } catch (Exception e){
            Toast.makeText(a6A3Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            if(currentItem.getUPLOAD().equals("F")){
                a6A3Activity.FAB_UPLOAD.hide();
                a6A3Activity.UPLOAD.setImageResource(R.drawable.ic_file_upload_white_18dp);
                a6A3Activity.FAB_UPLOAD.setEnabled(true);
            } else {
                a6A3Activity.UPLOAD.setImageResource(R.drawable.ic_done_white_18dp);
                a6A3Activity.FAB_UPLOAD.setEnabled(false);
            }
        } catch (Exception e){
            Toast.makeText(a6A3Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            a6A3Activity.ID = currentItem.getID();
            a6A3Activity.BUS_TXT = currentItem.getBUS();
            a6A3Activity.BUT_TXT = currentItem.getBUT();
            a6A3Activity.BUL_TXT = currentItem.getBUL();
            a6A3Activity.LUB_TXT = currentItem.getLUB();
            a6A3Activity.LUL_TXT = currentItem.getLUL();
            a6A3Activity.LUJ_TXT = currentItem.getLUJ();
            a6A3Activity.REC_TXT = currentItem.getREC();
            a6A3Activity.DIR1 = currentItem.getDIR1();
            a6A3Activity.DIR2 = currentItem.getDIR2();

            a6A3Activity.DEV_BUS = DEV_BUS;
            a6A3Activity.DEV_BUT = DEV_BUT;
            a6A3Activity.DEV_BUL = DEV_BUL;
            a6A3Activity.DEV_LUB = DEV_LUB;
            a6A3Activity.DEV_LUL = DEV_LUL;
            a6A3Activity.DEV_LUJ = DEV_LUJ;

            a6A3Activity.KTG_BUSS = KTG_BUSS;
            a6A3Activity.KTG_LUBB = KTG_LUBB;
            a6A3Activity.KTG_KLF = KTG_KLF;

            if(KTG_KLF.equals("LS")){
                a6A3Activity.klf.setText(KTG_KLF);
                a6A3Activity.klf.setBackground(a6A3Activity.getResources().getDrawable(R.drawable.failed_style));
                Animation animation = AnimationUtils.loadAnimation(a6A3Activity.getApplicationContext(), R.anim.recycle_bottom);
                a6A3Activity.klf.startAnimation(animation);
            } else if(KTG_KLF.equals("Tidak Dinilai")){
                a6A3Activity.klf.setText("Tidak Dinilai");
                a6A3Activity.klf.setBackground(a6A3Activity.getResources().getDrawable(R.drawable.null_style));
                Animation animation = AnimationUtils.loadAnimation(a6A3Activity.getApplicationContext(), R.anim.recycle_bottom);
                a6A3Activity.klf.startAnimation(animation);
            } else {
                a6A3Activity.klf.setText(KTG_KLF);
                a6A3Activity.klf.setBackground(a6A3Activity.getResources().getDrawable(R.drawable.success_style));
                Animation animation = AnimationUtils.loadAnimation(a6A3Activity.getApplicationContext(), R.anim.recycle_bottom);
                a6A3Activity.klf.startAnimation(animation);
            }
        } catch (Exception e){
            Toast.makeText(a6A3Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public int getItemCount() {
        return mdataList.size();
    }

}
