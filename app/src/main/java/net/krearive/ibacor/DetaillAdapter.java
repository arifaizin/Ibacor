package net.krearive.ibacor;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Win8 on 23/02/2017.
 */

public class DetaillAdapter extends RecyclerView.Adapter<DetaillAdapter.ViewHolder>{
    List<MovieList> movieLists;
    Context context; // supaya bisa dipakai di kelas lain

    //buat constructor : manggil DetailAdapter (movieLists, context)
    public DetaillAdapter(List<MovieList> movieLists, Context context) {
        this.movieLists = movieLists;
        this.context = context;
    }
    //sambungin ke id yang di layout
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView bioskop, harga;

        public ViewHolder(View itemView) {
            super(itemView);
            harga = (TextView) itemView.findViewById(R.id.harga);
            bioskop = (TextView) itemView.findViewById(R.id.bioskop);

        }
    }
    //ganti layout item
    @Override
    public DetaillAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.detail_item, parent, false);
        return new DetaillAdapter.ViewHolder(v);
    }
    //setText
    @Override
    public void onBindViewHolder(final DetaillAdapter.ViewHolder holder, final int position) {
        final MovieList movieList = movieLists.get(position);
        holder.bioskop.setText(movieList.getGenre());
        holder.harga.setText(movieList.getHarga());
    }

    @Override
    public int getItemCount() {
        //banyaknya list
        return movieLists.size();
    }


}