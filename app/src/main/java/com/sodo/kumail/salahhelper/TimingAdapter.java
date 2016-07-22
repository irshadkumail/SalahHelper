package com.sodo.kumail.salahhelper;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by kumail on 7/15/2016.
 */

public class TimingAdapter extends RecyclerView.Adapter<MyViewHolder> {

    Context context;
    ArrayList<SalahClass> arrayList;
    TimingFragment timingFragment;


    public TimingAdapter(Context context, ArrayList<SalahClass> arrayList,TimingFragment timingFragment)
    {
        this.context=context;
        this.arrayList=arrayList;
        this.timingFragment=timingFragment;

    }

    public MyViewHolder onCreateViewHolder(ViewGroup parent,final int p)
    {
        LayoutInflater layoutInflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        //View view=layoutInflater.inflate(R.layout.recycler_row,parent,false);
        View view=layoutInflater.inflate(R.layout.demo_row,parent,false);
        MyViewHolder myViewHolder= new MyViewHolder(view);
        myViewHolder.setOnSalahSelectedListener(timingFragment);


        return myViewHolder;
    }
    public void onBindViewHolder(MyViewHolder viewHolder,int p)
    {
        /*
        viewHolder.salahText.setText(arrayList.get(p).salahName);
        viewHolder.salahTime.setText(arrayList.get(p).salahTime);
        Animation animation= AnimationUtils.loadAnimation(context, android.R.anim.slide_in_left);
        viewHolder.view.startAnimation(animation);
*/


    }
    public int getItemCount()
    {

        return 6;
        //return arrayList.size();
    }

}
