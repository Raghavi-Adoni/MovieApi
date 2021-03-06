package com.raghavi.movieapi;

import android.app.DownloadManager;
import android.app.VoiceInteractor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    // TextView result;
    String data = "";
    String API_URL = "https://api.themoviedb.org/3/movie/now_playing?api_key=356198a284dfa824f91f9aab8aa3e1e8&language=en-US&page=1";
    //ListView listView;
    List<String> list;
    ArrayAdapter<String> adapter;
    RequestQueue requestQueue;
    ItemData[] dataSet;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        list = new ArrayList<String>();
        requestQueue = Volley.newRequestQueue(this);
        //result = (TextView) findViewById(R.id.jsonData);
        //listView = (ListView) findViewById(R.id.lview);
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, list);


        // JsonObjectRequest objectRequest=new JsonObjectRequest(Request.Method.GET,www.api.themoviedb.org/3/movie/550?api_key=356198a284dfa824f91f9aab8aa3e1e8)
        RequestQueue queue = Volley.newRequestQueue(this);

        StringRequest stringRequest = new StringRequest(Request.Method.GET, API_URL,

                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //This method is called when the response is gotten from the URL.
                        // Display the response
                        //result.setText("Response is: " + response);
                        data = response;
                        JSONArray arr = null;
                        try {
                            JSONObject obj = new JSONObject(data);
                            arr = obj.getJSONArray("results");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        dataSet = new ItemData[arr.length()];
                        for (int i = 0; i < arr.length(); i++) {
                            String info = null;
                            try {
                                JSONObject object = arr.getJSONObject(i);
                                info = object.getString("title");

                                list.add(info);
                                dataSet[i] = new ItemData(info, R.drawable.events);
                               // itemDataList.add(new ItemData(info, R.drawable.events));
                                // info = arr.getJSONObject(i).getString("title");
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }

                        adapter.notifyDataSetChanged();
                        //                    listView.setAdapter(adapter);
                        Log.d("output", String.valueOf(list));
                        //

                      /*  for (int i = 0; i < list.size(); i++) {
                            dataSet[i]= new ItemData(list.get(i), R.drawable.events);
                        }

                       dataSet[0]= new ItemData(list.get(0), R.drawable.events);
                        dataSet[1]= new ItemData(list.get(1), R.drawable.events);
                        dataSet[2]= new ItemData(list.get(2), R.drawable.events);
*/
                        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
                        adapter adapterobj = new adapter(dataSet);

                        recyclerView.setHasFixedSize(true);
                        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                        recyclerView.setAdapter(adapterobj);
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                //result.setText("That didn't work!");
            }

        });

        // Add the request to the RequestQueue.


        queue.add(stringRequest);


    }
}
/*
        JSONArray arr = null;
        try {
            JSONObject obj = new JSONObject(data);
            arr = obj.getJSONArray("results");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        List<String> list = new ArrayList<String>();
        for (int i = 0; i < arr.length(); i++) {
            String info = null;
            try {
                info = arr.getJSONObject(i).getString("title");
            } catch (JSONException e) {
                e.printStackTrace();
            }
            list.add(info);
        }
        //  ArrayAdapter adapter = new ArrayAdapter<String>(this,android.R.layout.list_single,list);
        ListView listView = (ListView) findViewById(R.id.lview);
        //listView.setAdapter(adapter);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.list_single, list);
        listView.setAdapter(adapter);
    }
*/
   /* void parseJson() {
        JSONArray arr = null;
        try {
            arr = new JSONArray(data);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        List<String> list = new ArrayList<String>();
        for (int i = 0; i < arr.length(); i++) {
            String info = null;
            try {
                info = arr.getJSONObject(i).getString("movie") + arr.getJSONObject(i).getString("rating");
            } catch (JSONException e) {
                e.printStackTrace();
            }
            list.add(info);
        }
    }*/
