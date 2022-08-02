package com.pratama.kelayakanjalan;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class Pro_Adapter extends RecyclerView.Adapter<Pro_Adapter.ProViewHolder> {

    private MainActivity mainActivity;
    private IdentitasActivity identitasActivity;
    private List<Pro_Class> mdataList;

    public class ProViewHolder extends RecyclerView.ViewHolder {

        private TextView NBL;
        private CardView posisi;

        public ProViewHolder(@NonNull View itemView) {
            super(itemView);

            NBL = (TextView) itemView.findViewById(R.id.NBL);
            posisi = (CardView) itemView.findViewById(R.id.posisi);

        }
    }

    public Pro_Adapter(IdentitasActivity identitasActivity, List<Pro_Class> mdataList) {
        this.identitasActivity = identitasActivity;
        this.mdataList = mdataList;
    }

    public Pro_Adapter(MainActivity mainActivity, List<Pro_Class> mdataList) {
        this.mainActivity = mainActivity;
        this.mdataList = mdataList;
    }

    @NonNull
    @Override
    public Pro_Adapter.ProViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.balai_layout, parent, false);
        Pro_Adapter.ProViewHolder pkk = new Pro_Adapter.ProViewHolder(view);
        return pkk;
    }

    @Override
    public void onBindViewHolder(@NonNull Pro_Adapter.ProViewHolder holder, int position) {

        final Pro_Class currentItem = mdataList.get(position);

        holder.NBL.setText(currentItem.getNPRO());

        holder.posisi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                identitasActivity.PRO.setText(currentItem.getNPRO());
                identitasActivity.pro_data.setVisibility(View.GONE);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mdataList.size();
    }

    public void filterList(List<Pro_Class> filteredList) {
        mdataList = filteredList;
        notifyDataSetChanged();

        if(mdataList.isEmpty()){
            identitasActivity.pro_data.setVisibility(View.GONE);
        } else {
            identitasActivity.pro_data.setVisibility(View.VISIBLE);
        }

    }

}
