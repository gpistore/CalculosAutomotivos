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
import br.com.gpistore.calculosautomotivos.fragments.BicosFragment;
import br.com.gpistore.calculosautomotivos.fragments.CambioFragment;
import br.com.gpistore.calculosautomotivos.fragments.ConfigFragment;
import br.com.gpistore.calculosautomotivos.fragments.RlFragment;
import br.com.gpistore.calculosautomotivos.fragments.RodaFragment;
import br.com.gpistore.calculosautomotivos.fragments.TaxaFragment;
import br.com.gpistore.calculosautomotivos.fragments.TorqueFragment;
import br.com.gpistore.calculosautomotivos.fragments.VmpFragment;
import common.Avaliacao;
import common.Propaganda;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    Toolbar toolbar;
    private Propaganda ads;
    NavigationView navigationView;
    Avaliacao avaliacao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setup();
        setSupportActionBar(toolbar);
        MainFragment Main = new MainFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.mainframe, Main).commit();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
        this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        if (drawer != null) {
            drawer.setDrawerListener(toggle);
        }
        toggle.syncState();
        avaliacao.mostraAvaliacao();
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        if (navigationView != null) {
            navigationView.setNavigationItemSelectedListener(this);
        }
    }

    protected void setup(){
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        ads = new Propaganda(this);
        avaliacao = new Avaliacao(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer != null && drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        switch (id){
            case  (R.id.rl):
                RlFragment RL = new RlFragment();
                getSupportFragmentManager().beginTransaction().replace(R.id.mainframe, RL).commit();
                toolbar.setTitle(R.string.opt_rl);
                ads.mostra();
                break;
            case (R.id.vmp):
                VmpFragment Vmp = new VmpFragment();
                getSupportFragmentManager().beginTransaction().replace(R.id.mainframe, Vmp).commit();
                toolbar.setTitle(R.string.opt_vmp);
                ads.mostra();
                break;
            case (R.id.rodas):
                RodaFragment Roda = new RodaFragment();
                getSupportFragmentManager().beginTransaction().replace(R.id.mainframe, Roda).commit();
                toolbar.setTitle(R.string.opt_rodas);
                ads.mostra();
                break;
            case (R.id.bicos):
                BicosFragment bicos = new BicosFragment();
                getSupportFragmentManager().beginTransaction().replace(R.id.mainframe, bicos).commit();
                toolbar.setTitle(R.string.opt_bicos);
                ads.mostra();
                break;
            case (R.id.cambio):
                CambioFragment cambio = new CambioFragment();
                getSupportFragmentManager().beginTransaction().replace(R.id.mainframe, cambio).commit();
                toolbar.setTitle(R.string.opt_cambio);
                ads.mostra();
                break;
            case (R.id.taxa):
                TaxaFragment taxa = new TaxaFragment();
                getSupportFragmentManager().beginTransaction().replace(R.id.mainframe, taxa).commit();
                toolbar.setTitle(R.string.opt_taxa);
                ads.mostra();
                break;
            case (R.id.torque):
                TorqueFragment torque = new TorqueFragment();
                getSupportFragmentManager().beginTransaction().replace(R.id.mainframe, torque).commit();
                toolbar.setTitle(R.string.opt_torque);
                ads.mostra();
                break;
            case (R.id.configuracoes):
                ConfigFragment config = new ConfigFragment();
                getSupportFragmentManager().beginTransaction().replace(R.id.mainframe, config).commit();
                toolbar.setTitle(R.string.opt_config);
                break;
            default:
                MainFragment Main = new MainFragment();
                getSupportFragmentManager().beginTransaction().replace(R.id.mainframe, Main).commit();
                toolbar.setTitle(R.string.app_name);
                navigationView.setCheckedItem(R.id.item_none);
                DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                if (drawer != null) {
                    drawer.closeDrawer(GravityCompat.START);
                }
                ads.mostra();
                break;
        }
        //Clique na imagem superior
        ImageView mainimg = (ImageView) findViewById(R.id.mainimg);
        if (mainimg != null) {
            mainimg.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    MainFragment Main = new MainFragment();
                    getSupportFragmentManager().beginTransaction().replace(R.id.mainframe, Main).commit();
                    toolbar.setTitle(R.string.app_name);
                    navigationView.setCheckedItem(R.id.item_none);
                    DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                    assert drawer != null;
                    drawer.closeDrawer(GravityCompat.START);
                    ads.mostra();

                }
            });
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer != null) {
            drawer.closeDrawer(GravityCompat.START);
        }
        return true;
}
}