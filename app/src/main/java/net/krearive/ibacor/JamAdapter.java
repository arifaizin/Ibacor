package net.krearive.ibacor;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import static net.krearive.ibacor.R.id.jam;

/**
 * Created by Win8 on 23/02/2017.
 */

public class JamAdapter extends RecyclerView.Adapter<net.krearive.ibacor.JamAdapter.ViewHolder> {

    List<JamList> jamLists;
    Context context; // supaya bisa dipakai di kelas lain
    //buat constructor

    public JamAdapter(List<JamList> jamLists, Context context) {
        this.jamLists = jamLists;
        this.context = context;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvjam;

        ///
        CardView cardView;

        public ViewHolder(View itemView) {
            super(itemView);
            tvjam = (TextView) itemView.findViewById(jam);

            ///
            cardView = (CardView) itemView.findViewById(R.id.cardview);


        }
    }

    @Override
    public net.krearive.ibacor.JamAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //nyambungin ke layout item
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.jam_item, parent, false);
        return new net.krearive.ibacor.JamAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(net.krearive.ibacor.JamAdapter.ViewHolder holder, int position) {
        final JamList JamList = jamLists.get(position);
        holder.tvjam.setText(JamList.getJam());


    }

    @Override
    public int getItemCount() {
        //banyaknya list
        return jamLists.size();
    }
}

