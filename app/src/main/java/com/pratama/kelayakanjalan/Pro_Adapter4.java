package com.pratama.kelayakanjalan;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Handler;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.eyalbira.loadingdots.LoadingDots;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.appcompat.view.ContextThemeWrapper;
import androidx.appcompat.widget.PopupMenu;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class Pro_Adapter4 extends RecyclerView.Adapter<Pro_Adapter4.ProViewHolder> {

    private DatabaseHelper databaseHelper;
    private MainActivity mainActivity;
    private List<Pro_Class> mdataList;
    private Dialog myDialog;
    private String IDPRO_TXT, NMPRO_TXT;
    private Boolean hasil = false;
    private int HAPUS;

    public class ProViewHolder extends RecyclerView.ViewHolder {

        private TextView NBL;
        private CardView posisi;

        public ProViewHolder(@NonNull View itemView) {
            super(itemView);

            NBL = (TextView) itemView.findViewById(R.id.NBL);
            posisi = (CardView) itemView.findViewById(R.id.back_prov);

        }
    }

    public Pro_Adapter4(MainActivity mainActivity, List<Pro_Class> mdataList) {
        this.mainActivity = mainActivity;
        this.mdataList = mdataList;
    }

    @NonNull
    @Override
    public Pro_Adapter4.ProViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.pro_layout, parent, false);
        Pro_Adapter4.ProViewHolder pkk = new Pro_Adapter4.ProViewHolder(view);
        return pkk;
    }

    @Override
    public void onBindViewHolder(@NonNull final Pro_Adapter4.ProViewHolder holder, int position) {

        final Pro_Class currentItem = mdataList.get(position);

        databaseHelper = new DatabaseHelper(mainActivity);
        myDialog = new Dialog(mainActivity);

        holder.NBL.setText(currentItem.getNPRO());

        holder.posisi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mainActivity, RuasActivity.class);
                intent.putExtra("idpro", currentItem.getID());
                intent.putExtra("pro", currentItem.getNPRO());
                mainActivity.startActivity(intent);
                mainActivity.overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        });

        holder.posisi.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Context wrapper = new ContextThemeWrapper(mainActivity, R.style.MyPopupStyle);
                PopupMenu popupMenu = new PopupMenu(wrapper, holder.posisi);
                popupMenu.inflate(R.menu.option_menu);
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()){
                            case R.id.edit:
                                final TextInputLayout TIIDPRO, TINMPRO;
                                final TextInputEditText IDPRO, NMPRO;
                                final LoadingDots load;
                                final Button yes, no;
                                final RelativeLayout line;
                                final ImageView img_success;
                                final TextView label;
                                myDialog.setContentView(R.layout.add_prov);
                                TIIDPRO = (TextInputLayout) myDialog.findViewById(R.id.TIIDPRO);
                                TINMPRO = (TextInputLayout) myDialog.findViewById(R.id.TINMPRO);
                                IDPRO = (TextInputEditText) myDialog.findViewById(R.id.IDPRO);
                                NMPRO = (TextInputEditText) myDialog.findViewById(R.id.NMPRO);
                                load = (LoadingDots) myDialog.findViewById(R.id.load);
                                label = (TextView) myDialog.findViewById(R.id.label);
                                yes = (Button) myDialog.findViewById(R.id.yes);
                                no = (Button) myDialog.findViewById(R.id.no);
                                line = (RelativeLayout) myDialog.findViewById(R.id.line);
                                img_success = (ImageView) myDialog.findViewById(R.id.img_success);

                                load.setVisibility(View.GONE);
                                img_success.setVisibility(View.GONE);
                                label.setText("EDIT PROV "+currentItem.getNPRO());
                                yes.setText("edit");

                                IDPRO.setText(currentItem.getID());
                                IDPRO.setEnabled(false);
                                NMPRO.setText(currentItem.getNPRO());

                                yes.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        if (IDPRO.getText().toString().equals("") || NMPRO.getText().toString().equals("")) {

                                            if(IDPRO.getText().toString().equals("")){
                                                TIIDPRO.setError("Masukkan ID!");
                                            }
                                            if(NMPRO.getText().toString().equals("")){
                                                TINMPRO.setError("Masukkan Nama!");
                                            }
                                        } else {
                                            IDPRO_TXT = IDPRO.getText().toString();
                                            NMPRO_TXT = NMPRO.getText().toString();
                                            HAPUS = 0;

                                            try {
                                                databaseHelper.updateDataProv(IDPRO_TXT, NMPRO_TXT, "F", HAPUS);
                                            } catch (Exception ex){
                                                Log.e("Error Update Prov : ", ex.toString());
                                            }

                                            if(mainActivity.connected == true){
                                                new updateDataProv().execute((Void[])null);
                                            }

                                            load.setVisibility(View.VISIBLE);
                                            TIIDPRO.setVisibility(View.GONE);
                                            TINMPRO.setVisibility(View.GONE);
                                            no.setVisibility(View.GONE);
                                            line.setVisibility(View.GONE);
                                            yes.setText("saving...");
                                            yes.setEnabled(false);
                                            hasil = true;

                                            final Handler handler = new Handler();
                                            handler.postDelayed(new Runnable() {
                                                @Override
                                                public void run() {
                                                    load.setVisibility(View.GONE);
                                                    img_success.setVisibility(View.VISIBLE);
                                                    no.setVisibility(View.VISIBLE);
                                                    no.setText("done");
                                                    no.setTextColor(mainActivity.getResources().getColor(R.color.WindowBgActiveDark));
                                                    yes.setVisibility(View.GONE);
                                                }
                                            }, 2000);

                                        }
                                    }
                                });

                                no.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        if(hasil == true){
                                            mainActivity.proArrayList.clear();

                                            Context context = mainActivity.pro_recycler.getContext();
                                            LayoutAnimationController controller = null;
                                            controller = AnimationUtils.loadLayoutAnimation(context, R.anim.layout_recycle);

                                            mainActivity.proArrayList.addAll(databaseHelper.allDataPRO());

                                            mainActivity.adapter3 = new Pro_Adapter3(mainActivity, mainActivity.proArrayList);
                                            mainActivity.pro_recycler.setAdapter(mainActivity.adapter3);
                                            mainActivity.pro_recycler.setNestedScrollingEnabled(false);
                                            mainActivity.pro_recycler.setLayoutAnimation(controller);
                                            mainActivity.pro_recycler.getAdapter().notifyDataSetChanged();
                                            mainActivity.pro_recycler.scheduleLayoutAnimation();
                                            hasil = false;
                                        }
                                        myDialog.dismiss();
                                    }
                                });

                                myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                                myDialog.getWindow().getAttributes().windowAnimations = R.style.animate_default;
                                myDialog.show();
                                break;
                            case R.id.delete:
                                final Button no2, yes2;
                                final TextView title, sub_title, NRU;
                                final ImageView icon;
                                final LoadingDots load2;
                                final RelativeLayout line2;
                                myDialog.setContentView(R.layout.delete_layout);
                                title = (TextView) myDialog.findViewById(R.id.judul);
                                sub_title = (TextView) myDialog.findViewById(R.id.sub_judul);
                                icon = (ImageView) myDialog.findViewById(R.id.image);
                                load2 = (LoadingDots) myDialog.findViewById(R.id.load);
                                no2 = (Button) myDialog.findViewById(R.id.no);
                                yes2 = (Button) myDialog.findViewById(R.id.yes);
                                NRU = (TextView) myDialog.findViewById(R.id.NRU);
                                line2 = (RelativeLayout) myDialog.findViewById(R.id.line);

                                load2.setVisibility(View.GONE);
                                icon.setVisibility(View.GONE);
                                IDPRO_TXT = currentItem.getID();
                                NMPRO_TXT = currentItem.getNPRO();
                                HAPUS = 1;
                                NRU.setText(currentItem.getNPRO());

                                no2.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        myDialog.dismiss();
                                    }
                                });

                                yes2.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        if(hasil != true){
                                            title.setText("Deleting");
                                            sub_title.setText("Mohon tunggu...");
                                            no2.setVisibility(View.GONE);
                                            NRU.setVisibility(View.GONE);
                                            line2.setVisibility(View.GONE);
                                            yes2.setTextColor(mainActivity.getResources().getColor(R.color.Red600));
                                            yes2.setText("Loading");
                                            load2.setVisibility(View.VISIBLE);
                                            yes2.setEnabled(false);

                                            try{
                                                databaseHelper.updateDataProv(currentItem.getID(), currentItem.getNPRO(), "F", HAPUS);
                                            } catch (Exception ex){
                                                Log.e("Error Delete Prov : ", ex.toString());
                                            }

                                            if(mainActivity.connected == true){
                                                new updateDataProv().execute((Void[])null);
                                            }

                                            final Handler handler = new Handler();
                                            handler.postDelayed(new Runnable() {
                                                @Override
                                                public void run() {
                                                    title.setText("Success");
                                                    sub_title.setText("Data berhasil dihapus");
                                                    load2.setVisibility(View.GONE);
                                                    icon.setVisibility(View.VISIBLE);
                                                    yes2.setText("Done");
                                                    hasil = true;
                                                    yes2.setEnabled(true);

                                                }
                                            }, 2000);

                                        } else {
                                            mainActivity.proArrayList.clear();

                                            Context context = mainActivity.pro_recycler.getContext();
                                            LayoutAnimationController controller = null;
                                            controller = AnimationUtils.loadLayoutAnimation(context, R.anim.layout_recycle);

                                            mainActivity.proArrayList.addAll(databaseHelper.allDataPRO());

                                            mainActivity.adapter3 = new Pro_Adapter3(mainActivity, mainActivity.proArrayList);
                                            mainActivity.pro_recycler.setAdapter(mainActivity.adapter3);
                                            mainActivity.pro_recycler.setNestedScrollingEnabled(false);
                                            mainActivity.pro_recycler.setLayoutAnimation(controller);
                                            mainActivity.pro_recycler.getAdapter().notifyDataSetChanged();
                                            mainActivity.pro_recycler.scheduleLayoutAnimation();
                                            hasil = false;
                                            myDialog.dismiss();
                                        }

                                    }
                                });

                                myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                                myDialog.getWindow().getAttributes().windowAnimations = R.style.animate_default;
                                myDialog.show();
                                break;
                            default:
                                break;
                        }
                        return false;
                    }
                });
                try {
                    Field[] fields = popupMenu.getClass().getDeclaredFields();
                    for (Field field : fields) {
                        if ("mPopup".equals(field.getName())) {
                            field.setAccessible(true);
                            Object menuPopupHelper = field.get(popupMenu);
                            Class<?> classPopupHelper = Class.forName(menuPopupHelper.getClass().getName());
                            Method setForceIcons = classPopupHelper.getMethod("setForceShowIcon", boolean.class);
                            setForceIcons.invoke(menuPopupHelper, true);
                            break;
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                popupMenu.setGravity(Gravity.RIGHT);
                popupMenu.show();
                return true;
            }
        });

    }

    @Override
    public int getItemCount() {
        return mdataList.size();
    }

    private class updateDataProv extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {

            try {

                Class.forName("com.mysql.jdbc.Driver");

                String url = "jdbc:mysql://srv67.niagahoster.com/u6887969_kelaikanjalan?zeroDateTimeBehavior=convertToNull&serverTimezone=UTC";
                String user = "u6887969_admin_kelaikanjalan";
                String password = "pr0yek4ndr01d";

                Connection conn = DriverManager.getConnection(url, user, password);

                Statement statement = conn.createStatement();

                String insert = "UPDATE propinsi SET ID_PROPINSI = '"+IDPRO_TXT+"', NAMA_PROPINSI = '"+NMPRO_TXT+"', HAPUS = '"+HAPUS+"' WHERE ID_PROPINSI = '"+IDPRO_TXT+"'";

                statement.executeUpdate(insert);

                databaseHelper.updateDataProv(IDPRO_TXT, NMPRO_TXT, "T", HAPUS);

            } catch (Exception ex) {
                Log.e("Error : ", ex.toString());
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
        }
    }

}
