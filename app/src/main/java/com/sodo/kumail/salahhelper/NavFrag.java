package com.sodo.kumail.salahhelper;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

/**
 * Created by kumail on 7/15/2016.
 */

public class NavFrag extends Fragment {

    ListView listView;
    String array[]={"Change City","School of Thought","Namaz Rules","Qibla Disclaimer"};
    NavListAdapter navListAdapter;

    public void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup group,Bundle bundle)
    {
       View view= layoutInflater.inflate(R.layout.nav_frag,group,false);
        listView= (ListView) view.findViewById(R.id.nav_list);
        navListAdapter= new NavListAdapter(getActivity(),array);
        listView.setAdapter(navListAdapter);

        return view;
    }

    class NavListAdapter extends BaseAdapter
    {
        Context context;
        String[] array;
        NavListAdapter(Context context,String[] array)
        {
            this.context=context;
            this.array=array;
        }

        public int getCount()
        {
            return array.length;

        }
        public Object getItem(int id)
        {
            return array[id];

        }
        public long getItemId(int id)
        {
            return id;
        }
        public View getView(int pos,View convertView,ViewGroup parent)
        {
            LayoutInflater layoutInflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View view=layoutInflater.inflate(R.layout.nav_row,parent,false);

            TextView listText= (TextView) view.findViewById(R.id.nav_list_text);
            ImageView listIcon= (ImageView) view.findViewById(R.id.nav_list_icon);
            listText.setText(array[pos]);

            return view;
        }


    }
}
