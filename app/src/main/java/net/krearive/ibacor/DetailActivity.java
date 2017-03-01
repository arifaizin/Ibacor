package net.krearive.ibacor;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import jp.wasabeef.recyclerview.adapters.AlphaInAnimationAdapter;

public class DetailActivity extends AppCompatActivity {
    //Creating a List of moviees
//    private List<JadwalList> listJadwal;
//    private List<JamList> listJam;
    //Creating a List of moviees
    private List<MovieList> listMovies;

    //Creating Views
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.Adapter adapter;

    private String idJudul, idGenre, idPoster;
    private int idPosition;
    ImageView detailposter;
    TextView detailgenre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        //terima data

        //sambungin ke xml
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        //inisialisasi recyclerview
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        listMovies = new ArrayList<>();

        getData();

        idJudul = getIntent().getStringExtra(Config.TAG_MOVIE);
        idGenre = getIntent().getStringExtra(Config.TAG_GENRE);
        idPoster = getIntent().getStringExtra(Config.TAG_POSTER);
        idPosition = getIntent().getExtras().getInt("position");
        getSupportActionBar().setTitle(idJudul);
        Log.i("Posisi ", "" + idPosition);

        detailgenre= (TextView) findViewById(R.id.detailgenre);
        detailgenre.setText(idGenre);

        detailposter = (ImageView)findViewById(R.id.detailposter);
        Picasso.with(getApplicationContext())
                .load(idPoster)
                .into(detailposter);


//        //sambungin ke xml
//        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
//        //inisialisasi recyclerview
//        recyclerView.setHasFixedSize(true);
//        layoutManager = new LinearLayoutManager(this);
//        recyclerView.setLayoutManager(layoutManager);
//
//        listJadwal = new ArrayList<>();
//
//        getData();
    }

    private void getData() {
        //saatnya ambil data
        //kasih loading
        final ProgressDialog loading = ProgressDialog.show(this, "Loading Data", "Mohon bersabar",false,false);

        JsonObjectRequest ambildata = new JsonObjectRequest(Request.Method.GET, Config.DATA_URL, null, new Response.Listener<JSONObject>() {
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

                        JSONArray arrayjadwal = json.getJSONArray("jadwal");
                        for (int ih = 0; ih < arrayjadwal.length(); ih++){
                            JSONObject objectJadwal = arrayjadwal.getJSONObject(0);
                            Log.i("JSON jadwal ",""+objectJadwal); //nampilin info
                            movie.setHarga(objectJadwal.getString(Config.TAG_HARGA));
                            movie.setBioskop(objectJadwal.getString(Config.TAG_BIOSKOP));

                        }

                        listMovies.add(movie);
                    }
                    setAdapter(listMovies);

                } catch (JSONException e) {
                    e.printStackTrace();
                    Log.i("Errornya",""+e);
                    Toast.makeText(DetailActivity.this, "Errornya"+e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //kalau gagal
                Log.i("Errornya",""+error);
                Toast.makeText(DetailActivity.this, "Errornya"+error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        //bikin antrian biar nggak langsung ngeload semua
        RequestQueue antrian = Volley.newRequestQueue(this);
        antrian.add(ambildata);
    }

    private void setAdapter(List<MovieList> listMovies) {

        //Finally initializing our adapter
        adapter = new DetaillAdapter(listMovies, this);
        //Adding adapter to recyclerview
        AlphaInAnimationAdapter alphaAdapter = new AlphaInAnimationAdapter(adapter);
        alphaAdapter.setFirstOnly(false);
        recyclerView.setAdapter(alphaAdapter);
//            recyclerView.setAdapter(adapter);
    }


//    private void getData() {
//        //saatnya ambil data
//        //kasih loading
//        final ProgressDialog loading = ProgressDialog.show(this, "Loading Data", "Mohon bersabar", false, false);
//
//        JsonObjectRequest ambildata = new JsonObjectRequest(Request.Method.GET, Config.DATA_URL, null, new Response.Listener<JSONObject>() {
//            @Override
//            public void onResponse(JSONObject response) {
//                //kalau berhasil ambil url
//                try {
//                    loading.dismiss();//hilangkan loading
//                    JSONArray array = response.getJSONArray("data");
//
//                        JSONObject json = array.getJSONObject(idPosition);
//                        Log.i("JSON ", "" + json); //nampilin info
//
//                        JSONArray arrayjadwal = json.getJSONArray("jadwal");
//                        for (int ih = 0; ih < arrayjadwal.length(); ih++) {
//                            JSONObject objectJadwal = arrayjadwal.getJSONObject(ih);
//                            Log.i("JSON jadwal ", "" + objectJadwal); //nampilin info
//                            JadwalList jadwal = new JadwalList();
//
//                            jadwal.setHarga(objectJadwal.getString(Config.TAG_HARGA));
//                            jadwal.setBioskop(objectJadwal.getString(Config.TAG_BIOSKOP));
//
//
////                            if (objectJadwal.has("jam")){
////                                JSONArray arrayJam = objectJadwal.getJSONArray("jam");
////                                for (int j = 0; j < arrayJam.length() ; j++){
////                                    JamList jam = new JamList();
////                                    jam.setJam(arrayJam.getString(j));
////                                    listJam.add(jam);
////                                }
////                            }
//
//                            listJadwal.add(jadwal);
//                        }
//
//
//
//                    setAdapter(listJadwal);
////                    setJamAdapter(listJam);
//
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                    Log.i("Errornya", "" + e);
//                    Toast.makeText(DetailActivity.this, "Errornya" + e.getMessage(), Toast.LENGTH_SHORT).show();
//                }
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                //kalau gagal
//                Log.i("Errornya", "" + error);
//                Toast.makeText(DetailActivity.this, "Errornya" + error.getMessage(), Toast.LENGTH_SHORT).show();
//            }
//        });
//
//        //bikin antrian biar nggak langsung ngeload semua
//        RequestQueue antrian = Volley.newRequestQueue(this);
//        antrian.add(ambildata);
//    }
//
//    private void setJamAdapter(List<JamList> listJam) {
//        //Finally initializing our adapter
//        adapter = new JamAdapter(listJam, this);
//        //Adding adapter to recyclerview
//        recyclerView.setAdapter(new AlphaInAnimationAdapter(adapter));
//    }
//
//    private void setAdapter(List<JadwalList> listJadwal) {
//
//        //Finally initializing our adapter
//        adapter = new DetailAdapter(listJadwal, this);
//        //Adding adapter to recyclerview
//        recyclerView.setAdapter(new AlphaInAnimationAdapter(adapter));
////            recyclerView.setAdapter(adapter);
//    }
}
