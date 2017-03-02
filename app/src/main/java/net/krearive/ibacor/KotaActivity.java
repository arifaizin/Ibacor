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

import jp.wasabeef.recyclerview.adapters.AlphaInAnimationAdapter;

public class KotaActivity extends AppCompatActivity {
    //Creating a List of datakotaes
    private List<KotaList> listKota;
    //Creating Views
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kota);
        //sambungin ke xml
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        //inisialisasi recyclerview
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        listKota = new ArrayList<>();

        getData();
    }

    private void getData() {
        //saatnya ambil data
        //kasih loading
        final ProgressDialog loading = ProgressDialog.show(this, "Loading Data", "Mohon bersabar", false, false);
        String alamat = Config.DATA_URL+"k="+Config.DATA_KEY;
        Log.i("alamat ",""+alamat);
        JsonObjectRequest ambildata = new JsonObjectRequest(Request.Method.GET,Config.DATA_FUL, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                //kalau berhasil ambil url
                try {
                    loading.dismiss();//hilangkan loading
                    JSONArray array = response.getJSONArray("data");

                    for (int i = 0; i < array.length(); i++){
                        JSONObject json = array.getJSONObject(i);
                        Log.i("JSON ",""+json); //nampilin info
                        KotaList datakota = new KotaList();
                        datakota.setIdKota(json.getString(Config.TAG_ID));
                        datakota.setNamaKota(json.getString(Config.TAG_KOTA));

                        listKota.add(datakota);
                    }
                    setAdapter(listKota);

                } catch (JSONException e) {
                    e.printStackTrace();
                    Log.i("Errornya parsing", "" + e);
                    Toast.makeText(KotaActivity.this, "Errornya" + e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //kalau gagal
                Log.i("Errornya koneksi", "" + error);
                Toast.makeText(KotaActivity.this, "Errornya" + error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        //bikin antrian biar nggak langsung ngeload semua
        RequestQueue antrian = Volley.newRequestQueue(this);
        antrian.add(ambildata);
    }

    private void setAdapter(List<KotaList> listKota) {

        //Finally initializing our adapter
        adapter = new KotaAdapter(listKota, this);
        //Adding adapter to recyclerview
        AlphaInAnimationAdapter animationAdapter = new AlphaInAnimationAdapter(adapter);
        animationAdapter.setFirstOnly(false);
        recyclerView.setAdapter(animationAdapter);
//            recyclerView.setAdapter(adapter);
    }
}
