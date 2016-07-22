package com.sodo.kumail.salahhelper;

import android.app.Activity;
import android.app.TimePickerDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.CheckBoxPreference;
import android.preference.EditTextPreference;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.FrameLayout;
import android.widget.TimePicker;
import android.widget.Toast;

/**
 * Created by kumail on 7/20/2016.
 */

public class SalahSettingActivity extends Activity {

    FrameLayout frameLayout;

    public void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        setContentView(R.layout.salah_layout);
        frameLayout= (FrameLayout) findViewById(R.id.frame);
        getFragmentManager().beginTransaction().add(R.id.frame,new FajrPrefFragment()).commit();

    }

    public static class FajrPrefFragment extends PreferenceFragment implements Preference.OnPreferenceClickListener,TimePickerDialog.OnTimeSetListener
    {
        CheckBoxPreference fajr_alarm;
        EditTextPreference alarm_edit_time;
        ListPreference alarm_list_tone;

        CheckBoxPreference fajr_noti;
        ListPreference noti_list;

        public void onCreate(Bundle bundle)
        {
            super.onCreate(bundle);
            addPreferencesFromResource(R.xml.fajr_salah_frag);
            init();
        }
        public void init()
        {
            fajr_alarm= (CheckBoxPreference) findPreference(getResources().getString(R.string.fajr_alarm));
            alarm_edit_time= (EditTextPreference) findPreference(getResources().getString(R.string.fajr_alarm_time));
            alarm_list_tone= (ListPreference) findPreference(getResources().getString(R.string.fajr_alarm_tone));
            fajr_alarm.setOnPreferenceClickListener(this);
            alarm_edit_time.setOnPreferenceClickListener(this);

            fajr_noti= (CheckBoxPreference) findPreference(getResources().getString(R.string.fajr_notification));
            noti_list= (ListPreference) findPreference(getResources().getString(R.string.fajr_notification_last_time));
            fajr_noti.setOnPreferenceClickListener(this);


        }

        @Override
        public boolean onPreferenceClick(Preference preference) {

            Log.d("Irshad","Preference Clicked-->"+preference.getKey());

            if (preference.getKey().equals(getResources().getString(R.string.fajr_alarm_time)))
            {
                Log.d("Irshad","Timer Initiated");
                TimePickerDialog timePickerDialog=new TimePickerDialog(getActivity(),this,12,0,false);
                timePickerDialog.show();

            }else if (true)
            {
                if (preference.getKey().equals(getResources().getString(R.string.fajr_alarm)))
                {
                    Log.d("Irshad","Alarm Clicked");
                    alarm_edit_time.setEnabled(true);
                    alarm_list_tone.setEnabled(true);

                }else if (preference.getKey().equals(getResources().getString(R.string.fajr_notification)))
                {
                    Log.d("Irshad","Noti Clicked");

                    noti_list.setEnabled(true);
                }


            }else
            if (!preference.isEnabled())
            {
                if (preference.getKey()==getResources().getString(R.string.fajr_alarm))
                {
                    alarm_edit_time.setEnabled(false);
                    alarm_list_tone.setEnabled(false);

                }else if (preference.getKey()==getResources().getString(R.string.fajr_notification))
                {
                    noti_list.setEnabled(false);
                }


            }



            return false;
        }

        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

            Toast.makeText(getActivity(),"Time is set as"+hourOfDay+":"+minute,Toast.LENGTH_SHORT).show();
        }
    }


}
