package com.pratama.kelayakanjalan;

import android.database.Cursor;
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

public class A6B7_Adapter extends RecyclerView.Adapter<A6B7_Adapter.A6B7ViewHolder> {

    private A6B7Activity a6B7Activity;
    private List<A6B7_Class> mdataList;
    private double DEV_KMI, DEV_KAL, DEV_KAL2, DEV_KFI, DEV_KFI2, STD = 100;
    private String KTG_KMI, KTG_KAL, KTG_KAL2, KTG_KALL, KTG_KFI, KTG_KFI2, KTG_KFII, KTG_KLF;
    private static DecimalFormat df = new DecimalFormat("0.0");

    public class A6B7ViewHolder extends RecyclerView.ViewHolder {

        private TextView kmi_data, kmi_dev, kmi_hasil, kal_data, kal_dev, kal2_data, kal2_dev, kal_hasil, kfi_data, kfi_dev, kfi2_data, kfi2_dev, kfi_hasil, rec_data;
        private LinearLayout kmi_back, kal_back, kfi_back;
        private ImageView FT1, FT2;

        public A6B7ViewHolder(@NonNull View itemView) {
            super(itemView);

            kmi_data = (TextView) itemView.findViewById(R.id.kmi_data);
            kmi_dev = (TextView) itemView.findViewById(R.id.kmi_dev);
            kmi_hasil = (TextView) itemView.findViewById(R.id.kmi_hasil);

            kal_data = (TextView) itemView.findViewById(R.id.kal_data);
            kal_dev = (TextView) itemView.findViewById(R.id.kal_dev);
            kal2_data = (TextView) itemView.findViewById(R.id.kal2_data);
            kal2_dev = (TextView) itemView.findViewById(R.id.kal2_dev);
            kal_hasil = (TextView) itemView.findViewById(R.id.kal_hasil);

            kfi_data = (TextView) itemView.findViewById(R.id.kfi_data);
            kfi_dev = (TextView) itemView.findViewById(R.id.kfi_dev);
            kfi2_data = (TextView) itemView.findViewById(R.id.kfi2_data);
            kfi2_dev = (TextView) itemView.findViewById(R.id.kfi2_dev);
            kfi_hasil = (TextView) itemView.findViewById(R.id.kfi_hasil);

            rec_data = (TextView) itemView.findViewById(R.id.rec_data);

            FT1 = (ImageView) itemView.findViewById(R.id.FT1);
            FT2 = (ImageView) itemView.findViewById(R.id.FT2);

            kmi_back = (LinearLayout) itemView.findViewById(R.id.kmi_back);
            kal_back = (LinearLayout) itemView.findViewById(R.id.kal_back);
            kfi_back = (LinearLayout) itemView.findViewById(R.id.kfi_back);

        }

    }

    public A6B7_Adapter(A6B7Activity a6B7Activity, List<A6B7_Class> mdataList) {
        this.a6B7Activity = a6B7Activity;
        this.mdataList = mdataList;
    }

