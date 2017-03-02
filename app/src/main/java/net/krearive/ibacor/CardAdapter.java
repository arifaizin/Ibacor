package net.krearive.ibacor;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.toolbox.ImageLoader;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Win8 on 10/01/2017.
 */

public class CardAdapter extends RecyclerView.Adapter<CardAdapter.ViewHolder>{

    List<MovieList> movieLists;
    Context context; // supaya bisa dipakai di kelas lain
    private ImageLoader imageLoader; //buat ngeload gambar

    //buat constructor


    public CardAdapter(List<MovieList> movieLists, Context context) {
        this.movieLists = movieLists;
        this.context = context;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView judul, genre, duration, harga;
        ImageView poster;

        ///
        CardView cardView;

        public ViewHolder(View itemView) {
            super(itemView);
            judul = (TextView) itemView.findViewById(R.id.judul);
            genre = (TextView) itemView.findViewById(R.id.genre);
            duration = (TextView) itemView.findViewById(R.id.duration);
            harga = (TextView) itemView.findViewById(R.id.harga);
            poster = (ImageView) itemView.findViewById(R.id.gambar);
            ///
            cardView = (CardView) itemView.findViewById(R.id.cardview);


        }
    }

    @Override
    public CardAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //nyambungin ke layout item
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.movie_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final CardAdapter.ViewHolder holder, final int position) {
        final MovieList movieList = movieLists.get(position);
        holder.judul.setText(movieList.getMovie());
        holder.genre.setText(movieList.getGenre());
        holder.harga.setText(movieList.getHarga());
        holder.duration.setText(movieList.getDuration());

//        imageLoader = CustomVolleyRequest.getInstance(context).getImageLoader();
//        //kalau pakai NetworkImageView bisa pakai ini
//        holder.poster.setImageUrl(movieList.getPoster(),imageLoader);

        Picasso.with(context)
                .load(movieList.getPoster())
                .resize(80, 100)
                .centerCrop()
                .into(holder.poster);

        //event klik
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Judulnya "+movieList.getMovie()+" loh..", Toast.LENGTH_SHORT).show();
//
                Intent pindah = new Intent(context, DetailActivity.class);
                //kirim data
                pindah.putExtra(Config.TAG_MOVIE,movieList.getMovie());
                pindah.putExtra(Config.TAG_GENRE,movieList.getGenre());
                pindah.putExtra(Config.TAG_POSTER,movieList.getPoster());
                pindah.putExtra("position",position);
                context.startActivity(pindah);
                //pakai context kalau mulainya nggak dari sini, tapi dari Activity lain

            }
        });




    }

    @Override
    public int getItemCount() {
        //banyaknya list
        return movieLists.size();
    }


}

