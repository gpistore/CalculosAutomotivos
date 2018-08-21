package br.com.gpistore.calculosautomotivos;


import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.support.v7.widget.Toolbar;

import br.com.gpistore.calculosautomotivos.fragments.BicosFragment;
import br.com.gpistore.calculosautomotivos.fragments.CambioFragment;
import br.com.gpistore.calculosautomotivos.fragments.ConfigFragment;
import br.com.gpistore.calculosautomotivos.fragments.RlFragment;
import br.com.gpistore.calculosautomotivos.fragments.RodaFragment;
import br.com.gpistore.calculosautomotivos.fragments.TaxaFragment;
import br.com.gpistore.calculosautomotivos.fragments.TorqueFragment;
import br.com.gpistore.calculosautomotivos.fragments.VmpFragment;

public class MainFragment extends Fragment {
    View view;
    ImageView imgrl;
    ImageView imgvmp;
    ImageView imgcambio;
    ImageView imgrodas;
    ImageView imgbicos;
    ImageView imgtaxa;
    ImageView imgtorque;
    ImageView imgsobre;
    NavigationView navigationView;
    Toolbar toolbar;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_main, container, false);
        navigationView = ((MainActivity) getActivity()).navigationView;
        toolbar = ((MainActivity) getActivity()).toolbar;
        setup();
        setupactions();

        return view;
    }

    private void setup(){
        imgrl = (ImageView) view.findViewById(R.id.imgrl);
        imgvmp = (ImageView) view.findViewById(R.id.imgvmp);
        imgcambio = (ImageView) view.findViewById(R.id.imgcambio);
        imgrodas = (ImageView) view.findViewById(R.id.imgrodas);
        imgbicos = (ImageView) view.findViewById(R.id.imgbicos);
        imgtaxa = (ImageView) view.findViewById(R.id.imgtaxa);
        imgtorque = (ImageView) view.findViewById(R.id.imgtorque);
        imgsobre = (ImageView) view.findViewById(R.id.imgsobre);
    }

    private void setupactions(){
        imgrl.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                RlFragment RL = new RlFragment();
                getFragmentManager().beginTransaction().replace(R.id.mainframe, RL).commit();
                navigationView.setCheckedItem(R.id.rl);
                toolbar.setTitle(R.string.opt_rl);
            }
        });
        imgvmp.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                VmpFragment VMP = new VmpFragment();
                getFragmentManager().beginTransaction().replace(R.id.mainframe, VMP).commit();
                navigationView.setCheckedItem(R.id.vmp);
                toolbar.setTitle(R.string.opt_vmp);
            }
        });
        imgcambio.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                CambioFragment cambio = new CambioFragment();
                getFragmentManager().beginTransaction().replace(R.id.mainframe, cambio).commit();
                navigationView.setCheckedItem(R.id.cambio);
                toolbar.setTitle(R.string.opt_cambio);
            }
        });
        imgrodas.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                RodaFragment Roda = new RodaFragment();
                getFragmentManager().beginTransaction().replace(R.id.mainframe, Roda).commit();
                navigationView.setCheckedItem(R.id.rodas);
                toolbar.setTitle(R.string.opt_rodas);
            }
        });

        imgbicos.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                BicosFragment Bicos = new BicosFragment();
                getFragmentManager().beginTransaction().replace(R.id.mainframe, Bicos).commit();
                navigationView.setCheckedItem(R.id.bicos);
                toolbar.setTitle(R.string.opt_bicos);
            }
        });

        imgtaxa.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                TaxaFragment Taxa = new TaxaFragment();
                getFragmentManager().beginTransaction().replace(R.id.mainframe, Taxa).commit();
                navigationView.setCheckedItem(R.id.taxa);
                toolbar.setTitle(R.string.opt_taxa);
            }
        });

        imgtorque.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                TorqueFragment Torque = new TorqueFragment();
                getFragmentManager().beginTransaction().replace(R.id.mainframe, Torque).commit();
                navigationView.setCheckedItem(R.id.torque);
                toolbar.setTitle(R.string.opt_torque);
            }
        });

        imgsobre.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                ConfigFragment Config= new ConfigFragment();
                getFragmentManager().beginTransaction().replace(R.id.mainframe, Config).commit();
                navigationView.setCheckedItem(R.id.configuracoes);
                toolbar.setTitle(R.string.opt_config);
            }
        });
    }

}