    @NonNull
    @Override
    public A6B7ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.a6b7_layout, parent, false);
        A6B7_Adapter.A6B7ViewHolder a6b7 = new A6B7_Adapter.A6B7ViewHolder(view);
        return a6b7;
    }

    @Override
    public void onBindViewHolder(@NonNull A6B7ViewHolder holder, int position) {

        final A6B7_Class currentItem = mdataList.get(position);

        try {
            if(currentItem.getKMI() == -1){
                DEV_KMI = -1;
                KTG_KMI = "-";
                holder.kmi_data.setText("-");
                holder.kmi_dev.setText("-");
                holder.kmi_hasil.setText(KTG_KMI);
                holder.kmi_back.setBackground(a6B7Activity.getResources().getDrawable(R.drawable.null_style));
            } else {
                DEV_KMI = STD - currentItem.getKMI();
                if(DEV_KMI == 0){
                    KTG_KMI = "LF";
                    holder.kmi_back.setBackground(a6B7Activity.getResources().getDrawable(R.drawable.success_style));
                } else {
                    KTG_KMI = "LS";
                    holder.kmi_back.setBackground(a6B7Activity.getResources().getDrawable(R.drawable.failed_style));
                }
                holder.kmi_data.setText(String.valueOf(currentItem.getKMI())+"%");
                holder.kmi_dev.setText(String.valueOf(DEV_KMI)+"%");
                holder.kmi_hasil.setText(KTG_KMI);
            }
        } catch (Exception e){
            Toast.makeText(a6B7Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            if(currentItem.getKAL() == -1){
                DEV_KAL = -1;
                KTG_KAL = "-";
                holder.kal_data.setText("-");
                holder.kal_dev.setText("-");
            } else {
                DEV_KAL = STD - currentItem.getKAL();
                if(DEV_KAL == 0){
                    KTG_KAL = "LF";
                } else {
                    KTG_KAL = "LS";
                }
                holder.kal_data.setText(String.valueOf(currentItem.getKAL())+"%");
                holder.kal_dev.setText(String.valueOf(DEV_KAL)+"%");
            }
        } catch (Exception e){
            Toast.makeText(a6B7Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            if(currentItem.getKAL2() == -1){
                DEV_KAL2 = -1;
                KTG_KAL2 = "-";
                holder.kal2_data.setText("-");
                holder.kal2_dev.setText("-");
            } else {
                DEV_KAL2 = STD - currentItem.getKAL2();
                if(DEV_KAL2 == 0){
                    KTG_KAL2 = "LF";
                } else {
                    KTG_KAL2 = "LS";
                }
                holder.kal2_data.setText(String.valueOf(currentItem.getKAL2())+"%");
                holder.kal2_dev.setText(String.valueOf(DEV_KAL2)+"%");
            }
        } catch (Exception e){
            Toast.makeText(a6B7Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            if((KTG_KAL.equals("LF") || KTG_KAL.equals("-")) && (KTG_KAL2.equals("LF") || KTG_KAL2.equals("-"))){

                if(KTG_KAL.equals("-") && KTG_KAL2.equals("-")){
                    KTG_KALL = "-";
                    holder.kal_back.setBackground(a6B7Activity.getResources().getDrawable(R.drawable.null_style));
                } else {
                    KTG_KALL = "LF";
                    holder.kal_back.setBackground(a6B7Activity.getResources().getDrawable(R.drawable.success_style));
                }

            } else {
                KTG_KALL = "LS";
                holder.kal_back.setBackground(a6B7Activity.getResources().getDrawable(R.drawable.failed_style));
            }
            holder.kal_hasil.setText(KTG_KALL);
        } catch (Exception e){
            Toast.makeText(a6B7Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            if(currentItem.getKFI() == -1){
                DEV_KFI = -1;
                KTG_KFI = "-";
                holder.kfi_data.setText("-");
                holder.kfi_dev.setText("-");
            } else {
                DEV_KFI = STD - currentItem.getKFI();
                if(DEV_KFI == 0){
                    KTG_KFI = "LF";
                } else {
                    KTG_KFI = "LS";
                }
                holder.kfi_data.setText(String.valueOf(currentItem.getKFI())+"%");
                holder.kfi_dev.setText(String.valueOf(DEV_KFI)+"%");
            }
        } catch (Exception e){
            Toast.makeText(a6B7Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            if(currentItem.getKFI2() == -1){
                DEV_KFI2 = -1;
                KTG_KFI2 = "-";
                holder.kfi2_data.setText("-");
                holder.kfi2_dev.setText("-");
            } else {
                DEV_KFI2 = STD - currentItem.getKFI2();
                if(DEV_KFI2 == 0){
                    KTG_KFI2 = "LF";
                } else {
                    KTG_KFI2 = "LS";
                }
                holder.kfi2_data.setText(String.valueOf(currentItem.getKFI2())+"%");
                holder.kfi2_dev.setText(String.valueOf(DEV_KFI2)+"%");
            }
        } catch (Exception e){
            Toast.makeText(a6B7Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            if((KTG_KFI.equals("LF") || KTG_KFI.equals("-")) && (KTG_KFI2.equals("LF") || KTG_KFI2.equals("-"))){

                if(KTG_KFI.equals("-") && KTG_KFI2.equals("-")){
                    KTG_KFII = "-";
                    holder.kfi_back.setBackground(a6B7Activity.getResources().getDrawable(R.drawable.null_style));
                } else {
                    KTG_KFII = "LF";
                    holder.kfi_back.setBackground(a6B7Activity.getResources().getDrawable(R.drawable.success_style));
                }

            } else {
                KTG_KFII = "LS";
                holder.kfi_back.setBackground(a6B7Activity.getResources().getDrawable(R.drawable.failed_style));
            }
            holder.kfi_hasil.setText(KTG_KALL);
        } catch (Exception e){
            Toast.makeText(a6B7Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            if((KTG_KMI.equals("LF") || KTG_KMI.equals("-")) && (KTG_KALL.equals("LF") || KTG_KALL.equals("-")) && (KTG_KFII.equals("LF") || KTG_KFII.equals("-"))){

                if(KTG_KMI.equals("-") && KTG_KALL.equals("-") && KTG_KFII.equals("-")){
                    KTG_KLF = "Tidak Dinilai";
                } else {
                    KTG_KLF = "LF";
                }

            } else {
                KTG_KLF = "LS";
            }
        } catch (Exception e){
            Toast.makeText(a6B7Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            holder.rec_data.setText(currentItem.getREC());
        } catch (Exception e){
            Toast.makeText(a6B7Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            holder.FT1.setImageBitmap(BitmapFactory.decodeFile(currentItem.getDIR1()));
        } catch (Exception e){
            Toast.makeText(a6B7Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            holder.FT2.setImageBitmap(BitmapFactory.decodeFile(currentItem.getDIR2()));
        } catch (Exception e){
            Toast.makeText(a6B7Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            if(currentItem.getUPLOAD().equals("F")){
                a6B7Activity.FAB_UPLOAD.hide();
                a6B7Activity.UPLOAD.setImageResource(R.drawable.ic_file_upload_white_18dp);
                a6B7Activity.FAB_UPLOAD.setEnabled(true);
            } else {
                a6B7Activity.UPLOAD.setImageResource(R.drawable.ic_done_white_18dp);
                a6B7Activity.FAB_UPLOAD.setEnabled(false);
            }
        } catch (Exception e){
            Toast.makeText(a6B7Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            a6B7Activity.ID = currentItem.getID();
            a6B7Activity.KMI_TXT = currentItem.getKMI();
            a6B7Activity.KAL_TXT = currentItem.getKAL();
            a6B7Activity.KAL2_TXT = currentItem.getKAL2();
            a6B7Activity.KFI_TXT = currentItem.getKFI();
            a6B7Activity.KFI2_TXT = currentItem.getKFI2();
            a6B7Activity.REC_TXT = currentItem.getREC();
            a6B7Activity.DIR1 = currentItem.getDIR1();
            a6B7Activity.DIR2 = currentItem.getDIR2();

            a6B7Activity.DEV_KMI = DEV_KMI;
            a6B7Activity.DEV_KAL = DEV_KAL;
            a6B7Activity.DEV_KAL2 = DEV_KAL2;
            a6B7Activity.DEV_KFI = DEV_KFI;
            a6B7Activity.DEV_KFI2 = DEV_KFI2;

            a6B7Activity.KTG_KMI = KTG_KMI;
            a6B7Activity.KTG_KALL = KTG_KALL;
            a6B7Activity.KTG_KFII = KTG_KFII;
            a6B7Activity.KTG_KLF = KTG_KLF;

            if(KTG_KLF.equals("LS")){
                a6B7Activity.klf.setText(KTG_KLF);
                a6B7Activity.klf.setBackground(a6B7Activity.getResources().getDrawable(R.drawable.failed_style));
                Animation animation = AnimationUtils.loadAnimation(a6B7Activity.getApplicationContext(), R.anim.recycle_bottom);
                a6B7Activity.klf.startAnimation(animation);
            } else if(KTG_KLF.equals("Tidak Dinilai")){
                a6B7Activity.klf.setText("Tidak Dinilai");
                a6B7Activity.klf.setBackground(a6B7Activity.getResources().getDrawable(R.drawable.null_style));
                Animation animation = AnimationUtils.loadAnimation(a6B7Activity.getApplicationContext(), R.anim.recycle_bottom);
                a6B7Activity.klf.startAnimation(animation);
            } else {
                a6B7Activity.klf.setText(KTG_KLF);
                a6B7Activity.klf.setBackground(a6B7Activity.getResources().getDrawable(R.drawable.success_style));
                Animation animation = AnimationUtils.loadAnimation(a6B7Activity.getApplicationContext(), R.anim.recycle_bottom);
                a6B7Activity.klf.startAnimation(animation);
            }
        } catch (Exception e){
            Toast.makeText(a6B7Activity, "Bug : "+e.toString(), Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public int getItemCount() {
        return mdataList.size();
    }

}