//    List<MovieList> movieLists;
//    Context context;
//    private ImageLoader imageLoader;
//
//    public CardAdapter(List<MovieList> movieLists, Context context) {
//        this.movieLists = movieLists;
//        this.context = context;
//    }
//
//    public class ViewHolder extends RecyclerView.ViewHolder {
//
//        TextView judul, genre;
//        ImageView poster;
//        CardView cardView;
//        public ViewHolder(View itemView) {
//            super(itemView);
//            judul = (TextView) itemView.findViewById(R.id.judul);
//            genre = (TextView) itemView.findViewById(R.id.genre);
//            poster = (ImageView) itemView.findViewById(R.id.gambar);
//            cardView = (CardView) itemView.findViewById(R.id.cardview);
//
//        }
//    }
//    @Override
//    public CardAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        View v = LayoutInflater.from(parent.getContext())
//                .inflate(R.layout.movie_item,parent,false);
//        return new ViewHolder(v);
//    }
//
//    @Override
//    public void onBindViewHolder(CardAdapter.ViewHolder holder, int position) {
//        final MovieList movieList = movieLists.get(position);
//
//       imageLoader = CustomVolleyRequest.getInstance(context).getImageLoader();
//       imageLoader.get(movieList.getPoster(), ImageLoader.getImageListener(holder.poster, R.mipmap.ic_launcher, android.R.drawable.ic_dialog_alert));
//
//        holder.judul.setText(movieList.getMovie());
//        holder.genre.setText(movieList.getGenre());
////        holder.poster.setImageUrl(movieList.getPoster(), imageLoader);
//
////        Picasso.with(context)
////                .load(movieList.getPoster())
////                .resize(70,100)
////                .error(android.R.drawable.ic_dialog_alert)
////                .into(holder.poster);
//
//        holder.cardView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(context, "Ini Film "+movieList.getMovie(), Toast.LENGTH_SHORT).show();
//            }
//        });
//
//        holder.genre.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(context, "Genrenya "+movieList.getGenre(), Toast.LENGTH_SHORT).show();
//            }
//        });
//
//        holder.poster.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(context, "URL "+movieList.getPoster(), Toast.LENGTH_SHORT).show();
//            }
//        });
//    }
//
//    @Override
//    public int getItemCount() {
//        return movieLists.size();
//    }
//}
//public class CardAdapter extends RecyclerView.Adapter<CardAdapter.ViewHolder> {
//
//    private ImageLoader imageLoader;
//    private Context context;
//
//    //List of superHeroes
//    List<MovieList> superHeroes;
//
//    public CardAdapter(List<MovieList> superHeroes, Context context){
//        super();
//        //Getting all the superheroes
//        this.superHeroes = superHeroes;
//        this.context = context;
//    }
//
//    @Override
//    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        View v = LayoutInflater.from(parent.getContext())
//                .inflate(R.layout.movie_item, parent, false);
//        ViewHolder viewHolder = new ViewHolder(v);
//        return viewHolder;
//    }
//
//    @Override
//    public void onBindViewHolder(ViewHolder holder, int position) {
//
//        MovieList superHero =  superHeroes.get(position);
//
//        imageLoader = CustomVolleyRequest.getInstance(context).getImageLoader();
//        imageLoader.get(superHero.getImageUrl(), ImageLoader.getImageListener(holder.imageView, R.mipmap.ic_launcher, android.R.drawable.ic_dialog_alert));
//
//        holder.imageView.setImageUrl(superHero.getImageUrl(), imageLoader);
//        holder.textViewName.setText(superHero.getName());
//        holder.textViewRank.setText(String.valueOf(superHero.getRank()));
//        holder.textViewRealName.setText(superHero.getRealName());
//        holder.textViewCreatedBy.setText(superHero.getCreatedBy());
//        holder.textViewFirstAppearance.setText(superHero.getFirstAppearance());
//
//        String powers = "";
//
//        for(int i = 0; i<superHero.getPowers().size(); i++){
//            powers+= superHero.getPowers().get(i);
//        }
//
//        holder.textViewPowers.setText(powers);
//    }
//
//    @Override
//    public int getItemCount() {
//        return superHeroes.size();
//    }
//
//    class ViewHolder extends RecyclerView.ViewHolder{
//        public NetworkImageView imageView;
//        public TextView textViewName;
//        public TextView textViewRank;
//        public TextView textViewRealName;
//        public TextView textViewCreatedBy;
//        public TextView textViewFirstAppearance;
//        public TextView  textViewPowers;
//
//        public ViewHolder(View itemView) {
//            super(itemView);
//            imageView = (NetworkImageView) itemView.findViewById(R.id.imageViewHero);
//            textViewName = (TextView) itemView.findViewById(R.id.textViewName);
//            textViewRank= (TextView) itemView.findViewById(R.id.textViewRank);
//            textViewRealName= (TextView) itemView.findViewById(R.id.textViewRealName);
//            textViewCreatedBy= (TextView) itemView.findViewById(R.id.textViewCreatedBy);
//            textViewFirstAppearance= (TextView) itemView.findViewById(R.id.textViewFirstAppearance);
//            textViewPowers= (TextView) itemView.findViewById(R.id.textViewPowers);
//        }
//    }
//}
