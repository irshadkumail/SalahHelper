package com.sodo.kumail.salahhelper;

import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

/**
 * Created by kumail on 7/16/2016.
 */

public class MyViewHolder extends RecyclerView.ViewHolder {

    TextView salahTime,salahText;
    View view;

    OnSalahSelectedListener onSalahSelectedListener;

    public void setOnSalahSelectedListener(OnSalahSelectedListener onSalahSelectedListener)
    {
        this.onSalahSelectedListener=onSalahSelectedListener;
    }

    public  MyViewHolder(View view)
    {
        super(view);

        this.view=view;
        /*
        salahText= (TextView) view.findViewById(R.id.recycler_text);
        salahTime= (TextView) view.findViewById(R.id.recycler_time);

        Typeface typeface=Typeface.createFromAsset(view.getContext().getAssets(),"ArabDances.ttf");
        salahText.setTypeface(typeface);
        salahTime.setTypeface(typeface);
       // Animation animation= AnimationUtils.loadAnimation(view.getContext(),android.R.anim.slide_in_left);
        //view.startAnimation(animation);
*/
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onSalahSelectedListener.onSalahSelected(getPosition());

            }
        });

    }



    public interface OnSalahSelectedListener{

        public void onSalahSelected(int id);
    }
}
