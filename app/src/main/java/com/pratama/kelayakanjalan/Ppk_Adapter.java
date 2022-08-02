package com.pratama.kelayakanjalan;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class Ppk_Adapter extends RecyclerView.Adapter<Ppk_Adapter.PkkViewHolder> {

    private IdentitasActivity identitasActivity;
    private List<Ppk_Class> mdataList;

    public class PkkViewHolder extends RecyclerView.ViewHolder {

        private TextView NBL;
        private CardView posisi;

        public PkkViewHolder(@NonNull View itemView) {
            super(itemView);

            NBL = (TextView) itemView.findViewById(R.id.NBL);
            posisi = (CardView) itemView.findViewById(R.id.posisi);

        }
    }

    public Ppk_Adapter(IdentitasActivity identitasActivity, List<Ppk_Class> mdataList) {
        this.identitasActivity = identitasActivity;
        this.mdataList = mdataList;
    }

    @NonNull
    @Override
    public Ppk_Adapter.PkkViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.balai_layout, parent, false);
        Ppk_Adapter.PkkViewHolder pkk = new Ppk_Adapter.PkkViewHolder(view);
        return pkk;
    }

    @Override
    public void onBindViewHolder(@NonNull Ppk_Adapter.PkkViewHolder holder, int position) {

        final Ppk_Class currentItem = mdataList.get(position);

        holder.NBL.setText(currentItem.getNPK());

        holder.posisi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                identitasActivity.PKK.setText(currentItem.getNPK());
                identitasActivity.pkk_data.setVisibility(View.GONE);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mdataList.size();
    }

    public void filterList(List<Ppk_Class> filteredList) {
        mdataList = filteredList;
        notifyDataSetChanged();

        if(mdataList.isEmpty()){
            identitasActivity.pkk_data.setVisibility(View.GONE);
        } else {
            identitasActivity.pkk_data.setVisibility(View.VISIBLE);
        }

    }

}
