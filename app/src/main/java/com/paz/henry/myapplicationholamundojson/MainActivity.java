package com.paz.henry.myapplicationholamundojson;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
TextView txmundo;
    public ArrayList<AndroidVersion> arrayList = new ArrayList<AndroidVersion>();
 //  public ArrayList<String> arrayList = new ArrayList<String>();
    ArrayAdapter<AndroidVersion> adapter;
    ListView lista;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       // txmundo = (TextView) findViewById(R.id.tmundo);
        lista = (ListView) findViewById(R.id.listaCosas);

    }
    @Override
    protected void onStart() {
        super.onStart();

        //final ArrayList<AndroidVersion> data = new ArrayList<>();

        final Retrofit[] retrofit = {new Retrofit.Builder()
                .baseUrl("http://api.learn2crack.com")
//                .baseUrl("https://earthquake.usgs.gov/fdsnws/event/1/query?")
                .addConverterFactory(GsonConverterFactory.create())
                .build()};
        RequestInterface request = retrofit[0].create(RequestInterface.class);
        Call<JSONResponse> call = request.getJSON();
        call.enqueue(new Callback<JSONResponse>() {
            @Override
            public void onResponse(Call<JSONResponse> call, Response<JSONResponse> response) {

                JSONResponse jsonResponse = response.body();
                ArrayList a =new ArrayList<AndroidVersion>(Arrays.asList(jsonResponse.getAndroid()));
                Iterator<AndroidVersion> it = a.iterator();
                Log.e("Resultados: ", ((AndroidVersion)a.get(0)).getName() +" ");
                Toast.makeText(getApplicationContext(),((AndroidVersion)a.get(0)).getName() +" ",Toast.LENGTH_LONG).show();
               // txmundo.setText("helado");
              //  txmundo.setText(((AndroidVersion)a.get(0)).getName()+" "+((AndroidVersion)a.get(0)).getApi());
                while(it.hasNext()) {

                    arrayList.add(it.next());

                }
                adapter = new ArrayAdapter<>(getApplicationContext(),R.layout.support_simple_spinner_dropdown_item, arrayList);
                lista.setAdapter(adapter);

            }

            @Override
            public void onFailure(Call<JSONResponse> call, Throwable t) {
                Log.d("Error:::: ",t.getMessage());
            }
        });
       /* final String BASE_URL = "https://api.learn2crack.com" ;//http://rest-service.guides.spring.io";
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        GreetingService service = retrofit.create(GreetingService.class);

        Call<Greeting> call = service.getGreeting();

        call.enqueue(new Callback<Greeting>() {
            @Override
            public void onResponse(Call<Greeting> call, Response<Greeting> response) {
                Greeting greeting = response.body();

                Log.e("/////////// ", greeting+"");
            }

            @Override
            public void onFailure(Call<Greeting> call, Throwable t) {
                Log.e(" Error:::::: ", t.getMessage());
            }
        });*/
    }
}
