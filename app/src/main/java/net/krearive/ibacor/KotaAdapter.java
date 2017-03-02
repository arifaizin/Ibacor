package net.krearive.ibacor;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import static net.krearive.ibacor.R.id.textViewKota;

/**
 * Created by Win8 on 23/02/2017.
 */

public class KotaAdapter extends RecyclerView.Adapter<KotaAdapter.ViewHolder>{
    List<KotaList> kotalists;
    Context context; // supaya bisa dipakai di kelas lain

    //buat constructor : manggil DetailAdapter (kotalists, context)
    public KotaAdapter(List<KotaList> kotalists, Context context) {
        this.kotalists = kotalists;
        this.context = context;
    }


    //sambungin ke id yang di layout
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView kota;

        public ViewHolder(View itemView) {
            super(itemView);
            kota = (TextView) itemView.findViewById(textViewKota);

        }
    }
    //ganti layout item
    @Override
    public KotaAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.kota_item, parent, false);
        return new KotaAdapter.ViewHolder(v);
    }
    //setText
    @Override
    public void onBindViewHolder(final KotaAdapter.ViewHolder holder, final int position) {
        final KotaList kotaList = kotalists.get(position);
        holder.kota.setText(kotaList.getNamaKota());

        holder.kota.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent pindah = new Intent(context, MainActivity.class);
                //kirim data
                pindah.putExtra(Config.TAG_ID,kotaList.getIdKota());
                pindah.putExtra(Config.TAG_KOTA,kotaList.getNamaKota());
                pindah.putExtra("position",position);
                context.startActivity(pindah);

                SharedPreferences simpanidkota = PreferenceManager.getDefaultSharedPreferences(context);
                SharedPreferences.Editor editor = simpanidkota.edit();
                editor.putString(Config.TAG_ID, kotaList.getIdKota());
                editor.commit();
            }
        });
    }

    @Override
    public int getItemCount() {
        //banyaknya list
        return kotalists.size();
    }


}