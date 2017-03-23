package br.com.gpistore.calculosautomotivos;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
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
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    public Toolbar toolbar;
    Propaganda prop;
    public NavigationView navigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
         prop = new Propaganda(this);
        final SharedPreferences prefs = getSharedPreferences("pref_calculadoraautomotiva", Context.MODE_PRIVATE);
            final int nracessos = prefs.getInt("qtdacessos",0);
        if (nracessos >=0) {
            SharedPreferences.Editor editor = prefs.edit();
            editor.putInt("qtdacessos", nracessos + 1);
            editor.apply();
        }

        final MainFragment Main = new MainFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.mainframe, Main).commit();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
        this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();


        if(nracessos > 3){
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
            alertDialog.setTitle(getString(R.string.classifique));
            alertDialog.setMessage(getString(R.string.texto_classifique));
            alertDialog.setPositiveButton(getString(R.string.btnpos), new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse("market://details?id=br.com.gpistore.calculosautomotivos"));
                    startActivity(intent);
                    SharedPreferences.Editor editor = prefs.edit();
                    editor.putInt("qtdacessos", -1);
                    editor.apply();
                }
            });
            alertDialog.setNegativeButton(getString(R.string.btnneg), new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    SharedPreferences.Editor editor = prefs.edit();
                    editor.putInt("qtdacessos", -1);
                    editor.apply();
                }
            });
            alertDialog.setNeutralButton(getString(R.string.btnneu), new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    SharedPreferences.Editor editor = prefs.edit();
                    editor.putInt("qtdacessos", 0);
                    editor.apply();
                }
            });

            alertDialog.setIcon(R.mipmap.ic_launcher);
            alertDialog.create();
            alertDialog.show();
        }

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


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
            toolbar.setTitle(R.string.calculo_rl);
            prop.mostra();
        } else if (id == R.id.vmp) {
            VmpFragment Vmp = new VmpFragment();
            getSupportFragmentManager().beginTransaction().replace(R.id.mainframe, Vmp).commit();
            toolbar.setTitle(R.string.calculo_vmp);
            prop.mostra();
        } else if (id == R.id.rodas) {
            RodaFragment Roda = new RodaFragment();
            getSupportFragmentManager().beginTransaction().replace(R.id.mainframe, Roda).commit();
            toolbar.setTitle(R.string.tamanho_rodas);
            prop.mostra();
          } else if (id == R.id.bicos) {
            BicosFragment bicos = new BicosFragment();
            getSupportFragmentManager().beginTransaction().replace(R.id.mainframe, bicos).commit();
            toolbar.setTitle(R.string.bicos);
            prop.mostra();
        } else if (id == R.id.cambio) {
            CambioFragment cambio = new CambioFragment();
            getSupportFragmentManager().beginTransaction().replace(R.id.mainframe, cambio).commit();
            toolbar.setTitle(R.string.calculo_cambio);
            prop.mostra();
        }else if (id == R.id.taxa) {
                TaxaFragment taxa = new TaxaFragment();
                getSupportFragmentManager().beginTransaction().replace(R.id.mainframe, taxa).commit();
                toolbar.setTitle(R.string.taxa);
            prop.mostra();
         }else if (id == R.id.configuracoes) {
            ConfigFragment config = new ConfigFragment();
            getSupportFragmentManager().beginTransaction().replace(R.id.mainframe, config).commit();
            toolbar.setTitle(R.string.config);
            prop.mostra();
        }else if (id == R.id.torque) {
            TorqueFragment torque = new TorqueFragment();
            getSupportFragmentManager().beginTransaction().replace(R.id.mainframe, torque).commit();
            toolbar.setTitle(R.string.torque);
            prop.mostra();
        }


        ImageView mainimg = (ImageView) findViewById(R.id.mainimg);
        mainimg.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                   MainFragment Main = new MainFragment();
                getSupportFragmentManager().beginTransaction().replace(R.id.mainframe, Main).commit();
                toolbar.setTitle(R.string.app_name);
                navigationView.setCheckedItem(R.id.item_none);
                DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                drawer.closeDrawer(GravityCompat.START);
                prop.mostra();

            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
