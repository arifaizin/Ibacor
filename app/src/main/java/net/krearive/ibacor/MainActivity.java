package net.krearive.ibacor;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import jp.wasabeef.recyclerview.adapters.ScaleInAnimationAdapter;

public class MainActivity extends AppCompatActivity {
    //Creating a List of moviees
    private List<MovieList> listMovies;
    //Creating Views
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.Adapter adapter;

    private String idKota, idNamaKota;
    private int idPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //sambungin ke xml
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        //inisialisasi recyclerview
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        listMovies = new ArrayList<>();

        //terima data
        idKota = getIntent().getStringExtra(Config.TAG_ID);
        idNamaKota = getIntent().getStringExtra(Config.TAG_KOTA);
        idPosition = getIntent().getExtras().getInt("position");
        getSupportActionBar().setTitle(idNamaKota);
        Log.i("Posisi ", "" + idPosition);

        getData();
    }
        //pakai data contoh
//        for (int i = 0; i<= 10 ; i++){
//            MovieList movieList = new MovieList("Judul" + i, "http://ibacor.com/bcr_asset/images/poster.jpg", "Genrenya nanti disini" );
//            listMovies.add(movieList);
//        }
//
//        adapter = new CardAdapter(listMovies, this);
//
//        recyclerView.setAdapter(adapter);

    private void getData() {
        //saatnya ambil data
        //kasih loading
        final ProgressDialog loading = ProgressDialog.show(this, "Loading Data", "Mohon bersabar",false,false);

        JsonObjectRequest ambildata = new JsonObjectRequest(Request.Method.GET, Config.DATA_URL+"k="+Config.DATA_KEY+"&id="+idKota, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                //kalau berhasil ambil url
                try {
                    loading.dismiss();//hilangkan loading
                    JSONArray array = response.getJSONArray("data");

                    for (int i = 0; i < array.length(); i++){
                        JSONObject json = array.getJSONObject(i);
                        Log.i("JSON ",""+json); //nampilin info
                        MovieList movie = new MovieList();
                        movie.setMovie(json.getString(Config.TAG_MOVIE));
                        movie.setPoster(json.getString(Config.TAG_POSTER));
                        movie.setGenre(json.getString(Config.TAG_GENRE));
                        movie.setDuration(json.getString(Config.TAG_DURATION));

                        JSONArray arrayjadwal = json.getJSONArray("jadwal");
                        for (int ih = 0; ih < arrayjadwal.length(); ih++){
                            JSONObject objectJadwal = arrayjadwal.getJSONObject(0);
//                            Log.i("JSON jadwal ",""+objectJadwal); //nampilin info
                            movie.setHarga(objectJadwal.getString(Config.TAG_HARGA));
                        }

                        listMovies.add(movie);
                    }
                    setAdapter(listMovies);

                } catch (JSONException e) {
                    e.printStackTrace();
                    Log.i("Errornya",""+e);
                    Toast.makeText(MainActivity.this, "Errornya"+e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //kalau gagal
                Log.i("Errornya",""+error);
                Toast.makeText(MainActivity.this, "Errornya"+error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        //bikin antrian biar nggak langsung ngeload semua
        RequestQueue antrian = Volley.newRequestQueue(this);
        antrian.add(ambildata);
    }

    private void setAdapter(List<MovieList> listMovies) {

            //Finally initializing our adapter
            adapter = new CardAdapter(listMovies, this);
            //Adding adapter to recyclerview
        ScaleInAnimationAdapter animationAdapter = new ScaleInAnimationAdapter(adapter);
        animationAdapter.setFirstOnly(false);
        recyclerView.setAdapter(animationAdapter);
//            recyclerView.setAdapter(adapter);
    }


    //This method will get data from the web api
//    private void getData() {
//        //Showing a progress dialog
//        final ProgressDialog loading = ProgressDialog.show(this, "Loading Data", "Please wait...", false, false);
//
//        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, Config.DATA_URL, null, new Response.Listener<JSONObject>() {
//            @Override
//            public void onResponse(JSONObject response) {
//                try {
//                    loading.dismiss();
//                    JSONArray array = response.getJSONArray("data");
//                    for (int i = 0; i < array.length(); i++) {
//
//                        JSONObject json = array.getJSONObject(i);
//                        Log.i("JSON :", "" + json);
//                        MovieList movie = new MovieList();
//                        movie.setMovie(json.getString(Config.TAG_MOVIE));
//                        movie.setGenre(json.getString(Config.TAG_GENRE));
//                        movie.setPoster(json.getString(Config.TAG_POSTER));
//                        listMovies.add(movie);
////                        MovieList movie = new MovieList(
////                                json.getString(Config.TAG_MOVIE),
////                                json.getString(Config.TAG_GENRE),
////                                json.getString(Config.TAG_POSTER)
////                        );
////                        listMovies.add(movie);
//                    }
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//                //Finally initializing our adapter
//                adapter = new CardAdapter(listMovies, getApplicationContext());
//
//                //Adding adapter to recyclerview
//                recyclerView.setAdapter(adapter);
////                setAdapterRecyce(listMovies);
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                Log.i("JSON Response error :", "" + error);
//            }
//        });
//
//                //Creating request queue
//        RequestQueue requestQueue = Volley.newRequestQueue(this);
//
//        //Adding request to the queue
//        requestQueue.add(jsonObjectRequest);
//    }

//    private void setAdapterRecyce(List<MovieList> listMovies) {
//        //Finally initializing our adapter
//        adapter = new CardAdapter(listMovies, this);
//
//        //Adding adapter to recyclerview
//        recyclerView.setAdapter(adapter);
//    }
}
