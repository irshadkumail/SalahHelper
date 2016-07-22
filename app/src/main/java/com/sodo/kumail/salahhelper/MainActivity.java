package com.sodo.kumail.salahhelper;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Typeface;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity implements LocationListener {

    Toolbar toolbar;
    TabLayout tabLayout;
    TextView toolbarHeading;
    ViewPager viewPager;
    SalahPageAdapter salahPageAdapter;
    LocationManager locationManager;
    Location selectedLocation;
    TimingFragment timingFragment;
    QiblaFragment qiblaFragment;
    DrawerLayout drawerLayout;
    RelativeLayout relativeLayout;
    Button retryButton;
    //The minimum distance to change Updates in meters
    private static final long MIN_DISTANCE_CHANGE_FOR_UPDATES = 10; // 10 meters

    // The minimum time between updates in milliseconds
    private static final long MIN_TIME_BW_UPDATES = 1000 * 60 * 1; // 1 minute

    ActionBarDrawerToggle actionBarDrawerToggle;

    public static final int REQUEST_CODE = 101;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();


    }

    public void startOver() {

        tabLayout.setVisibility(View.VISIBLE);
        viewPager.setVisibility(View.VISIBLE);
        // drawerLayout.setVisibility(View.INVISIBLE);
        //  setSupportActionBar(toolbar);

        relativeLayout.setVisibility(View.INVISIBLE);



        initLocation();

        drawerLayout.addDrawerListener(actionBarDrawerToggle);

        viewPager.setAdapter(salahPageAdapter);
        setSupportActionBar(toolbar);
        tabLayout.setupWithViewPager(viewPager);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Typeface typeface = Typeface.createFromAsset(getAssets(), "ArabDances.ttf");
        toolbarHeading.setTypeface(typeface);
    }

    public void init() {

        timingFragment = new TimingFragment();
        qiblaFragment = new QiblaFragment();
        salahPageAdapter = new SalahPageAdapter(getSupportFragmentManager());
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        tabLayout = (TabLayout) findViewById(R.id.tablayout);
        toolbarHeading = (TextView) findViewById(R.id.toolbarHeading);
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.string_open, R.string.string_close);
        relativeLayout = (RelativeLayout) findViewById(R.id.noInternet_layout);
       // relativeLayout.setVisibility(View.INVISIBLE);
        retryButton= (Button) findViewById(R.id.internet_retry_button);

        if (isInternetON())
            startOver();
        else
            noInternetDetected();


    }

    public void noInternetDetected() {
        tabLayout.setVisibility(View.INVISIBLE);
        viewPager.setVisibility(View.INVISIBLE);
        // drawerLayout.setVisibility(View.INVISIBLE);
        //  setSupportActionBar(toolbar);


        relativeLayout.setVisibility(View.VISIBLE);
        retryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isInternetON())
                    startOver();
                else
                    noInternetDetected();

            }
        });


    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        actionBarDrawerToggle.syncState();
    }


    public void initLocation() {

        /*
        if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {

            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling

                Toast.makeText(this, "No Permission GPS", Toast.LENGTH_SHORT).show();



                return;
            }
            Log.d("Irshad","Aaagaya");


           locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, MIN_TIME_BW_UPDATES, MIN_DISTANCE_CHANGE_FOR_UPDATES, this);

            locationManager.addGpsStatusListener(new GpsStatus.Listener() {
                @Override
                public void onGpsStatusChanged(int event) {
                    Log.d("iRSHAD",event+"");
                }
            });

        } else {


            if (locationManager.isProviderEnabled(NETWORK_PROVIDER))
                if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    Toast.makeText(this, "No Permission Network", Toast.LENGTH_SHORT).show();
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    return;


                }

            locationManager.requestLocationUpdates(NETWORK_PROVIDER, MIN_TIME_BW_UPDATES, MIN_DISTANCE_CHANGE_FOR_UPDATES, this);

        }
       // Location location=locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        //Toast.makeText(this,location.toString(),Toast.LENGTH_LONG);
    */

        //Criteria criteria = new Criteria();
        //String provider = locationManager.getBestProvider(criteria, true);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling

        }
        // Log.d("iRSHAD", provider.toString());


        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, MIN_TIME_BW_UPDATES, MIN_DISTANCE_CHANGE_FOR_UPDATES, this);
        Log.d("Irshad-PASSIVE", locationManager.getLastKnownLocation(LocationManager.PASSIVE_PROVIDER).toString());
    }

    @Override
    public void onLocationChanged(Location location) {

        Log.d("IRSHAD-NETWORK", location.toString());
        if (selectedLocation == null || selectedLocation != location) {
            selectedLocation = location;
            try {
                Geocoder geocoder = new Geocoder(this, Locale.getDefault());
                List<Address> addresses = geocoder.getFromLocation(selectedLocation.getLatitude(), selectedLocation.getLongitude(), 1);
                String city = addresses.get(0).getLocality();
                Toast.makeText(this, city, Toast.LENGTH_SHORT).show();
                timingFragment.createUrl(city);


            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

        Log.d("Irshad", provider + "," + status);
    }

    @Override
    public void onProviderEnabled(String provider) {
        Log.d("Irshad", "Provider Enabled");

    }

    @Override
    public void onProviderDisabled(String provider) {

        Log.d("Irshad", "Provider Disabled");

    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();

        menuInflater.inflate(R.menu.menu, menu);

        return true;
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        int id = menuItem.getItemId();

        if (actionBarDrawerToggle.onOptionsItemSelected(menuItem)) {
            return true;
        }

        if (id == R.id.menu_settings) {
            Toast.makeText(this, "Setting Pressed", Toast.LENGTH_SHORT).show();
        }
        if (id == R.id.menu_search) {
            Toast.makeText(this, "Search Pressed", Toast.LENGTH_SHORT).show();

        }

        return super.onOptionsItemSelected(menuItem);
    }

    public boolean isInternetON() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

        return (networkInfo != null && networkInfo.isConnected());

    }

    public void onSalahClicked(int id)
    {
        Log.d("Irshad","Clicked-->"+id);
       startActivity(new Intent(this,SalahSettingActivity.class));
    }


    class SalahPageAdapter extends FragmentStatePagerAdapter {

        public SalahPageAdapter(FragmentManager fragmentManager) {
            super(fragmentManager);
        }

        public Fragment getItem(int pos) {


            if (pos == 0) {
                return timingFragment;
            } else if (pos == 1) {
                return qiblaFragment;
            }

            return null;
        }

        public int getCount() {
            return 2;

        }

        public CharSequence getPageTitle(int pos) {
            if (pos == 0)
                return "TIMINGS";
            if (pos == 1)
                return "QIBLA";

            return null;
        }
    }


}
