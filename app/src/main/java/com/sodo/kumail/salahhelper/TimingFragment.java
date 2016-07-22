package com.sodo.kumail.salahhelper;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by kumail on 7/15/2016.
 */

public class TimingFragment extends Fragment implements MyViewHolder.OnSalahSelectedListener {


    RecyclerView recyclerView;
    TimingAdapter timingAdapter;
    RequestQueue requestQueue;
    ProgressBar progressBar;
    MainActivity mainActivity;
    public static int SELECTED_METHOD = 1;

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        requestQueue = VolleySingleton.getRequestQueue();


    }


    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        ArrayList<SalahClass> arrayList = new ArrayList<>();

        for (int i = 0; i < 6; i++) {
            SalahClass salahClass = new SalahClass("Fajr", "6:30PM");
            arrayList.add(salahClass);
        }

        View view = layoutInflater.inflate(R.layout.fragment_timing, viewGroup, false);
        //timingAdapter = new TimingAdapter(getActivity(), arrayList,this);
        //timingAdapter.setOnSalahSelectedListener(this);
        recyclerView = (RecyclerView) view.findViewById(R.id.timing_recycler);
        progressBar= (ProgressBar) view.findViewById(R.id.timing_progress);
        progressBar.setVisibility(View.VISIBLE);
     //   recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        //recyclerView.setAdapter(timingAdapter);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(),2));


        return view;
    }
    public void onActivityCreated(Bundle bundle)
    {
        super.onActivityCreated(bundle);
        mainActivity= (MainActivity) getActivity();

    }

    public void createUrl(String city) {

        String url="http://muslimsalat.com/"+city+"/"+SELECTED_METHOD+".json?key=b705a33b37684ee411df05d4981dc8e3";

        getJson(url);

    }

    public void getJson(String url) {

        timingAdapter= new TimingAdapter(getActivity(),null,TimingFragment.this);
        recyclerView.setAdapter(timingAdapter);
        progressBar.setVisibility(View.INVISIBLE);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                timingAdapter= new TimingAdapter(getActivity(),parseJson(response),TimingFragment.this);
                recyclerView.setAdapter(timingAdapter);
                progressBar.setVisibility(View.INVISIBLE);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        requestQueue.add(jsonObjectRequest);
    }

    public ArrayList<SalahClass> parseJson(JSONObject jsonObject) {


        String[] names={"Fajr","Sunrise","Zuhr","Asar","Maghrib","Isha"};
        ArrayList<SalahClass> responseList=new ArrayList<>();
        try {
            JSONArray jsonArray=jsonObject.getJSONArray("items");
            JSONObject currentObject=jsonArray.getJSONObject(0);


            responseList.add(new SalahClass(names[0],currentObject.getString("fajr")));
            responseList.add(new SalahClass(names[1],currentObject.getString("shurooq")));
            responseList.add(new SalahClass(names[2],currentObject.getString("dhuhr")));
            responseList.add(new SalahClass(names[3],currentObject.getString("asr")));
            responseList.add(new SalahClass(names[4],currentObject.getString("maghrib")));
            responseList.add(new SalahClass(names[5],currentObject.getString("isha")));



        } catch (JSONException e) {
            e.printStackTrace();
        }

        return responseList;
    }

    @Override
    public void onSalahSelected(int id) {
       // Log.d("Irshad","Clicked-->"+id);
    mainActivity.onSalahClicked(id);
    }
}
