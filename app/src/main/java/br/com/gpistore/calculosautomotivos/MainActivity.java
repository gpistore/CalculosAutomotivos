package br.com.gpistore.calculosautomotivos;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    InterstitialAd mInterstitialAd;
    boolean click = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        MainFragment Main = new MainFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.mainframe, Main).commit();


        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId("ca-app-pub-2664724094770763/3644317232");
        requestNewInterstitial();
        mInterstitialAd.setAdListener(new AdListener() {
            //@Override
            public void onAdClosed() {
                requestNewInterstitial();
            }
        });




        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
        this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


    }

    private void requestNewInterstitial() {
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice("3E57B5136D19E0854E00586D5E6A581A")
                .build();

        mInterstitialAd.loadAd(adRequest);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.rl) {
            RlFragment RL = new RlFragment();
            getSupportFragmentManager().beginTransaction().replace(R.id.mainframe, RL).commit();
            if (mInterstitialAd.isLoaded() && click == true ) {
                mInterstitialAd.show();
                click = false;
            }else{
                click = true;
            }
        } else if (id == R.id.vmp) {
            VmpFragment Vmp = new VmpFragment();
            getSupportFragmentManager().beginTransaction().replace(R.id.mainframe, Vmp).commit();
            if (mInterstitialAd.isLoaded() && click == true) {
                mInterstitialAd.show();
                click = false;
            }else{
                click = true;
            }
        } else if (id == R.id.rodas) {
            RodaFragment Roda = new RodaFragment();
            getSupportFragmentManager().beginTransaction().replace(R.id.mainframe, Roda).commit();
            if (mInterstitialAd.isLoaded() && click == true)  {
                mInterstitialAd.show();
                click = false;
            }else{
                click = true;
            }
//      } else if (id == R.id.bicos) {
        } else if (id == R.id.cambio) {
            CambioFragment cambio = new CambioFragment();
            getSupportFragmentManager().beginTransaction().replace(R.id.mainframe, cambio).commit();
            if (mInterstitialAd.isLoaded() && click == true) {
                mInterstitialAd.show();
                click = false;
            }else{
                click = true;
            }
        }

        ImageView mainimg = (ImageView) findViewById(R.id.mainimg);
        mainimg.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                   MainFragment Main = new MainFragment();
                getSupportFragmentManager().beginTransaction().replace(R.id.mainframe, Main).commit();
                DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                drawer.closeDrawer(GravityCompat.START);
                if (mInterstitialAd.isLoaded() && click == true) {
                    mInterstitialAd.show();
                    click = false;
                }else{
                    click = true;
                }

            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
