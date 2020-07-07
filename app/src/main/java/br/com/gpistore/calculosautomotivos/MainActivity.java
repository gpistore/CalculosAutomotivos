package br.com.gpistore.calculosautomotivos;

import android.os.Bundle;
import com.google.android.material.navigation.NavigationView;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import br.com.gpistore.calculosautomotivos.fragments.BicosFragment;
import br.com.gpistore.calculosautomotivos.fragments.CambioFragment;
import br.com.gpistore.calculosautomotivos.fragments.ConfigFragment;
import br.com.gpistore.calculosautomotivos.fragments.MainFragment;
import br.com.gpistore.calculosautomotivos.fragments.RlFragment;
import br.com.gpistore.calculosautomotivos.fragments.RodaFragment;
import br.com.gpistore.calculosautomotivos.fragments.TaxaFragment;
import br.com.gpistore.calculosautomotivos.fragments.TorqueFragment;
import br.com.gpistore.calculosautomotivos.fragments.VmpFragment;
import common.Avaliacao;
import common.Propaganda;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    public NavigationView navigationView;
    public Toolbar toolbar;
    public Propaganda ads;
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
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();

        switch (id){
            case  (R.id.rl):
                RlFragment RL = new RlFragment();
                ft.replace(R.id.mainframe, RL);
                ft.addToBackStack("pilha");
                ft.commit();
                ads.mostra();
                break;
            case (R.id.vmp):
                VmpFragment Vmp = new VmpFragment();
                ft.replace(R.id.mainframe, Vmp);
                ft.addToBackStack("pilha");
                ft.commit();
                ads.mostra();
                break;
            case (R.id.rodas):
                RodaFragment Roda = new RodaFragment();
                ft.replace(R.id.mainframe, Roda);
                ft.addToBackStack("pilha");
                ft.commit();
                ads.mostra();
                break;
            case (R.id.bicos):
                BicosFragment bicos = new BicosFragment();
                ft.replace(R.id.mainframe, bicos);
                ft.addToBackStack("pilha");
                ft.commit();
                ads.mostra();
                break;
            case (R.id.cambio):
                CambioFragment cambio = new CambioFragment();
                ft.replace(R.id.mainframe, cambio);
                ft.addToBackStack("pilha");
                ft.commit();
                ads.mostra();
                break;
            case (R.id.taxa):
                TaxaFragment taxa = new TaxaFragment();
                ft.replace(R.id.mainframe, taxa);
                ft.addToBackStack("pilha");
                ft.commit();
                ads.mostra();
                break;
            case (R.id.torque):
                TorqueFragment torque = new TorqueFragment();
                ft.replace(R.id.mainframe, torque);
                ft.addToBackStack("pilha");
                ft.commit();
                ads.mostra();
                break;
            case (R.id.configuracoes):
                ConfigFragment config = new ConfigFragment();
                ft.replace(R.id.mainframe, config);
                ft.addToBackStack("pilha");
                ft.commit();
                break;
            default:
                MainFragment Main = new MainFragment();
                ft.replace(R.id.mainframe, Main);
                ft.addToBackStack("pilha");
                ft.commit();
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