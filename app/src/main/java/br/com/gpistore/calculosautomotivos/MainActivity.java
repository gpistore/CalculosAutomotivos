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

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        MainFragment Main = new MainFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.mainframe, Main).commit();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
        this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
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
        } else if (id == R.id.vmp) {
            VmpFragment Vmp = new VmpFragment();
            getSupportFragmentManager().beginTransaction().replace(R.id.mainframe, Vmp).commit();
        } else if (id == R.id.rodas) {
            RodaFragment Roda = new RodaFragment();
            getSupportFragmentManager().beginTransaction().replace(R.id.mainframe, Roda).commit();
//      } else if (id == R.id.bicos) {
        } else if (id == R.id.cambio) {
            CambioFragment cambio = new CambioFragment();
            getSupportFragmentManager().beginTransaction().replace(R.id.mainframe, cambio).commit();
        }

        ImageView mainimg = (ImageView) findViewById(R.id.mainimg);
        mainimg.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                MainFragment Main = new MainFragment();
                getSupportFragmentManager().beginTransaction().replace(R.id.mainframe, Main).commit();
                DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                drawer.closeDrawer(GravityCompat.START);

            }
        });

        /*
        * Como calcular a velocidade real (quase real na realidade) usando uma calculadora boqueta que vc ganha quando compra um barbeador:

        Pneu: 185/60 R14
        18,5 * 0,6 * 2 = 22,2 cm ( * 2 é pq o diâmetro do conjunto roda/pneu é perfil + roda + perfil, portando perfil * 2 )
        Roda aro 14 polegadas
        14 * 2,54 = 35,56 cm

        22,2 + 35,56 = 57,76 cm de diâmetro

        Circunferência do pneu: 57,76 * 3,1416 ( pi ) = 181,45 cm = 1,8145 m

        Fórmula :
        (Circ.do.pneu * RPM * 0,06) / (diferencial * marcha) = velocidade

        (1,8145 * 3000 * 0,06) / ( 4,11 * 0,68 ) = 116,15 km/h

        0,06 é um fator de conversão de m/min para km/h

        a RPM vc utiliza a que vc quiser.... por exemplo: se quiser saber a velocidade do carro a 5500 RPM é só colocar este valor na fórmula... (para saber a velocidade máxima é só colocar a RPM em que se atinge a potência máxima do motor)
        *
        * */
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
