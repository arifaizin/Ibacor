package net.krearive.ibacor;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Win8 on 10/01/2017.
 */

public class DetailAdapter extends RecyclerView.Adapter<DetailAdapter.ViewHolder>{

    List<MovieList> movieLists;
    Context context; // supaya bisa dipakai di kelas lain

    //buat constructor
    public DetailAdapter(List<MovieList> movieLists, Context context) {
        this.movieLists = movieLists;
        this.context = context;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView bioskop, harga;

        ///
        CardView cardView;

        public ViewHolder(View itemView) {
            super(itemView);
            harga = (TextView) itemView.findViewById(R.id.harga);
            bioskop = (TextView) itemView.findViewById(R.id.bioskop);

            ///
            cardView = (CardView) itemView.findViewById(R.id.cardview);


        }
    }

    @Override
    public DetailAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //nyambungin ke layout item
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.detail_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(DetailAdapter.ViewHolder holder, int position) {
        final MovieList MovieList = movieLists.get(position);
        holder.harga.setText(MovieList.getHarga());
        holder.bioskop.setText(MovieList.getBioskop());


    }

    @Override
    public int getItemCount() {
        //banyaknya list
        return movieLists.size();
    }
}

